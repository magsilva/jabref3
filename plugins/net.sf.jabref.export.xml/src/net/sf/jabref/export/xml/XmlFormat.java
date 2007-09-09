package net.sf.jabref.export.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.Set;

import net.sf.jabref.BibtexDatabase;
import net.sf.jabref.BibtexEntry;
import net.sf.jabref.Globals;
import net.sf.jabref.MetaData;
import net.sf.jabref.export.ExportFormat;
import net.sf.jabref.export.FileActions;
import net.sf.jabref.export.SaveSession;
import net.sf.jabref.export.VerifyingWriter;
import net.sourceforge.bibtexml.AbstractBibTeXParser;
import net.sourceforge.bibtexml.TeXLipseParser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.input.DOMBuilder;
import org.jdom.output.DOMOutputter;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Exports to bibtexml
 * 
 * @author kariem
 */
public class XmlFormat extends ExportFormat {

    private static final Log LOG = LogFactory.getLog(XmlFormat.class);

    private final AbstractBibTeXParser parser;

    /** Default constructor */
    public XmlFormat() {
        this(null, null, null);
    }

    /**
     * Creates an instance-level parser with utf-8 encoding. The encoding will
     * be overridden by the {@code encoding} parameter in
     * {@link #performExport(BibtexDatabase, Writer, String, Set)}
     * 
     * @param consoleName
     *            the name when called from the console
     * @param displayName
     *            the name to be displayed
     * @param extension
     *            the extension to be used by default
     */
    public XmlFormat(String consoleName, String displayName, String extension) {
        super(displayName, consoleName, null, null, extension);
        // default to UTF-8, will be overridden by encoding parameter in
        parser = new TeXLipseParser("UTF-8", true);
    }

    public void performExport(BibtexDatabase database,
        Writer writer, String encoding, Set<String> entryIds) throws Exception {
        File tmpFile = File.createTempFile("jabref.xml", ".bib");
        LOG.debug("Creating temp file: " + tmpFile);

        // save the entries in bibtex
        final SaveSession ss;
        if (entryIds == null || entryIds.isEmpty()) {
            ss = FileActions.saveDatabase(database, null, tmpFile,
                Globals.prefs, false, false, encoding);
        } else {
            List<BibtexEntry> sortedEntries = FileActions.getSortedEntries(
                database, entryIds, true);
            BibtexEntry[] entries = sortedEntries
                .toArray(new BibtexEntry[sortedEntries.size()]);
            ss = FileActions.savePartOfDatabase(database, null, tmpFile,
                Globals.prefs, entries, encoding);
        }

        VerifyingWriter tempWriter = ss.getWriter();
        if (!tempWriter.couldEncodeAll()) {
            LOG.warn(ss.getEncoding() + ": "
                + tempWriter.getProblemCharacters());
        }
        ss.commit();

        // prepare parser encoding
        parser.setInputCharset(encoding);

        // convert to bibtexml
        Document document = parser.parse(tmpFile);

        // delete temp file
        boolean deleted = tmpFile.delete();
        if (LOG.isDebugEnabled()) {
            if (deleted) {
                LOG.debug("Tempfile " + tmpFile + " deleted.");
            } else {
                LOG.warn("Tempfile " + tmpFile
                    + " was not deleted. Requested deletion on JVM exit.");
                tmpFile.deleteOnExit();
            }
        }

        org.w3c.dom.Document prepared = prepare(new DOMOutputter()
            .output(document));

        Format format = Format.getPrettyFormat().setEncoding(encoding);
        new XMLOutputter(format).output(new DOMBuilder().build(prepared),
            writer);
    }

    /**
     * This method can be overridden by subclasses to perform additional
     * manipulation.
     * 
     * @param document
     *            the document before it is returned
     * @return the prepared document
     */
    protected org.w3c.dom.Document prepare(org.w3c.dom.Document document) {
        return document;
    }

    @Override
    public void performExport(BibtexDatabase database, MetaData data, String file,
        String encoding, Set<String> entryIds) throws Exception {

        performExport(database, new FileWriter(file), encoding, entryIds);
    }

}