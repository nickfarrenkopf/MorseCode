package MorseCode;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import GUI.Frame;
import GUI.Screen;

import static Others.Constants.*;

/**
 * Controller is controller for Morse Code Program. Program allows user to play variety of puzzles,
 * random or chosen, in morse code and figure out what the text should be.
 * Later part will allow user to convert morse code to text.
 * @author Nick Farrenkopf
 */
public class Controller {
	
	/**
	 * 
	 * Main method for program start
	 * 
	 */
	public static void main(String[] args)
	{
		new Controller();
	}
	/**
	 * 
	 * 
	 * 
	 */

	// GUI variables
	private Screen screen;
	
	// Holds string that is puzzle
	private String puzzle;
	
	// Variables for holding morse code sounds
	private Clip dotClip;
	private Clip dashClip;

	// Variable to hold length of single unit
	private int unitLength;
	
	/**
	 * Initializes controller for morse code program. Creates and adds frame, then loads sound files
	 */
	public Controller()
	{
		// Creates frame and panel, adds to screen and updates
		Frame frame = new Frame();
		screen = new Screen(this);
		frame.add(screen);
		frame.revalidate();
		
		// Initialize some variables
		puzzle = "";
		loadClips();
	}	

	///// MORSE METHODS /////
	
	/**
	 * Loads dot and dash sound files for easy playing
	 */
	public void loadClips()
	{
		// Set to null
		dotClip = null;
		dashClip = null;
		unitLength = 0;
		puzzle = "Entered?";
		
		// Try to load file and play it
		try {
			// Load dot audio file
			InputStream dotStream = getClass().getResourceAsStream("/Dot.wav");
			BufferedInputStream dotbis = new BufferedInputStream(dotStream);
			AudioInputStream dotAudio = AudioSystem.getAudioInputStream(dotbis);
			dotClip = AudioSystem.getClip();
			dotClip.open(dotAudio);
			
			// Load dash audio file
			InputStream dashStream = Controller.class.getResourceAsStream("/Dash.wav");
			BufferedInputStream dashbis = new BufferedInputStream(dashStream);
			AudioInputStream dashAudio = AudioSystem.getAudioInputStream(dashbis);
			dashClip = AudioSystem.getClip();
			dashClip.open(dashAudio);

			// Find unit length
			unitLength = (int) (1000 * dotClip.getFrameLength() / dotClip.getFormat().getFrameRate()) + 1;
			
			// Play dot because first sound is always off in java
			dotClip.start();

		// Exception catch
		} catch (Exception e) {
			//puzzle = e.getMessage();
			e.printStackTrace();
		}
	}

	///// PUZZLE /////
	
	/**
	 * Creates new puzzle given difficulty level
	 * @param i - integer depicting difficulty level
	 */
	public void newPuzzle(int i)
	{
		// If chooses random puzzle given list
		ArrayList<String> puzzleList = Examples.getExamples(i);
		puzzle = puzzleList.get(new Random().nextInt(puzzleList.size()));
	}
	
	/**
	 * Sets puzzle to given string
	 * @param s - String
	 */
	public void setPuzzle(String s)
	{
		puzzle = s;
	}

	/**
	 * Return current string puzzle
	 * @return String
	 */
	public String getPuzzle()
	{
		return puzzle;
	}

	/**
	 * Checks if given text and puzzle are same text, return boolean
	 * @param s - String 
	 * @return boolean - String parameter and puzzle are same
	 */
	public boolean checkPuzzle(String s)
	{
		if (s.toLowerCase().equals(puzzle.toLowerCase()))
			return true;
		return false;
	}	

	/**
	 * Plays the current morse code puzzle by iterating through puzzle string and playing sounds
	 * setting timer for when next sound should play.
	 * Morse code has unit pause in between sounds, 3 units between characters, and 7 between words
	 */
	public void playPuzzle()
	{
		// Initialize needed variables
		int time = 0;
		String fileToPlay, s;
		String morseMessage = new MorseCode().toMorseCode(puzzle);
		Timer timer = new Timer();
		
		// Iterate through morse message characters
		for (int i=0; i<morseMessage.length(); i++)
		{
			// Get single character to play
			s = String.valueOf(morseMessage.charAt(i));
			
			// Reset file to play, then set file to play if dot or dash
			fileToPlay = "";
			if (s.equals("."))
				fileToPlay = "Dot";
			else if (s.equals("-"))
				fileToPlay = "Dash";

			// If file to play is not empty, schedule play file
			if (!fileToPlay.equals(""))
				timer.schedule(new PlayFile(fileToPlay), time);

			// Update timer
			if (s.equals("."))
				time += unitLength * 2;
			else if (s.equals("-"))
				time += unitLength * (dashLength + 1);
			else if (s.equals("c"))
				time += unitLength * (charLength - 1);
			else if (s.equals("s"))
				time += unitLength * (wordLength - 1);
		}
	}

	/**
	 * Timer task that plays given file name from preloaded sound files
	 */
	class PlayFile extends TimerTask {
		
		// Initializes timer task with file name
		private String fileName;
		PlayFile(String fileName)
		{
			this.fileName = fileName;
		}
		
		// Play file
		public void run()
		{	
			// Sets position to beginning of sound file, then plays
			if (fileName.equals("Dot")) {
				dotClip.setFramePosition(0);
				dotClip.start();
			} else if (fileName.equals("Dash")) {
				dashClip.setFramePosition(0);
				dashClip.start();
			}
		}
	}

	///// GUI /////
	
	/**
	 * Returns screen so cards can grab other variables
	 * @return Screen
	 */
	public Screen getScreen()
	{
		return screen;
	}
}