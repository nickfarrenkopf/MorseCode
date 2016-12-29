package Others;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Table is a JPanel that holds a JTable to view cheat morse code data.
 */
@SuppressWarnings("serial")
public class Table extends JPanel {

	// Table variables
	private JTable table;
	private MyTableModel tableModel;
	
	/**
	 * Initializes the data with specified column names and data
	 * @param columnNames - String[] that contains column names
	 * @param data - Object[] that contains data values
	 */
	public Table(String[] columnNames, Object[][] data)
	{
		// Create table model (control data) and table container
		tableModel = new MyTableModel(columnNames, data);
		table = new JTable(tableModel);
		setEnabled(false);
		
		// Table properties
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(String.class, centerRenderer);
		table.setDefaultRenderer(Integer.class, centerRenderer);
		
		// Sets layout and grid variables
		BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(box);

		// Adds header and table to panel
		add(table.getTableHeader());
		add(table);
	}
	
	/**
	 * Updates the table with given data
	 * @param columnNames - String[] that contains column names
	 * @param data - Object[] that contains data values
	 */
	public void updateTable(String[] columnNames, Object[][] data)
	{
		int stuff = data.length;
		MyTableModel tm = (MyTableModel) table.getModel();
		for (int i=0; i<tm.getRowCount(); i++)
		{
			for (int j=0; j<columnNames.length; j++)
			{
				if (i < stuff)
				{
				tm.setValueAt(data[i][j], i, j);
				tm.fireTableCellUpdated(i, j);
				} else {
					tm.setValueAt("", i, j);
					tm.fireTableCellUpdated(i, j);
				}
			}
		}
	}
	
	/**
	 * Returns the table
	 * @return (JTable)
	 */
	public JTable getTable()
	{
		return table;
	}

	/**
	 * Creates TableModel for table that controls how things are organized and changed
	 */
	class MyTableModel extends AbstractTableModel {
	    private String[] columnNames; //same as before...
	    private Object[][] data; //same as before...

	    // MyTableModel constructor
	    public MyTableModel(String[] s, Object[][] data)
	    {
	    	columnNames = s;
	    	this.data = data;
	    }
	    
	    // Returns number of columns
	    public int getColumnCount() 
	    {
	        return columnNames.length;
	    }

	    // Returns number of data points
	    public int getRowCount() 
	    {
	        return data.length;
	    }

	    // Returns name of column
	    public String getColumnName(int col) 
	    {
	    	pointCheck(0, col);
	        return columnNames[col];
	    }

	    // Gets value at specified row, col
	    public Object getValueAt(int row, int col) 
	    {
	    	pointCheck(row, col);
	        return data[row][col];
	    }

	    // Return class type of column
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int c) 
		{
	    	pointCheck(0, c);
	    	if (getValueAt(0,c) != null)
	    		return getValueAt(0, c).getClass();
	    	else
	    		return "hi".getClass();
	    }

	    // Updates table data
	    public void setValueAt(Object value, int row, int col) 
	    {
	    	pointCheck(row, col);
	        data[row][col] = value;
	        fireTableCellUpdated(row, col);
	    }
	    
	    // Exception checks
	    public void pointCheck(int row, int col)
	    {
	    	if (row < 0 || row >= getRowCount())
	    		throw new IllegalArgumentException("Invalid row at (" + row + ").");
	    	if (col < 0 || col >= getColumnCount())
	    		throw new IllegalArgumentException("Invalid column at (" + col + ").");
	    }
	}
}