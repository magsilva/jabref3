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

import net.sf.jabref.imports.dblppp.generated._JabRefDBLPppPlugin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.java.plugin.PluginLifecycleException;
import org.java.plugin.PluginManager;

/**
 * 
 * <p>
 * See the <a href="{@docRoot}/LICENSE">License</a>, <a href="{@docRoot}/README">ReadMe</a>.
 * </p>
 * 
 * @author <a href="mailto:toennies@l3s.de">Sascha Toennies</a>
 * @author $Author: toennies $
 * @version $Revision: 1 $ $Date: 2007-11-05 14:45:01 +0100 (Mo, 05 Nov 2007) $
 * 
 */
public class JabRefDBLPppPlugin extends _JabRefDBLPppPlugin {

	private static final Log LOG = LogFactory.getLog(JabRefDBLPppPlugin.class);

	  /**
	   * Retrieve the Plug-in instance from the given manager.
	   * 
	   * @param manager
	   *            The manager from which to retrieve the plug-in instance
	   * 
	   * @return The requested plug-in or null if not found.
	   */
	  public static JabRefDBLPppPlugin getInstance(PluginManager manager) {
	    String id = getId();
	    try{
	      return (JabRefDBLPppPlugin) manager.getPlugin(id);
	    }catch (PluginLifecycleException e){
	      LOG.error("Error while trying to retrieve plugin " + id, e);
	    }catch (IllegalArgumentException e){
	      LOG.error("Could not find plugin " + id, e);
	    }
	    return null;
	  }
	
}
