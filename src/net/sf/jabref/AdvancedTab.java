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
package net.sf.jabref;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sf.jabref.remote.RemoteListener;

import com.ironiacorp.computer.OperationalSystemType;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class AdvancedTab extends JPanel implements PrefsTab {

    JabRefPreferences _prefs;
    JabRefFrame _frame;
    HelpDialog helpDiag;
    HelpAction remoteHelp;
    JPanel pan = new JPanel(),
        lnf = new JPanel();
    JLabel lab;
    JCheckBox useDefault, useRemoteServer, filechooserDisableRename;
    JTextField className, remoteServerPort;
    JButton def1 = new JButton(Globals.lang("Default")),
        def2 = new JButton(Globals.lang("Default"));
    JPanel p1 = new JPanel(),
        p2 = new JPanel();
    String oldLnf = "";
    boolean oldUseDef, oldBiblMode=false;
    int oldPort = -1;

    public AdvancedTab(JabRefPreferences prefs, HelpDialog diag) {
        _prefs = prefs;


    remoteHelp = new HelpAction(diag, GUIGlobals.remoteHelp, "Help",
                GUIGlobals.getIconUrl("helpSmall"));
    useDefault = new JCheckBox(Globals.lang("Use other look and feel"));
    useRemoteServer = new JCheckBox(Globals.lang("Listen for remote operation on port")+":");
    filechooserDisableRename = new JCheckBox(Globals.lang("Disable file renaming in non-native file dialog"));
    remoteServerPort = new JTextField();
    className = new JTextField(50);
    final JTextField clName = className;
    useDefault.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            clName.setEnabled(((JCheckBox)e.getSource()).isSelected());
        }
        });


    FormLayout layout = new FormLayout
        ("1dlu, 8dlu, left:pref, 4dlu, fill:3dlu",//, 4dlu, fill:pref",// 4dlu, left:pref, 4dlu",
         "");
    DefaultFormBuilder builder = new DefaultFormBuilder(layout);
    JPanel pan = new JPanel();

    if (Globals.prefs.osType != OperationalSystemType.MacOS) {
        builder.appendSeparator(Globals.lang("Look and feel"));
        JLabel lab = new JLabel(Globals.lang("Default look and feel")+": "+UIManager.getSystemLookAndFeelClassName());
        builder.nextLine();
        builder.append(pan);
        builder.append(lab);
        builder.nextLine();
        builder.append(pan);
        builder.append(useDefault);
        builder.nextLine();
        builder.append(pan);
        JPanel pan2 = new JPanel();
        lab = new JLabel(Globals.lang("Class name")+":");
        pan2.add(lab);
        pan2.add(className);
        builder.append(pan2);
        builder.nextLine();
        builder.append(pan);
        lab = new JLabel(Globals.lang("Note that you must specify the fully qualified class name for the look and feel,"));
        builder.append(lab);
        builder.nextLine();
        builder.append(pan);
        lab = new JLabel(Globals.lang("and the class must be available in your classpath next time you start JabRef."));
        builder.append(lab);
        builder.nextLine();
    }
    builder.appendSeparator(Globals.lang("Remote operation"));
    builder.nextLine();
    builder.append(new JPanel());    
    builder.append(new JLabel("<html>"+Globals.lang("This feature lets new files be opened or imported into an "
        +"already running instance of JabRef<BR>instead of opening a new instance. For instance, this "
        +"is useful when you open a file in JabRef<br>from your web browser."
        +"<BR>Note that this will prevent you from running more than one instance of JabRef at a time.")+"</html>"));
    builder.nextLine();
    builder.append(new JPanel());

    JPanel p = new JPanel();
    p.add(useRemoteServer);
    p.add(remoteServerPort);
    p.add(remoteHelp.getIconButton());
    builder.append(p);

    //if (Globals.ON_MAC) {
    builder.nextLine();
    builder.appendSeparator(Globals.lang("File dialog"));
    builder.nextLine();
    builder.append(new JPanel());
    builder.append(filechooserDisableRename);
    //}
	// IEEE
    builder.nextLine();
    builder.appendSeparator(Globals.lang("Search IEEEXplore"));
    builder.nextLine();
    builder.append(new JPanel());

    
    pan = builder.getPanel();
    pan.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    setLayout(new BorderLayout());
    add(pan, BorderLayout.CENTER);

    }

    public void setValues() {
        oldUseDef = _prefs.getBoolean("useDefaultLookAndFeel");
        oldLnf = _prefs.get("lookAndFeel");
        useDefault.setSelected(!oldUseDef);
        className.setText(oldLnf);
        className.setEnabled(!oldUseDef);
        useRemoteServer.setSelected(_prefs.getBoolean("useRemoteServer"));
        oldPort = _prefs.getInt("remoteServerPort");
        remoteServerPort.setText(String.valueOf(oldPort));
        filechooserDisableRename.setSelected(Globals.prefs.getBoolean("filechooserDisableRename"));
    }

    public void storeSettings() {
        _prefs.putBoolean("useDefaultLookAndFeel", !useDefault.isSelected());
        _prefs.put("lookAndFeel", className.getText());
        _prefs.putBoolean("filechooserDisableRename", filechooserDisableRename.isSelected());
        UIManager.put("FileChooser.readOnly", filechooserDisableRename.isSelected());
        try {
            int port = Integer.parseInt(remoteServerPort.getText());
            if (port != oldPort) {
                _prefs.putInt("remoteServerPort", port);
                /*JOptionPane.showMessageDialog(null, Glbals.lang("You have changed the menu and label font size. "
                        + "You must restart JabRef for this to come into effect."), Globals.lang("Changed font settings"),
                        JOptionPane.WARNING_MESSAGE);*/
            }

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        _prefs.putBoolean("useRemoteServer", useRemoteServer.isSelected());
        if (useRemoteServer.isSelected() && (JabRef.remoteListener == null)) {
            // Start the listener now.

            JabRef.remoteListener = RemoteListener.openRemoteListener(JabRef.singleton);
            if (JabRef.remoteListener != null) {
                JabRef.remoteListener.start();
            }
        } else if (!useRemoteServer.isSelected() && (JabRef.remoteListener != null)) {
            JabRef.remoteListener.disable();
            JabRef.remoteListener = null;
        }

        if ((useDefault.isSelected() == oldUseDef) ||
            !oldLnf.equals(className.getText())) {
            JOptionPane.showMessageDialog(null, 
            		Globals.lang("You have changed the look and feel setting.")
            		.concat(" ")
            		.concat(Globals.lang("You must restart JabRef for this to come into effect.")),
            		Globals.lang("Changed look and feel settings"),
            		JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean readyToClose() {
	   
        try {
            int portNumber = Integer.parseInt(remoteServerPort.getText());
            if (portNumber > 1024 && portNumber <= 65535)
                return true; // Ok, the number was legal.
            else {
                JOptionPane.showMessageDialog
                    (null, Globals.lang("You must enter an integer value in the interval 1025-65535 in the text field for") + " '" +
                    Globals.lang("Remote server port") + "'", Globals.lang("Remote server port"),
                            JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog
                    (null, Globals.lang("You must enter an integer value in the interval 1025-65535 in the text field for") + " '" +
                    Globals.lang("Remote server port") + "'", Globals.lang("Remote server port"),
                            JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

	public String getTabName() {
		return Globals.lang("Advanced");
	}

}
