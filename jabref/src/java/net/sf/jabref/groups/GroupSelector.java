/*
 Copyright (C) 2003 Morten O. Alver, Nizar N. Batada

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
package net.sf.jabref.groups;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import net.sf.jabref.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.undo.CompoundEdit;

public class GroupSelector
    extends SidePaneComponent
    implements ListSelectionListener, ActionListener {

  public static final int
      DIM = 3, // The number of vector elements for each group.
      OFFSET = 0; // The number of vector elements before first group.

  /*
  Groups are stored in the vector like the following:
      field1, name1, regexp1, field2, name2, regexp2, ...
   */

  JButton newButton = new JButton
      (new ImageIcon(GUIGlobals.newSmallIconFile)),
      helpButton = new JButton
      (new ImageIcon(GUIGlobals.helpSmallIconFile)),
      refresh = new JButton
      (new ImageIcon(GUIGlobals.refreshSmallIconFile)),
      autoGroup = new JButton
      (new ImageIcon(GUIGlobals.autoGroupIcon)),
      openset = new JButton(Globals.lang("Settings"));

  Color bgColor = Color.white;
  JList list;
  JTree tree;
  ListModel listModel;
  JScrollPane sp;
  GridBagLayout gbl = new GridBagLayout();
  GridBagConstraints con = new GridBagConstraints();
  Vector groups;
  JabRefFrame frame;
  BasePanel panel;
  String searchField;
  JPopupMenu gropt = new JPopupMenu(),
      settings = new JPopupMenu();
  //JCheckBoxMenuItem
  JRadioButtonMenuItem
      andCb = new JRadioButtonMenuItem(Globals.lang("Intersection"), true),
      orCb = new JRadioButtonMenuItem(Globals.lang("Union"), false),
      floatCb = new JRadioButtonMenuItem(Globals.lang("Float"), true),
      highlCb = new JRadioButtonMenuItem(Globals.lang("Highlight"), false);
  JCheckBoxMenuItem invCb = new JCheckBoxMenuItem(Globals.lang("Inverted"), false),
      select = new JCheckBoxMenuItem(Globals.lang("Select matches"), false);
  //JMenu
   //   moreRow = new JMenuItem(Globals.lang("Size of groups interface (rows)"));
      //lessRow = new JMenuItem(Globals.lang("Show one less rows"));
  ButtonGroup bgr = new ButtonGroup(),
      visMode = new ButtonGroup();
  JButton expand = new JButton(new ImageIcon(GUIGlobals.downIconFile)),
      reduce = new JButton(new ImageIcon(GUIGlobals.upIconFile));
  SidePaneManager manager;
  JabRefPreferences prefs;
  GroupSelector ths;

  /**
   * The first element for each group defines which field to
   * use for the quicksearch. The next two define the name and
   * regexp for the group.
   */
  public GroupSelector(JabRefFrame frame, BasePanel panel, Vector groupData,
                       SidePaneManager manager, JabRefPreferences prefs) {
    super(manager);
    ths = this;
    this.prefs = prefs;
    final JabRefPreferences _prefs = prefs;
    final BasePanel _panel = panel;
    groups = groupData;
    this.manager = manager;
    this.frame = frame;
    this.panel = panel;
    double n = (double) (groups.size() - OFFSET);
    while ( (n > 0) && (n / DIM != Math.floor(n / DIM))) {
      groups.removeElementAt(groups.size() - 1);
      n = (double) (groups.size() - OFFSET);
      // If the number of elements is not divisible by DIM, we're
      // in trouble, so we must remove one or two elements.
    }
    floatCb.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent event) {
            _prefs.putBoolean("groupFloatSelections", floatCb.isSelected());
        } 
    });
    andCb.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent event) {
            _prefs.putBoolean("groupIntersectSelections", andCb.isSelected());
        } 
    });    
    invCb.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent event) {
            _prefs.putBoolean("groupInvertSelections", invCb.isSelected());
        } 
    });
    select.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent event) {
            _prefs.putBoolean("groupSelectMatches", select.isSelected());
        } 
    });
    if (prefs.getBoolean("groupFloatSelections")) {
        floatCb.setSelected(true);
        highlCb.setSelected(false);
    } else {
        highlCb.setSelected(true); 
        floatCb.setSelected(false);
    }
    if (prefs.getBoolean("groupIntersectSelections")) {
        andCb.setSelected(true);
        orCb.setSelected(false);
    } else {
        orCb.setSelected(true);
        andCb.setSelected(false);
    }
    invCb.setSelected(prefs.getBoolean("groupInvertSelections"));
    select.setSelected(prefs.getBoolean("groupSelectMatches"));
    openset.setMargin(new Insets(0, 0, 0, 0));
    settings.add(andCb);
    settings.add(orCb);
    settings.addSeparator();
    settings.add(invCb);
    settings.addSeparator();
    settings.add(highlCb);
    settings.add(floatCb);
    settings.addSeparator();
    settings.add(select);
    settings.addSeparator();
    //settings.add(moreRow);
    //settings.add(lessRow);
    openset.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (settings.isVisible()) {
          //System.out.println("oee");
          //settings.setVisible(false);
        }
        else {
          JButton src = (JButton) e.getSource();
          settings.show(src, 0, openset.getHeight());
        }
      }
    });

    expand.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        int i = _prefs.getInt("groupsVisibleRows")+1;
        list.setVisibleRowCount(i);
        list.revalidate();
        list.repaint();
        ths.revalidate();
        //_panel.sidePaneManager.revalidate();
        ths.repaint();
        _prefs.putInt("groupsVisibleRows", i);
      }
    });

    reduce.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int i = _prefs.getInt("groupsVisibleRows")-1;
        if (i<1)
          i=1;
        list.setVisibleRowCount(i);
        list.revalidate();
        list.repaint();
        ths.revalidate();
        //_panel.sidePaneManager.revalidate();
        ths.repaint();
        _prefs.putInt("groupsVisibleRows", i);
      }
    });

    Dimension butDim = new Dimension(20, 20);
    Dimension butDim2 = new Dimension(40, 20);
    newButton.setPreferredSize(butDim);
    newButton.setMinimumSize(butDim);
    refresh.setPreferredSize(butDim);
    refresh.setMinimumSize(butDim);
    helpButton.setPreferredSize(butDim);
    helpButton.setMinimumSize(butDim);
    autoGroup.setPreferredSize(butDim);
    autoGroup.setMinimumSize(butDim);
    openset.setPreferredSize(butDim2);
    openset.setMinimumSize(butDim2);
    expand.setPreferredSize(butDim);
    expand.setMinimumSize(butDim);
    reduce.setPreferredSize(butDim);
    reduce.setMinimumSize(butDim);

    newButton.addActionListener(this);
    refresh.addActionListener(this);
    andCb.addActionListener(this);
    orCb.addActionListener(this);
    invCb.addActionListener(this);
    autoGroup.addActionListener(this);
    newButton.setToolTipText(Globals.lang("New group"));
    refresh.setToolTipText(Globals.lang("Refresh view"));
    andCb.setToolTipText(Globals.lang(
        "Display only entries belonging to all selected"
        + " groups."));
    orCb.setToolTipText(Globals.lang(
        "Display all entries belonging to one or more "
        + "of the selected groups."));
    autoGroup.setToolTipText(Globals.lang(
        "Automatically create groups for database."));
      invCb.setToolTipText(Globals.lang("Show entries *not* in group selection"));
      floatCb.setToolTipText(Globals.lang("Move entries in group selection to the top"));
      highlCb.setToolTipText(Globals.lang("Gray out entries not in group selection"));
      select.setToolTipText(Globals.lang("Select entries in group selection"));
      expand.setToolTipText(Globals.lang("Show one more row"));
      reduce.setToolTipText(Globals.lang("Show one less rows"));

    bgr.add(andCb);
    bgr.add(orCb);
    visMode.add(floatCb);
    visMode.add(highlCb);
    setLayout(gbl);

    SidePaneHeader header = new SidePaneHeader
        ("Groups", GUIGlobals.groupsIconFile, this);
    con.gridwidth = GridBagConstraints.REMAINDER;
    con.fill = GridBagConstraints.BOTH;
    con.insets = new Insets(0, 0, 2, 0);
    gbl.setConstraints(header, con);
    add(header);
    con.gridwidth = 1;
    con.weightx = 1;
    con.insets = new Insets(0, 0, 0, 0);
    gbl.setConstraints(newButton, con);
    add(newButton);
    gbl.setConstraints(refresh, con);
    add(refresh);
    gbl.setConstraints(autoGroup, con);
    add(autoGroup);

    con.gridwidth = GridBagConstraints.REMAINDER;
    HelpAction helpAction = new HelpAction(frame.helpDiag,
                                           GUIGlobals.groupsHelp,
                                           "Help on groups");
    helpButton.addActionListener(helpAction);
    helpButton.setToolTipText(Globals.lang("Help on groups"));
    gbl.setConstraints(helpButton, con);
    add(helpButton);

    list = new JList();
    revalidateList();
    //list.setSelectedIndex(0);
    list.addListSelectionListener(this);
    list.setPrototypeCellValue("Suitable length");
    // The line above decides on the list's preferred width.
    list.setVisibleRowCount(prefs.getInt("groupsVisibleRows"));
    list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    DefaultMutableTreeNode root = new DefaultMutableTreeNode(new String("All Entries"));
    tree = new JTree(root);
    
    sp = new JScrollPane
        (list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
         JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    /*
    sp = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    */
    con.gridwidth = GridBagConstraints.REMAINDER;
    con.weighty = 1;
    gbl.setConstraints(sp, con);
    add(sp);

    JPanel pan = new JPanel();
    GridBagLayout gb = new GridBagLayout();
    gbl.setConstraints(pan, con);
    pan.setLayout(gb);
    con.weightx = 1;
    con.gridwidth = 1;
    gb.setConstraints(openset, con);
    pan.add(openset);
    con.weightx = 0;
    gb.setConstraints(expand, con);
    pan.add(expand);

    gb.setConstraints(reduce, con);
    pan.add(reduce);
        add(pan);
    /*
    JPanel lower = new JPanel();
    lower.setLayout(gbl);
    lower.setBorder(BorderFactory.createEtchedBorder());
    con.weighty = 0;
    con.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(andCb, con);
    lower.add(andCb);
    gbl.setConstraints(orCb, con);
    lower.add(orCb);
    gbl.setConstraints(invCb, con);
    lower.add(invCb);
    gbl.setConstraints(lower, con);
    add(lower);*/
    definePopup();
  }

  public void definePopup() {

    gropt.add(modifyAction);

    gropt.add(new AbstractAction(Globals.lang("Remove")) {
      public void actionPerformed(ActionEvent e) {
        String gname = (String) groups.elementAt
            (OFFSET + DIM * (list.getSelectedIndex() - 1) + 1);
        int conf = JOptionPane.showConfirmDialog
            (frame, Globals.lang("Remove group") + " '" + gname + "'?",
             Globals.lang("Remove group"), JOptionPane.YES_NO_OPTION);
        if (conf == JOptionPane.YES_OPTION) {
          try {

            int index = OFFSET + DIM * (list.getSelectedIndex() - 1);
            String field = (String) groups.elementAt(index),
                name = (String) groups.elementAt(index + 1),
                regexp = (String) groups.elementAt(index + 2);
            for (int i = 0; i < DIM; i++) {
              groups.removeElementAt(index);

            }
            revalidateList();

            // Store undo information.
            panel.undoManager.addEdit
                (new UndoableAddOrRemoveGroup
                 (ths, groups, index, false,
                  field, name, regexp));
            panel.markBaseChanged();
            frame.output(Globals.lang("Removed group") + " '"
                         + name + "'.");
          }
          catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println(Globals.lang("Unexpected error when "
                                            + "trying to remove group."));
          }
        }
      }
    });

    list.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        int index = list.locationToIndex(e.getPoint());
        if (index == 0) {
          list.setSelectedIndex(0);
        }
      }

      public void mouseClicked(MouseEvent e) {
        int index = list.locationToIndex(e.getPoint());
        if (e.getButton() == MouseEvent.BUTTON3) {
          if ( (index > 0) // Menu for index 0 also?
              && (index < list.getModel().getSize())) {
            list.setSelectedIndex(index);
            gropt.show(list, e.getPoint().x,
                       list.indexToLocation(index).y);
          }
        }
        else {
          if ( (index > 0) && (e.getClickCount() == 2)) {
            //list.setSelectedIndex(index);
            modifyAction.actionPerformed
                (new ActionEvent(list, 0, "Modify"));
          }
        }
      }
    });

  }

  public void valueChanged(ListSelectionEvent e) {
    if (!e.getValueIsAdjusting() && !list.isSelectionEmpty()) {
      int[] sel = list.getSelectedIndices();
      if ( (sel.length == 1) && (sel[0] == 0)) {
        // Show all entries.
        panel.stopShowingGroup();
        frame.output(Globals.lang("Displaying no groups") + ".");
      }
      else {
        // Show a search for the keyword of field sel*DIM+OFFSET-1
        // in the vector. The following is analogue with
        // the doSearch() method of SearchPane.

        // We use a search rule set that takes care of multiple groups,
        // in an AND or OR fashion, and optionally inverts it.
        AndOrSearchRuleSet searchRules =
            new AndOrSearchRuleSet(andCb.isSelected(), invCb.isSelected());
        for (int i = 0; i < sel.length; i++) {
          if (sel[i] > 0) {
            SearchRule rule = new QuickSearchRule
                ( (String) groups.elementAt(sel[i] * DIM + OFFSET - 3),
                 (String) groups.elementAt(sel[i] * DIM + OFFSET - 1));
            searchRules.addRule(rule);
          }
        }
        Hashtable searchOptions = new Hashtable();
        searchOptions.put("option", "dummy");
        DatabaseSearch search = new DatabaseSearch
            (searchOptions, searchRules, panel,
             Globals.GROUPSEARCH, floatCb.isSelected(), true, select.isSelected());
        search.start();
        frame.output(Globals.lang("Updated group selection") + ".");

        //groups.elementAt(sel*DIM+OFFSET-DIM+1)+"'.");

      }

    }
  }

  public void revalidateList() {
    revalidateList(0);
  }

  public void revalidateList(int sel) {
    Vector newData = new Vector();
    newData.add(Globals.lang("All entries"));
    for (int i = 1 + OFFSET; i < groups.size(); i += DIM) {
      newData.add(groups.elementAt(i));
    }
    list.clearSelection();
    list.setListData(newData);
    list.setSelectedIndex(sel);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == refresh) {
      valueChanged(new ListSelectionEvent(list, 1, 2, false));
    }
    if (e.getSource() == newButton) {

      GroupDialog gd = new GroupDialog(frame,
                                       groups, -1, //groups.size(),
                                       prefs.get("groupsDefaultField"));
      gd.show();
      if (gd.okPressed()) {
        revalidateList( (gd.index() - OFFSET) / DIM + 1);
        /*sp.getVerticalScrollBar().setValue
          (sp.getVerticalScrollBar().getMaximum());*/
        int index = gd.index();
        panel.undoManager.addEdit(new UndoableAddOrRemoveGroup
                                  (this, groups, index, true,
                                   (String) groups.elementAt(index),
                                   (String) groups.elementAt(index + 1),
                                   (String) groups.elementAt(index + 2)));
        panel.markBaseChanged();
        frame.output(Globals.lang("Created group") + " '"
                     + gd.name() + "'.");

      }

    }

    if (e.getSource() == autoGroup) {
      AutoGroupDialog gd = new AutoGroupDialog
          (frame, panel, this, groups,
           prefs.get("groupsDefaultField"), " .,", ",");
      gd.show();
      if (gd.okPressed()) {

      }
    }

    else if (e.getSource() instanceof JCheckBox) {
      valueChanged(new ListSelectionEvent(list, 1, 2, false));
    }
  }

  public void componentOpening() {
    valueChanged(new ListSelectionEvent(list, list.getSelectedIndex(),
                                        list.getSelectedIndex(), false));
  }

  public void componentClosing() {
    panel.stopShowingGroup();
    frame.groupToggle.setSelected(false);
  }

  AbstractAction modifyAction = new AbstractAction(Globals.lang("Modify")) {
    public void actionPerformed(ActionEvent e) {
      int index = OFFSET + DIM * (list.getSelectedIndex() - 1),
          groupIndex = list.getSelectedIndex();
      GroupDialog gd = new GroupDialog
          (frame, groups, index,
           prefs.get("groupsDefaultField"));
      gd.show();
      if (gd.okPressed()) {
        revalidateList( (gd.index() - OFFSET) / DIM + 1);

        // Store undo information.
        panel.undoManager.addEdit
            (new UndoableModifyGroup
             (ths, groups, gd.index(),
              gd.field(), gd.name(), gd.regexp(),
              gd.oldField(), gd.oldName(), gd.oldRegexp()));

        panel.markBaseChanged();
        frame.output(Globals.lang("Modified group") + " '" + gd.name() + "'.");
      }
    }
  };

  public static int findPos(Vector groups, String name) {
    int index = -1;
    for (int i = OFFSET; i < groups.size(); i += DIM) {
      if (name.toLowerCase().compareTo
          ( ( (String) groups.elementAt(i + 1)).toLowerCase()) < 0) {
        index = i;
        i = groups.size();
      }
    }
    if (index == -1) {
      index = groups.size();
    }
    return index;
  }

  /**
   * addGroups
   *
   * @param newGroups Vector of group information to insert.
   */
  public void addGroups(Vector newGroups, CompoundEdit ce) {
    double n = (double) (newGroups.size() - OFFSET);
    while ( (n > 0) && (n / DIM != Math.floor(n / DIM))) {
      newGroups.removeElementAt(groups.size() - 1);
      n = (double) (groups.size() - OFFSET);
      // If the number of elements is not divisible by DIM, we're
      // in trouble, so we must remove one or two elements.
    }

    for (int i = 0; i < (newGroups.size() - OFFSET) / DIM; i++) {
      int pos = OFFSET + i * DIM;
      int index = findPos(groups, (String) newGroups.elementAt(pos + 1));
      String regexp = (String) newGroups.elementAt(pos + 2),
          name = (String) newGroups.elementAt(pos + 1),
          field = (String) newGroups.elementAt(pos);
      groups.add(index, regexp);
      groups.add(index, name);
      groups.add(index, field);
      ce.addEdit
          (new UndoableAddOrRemoveGroup
           (this, groups, index, true,
            field, name, regexp));

    }
  }
}
