package net.sf.jabref.export.xml;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import net.sf.jabref.export.xml.generated._JabRefXmlPlugin.XmlBasedFormatExtension;
import net.sf.jabref.plugin.PluginCore;

import org.java.plugin.JpfException;
import org.java.plugin.PluginManager;
import org.java.plugin.standard.StandardPluginLocation;
import org.junit.Test;

/**
 * Tests {@link JabRefXmlPlugin}
 * @author kariem
 */
public class JabRefXmlPluginTest {

	/**
	 * Tests {@link JabRefXmlPlugin#getInstance(PluginManager)}.
	 * @throws MalformedURLException 
	 * @throws JpfException 
	 */
	@Test
	public void testGetInstance() throws MalformedURLException, JpfException {
	  
	    PluginManager manager = PluginCore.getManager();
	    
	    StandardPluginLocation dot = new StandardPluginLocation(new File("."), "plugin.xml");
	    
	    manager.publishPlugins(new StandardPluginLocation[]{dot});
	  
		JabRefXmlPlugin instance = JabRefXmlPlugin.getInstance(manager);
		List<XmlBasedFormatExtension> list = instance.getXmlBasedFormatExtensions();
		assertNotNull(list);
		assertFalse(list.isEmpty());
	}

}
