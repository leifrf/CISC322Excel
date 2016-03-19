package excel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ColumnInfo {

	JButton button;
	JOptionPane dialog;
	Object[][] entries;
	double[] colEntries;
	double min, max, mean, total;
	boolean allNumeric = true;

	public ColumnInfo(JTable table, JFrame frame, int col) {
		entries = getTableData(table);
		colEntries = new double[entries.length];
		for (int i = 0; i < colEntries.length; i++) {
			try {
			colEntries[i] = Double.parseDouble((String)entries[i][col]);
			} catch (NumberFormatException e) {
				allNumeric = false;
			}
		}
		if (allNumeric) {
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			for (int i = 0; i < colEntries.length; i++) {
				if (colEntries[i] < min) { min = colEntries[i]; }
				if (colEntries[i] > max) { max = colEntries[i]; }
				total += (double)colEntries[i];
			}
			mean = total / colEntries.length;
			JOptionPane.showMessageDialog(frame, "Min: "+ min + " Max: " + max + " Mean: " + mean);
		}
	}


	public Object[][] getTableData (JTable table) {
		TableModel dtm = table.getModel();
		int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
		Object[][] tableData = new Object[nRow][nCol];
		for (int i = 0 ; i < nRow ; i++)
			for (int j = 0 ; j < nCol ; j++)
				tableData[i][j] = dtm.getValueAt(i,j);
		return tableData;
	}


}
