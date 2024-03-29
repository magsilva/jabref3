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
package net.sf.jabref.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import net.sf.jabref.*;

import com.jgoodies.forms.builder.ButtonBarBuilder;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Created by IntelliJ IDEA.
 * User: alver
 * Date: Oct 31, 2005
 * Time: 10:46:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabasePropertiesDialog extends JDialog {

    MetaData metaData;
    BasePanel panel = null;
    JComboBox encoding;
    JButton ok, cancel;
    JTextField fileDir = new JTextField(40);
    JTextField fileDirIndv = new JTextField(40);
    String oldFileVal="", oldFileIndvVal=""; // Remember old values to see if they are changed.
    JCheckBox protect = new JCheckBox(Globals.lang("Refuse to save the database before external changes have been reviewed."));
    boolean oldProtectVal = false;

    public DatabasePropertiesDialog(JFrame parent) {
        super(parent, Globals.lang("Database properties"), true);
        encoding = new JComboBox(Globals.ENCODINGS);
        ok = new JButton(Globals.lang("Ok"));
        cancel = new JButton(Globals.lang("Cancel"));
        init(parent);
    }

    public void setPanel(BasePanel panel) {
        this.panel = panel;
        this.metaData = panel.metaData();
    }

    public final void init(JFrame parent) {

        JButton browseFile = new JButton(Globals.lang("Browse"));
        JButton browseFileIndv = new JButton(Globals.lang("Browse"));
        browseFile.addActionListener(new BrowseAction(parent, fileDir, true));
        browseFileIndv.addActionListener(new BrowseAction(parent, fileDirIndv, true));

        DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout("left:pref, 4dlu, left:pref, 4dlu, fill:pref", ""));
        builder.getPanel().setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        builder.append(Globals.lang("Database encoding"));
        builder.append(encoding);
        builder.nextLine();
        builder.appendSeparator(Globals.lang("Override default file directories"));
        builder.nextLine();
        builder.append(Globals.lang("General file directory"));
        builder.append(fileDir);
        builder.append(browseFile);
        builder.nextLine();
        builder.append(Globals.lang("User-specific file directory"));
        builder.append(fileDirIndv);
        builder.append(browseFileIndv);
        builder.nextLine();
        builder.appendSeparator(Globals.lang("Database protection"));
        builder.nextLine();
        builder.append(protect,3);
        ButtonBarBuilder bb = new ButtonBarBuilder();
        bb.addGlue();
        bb.addButton(ok);
        bb.addButton(cancel);
        bb.addGlue();

        getContentPane().add(builder.getPanel(), BorderLayout.CENTER);
        getContentPane().add(bb.getPanel(), BorderLayout.SOUTH);
        pack();

        AbstractAction closeAction = new AbstractAction() {
          public void actionPerformed(ActionEvent e) {
            dispose();
          }
        };
        ActionMap am = builder.getPanel().getActionMap();
        InputMap im = builder.getPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        im.put(Globals.prefs.getKey("Close dialog"), "close");
        am.put("close", closeAction);

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                storeSettings();
                dispose();
            }
        });

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    public void setVisible(boolean visible) {
        if (visible)
            setValues();
        super.setVisible(visible);
    }

    public void setValues() {
        encoding.setSelectedItem(panel.getEncoding());

        Vector<String> fileD = metaData.getData(Globals.prefs.get("userFileDir"));
        if (fileD == null)
            fileDir.setText("");
        else {
            // Better be a little careful about how many entries the Vector has:
            if (fileD.size() >= 1)
                fileDir.setText((fileD.get(0)).trim());
        }

        Vector<String> fileDI = metaData.getData(Globals.prefs.get("userFileDirIndividual"));
        if (fileDI == null)
            fileDirIndv.setText("");
        else {
            // Better be a little careful about how many entries the Vector has:
            if (fileDI.size() >= 1)
                fileDirIndv.setText((fileDI.get(0)).trim());
        }

        Vector<String> prot = metaData.getData(Globals.PROTECTED_FLAG_META);
        if (prot == null)
            protect.setSelected(false);
        else {
            if (prot.size() >= 1)
                protect.setSelected(Boolean.parseBoolean(prot.get(0)));
        }

        // Store original values to see if they get changed:
        oldFileVal = fileDir.getText();
        oldFileIndvVal = fileDir.getText();
        oldProtectVal = protect.isSelected();
    }

    public void storeSettings() {
        String oldEncoding = panel.getEncoding();
        String newEncoding = (String)encoding.getSelectedItem();
        panel.setEncoding(newEncoding);

        Vector<String> dir = new Vector<String>(1);
        String text = fileDir.getText().trim();
        if (text.length() > 0) {
            dir.add(text);
            metaData.putData(Globals.prefs.get("userFileDir"), dir);
        }
        else
            metaData.remove(Globals.prefs.get("userFileDir"));
	// Repeat for individual file dir - reuse 'text' and 'dir' vars
	dir = new Vector<String>(1);
	text = fileDirIndv.getText().trim();
        if (text.length() > 0) {
            dir.add(text);
            metaData.putData(Globals.prefs.get("userFileDirIndividual"), dir);
        }
        else
            metaData.remove(Globals.prefs.get("userFileDirIndividual"));

        if (protect.isSelected()) {
            dir = new Vector<String>(1);
            dir.add("true");
            metaData.putData(Globals.PROTECTED_FLAG_META, dir);
        }
        else
            metaData.remove(Globals.PROTECTED_FLAG_META);


        // See if any of the values have been modified:
        boolean changed = !newEncoding.equals(oldEncoding)
            || !oldFileVal.equals(fileDir.getText())
            || !oldFileIndvVal.equals(fileDirIndv.getText())
            || (oldProtectVal != protect.isSelected());
        // ... if so, mark base changed. Prevent the Undo button from removing
        // change marking:
        if (changed)
            panel.markNonUndoableBaseChanged();
    }
}
