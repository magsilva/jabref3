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

package net.sf.jabref;

import java.io.*;
import javax.swing.*;

import net.sf.jabref.labelPattern.DefaultLabelPatterns;
import net.sf.jabref.labelPattern.LabelPattern;

import java.awt.Point;
import java.awt.Dimension;
import java.util.prefs.*;
import java.util.*;

public class JabRefPreferences {

    final String
	CUSTOM_TYPE_NAME = "customTypeName_",
	CUSTOM_TYPE_REQ = "customTypeReq_",
	CUSTOM_TYPE_OPT = "customTypeOpt_";

    Preferences prefs;
    HashMap defaults = new HashMap(),
	keyBinds = new HashMap(),
	defKeyBinds = new HashMap();
	private static final LabelPattern KEY_PATTERN = new DefaultLabelPatterns();
	private static LabelPattern keyPattern = new LabelPattern(KEY_PATTERN);

    public JabRefPreferences() {

	prefs = Preferences.userNodeForPackage(JabRef.class);
	//Util.pr(prefs.toString());

	defaults.put("pdfviewer","/usr/bin/acroread");
	defaults.put("psviewer","/usr/X11R6/bin/gv");
	defaults.put("htmlviewer","/usr/bin/mozilla");
	defaults.put("lyxpipe", System.getProperty("user.home")+File.separator+".lyx/lyxpipe");
	defaults.put("posX", new Integer(0));
	defaults.put("posY", new Integer(0));
	defaults.put("sizeX", new Integer(840));
	defaults.put("sizeY", new Integer(680));
	defaults.put("autoResizeMode", new Integer(JTable.AUTO_RESIZE_OFF));
	defaults.put("tableColorCodesOn", new Boolean(true));
        defaults.put("language", "en");
	defaults.put("priSort", "author");
	defaults.put("priDescending", new Boolean(false));
	defaults.put("secSort", "year");
	defaults.put("secDescending", new Boolean(true));
	defaults.put("terSort", "author");
	defaults.put("terDescending", new Boolean(false));
	defaults.put("columnNames",
		     "author;title;year;journal;bibtexkey");
	defaults.put("workingDirectory", (String)null);

	defaults.put("autoOpenForm", new Boolean(true));
	defaults.put("entryTypeFormHeightFactor", new Integer(1));
	defaults.put("entryTypeFormWidth", new Integer(1));
	defaults.put("backup", new Boolean(true));
	defaults.put("openLastEdited", new Boolean(false));
	defaults.put("lastEdited", (String)null);
	defaults.put("stringsPosX", new Integer(0));
	defaults.put("stringsPosY", new Integer(0));
	defaults.put("stringsSizeX", new Integer(600));
	defaults.put("stringsSizeY", new Integer(400));
	defaults.put("defaultShowSource", new Boolean(false));
	defaults.put("enableSourceEditing", new Boolean(true));
	defaults.put("caseSensitiveSearch", new Boolean(false));
	defaults.put("searchReq", new Boolean(true));
	defaults.put("searchOpt", new Boolean(true));
	defaults.put("searchGen", new Boolean(true));
	defaults.put("searchAll", new Boolean(false));
	defaults.put("incrementS", new Boolean(true));
	defaults.put("selectS", new Boolean(false));
	defaults.put("regExpSearch", new Boolean(true));
	defaults.put("searchPanePosX", new Integer(0));
	defaults.put("searchPanePosY", new Integer(0));
	defaults.put("autoComplete", new Boolean(true));
	defaults.put("autoCompFields", new byte[] {0, 1, 28});
	defaults.put("groupSelectorVisible", new Boolean(true));
	defaults.put("groupsDefaultField", "keywords");
        defaults.put("generalFields", "crossref;keywords;doi;url;"+
	     "pdf;abstract;comment");

	//defaults.put("recentFiles", "/home/alver/Documents/bibk_dok/hovedbase.bib");
	defaults.put("historySize", new Integer(5));
        defaults.put("fontFamily", "Times");
        defaults.put("fontStyle", new Integer(java.awt.Font.PLAIN));
        defaults.put("fontSize", new Integer(12));
	//defaults.put("keyPattern", new LabelPattern(KEY_PATTERN));

	restoreKeyBindings();

	//defaults.put("oooWarning", new Boolean(true));

    }

    public String get(String key) {
	return prefs.get(key, (String)defaults.get(key));
    }

    public boolean getBoolean(String key) {
	return prefs.getBoolean(key, ((Boolean)defaults.get(key)).booleanValue());
    }

    public double getDouble(String key) {
	return prefs.getDouble(key, ((Double)defaults.get(key)).doubleValue());
    }

