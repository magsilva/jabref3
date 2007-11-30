/***************************************************************
*  Copyright notice
*  
*  (c) 2007 Toennies
*  All rights reserved
*
*  This script is part of the net.sf.jabref.imports.dblppp project. The net.sf.jabref.imports.dblppp project is 
*  free software; you can redistribute it and/or modify
*  it under the terms of the GNU General Public License as published by
*  the Free Software Foundation; either version 2 of the License, or
*  (at your option) any later version.
* 
*  The GNU General Public License can be found at
*  http://www.gnu.org/copyleft/gpl.html.
* 
*  This script is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*
*  This copyright notice MUST APPEAR in all copies of the script!
***************************************************************/
/**
 * 
 */
package net.sf.jabref.imports.dblppp;

import net.sf.jabref.BibtexEntryType;

/**
 * 
 * <p>See the
 * <a href="{@docRoot}/LICENSE">License</a>,
 * <a href="{@docRoot}/README">ReadMe</a>.</p>
 *
 * @author <a href="mailto:toennies@l3s.de">Sascha Toennies</a>
 * @author $Author: toennies $
 * @version $Revision: 2 $ $Date: 2007-11-05 16:13:40 +0100 (Mo, 05 Nov 2007) $
 *
 */
public final class KeyMapper {
	
	public static enum DBLPPPFIELDS {number, month, year, 
		title, dblp_key, conference, ee, source, series, volume,
		pages, publisher, isbn, doi}
	
	public static String getCorrespondingField(BibtexEntryType type, DBLPPPFIELDS fieldName) {

		switch(fieldName) {
		case number:
			return "number";
		case month:
			return null;
		case year:
			return "year";
		case title:
			return "title";
		case dblp_key:
			return "bibtexkey";
		case conference:
			return "conference";
		case ee:
			return "eeid";
		case source:
			if(type.getName().toLowerCase().equals("article")) {
				return "journal";
			} else if (type.getName().toLowerCase().equals("inproceedings")) {
				return "booktitle";
			} else {
				return "source";
			}
		case series:
			return "series";
		case volume:
			return "volume";
		case pages:
			return "pages";
		case publisher:
			if(type.getName().toLowerCase().equals("mastersthesis")) {
				return "school";
			} else {
				return "publisher";
			}
		case isbn:
			return "isbn";
		case doi:
			return "doi";
		}
		
		return null;
	}
	

}
