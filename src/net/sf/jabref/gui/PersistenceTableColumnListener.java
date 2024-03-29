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

import java.util.Vector;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;

import net.sf.jabref.Globals;
import net.sf.jabref.TableColumnsTab;

/**
 * Listens for TableColumnModelEvents to keep track of changes made to the
 * MainTable columns, like reordering or resizing.
 * 
 * Changes to columns without a name and the "#" column are not saved. To have
 * consistent behavior (e.g. as in TableColumnsTab).
 * 
 * @author Fabian Bieker
 * @author Daniel Waeber
 * @since 12/2008
 * 
 */
public class PersistenceTableColumnListener implements TableColumnModelListener {

	public static final String ACTIVATE_PREF_KEY = "ActivatePersistenceTableColumnListener";

	public static final boolean DEFAULT_ENABLED = true;

	private static final String simpleClassName = PersistenceTableColumnListener.class.getSimpleName();

	// needed to get column names / indices mapped from view to model 
	// and to access the table model 
	private final MainTable mainTable;

	/**
	 * @param mainTable
	 */
	public PersistenceTableColumnListener(final MainTable mainTable) {
		this.mainTable = mainTable;
	}

	/**
	 * update columns names and their width, store it in the global prefs.
	 */
	private void updateColumnPrefs() {
        final int columnCount = mainTable.getColumnCount();
		Vector<String> storedColumns = new Vector<String>(columnCount - 1);
		Vector<String> columnsWidths = new Vector<String>(columnCount - 1);

		for (int i = 0; i < columnCount; i++) {
			final String name = mainTable.getColumnName(i);
            if (name == null || name.equals("")) {
				continue;
			} else {
				storedColumns.add(name.toLowerCase());
				columnsWidths.add(String.valueOf(mainTable.getColumnModel().getColumn(i).getWidth()));
			}
		}

		// Finally, we store the new preferences.
		Globals.prefs.putStringArray(TableColumnsTab.COLUMN_NAME, storedColumns.toArray(new String[0]));
		Globals.prefs.putStringArray(TableColumnsTab.COLUMN_WIDTH, columnsWidths.toArray(new String[0]));
	}

	/**
	 * @see javax.swing.event.TableColumnModelListener#columnAdded(javax.swing.event.TableColumnModelEvent)
	 */
	public void columnAdded(TableColumnModelEvent e) {
		assert e != null : simpleClassName + " received null event";

		updateColumnPrefs();
	}

	/**
	 * @see javax.swing.event.TableColumnModelListener#columnMarginChanged(javax.swing.event.ChangeEvent)
	 */
	public void columnMarginChanged(ChangeEvent e) {
		assert e != null : simpleClassName + " received null event";
		
		updateColumnPrefs();
	}

	/**
	 * @see javax.swing.event.TableColumnModelListener#columnMoved(javax.swing.event.TableColumnModelEvent)
	 */
	public void columnMoved(TableColumnModelEvent e) {
		assert e != null : simpleClassName + " received null event";

		// not really moved, ignore ...
		if (e.getFromIndex() == e.getToIndex())
			return;

		updateColumnPrefs();

	}

	/**
	 * @see javax.swing.event.TableColumnModelListener#columnRemoved(javax.swing.event.TableColumnModelEvent)
	 */
	public void columnRemoved(TableColumnModelEvent e) {
		assert e != null : simpleClassName + " received null event";

		updateColumnPrefs();

	}

	/**
	 * @see javax.swing.event.TableColumnModelListener#columnSelectionChanged(javax.swing.event.ListSelectionEvent)
	 */
	public void columnSelectionChanged(ListSelectionEvent e) {
		// ignore
	}

}
