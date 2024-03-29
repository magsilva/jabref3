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

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.jabref.export.layout.LayoutFormatter;

/**
 * Created by IntelliJ IDEA.
 * User: alver
 * Date: Mar 26, 2006
 * Time: 8:05:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class HTMLConverter implements LayoutFormatter {
	private HashMap<String, String> escapedSymbols = new HashMap<String, String>();
	
	public HTMLConverter() {
		super();
		escapedSymbols.put("&ldquo;", "``");
		escapedSymbols.put("&rdquo;", "''");
		escapedSymbols.put("&lsquo;", "``");
		escapedSymbols.put("&rsquo;", "''");
		escapedSymbols.put("&nbsp;", " ");
		escapedSymbols.put("&quot;", "\"");
		escapedSymbols.put("&amp;", "&");
		escapedSymbols.put("&lt;", "<");
		escapedSymbols.put("&gt;", ">");
	}
    public String format(String text) {
        if (text == null)
            return null;
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<text.length(); i++) {

            int c = text.charAt(i);

            if (c == '<') {
                i = readTag(text, sb, i);
            } else
                sb.append((char)c);

        }
        text = sb.toString();
        Set<String> patterns = escapedSymbols.keySet();
        for (String pattern: patterns) {
        	text = text.replaceAll(pattern, escapedSymbols.get(pattern));
        }
        
        Pattern escapedPattern = Pattern.compile("&#([x]*\\p{XDigit}+);");
        Matcher m = escapedPattern.matcher(text);
        while (m.find()) {
        	int num = Integer.decode(m.group(1).replace("x", "#"));
        	switch (num) {
        	case 37:
        		text = text.replaceAll("&#" + m.group(1) + ";", "%");
        		break;
        	case 38:
        		text = text.replaceAll("&#" + m.group(1) + ";", "&");
        		break;
        	case 916:
        		text = text.replaceAll("&#" + m.group(1) + ";", "\\$\\\\delta\\$");
        		break;
		case 956: case 181:
        		text = text.replaceAll("&#" + m.group(1) + ";", "\\$\\\\mu\\$");
        		break;
        	case 8208:
        		text = text.replaceAll("&#" + m.group(1) + ";", "-");
        		break;
        	case 8211:
        		text = text.replaceAll("&#" + m.group(1) + ";", "--");
        		break;
        	case 8212:
        		text = text.replaceAll("&#" + m.group(1) + ";", "---");
        		break;
        	case 8217:
        		text = text.replaceAll("&#" + m.group(1) + ";", "'");
        		break;
        	case 8730:
        		text = text.replaceAll("&#" + m.group(1) + ";", "\\$\\\\sqrt{}\\$");
        		break;
        	default:
        		System.err.println("HTML escaped char not converted " + m.group(1) + ": " + Integer.toString(num));
        	}
        }

	// Also match special characters with alphabetic codes
        escapedPattern = Pattern.compile("&(\\w+);");
        m = escapedPattern.matcher(text);
        while (m.find()) {
	  String match = m.group(1).toLowerCase();
	  if (match.equals("mu"))
	    text = text.replaceAll("&" + m.group(1) + ";", "\\$\\\\mu\\$");
	  else if (match.equals("radic"))
	    text = text.replaceAll("&" + m.group(1) + ";", "\\$\\\\sqrt{}\\$");
	  else
	    System.err.println("HTML escaped char not converted " + m.group(1));
	}

        return text.trim();
    }

    private final int MAX_TAG_LENGTH = 30;
    /*private final int MAX_CHAR_LENGTH = 10;

    private int readHtmlChar(String text, StringBuffer sb, int position) {
        // Have just read the < character that starts the tag.
        int index = text.indexOf(';', position);
        if ((index > position) && (index-position < MAX_CHAR_LENGTH)) {
        	//String code = text.substring(position, index);
            //System.out.println("Removed code: "+text.substring(position, index));
            return index; // Just skip the tag.
        } else return position; // Don't do anything.
    }*/

    private int readTag(String text, StringBuffer sb, int position) {
        // Have just read the < character that starts the tag.
        int index = text.indexOf('>', position);
        if ((index > position) && (index-position < MAX_TAG_LENGTH)) {
            //System.out.println("Removed tag: "+text.substring(position, index));
            return index; // Just skip the tag.
        } else return position; // Don't do anything.
    }
}
