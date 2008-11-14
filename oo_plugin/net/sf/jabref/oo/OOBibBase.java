package net.sf.jabref.oo;

import com.sun.star.awt.XTextComponent;
import com.sun.star.beans.XPropertySet;
import com.sun.star.beans.Property;
import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.container.XEnumeration;
import com.sun.star.container.XEnumerationAccess;
import com.sun.star.container.XNameAccess;
import com.sun.star.container.XNamed;
import com.sun.star.frame.XController;
import com.sun.star.frame.XDesktop;
import com.sun.star.frame.XModel;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.text.*;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import net.sf.jabref.BibtexDatabase;
import net.sf.jabref.BibtexEntry;
import net.sf.jabref.Globals;
import net.sf.jabref.export.layout.Layout;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for manipulating the Bibliography of the currently start document in OpenOffice.
 */
public class OOBibBase {

    final static String BIB_SECTION_NAME = "JR_bib";
    final static String BIB_SECTION_END_NAME = "JR_bib_end";
    final static String BIB_CITATION = "JR_cite";
    final Pattern citePattern = Pattern.compile(BIB_CITATION+"\\d*_(\\d*)_(.*)");

    final static int
        AUTHORYEAR_PAR = 1,
        AUTHORYEAR_INTEXT = 2;

    final static String DEFAULT_CONNECTION_STRING = "uno:socket,host=localhost,port=2002;urp;StarOffice.ServiceManager";
    final String[] BIB_TYPES = new String[] { "ARTICLE", "BOOK", "BOOKLET", "CONFERENCE",
            "INBOOK", "INCOLLECTION", "INPROCEEDINGS", "JOURNAL", "MANUAL", "MASTERTHESIS",
            "MISC", "PHDTHESIS", "PROCEEDINGS", "TECHREPORT", "UNPUBLISHED", "EMAIL", "WWW",
            "CUSTOM1", "CUSTOM2", "CUSTOM3", "CUSTOM4", "CUSTOM5" };

    private XMultiServiceFactory mxDocFactory = null;
    private XTextDocument mxDoc = null;
    private XText text = null;
    private XDesktop xDesktop = null;
    XTextViewCursorSupplier xViewCursorSupplier = null;
    XComponent xCurrentComponent = null;
    private boolean atEnd;
    private AlphanumericComparator entryComparator = new AlphanumericComparator();
    private YearComparator yearComparator = new YearComparator();

    private HashMap<String,String> uniquefiers = new HashMap<String, String>();


    public OOBibBase(String pathToOO, boolean atEnd) throws Exception {
        this.atEnd = atEnd;
        xDesktop = simpleBootstrap(pathToOO);//getDesktop();
        try {
            selectDocument();
        } catch (Exception ex) {
            // Could not find a writer document?
            return;
        }
        
    }

    public boolean isConnectedToDocument() {
        return xCurrentComponent != null;
    }

