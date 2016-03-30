package excel;
import java.awt.Container;

import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultRowSorter;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;

/**
 * TableDisplay Class displays the JTable
 * Contains the main method for our project
 * 
 * @author Leif Raptis Firth, Ray Wen, Emma Wong
 *
 */
public class TableDisplay {
	
	static boolean sorted = false; 

	/**
	 * Main method: displays the table
	 * @param argv The csv file we want to show
	 * @throws Exception If there is no csv file provided
	 */
	public static void main(String argv[]) throws Exception {
		JFrame frame = new JFrame("CISC 322 Project Skeleton");
		
		if (argv.length == 0)
			throw new Exception("Expecting a csv file as a parameter.");
		
		String csvFile = argv[0];
		List<String[]> csvData = ReverseCSV.readCsv(csvFile);
		
		TableDisplay dummyTable = new TableDisplay();

		TableModel dataModel = dummyTable.new DataTable(csvData);
		JTable table = new JTable(dataModel);
		
		String header[] = {"name", "age", "gender", "saving", "nationality", "blank"};
		  
		for (int i = 0; i < table.getColumnCount(); i++) {
			TableColumn column = table.getTableHeader().getColumnModel().getColumn(i);	  
			column.setHeaderValue(header[i]);
		} 
		
//		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dataModel);
//		table.setRowSorter(sorter);
		JScrollPane scrollpane = new JScrollPane(table);
		

		// Add listener to call ColumnInfo
		// Double click the header to show
		table.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int col = table.columnAtPoint(e.getPoint());
				
				System.out.println("Selected column " + col);
				
				if (e.getClickCount() == 1) {
					System.out.printf("Single clicked column %d header!\n", col);
					sorted = true;
				}

				if (e.getClickCount() == 2) {
					System.out.printf("Double clicked column %d header!\n", col);
					new ColumnInfo(table, frame, col);
				}
			}
		});
		
		// Switch on sort functionality (JTable built-in method)
		table.setAutoCreateRowSorter(true);
		
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.addMouseListener(new RowMover(table));
		
		Container content = frame.getContentPane();
		content.add(scrollpane);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	} // main

	

	public class DataTable extends AbstractTableModel {
		
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
		
		/**
		 * moveOneRow
		 * Move a selected row (src) to a destination row (dest).
		 * Percolates other rows up or down, depending on movement.
		 * 
		 * @param 	src  	the source row
		 * @param 	dest 	the destination row
		 * @return 	boolean	true if move was successful;
		 * 					false otherwise
		 */
		public boolean moveOneRow(int src, int dest) {	
			if (!sorted) {
				// make sure that src and dest rows are valid
				if (src < 0 || dest < 0 || src >= rowCount || dest >= rowCount) {
					return false;
				}

				// case where we need to insert src first and shift down to
				// destination
				Object tmp_src[] = entries[src];

				if (src > dest) {
					for (int row = src; row > dest; row--) { // at the place where want to put the src back in
						entries[row] = entries[row - 1]; // move rows down
					}

					entries[dest] = tmp_src; // move source row to destination row
					this.fireTableRowsUpdated(0, rowCount-1); // update table
					return true;
				}

				// case where we need to first remove src row and ripple up until
				// we get to destination row where we can insert the src row
				if (src < dest) {
					for (int row = src; row < dest; row ++) {
						entries[row] = entries [row + 1]; // move rows up
					}

					entries[dest] = tmp_src; // move source row to destination row
					this.fireTableRowsUpdated(0, rowCount-1); // update table
					return true;
				}
			}
			
			// src must be equal to dest, so phantom move
			return true;
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
			return entries[rowIndex][columnIndex];
		}
	} // DataTable
	
	// For detecting if the user has changed DataTable
	private class DataTableListener implements TableModelListener {
		
		@Override
		public void tableChanged(TableModelEvent arg0) {
			System.out.printf("Table change detected in DataTableListener\n");
		}			
	}

} // TableDisplay
