package net.sf.jabref;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

/**
 * <p>Title: MergeDialog</p>
 * <p>Description: Asks for details about merge database operation.</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @author Morten O. Alver
 */

public class MergeDialog extends JDialog {
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JButton ok = new JButton();
  JButton Cancel = new JButton();
  TitledBorder titledBorder1;
  JCheckBox entries = new JCheckBox();
  JCheckBox strings = new JCheckBox();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JCheckBox groups = new JCheckBox();

  boolean okPressed = false;

  public MergeDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public MergeDialog() {
    this(null, "", false);
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder(BorderFactory.createLineBorder(new Color(153, 153, 153),2),"Options");
    panel1.setLayout(borderLayout1);
    ok.setText("Ok");
    ok.addActionListener(new MergeDialog_ok_actionAdapter(this));
    Cancel.setText("Cancel");
    Cancel.addActionListener(new MergeDialog_Cancel_actionAdapter(this));
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(gridBagLayout1);
    entries.setToolTipText("");
    entries.setSelected(true);
    entries.setText("Import entries");
    strings.setSelected(true);
    strings.setText("Import strings");
    groups.setText("Import group definitions");
    this.setModal(true);
    this.setResizable(false);
    this.setTitle("Import database");
    getContentPane().add(panel1);
    panel1.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(ok, null);
    jPanel2.add(Cancel, null);
    panel1.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(entries,     new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(strings,     new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(groups,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

    // Key bindings:
    ActionMap am = jPanel1.getActionMap();
    InputMap im = jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    im.put(GUIGlobals.exitDialog, "close");
    am.put("close", new AbstractAction() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });

  }

  void ok_actionPerformed(ActionEvent e) {
    okPressed = true;
    dispose();
  }

  void Cancel_actionPerformed(ActionEvent e) {
    dispose();
  }

  public boolean okPressed() {
    return okPressed;
  }

  public boolean importEntries() { return entries.isSelected(); }
  public boolean importGroups() { return groups.isSelected(); }
  public boolean importStrings() { return strings.isSelected(); }
}

class MergeDialog_ok_actionAdapter implements java.awt.event.ActionListener {
  MergeDialog adaptee;

  MergeDialog_ok_actionAdapter(MergeDialog adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.ok_actionPerformed(e);
  }
}

class MergeDialog_Cancel_actionAdapter implements java.awt.event.ActionListener {
  MergeDialog adaptee;

  MergeDialog_Cancel_actionAdapter(MergeDialog adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Cancel_actionPerformed(e);
  }
}
