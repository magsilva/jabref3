/*
Copyright (C) 2003 Nizar N. Batada, Morten O. Alver

All programs in this directory and
subdirectories are published under the GNU General Public License as
described below.

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or (at
your option) any later version.

This program is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
USA

Further information about the GNU GPL is available at:
http://www.gnu.org/copyleft/gpl.ja.html

*/
package net.sf.jabref;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.IOException;
import java.util.Stack;
import javax.swing.event.HyperlinkListener;
import java.io.File;

public class HelpContent extends JEditorPane {

    JScrollPane pane;
    private Stack history, forw;
    JabRefPreferences prefs;
    public HelpContent(JabRefPreferences prefs_) {
	pane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			       JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        prefs = prefs_;
	history = new Stack();
	forw = new Stack();
	setContentType("text/html");
	setText("");
	setEditable(false);
    }

    public boolean back() {
	if (!history.empty()) {
	    URL prev = (URL)(history.pop());
	    forw.push(getPage());
	    setPageOnly(prev);
	}
	//System.out.println("HelpContent: "+history.empty());
	return !history.empty();
    }

    public boolean forward() {
	if (!forw.empty()) {
	    URL next = (URL)(forw.pop());
	    history.push(getPage());
	    setPageOnly(next);
	}
	return !forw.empty();


    }

    public void reset() {
	forw.removeAllElements();
	history.removeAllElements();
    }

    public void setPage(URL url) {
	String lang = prefs.get("language");
	//	if (!url.getPath
	URL old = getPage();
	//System.out.println(url.toString());
        File f = new File(url.getPath());
        File directory = new File(f.getParent());

	/*
	// Search 
	File[] listing = directory.listFiles();
	for (int i=0; i<listing.length(); i++) {
	    if (
	}
	*/

        File translatedFile = new File(directory.getPath()+"/"+lang
                                       +"/"+f.getName());
	//System.out.println(translatedFile.getPath());
        if (translatedFile.exists()) {
          try {
            Util.pr("file:"+translatedFile.getPath());
            URL translatedURL = new URL("file:"+translatedFile.getPath());
            setPageOnly(translatedURL);

          } catch (Throwable ex) {ex.printStackTrace();}//(MalformedURLException ex) {}
        }
        else {	 	    
          setPageOnly(url);
        }
	forw.removeAllElements();
	if (old != null)
	    history.push(old);
    }

    private void setPageOnly(URL url) {
	try {
	    super.setPage(url);
	} catch (IOException ex) {
	    System.out.println("Error: could not read help file: '"
			       +url.getFile()+"'");
	}
    }

    public JComponent getPane() {
	return pane;
    }

}
