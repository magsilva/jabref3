/*
Copyright (C) 2003 Morten O. Alver and Nizar N. Batada

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

package net.sf.jabref;

import net.sf.jabref.undo.*;
import net.sf.jabref.export.*;
import net.sf.jabref.groups.QuickSearchRule;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.datatransfer.*;
import javax.swing.undo.*;

public class BasePanel extends JSplitPane implements MouseListener, 
						     ClipboardOwner {

    BasePanel ths = this;
    JSplitPane splitPane;

    JabRefFrame frame;
    BibtexDatabase database;
    JabRefPreferences prefs;
    // The database shown in this panel.
    File file = null, 
	fileToOpen = null; // The filename of the database.

    //Hashtable autoCompleters = new Hashtable();
    // Hashtable that holds as keys the names of the fields where 
    // autocomplete is active, and references to the autocompleter objects.

    // The undo manager.
    public CountingUndoManager undoManager = new CountingUndoManager();
    UndoAction undoAction = new UndoAction();
    RedoAction redoAction = new RedoAction();

    //ExampleFileFilter fileFilter;
    // File filter for .bib files.

    boolean baseChanged = false;
    // Used to track whether the base has changed since last save.

    EntryTableModel tableModel = null;
    EntryTable entryTable = null;

    BibtexEntry showing = null;
    // To indicate which entry is currently shown.
    HashMap entryEditors = new HashMap();
    // To contain instantiated entry editors. This is to save time
    // in switching between entries.

    //HashMap entryTypeForms = new HashMap();
    // Hashmap to keep track of which entries currently have open
    // EntryTypeForm dialogs.
    
    PreambleEditor preambleEditor = null;
    // Keeps track of the preamble dialog if it is open.

    StringDialog stringDialog = null;
    // Keeps track of the string dialog if it is open.

    boolean showingSearchResults = false, 
	showingGroup = false;

    // The sidepane manager takes care of populating the sidepane.
    SidePaneManager sidePaneManager;

    // MetaData parses, keeps and writes meta data.
    MetaData metaData;
    //## keep track of all keys for duplicate key warning and unique key generation
    private HashMap allKeys  = new HashMap();	// use a map instead of a set since i need to know how many of each key is inthere

    private boolean suppressOutput = false;

    private HashMap actions = new HashMap();
   
    public BasePanel(JabRefFrame frame, JabRefPreferences prefs) {
	database = new BibtexDatabase();
	metaData = new MetaData();
	this.frame = frame;
	this.prefs = prefs;
	setupActions();
	setupMainPanel();
    }

    public BasePanel(JabRefFrame frame, BibtexDatabase db, File file,
		     HashMap meta, JabRefPreferences prefs) {
	super(JSplitPane.HORIZONTAL_SPLIT, true);
	this.frame = frame;
        database = db;
	this.prefs = prefs;
	parseMetaData(meta);
	setupActions();
	setupMainPanel();
	/*if (prefs.getBoolean("autoComplete")) {
	    db.setCompleters(autoCompleters);
	    }*/

        this.file = file;

    }

    void output(String s) {
	if (!suppressOutput)
	    frame.output(s);
    }

    /**
     * BaseAction is used to define actions that are called from the
     * base frame through runCommand(). runCommand() finds the
     * appropriate BaseAction object, and runs its action() method.
     */
    abstract class BaseAction {
	abstract void action();
    }

    private void setupActions() {

	actions.put("undo", undoAction);
	actions.put("redo", redoAction);

	// The action for opening an entry editor.
	actions.put("edit", new BaseAction() {
		public void action() {
		    int clickedOn = -1;
		    // We demand that one and only one row is selected.
		    if (entryTable.getSelectedRowCount() == 1) {
			clickedOn = entryTable.getSelectedRow();		
		    }
		    if (clickedOn >= 0) {
			String id =  tableModel.getNameFromNumber(clickedOn);
			BibtexEntry be = database.getEntryById(id);
			showEntry(be);		       
		    }
		}
	    	       
	    });

	// The action for saving a database.
	actions.put("save", new BaseAction() {
		public void action() {
		    if (file == null)
			runCommand("saveAs");
		    else {
			try {
			    FileActions.saveDatabase(database, metaData, file,
						     prefs, false, false);
			    undoManager.markUnchanged();
			    // (Only) after a successful save the following
			    // statement marks that the base is unchanged
			    // since last save:
			    baseChanged = false;
			    frame.setTabTitle(ths, file.getName());
			    frame.output(Globals.lang("Saved database")+" '"
					 +file.getPath()+"'.");
			} catch (SaveException ex) {
			    if (ex.specificEntry()) {
				// Error occured during processing of
				// be. Highlight it:
				int row = tableModel.getNumberFromName
				    (ex.getEntry().getId()),
				    topShow = Math.max(0, row-3);
				//Util.pr(""+row);
				entryTable.setRowSelectionInterval(row, row);
				entryTable.setColumnSelectionInterval
				    (0, entryTable.getColumnCount()-1);
				entryTable.scrollTo(topShow);
			    }
			    ex.printStackTrace();
			    JOptionPane.showMessageDialog
				(frame, Globals.lang("Could not save file")
				 +".\n"+ex.getMessage(), 
				 Globals.lang("Save database"),
				 JOptionPane.ERROR_MESSAGE);
			}		
		    }	
		}
	    });

	actions.put("saveAs", new BaseAction () {
		public void action() {
		    JFileChooser chooser = new JFileChooser
			(prefs.get("workingDirectory"));
		    Util.pr("BasePanel: must set file filter");
		    //chooser.setFileFilter(fileFilter);
		    int returnVal = chooser.showSaveDialog(frame);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
			String name = chooser.getSelectedFile().getName(),
			    path = chooser.getSelectedFile().getParent();
			if (!name.endsWith(".bib"))
			    name = name+".bib";
			file = new File(path, name);
			if (!file.exists() || 
			    (JOptionPane.showConfirmDialog
			     (frame, "File '"+name+"' exists. Overwrite?",
			      "Save database", JOptionPane.OK_CANCEL_OPTION) 
			     == JOptionPane.OK_OPTION)) {
			    runCommand("save");
			    prefs.put("workingDirectory", path);
			}
			else
			    file = null;
		    } else {
			// Cancelled.
		    }
		}
	    });

	// The action for copying selected entries.
	actions.put("copy", new BaseAction() {
		public void action() {
		    BibtexEntry[] bes = entryTable.getSelectedEntries();
		    
		    if ((bes != null) && (bes.length > 0)) {
			TransferableBibtexEntry trbe 
			    = new TransferableBibtexEntry(bes);
			Toolkit.getDefaultToolkit().getSystemClipboard()
			    .setContents(trbe, ths);
			output("Copied "+(bes.length>1 ? bes.length+" entries." 
					  : "1 entry."));
		    } else {
			// The user maybe selected a single cell.
			int[] rows = entryTable.getSelectedRows(),
			    cols = entryTable.getSelectedColumns();
			if ((cols.length == 1) && (rows.length == 1)) {
			    // Copy single value.
			    Object o = tableModel.getValueAt(rows[0], cols[0]);
			    if (o != null) {
				StringSelection ss = new StringSelection(o.toString());
				Toolkit.getDefaultToolkit().getSystemClipboard()
				    .setContents(ss, ths);

				output(Globals.lang("Copied cell contents")+".");
			    }
			}
		    }
		}
	    });
		
	actions.put("cut", new BaseAction() {
		public void action() {
		    runCommand("copy");
		    BibtexEntry[] bes = entryTable.getSelectedEntries();
		    if (bes.length > 0) {
			// Create a CompoundEdit to make the action undoable.
			NamedCompound ce = new NamedCompound
			    (bes.length > 1 ? Globals.lang("cut entries") 
			     : Globals.lang("cut entry"));
			// Loop through the array of entries, and delete them.
			for (int i=0; i<bes.length; i++) {
			    database.removeEntry(bes[i].getId());
			    ensureNotShowing(bes[i]);
			    ce.addEdit(new UndoableRemoveEntry
				       (database, bes[i], ths));
			}
			entryTable.clearSelection();
			frame.output(Globals.lang("Cut")+" "+
				     (bes.length>1 ? bes.length
				      +" "+ Globals.lang("entries") 
				      : Globals.lang("entry"))+".");
			ce.end();
			undoManager.addEdit(ce);		    
			refreshTable();
			markBaseChanged();
		    }	       		
		}
	    });

	actions.put("delete", new BaseAction() {
		public void action() {
		    BibtexEntry[] bes = entryTable.getSelectedEntries();

		    if (bes.length > 0) {
			//&& (database.getEntryCount() > 0) && (entryTable.getSelectedRow() < database.getEntryCount())) {
			
			/* 
			   I have removed the confirmation dialog, since I converted
			   the "remove" action to a "cut". That means the user can
			   always paste the entries, in addition to pressing undo.
			   So the confirmation seems redundant.
			  
			String msg = Globals.lang("Really delete the selected")
			    +" "+Globals.lang("entry")+"?",
			    title = Globals.lang("Delete entry");
			if (rows.length > 1) {
			    msg = Globals.lang("Really delete the selected")
				+" "+rows.length+" "+Globals.lang("entries")+"?";
			    title = Globals.lang("Delete multiple entries");
			}
			int answer = JOptionPane.showConfirmDialog(frame, msg, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (answer == JOptionPane.YES_OPTION) {*/

			// Create a CompoundEdit to make the action undoable.
			NamedCompound ce = new NamedCompound
			    (bes.length > 1 ? Globals.lang("delete entries") 
			     : Globals.lang("delete entry"));
			// Loop through the array of entries, and delete them.
			for (int i=0; i<bes.length; i++) {
			    database.removeEntry(bes[i].getId());
			    ensureNotShowing(bes[i]);
			    ce.addEdit(new UndoableRemoveEntry(database, bes[i], ths));
			}
			entryTable.clearSelection();
			frame.output(Globals.lang("Deleted")+" "+
				     (bes.length>1 ? bes.length
				      +" "+ Globals.lang("entries") 
				      : Globals.lang("entry"))+".");
			ce.end();
			undoManager.addEdit(ce);		    
			refreshTable();
			markBaseChanged();
		    }	       
		}



	    });

	// The action for pasting entries or cell contents.
	actions.put("paste", new BaseAction() {
		public void action() {
		    // We pick an object from the clipboard, check if
		    // it exists, and if it is a set of entries.
		    Transferable content = Toolkit.getDefaultToolkit()
			.getSystemClipboard().getContents(null);
		    if (content != null) {
			DataFlavor[] flavor = content.getTransferDataFlavors();
			if ((flavor != null) && (flavor.length > 0) && flavor[0].equals(TransferableBibtexEntry.entryFlavor)) {
			    // We have determined that the clipboard data is a set of entries.
			    BibtexEntry[] bes = null;
			    try {
				bes = (BibtexEntry[])(content.getTransferData(TransferableBibtexEntry.entryFlavor));
			    } catch (UnsupportedFlavorException ex) {
			    } catch (IOException ex) {}
			    
			    if ((bes != null) && (bes.length > 0)) {
				NamedCompound ce = new NamedCompound
				    (bes.length > 1 ? "paste entries" : "paste entry");
				for (int i=0; i<bes.length; i++) {
				    try { 
					BibtexEntry be = (BibtexEntry)(bes[i].clone());
					// We have to clone the
					// entries, since the pasted
					// entries must exist
					// independently of the copied
					// ones.
					be.setId(Util.createId(be.getType(), database));
					database.insertEntry(be);
					ce.addEdit(new UndoableInsertEntry
						   (database, be, ths));
				    } catch (KeyCollisionException ex) {
					Util.pr("KeyCollisionException... this shouldn't happen.");
				    }
				}
				ce.end();
				undoManager.addEdit(ce);
				tableModel.remap();
				entryTable.clearSelection();
				entryTable.revalidate();
				output("Pasted "+(bes.length>1 ? bes.length+" entries." : "1 entry."));
				refreshTable();
				markBaseChanged();
			    }
			}
			if ((flavor != null) && (flavor.length > 0) && flavor[0].equals(DataFlavor.stringFlavor)) { 
			    // We have determined that the clipboard data is a string.
			    int[] rows = entryTable.getSelectedRows(),
				cols = entryTable.getSelectedColumns();
			    if ((cols != null) && (cols.length == 1) && (cols[0] != 0)
				&& (rows != null) && (rows.length == 1)) {
				try {
				    tableModel.setValueAt((String)(content.getTransferData(DataFlavor.stringFlavor)), rows[0], cols[0]);
				    refreshTable();
				    markBaseChanged();			   
				    output("Pasted cell contents");
				} catch (UnsupportedFlavorException ex) {
				} catch (IOException ex) {
				} catch (IllegalArgumentException ex) {
				    output("Can't paste.");
				}
			    }
			}
		    }
		}
	    });	   

	actions.put("selectAll", new BaseAction() {
		public void action() {
		    entryTable.selectAll();
		}
	    });      

	// The action for opening the preamble editor
	actions.put("editPreamble", new BaseAction() {
		public void action() {
		    if (preambleEditor == null) {
			PreambleEditor form = new PreambleEditor
			    (frame, ths, database, prefs);
			Util.placeDialog(form, frame);
			form.setVisible(true);
			preambleEditor = form;
		    } else {
			preambleEditor.setVisible(true);
		    }
	    
		}
	    });

	// The action for opening the string editor
	actions.put("editStrings", new BaseAction() {
		public void action() {
		    if (stringDialog == null) {
			StringDialog form = new StringDialog
			    (frame, ths, database, prefs);
			Util.placeDialog(form, frame);
			form.setVisible(true);
			stringDialog = form;
		    } else {
			stringDialog.setVisible(true);
		    }
	    
		}
	    });

	// The action for toggling the groups interface
	actions.put("toggleGroups", new BaseAction() {
		public void action() {
		    sidePaneManager.togglePanel("groups");
		}
	    });

	actions.put("pushToLyX",new BaseAction(){
		public void action(){
		    int[] rows = entryTable.getSelectedRows();
		    int numSelected = rows.length;
		    BibtexEntry bes = null;
		    Globals.logger("Pushing " +numSelected+(numSelected>1? " entries" : "entry") + " to LyX");
		    // check if lyxpipe is defined
		    File lyxpipe = new File( prefs.get("lyxpipe") +".in"); // this needs to fixed because it gives "asdf" when going prefs.get("lyxpipe")
		    if( !lyxpipe.exists() || !lyxpipe.canWrite()){
			output("ERROR: verify that LyX is running and that the lyxpipe is valid. [" + prefs.get("lyxpipe") +"]");
			return;
		    }
		    if( numSelected > 0){
			try {
			    BufferedWriter lyx_out = new BufferedWriter(new FileWriter(lyxpipe));
			    String citeStr="", citeKey="", message="";
			    for(int i=0; i< numSelected; i++){
				bes = database.getEntryById( tableModel.getNameFromNumber( rows[i] ));
				citeKey= (String)bes.getField(GUIGlobals.KEY_FIELD);
				// if the key is empty we give a warning and ignore this entry
				if(citeKey==null || citeKey.equals(""))
				    continue;
				if(citeStr.equals(""))
				    citeStr= citeKey;
				else
				    citeStr += "," + citeKey;
				message+= ", " + rows[i];
			    }
			    if(citeStr.equals(""))
				output("Please define citekey first");
			    else{
				citeStr="LYXCMD:sampleclient:citation-insert:"+ citeStr;
				lyx_out.write(citeStr +"\n");
				output("Pushed the citations for the following rows to Lyx: " + message);
			    }
			    lyx_out.close();

			}
			catch (IOException excep) {
			    output("ERROR: unable to write to " + prefs.get("lyxpipe") +".in");
			}
		    }
		}
	    });
	// The action for auto-generating keys.
	actions.put("makeKey", new BaseAction() {
		public void action() {
		    int[] rows = entryTable.getSelectedRows() ;
		    int numSelected = rows.length ; 
		    BibtexEntry bes = null ;
		    if (numSelected > 0) {
			int answer = JOptionPane.showConfirmDialog
			    (frame, "Generate bibtex key"+
			     (numSelected>1 ? "s for the selected "
			      +numSelected+" entries?" :
			      " for the selected entry?"), 
			     "Autogenerate Bibtexkey", 
			     JOptionPane.YES_NO_CANCEL_OPTION);
			if (answer != JOptionPane.YES_OPTION) {
			    return ; 
			}
		    } else { // None selected. Inform the user to select entries first.
			JOptionPane.showMessageDialog(frame, "First select the entries you want keys to be generated for.",
						      "Autogenerate Bibtexkey", JOptionPane.INFORMATION_MESSAGE);
			return ;
		    }
		    
		    output("Generating Bibtexkey for "+numSelected+(numSelected>1 ? " entries" : "entry"));
		    
		    NamedCompound ce = new NamedCompound("autogenerate keys");
		    //BibtexEntry be;
		    Object oldValue;
		    for(int i = 0 ; i < numSelected ; i++){
			bes = database.getEntryById(tableModel.getNameFromNumber(rows[i]));
			oldValue = bes.getField(GUIGlobals.KEY_FIELD);
			bes = frame.labelMaker.applyRule(bes) ; 
			ce.addEdit(new UndoableFieldChange
				   (bes, GUIGlobals.KEY_FIELD, oldValue,
				    bes.getField(GUIGlobals.KEY_FIELD)));
		    }
		    ce.end();
		    undoManager.addEdit(ce);
		    markBaseChanged() ; 
		    refreshTable() ; 
		}
	    });

    }			    
		    

    /**
     * This method is called from JabRefFrame is a database specific
     * action is requested by the user. Runs the command if it is
     * defined, or prints an error message to the standard error
     * stream.
    */
    public void runCommand(String command) { 
	if (actions.get(command) == null)
	    Util.pr("No action defined for'"+command+"'");
	else ((BaseAction)actions.get(command)).action();
    }

    /**
     * This method is called from JabRefFrame when the user wants to
     * create a new entry. If the argument is null, the user is
     * prompted for an entry type.
     */
    public void newEntry(BibtexEntryType type) {
	if (type == null) {
	    // Find out what type is wanted.
	    EntryTypeDialog etd = new EntryTypeDialog(frame);	       
	    // We want to center the dialog, to make it look nicer.
	    Util.placeDialog(etd, frame);
	    etd.setVisible(true);
	    type = etd.getChoice();
	}
	if (type != null) { // Only if the dialog was not cancelled.
	    String id = Util.createId(type, database);
	    BibtexEntry be = new BibtexEntry(id, type);
	    try {
		database.insertEntry(be);
		
		// Create an UndoableInsertEntry object.
		undoManager.addEdit(new UndoableInsertEntry(database, be, ths));
		output("Added new "+type.getName().toLowerCase()+" entry.");
		refreshTable();
		markBaseChanged(); // The database just changed.
		if (prefs.getBoolean("autoOpenForm")) {
		    showEntry(be);
		    //EntryTypeForm etf = new EntryTypeForm(frame, ths, be, prefs);
		    //Util.placeDialog(etf, frame);
		    //etf.setVisible(true);
		    //entryTypeForms.put(id, etf);
		}
	    } catch (KeyCollisionException ex) {
		Util.pr(ex.getMessage());
	    }
	}
	
    }

    public void validateMainPanel() {
    }

    public void setupTable() {
	tableModel = new EntryTableModel(frame, this, database);
	entryTable = new EntryTable(tableModel, frame.prefs);

	entryTable.addMouseListener(this);
	entryTable.getInputMap().put(prefs.getKey("Cut"), "Cut");
	entryTable.getInputMap().put(prefs.getKey("Copy"), "Copy");
	entryTable.getInputMap().put(prefs.getKey("Paste"), "Paste");
	entryTable.getActionMap().put("Cut", new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
		    runCommand("cut");
		}
	    });
	entryTable.getActionMap().put("Copy", new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
		    runCommand("copy");
		}
	    });
	entryTable.getActionMap().put("Paste", new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
		    runCommand("paste");
		}
	    });
	
	entryTable.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					runCommand("edit");
				}
				else if(e.getKeyCode() == KeyEvent.VK_DELETE){
					runCommand("delete");
		    }
		}
	    });


	// Set the right-click menu for the entry table.
	RightClickMenu rcm = new RightClickMenu(this, metaData);
	entryTable.setRightClickMenu(rcm);
	int pos = splitPane.getDividerLocation();
	splitPane.setTopComponent(entryTable.getPane());
	splitPane.setDividerLocation(pos);
	//splitPane.revalidate();
    }

    public void setupMainPanel() {
	splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	splitPane.setDividerSize(GUIGlobals.SPLIT_PANE_DIVIDER_SIZE);

	setupTable();
	// If an entry is currently being shown, make sure it stays shown,
	// otherwise set the bottom component to null.
	if (showing == null)
	    splitPane.setBottomComponent(null);
	else
	    showEntry(showing);

	setRightComponent(splitPane);
	sidePaneManager = new SidePaneManager
	    (frame, this, prefs, metaData);
	sidePaneManager.populatePanel();

	//mainPanel.setDividerLocation(GUIGlobals.SPLIT_PANE_DIVIDER_LOCATION);
	setDividerSize(GUIGlobals.SPLIT_PANE_DIVIDER_SIZE);
	setResizeWeight(0);
	revalidate();
    }

    /**
     * This method is called after a database has been parsed. The
     * hashmap contains the contents of all comments in the .bib file
     * that started with the meta flag (GUIGlobals.META_FLAG).
     * In this method, the meta data are input to their respective
     * handlers.
     */
    public void parseMetaData(HashMap meta) {       
	metaData = new MetaData(meta);
    }

    public void refreshTable() {
	// This method is called by EntryTypeForm when a field value is
	// stored. The table is scheduled for repaint.
	tableModel.remap();
	entryTable.revalidate();
	entryTable.repaint();
    }

    public void updatePreamble() {
	if (preambleEditor != null)
	    preambleEditor.updatePreamble();
    }
    
    public void assureStringDialogNotEditing() {	
	if (stringDialog != null)
	    stringDialog.assureNotEditing();	
    }

    public void updateStringDialog() {
	if (stringDialog != null)
	    stringDialog.refreshTable(); 
    }

    public void showEntry(BibtexEntry be) {
	if (showing == be) {
	    if (splitPane.getBottomComponent() == null) {
		// This is the special occasion when showing is set to an
		// entry, but no entry editor is in fact shown. This happens
		// after Preferences dialog is closed, and it means that we
		// must make sure the same entry is shown again. We do this by
		// setting showing to null, and recursively calling this method.
	        showing = null;
		showEntry(be);
	    }
	    return;
	}
	
	EntryEditor form;
	int divLoc = -1, visPan = -1;
	if (showing != null)
	    visPan = ((EntryEditor)splitPane.getBottomComponent()).
		getVisiblePanel();
	if (showing != null)
	    divLoc = splitPane.getDividerLocation();	
    
	if (entryEditors.containsKey(be.getType().getName())) {
	    // We already have an editor for this entry type.
	    form = (EntryEditor)entryEditors.get
		((be.getType().getName()));
	    form.switchTo(be);
	    if (visPan >= 0)
		form.setVisiblePanel(visPan);
	    splitPane.setBottomComponent(form);
	} else {
	    // We must instantiate a new editor for this type.
	    form = new EntryEditor
		(frame, ths, be, prefs);
	    if (visPan >= 0)
		form.setVisiblePanel(visPan);
	    splitPane.setBottomComponent(form);
	    entryEditors.put(be.getType().getName(), form);
	}
	if (showing != null)
	    splitPane.setDividerLocation(divLoc);
	else
	    splitPane.setDividerLocation
		(GUIGlobals.VERTICAL_DIVIDER_LOCATION);	
	form.requestFocus();

	showing = be;
    }
    
    /**
     * Closes the entry editor.
     *
     */
    public void hideEntryEditor() {
	splitPane.setBottomComponent(null);
	new FocusRequester(entryTable);
	showing = null;
    }

    /**
     * Closes the entry editor if it is showing the given entry.
     *
     * @param be a <code>BibtexEntry</code> value
     */
    public void ensureNotShowing(BibtexEntry be) {
	if (showing == be) {
	    hideEntryEditor();      
	    showing = null;
	}
    }

    public void markBaseChanged() {
	baseChanged = true;
	
	// Put an asterix behind the file name to indicate the
	// database has changed.
	String oldTitle = frame.getTabTitle(this);
	if (!oldTitle.endsWith("*"))
	    frame.setTabTitle(this, oldTitle+"*");

	// If the status line states that the base has been saved, we
	// remove this message, since it is no longer relevant. If a
	// different message is shown, we leave it.
	if (frame.statusLine.getText().startsWith("Saved database"))
	    frame.output(" ");
    }

    public synchronized void markChangedOrUnChanged() {
	if (undoManager.hasChanged()) {
	    if (!baseChanged)
		markBaseChanged();
	}
	else if (baseChanged) {
	    baseChanged = false;
	    if (file != null)
		frame.setTabTitle(ths, file.getName());
	    else
		frame.setTabTitle(ths, Globals.lang("untitled"));
	}
    }

    /**
     * Shows either normal search results or group search, depending
     * on the searchValueField. This is done by reordering entries and
     * graying out non-hits.
     */
    public void showSearchResults(String searchValueField) {
	//entryTable.scrollTo(0);
	
	if (searchValueField == Globals.SEARCH)
	    showingSearchResults = true;	           
	else if (searchValueField == Globals.GROUPSEARCH)
	    showingGroup = true;
	
	entryTable.setShowingSearchResults(showingSearchResults,
					   showingGroup);
	entryTable.clearSelection();
	entryTable.scrollTo(0);
	refreshTable();
	
    }

    /**
     * Selects all entries with a non-zero value in the field
     * Globals.SEARCH.
     */
    public void selectSearchResults() {

	entryTable.clearSelection();
	for (int i=0; i<entryTable.getRowCount(); i++) {
	    String value = (String)(database.getEntryById
				    (tableModel.getNameFromNumber(i)))
		.getField(Globals.SEARCH);
	    if ((value != null) && !value.equals("0"))
		entryTable.addRowSelectionInterval(i, i);	    
	}
    }

    /**
     * Selects a single entry, and scrolls the table to center it.
     *
     */
    public void selectSingleEntry(int pos) {
	entryTable.clearSelection();
	entryTable.addRowSelectionInterval(pos, pos);
	entryTable.scrollToCenter(pos, 0);
    }

    public void stopShowingSearchResults() {
	showingSearchResults = false;
	entryTable.setShowingSearchResults(showingSearchResults,
					   showingGroup);
	refreshTable();
    }

    public void stopShowingGroup() {
	showingGroup = false;
	entryTable.setShowingSearchResults(showingSearchResults,
					   showingGroup);
	refreshTable();
    }

    protected EntryTableModel getTableModel(){
		return tableModel ; 
    }

    protected BibtexDatabase getDatabase(){
		return database ; 
    }

    public void entryTypeFormClosing(String id) {
	// Called by EntryTypeForm when closing.
	// Deprecated, since EntryEditor has replaced EntryTypeForm.
    }

    public void preambleEditorClosing() {
	preambleEditor = null;
    }

    public void stringsClosing() {
	stringDialog = null;
    }

    public void addToGroup(String groupName, String regexp, String field) {
	
	boolean giveWarning = false;
	for (int i=0; i<GUIGlobals.ALL_FIELDS.length; i++) {
	    if (field.equals(GUIGlobals.ALL_FIELDS[i])
		&& !field.equals("keywords")) {
		giveWarning = true;
		break;
	    }	       
	}
	if (giveWarning) {
	    String message = "This action will modify the '"+field+"' field "
		+"of your entries.\nThis could cause undesired changes to "
		+"your entries, so it\nis recommended that you change the field "
		+"in your group\ndefinition to 'keywords' or a non-standard name."
		+"\n\nDo you still want to continue?";
	    int choice = JOptionPane.showConfirmDialog
		(this, message, "Warning", JOptionPane.YES_NO_OPTION,
		 JOptionPane.WARNING_MESSAGE);
	    
	    if (choice == JOptionPane.NO_OPTION)
		return;
	}
	
	BibtexEntry[] bes = entryTable.getSelectedEntries();	    
	if ((bes != null) && (bes.length > 0)) {
	    QuickSearchRule qsr = new QuickSearchRule(field, regexp);
	    NamedCompound ce = new NamedCompound("add to group");
	    boolean hasEdits = false;
	    for (int i=0; i<bes.length; i++) {
		if (qsr.applyRule(null, bes[i]) == 0) {
		    String oldContent = (String)bes[i].getField(field),
			pre = " ",
			post = "";
		    String newContent = 
			(oldContent==null ? "" : oldContent+pre)
			+regexp+post;
		    bes[i].setField
			(field, newContent);
		    
		    // Store undo information.
		    ce.addEdit(new UndoableFieldChange
			       (bes[i], field, oldContent, newContent));
		    hasEdits = true;
		}
	    }
	    if (hasEdits) {
		ce.end();
		undoManager.addEdit(ce);
		refreshTable();
		markBaseChanged();
	    }		    

	    output("Appended '"+regexp+"' to the '"
		   +field+"' field of "+bes.length+" entr"+
		   (bes.length > 1 ? "ies." : "y."));
	}       
    }

    public void removeFromGroup
	(String groupName, String regexp, String field) {
	
	boolean giveWarning = false;
	for (int i=0; i<GUIGlobals.ALL_FIELDS.length; i++) {
	    if (field.equals(GUIGlobals.ALL_FIELDS[i])
		&& !field.equals("keywords")) {
		giveWarning = true;
		break;
	    }	       
	}
	if (giveWarning) {
	    String message = "This action will modify the '"+field+"' field "
		+"of your entries.\nThis could cause undesired changes to "
		+"your entries, so it\nis recommended that you change the field "
		+"in your group\ndefinition to 'keywords' or a non-standard name."
		+"\n\nDo you still want to continue?";
	    int choice = JOptionPane.showConfirmDialog
		(this, message, "Warning", JOptionPane.YES_NO_OPTION,
		 JOptionPane.WARNING_MESSAGE);
	    
	    if (choice == JOptionPane.NO_OPTION)
		return;
	}
	
	BibtexEntry[] bes = entryTable.getSelectedEntries();	    
	if ((bes != null) && (bes.length > 0)) {
	    QuickSearchRule qsr = new QuickSearchRule(field, regexp);
	    NamedCompound ce = new NamedCompound("remove from group");
	    boolean hasEdits = false;
	    for (int i=0; i<bes.length; i++) {
		if (qsr.applyRule(null, bes[i]) > 0) {
		    String oldContent = (String)bes[i].getField(field);
		    qsr.removeMatches(bes[i]);
		    		    // Store undo information.
		    ce.addEdit(new UndoableFieldChange
			       (bes[i], field, oldContent,
				bes[i].getField(field)));
		    hasEdits = true;
		}
	    }
	    if (hasEdits) {
		ce.end();
		undoManager.addEdit(ce);
		refreshTable();
		markBaseChanged();
	    }	    
	    
	    output("Removed '"+regexp+"' from the '"
		   +field+"' field of "+bes.length+" entr"+
		   (bes.length > 1 ? "ies." : "y."));
	}
	
    }

    public void changeType(BibtexEntryType type) {
	BibtexEntry[] bes = entryTable.getSelectedEntries();
	if ((bes == null) || (bes.length == 0)) {
	    output("First select the entries you wish to change type "+
		   "for.");
	    return;
	}
	if (bes.length > 1) {
	    int choice = JOptionPane.showConfirmDialog
		(this, "Multiple entries selected. Do you want to change"
		 +"\nthe type of all these to '"+type.getName()+"'?",
		 "Change type", JOptionPane.YES_NO_OPTION,
		 JOptionPane.WARNING_MESSAGE);
	    if (choice == JOptionPane.NO_OPTION)
		return;
	}

	NamedCompound ce = new NamedCompound("change type");
	for (int i=0; i<bes.length; i++) {
	    ce.addEdit(new UndoableChangeType(bes[i],
					      bes[i].getType(),
					      type));
	    bes[i].setType(type);
	}

	output("Changed type to '"+type.getName()+"' for "+bes.length
	       +" entries.");
	ce.end();
	undoManager.addEdit(ce);
	refreshTable();
	markBaseChanged();
    }

    class UndoAction extends BaseAction {
	public void action() {
	    try {
		String name = undoManager.getUndoPresentationName();
		undoManager.undo();
		markBaseChanged();
		refreshTable();
		frame.output(name);
	    } catch (CannotUndoException ex) {
		frame.output(Globals.lang("Nothing to undo")+".");
	    }
	    // After everything, enable/disable the undo/redo actions
	    // appropriately.
	    //updateUndoState();
	    //redoAction.updateRedoState();
	    markChangedOrUnChanged();
	}
    }

    class RedoAction extends BaseAction {
	public void action() {
	    try {
		String name = undoManager.getRedoPresentationName();
		undoManager.redo();
		markBaseChanged();
		refreshTable();
		frame.output(name);
	    } catch (CannotRedoException ex) {
		frame.output(Globals.lang("Nothing to redo")+".");
	    }
	    // After everything, enable/disable the undo/redo actions
	    // appropriately.
	    //updateRedoState();
	    //undoAction.updateUndoState();	   
	    markChangedOrUnChanged();
	}
    }


    public void mouseClicked(MouseEvent e) {
	// Intercepts mouse clicks from the JTable showing the base contents.
	// A double click on an entry should open the entry's editor.
	if (e.getClickCount() == 2) {
	    runCommand("edit");
	}
	
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    // Method pertaining to the ClipboardOwner interface.
    public void lostOwnership(Clipboard clipboard, Transferable contents) {}

    //##########################################
    //  usage: 
    //  isDuplicate=checkForDuplicateKeyAndAdd( null, b.getKey() , issueDuplicateWarning);
    //############################################
        // if the newkey already exists and is not the same as oldkey it will give a warning
    // else it will add the newkey to the to set and remove the oldkey
    public boolean checkForDuplicateKeyAndAdd(String oldKey, String newKey, boolean issueWarning){
	Globals.logger(" checkForDuplicateKeyAndAdd [oldKey = " + oldKey + "] [newKey = " + newKey + "]");
	
	boolean duplicate=false;
	if(oldKey==null){// this is a new entry so don't bother removing oldKey
	    duplicate= addKeyToSet( newKey);
	}else{
	    if(oldKey.equals(newKey)){// were OK because the user did not change keys
		duplicate=false;
	    }else{// user changed the key
		
		// removed the oldkey
		// But what if more than two have the same key?
		// this means that user can add another key and would not get a warning!
		// consider this: i add a key xxx, then i add another key xxx . I get a warning. I delete the key xxx. JBM
		// removes this key from the allKey. then I add another key xxx. I don't get a warning!
		// i need a way to count the number of keys of each type
		// hashmap=>int (increment each time)
		
		removeKeyFromSet( oldKey);
		duplicate = addKeyToSet( newKey );
	    }
	}
	if(duplicate==true && issueWarning==true){
	    JOptionPane.showMessageDialog(null,  Globals.lang("Warning there is a duplicate key")+":" + newKey ,
					  Globals.lang("Duplicate Key Warning"),
					  JOptionPane.WARNING_MESSAGE);//, options);
	}
	return duplicate;
    }

    //========================================================
    // keep track of all the keys to warn if there are duplicates
    //========================================================
    private boolean addKeyToSet(String key){
	boolean exists=false;
	if(  key.equals(""))
	    return false;//don't put empty key
	if(allKeys.containsKey(key)){
	    // warning
	    exists=true;
	    allKeys.put( key, new Integer( ((Integer)allKeys.get(key)).intValue() + 1));// incrementInteger( allKeys.get(key)));
	}else
	    allKeys.put( key, new Integer(1));
	return exists;
    }
    //========================================================
    // reduce the number of keys by 1. if this number goes to zero then remove from the set
    // note: there is a good reason why we should not use a hashset but use hashmap instead
    //========================================================
    private void removeKeyFromSet(String key){
	if(key.equals("")) return;
	if(allKeys.containsKey(key)){
	    Integer tI = (Integer)allKeys.get(key); // if(allKeys.get(key) instanceof Integer)
	    if(tI.intValue()==1)
		allKeys.remove( key);
	    else
		allKeys.put( key, new Integer( ((Integer)tI).intValue() - 1));//decrementInteger( tI ));
	}else // ignore, as there is no such key
	    ;
    }

}
