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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigInteger;
import java.rmi.RemoteException;

import net.sf.jabref.BibtexEntry;
import net.sf.jabref.BibtexEntryType;
import net.sf.jabref.imports.ws.Sequence4;
import net.sf.jabref.imports.ws.Sequence6;

import org.junit.Test;

/**
 * 
 * <p>
 * See the <a href="{@docRoot}/LICENSE">License</a>, <a href="{@docRoot}/README">ReadMe</a>.
 * </p>
 * 
 * @author <a href="mailto:toennies@l3s.de">Sascha Toennies</a>
 * @author $Author: toennies $
 * @version $Revision: 2 $ $Date: 2007-11-05 16:13:40 +0100 (Mo, 05 Nov 2007) $
 * 
 */
public class QueristTest {

	@Test
	public void keywordQueryTest() {
		DBLPppQuerist querist = new DBLPppQuerist();
		try {
			Sequence6[] result = querist.keywordQuery("ontology", BigInteger
					.valueOf(2007), BigInteger.valueOf(2007), BigInteger
					.valueOf(10));
			BibtexEntry entry = querist.parseResult(result[0], "Test Author");
			assertNotNull(result);
			assertTrue(result.length <= 10);
		} catch (RemoteException e) {
			fail("should not have thrown Exception" + e.getMessage());
		}
	}

	@Test
	public void authorTest() {
//		BibtexEntryType type = BibtexEntryType.getType("inproceedings");
//		for(String field : type.getRequiredFields()) {
//			System.out.println(field);
//		}

		DBLPppQuerist querist = new DBLPppQuerist();
		Sequence4[] testResult = new Sequence4[4];
		Sequence4 author = new Sequence4();
		author.setAuthor("Alessandro Bozzon");
		author.setDblp_key("conf/icwe/BozzonINT07");
		testResult[0] = author;
		author = new Sequence4();
		author.setAuthor("Sascha Tönnies");
		author.setDblp_key("conf/icwe/BozzonINT07");
		testResult[1] = author;
		author = new Sequence4();
		author.setAuthor("Tereza Iofciu");
		author.setDblp_key("conf/icwe/BozzonINT07");
		testResult[2] = author;
		author = new Sequence4();
		author.setAuthor("Wolfgang Nejdl");
		author.setDblp_key("conf/icwe/BozzonINT07");
		testResult[3] = author;

		try {
			Sequence4[] results = querist.getAuthors("conf/icwe/BozzonINT07");
			assertNotNull(results);

			boolean fail = true;
			for (Sequence4 result : results) {
				fail = true;
				if (result.getAuthor().equals("Alessandro Bozzon")
						|| result.getAuthor().equals("Sascha Tönnies")
						|| result.getAuthor().equals("Tereza Iofciu")
						|| result.getAuthor().equals("Wolfgang Nejdl")) {
					fail = false;
				}
			}
			if (fail) {
				fail("All authors should appear");
			}

		} catch (RemoteException e) {
			fail("should not have thrown Exception" + e.getMessage());
		}

	}

	@Test
	public void nullTest() {
		DBLPppQuerist querist = new DBLPppQuerist();
		try {
			Sequence6[] result = querist.keywordQuery("ontology", null, null,
					null);
			assertNotNull(result);
		} catch (RemoteException e) {
			fail("should not have thrown Exception" + e.getMessage());
		}

	}
}
