package net.sf.jabref.export.xml.generated;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import net.sf.jabref.plugin.util.RuntimeExtension;
import org.java.plugin.Plugin;
import org.java.plugin.PluginLifecycleException;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.ExtensionPoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Do not modify this file, as it was auto generated and will be overwritten!
 * User modifications should go in net.sf.jabref.export.xml.JabRefXmlPlugin.
 */
public abstract class _JabRefXmlPlugin extends Plugin {

    public static String getId(){
        return "net.sf.jabref.export.xml";
    }

	static Log log = LogFactory.getLog(_JabRefXmlPlugin.class);

	public List<XmlBasedFormatExtension> getXmlBasedFormatExtensions(){
        ExtensionPoint extPoint = getManager().getRegistry().getExtensionPoint(getId(), "XmlBasedFormat");
        List<XmlBasedFormatExtension> result = new ArrayList<XmlBasedFormatExtension>();
        for (Extension ext : extPoint.getConnectedExtensions()) {
			try {
				result.add(new XmlBasedFormatExtension(getManager().getPlugin(
						ext.getDeclaringPluginDescriptor().getId()), ext));
			} catch (PluginLifecycleException e) {
				log.error("Failed to activate plug-in " + ext.getDeclaringPluginDescriptor().getId(), e);
			}
		}
        return result;
    }

    public static class XmlBasedFormatExtension extends RuntimeExtension {
        public XmlBasedFormatExtension(Plugin declaringPlugin, Extension wrapped){
            super(declaringPlugin, wrapped);
        }
                
	     
              public String getDisplayName(){
            return getStringParameter("displayName");
        }
  
  	     
              public String getConsoleName(){
            return getStringParameter("consoleName");
        }
  
  	     
      		public URL getXslAsUrl(){
		    return getResourceParameter("xsl");
		}
		
		public URL getXslAsUrl(String relativePath){
		    return getResourceParameter("xsl", relativePath);
		}
  
  	     
              public String getExtension(){
            return getStringParameter("extension");
        }
  
      }

}
