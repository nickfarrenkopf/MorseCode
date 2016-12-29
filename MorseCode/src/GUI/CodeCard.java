package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MorseCode.Controller;

@SuppressWarnings("serial")
public class CodeCard extends JPanel implements ActionListener {
	
	// Controller variable
	//private Controller controller;
	
	private JLabel label;
	
	
	public CodeCard(Controller c)
	{
		// Sets controller
		//controller = c;
		
		label = new JLabel("Coming soon");
		add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	}
}