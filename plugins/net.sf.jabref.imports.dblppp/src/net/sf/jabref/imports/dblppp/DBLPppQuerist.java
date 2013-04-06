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
package net.sf.jabref.imports.dblppp;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.BibtexEntryType;
import net.sf.jabref.imports.dblppp.KeyMapper.DBLPPPFIELDS;
import net.sf.jabref.imports.ws.DBLPPlusPlusPortProxy;
import net.sf.jabref.imports.ws.Sequence4;
import net.sf.jabref.imports.ws.Sequence6;

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
public class DBLPppQuerist {
	
	private DBLPPlusPlusPortProxy proxy;

	public DBLPppQuerist() {
		proxy = new DBLPPlusPlusPortProxy();
	}

	/**
	 * @param terms
	 * @param bigInteger3 
	 * @param bigInteger2 
	 * @param bigInteger 
	 * @return
	 * @throws RemoteException 
	 */
	public Sequence6[] keywordQuery(String terms, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) throws RemoteException {
		Sequence6[] results = proxy.all_publications_keywords_year(
				terms, bigInteger, bigInteger2, bigInteger3);
		return results;
	}

	/**
	 * @param dblp_key
	 * @return
	 * @throws RemoteException 
	 */
	public Sequence4[] getAuthors(String dblp_key) throws RemoteException {
		Sequence4[] result = proxy.publication_authors(dblp_key);
		return result;
	}

	/**
	 * @param result
	 * @return
	 */
	public BibtexEntry parseResult(Sequence6 result, String author) {
		BibtexEntry entry = new BibtexEntry();

		BibtexEntryType type = BibtexEntryType.getType(result.getType());
		entry.setType(type);
		
		
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("author", author);
		fields.put(KeyMapper.getCorrespondingField(type, DBLPPPFIELDS.conference), result.getConference());
		fields.put(KeyMapper.getCorrespondingField(type, DBLPPPFIELDS.dblp_key), result.getDblp_key());
		fields.put(KeyMapper.getCorrespondingField(type, DBLPPPFIELDS.doi), result.getDoi());
		fields.put(KeyMapper.getCorrespondingField(type, DBLPPPFIELDS.ee), result.getEe());
		fields.put(KeyMapper.getCorrespondingField(type, DBLPPPFIELDS.isbn), result.getIsbn());
		fields.put(KeyMapper.getCorrespondingField(type, DBLPPPFIELDS.month), result.getMonth());
		fields.put(KeyMapper.getCorrespondingField(type, DBLPPPFIELDS.number), result.getNumber());
		fields.put(KeyMapper.getCorrespondingField(type, DBLPPPFIELDS.pages), result.getIsbn());
		fields.put(KeyMapper.getCorrespondingField(type, DBLPPPFIELDS.publisher), result.getPublisher());
		fields.put(KeyMapper.getCorrespondingField(type, DBLPPPFIELDS.series), result.getSeries());
		fields.put(KeyMapper.getCorrespondingField(type, DBLPPPFIELDS.source), result.getSource());
		fields.put(KeyMapper.getCorrespondingField(type, DBLPPPFIELDS.title), result.getTitle());
		fields.put(KeyMapper.getCorrespondingField(type, DBLPPPFIELDS.volume), result.getVolume());
		fields.put(KeyMapper.getCorrespondingField(type, DBLPPPFIELDS.year), result.getYear());
		
		entry.setField(fields);
		return entry;
		
	}

}
