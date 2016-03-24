package excel;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragSource;
import java.awt.event.*;

import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import excel.TableDisplay.DataTable;

/**
 * RowMover Class
 * 		Implements methods to move one row using a mouse listener. 
 * 		Rows can be moved up or down, within the table.
 * 		<p>Expects: JTable - where to perform the move row
 * 		<p>Output: None, this just describes what should be done when a
 * 				mouse event is detected
 * 		<p>How to use: provide a table to attach the mouse listener to,
 * 					use the moveOneRow method, defined in TableDisplay.java
 * 					to move a row.
 * 		<p>Methods: 
 * 		</br>- mousePressed
 * 		</br>- mouseReleased
 * 
 * @author Emma Wong, 10084142
 */
public class RowMover extends MouseAdapter {
    private int srcRow;
    private JTable table; 
    
    /**
     * Constructor for the RowMover object.
     * 
     * @param table	 a JTable to apply the mouse listener to
     */
    public RowMover(JTable table) {
    	this.table = table;
    }

    /**
     * mousePressed
     * Programs action when right mouse button is pressed; get the source row.
     * 
     * @param	 e a MouseEvent, in this case a right click
     * @return	 void
     */
    @Override
    public void mousePressed(MouseEvent e) {
      // select and highlight the row to move when the right mouse button is pressed
        JTable jtable = (JTable) e.getSource();
        srcRow = jtable.getSelectedRow();
        
        System.out.println("source row = " + srcRow);
    }

    /**
     * mouseReleased
     * Programs action when right mouse button is released. 
     * Find the destination row (mouse release) and invoke moveOneRow to 
     * move the source row to the destination row.
     * 
     * @param e	 MouseEvent, in this case the release of a right click
     * @return	 void
     */
    @Override
    public void mouseReleased(MouseEvent e) {     
    	// find the row that the mouse is over when the right button is released so that the selected row will be dropped
    	Point point = e.getPoint();
      	JTable jtable = (JTable) e.getSource(); // the viewer - sees part of table model
      	DataTable model =  (DataTable) jtable.getModel(); // data is in the model
        int destRow = jtable.rowAtPoint(point);

        // move row from where the right mouse button was pressed to where it was released. Clear the highlighting.     
        model.moveOneRow(srcRow, destRow); // move the row
        jtable.clearSelection();

        System.out.println("destination row selected = " + destRow);
    }
} // RowMover
