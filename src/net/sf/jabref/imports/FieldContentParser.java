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

import net.sf.jabref.BibtexFieldManager;


/**
 * This class provides the reformatting needed when reading BibTeX fields formatted
 * in JabRef style. The reformatting must undo all formatting done by JabRef when
 * writing the same fields.
 */
public class FieldContentParser {

    /**
     * Performs the reformatting of the text within a field. The text can (and probably will!)
     * have braces or quotation marks. 
     * 
     * @param content StringBuffer containing the field to format.
     * @param key contains field name according to field
     * 
     * @return The formatted field content. The StringBuffer will be reused!
     */
	public StringBuilder format(StringBuilder content, String key) {
    	if (key == null || (key != null && ! key.equals(BibtexFieldManager.FILE_FIELD) && ! key.startsWith("jabref"))) {
    		char c;
    		int state = 0; 
    		for (int i = 0; i < content.length(); i++) {
	            c = content.charAt(i);
	            switch (state) {
	            	case 0:
	            		if (Character.isWhitespace(c) || c == '\r') {
	            			content.deleteCharAt(i);
	            			i--;
	            			state = 1;
	            		}
	            		break;
	            	case 1:
	            		if (Character.isWhitespace(c) || c == '\r') {
	            			content.deleteCharAt(i);
	            			i--;
	            		} else {
	            			content.insert(i, ' ');
	            			i++;
	            			state = 0;
	            		}
	            		break;
	            }
    		}
    	}

    	return content;
	}

    /**
     * Performs the reformatting
     * @param content StringBuffer containing the field to format.
     * @return The formatted field content. NOTE: the StringBuffer returned may
     * or may not be the same as the argument given.
     */
    public StringBuilder format(StringBuilder content) { 
    	return format(content, null);
    }
}
