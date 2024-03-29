/*  Copyright (C) 2003-2011 JabRef contributors.
    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/
package net.sf.jabref.external;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import net.sf.jabref.BasePanel;
import net.sf.jabref.BibtexEntry;
import net.sf.jabref.BibtexFieldManager;
import net.sf.jabref.Globals;
import net.sf.jabref.JabRefFrame;

/**
 * An Action class representing the process of invoking a PushToApplication operation.
 */
public class PushToApplicationAction extends AbstractAction implements Runnable {
    private PushToApplication operation;
    private JabRefFrame frame;
    private BasePanel panel;
    private BibtexEntry[] entries;
    
    public PushToApplicationAction(JabRefFrame frame, PushToApplication operation) {
        this.frame = frame;
        putValue(SMALL_ICON, operation.getIcon());
        putValue(NAME, operation.getName());
        putValue(SHORT_DESCRIPTION, operation.getTooltip());
        if (operation.getKeyStrokeName() != null)
            putValue(ACCELERATOR_KEY, Globals.prefs.getKey(operation.getKeyStrokeName()));
        this.operation = operation;
    }

    public void actionPerformed(ActionEvent e) {
        panel = frame.basePanel();

        // Check if a BasePanel exists:
        if (panel == null)
            return;

        // Check if any entries are selected:
        entries = panel.getSelectedEntries();
        if (entries.length == 0) {
            JOptionPane.showMessageDialog(frame, Globals.lang("This operation requires one or more entries to be selected."),
                    (String)getValue(NAME), JOptionPane.ERROR_MESSAGE);
            return;
        }

        // If required, check that all entries have BibTeX keys defined:
        if (operation.requiresBibtexKeys())
            for (int i=0; i<entries.length; i++) {
                if ((entries[i].getCiteKey() == null) || (entries[i].getCiteKey().trim().length() == 0)) {
                    JOptionPane.showMessageDialog(frame, Globals.lang("This operation requires all selected entries to have BibTex keys defined."),
                        (String)getValue(NAME), JOptionPane.ERROR_MESSAGE);
                    return;
                }
        }

        // All set, call the operation in a new thread:
        Thread t = new Thread(this);
        t.start();

    }

    public void run() {
        // Do the operation:
        operation.pushEntries(panel.database(), entries, getKeyString(entries), panel.metaData());

        // Call the operationCompleted() method on the event dispatch thread:
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                operation.operationCompleted(panel);
            }
        });
    }

    protected String getKeyString(BibtexEntry[] entries) {
        StringBuffer result = new StringBuffer();
        String citeKey = "";//, message = "";
        boolean first = true;
        for (int i=0; i<entries.length; i++) {
            BibtexEntry bes = entries[i];
            citeKey = bes.getField(BibtexFieldManager.KEY_FIELD);
            // if the key is empty we give a warning and ignore this entry
            if (citeKey == null || citeKey.equals(""))
                continue;
            if (first) {
                result.append(citeKey);
                first = false;
            } else {
                result.append(",").append(citeKey);
            }
        }
        return result.toString();
    }
}
