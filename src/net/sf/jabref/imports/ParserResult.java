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
package net.sf.jabref.imports;

import java.io.File;
import net.sf.jabref.BibtexEntryType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import net.sf.jabref.BibtexDatabase;
import net.sf.jabref.BibtexEntry;
import net.sf.jabref.MetaData;

public class ParserResult
{
    private BibtexDatabase base;
    
    private MetaData metaData;
    
    private HashMap<String, BibtexEntryType> entryTypes;

    private File file = null;
    
    private ArrayList<String> warnings = new ArrayList<String>();
    
    private ArrayList<String> duplicateKeys = new ArrayList<String>();

    private String errorMessage = null;
    
    private String encoding = null; // Which encoding was used?

    private boolean postponedAutosaveFound = false;
    
    private boolean invalid = false;

    private String jabrefVersion = null; // Which JabRef version wrote the file, if any?
    
    private int jabrefMajorVersion = 0;
    
    private int jabrefMinorVersion = 0;
    
    private int jabrefMinor2Version = 0; // Numeric version representation
    
    private boolean toOpenTab = false;

    public ParserResult(Collection<BibtexEntry> entries){
    	this(ImportFormatReader.createDatabase(entries), null, new HashMap<String, BibtexEntryType>());
    }
    
    public ParserResult(BibtexDatabase base, MetaData metaData, HashMap<String, BibtexEntryType> entryTypes) {
		this.base = base;
		this.metaData = metaData;
		this.entryTypes = entryTypes;
    }

    /**
     * Check if this base is marked to be added to the currently open tab. Default is false.
     * @return
     */
    public boolean toOpenTab() {
        return toOpenTab;
    }

    public void setToOpenTab(boolean toOpenTab) {
        this.toOpenTab = toOpenTab;
    }


    /**
     * Find which version of JabRef, if any, produced this bib file.
     * @return The version number string, or null if no JabRef signature could be read.
     */
    public String getJabrefVersion() {
        return jabrefVersion;
    }

    /**
     * Set the JabRef version number string for this parser result.
     * @param jabrefVersion The version number string.                                         
     */
    public void setJabrefVersion(String jabrefVersion) {
        this.jabrefVersion = jabrefVersion;
    }


    public int getJabrefMajorVersion() {
        return jabrefMajorVersion;
    }

    public void setJabrefMajorVersion(int jabrefMajorVersion) {
        this.jabrefMajorVersion = jabrefMajorVersion;
    }

    public int getJabrefMinorVersion() {
        return jabrefMinorVersion;
    }

    public void setJabrefMinorVersion(int jabrefMinorVersion) {
        this.jabrefMinorVersion = jabrefMinorVersion;
    }

    public int getJabrefMinor2Version() {
        return jabrefMinor2Version;
    }

    public void setJabrefMinor2Version(int jabrefMinor2Version) {
        this.jabrefMinor2Version = jabrefMinor2Version;
    }
    
    public BibtexDatabase getDatabase() {
    	return base;
    }

    public MetaData getMetaData() {
	    return metaData;
    }

    public void setMetaData(MetaData md) {
        this.metaData = md;
    }

    public HashMap<String, BibtexEntryType> getEntryTypes() {
    	return entryTypes;
    }

    public File getFile() {
      return file;
    }

    public void setFile(File f) {
      file = f;
    }

    /**
     * Sets the variable indicating which encoding was used during parsing.
     *
     * @param enc String the name of the encoding.
     */
    public void setEncoding(String enc) {
      encoding = enc;
    }

    /**
     * Returns the name of the encoding used during parsing, or null if not specified
     * (indicates that prefs.get("defaultEncoding") was used).
     */
    public String getEncoding() {
      return encoding;
    }

    /**
     * Add a parser warning.
     *
     * @param s String Warning text. Must be pretranslated. Only added if there isn't already a dupe.
     */
    public void addWarning(String s) {
        if (!warnings.contains(s))
            warnings.add(s);
    }

    public boolean hasWarnings() {
      return (warnings.size() > 0);
    }

    public String[] warnings() {
      String[] s = new String[warnings.size()];
      for (int i=0; i<warnings.size(); i++)
        s[i] = warnings.get(i);
      return s;
    }

    /**
     * Add a key to the list of duplicated BibTeX keys found in the database.
     * @param key The duplicated key
     */
    public void addDuplicateKey(String key) {
        if (!duplicateKeys.contains(key))
            duplicateKeys.add(key);
    }

    /**
     * Query whether any duplicated BibTeX keys have been found in the database.
     * @return true if there is at least one duplicate key.
     */
    public boolean hasDuplicateKeys() {
        return duplicateKeys.size() > 0;
    }

    /**
     * Get all duplicated keys found in the database.
     * @return An array containing the duplicated keys.
     */
    public String[] getDuplicateKeys() {
        return duplicateKeys.toArray(new String[duplicateKeys.size()]);
    }
    

    public boolean isPostponedAutosaveFound() {
        return postponedAutosaveFound;
    }

    public void setPostponedAutosaveFound(boolean postponedAutosaveFound) {
        this.postponedAutosaveFound = postponedAutosaveFound;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
