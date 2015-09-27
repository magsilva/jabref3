/*  Copyright (C) 2003-2011 Raik Nagel and JabRef contributors
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

package net.sf.jabref ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handling of bibtex fields.
 */
public class BibtexFieldManager
{
	public static final BibtexFieldManager singleton = new BibtexFieldManager();
	
	public static final String KEY_FIELD = "bibtexkey";

	public static final String MARKED = "__markedentry";

	public static final String OWNER = "owner";

	public static final String TIMESTAMP = "timestamp";

	public static final String ENTRYTYPE = "entrytype";

	public static final String FILE_FIELD = "file";

	public static final String ENTRYTYPE_FLAG = "jabref-entrytype: ";

	public static final String META_FLAG = "jabref-meta: ";
	
	public static final String[] DEFAULT_INSPECTION_FIELDS = new String[] { "author", "title", "year", KEY_FIELD };

	// contains all bibtex-field objects (BibtexField)
	private Map<String, BibtexField> fieldSet;

	// contains all known (and public) bibtex fieldnames
	private String[] PUBLIC_FIELDS = null;

	private BibtexFieldManager() {
		fieldSet = new HashMap<String, BibtexField>();
		BibtexField field = null;

		// FIRST: all standard fields
		// These are the fields that BibTex might want to treat, so these must conform to BibTex rules.
		add(new BibtexField("abstract", true, GUIGlobals.LARGE_W, 400));
		add(new BibtexField("address", true, GUIGlobals.SMALL_W));
		add(new BibtexField("annote", true, GUIGlobals.LARGE_W));
		add(new BibtexField("author", true, GUIGlobals.MEDIUM_W, 280));
		add(new BibtexField("booktitle", true, 175));
		add(new BibtexField("chapter", true, GUIGlobals.SMALL_W));
		add(new BibtexField("crossref", true, GUIGlobals.SMALL_W));
		add(new BibtexField("edition", true, GUIGlobals.SMALL_W));
		add(new BibtexField("editor", true, GUIGlobals.MEDIUM_W, 280));
		add(new BibtexField("howpublished", true, GUIGlobals.MEDIUM_W));
		add(new BibtexField("institution", true, GUIGlobals.MEDIUM_W));
		add(new BibtexField("journal", true, GUIGlobals.SMALL_W));
		add(new BibtexField("keywords", true, GUIGlobals.SMALL_W));
		add(new BibtexField("location", true));
		add(new BibtexField("month", true, GUIGlobals.SMALL_W));
		add(new BibtexField("note", true, GUIGlobals.MEDIUM_W));
		add(new BibtexField("number", true, GUIGlobals.SMALL_W, 60).setNumeric(true));
		add(new BibtexField("organization", true, GUIGlobals.MEDIUM_W));
		add(new BibtexField("pages", true, GUIGlobals.SMALL_W));
		add(new BibtexField("publisher", true, GUIGlobals.MEDIUM_W));
		add(new BibtexField("school", true, GUIGlobals.MEDIUM_W));
		add(new BibtexField("series", true, GUIGlobals.SMALL_W));
		add(new BibtexField("title", true, 400));
		add(new BibtexField("type", true, GUIGlobals.SMALL_W));
		add(new BibtexField("language", true, GUIGlobals.SMALL_W));
		add(new BibtexField("volume", true, GUIGlobals.SMALL_W, 60).setNumeric(true));
		add(new BibtexField("year", true, GUIGlobals.SMALL_W, 60).setNumeric(true));
		
		// some semi-standard fields
		add(new BibtexField("doi", true, GUIGlobals.SMALL_W));
		add(new BibtexField("url", false, GUIGlobals.SMALL_W));
		add(new BibtexField("comment", false, GUIGlobals.MEDIUM_W));

		field = new BibtexField(BibtexFieldManager.FILE_FIELD, false);
		field.setEditorType(GUIGlobals.FILE_LIST_EDITOR);
		add(field);


		// JabRef fields
		add(new BibtexField(KEY_FIELD, true).setPrivate());
		add(new BibtexField(TIMESTAMP, false, GUIGlobals.SMALL_W).setPrivate());
		add(new BibtexField(ENTRYTYPE, false, 75).setPrivate());
		add(new BibtexField(MARKED, false).setPrivate().setWriteable(true).setDisplayable(false));

		// collect all public fields for the PUBLIC_FIELDS array
		List<String> pFields = new ArrayList<String>(fieldSet.size());
		for (BibtexField sField : fieldSet.values()) {
			if (sField.isPublic()) {
				pFields.add(sField.getFieldName());
			}
		}
		PUBLIC_FIELDS = pFields.toArray(new String[pFields.size()]);
		Arrays.sort(PUBLIC_FIELDS);
	}

	/**
	 * Insert a field into the internal list
	 */
	private void add(BibtexField field) {
		String key = field.getFieldName().toLowerCase();
		fieldSet.put(key, field) ;
	}

	public BibtexField getField(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Invalid name", new NullPointerException());
		}
		return fieldSet.get(name.toLowerCase());
	}

	/** 
	 * Returns an string-array with all public field names
	 */
	public String[] getAllFieldNames() {
		return PUBLIC_FIELDS ;
	}

	/**
	 * Returns the public fieldname of the entry at index t
	 */
	public String getFieldName(int t) {
		return PUBLIC_FIELDS[t] ;
	}

	/**
	 * Returns the number of available fields
	 */
	public int numberOfPublicFields() {
		return PUBLIC_FIELDS.length ;
	}
}
