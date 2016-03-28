package editor;

// $Id: CapitalizeAction.java,v 1.0 2012/10/04 13:57:18 dalamb Exp $
import java.awt.event.ActionEvent;
import java.util.regex.*;
import javax.swing.JTextArea;
// For documentation purposes, import only edfmwk classes actually used.
import ca.queensu.cs.dal.edfmwk.Application;
import ca.queensu.cs.dal.edfmwk.act.DefaultAction;
import ca.queensu.cs.dal.edfmwk.win.CommonWindow;
import ca.queensu.cs.dal.flex.log.Log;
/**
 * {@link javax.swing.Action} for implementing "Capitalize" functionality.
 *<p>
 * Copyright 2010 David Alex Lamb.
 * See the <a href="../doc-files/copyright.html">copyright notice</a> for details.
 */
public class CapitalizeAction extends TextAction {
    private static final String wordPatternText =
	// "\\{L}+";
	"\\w+";
    protected static Pattern wordPattern;
    /**
     * Constructs a text deletion action -- delete selected text.
     */
    public CapitalizeAction() {
	super("Capitalize");
    } // end constructor CapitalizeAction

    /**
     * Capitalize the text in a given range of the document -- that is,
     * capitalize each "word" (string of letters or digits). Does nothing if
     * the start and end indices are equal. 
     * @param con Text to change.
     * @param start Index of the first character to change (the one to be
     *  capitalized).
     * @param end Index one beyond the last character to change.
     */
    protected void changeText(TextContents con, int start, int end) {
	if (wordPattern==null)
	    wordPattern = Pattern.compile(wordPatternText);
	try {
	    //System.err.println("Cap "+start+":"+end);
	    int len = end-start;
	    if (len>0) {
		String oldText = con.getText(start,len);
		StringBuilder newText = new StringBuilder(oldText.length());
		//System.err.println("old '"+oldText+"'");
		Matcher matcher = wordPattern.matcher(oldText);
		int lastEnd = 0;
		String tmp = "";
		while(matcher.find()) {
		    int oldStart = matcher.start();
		    int oldEnd = matcher.end();
		    tmp = oldText.substring(lastEnd,oldStart);
		    //System.err.print(oldStart+":"+oldEnd+" gap '"+tmp+"'");
		    newText.append(tmp);
		    tmp = oldText.substring(oldStart,oldStart+1).toUpperCase();
		    //System.err.print("cap '"+tmp+"'");
		    newText.append(tmp);
		    tmp = oldText.substring(oldStart+1,oldEnd).toLowerCase();
		    //System.err.println(" lower '"+tmp+"'");
		    newText.append(tmp);
		    lastEnd = oldEnd;
		} // while
		tmp = oldText.substring(lastEnd);
		//System.err.println("tail '"+tmp+"'");
		newText.append(tmp);
		// replace old selection contents with new
		con.replace(start,len,newText.toString(),null);
	    } else {
		// nothing to do
	    }
	} catch(Exception e) {
	    e.printStackTrace();
	}
    } // end changeText
} // end class CapitalizeAction