    public int getInt(String key) {
	return prefs.getInt(key, ((Integer)defaults.get(key)).intValue());
    }

    public byte[] getByteArray(String key) {
	return prefs.getByteArray(key, (byte[])defaults.get(key));
    }

    public void put(String key, String value) {
	prefs.put(key, value);
    }

    public void putBoolean(String key, boolean value) {
	prefs.putBoolean(key, value);
    }

    public void putDouble(String key, double value) {
	prefs.putDouble(key, value);
    }

    public void putInt(String key, int value) {
	prefs.putInt(key, value);
    }

    public void putByteArray(String key, byte[] value) {
	prefs.putByteArray(key, value);
    }

    public void remove(String key) {
	prefs.remove(key);
    }

    /**
     * Puts a string array into the Preferences, by linking its elements
     * with ';' into a single string. Escape characters make the process
     * transparent even if strings contain ';'.
     */
    public void putStringArray(String key, String[] value) {
	if (value == null) {
	    remove(key);
	    return;
	}

	if (value.length > 0) {
	    StringBuffer linked = new StringBuffer();
	    for (int i=0; i<value.length-1; i++) {
		linked.append(makeEscape(value[i]));
		linked.append(";");
	    }
	    linked.append(makeEscape(value[value.length-1]));
	    put(key, linked.toString());
	} else {
	    put(key, "");
	}
    }

    /**
     * Returns a String[] containing the chosen columns.
     */
    public String[] getStringArray(String key) {
	String names = get(key);
	if (names == null)
	    return null;
	//Util.pr(key+"\n"+names);
	StringReader rd = new StringReader(names);
	Vector arr = new Vector();
	String rs;
	try {
	    while ((rs = getNextUnit(rd)) != null) {
		arr.add(rs);
	    }
	} catch (IOException ex) {}
	String[] res = new String[arr.size()];
	for (int i=0; i<res.length; i++)
	    res[i] = (String)arr.elementAt(i);

	return res;
    }

    /**
     * Returns the KeyStroke for this binding, as defined by the
     * defaults, or in the Preferences.
     */
    public KeyStroke getKey(String bindName) {
	//Util.pr(bindName+" "+(String)keyBinds.get(bindName));
	String s = (String)keyBinds.get(bindName);

	// If the current key bindings don't contain the one asked for,
	// we fall back on the default. This should only happen when a
	// user has his own set in Preferences, and has upgraded to a
	// new version where new bindings have been introduced.
	if (s == null) {
	    s = (String)defKeyBinds.get(bindName);
	    // So, if this happens, we add the default value to the current
	    // hashmap, so this doesn't happen again, and so this binding
	    // will appear in the KeyBindingsDialog.
	    keyBinds.put(bindName, s);
	}
	return KeyStroke.getKeyStroke(s);
    }

    /**
     * Returns the HashMap containing all key bindings.
     */
    public HashMap getKeyBindings() {
	return keyBinds;
    }

    /**
     * Stores new key bindings into Preferences, provided they
     * actually differ from the old ones.
     */
    public void setNewKeyBindings(HashMap newBindings) {
	if (!newBindings.equals(keyBinds)) {
	    // This confirms that the bindings have actually changed.
	    String[] bindNames = new String[newBindings.size()],
		bindings = new String[newBindings.size()];
	    int index = 0;
	    for (Iterator i=newBindings.keySet().iterator();
		 i.hasNext();) {
		String nm = (String)i.next();
		String bnd = (String)newBindings.get(nm);
		bindNames[index] = nm;
		bindings[index] = bnd;
		index++;
	    }
	    putStringArray("bindNames", bindNames);
	    putStringArray("bindings", bindings);
	}
    }

	public LabelPattern getKeyPattern(){
		return keyPattern;
	}

	public void putKeyPattern(LabelPattern pattern){
		keyPattern = pattern;
	}

    private void restoreKeyBindings() {
	// Define default keybindings.
	defineDefaultKeyBindings();

	// First read the bindings, and their names.
	String[] bindNames = getStringArray("bindNames"),
	    bindings = getStringArray("bindings");

	// Then set up the key bindings HashMap.
	if ((bindNames == null) || (bindings == null)
	    || (bindNames.length != bindings.length)) {
	    // Nothing defined in Preferences, or something is wrong.
	    setDefaultKeyBindings();
	    return;
	}

	for (int i=0; i<bindNames.length; i++)
	    keyBinds.put(bindNames[i], bindings[i]);
    }

    private void setDefaultKeyBindings() {
	keyBinds = defKeyBinds;
    }

