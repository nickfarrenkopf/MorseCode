package Others;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class RadioGroup {

	// Array list to hold button
	private ArrayList<JRadioButton> buttons = new ArrayList<JRadioButton>();
	
	// Button group to allow only one selected
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	///// CONSTRUCTORS /////
	
	// Creates a group of buttons given string button names
	public RadioGroup(String[] buttonNames)
	{
		for (int i=0; i<buttonNames.length; i++)
		{
			buttons.add(new JRadioButton(buttonNames[i]));
			buttonGroup.add(buttons.get(i));
		}
		setSelected(0);
	}
	
	// Created button group given array of JButtons, selecting the first button
	public RadioGroup(JRadioButton[] buttons)
	{
		for (JRadioButton rb:buttons)
		{
			this.buttons.add(rb);
			buttonGroup.add(rb);
		}
		setSelected(0);
	}
	
	///// EDIT GROUP /////
	
	// Add button to group
	public void addButton(JRadioButton button)
	{
		buttons.add(button);
		buttonGroup.add(button);
	}
	
	// Removes button from group
	public void removeButton(JRadioButton button)
	{
		if (buttons.contains(button))
		{
			buttons.remove(button);
			buttonGroup.remove(button);
		} else
			System.out.println("Radio Group does not contain button.");
	}

	// Sets selected button given int
	public void setSelected(int i)
	{
		buttons.get(i).setSelected(true);
	}

	// Sets selected button given button 
	public void setSelected(JRadioButton button)
	{
		button.setSelected(true);
		for (JRadioButton rb:buttons)
			if (rb == button)
				rb.setSelected(true);
	}
	
	///// LOOK AT GROUP /////
	
	// Returns index of selected button
	public int getSelectedIndex()
	{
		for (int i=0; i<buttons.size(); i++)
			if (buttons.get(i).isSelected())
				return i;
		return -1;
	}
	
	// Returns selected radio button
	public JRadioButton getSelected()
	{
		return (getSelectedIndex() == -1? null: buttons.get(getSelectedIndex()));
	}	
	
	// Returns true if group contains button, false if not
	public boolean contains(JRadioButton button)
	{
		for (JRadioButton rb:buttons)
			if (rb == button)
				return true;
		return false;
	}
	
	// Returns index of button in group
	public int indexOf(JRadioButton button)
	{
		for (JRadioButton rb:buttons)
			if (rb == button)
				return buttons.indexOf(rb);
		return -1;
	}
	
	// Returns size of radio group
	public int getSize()
	{
		return buttons.size();
	}
	
	public JRadioButton getButton(int i)
	{
		return buttons.get(i);
	}
	
	///// DISABLE ENABLE /////
	
	// Disable all buttons
	public void disableAll()
	{
		for (JRadioButton rb:buttons)
			rb.setEnabled(false);
	}

	// Enable all buttons
	public void enableAll()
	{
		for (JRadioButton rb:buttons)
			rb.setEnabled(true);
	}
}