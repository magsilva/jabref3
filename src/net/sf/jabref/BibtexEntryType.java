/*
Copyright (C) 2003 David Weitzman, Morten O. Alver

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

Note:
Modified for use in JabRef.
 */

package net.sf.jabref;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Locale;

public abstract class BibtexEntryType implements Comparable<BibtexEntryType>
{
	public static final BibtexEntryType ARTICLE = new BibtexEntryType() {
		public String getName() {
			return "Article";
		}

		public String[] getRequiredFields() {
			return new String[] { "author", "title", "crossref", "volume", "number", "month", "year", "pages"};
		}

		
		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "doi", "url"};
		}
	};

	public static final BibtexEntryType BOOK = new BibtexEntryType() {
		public String getName() {
			return "Book";
		}

		public String[] getRequiredFields() {
			return new String[] { "author", "booktitle/title", "publisher", "edition", "year", "pages", "address", "isbn" };
		}

		
		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "volume", "series", "doi", "url" };
		}
	};

	public static final BibtexEntryType INBOOK = new BibtexEntryType() {
		public String getName() {
			return "InBook";
		}

		public String[] getRequiredFields() {
			return new String[] { "author", "crossref", "chapter", "pages", "doi"};
		}

		
		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "doi", "url"};
		}
	};

	public static final BibtexEntryType INCOLLECTION = new BibtexEntryType() {
		public String getName() {
			return "InCollection";
		}

		public String[] getRequiredFields() {
			return new String[] { "author", "title", "crossref", "chapter", "pages", "doi"};
		}

		
		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "doi", "url"};
		}
	};

	public static final BibtexEntryType INPROCEEDINGS = new BibtexEntryType() {
		public String getName() {
			return "InProceedings";
		}

		public String[] getRequiredFields() {
			return new String[] { "author", "title", "crossref", "pages" };
		}
		
		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "doi", "url" };
		}
	};

	public static final BibtexEntryType JOURNAL = new BibtexEntryType() {
		public String getName() {
			return "Journal";
		}
		
		public String[] getRequiredFields() {
			return new String[] { "journal", "publisher", "address", "issn", "e-issn" };
		}

		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "edition", "url" };
		}
	};

	public static final BibtexEntryType MANUAL = new BibtexEntryType() {
		public String getName() {
			return "Manual";
		}

		public String[] getRequiredFields() {
			return new String[] { "author", "booktitle/title", "organization", "year", "pages"};
		}
		
		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "edition", "address", "isbn", "url" };
		}
	};

	public static final BibtexEntryType MASTERSTHESIS = new BibtexEntryType() {
		public String getName() {
			return "MastersThesis";
		}

		public String[] getRequiredFields() {
			return new String[] { "author", "title", "school", "month", "year", "advisor", "pages", "address" };
		}
		
		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "url" };
		}
	};

	public static final BibtexEntryType MISC = new BibtexEntryType() {
		public String getName() {
			return "Misc";
		}

		public String[] getRequiredFields() {
			return new String[] { "author", "title", "howpublished", "month", "year", "note" };
		}

		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "url" };
		}
	};

	public static final BibtexEntryType MONOGRAPH = new BibtexEntryType() {
		public String getName() {
			return "Monograph";
		}

		public String[] getRequiredFields() {
			return new String[] { "author", "title", "school", "month", "year", "advisor", "pages", "address" };
		}

		
		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "url" };
		}
	};

	public static final BibtexEntryType PATENT = new BibtexEntryType() {
		public String getName() {
			return "Patent";
		}

		public String[] getRequiredFields() {
			return new String[] { "author", "title", "number", "month", "year", "yearfiled", "nationality/address" };
		}
		
		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "url" };
		}
	};

	public static final BibtexEntryType PHDTHESIS = new BibtexEntryType() {
		public String getName() {
			return "PhdThesis";
		}

		public String[] getRequiredFields() {
			return new String[] { "author", "title", "school", "month", "year", "advisor", "pages", "address" };
		}
		
		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "url" };
		}
	};


	public static final BibtexEntryType PROCEEDINGS = new BibtexEntryType() {
		public String getName() {
			return "Proceedings";
		}

		public String[] getRequiredFields() {
			return new String[] { "booktitle/title", "days", "month", "year", "location", "isbn", "e-isbn" };
		}

		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "editor", "series", "volume", "address", "publisher", "organization", "issn", "url" };
		}
	};

	public static final BibtexEntryType PROJECT = new BibtexEntryType() {
		public String getName() {
			return "Project";
		}

		public String[] getRequiredFields() {
			return new String[] { "author", "title", "school", "funding", "number", "month", "year", "duration"};
		}

		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "url" };
		}
	};

	public static final BibtexEntryType SOFTWARE = new BibtexEntryType() {
		public String getName() {
			return "Software";
		}

		public String[] getRequiredFields() {
			return new String[] {"author", "organization", "title", "month", "year"};
		}
		
		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "license", "url" };
		}
	};
	
	public static final BibtexEntryType STANDARD = new BibtexEntryType() {
		public String getName() {
			return "Standard";
		}

		public String[] getRequiredFields() {
			return new String[] { "number", "title", "organization", "author", "year", "address", "pages"};
		}
		
		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "url" };
		}
	};

	public static final BibtexEntryType TECHREPORT = new BibtexEntryType() {
		public String getName() {
			return "TechReport";
		}

		public String[] getRequiredFields() {
			return new String[] { "author", "number", "title", "institution", "year", "pages", "address" };
		}

		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "url" };
		}

	};

	public static final BibtexEntryType UNPUBLISHED = new BibtexEntryType() {
		public String getName() {
			return "Unpublished";
		}

		public String[] getRequiredFields() {
			return new String[] { "author", "title", "month", "year", "note"};
		}

		public String[] getOptionalFields() {
			return new String[] { "abstract", "keywords", "url" };
		}
	};

	public int compareTo(BibtexEntryType o) {
		return getName().toLowerCase().compareTo(o.getName().toLowerCase());
	}
	
	public boolean equals(Object o) {
		if (o instanceof BibtexEntryType) {
			BibtexEntryType other = (BibtexEntryType) o;
			return getName().toLowerCase().equals(other.getName().toLowerCase());
		}
		return false;
	}

	public boolean hasAllRequiredFields(BibtexEntry entry, BibtexDatabase database)
	{
		return entry.allFieldsPresent(getRequiredFields(), database);
	}

	public boolean isRequired(String field) {
		for (String requiredField : getRequiredFields()) {
			if (requiredField.equals(field))
				return true;
		}
		return false;
	}

	public boolean isOptional(String field) {
		for (String requiredField : getOptionalFields()) {
			if (requiredField.equals(field))
				return true;
		}
		return false;
	}

	public abstract String getName();

	public abstract String[] getRequiredFields();

	public abstract String[] getOptionalFields();

	
	public static final String COMMENT_TYPE_NAME = "comment";
	
	public static Map<String, BibtexEntryType> ALL_TYPES = new TreeMap<String, BibtexEntryType>();
	
	public static Map<String, BibtexEntryType> STANDARD_TYPES = new TreeMap<String, BibtexEntryType>();
	
	static {
			// Put the standard entry types into the type map.
			STANDARD_TYPES.put("article", ARTICLE);
			STANDARD_TYPES.put("book", BOOK);
			STANDARD_TYPES.put("inbook", INBOOK);
			STANDARD_TYPES.put("incollection", INCOLLECTION);
			STANDARD_TYPES.put("inproceedings", INPROCEEDINGS);
			STANDARD_TYPES.put("journal", JOURNAL);
			STANDARD_TYPES.put("manual", MANUAL);
			STANDARD_TYPES.put("mastersthesis", MASTERSTHESIS);
			STANDARD_TYPES.put("misc", MISC);
			STANDARD_TYPES.put("monograph", MONOGRAPH);
			STANDARD_TYPES.put("patent", PATENT);
			STANDARD_TYPES.put("phdthesis", PHDTHESIS);
			STANDARD_TYPES.put("proceedings", PROCEEDINGS);
			STANDARD_TYPES.put("project", PROJECT);
			STANDARD_TYPES.put("software", SOFTWARE);
			STANDARD_TYPES.put("standard", STANDARD);
			STANDARD_TYPES.put("techreport", TECHREPORT);
			STANDARD_TYPES.put("unpublished", UNPUBLISHED);

			// We need a record of the standard types, in case the user wants
			// to remove a customized version. Therefore we clone the map.
			ALL_TYPES = new TreeMap<String, BibtexEntryType>(STANDARD_TYPES);
	}

	/**
	 * This method returns the BibtexEntryType for the name of a type, or null
	 * if it does not exist.
	 */
	public static BibtexEntryType getType(String name) {
		BibtexEntryType type = ALL_TYPES.get(name.toLowerCase(Locale.US));
		return type;
	}


	/**
	 * Removes a customized entry type from the type map. If this type override
	 * a standard type, we reinstate the standard one.
	 *
	 * @param name The customized entry type to remove.
	 */
	public static void removeType(String name) {
		String nm = name.toLowerCase();
		ALL_TYPES.remove(nm);
		if (STANDARD_TYPES.get(nm) != null) {
			ALL_TYPES.put(nm, STANDARD_TYPES.get(nm));
		}
	}

	/**
	 * Load all custom entry types from preferences. This method is called from
	 * JabRef when the program starts.
	 */
	public static void loadCustomEntryTypes(JabRefPreferences prefs) {
		int number = 0;
		CustomEntryType type;
		while ((type = prefs.getCustomEntryType(number)) != null) {
			ALL_TYPES.put(type.getName().toLowerCase(), type);
			number++;
		}
	}

	/**
	 * Iterate through all entry types, and store those that are custom defined
	 * to preferences. This method is called from JabRefFrame when the program
	 * closes.
	 */
	public static void saveCustomEntryTypes(JabRefPreferences prefs) {
		Iterator<String> i = ALL_TYPES.keySet().iterator();
		int number = 0;
		while (i.hasNext()) {
			Object o = ALL_TYPES.get(i.next());
			if (o instanceof CustomEntryType) {
				// Store this entry type.
				prefs.storeCustomEntryType((CustomEntryType) o, number);
				number++;
			}
		}
		// Then, if there are more 'old' custom types defined, remove these
		// from preferences. This is necessary if the number of custom types
		// has decreased.
		prefs.purgeCustomEntryTypes(number);
	}
}