    private void defineDefaultKeyBindings() {
	defKeyBinds.put("Push To LyX","ctrl L");
	defKeyBinds.put("Quit JabRef", "ctrl Q");
	defKeyBinds.put("Open", "ctrl O");
	defKeyBinds.put("Save", "ctrl S");
	defKeyBinds.put("Close database", "ctrl W");
	defKeyBinds.put("New entry", "ctrl N");
	defKeyBinds.put("Cut", "ctrl X");
	defKeyBinds.put("Copy", "ctrl C");
	defKeyBinds.put("Paste", "ctrl V");
	defKeyBinds.put("Undo", "ctrl Z");
	defKeyBinds.put("Redo", "ctrl Y");
	defKeyBinds.put("Help", "F1");
	defKeyBinds.put("New article", "ctrl shift A");
	defKeyBinds.put("New book", "ctrl shift B");
	defKeyBinds.put("New phdthesis", "ctrl shift T");
	defKeyBinds.put("New inbook", "ctrl shift I");
	defKeyBinds.put("New mastersthesis", "ctrl shift M");
	defKeyBinds.put("New proceedings", "ctrl shift P");
	defKeyBinds.put("New unpublished", "ctrl shift U");
	defKeyBinds.put("Edit strings", "ctrl shift S");
	defKeyBinds.put("Edit preamble", "ctrl P");
	defKeyBinds.put("Select all", "ctrl A");
	defKeyBinds.put("Toggle groups", "ctrl shift G");
	defKeyBinds.put("Autgenerate BibTeX keys", "ctrl G");
	defKeyBinds.put("Search", "ctrl F");
	defKeyBinds.put("Incremental search", "ctrl shift F");
	defKeyBinds.put("Repeat incremental search", "ctrl shift F");
	defKeyBinds.put("Close entry editor", "ESCAPE");
	defKeyBinds.put("Clear search", "ESCAPE");
	defKeyBinds.put("Entry editor: next panel", "ctrl shift RIGHT");
	defKeyBinds.put("Entry editor: previous panel", "ctrl shift LEFT");
	defKeyBinds.put("Entry editor: next entry", "ctrl shift DOWN");
	defKeyBinds.put("Entry editor: previous entry", "ctrl shift UP");
	defKeyBinds.put("Entry editor: store field", "alt S");
	defKeyBinds.put("Save session", "F11");
	defKeyBinds.put("Load session", "F12");
    }

    private String getNextUnit(Reader data) throws IOException {
	int c;
	boolean escape = false, done = false;
	StringBuffer res = new StringBuffer();
	while (!done && ((c = data.read()) != -1)) {
	    if (c == '\\') {
		if (!escape)
		    escape = true;
		else {
		    escape = false;
		    res.append('\\');
		}
	    } else {
		if (c == ';') {
		    if (!escape)
			done = true;
		    else
			res.append(';');
		} else {
		    res.append((char)c);
		}
		escape = false;
	    }
	}
	if (res.length() > 0)
	    return res.toString();
	else
	    return null;
    }

    private String makeEscape(String s) {
        StringBuffer sb = new StringBuffer();
	int c;
	for (int i=0; i<s.length(); i++) {
	    c = s.charAt(i);
	    if ((c == '\\') || (c == ';'))
		sb.append('\\');
	    sb.append((char)c);
	}
	return sb.toString();
    }

    /**
     * Stores all information about the entry type in preferences, with
     * the tag given by number.
     */
    public void storeCustomEntryType(CustomEntryType tp, int number) {
	String nr = ""+number;
	put(CUSTOM_TYPE_NAME+nr, tp.getName());
	putStringArray(CUSTOM_TYPE_REQ+nr, tp.getRequiredFields());
	putStringArray(CUSTOM_TYPE_OPT+nr, tp.getOptionalFields());

    }

    /**
     * Retrieves all information about the entry type in preferences,
     * with the tag given by number.
     */
    public CustomEntryType getCustomEntryType(int number) {
	String nr = ""+number;
	String
	    name = get(CUSTOM_TYPE_NAME+nr);
	String[]
	    req = getStringArray(CUSTOM_TYPE_REQ+nr),
	    opt = getStringArray(CUSTOM_TYPE_OPT+nr);
	if (name == null)
	    return null;
	else return new CustomEntryType
	    (Util.nCase(name), req, opt);


    }

    /**
     * Removes all information about custom entry types with tags of
     * @param number or higher.
     */
    public void purgeCustomEntryTypes(int number) {
	while (get(CUSTOM_TYPE_NAME+number) != null) {
	    remove(CUSTOM_TYPE_NAME+number);
	    remove(CUSTOM_TYPE_REQ+number);
	    remove(CUSTOM_TYPE_OPT+number);
	    number++;
	}
    }
}
