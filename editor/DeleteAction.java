package editor;

// $Id: DeleteAction.java,v 1.0 2012/10/04 13:57:18 dalamb Exp $
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
// For documentation purposes, import only those classes from edfmwk that are
// actually used.
import ca.queensu.cs.dal.edfmwk.Application;
import ca.queensu.cs.dal.edfmwk.act.DefaultAction;
import ca.queensu.cs.dal.edfmwk.win.CommonWindow;
import ca.queensu.cs.dal.flex.log.Log;
/**
 * {@link javax.swing.Action} for implementing "Delete" functionality.
 *<p>
 * Copyright 2010 David Alex Lamb.
 * See the <a href="../doc-files/copyright.html">copyright notice</a> for details.
 */
public class DeleteAction extends TextAction {
    /**
     * Constructs a text deletion action -- delete selected text.
     */
    public DeleteAction() {
	super("Delete");
    } // end constructor DeleteAction

    /**
     * Delete the selected region of text.
     * If <code>start</code> and <code>end</code> are equal, delete the
     * character after the caret (if any).
     * @param con Text document to change.
     * @param start Index of the first character to change.
     * @param end Index one beyond the last character to change.
     */
    protected void changeText(TextContents con, int start, int end) {
	try {
	    if (end>start) {
		// delete selection
		con.replace(start,end-start,"",null);
	    } else {
		// delete text after caret
		con.replace(end,1,"",null);
	    }
	} catch (Exception e) {
	    //error("Deleting nonexistent character.");
	}
    } // end changeText
} // end class DeleteAction
