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

//import excel.TableDisplay.DataTable;

public class RowMover extends MouseAdapter {
    private int srcRow;
    private JTable table;
    
    public RowMover(JTable table) {
    	this.table = table;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
      // select and highlight the row to move when the right mouse button is pressed
        JTable jtable = (JTable) e.getSource();
        srcRow = jtable.getSelectedRow();
        
        System.out.println("source row = " + srcRow);
    }

    @Override
    public void mouseReleased(MouseEvent e){     
    	// find the row that the mouse if over when the right button is released so that the selected row will be dropped
    	Point point = e.getPoint();
      	JTable jtable = (JTable) e.getSource(); // the viewer - sees part of table model
      	DataTable model =  (DataTable) jtable.getModel(); // data is in the model
        int destRow = jtable.rowAtPoint(point);

        // move row from where the right mouse button was pressed to where it was released. Clear the highlighting.
      
	        model.moveOneRow(srcRow, destRow);
	        jtable.clearSelection();

        System.out.println("destination row selected = " + destRow);
    }
}
