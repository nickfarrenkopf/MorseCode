package GUI;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import MorseCode.Controller;
import MorseCode.MorseCode;
import Others.RadioGroup;
import Others.Table;
import static Others.Constants.*;

/**
 * Screen is a JPanel with JComponents to control the morse code program.
 * A collection of JRadioButtons control difficulty level and problem choice.
 * One button controls whether a cheat table is on or off.
 * The bottom half creates, plays, and resets morse code puzzle.
 */
@SuppressWarnings("serial")
public class Screen extends JPanel implements ActionListener{

	// Cheat table
	private JButton tableButton;
	private Table table;
	
	// Difficulty buttons
	private RadioGroup difficultyGroup;
	
	// Mode selection (words vs numbers)
	private RadioGroup modeGroup;
	
	// Tabbed pane to hold decode and code panels
	private JTabbedPane tabbed;
	
	// Panels on tabbed pane
	DecodeCard dc;
	
	/**
	 * Initializes Screen with specified components.
	 * @param controller
	 */
	public Screen(Controller controller)
	{	
		// Morse Code stuff
		MorseCode mc = new MorseCode();
		String[][] symbols = mc.getSymbols();

		// Creates difficulty buttons
		JLabel difficultyLabel = new JLabel("Select Difficulty");
		String[] labels = new String[] {"Easy (single)", "Medium (word)", "Hard (phrase)"};
		difficultyGroup = new RadioGroup(labels);

		// Creates mode buttons
		JLabel modeLabel = new JLabel("Select Mode");
		labels = new String[] {"Word", "Number"};
		modeGroup = new RadioGroup(labels);
		modeGroup.disableAll();

		// Table on/off button
		tableButton = new JButton("Table Off");

		// Initializes tables variables
		int row = 0;
		int col = 0;
		int numCols = (int) Math.ceil(symbols.length / (double) rowMax);
		String[] columnNames = new String[2 * numCols];
		Object[][] obs = new Object[rowMax][2 * numCols];
		// Iterate through column names
		for (int i=0; i<numCols; i++)
		{
			columnNames[2 * i] = "Value";
			columnNames[2 * i + 1] = "Symbol";
		}
		// Adds items to table
		for (int i=0; i<symbols.length; i++)
		{
			// Adds to objects
			obs[row][2 * col] = symbols[i][0];
			obs[row][2 * col + 1] = symbols[i][1];

			// Iterate row and column counter
			row++;
			if (row == rowMax)
			{
				row = 0;
				col++;
			}
		}
		table = new Table(columnNames, obs);
		table.getTable().setFont(font);
		
		// Add action listeners to all things
		for (int i=0; i<difficultyGroup.getSize(); i++)
			difficultyGroup.getButton(i).addActionListener(this);
		for (int i=0; i<modeGroup.getSize(); i++)
			modeGroup.getButton(i).addActionListener(this);
		tableButton.addActionListener(this);

		// Difficulty panel
		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setLayout(new BoxLayout(difficultyPanel, BoxLayout.Y_AXIS));
		difficultyPanel.add(difficultyLabel);
		for (int i=0; i<difficultyGroup.getSize(); i++)
			difficultyPanel.add(difficultyGroup.getButton(i));
		
		// Mode panel
		JPanel modePanel = new JPanel();
		modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.Y_AXIS));
		modePanel.add(modeLabel);
		for (int i=0; i<modeGroup.getSize(); i++)
			modePanel.add(modeGroup.getButton(i));

		// Table panel
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		tablePanel.add(tableButton);
		tablePanel.add(table);

		// Initializes all cards
		DecodeCard dc = new DecodeCard(controller);
		CodeCard cc = new CodeCard(controller);
		PlayCard pc = new PlayCard(controller);

		// Add things to tabbed pane
		tabbed = new JTabbedPane();
		tabbed.add(dc, "Morse Code to Text");
		tabbed.add(cc, "Text to Morse Code");
		tabbed.add(pc, "Play");
		
		// Set layout
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(grid);

		// Add difficulty Panel
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		add(difficultyPanel, c);

		// Add mode panel
		c.gridx = 1;
		add(modePanel, c);

		// Add table panel
		c.gridx = 2;
		add(tablePanel, c);

		// Add tabbed pane
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5;
		c.weighty = 5;
		add(tabbed, c);
	}
	
	/**
	 * Action listeners that listens for clicked buttons
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// Set table to visible or not visible
		if (e.getSource() == tableButton)
		{
			// Check text of button to decide
			if (table.isVisible())
				tableButton.setText("Table On");
			else 
				tableButton.setText("Table Off");
				
			// Changes visibility of table
			table.setVisible(!table.isVisible());
		}
		
		// Disable mode if easy button selected
		if (e.getSource() == difficultyGroup.getButton(0))
			modeGroup.disableAll();
		
		// Enable mode if easy button not selected
		if (e.getSource() == difficultyGroup.getButton(1) || e.getSource() == difficultyGroup.getButton(2))
			modeGroup.enableAll();
		
		// Refresh panel
		repaint();
	}
	
	/**
	 * Returns difficulty Group selection so decode panel can see difficulty
	 */
	public RadioGroup getDifficultyGroup()
	{
		return difficultyGroup;
	}
}