package net.sf.jabref.gui;

import net.sf.jabref.Globals;

import javax.swing.table.AbstractTableModel;
import javax.swing.event.TableModelEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Data structure to contain a list of file links, parseable from a coded string.
 * Doubles as a table model for the file list editor.
*/
public class FileListTableModel extends AbstractTableModel {

    private ArrayList list = new ArrayList();

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

    public Class getColumnClass(int columnIndex) {
        return String.class;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        synchronized (list) {
            FileListEntry entry = (FileListEntry)list.get(rowIndex);
            switch (columnIndex) {
                case 0: return entry.getDescription();
                case 1: return entry.getLink();
                default: return entry.getType() != null ?
                        entry.getType().getName() : "";
            }
        }
    }

    public FileListEntry getEntry(int index) {
        return (FileListEntry)list.get(index);
    }

    public void removeEntry(int index) {
        synchronized (list) {
            list.remove(index);
        }
        fireTableRowsDeleted(index, index);
    }

    public void addEntry(int index, FileListEntry entry) {
        synchronized (list) {
            list.add(index, entry);
        }
        fireTableRowsInserted(index, index);
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
        ArrayList newList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        ArrayList thisEntry = new ArrayList();
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

    private FileListEntry decodeEntry(ArrayList contents) {
        if (contents.size() < 3) {
            return null;
        }
        return new FileListEntry((String)contents.get(0),
                (String)contents.get(1),
                Globals.prefs.getExternalFileTypeByName((String)contents.get(2)));
    }

    /**
     * Transform the file list shown in the table into a flat string representable
     * as a BibTeX field:
     * @return
     */
    public String getStringRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            FileListEntry entry = (FileListEntry) iterator.next();
            sb.append(encodeEntry(entry));
            if (iterator.hasNext())
                sb.append(';');
        }
        return sb.toString();
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
}
