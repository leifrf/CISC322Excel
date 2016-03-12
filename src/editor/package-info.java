package editor;

// $Id: package-info.java,v 1.1 2012/12/14 20:23:24 dalamb Exp $
/**
 * An editor for text files, acting as an illustration of how to use the
 * {@linkplain ca.queensu.cs.dal.edfmwk editor framework}.
 * To use the framework, one must write:
 *<ul>
 *  <li>An implementation of the
 *    {@link ca.queensu.cs.dal.edfmwk.doc.Document} interface, such as
 *    {@link ca.queensu.cs.dal.txt.TextDocument}; in this case part of the
 *    implementation is split off into
 *    {@link ca.queensu.cs.dal.txt.TextContents}.
 *  <li>An implementation of the
 *    {@link ca.queensu.cs.dal.edfmwk.doc.DocumentType} interface, such as
 *    {@link ca.queensu.cs.dal.txt.TextType}.
 *  <li>A main class that extends
 *    {@link ca.queensu.cs.dal.edfmwk.Application} class, such as
 *    {@link ca.queensu.cs.dal.txt.TextEditor}.
 *  <li>A implementations of any actions specific to the editor, which might
 *    be extensions of 
 *    {@link ca.queensu.cs.dal.edfmwk.act.DefaultAction} class, such as
 *    {@link ca.queensu.cs.dal.txt.CapitalizeAction} and
 *    {@link ca.queensu.cs.dal.txt.DeleteAction}.
 *  <li>A <code>.jar</code> manifest file, such as
 *    <a href="doc-files/txtManifest.txt">txt.mf</a>.
 *</ul>
 * This is not meant to be a complete editor &endash; just an example of how
 * to interact with the
 * {@link ca.queensu.cs.dal.edfmwk editing framework}.
 * The primary example of how to code an editor for a text component is
 * <a href="http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextComponentDemoProject/src/components/TextComponentDemo.java">TextComponentDemo.java</a>
 * from the
 * <a href="http://docs.oracle.com/javase/tutorial/uiswing/components/generaltext.html">Java tutorial</a> on
 * {@link javax.swing.text.JTextComponent};
 * <p>
 * This program is currently in a very early stage of development.
 *<p>
 * Copyright 2010-2011 David Alex Lamb.
 * See the <a href="../doc-files/copyright.html">copyright notice</a> for details.
 */
