package net.sf.jabref.export.xml;

import net.sf.jabref.export.xml.generated._JabRefXmlPlugin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.java.plugin.PluginLifecycleException;
import org.java.plugin.PluginManager;

/**
 * 
 */
public class JabRefXmlPlugin extends _JabRefXmlPlugin {

  private static final Log LOG = LogFactory.getLog(JabRefXmlPlugin.class);

  public void doStart() {
    LOG.debug("starting plugin");
  }

  public void doStop() {
    LOG.debug("stopping plugin");
  }

  /**
   * Retrieve the Plug-in instance from the given manager.
   * 
   * @param manager
   *            The manager from which to retrieve the plug-in instance
   * 
   * @return The requested plug-in or null if not found.
   */
  public static JabRefXmlPlugin getInstance(PluginManager manager) {
    String id = getId();
    try{
      return (JabRefXmlPlugin) manager.getPlugin(id);
    }catch (PluginLifecycleException e){
      LOG.error("Error while trying to retrieve plugin " + id, e);
    }catch (IllegalArgumentException e){
      LOG.error("Could not find plugin " + id, e);
    }
    return null;
  }

}
