package GUI;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import static Others.Constants.frameTitle;

/**
 * Frame for Morse Code program. Sets initial conditions and centers on screen.
 */
@SuppressWarnings("serial")
public class Frame extends JFrame {

	// Creates frame object
	public Frame()
	{
		// Initializes components of frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(frameTitle);
		setVisible(true);

		// ToolMethods to make GUI half of computer screen size
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension dim = kit.getScreenSize();
		setSize(dim.width / 2, dim.height / 2);
		setLocation(dim.width / 4, dim.height / 4);
	}	
}