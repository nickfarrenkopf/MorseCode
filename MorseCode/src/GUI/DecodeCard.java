package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MorseCode.Controller;

import static Others.Constants.*;

/**
 * DecoddPanel is a JPanel that holds JComponents for playing and testing puzzles.
 * It contains several buttons for puzzle creating, playing, and reseting.
 * It also contains a JTextField so user can type answer.
 */
@SuppressWarnings("serial")
public class DecodeCard extends JPanel implements ActionListener {

	// Controller variable
	private Controller controller;

	// Text field for answer typing
	private JTextField answerBox;
	private JLabel answerLabel;
	
	// Buttons
	private JButton playButton;
	private JButton checkButton;
	private JButton newButton;

	/**
	 * Initializes decode panel containing several buttons for puzzle choosing/playing/reseting
	 * @param c - Controller
	 */
	public DecodeCard(Controller c)
	{
		// Sets controller
		controller = c;

		// Decode Text field and label
		answerBox = new JTextField("");
		answerBox.setMaximumSize(answerBoxSize);
		answerLabel = new JLabel("Answer: ");

		// Decode buttons
		playButton = new JButton("New Puzzle");
		checkButton = new JButton("Check Answer");
		newButton = new JButton("New Puzzle");

		// Adds action listeners
		playButton.addActionListener(this);
		checkButton.addActionListener(this);
		newButton.addActionListener(this);

		// Sets layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Adds things to panel
		add(Box.createVerticalGlue());
		add(newButton);
		add(Box.createVerticalGlue());
		add(playButton);
		add(Box.createVerticalGlue());
		add(answerBox);
		add(Box.createVerticalGlue());
		add(answerLabel);
		add(Box.createVerticalGlue());
		add(checkButton);
		add(Box.createVerticalGlue());
		
		// Centers components
		playButton.setAlignmentX(CENTER_ALIGNMENT);
		answerLabel.setAlignmentX(CENTER_ALIGNMENT);
		checkButton.setAlignmentX(CENTER_ALIGNMENT);
		newButton.setAlignmentX(CENTER_ALIGNMENT);
	}

	/**
	 * Action listeners for when buttons are clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// If reset button is clicked
		if (e.getSource() == newButton)
		{
			playButton.setText("Play Puzzle");
			controller.newPuzzle(controller.getScreen().getDifficultyGroup().getSelectedIndex());
			answerBox.setText("");
			answerLabel.setText("Answer: ");
		}

		// If play button is pressed
		if (e.getSource() == playButton)
			if (playButton.getText().equals("New Puzzle"))
				newButton.doClick();
			else
				controller.playPuzzle();

		// If check answer button is clicked
		if (e.getSource() == checkButton)
			if (controller.checkPuzzle(answerBox.getText()))
				answerLabel.setText("Answer: Correct!");	
			else 
				answerLabel.setText("Answer: " + controller.getPuzzle());
	}	
}