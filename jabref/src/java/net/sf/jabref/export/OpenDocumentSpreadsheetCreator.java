/*
 * OpenOfficeDocumentCreator.java
 *
 * Created on February 16, 2005, 8:04 PM
 */

package net.sf.jabref.export;

import net.sf.jabref.*;

import java.io.*;
import java.util.zip.*;
import java.util.Set;
import java.net.URL;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

/**
 * @author alver
 */
public class OpenDocumentSpreadsheetCreator extends ExportFormat {

    /**
     * Creates a new instance of OpenOfficeDocumentCreator
     */
    public OpenDocumentSpreadsheetCreator() {
        super(Globals.lang("OpenDocument Spreadsheet"), "ods", null, null, ".ods");
    }

    public void performExport(final BibtexDatabase database, final String file, final String encoding, Set keySet) throws Exception {
        exportOpenDocumentSpreadsheet(new File(file), database, keySet);
    }

    public static void storeOpenDocumentSpreadsheetFile(File file, InputStream source) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        try {
            ZipEntry zipEntry = new ZipEntry("content.xml");
            out.putNextEntry(zipEntry);
            int c = -1;
            while ((c = source.read()) >= 0) {
                out.write(c);
            }
            out.closeEntry();

            // Add manifest (required for OOo 2.0) and "meta.xml": These are in the
            // resource/ods directory, and are copied verbatim into the zip file.
            addResourceFile("meta.xml", "/resource/ods/meta.xml", out);
            //addResourceFile("mimetype", "/resource/openoffice/mimetype", out);
            addResourceFile("META-INF/manifest.xml", "/resource/ods/manifest.xml", out);

            //zipEntry = new ZipEntry()

        } finally {
            out.close();
        }
    }

    public static void exportOpenDocumentSpreadsheet(File file, BibtexDatabase database, Set keySet) throws Exception {

        // First store the xml formatted content to a temporary file.
        File tmpFile = File.createTempFile("opendocument", null);
        exportOpenDocumentSpreadsheetXML(tmpFile, database, keySet);

        // Then add the content to the zip file:
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(tmpFile));
        storeOpenDocumentSpreadsheetFile(file, in);

        // Delete the temporary file:
        tmpFile.delete();
    }

    public static void exportOpenDocumentSpreadsheetXML(File tmpFile, BibtexDatabase database, Set keySet) {
        OpenDocumentRepresentation od = new OpenDocumentRepresentation(database, keySet);

        try {
            Writer ps = new OutputStreamWriter(new FileOutputStream(tmpFile), "UTF8");
            try {

                //            Writer ps = new FileWriter(tmpFile);
                DOMSource source = new DOMSource(od.getDOMrepresentation());
                StreamResult result = new StreamResult(ps);
                Transformer trans = TransformerFactory.newInstance().newTransformer();
                trans.setOutputProperty(OutputKeys.INDENT, "yes");
                trans.transform(source, result);
            } finally {
                ps.close();
            }
        } catch (Exception e) {
            throw new Error(e);
        }

        return;
    }

    private static void addResourceFile(String name, String resource, ZipOutputStream out) throws IOException {
        ZipEntry zipEntry = new ZipEntry(name);
        out.putNextEntry(zipEntry);
        addFromResource(resource, out);
        out.closeEntry();
    }

    private static void addFromResource(String resource, OutputStream out) {
        URL url = OpenDocumentSpreadsheetCreator.class.getResource(resource);
        try {
            InputStream in = url.openStream();
            byte[] buffer = new byte[256];
            synchronized (in) {
                synchronized (out) {
                    while (true) {
                        int bytesRead = in.read(buffer);
                        if (bytesRead == -1) break;
                        out.write(buffer, 0, bytesRead);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
