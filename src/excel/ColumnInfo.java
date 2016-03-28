package excel;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * ColumnInfo Class
 * 		Implements method to display statistical data of a selected column
 * 		after double-clicking corresponding column header
 * 		<p>Expects: JTable, JFrame and the column number
 * 		<p>Output: JOptionPane pop up window with statistics
 * 		<p>How to use: provide a table to attach the mouse listener to,
 * 					instantiate this object once user triggers the event
 * 
 * @author Ray Wen, 10089627
 */
public class ColumnInfo {

	JButton button;
	JOptionPane dialog;
	Object[][] entries;
	ArrayList<Double> colEntries;
	double min, max, mean, total;
	boolean allNumeric = true;
	boolean allBlank = true;
	
	/**
     * Constructor for the ColumnInfo object.
     * 
     * @param table	 a JTable that contains data
     * @param frame  a JFrame for attaching the pop-up window
     * @param col	 the column number in interest
     */
	public ColumnInfo(JTable table, JFrame frame, int col) {
		entries = getTableData(table);
		colEntries = new ArrayList<Double>();
		for (int i = 0; i < entries.length; i++) {
			try {
			colEntries.add(Double.parseDouble((String)entries[i][col]));
			allBlank = false;
			} catch (NumberFormatException e) {
				allNumeric = false;
				if (((String)entries[i][col]).equals(" ")) { allNumeric = true; }
			}
		}
		if (allNumeric && !allBlank) {
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			for (int i = 0; i < colEntries.size(); i++) {
				if (colEntries.get(i) < min) { min = colEntries.get(i); }
				if (colEntries.get(i) > max) { max = colEntries.get(i); }
				total += (double)colEntries.get(i);
			}
			mean = total / colEntries.size();
			String display = String.format("Min:%.2f, Max:%.2f, Mean:%.2f", min,max,mean);
			JOptionPane.showMessageDialog(frame, display);
		}
	}

	// Retrieves data from a JTable
	private Object[][] getTableData (JTable table) {
		TableModel dtm = table.getModel();
		int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
		Object[][] tableData = new Object[nRow][nCol];
		for (int i = 0 ; i < nRow ; i++)
			for (int j = 0 ; j < nCol ; j++)
				tableData[i][j] = dtm.getValueAt(i,j);
		return tableData;
	}


}
