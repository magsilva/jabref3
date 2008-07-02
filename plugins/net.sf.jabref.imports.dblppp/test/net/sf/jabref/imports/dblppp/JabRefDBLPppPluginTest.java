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

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import net.sf.jabref.plugin.PluginCore;
import net.sf.jabref.plugin.core.JabRefPlugin;
import net.sf.jabref.plugin.core.generated._JabRefPlugin.EntryFetcherExtension;

import org.java.plugin.JpfException;
import org.java.plugin.PluginManager;
import org.java.plugin.standard.StandardPluginLocation;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * <p>See the
 * <a href="{@docRoot}/LICENSE">License</a>,
 * <a href="{@docRoot}/README">ReadMe</a>.</p>
 *
 * @author <a href="mailto:toennies@l3s.de">Sascha Toennies</a>
 * @author $Author: toennies $
 * @version $Revision: 1 $ $Date: 2007-11-05 14:45:01 +0100 (Mo, 05 Nov 2007) $
 *
 */
public class JabRefDBLPppPluginTest {

	@Test
	public void testGetInstance() throws MalformedURLException, JpfException {
		  
	    PluginManager manager = PluginCore.getManager();
	    
	    StandardPluginLocation dot = new StandardPluginLocation(new File("."), "plugin.xml");
	    
	    manager.publishPlugins(new StandardPluginLocation[]{dot});
	   
	    JabRefPlugin plugin = JabRefPlugin.getInstance(manager);
	    
	    
	    List<EntryFetcherExtension> importers = plugin.getEntryFetcherExtensions();
		assertNotNull(importers);
		
		for (EntryFetcherExtension ext : importers){
			if (ext.getName().equals("DBLP++ Entry Fetcher"))
				return;
		}
		Assert.fail("Did not find DBLPFetcher");
	}
}
