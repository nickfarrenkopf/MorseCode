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
 * PlayCard is an extra card that allows user to type a message and play it in morse code.
 * It contains a JTextField for message typing and a button to play the message.
 */
@SuppressWarnings("serial")
public class PlayCard extends JPanel implements ActionListener{

	// Controller variable
	private Controller controller;
	
	// JTextField so user can type message
	private JTextField messageField;
	
	// JButton so user can play message
	private JButton playButton;
	
	// Initializes PlayCard with components so user can type and play a string message
	public PlayCard(Controller c)
	{
		//Sets controller
		controller = c;
		
		// Message variables
		JLabel messageLabel = new JLabel("Message to play: ");
		messageField = new JTextField("");
		messageField.setPreferredSize(answerBoxSize);
		
		// Play button
		playButton = new JButton("Play message");
		playButton.addActionListener(this);
		
		// Sets layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Adds to panel
		JPanel panel = new JPanel();
		panel.add(messageLabel);
		panel.add(messageField);
		
		// Adds to card
		add(Box.createVerticalGlue());
		add(panel);
		add(playButton);
		add(Box.createVerticalGlue());
		
		panel.setAlignmentX(CENTER_ALIGNMENT);
		playButton.setAlignmentX(CENTER_ALIGNMENT);
	}

	/**
	 * Action listeners for when buttons are clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// What happens when play button clicked
		if (e.getSource() == playButton)
		{
			// Grabs message, removes all non alphanumerics
			String message = messageField.getText();
			message = message.replaceAll("[^A-Za-z0-9]", "");
			messageField.setText(message);
			
			// Sets puzzle to controller then plays puzzle
			controller.setPuzzle(message);
			controller.playPuzzle();
			repaint();
		}
	}
}