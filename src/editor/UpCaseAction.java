package editor;

// $Id: UpCaseAction.java,v 1.0 2012/10/04 13:57:18 dalamb Exp $
import java.awt.event.ActionEvent;
import java.util.regex.*;
import javax.swing.JTextArea;
// For documentation purposes, import only edfmwk classes actually used.
import ca.queensu.cs.dal.edfmwk.Application;
import ca.queensu.cs.dal.edfmwk.act.DefaultAction;
import ca.queensu.cs.dal.edfmwk.win.CommonWindow;
import ca.queensu.cs.dal.flex.log.Log;
/**
 * {@link javax.swing.Action} for implementing "Upper Case" functionality.
 *<p>
 * Copyright 2010 David Alex Lamb.
 * See the <a href="../doc-files/copyright.html">copyright notice</a> for details.
 */
public class UpCaseAction extends CSVAction {
    /**
     * Constructs an uppercase action -- convert text to upper case.
     */
    public UpCaseAction() {
	super("Upper Case");
    } // end constructor UpCaseAction

    /**
     * Convert the text in a given range of the document to upper case.
     * Does nothing if the start and end indices are equal.
     * @param con Text to change.
     * @param start Index of the first character to change (the one to be
     *  capitalized).
     * @param end Index one beyond the last character to change.
     */
    protected void changeText(CSVContents con, int start, int end) {
	try {
	    //System.err.println("Up "+start+":"+end);
	    int len = end-start;
	    if (len>0) {
		String oldText = con.getText(start,len);
		con.replace(start,len,oldText.toUpperCase(),null);
	    } else {
		// nothing to do
	    }
	} catch(Exception e) {
	    e.printStackTrace();
	}
    } // end changeText
} // end class UpCaseAction