    public String getCurrentDocumentTitle() {
        if (mxDoc != null) {
            try {
                return String.valueOf(OOUtil.getProperty
                        (mxDoc.getCurrentController().getFrame(), "Title"));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else
            return null;
    }

    public void selectDocument() throws Exception {
        List<XTextDocument> ls = getTextDocuments();
        XTextDocument selected = null;
        if (ls.size() == 0) {
            // No text documents found.
            throw new Exception("No Writer documents found");
        }
        else if (ls.size() > 1) {
            selected = OOUtil.selectComponent(null, xDesktop, ls);
        }
        else
            selected = ls.get(0);

        if (selected == null) {
            return;
        }
        xCurrentComponent = (XComponent) UnoRuntime.queryInterface(
                XComponent.class, selected);
        mxDoc = selected;

        com.sun.star.text.XDocumentIndexesSupplier indexesSupp = (com.sun.star.text.XDocumentIndexesSupplier) UnoRuntime.queryInterface(
                 com.sun.star.text.XDocumentIndexesSupplier.class, xCurrentComponent);

        XModel xModel = (XModel) UnoRuntime.queryInterface(XModel.class, xCurrentComponent);
        XController xController = xModel.getCurrentController();
        xViewCursorSupplier =
            (com.sun.star.text.XTextViewCursorSupplier) UnoRuntime.queryInterface(
                    com.sun.star.text.XTextViewCursorSupplier.class, xController);

        // get a reference to the body text of the document
        text = mxDoc.getText();

        // Access the text document's multi service factory:
        mxDocFactory = (XMultiServiceFactory) UnoRuntime.queryInterface(XMultiServiceFactory.class, mxDoc);

    }

    public XDesktop simpleBootstrap(String pathToExecutable) throws Exception {


        ClassLoader loader = ClassLoader.getSystemClassLoader();
        if (loader instanceof URLClassLoader) {
            URLClassLoader cl = (URLClassLoader) loader;
            Class sysclass = URLClassLoader.class;
            try {

                 Method method = sysclass.getDeclaredMethod("addURL", new Class[]{URL.class});
                 method.setAccessible(true);
                
                 method.invoke(cl, new Object[]{new File(pathToExecutable).toURI().toURL()});
             } catch (Throwable t) {
                 t.printStackTrace();
                 throw new IOException("Error, could not add URL to system classloader");
             }
         } else {
             System.out.println("Error occured, URLClassLoader expected but " +
                     loader.getClass() + " received. Could not continue.");
         }

         //Get the office component context:
         XComponentContext xContext = Bootstrap.bootstrap();

         //Get the office service manager:
         XMultiComponentFactory xServiceManager = xContext.getServiceManager();

         //Create the desktop, which is the root frame of the
         //hierarchy of frames that contain viewable components:
         Object desktop = xServiceManager.createInstanceWithContext("com.sun.star.frame.Desktop", xContext);

         return (XDesktop) UnoRuntime.queryInterface(XDesktop.class, desktop);

     }

     public List<XTextDocument> getTextDocuments() throws Exception {
         List<XTextDocument> res = new ArrayList<XTextDocument>();
         XEnumerationAccess enumA = xDesktop.getComponents();
         XEnumeration e = enumA.createEnumeration();

         // TODO: http://api.openoffice.org/docs/DevelopersGuide/OfficeDev/OfficeDev.xhtml#1_1_3_2_1_2_Frame_Hierarchies

         while (e.hasMoreElements()) {
             Object o = e.nextElement();
             XComponent comp = (XComponent) UnoRuntime.queryInterface(XComponent.class, o);
             XTextDocument doc = (XTextDocument) UnoRuntime.queryInterface(
                    XTextDocument.class, comp);
             if (doc != null) {
                res.add(doc);
             }
         }
         return res;
     }


    /**
     * This method inserts a cite marker in the text for the given BibtexEntry,
     * and refreshes the bibliography.
     * @param entries The entries to cite.
     * @param database The database the entry belongs to.
     * @param style The bibliography style we are using.
     * @param inParenthesis Indicates whether it is an in-text citation or a citation in parenthesis.
     *   This is not relevant if numbered citations are used.
     * @throws Exception
     */
    public void insertEntry(BibtexEntry[] entries, BibtexDatabase database, OOBibStyle style,
                            boolean inParenthesis) throws Exception {

        XTextViewCursor xViewCursor = xViewCursorSupplier.getViewCursor();

        if (entries.length > 1) {
            Arrays.sort(entries, yearComparator);    
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < entries.length; i++) {
            BibtexEntry entry = entries[i];
            if (i > 0)
                sb.append(",");
            sb.append(entry.getCiteKey());
        }
        String keyString = sb.toString();
        // Insert bookmark:
        String bName = getUniqueReferenceMarkName(keyString,
                inParenthesis ? AUTHORYEAR_PAR : AUTHORYEAR_INTEXT);
        //XTextContent content = insertBookMark(bName, xViewCursor);


        String citeText = style.isNumberEntries() ? "-" : style.getCitationMarker(entries, database, inParenthesis, null);
        text.insertString(xViewCursor, " ", false);
        xViewCursor.goLeft((short)1,false);
        insertReferenceMark(bName, citeText, xViewCursor);
        //xViewCursor.collapseToEnd();

        xViewCursor.collapseToEnd();
        xViewCursor.goRight((short)1,false);

        XTextRange position = xViewCursor.getEnd();
        // To account for numbering and for uniqiefiers, we must refresh the cite markers:
        refreshCiteMarkers(database, style);

        // Insert it at the current position:
        rebuildBibTextSection(database, style);
        // Go back to the relevant position:
        try {
            xViewCursor.gotoRange(position, false);
        } catch (Exception ex) {
            System.out.println("Catch");
            ex.printStackTrace();
        }

    }

    public void refreshCiteMarkers(BibtexDatabase database, OOBibStyle style) throws
            UndefinedParagraphFormatException, Exception {

        List<String> cited = findCitedKeys();
        List<BibtexEntry> entries = findCitedEntries(database, cited);

        XReferenceMarksSupplier supplier = (XReferenceMarksSupplier) UnoRuntime.queryInterface(
                XReferenceMarksSupplier.class, xCurrentComponent);
        XNameAccess nameAccess = supplier.getReferenceMarks();
        String[] names;
        if (style.isSortByPosition()) {
            // We need to sort the reference marks according to their order of appearance:
           names = getSortedReferenceMarks(nameAccess);
        }
        else if (style.isNumberEntries()) {
            // We need to sort the reference marks according to the sorting of the bibliographic
            // entries:
            Collections.sort(entries, entryComparator);
            // Rebuild the list of cited keys according to the sort order:
            cited.clear();
            for (Iterator<BibtexEntry> iterator = entries.iterator(); iterator.hasNext();) {
                BibtexEntry entry = iterator.next();
                cited.add(entry.getCiteKey());
            }
            names = nameAccess.getElementNames();
        }
        else
            names = getSortedReferenceMarks(nameAccess);

        HashMap<String,Integer> numbers = new HashMap<String, Integer>();
        //HashMap<S
        int lastNum = 0;
        // First compute citation markers for all citations:
        String[] citMarkers = new String[names.length];
        String[][] normCitMarkers = new String[names.length][];
        String[][] bibtexKeys = new String[names.length][];
        int[] types = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            Matcher m = citePattern.matcher(names[i]);
            if (m.find()) {
                String typeStr = m.group(1);
                int type = Integer.parseInt(typeStr);
                types[i] = type; // Remember the type in case we need to uniqiefy.
                String[] keys = m.group(2).split(",");
                bibtexKeys[i] = keys;
                BibtexEntry[] cEntries = new BibtexEntry[keys.length];
                for (int j = 0; j < cEntries.length; j++) {
                    cEntries[j] = database.getEntryByKey(keys[j]);
                     if (cEntries[j] == null) {
                        throw new BibtexEntryNotFoundException(keys[j], "");
                     }
                }

                String[] normCitMarker = new String[keys.length];
                String citationMarker;
                if (style.isNumberEntries()) {
                    if (style.isSortByPosition()) {
                        // We have sorted the citation markers according to their order of appearance,
                        // so we simply count up for each marker referring to a new entry:
                        int[] num = new int[keys.length];
                        for (int j=0; j<keys.length; j++) {
                            num[j] = lastNum + 1;
                            if (numbers.containsKey(keys[j]))
                                num[j] = numbers.get(keys[j]);
                            else {
                                numbers.put(keys[j], num[j]);
                                lastNum = num[j];
                            }
                        }
                        citationMarker = style.getNumCitationMarker(num);
                        for (int j=0; j<keys.length; j++)
                            normCitMarker[j] = style.getNumCitationMarker(new int[] {num[j]});
                    }
                    else {
                        // We need to find the number of the cited entry in the bibliography,
                        // and use that number for the cite marker:
                        int[] num = findCitedEntryIndex(names[i], cited);

                        if (num != null)
                            citationMarker = style.getNumCitationMarker(num);
                        else
                            throw new BibtexEntryNotFoundException(names[i], Globals.lang("Could not resolve BibTeX entry for citation marker '%0'.", names[i]));

                        for (int j=0; j<keys.length; j++)
                            normCitMarker[j] = style.getNumCitationMarker(new int[] {num[j]});
                    }
                }
                else {
                    citationMarker = style.getCitationMarker(cEntries, database, type == AUTHORYEAR_PAR, null);
                    // We need "normalized" (in parenthesis) markers for uniqueness checking purposes:
                    for (int j=0; j<cEntries.length; j++)
                        normCitMarker[j] = style.getCitationMarker(cEntries[j], database, true, null);
                }
                citMarkers[i] = citationMarker;
                normCitMarkers[i] = normCitMarker;
            }
        }

        uniquefiers.clear();
        if (!style.isNumberEntries()) {
            // See if there are duplicate citations marks referring to different entries. If so, we need to
            // use uniquefiers:
            HashMap<String,List<String>> refKeys = new HashMap<String, List<String>>();
            HashMap<String,List<Integer>> refNums = new HashMap<String, List<Integer>>();
            for (int i = 0; i < citMarkers.length; i++) {
                String[] markers = normCitMarkers[i]; // compare normalized markers, since the actual markers can be different
                for (int j=0; j<markers.length; j++) {
                    String marker = markers[j];
                    if (!refKeys.containsKey(marker)) {
                        List<String> l = new ArrayList<String>(1);
                        l.add(bibtexKeys[i][j]);
                        refKeys.put(marker, l);
                        List<Integer> l2 = new ArrayList<Integer>(1);
                        l2.add(i);
                        refNums.put(marker, l2);
                    }
                    else {
                        // Ok, we have seen this exact marker before.
                        if (!refKeys.get(marker).contains(bibtexKeys[i][j])) {
                            // ... but not for this entry.
                            refKeys.get(marker).add(bibtexKeys[i][j]);
                            refNums.get(marker).add(i);
                        }
                    }
                }
            }
            // Go through the collected lists and see where we need to uniquefy:
            for (String marker : refKeys.keySet()) {
                List<String> keys = refKeys.get(marker);
                if (keys.size() > 1) {
                    // This marker appears for more than one unique entry:
                    int uniq = 'a';
                    for (String key : keys) {
                        // Update the map of uniquefiers for the benefit of both the following generation of new
                        // citation markers, and for the method that builds the bibliography:
                        uniquefiers.put(key, String.valueOf((char)uniq));
                        uniq++;
                    }
                }
            }

            // Finally, go through all citation markers, and update those referring to entries in our current list:
            for (int j = 0; j < bibtexKeys.length; j++) {
                boolean needsChange = false;
                String[] uniquif = new String[bibtexKeys[j].length];
                BibtexEntry[] cEntries = new BibtexEntry[bibtexKeys[j].length];
                for (int k=0; k<bibtexKeys[j].length; k++) {
                    String uniq = uniquefiers.get(bibtexKeys[j][k]);
                    if ((uniq != null) && (uniq.length() >= 0)) {
                        needsChange = true;
                        cEntries[k] = database.getEntryByKey(bibtexKeys[j][k]);
                        uniquif[k] = uniq;
                    }
                    else {
                        cEntries[k] = database.getEntryByKey(bibtexKeys[j][k]);
                        uniquif[k] = "";
                    }
                }
                if (needsChange)
                    citMarkers[j] = style.getCitationMarker(cEntries, database, types[j] == AUTHORYEAR_PAR,
                        uniquif);
            }
        }

        // Refresh all reference marks with the citation markers we computed:
        boolean hadBibSection = getBookmarkRange(BIB_SECTION_NAME) != null;
        for (int i = 0; i < names.length; i++) {
            Object o = nameAccess.getByName(names[i]);
            XTextContent bm = (XTextContent) UnoRuntime.queryInterface
                    (XTextContent.class, o);
            XTextCursor cursor = text.createTextCursorByRange(bm.getAnchor());
            text.removeTextContent(bm);
            insertReferenceMark(names[i], citMarkers[i], cursor);
            if (hadBibSection && (getBookmarkRange(BIB_SECTION_NAME) == null)) {
                // We have overwritten the marker for the start of the reference list.
                // We need to add it again.
                cursor.collapseToEnd();
                OOUtil.insertParagraphBreak(text, cursor);
                insertBookMark(BIB_SECTION_NAME, cursor);
                /* The following is for resetting the paragraph format, but should probably
                   not be done.
                   
                XParagraphCursor parCursor =
                    (XParagraphCursor)UnoRuntime.queryInterface(
                    java.lang.Class.forName("com.sun.star.text.XParagraphCursor"), cursor);
                parCursor.gotoPreviousParagraph(false);
                parCursor.gotoStartOfParagraph(false);
                parCursor.gotoEndOfParagraph(true);
                XPropertySet props = (XPropertySet) UnoRuntime.queryInterface(
                    XPropertySet.class, parCursor);

                try {
                    props.setPropertyValue("ParaStyleName", "Default");
                } catch (com.sun.star.lang.IllegalArgumentException ex) {
                    throw new UndefinedParagraphFormatException("Default");
                }
                */

            }
        }
    }

    public String[] getSortedReferenceMarks(final XNameAccess nameAccess) throws Exception {
        String[] names = nameAccess.getElementNames();
        final XTextRangeCompare compare = (XTextRangeCompare) UnoRuntime.queryInterface
                (XTextRangeCompare.class, text);
        Arrays.sort(names, new Comparator<String>() {
            public int compare(String o1, String o2) {
                try {
                    XTextRange r1 = ((XTextContent) UnoRuntime.queryInterface
                            (XTextContent.class, nameAccess.getByName(o1))).getAnchor();
                    XTextRange r2 = ((XTextContent) UnoRuntime.queryInterface
                            (XTextContent.class, nameAccess.getByName(o2))).getAnchor();
                    return compare.compareRegionStarts(r2, r1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return 0;
                }
            }
        });
        return names;
    }

    public void rebuildBibTextSection(BibtexDatabase database, OOBibStyle style)
            throws UndefinedParagraphFormatException, Exception {
        List<String> cited = findCitedKeys();
        List<BibtexEntry> entries = findCitedEntries(database, cited);
        clearBibTextSectionContent();
        populateBibTextSection(database, entries, style);
    }



    public String getUniqueReferenceMarkName(String bibtexKey, int type) {
        XReferenceMarksSupplier supplier = (XReferenceMarksSupplier) UnoRuntime.queryInterface(
                XReferenceMarksSupplier.class, xCurrentComponent);
        XNameAccess xNamedRefMarks = supplier.getReferenceMarks();
        int i=0;
        String name = BIB_CITATION+"_"+type+"_"+bibtexKey;
        while (xNamedRefMarks.hasByName(name)) {
            name = BIB_CITATION+i+"_"+type+"_"+bibtexKey;
            i++;
        }
        return name;
    }

    public List<BibtexEntry> findCitedEntries(BibtexDatabase database, List<String> keys) {
        List<BibtexEntry> entries = new ArrayList<BibtexEntry>();
        for (String key : keys) {
            entries.add(database.getEntryByKey(key));
        }
        return entries;
    }

    public List<String> findCitedKeys() {

        XReferenceMarksSupplier supplier = (XReferenceMarksSupplier) UnoRuntime.queryInterface(
                XReferenceMarksSupplier.class, xCurrentComponent);
        XNameAccess xNamedMarks = supplier.getReferenceMarks();
        String[] names = xNamedMarks.getElementNames();
        ArrayList<String> keys = new ArrayList<String>();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            List<String> newKeys = parseRefMarkName(name);
            for (String key : newKeys)
                if (!keys.contains(key))
                    keys.add(key);
        }

        return keys;
    }

    /**
     * Extract the list of bibtex keys from a reference mark name.
     * @param name The reference mark name.
     * @return The list of bibtex keys encoded in the name.
     */
    public List<String> parseRefMarkName(String name) {
        ArrayList<String> keys = new ArrayList<String>();
        Matcher m = citePattern.matcher(name);
        if (m.find()) {
            String[] keystring = m.group(2).split(",");
            for (int j = 0; j < keystring.length; j++) {
                if (!keys.contains(keystring[j]))
                    keys.add(keystring[j]);
            }
        }
        return keys;
    }

    /**
     * Resolve the bibtex key from a citation reference marker name, and look up
     * the index of the key in a list of keys.
     * @param citRefName The name of the ReferenceMark representing the citation.
     * @param keys A List of bibtex keys representing the entries in the bibliography.
     * @return the indices of the cited keys, 0 if a key is not found. Returns null if the ref name
     *   could not be resolved as a citation.
     */
    public int[] findCitedEntryIndex(String citRefName, List<String> keys) {
        Matcher m = citePattern.matcher(citRefName);
        if (m.find()) {
            String[] keyStrings = m.group(2).split(",");
            int[] res = new int[keyStrings.length];
            for (int i=0; i<keyStrings.length; i++)
                res[i] = 1+keys.indexOf(keyStrings[i]);
            return res;
        }
        else
            return null;
    }


    public void insertFullReferenceAtCursor(XTextCursor cursor, BibtexDatabase database,
                                            List<BibtexEntry> entries,
                                            OOBibStyle style, String parFormat)
            throws UndefinedParagraphFormatException, Exception {
        // If we don't have numbered entries, we need to sort the entries before adding them:
        if (!style.isSortByPosition())
            Collections.sort(entries, entryComparator);
        int number = 1;
        for (BibtexEntry entry : entries) {
            OOUtil.insertParagraphBreak(text, cursor);
            if (style.isNumberEntries()) {
                OOUtil.insertTextAtCurrentLocation(text, cursor, style.getNumCitationMarker(new int[] {number++})+" ", false, false);
            }
            Layout layout = style.getReferenceFormat(entry.getType().getName());
            OOUtil.insertFullReferenceAtCurrentLocation(text, cursor, layout, parFormat, entry, database,
                    uniquefiers.get(entry.getCiteKey()));
        }
        
    }

    public void insertFullReferenceAtViewCursor(BibtexDatabase database, List<BibtexEntry> entries,
                                                OOBibStyle style, String parFormat) throws Exception {
        XTextViewCursor xViewCursor = xViewCursorSupplier.getViewCursor();
        insertFullReferenceAtCursor(xViewCursor, database, entries, style, parFormat);
    }


    public void insertMarkedUpTextAtViewCursor(String lText, String parFormat) throws Exception {
        XTextViewCursor xViewCursor = xViewCursorSupplier.getViewCursor();
        XTextCursor cursor = text.createTextCursorByRange(xViewCursor.getEnd());
        OOUtil.insertOOFormattedTextAtCurrentLocation(text,cursor, lText, parFormat);
        
    }

    /**
     * This method creates and inserts an XTextSection named as determined by the
     * string BIB_SECTION_NAME.
     * @param end true to indicate that the section should be put at the end of the document,
     *  false to indicate that it should be put at the cursor position.
     * @return true if the bibliography already existed, false otherwise..
     * @throws Exception
     */
    public boolean createBibTextSection(boolean end) throws Exception {
        // Check if there already is a bookmarked section:
        XBookmarksSupplier bSupp = (XBookmarksSupplier) UnoRuntime.queryInterface(
                 XBookmarksSupplier.class, mxDoc);
        if (bSupp.getBookmarks().hasByName(BIB_SECTION_NAME)) {
            System.out.println("Found existing JabRef bookmark");
            return true;
        }
        XTextCursor mxDocCursor = text.createTextCursor();
        if (end)
            mxDocCursor.gotoEnd(false);
        OOUtil.insertParagraphBreak(text, mxDocCursor);
        insertBookMark(BIB_SECTION_NAME, mxDocCursor);
        return false;
    }

    public void clearBibTextSectionContent() throws Exception {
        // Get a range comparator:
        XTextRangeCompare compare = (XTextRangeCompare) UnoRuntime.queryInterface
                (XTextRangeCompare.class, text);
        // Find the bookmarks for the bibliography:
        XTextRange range = getBookmarkRange(BIB_SECTION_NAME);
        if (range == null)
            createBibTextSection(atEnd);
        XTextRange rangeEnd = getBookmarkRange(BIB_SECTION_END_NAME);
        if (rangeEnd == null) {
            // No end bookmark. This means that there is no bibliography.
            return;
        }
        // Get a paragraph cursor at the start of the bibliography:
        //System.out.println("text="+text+" range="+range);
        XTextCursor mxDocCursor = text.createTextCursorByRange(range.getEnd());
        mxDocCursor.goRight((short)1, true);
        boolean couldExpand = true;
        while (couldExpand && (compare.compareRegionEnds(mxDocCursor, rangeEnd) > 0)) {
            couldExpand = mxDocCursor.goRight((short)1, true);
        }
        // Finally, clear the bibliography:
        mxDocCursor.setString("");
        mxDocCursor.collapseToStart();
        removeBookMark(BIB_SECTION_END_NAME);
        // If we lost the start bookmark, recreate it:
        if (getBookmarkRange(BIB_SECTION_NAME) == null)
            insertBookMark(BIB_SECTION_NAME, mxDocCursor);
    }

    public void populateBibTextSection(BibtexDatabase database, List<BibtexEntry> entries,
                                       OOBibStyle style)
            throws UndefinedParagraphFormatException, Exception {
        XTextRange range = getBookmarkRange(BIB_SECTION_NAME);
        XTextCursor cursor = text.createTextCursorByRange(range.getEnd());
        OOUtil.insertTextAtCurrentLocation(text, cursor, (String)style.getProperty("Title"),
                (String)style.getProperty("ReferenceHeaderParagraphFormat"));
        insertFullReferenceAtCursor(cursor, database, entries, style,
                (String)style.getProperty("ReferenceParagraphFormat"));
        insertBookMark(BIB_SECTION_END_NAME, cursor);
    }

    public XTextContent insertBookMark(String name, XTextCursor position) throws Exception {
        Object bookmark = mxDocFactory.createInstance("com.sun.star.text.Bookmark");
        // name the bookmark
        XNamed xNamed = (XNamed) UnoRuntime.queryInterface(
                XNamed.class, bookmark);
        xNamed.setName(name);
        // get XTextContent interface
        XTextContent xTextContent = (XTextContent) UnoRuntime.queryInterface(
                XTextContent.class, bookmark);
        // insert bookmark at the end of the document
        // instead of mxDocText.getEnd you could use a text cursor's XTextRange interface or any XTextRange
        text.insertTextContent(position, xTextContent, true);
        position.collapseToEnd();
        return xTextContent;
    }

    public void insertReferenceMark(String name, String citText, XTextCursor position)
            throws Exception {
        Object bookmark = mxDocFactory.createInstance("com.sun.star.text.ReferenceMark");
        // Name the reference
        XNamed xNamed = (XNamed) UnoRuntime.queryInterface(
                XNamed.class, bookmark);
        xNamed.setName(name);
        // get XTextContent interface
        XTextContent xTextContent = (XTextContent) UnoRuntime.queryInterface(
                XTextContent.class, bookmark);
        position.setString(citText);
        text.insertTextContent(position, xTextContent, true);
        position.collapseToEnd();

    }

    public void testFootnote() throws Exception {
        XTextViewCursor xViewCursor = xViewCursorSupplier.getViewCursor();
        insertFootnote("jabbes", "Cite text", xViewCursor);
    }

    public void insertFootnote(String name, String citText, XTextCursor position) throws Exception {
        XFootnote xFootnote = (XFootnote) UnoRuntime.queryInterface( XFootnote.class,
            mxDocFactory.createInstance("com.sun.star.text.Footnote"));
        xFootnote.setLabel("");
        XPropertySet props = (XPropertySet)UnoRuntime.queryInterface(XPropertySet.class, xFootnote);
        props.setPropertyValue("ReferenceId", name); // doesn't work: short data type
        System.out.println(props.getPropertyValue("ReferenceId"));
        XTextContent xContent = (XTextContent)UnoRuntime.queryInterface(
            XTextContent.class, xFootnote);
        text.insertTextContent (position, xContent, false);
        XSimpleText xSimple = (XSimpleText)UnoRuntime.queryInterface(XSimpleText.class, xFootnote);
        XTextRange xRange = (XTextRange)UnoRuntime.queryInterface(XTextRange.class, xSimple.createTextCursor());
        xSimple.insertString (xRange, citText, false);
    }

    public void removeBookMark(String name) throws Exception {
        XBookmarksSupplier xBookmarksSupplier = (XBookmarksSupplier) UnoRuntime.queryInterface(
                XBookmarksSupplier.class, xCurrentComponent);
        if (xBookmarksSupplier.getBookmarks().hasByName(name)) {
            Object o = xBookmarksSupplier.getBookmarks().getByName(name);
            XTextContent bm = (XTextContent) UnoRuntime.queryInterface(
                XTextContent.class, o);
            text.removeTextContent(bm);
        }
    }

    public void removeReferenceMark(String name) throws Exception {
        XReferenceMarksSupplier xSupplier = (XReferenceMarksSupplier) UnoRuntime.queryInterface(
                XReferenceMarksSupplier.class, xCurrentComponent);
        if (xSupplier.getReferenceMarks().hasByName(name)) {
            Object o = xSupplier.getReferenceMarks().getByName(name);
            XTextContent bm = (XTextContent) UnoRuntime.queryInterface(
                XTextContent.class, o);
            text.removeTextContent(bm);
        }
    }

    /**
     * Get the XTextRange corresponding to the named bookmark.
     * @param name The name of the bookmark to find.
     * @return The XTextRange for the bookmark.
     * @throws Exception
     */
    public XTextRange getBookmarkRange(String name) throws Exception {
        // query XBookmarksSupplier from document model and get bookmarks collection
        XBookmarksSupplier xBookmarksSupplier = (XBookmarksSupplier) UnoRuntime.queryInterface(
                XBookmarksSupplier.class, xCurrentComponent);
        XNameAccess xNamedBookmarks = xBookmarksSupplier.getBookmarks();

        // retrieve bookmark by name
        //System.out.println("Name="+name+" : "+xNamedBookmarks.hasByName(name));
        if (!xNamedBookmarks.hasByName(name))
            return null;
        Object foundBookmark = xNamedBookmarks.getByName(name);
        XTextContent xFoundBookmark = (XTextContent) UnoRuntime.queryInterface(
                XTextContent.class, foundBookmark);
        return xFoundBookmark.getAnchor();
    }

    public void printBookmarkNames() throws Exception {
        XBookmarksSupplier xBookmarksSupplier = (XBookmarksSupplier) UnoRuntime.queryInterface(
                XBookmarksSupplier.class, xCurrentComponent);
        XNameAccess xNamedBookmarks = xBookmarksSupplier.getBookmarks();
        String[] names = xNamedBookmarks.getElementNames();
        for (int i = 0; i < names.length; i++) {
            System.out.println(i+". "+names[i]);
        }
    }


    /**
     * Focus the active OO document.
     */
    public void setFocus() {
        xDesktop.getCurrentFrame().getContainerWindow().setFocus();
    }


    public void combineCiteMarkers(BibtexDatabase database, OOBibStyle style) throws Exception {
        XReferenceMarksSupplier supplier = (XReferenceMarksSupplier) UnoRuntime.queryInterface(
                XReferenceMarksSupplier.class, xCurrentComponent);
        XNameAccess nameAccess = supplier.getReferenceMarks();
        String[] names = getSortedReferenceMarks(nameAccess);
        final XTextRangeCompare compare = (XTextRangeCompare) UnoRuntime.queryInterface
                (XTextRangeCompare.class, text);

        int piv = 0;
        boolean madeModifications = false;
        while (piv < names.length-1) {
            XTextRange r1 = ((XTextContent) UnoRuntime.queryInterface
                            (XTextContent.class, nameAccess.getByName(names[piv]))).getAnchor().getEnd();
            XTextRange r2 = ((XTextContent) UnoRuntime.queryInterface
                            (XTextContent.class,
                                    nameAccess.getByName(names[piv+1]))).getAnchor().getStart();
            XTextCursor mxDocCursor = text.createTextCursorByRange(r1);
            mxDocCursor.goRight((short)1, true);
            boolean couldExpand = true;
            while (couldExpand && (compare.compareRegionEnds(mxDocCursor, r2) > 0)) {
                couldExpand = mxDocCursor.goRight((short)1, true);
            }
            String text = mxDocCursor.getString();
            // Check if the string contains no line breaks and only whitespace:
            if ((text.indexOf('\n') == -1) && (text.trim().length() == 0)) {
                List<String> keys = parseRefMarkName(names[piv]);
                keys.addAll(parseRefMarkName(names[piv+1]));
                removeReferenceMark(names[piv]);
                removeReferenceMark(names[piv+1]);
                StringBuilder sb = new StringBuilder();
                int i=0;
                for (String key : keys) {
                    if (i > 0)
                        sb.append(",");
                    sb.append(key);
                    i++;
                }
                String keyString = sb.toString();
                boolean inParenthesis = true;
                // Insert bookmark:
                String bName = getUniqueReferenceMarkName(keyString,
                        inParenthesis ? AUTHORYEAR_PAR : AUTHORYEAR_INTEXT);
                insertReferenceMark(bName, "tmp", mxDocCursor);
                names[piv+1] = bName;
                madeModifications = true;
            }
            piv++;
        }
        if (madeModifications)
            refreshCiteMarkers(database, style);

    }
}
