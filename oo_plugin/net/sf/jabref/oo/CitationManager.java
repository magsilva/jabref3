package net.sf.jabref.oo;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.swing.EventTableModel;
import com.jgoodies.forms.builder.ButtonBarBuilder;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.sun.star.container.XNameAccess;
import net.sf.jabref.Globals;
import net.sf.jabref.JabRefFrame;
import net.sf.jabref.gui.FileListEntry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Dialog for modifying existing citations.
 */
public class CitationManager {

    OOBibBase ooBase;
    JDialog diag;
    EventList<CitEntry> list;
    JTable table;
    EventTableModel tableModel;
    JButton ok = new JButton(Globals.lang("Ok")),
        cancel = new JButton(Globals.lang("Cancel"));

    public CitationManager(final JabRefFrame frame, OOBibBase ooBase) throws Exception {
        diag = new JDialog(frame, Globals.lang("Manage citations"), true);
        this.ooBase = ooBase;

        list = new BasicEventList<CitEntry>();
        XNameAccess nameAccess = ooBase.getReferenceMarks();
        String[] names = ooBase.getJabRefReferenceMarks(nameAccess);
        for (int i=0; i<names.length; i++) {
            String name = names[i];
            List<String> keys = ooBase.parseRefMarkName(name);
            list.add(new CitEntry(name, keys,
                    "<html>..."+ooBase.getCitationContext(nameAccess, name, 30, 30, true)+"...</html>",
                    ooBase.getCustomProperty(name)));
        }
        tableModel = new EventTableModel(list, new CitEntryFormat());
        table = new JTable(tableModel);
        diag.add(new JScrollPane(table), BorderLayout.CENTER);

        ButtonBarBuilder bb = new ButtonBarBuilder();
        bb.addGlue();
        bb.addGridded(ok);
        bb.addGridded(cancel);
        bb.addGlue();
        bb.getPanel().setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        diag.add(bb.getPanel(), BorderLayout.SOUTH);

        diag.pack();
        diag.setSize(700, 400);

        Action okAction = new AbstractAction() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    storeSettings();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, Globals.lang("Problem modifying citation"));
                }
                diag.dispose();
            }
        };
        ok.addActionListener(okAction);

        Action cancelAction = new AbstractAction() {
            public void actionPerformed(ActionEvent actionEvent) {
                diag.dispose();
            }
        };
        cancel.addActionListener(cancelAction);

        bb.getPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put
                (Globals.prefs.getKey("Close dialog"), "close");
        bb.getPanel().getActionMap().put("close", cancelAction);

        table.getColumnModel().getColumn(0).setPreferredWidth(600);
        table.getColumnModel().getColumn(1).setPreferredWidth(90);
        table.setPreferredScrollableViewportSize(new Dimension(700, 500));
        table.addMouseListener(new TableClickListener());
    }

    private void storeSettings() throws Exception {
        for (CitEntry entry : list) {
            if (entry.pageInfoChanged()) {
                ooBase.setCustomProperty(entry.refMarkName, entry.pageInfo);
            }
        }
    }

    public void showDialog() {
        diag.setLocationRelativeTo(diag.getParent());
        diag.setVisible(true);
    }

    class CitEntry implements Comparable {
        String refMarkName, pageInfo, keyString, context, origPageInfo;
        List<String> keys;

        public CitEntry(String refMarkName, List<String> keys, String context, String pageInfo) {
            this.refMarkName = refMarkName;
            this.keys = keys;
            this.context = context;
            this.pageInfo = pageInfo;
            this.origPageInfo = pageInfo;
            StringBuilder sb = new StringBuilder();
            for (int j=0; j<keys.size(); j++) {
                sb.append(keys.get(j));
                if (j < keys.size()-1)
                    sb.append(", ");
            }
            keyString = sb.toString();
        }

        public boolean pageInfoChanged() {
            if (((pageInfo != null) && (origPageInfo == null))
                || ((pageInfo == null) && (origPageInfo != null)))
                return true;
            if (pageInfo != null)
                return pageInfo.compareTo(origPageInfo) != 0;
            else return false;
        }

        public int compareTo(Object o) {
            CitEntry other = (CitEntry)o;
            return this.refMarkName.compareTo(other.refMarkName);
        }
    }

    class CitEntryFormat implements TableFormat<CitEntry> {

        public int getColumnCount() {
            return 2;
        }

        public String getColumnName(int i) {
            switch (i) {
                case 0: return Globals.lang("Citation");
                //case 1: return Globals.lang("Context");
                default: return Globals.lang("Extra information");
            }
        }

        public Object getColumnValue(CitEntry citEntry, int i) {
            switch (i) {
                //case 0: return citEntry.keyString;
                case 0: return citEntry.context;
                default: return citEntry.pageInfo != null ? citEntry.pageInfo : "";
            }
        }
    }

    class TableClickListener extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if ((e.getButton() == MouseEvent.BUTTON1) && (e.getClickCount() == 2)) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    SingleCitDialog scd = new SingleCitDialog(list.get(row));
                    scd.showDialog();
                }
            }
            //else if (e.isPopupTrigger())
            //    processPopupTrigger(e);
        }
    }

    class SingleCitDialog {
        JDialog diag;
        JTextField pageInfo = new JTextField(20);
        JLabel title;
        JButton ok = new JButton(Globals.lang("Ok")),
                cancel = new JButton(Globals.lang("Cancel"));
        CitEntry _entry;

        public SingleCitDialog(CitEntry entry) {
            this._entry = entry;
            title = new JLabel(entry.context);
            pageInfo.setText(entry.pageInfo);

            diag = new JDialog(CitationManager.this.diag, Globals.lang("Citation"), true);

            DefaultFormBuilder b = new DefaultFormBuilder(
                    new FormLayout("left:pref, 4dlu, fill:pref", "p, p"));
            b.append(title, 3);
            b.nextLine();
            b.append(Globals.lang("Extra information (e.g. page number)"));
            b.append(pageInfo);
            b.getPanel().setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            diag.getContentPane().add(b.getPanel(), BorderLayout.CENTER);

            ButtonBarBuilder bb = new ButtonBarBuilder();
            bb.addGlue();
            bb.addGridded(ok);
            bb.addGridded(cancel);
            bb.addGlue();
            bb.getPanel().setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            diag.add(bb.getPanel(), BorderLayout.SOUTH);

            diag.pack();

            Action okAction = new AbstractAction() {
                public void actionPerformed(ActionEvent actionEvent) {
                    if (pageInfo.getText().trim().length() > 0) {
                        _entry.pageInfo = pageInfo.getText().trim();
                    }
                    else
                        _entry.pageInfo = null;
                    tableModel.fireTableDataChanged();
                    diag.dispose();
                }
            };
            ok.addActionListener(okAction);

            Action cancelAction = new AbstractAction() {
                public void actionPerformed(ActionEvent actionEvent) {
                    diag.dispose();
                }
            };
            cancel.addActionListener(cancelAction);

            b.getPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put
                    (Globals.prefs.getKey("Close dialog"), "close");
            b.getPanel().getActionMap().put("close", cancelAction);

        }

        public void showDialog() {
            diag.setLocationRelativeTo(diag.getParent());
            diag.setVisible(true);
        }
    }
}
