package excel;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;

public class TableDisplay {

	public static void main(String argv[]) throws Exception {
		JFrame frame = new JFrame("CISC 322 Project Skeleton");
		
		if (argv.length == 0)
			throw new Exception("Expecting a csv file as a parameter.");
		
		String csvFile = argv[0];
		List<String[]> csvData = ReverseCSV.readCsv(csvFile);
		
		TableDisplay dummyTable = new TableDisplay();
		// TableModel dataModel = dummyTable.new DataTable(csvData);
		TableModel dataModel = dummyTable.new DataTable(csvData);
		JTable table = new JTable(dataModel);
		table.setAutoCreateRowSorter(true);
		JScrollPane scrollpane = new JScrollPane(table);
		
		// Ray's change starts here
		TableColumnModel columnModel = table.getColumnModel();
		TableColumn column;
		int columnCount = columnModel.getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			column = columnModel.getColumn(i);
			column.setPreferredWidth(100);
		}
		System.out.println(columnCount);
		
		Container content = frame.getContentPane();
		content.add(scrollpane);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	} // main
	
	private class DataTable extends AbstractTableModel {
		
		private final int rowCount;
		private final int columnCount;
		
		private final Object[][] entries;
		
		public DataTable(int rowCount, int columnCount) {
			super();
			this.entries = new Object[rowCount][columnCount];
			this.rowCount = rowCount;
			this.columnCount = columnCount;
			super.addTableModelListener(new DataTableListener());
		}
		
		public DataTable(List<String[]> csvData) {
			super();
			this.entries = convertData(csvData);
			this.rowCount = this.entries.length;
			this.columnCount = this.rowCount > 0 ? this.entries[0].length : 0;
			super.addTableModelListener(new DataTableListener());
		}
		
		public Object[][] convertData(List<String[]> listData) {
			int maxRow = listData.size();
			int maxColumn = 0;
			
			for (String[] row : listData) {
				if (row.length > maxColumn)
					maxColumn = row.length;
			}
			Object[][] arrayData = new Object[maxRow][maxColumn];
			
			for (int row = 0; row < maxRow; row++){
				for (int column = 0; column < maxColumn; column++) {
					arrayData[row][column] = listData.get(row)[column];
				}
			}			
			return arrayData;
		}
		
		@Override
		public int getColumnCount() {
			return columnCount;
		}

		@Override
		public int getRowCount() {
			return rowCount;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			System.out.printf("Looking for (%d %d)\n", rowIndex, columnIndex);
			return entries[rowIndex][columnIndex];
		}
	}
	
	private class DataTableListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent arg0) {
			System.out.printf("Table change detected in DataTableListener: %o\n", arg0);
		}
		
	}

} // TableDisplay
