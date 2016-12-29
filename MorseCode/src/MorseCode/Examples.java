package MorseCode;
import java.util.ArrayList;

/**
 * Holds all examples of text to convert to morse code. ArryList for ease of adding.
 */
public class Examples {

	/**
	 * Returns ArrayList<String> holding example problems given a difficulty level
	 * @param level - int (0, 1, 2)
	 * @return ArrayList<String> - problems based on difficulty level
	 */
	public static ArrayList<String> getExamples(int level)
	{
		// Returns easy examples (letters and numbers found in morse code)
		if (level == 0)
		{
			ArrayList<String> easyEx = new ArrayList<>();
			MorseCode mc = new MorseCode();
			String[][] symbols = mc.getSymbols();
			for (int i=0; i<symbols.length; i++)
				easyEx.add(symbols[i][0]);
			return easyEx;
		}

		// Medium examples, one word
		if (level == 1)
		{
			ArrayList<String> mediumEx = new ArrayList<>();
			mediumEx.add("party");
			mediumEx.add("zoinks");
			mediumEx.add("farrenkopf");
			mediumEx.add("saxophone");
			mediumEx.add("library");
			mediumEx.add("excited");
			mediumEx.add("tango");
			mediumEx.add("waltz");
			mediumEx.add("foxtrot");
			mediumEx.add("windmill");
			mediumEx.add("limitless");
			mediumEx.add("marvel");
			mediumEx.add("ironman");
			mediumEx.add("thehulk");
			mediumEx.add("alanturing");
			mediumEx.add("mandelbrot");
			mediumEx.add("fractal");
			mediumEx.add("juliaset");
			mediumEx.add("movies");
			mediumEx.add("puppies");
			mediumEx.add("kittens");
			mediumEx.add("streetlight");
			mediumEx.add("manifesto");
			mediumEx.add("acapella");
			mediumEx.add("korean");
			mediumEx.add("sterling");
			mediumEx.add("morsecode");
			mediumEx.add("trombone");
			mediumEx.add("cello");
			mediumEx.add("trumpet");
			mediumEx.add("timpani");
			mediumEx.add("bonjovi");
			mediumEx.add("sassifraz");
			mediumEx.add("randomletters");
			mediumEx.add("hearthstone");
			mediumEx.add("pokemon");
			return mediumEx;
		}

		// Hard examples, a phrase
		if (level == 2)
		{
			ArrayList<String> hardEx = new ArrayList<>();
			hardEx.add("Mary had a little lamb");
			hardEx.add("Twinkle twinkle little star");
			hardEx.add("London bridge is falling down");
			hardEx.add("Ive been working round the railroad");
			hardEx.add("Jack and jill went up the hill");
			hardEx.add("Knick knack paddy whack");
			hardEx.add("Do you believe in life after love");
			hardEx.add("Guys dont you wish you were a zebra");
			hardEx.add("Pokemon gotta catch em all");
			hardEx.add("I knew you were trouble when you walked in");
			hardEx.add("Im so excited and I just cant hid it");
			hardEx.add("And when we fall we will fall together");
			hardEx.add("Toe to toe like david and goliath");
			hardEx.add("I want your girlfriend to be my girlfriend too");
			hardEx.add("Carry on my wayward son");
			return hardEx;
		}
		
		// Else
		System.out.println("Bad example selection.");
		return null;
	}
}