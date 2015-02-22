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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.ironiacorp.string.StringUtil;

/**
 * UI element used to configure the entries shown in the main table.
 */
public class TableColumnsTab extends JPanel implements PrefsTab
{
    class TableRow {
        String name;
        
        int length;
        
        public TableRow() {
        	this("", GUIGlobals.DEFAULT_FIELD_LENGTH, "");
        }

        
        public TableRow(String name) {
        	this(name, GUIGlobals.DEFAULT_FIELD_LENGTH, name);
        }
        
        public TableRow(int length) {
        	this("", length, "");
        }
        
        public TableRow(String name, int length) {
        	this(name, length, name);
        }

        public TableRow(String name, int length, String fields) {
            this.name = name;
            this.length = length;
        }
        
    }

    public static final String COLUMN_NAME = "columnNames";
    
    public static final String COLUMN_WIDTH = "columnWidths";
    
    private JabRefPreferences _prefs;
    
    private boolean tableChanged = false;
    
    /**
     * Table with the field's configuration.
     */
    private JTable colSetup;
       
    private Vector<TableRow> tableRows = new Vector<TableRow>(10);
    
    private JabRefFrame frame;



    /**
     * Table for the configuration of the fields to be shown in the main GUI table.
     *
     * @param prefs JabRefPreferences
     * @param frame Frame where the table will be used.
     */
    public TableColumnsTab(JabRefPreferences prefs, JabRefFrame frame) {
        JPanel entryPanel;
    	TableModel entryTableModel;
        JScrollPane entryTablePanel;
        JToolBar entryToolbar;
        JPanel buttonsPanel;
        JButton buttonWidth;
        JButton buttonOrder;
        
        this._prefs = prefs;
        this.frame = frame;

        // Create entry table (with scrolling)
        entryTableModel = new AbstractTableModel() {
                public int getRowCount() {
                	return tableRows.size();
                }
                public int getColumnCount() {
                	return 2;
                }
                public Object getValueAt(int row, int column) {
                	if (row >= tableRows.size() || row < 0) {
                		throw new IllegalArgumentException("Invalid row");
                	}
                	if (column >= getColumnCount() || column < 0) {
                		throw new IllegalArgumentException("Invalid column");
                	}

                	Object rowContent = tableRows.elementAt(row);
                	if (rowContent == null) {
                		throw new IllegalArgumentException("Invalid row");
                	}
                	TableRow tr = (TableRow) rowContent;
                	if (column == 0) {
                   		return tr.name;
                	} else {
                   		return ((tr.length > 0) ? Integer.toString(tr.length) : -1);
                	}
                }

                public String getColumnName(int column) {
                    if (column == 0) {
                    	return Globals.lang("Field name");
                    } else {
                    	return Globals.lang("Column width");
                    }
                }
                
                public Class<?> getColumnClass(int column) {
                    if (column == 0) {
                    	return String.class;
                    } else {
                    	return Integer.class;
                    }
                }
                
                public boolean isCellEditable(int row, int col) {
                    return true;
                }
                
                public void setValueAt(Object value, int row, int col) {
                	TableRow rowContent = tableRows.elementAt(row);
                    if (col == 0) {
                    	if (value == null || StringUtil.isEmpty(value.toString())) {
                    		throw new IllegalArgumentException();
                    	}
                        rowContent.name = value.toString();
                        if (StringUtil.isEmpty((String) getValueAt(row, 1))) {
                            setValueAt(GUIGlobals.DEFAULT_FIELD_LENGTH, row, 1);
                        }
                    } else {
                        if (value == null) {
                        	rowContent.length = -1;
                        } else {
                        	rowContent.length = Integer.parseInt(value.toString());
                        }
	                }
                    tableChanged = true;
                }

            };

        colSetup = new JTable(entryTableModel);
        colSetup.setPreferredScrollableViewportSize(new Dimension(250,250));
        entryTablePanel = new JScrollPane(colSetup, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,  JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        entryTablePanel.setMinimumSize(new Dimension(250,300));
        
        // Creates toolbar at the left of the table
        entryToolbar = new JToolBar(SwingConstants.VERTICAL);
        entryToolbar.setFloatable(false);
        entryToolbar.setBorder(null);
        entryToolbar.add(new AddRowAction());
        entryToolbar.add(new DeleteRowAction());
        entryToolbar.addSeparator();
        entryToolbar.add(new MoveRowUpAction());
        entryToolbar.add(new MoveRowDownAction());
       
        // Configure the tab panel with the table and the toolbar
        entryPanel = new JPanel();
        entryPanel.setLayout(new BorderLayout(5, 5));
        entryPanel.add(entryTablePanel, BorderLayout.CENTER);
        entryPanel.add(entryToolbar, BorderLayout.EAST);

        buttonWidth = new JButton(new UpdateWidthsAction());
        buttonOrder = new JButton(new UpdateOrderAction());
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(buttonWidth);
        buttonsPanel.add(buttonOrder);
        
        setLayout(new BorderLayout(5, 5));
        add(entryPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    
    public void setValues()
    {
		tableRows.clear();
		String[] names = _prefs.getStringArray(TableColumnsTab.COLUMN_NAME);
		String[] lengths = _prefs.getStringArray(TableColumnsTab.COLUMN_WIDTH);
        for (int i = 0; i < names.length; i++) {
            try {
                tableRows.add(new TableRow(names[i], Integer.parseInt(lengths[i])));
            } catch (Exception e) {
                tableRows.add(new TableRow(names[i]));
            }
        }
    }


    class DeleteRowAction extends AbstractAction {
        public DeleteRowAction() {
        	super("Delete row", GUIGlobals.getImage("remove"));
        	putValue(SHORT_DESCRIPTION, Globals.lang("Delete rows"));
        }
        
        public void actionPerformed(ActionEvent e) {
        	int[] rows = colSetup.getSelectedRows();
        	if (rows.length > 0) {
	          	for (int i : rows) {
	       			tableRows.remove(i);
	        	}
	       		colSetup.clearSelection();
	        	colSetup.revalidate();
	        	colSetup.repaint();
	        	tableChanged = true;
        	}
        }
    }

    class AddRowAction extends AbstractAction {
        public AddRowAction() {
          super("Add row", GUIGlobals.getImage("add"));
          putValue(SHORT_DESCRIPTION, Globals.lang("Insert rows"));
        }
        
        public void actionPerformed(ActionEvent e) {
            int[] rows = colSetup.getSelectedRows();
            if (rows.length == 0) {
                // No rows selected, so we just add one at the end.
                tableRows.add(new TableRow());
            } else {
                Arrays.sort(rows);
	            for (int i = (rows.length - 1); i >= 0; i--) {
                    tableRows.add(rows[i], new TableRow());
	            }
	        	colSetup.clearSelection();
            }
            colSetup.revalidate();
            colSetup.repaint();
            tableChanged = true;
        }
    }

    abstract class AbstractMoveRowAction extends AbstractAction {
		public AbstractMoveRowAction(String string, ImageIcon image) {
			super(string, image);
		}

		protected void swap(int i, int j) {
			if (i < 0 || i >= tableRows.size()) {
				return;
			}
			if (j < 0 || j >= tableRows.size()) {
				return;
			}
			TableRow tmp = tableRows.get(i);
			tableRows.set(i, tableRows.get(j));
			tableRows.set(j, tmp);
		}
	}

	class MoveRowUpAction extends AbstractMoveRowAction {
		public MoveRowUpAction() {
			super("Up", GUIGlobals.getImage("up"));
			putValue(SHORT_DESCRIPTION, Globals.lang("Move up"));
		}

		public void actionPerformed(ActionEvent e) {
			int selected[] = colSetup.getSelectedRows();
			if (selected.length > 0) {
				Arrays.sort(selected);
				if (selected[0] > 0) {
					boolean newSelected[] = new boolean[colSetup.getRowCount()];
					for (int i : selected) {
						swap(i, i - 1);
						newSelected[i - 1] = true;
					}
					
					// select all and remove unselected
					colSetup.setRowSelectionInterval(0, colSetup.getRowCount() - 1);
					for (int i = 0; i < colSetup.getRowCount(); i++) {
						if (! newSelected[i])
							colSetup.removeRowSelectionInterval(i, i);
					}
					colSetup.revalidate();
					colSetup.repaint();
					tableChanged = true;
				}
			}
		}
	}

	class MoveRowDownAction extends AbstractMoveRowAction {
		public MoveRowDownAction() {
			super("Down", GUIGlobals.getImage("down"));
			putValue(SHORT_DESCRIPTION, Globals.lang("Down"));
		}

		public void actionPerformed(ActionEvent e) {
			int selected[] = colSetup.getSelectedRows();
			if (selected.length > 0) {
				Arrays.sort(selected);
				// don't move if a selected element is at bounce
				if (selected[selected.length - 1] < (tableRows.size() - 1)) {
					boolean newSelected[] = new boolean[colSetup.getRowCount()];
					for (int i = (selected.length - 1); i >= 0; i--) {
						swap(selected[i], selected[i] + 1);
						newSelected[selected[i] + 1] = true;
					}
				
					// select all and remove unselected
					colSetup.setRowSelectionInterval(0, colSetup.getRowCount() - 1);
					for (int i = 0; i < colSetup.getRowCount(); i++) {
						if (! newSelected[i])
							colSetup.removeRowSelectionInterval(i, i);
					}
				}
				colSetup.revalidate();
				colSetup.repaint();
				tableChanged = true;
			}
		}
	}

	class UpdateOrderAction extends AbstractAction {
		public UpdateOrderAction() {
			super(Globals.lang("Update to current column order"));
		}

		public void actionPerformed(ActionEvent e) {
			BasePanel panel = frame.basePanel();
			if (panel == null) {
				return;
			}
			// idea: sort elements according to value stored in hash, keep
			// everything not inside hash/mainTable as it was
			final HashMap<String, Integer> map = new HashMap<String, Integer>();

			for (int i = 0; i < panel.mainTable.getColumnCount(); i++) {
				String name = panel.mainTable.getColumnName(i);
				if (! StringUtil.isEmpty(name)) {
					map.put(name.toLowerCase(), i);
				}
			}
			
			Collections.sort(tableRows, new Comparator<TableRow>() {
				public int compare(TableRow o1, TableRow o2) {
					Integer n1 = map.get(o1.name);
					Integer n2 = map.get(o2.name);
					if (n1 == null || n2 == null) {
						return 0;
					}
					return n1.compareTo(n2);
				}
			});

			colSetup.revalidate();
			colSetup.repaint();
			tableChanged = true;
		}
	}

    class UpdateWidthsAction extends AbstractAction {
        public UpdateWidthsAction() {
          super(Globals.lang("Update to current column widths"));
          //putValue(SHORT_DESCRIPTION, Globals.lang("Update to current column widths"));
        }
        
        public void actionPerformed(ActionEvent e) {
            BasePanel panel = frame.basePanel();
            if (panel == null)
            	return;
            
            TableColumnModel colMod = panel.mainTable.getColumnModel();
            for (int i = 0; i < colMod.getColumnCount(); i++) {
                String name = panel.mainTable.getColumnName(i).toLowerCase();
                int width = colMod.getColumn(i).getWidth();
                if ((i <= tableRows.size()) && (((String)colSetup.getValueAt(i, 0)).toLowerCase()).equals(name)) {
                    colSetup.setValueAt(Integer.toString(width), i, 1);
                } else { // Doesn't match; search for a matching col in our table
                    for (int j = 0; j < colSetup.getRowCount(); j++) {
                        if ((j < tableRows.size()) && (((String)colSetup.getValueAt(j, 0)).toLowerCase()).equals(name)) {
                            colSetup.setValueAt(Integer.toString(width), j, 1);
                            break;
                        }
                    }
                }         
	            colSetup.revalidate();
            	colSetup.repaint();
            }
        }
    }

    /**
     * Store changes to table preferences. This method is called when
     * the user clicks Ok.
     *
     */
    public void storeSettings() {
		if (colSetup.isEditing()) {
            int col = colSetup.getEditingColumn();
            int row = colSetup.getEditingRow();
            colSetup.getCellEditor(row, col).stopCellEditing();
        }

        // Now we need to make sense of the contents the user has made to the table setup table.
        if (tableChanged) {
            // First we remove all rows with empty names.
            for (int i = 0; i < tableRows.size(); i++) {
                if (StringUtil.isEmpty(tableRows.elementAt(i).name)) {
                    tableRows.removeElementAt(i);
                }
            }

            // Then we make arrays
            String[] names = new String[tableRows.size()];
            String[] widths = new String[tableRows.size()];

            for (int i = 0; i < tableRows.size(); i++) {
                TableRow tr = tableRows.elementAt(i);
                names[i] = tr.name.toLowerCase();
                widths[i] = Integer.toString(tr.length);
            }

            // Finally, we store the new preferences.
            _prefs.putStringArray(TableColumnsTab.COLUMN_NAME, names);
            _prefs.putStringArray(TableColumnsTab.COLUMN_WIDTH, widths);
            tableChanged = false;
        }

    }

    public boolean readyToClose() {
        return true;
    }

	public String getTabName() {
	    return Globals.lang("Entry table columns");
	}
}
