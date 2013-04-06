package net.sf.jabref.export.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import net.sf.jabref.export.xml.generated._JabRefXmlPlugin.XmlBasedFormatExtension;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;

/**
 * Xmlformat that transform the result via XSL.
 * 
 * @author kariem
 */
public class XslFormatter extends XmlFormat implements ErrorListener {

  private static final Log LOG = LogFactory.getLog(XslFormatter.class);

  private static TransformerFactory transformerFactory;

  private Transformer transformer;

  private DocumentBuilder docBuilder;

  private XslFormatter(String consoleName, String displayName, String extension) {
    super(consoleName, displayName, extension);

    ClassLoader currThreadClsLdr = Thread.currentThread()
        .getContextClassLoader();

    try{
      // set current thread class loader to current class' class loader
      Thread.currentThread().setContextClassLoader(
          this.getClass().getClassLoader());

      if (transformerFactory == null){
        // needed for XSLT 2.0
        System.setProperty("javax.xml.transform.TransformerFactory",
            "net.sf.saxon.TransformerFactoryImpl");
        transformerFactory = TransformerFactory.newInstance();
      }
      transformerFactory.setErrorListener(this);
      try{
        docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      }catch (ParserConfigurationException e){
        throw new RuntimeException(e);
      }
    }finally{
      // restore previous class loader in current thread
      Thread.currentThread().setContextClassLoader(currThreadClsLdr);
    }

  }

  /**
   * @param xbfe
   *            the extension
   */
  public XslFormatter(XmlBasedFormatExtension xbfe) {
    this(xbfe.getConsoleName(), xbfe.getDisplayName(), xbfe.getExtension(),
        xbfe.getXslAsUrl());
  }

  /**
   * @param consoleName
   *            the console name
   * @param displayName
   *            the display name
   * @param extension
   *            the file extension
   * @param xsl
   *            the URL of the xsl file
   */
  protected XslFormatter(String consoleName, String displayName,
      String extension, URL xslUrl) {
    this(consoleName, displayName, extension);

    if (xslUrl == null){ throw new RuntimeException(
        "Could not find XSL file for exporter " + getDisplayName()); }

    LOG.debug("Loading from " + xslUrl);
    InputStream is;
    try{
      is = xslUrl.openStream();
    }catch (IOException e){
      throw new RuntimeException("Could not read from XSL file for exporter "
          + getDisplayName(), e);
    }
    try{
      StreamSource source = new StreamSource(is);
      Templates template = transformerFactory.newTemplates(source);
      transformer = template.newTransformer();
    }catch (TransformerConfigurationException e){
      throw new RuntimeException("Configuration problem for exporter "
          + getDisplayName(), e);
    }
  }

  @Override
  protected Document prepare(Document document) {
    Source source = new DOMSource(document);
    return prepare(source);
  }

  /**
   * @param source
   *            the source to prepare
   * @return a document prepared according to this formatter's configuration.
   */
  protected Document prepare(Source source) {
    Document doc = docBuilder.newDocument();
    Result result = new DOMResult(doc);
    try{
      // transform from source to result
      transformer.transform(source, result);
      // create result
      return doc;
    }catch (TransformerException e){
      throw new RuntimeException(
          "An error occurred while trying to export using " + getDisplayName(),
          e);
    }
  }

  public void error(TransformerException e) {
    LOG.error(e.getMessageAndLocation(), e);
  }

  public void fatalError(TransformerException e) {
    LOG.fatal(e.getMessageAndLocation(), e);
  }

  public void warning(TransformerException e) {
    LOG.warn(e.getMessageAndLocation(), e);
  }

}
