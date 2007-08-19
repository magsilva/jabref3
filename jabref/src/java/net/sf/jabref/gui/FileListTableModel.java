package net.sf.jabref.gui;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import net.sf.jabref.Globals;

/**
 * Data structure to contain a list of file links, parseable from a coded string.
 * Doubles as a table model for the file list editor.
*/
public class FileListTableModel extends AbstractTableModel {

    private final ArrayList<FileListEntry> list = new ArrayList<FileListEntry>();

    public FileListTableModel() {
    }

    public int getRowCount() {
        synchronized (list) {
            return list.size();
        }
    }

    public int getColumnCount() {
        return 3;
    }

    public Class<String> getColumnClass(int columnIndex) {
        return String.class;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        synchronized (list) {
            FileListEntry entry = list.get(rowIndex);
            switch (columnIndex) {
                case 0: return entry.getDescription();
                case 1: return entry.getLink();
                default: return entry.getType() != null ?
                        entry.getType().getName() : "";
            }
        }
    }

    public FileListEntry getEntry(int index) {
        synchronized (list) {
            return list.get(index);
        }
    }

    public void removeEntry(int index) {
        synchronized (list) {
            list.remove(index);
            fireTableRowsDeleted(index, index);
        }

    }

    /**
     * Add an entry to the table model, and fire a change event. The change event
     * is fired on the event dispatch thread.
     * @param index The row index to insert the entry at.
     * @param entry The entry to insert.
     */
    public void addEntry(final int index, final FileListEntry entry) {
        synchronized (list) {
            list.add(index, entry);
            if (!SwingUtilities.isEventDispatchThread()) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        fireTableRowsInserted(index, index);
                    }
                });
            } else
                fireTableRowsInserted(index, index);
        }

    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    /**
     * Set up the table contents based on the flat string representation of the file list
     * @param value The string representation
     */
    public void setContent(String value) {
        if (value == null)
            value = "";
        ArrayList<FileListEntry> newList = new ArrayList<FileListEntry>();
        StringBuilder sb = new StringBuilder();
        ArrayList<String> thisEntry = new ArrayList<String>();
        boolean escaped = false;
        for (int i=0; i<value.length(); i++) {
            char c = value.charAt(i);
            if (!escaped && (c == '\\')) {
                escaped = true;
                continue;
            }
            else if (!escaped && (c == ':')) {
                thisEntry.add(sb.toString());
                sb = new StringBuilder();
            }
            else if (!escaped && (c == ';')) {
                thisEntry.add(sb.toString());
                sb = new StringBuilder();
                newList.add(decodeEntry(thisEntry));
                thisEntry.clear();
            }
            else sb.append(c);
            escaped = false;
        }
        if (sb.length() > 0)
            thisEntry.add(sb.toString());
        if (thisEntry.size() > 0)
            newList.add(decodeEntry(thisEntry));

        synchronized (list) {
            list.clear();
            list.addAll(newList);
        }
        fireTableChanged(new TableModelEvent(this));
    }

    private FileListEntry decodeEntry(ArrayList<String> contents) {
        return new FileListEntry(getElementIfAvailable(contents, 0),
                getElementIfAvailable(contents, 1),
                Globals.prefs.getExternalFileTypeByName
                        (getElementIfAvailable(contents, 2)));
    }

    private String getElementIfAvailable(ArrayList<String> contents, int index) {
        if (index < contents.size())
            return contents.get(index);
        else return "";
    }

    /**
     * Transform the file list shown in the table into a flat string representable
     * as a BibTeX field:
     * @return String representation.
     */
    public String getStringRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (Iterator<FileListEntry> iterator = list.iterator(); iterator.hasNext();) {
            FileListEntry entry = iterator.next();
            sb.append(encodeEntry(entry));
            if (iterator.hasNext())
                sb.append(';');
        }
        return sb.toString();
    }

    /**
     * Transform the file list shown in the table into a HTML string representation
     * suitable for displaying the contents in a tooltip.
     * @return Tooltip representation.
     */
    public String getToolTipHTMLRepresentation() {
        StringBuilder sb = new StringBuilder("<html>");
        for (Iterator<FileListEntry> iterator = list.iterator(); iterator.hasNext();) {
            FileListEntry entry = iterator.next();
            sb.append(entry.getDescription()).append(" (").append(entry.getLink()).append(')');
            if (iterator.hasNext())
                sb.append("<br>");
        }
        return sb.append("</html>").toString();
    }

    private String encodeEntry(FileListEntry entry) {
        StringBuilder sb = new StringBuilder();
        sb.append(encodeString(entry.getDescription()));
        sb.append(':');
        sb.append(encodeString(entry.getLink()));
        sb.append(':');
        sb.append(encodeString(entry.getType() != null ? entry.getType().getName() : ""));
        return sb.toString();
    }

    private String encodeString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if ((c == ';') || (c == ':') || (c == '\\'))
                sb.append('\\');
            sb.append(c);
        }
        return sb.toString();
    }

    public void print() {
        System.out.println("----");
        for (Iterator<FileListEntry> iterator = list.iterator(); iterator.hasNext();) {
            FileListEntry fileListEntry = iterator.next();
            System.out.println(fileListEntry);
        }
        System.out.println("----");
    }

   
}
