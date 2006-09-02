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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Stack;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;
import javax.swing.text.html.HTMLEditorKit;

public class HelpContent extends JTextPane {

	JScrollPane pane;

	private Stack history, forw;

	JabRefPreferences prefs;

	public HelpContent(JabRefPreferences prefs_) {
		super();
		pane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setDoubleBuffered(true);
		prefs = prefs_;
		history = new Stack();
		forw = new Stack();
		setEditorKitForContentType("text/html", new MyEditorKit());
		setContentType("text/html");
		setText("");
		setEditable(false);
		
		// Handles Anchors
		final HyperlinkListener hyperLinkListener = new HyperlinkListener() {
			public void hyperlinkUpdate(final HyperlinkEvent e) {
				if (e.getDescription().startsWith("#")) {
					scrollToReference(e.getDescription().substring(1));
				}
			}
		};
		addHyperlinkListener(hyperLinkListener);
	}

	public boolean back() {
		if (!history.empty()) {
			URL prev = (URL) (history.pop());
			forw.push(getPage());
			setPageOnly(prev);
		}
		return !history.empty();
	}

	public boolean forward() {
		if (!forw.empty()) {
			URL next = (URL) (forw.pop());
			history.push(getPage());
			setPageOnly(next);
		}
		return !forw.empty();
	}

	public void reset() {
		forw.removeAllElements();
		history.removeAllElements();
	}

	public void setPage(String filename) {
		
		// Check for anchor
		int indexOf = filename.indexOf('#');
		String file;
		String reference;
		
		if (indexOf != -1) {
			file = filename.substring(0, indexOf);
			reference = filename.substring(indexOf + 1);
		} else {
			file = filename;
			reference = "";
		}
		
		String middle = prefs.get("language") + "/";
		if (middle.equals("en/"))
			middle = ""; // english in base help dir.
		
		URL old = getPage();
		try {
            URL resource = JabRef.class.getResource(GUIGlobals.helpPre + middle + file);
            // Because of the call to toString(), we must test for null, or the fallback
            // to english won't work if the page is missing in the selected langugage:
            if (resource != null) {
                super.setPage(new URL(resource.toString() + "#" + reference));
            }
            else {
                setPageOnly(new URL(HelpContent.class.getResource(GUIGlobals.helpPre + file) + "#" + reference));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            // The fallback below shouldn't bee needed any more, because of the null
            // check above.
            /*
            try {
				setPageOnly(new URL(HelpContent.class.getResource(GUIGlobals.helpPre + file) + "#" + reference));
			} catch (MalformedURLException e) {
				setPageOnly(HelpContent.class.getResource(GUIGlobals.helpPre + file));
			}*/
		}

		forw.removeAllElements();
		if (old != null)
			history.push(old);

	}

	/**
	 * Convenience method for setPage(String)
	 */
	public void setPage(URL url) {
		File f = new File(url.getPath());
		setPage(f.getName());
	}

	private void setPageOnly(URL url) {
		try {
			super.setPage(url);
		} catch (IOException ex) {
			if (url == null) {
				System.out.println("Error: Help file not set");
			} else {
				System.out.println("Error: Help file not found '" + url.getFile() + "'");
			}
		}
	}

	public JComponent getPane() {
		return pane;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		super.paintComponent(g2);
	}

	public class MyNextVisualPositionAction extends TextAction {
		private Action textActn;

		private int direction;

		private MyNextVisualPositionAction(Action textActn, int direction) {
			super((String) textActn.getValue(Action.NAME));
			this.textActn = textActn;
			this.direction = direction;
		}

		public void actionPerformed(ActionEvent e) {
			JTextComponent c = getTextComponent(e);

			if (c.getParent() instanceof JViewport) {
				JViewport viewport = (JViewport) c.getParent();
				Point p = viewport.getViewPosition();

				if (this.direction == SwingConstants.NORTH) {
					c.setCaretPosition(c.viewToModel(p));
				} else {
					p.y += viewport.getExtentSize().height;
					c.setCaretPosition(c.viewToModel(p));
				}
			}

			textActn.actionPerformed(e);
		}
	}

	public class MyEditorKit extends HTMLEditorKit {
		private Action[] myActions;

		public Action[] getActions() {
			if (myActions == null) {
				Action[] actions = super.getActions();
				Action[] newActions = new Action[2];

				for (int i = 0; i < actions.length; i++) {
					Action actn = actions[i];

					String name = (String) actn.getValue(Action.NAME);

					if (name.equals(DefaultEditorKit.upAction)) {
						newActions[0] = new MyNextVisualPositionAction(actions[i],
							SwingConstants.NORTH);
					} else if (name.equals(DefaultEditorKit.downAction)) {
						newActions[1] = new MyNextVisualPositionAction(actions[i],
							SwingConstants.SOUTH);
					}
				}

				myActions = TextAction.augmentList(actions, newActions);
			}

			return myActions;
		}
	}
}
