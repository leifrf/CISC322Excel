import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class TableDisplay {

	public static void main(String argv[]) {
		JFrame frame = new JFrame("CISC 322 Project Skeleton");
		TableDisplay dummyTable = new TableDisplay();
		TableModel dataModel = dummyTable.new DataTable();
		JTable table = new JTable(dataModel);
		JScrollPane scrollpane = new JScrollPane(table);
		
		Container content = frame.getContentPane();
		content.add(scrollpane);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	} // main
	
	private class DataTable extends AbstractTableModel {
		
		private final int ROW_COUNT;
		private final int COLUMN_COUNT;
		
		public DataTable() {
			this(10, 10);
		}
		
		public DataTable(int rowCount, int columnCount) {
			super();
			ROW_COUNT = rowCount;
			COLUMN_COUNT = columnCount;
			super.addTableModelListener(new DataTableListener());
		}
		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return ROW_COUNT;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return COLUMN_COUNT;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return new Integer(rowIndex * columnIndex);
		}		
	}
	
	private class DataTableListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent arg0) {
			System.out.println("Table change detected in DataTableListener:");
			System.out.println(arg0);			
		}
		
	}

} // TableDisplay
