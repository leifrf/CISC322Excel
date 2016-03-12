package editor;

// $Id: MainPanel.java,v 1.0 2012/10/04 13:57:18 dalamb Exp $
import java.awt.Color;
import java.awt.BorderLayout;
import java.io.Writer;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
// Import only those classes from edfmwk that are actually used, for
// documentation purposes.
import ca.queensu.cs.dal.edfmwk.win.TextAreaWriter;

/**
 * Main panel for the editor's user inferface.
 *<p>
 * Copyright 2010-2011 David Alex Lamb.
 * See the <a href="../doc-files/copyright.html">copyright notice</a> for details.
 */
public class MainPanel extends JPanel {

    /*
     * The inital GUI component to display.
     */
    private JTextArea mainArea;

    /**
     * A writer whose output goes to the main panel.
     */
    private Writer log;

    /**
     * Gets a writer that sends information to the main panel. Thus
     * the initial main panel also serves as a log for messages.
     */
    public Writer getLog() { return log; }

    /**
     * Constructs the main panel.
     */
    public MainPanel() {
	super();
	mainArea = new JTextArea(20,80);
	mainArea.setBackground(Color.CYAN);
	JScrollPane sc = new JScrollPane(mainArea);
        // Add a border to the panel so we can see its boundaries
        sc.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(sc, BorderLayout.CENTER);
	log = new TextAreaWriter(mainArea);
    } // end constructor MainPanel

} // end class MainPanel
