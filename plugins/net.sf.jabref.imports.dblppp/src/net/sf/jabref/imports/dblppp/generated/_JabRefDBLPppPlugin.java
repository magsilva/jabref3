package net.sf.jabref.imports.dblppp.generated;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.java.plugin.Plugin;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.ExtensionPoint;

public abstract class _JabRefDBLPppPlugin extends Plugin {

	static Log log = LogFactory.getLog(_JabRefDBLPppPlugin.class);

	public void doStart() {
		log.debug("starting plugin");
	}

	public void doStop() {
		log.debug("stopping plugin");
	}

	public static String getId(){
        return "net.sf.jabref.imports.dblppp";
    }
	
	public List<String> getExtensions(){
        ExtensionPoint extPoint = getManager().getRegistry().getExtensionPoint("net.sf.jabref.core", "EntryFetcher");
        List<String> importers = new ArrayList<String>();
        for (Extension ext : extPoint.getConnectedExtensions()) {
        	importers.add(ext.getId());
		}
        
        return importers;
	}


}
