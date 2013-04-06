package net.sf.jabref.export.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.jabref.export.IExportFormat;
import net.sf.jabref.export.IExportFormatProvider;
import net.sf.jabref.export.xml.generated._JabRefXmlPlugin.XmlBasedFormatExtension;
import net.sf.jabref.plugin.PluginCore;

/**
 * Provides a pluggable list of {@link IExportFormat}s based on XML
 * transformation.
 * 
 * @author kariem
 */
public class XmlFormatProvider implements IExportFormatProvider {
 
  public List<IExportFormat> getExportFormats() {
    List<IExportFormat> exportFormats = Collections.emptyList();
    JabRefXmlPlugin plugin = JabRefXmlPlugin.getInstance(PluginCore
        .getManager());
    if (plugin != null){
      List<XmlBasedFormatExtension> extensions = plugin
          .getXmlBasedFormatExtensions();
      exportFormats = new ArrayList<IExportFormat>(extensions.size());
      for (XmlBasedFormatExtension formatter : extensions){
        XslFormatter xbf = new XslFormatter(formatter);
        exportFormats.add(xbf);
      }
    }
    return exportFormats;
  }
}
