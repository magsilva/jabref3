package net.sf.jabref.oo;

import net.sf.jabref.AuthorList;
import net.sf.jabref.BibtexDatabase;
import net.sf.jabref.BibtexEntry;
import net.sf.jabref.Globals;
import net.sf.jabref.export.layout.Layout;
import net.sf.jabref.export.layout.LayoutFormatter;
import net.sf.jabref.export.layout.LayoutHelper;
import net.sf.jabref.export.layout.format.RemoveLatexCommands;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * This class embodies a bibliography formatting for OpenOffice, which is composed
 * of the following elements:
 *
 * 1) Each OO bib entry type must have a formatting. A formatting is an array of elements, each
 *    of which is either a piece of constant text, an entry field value, or a tab. Each element has
 *    a character format associated with it.
 *
 * 2) Many field values (e.g. author) need to be formatted before input to OpenOffice. The style
 *    has the responsibility of formatting all field values. Formatting is handled by 0-n
 *    JabRef LayoutFormatter classes.
 *
 * 3) If the entries are not numbered, a citation marker must be produced for each entry. This
 *    operation is performed for each JabRef BibtexEntry.
 */
public class OOBibStyle implements Comparable {

    String name = null;
    SortedSet<String> journals = new TreeSet<String>();

    Layout defaultBibLayout;
    // formatters mapped from field names. Each field can have no mapping, or a set of formatters:
    HashMap formatters = new HashMap();
    // 0-n formatters applied to all fields before field-speficic formatters:
    LayoutFormatter[] allBeforeFormat, allAfterFormat;
    // reference layout mapped from entry type number:
    HashMap<String, Layout> bibLayout = new HashMap<String, Layout>();

    HashMap properties = new HashMap();
    HashMap citProperties = new HashMap();

    Pattern numPattern = Pattern.compile("\\d*");

    boolean valid = false;

    List juks = new ArrayList();

    final static int NONE = 0, LAYOUT = 1, PROPERTIES=2, CITATION=3, NAME=4, JOURNALS=5;
    final static String LAYOUT_MRK = "LAYOUT",
        PROPERTIES_MARK = "PROPERTIES",
        CITATION_MARK = "CITATION",
        NAME_MARK = "NAME",
        JOURNALS_MARK = "JOURNALS",
        DEFAULT_MARK = "default";
    private File styleFile = null;
    private static long styleFileModificationTime = Long.MIN_VALUE;
    private String COMBINED_ENTRIES_SEPARATOR = "-";

    public OOBibStyle(File styleFile) throws Exception {
        this(new FileReader(styleFile));
        this.styleFile = styleFile;
        styleFileModificationTime = (styleFile).lastModified();
    }

    public OOBibStyle(Reader in) throws Exception {

        // Set default property values:
        properties.put("Title", "Bibliography");
        properties.put("SortAlgorithm", "alphanumeric");
        properties.put("IsSortByPosition", Boolean.FALSE);
        properties.put("IsNumberEntries", Boolean.FALSE);
        properties.put("BracketBefore", "[");
        properties.put("BracketAfter", "]");
        properties.put("ReferenceParagraphFormat", "Default");
        properties.put("ReferenceHeaderParagraphFormat", "Heading 1");

        // Set default properties for the citation marker:
        citProperties.put("AuthorField", "author/editor");
        citProperties.put("YearField", "year");
        citProperties.put("MaxAuthors", 3);
        citProperties.put("AuthorSeparator", ", ");
        citProperties.put("AuthorLastSeparator", " & ");
        citProperties.put("EtAlString", " et al.");
        citProperties.put("YearSeparator", ", ");
        citProperties.put("InTextYearSeparator", " ");
        citProperties.put("BracketBefore", "(");
        citProperties.put("BracketAfter", ")");
        citProperties.put("CitationSeparator", "; ");
        citProperties.put("GroupedNumbersSeparator", "-");
        citProperties.put("FormatCitations", Boolean.FALSE);
        citProperties.put("ItalicCitations", Boolean.FALSE);
        citProperties.put("BoldCitations", Boolean.FALSE);


        initialize(in);


    }

    public String getName() {
        return name;
    }

    public File getFile() {
        return styleFile;
    }

    public Set<String> getJournals() {
        return Collections.unmodifiableSet(journals);
    }

    private void initialize(Reader in) throws IOException {
        name = null;
        readFormatFile(in);
        allBeforeFormat = new LayoutFormatter[] {new RemoveLatexCommands()};
    }

    /**
     * If this style was initialized from a file on disk, reload the style
     * if the file has been modified since it was read.
     * @throws Exception
     */
    public void ensureUpToDate() throws Exception {
        if (!isUpToDate())
            reload();
    }

    /**
     * If this style was initialized from a file on disk, reload the style
     * information.
     * @throws Exception
     */
    public void reload() throws Exception {
        if (styleFile != null) {
            styleFileModificationTime = (styleFile).lastModified();
            initialize(new FileReader(styleFile));
        }
    }

    /**
     * If this style was initialized from a file on disk, check whether the file
     * is unmodified since initialization.
     * @return true if the file has not been modified, false otherwise.
     */
    public boolean isUpToDate() {
        if (styleFile != null) {
            return styleFile.lastModified() == styleFileModificationTime;
        }
        else return true;
    }

    private void readFormatFile(Reader in) throws IOException {

        // First read all the contents of the file:
        StringBuffer sb = new StringBuffer();
        int c;
        while ((c = in.read()) != -1) {
            sb.append((char)c);
        }
        // Break into separate lines:
        String[] lines = sb.toString().split("\n");
        int mode = NONE;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            // Check for empty line or comment:
            if ((line.trim().length() == 0) || (line.charAt(0) == '#'))
                continue;
            // Check if we should change mode:
            if (line.equals(NAME_MARK)) {
                mode = NAME;
                continue;
            }
            else if (line.equals(LAYOUT_MRK)) {
                mode = LAYOUT;
                continue;
            }
            else if (line.equals(PROPERTIES_MARK)) {
                mode = PROPERTIES;
                continue;
            }
            else if (line.equals(CITATION_MARK)) {
                mode = CITATION;
                continue;
            }
            else if (line.equals(JOURNALS_MARK)) {
                mode = JOURNALS;
                continue;
            }

            switch (mode) {
                case NAME:
                    if (line.trim().length() > 0)
                        name = line.trim();
                case LAYOUT:
                    handleStructureLine(line);
                    break;
                case PROPERTIES:
                    handlePropertiesLine(line, properties);
                    break;
                case CITATION:
                    handlePropertiesLine(line, citProperties);
                    break;
                case JOURNALS:
                    handleJournalsLine(line);
            }

        }

        // Set validity boolean based on whether we found anything interesting
        // in the file:
        if (mode != NONE)
            valid = true;

    }

    /**
     * After initalizing this style from a file, this method can be used to check
     * whether the file appeared to be a proper style file.
     * @return true if the file could be parsed as a style file, false otherwise.
     */
    public boolean isValid() {
        return valid;
    }


    /**
     * Parse a line providing bibliography structure information for an entry type.
     * @param line The string containing the structure description.
     * @throws IOException
     */
    private void handleStructureLine(String line) throws IOException {
        int index = line.indexOf("=");
        if ((index > 0) && (index < line.length()-1)) {
            String formatString = line.substring(index+1);
            //System.out.println("'"+line.substring(0, index)+"' : '"+formatString+"'");
            boolean setDefault = line.substring(0, index).equals(DEFAULT_MARK);
            String type = line.substring(0, index);
            Short typeS;
            try {
                /*typeS = new Short(Short.parseShort(type));
                OOBibFormatParser parser = new OOBibFormatParser(new StringReader(formatString));
                PropertyValue[][] layout = parser.parse();*/
                Layout layout = new LayoutHelper(new StringReader(formatString)).
                        getLayoutFromText(Globals.FORMATTER_PACKAGE);
                if (setDefault)
                    defaultBibLayout = layout;
                else
                    bibLayout.put(type.toLowerCase(), layout);

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
    }


    /**
     * Parse a line providing a property name and value.
     * @param line The line containing the formatter names.
     * @throws IOException
     */
    private void handlePropertiesLine(String line, HashMap map) throws IOException {
        int index = line.indexOf("=");
        if ((index > 0) && (index < line.length()-1)) {
            String propertyName = line.substring(0, index).trim();
            String value = line.substring(index+1);
            Object toSet = value;
            if (numPattern.matcher(value).matches()) {
                toSet = Integer.parseInt(value);
            }
            else if (value.toLowerCase().equals("true"))
                toSet = Boolean.TRUE;
            else if (value.toLowerCase().equals("false"))
                toSet = Boolean.FALSE;
            map.put(propertyName, toSet);
        }
    }

    /**
     * Parse a line providing a journal name for which this style is valid.
     * @param line
     * @throws IOException
     */
    private void handleJournalsLine(String line) throws IOException {
        if (line.trim().length() > 0)
            journals.add(line.trim());
    }

    public Layout getReferenceFormat(String type) {
        Layout l = bibLayout.get(type.toLowerCase());
        if (l != null)
            return l;
        else
            return defaultBibLayout;
    }

    /**
     * Get the array of elements composing the reference for a given entry type.
     * @param bibType The OO type number.
     * @return The format definition.

    public PropertyValue[][] getReferenceFormat(short bibType) {
        Object o = bibLayout.get(new Short(bibType));
        if (o != null)
            return (PropertyValue[][])o;
        else
            return defaultBibLayout;
    }*/

    /**
     * Format the given field value based on the rules of this bib style.
     * @param field The name of the field.
     * @param content The unformatted field content.
     * @return The formatted field value.
     */
    public String formatField(String field, String content) {
        if (allBeforeFormat != null)
            for (int i = 0; i < allBeforeFormat.length; i++) {
                LayoutFormatter formatter = allBeforeFormat[i];
                content = formatter.format(content);
            }

        Object o = formatters.get(field);
        if (o == null)
            return content;
        LayoutFormatter[] form = (LayoutFormatter[])o;
        for (int i = 0; i < form.length; i++) {
            LayoutFormatter formatter = form[i];
            content = formatter.format(content);
        }

        if (allAfterFormat != null)
            for (int i = 0; i < allAfterFormat.length; i++) {
                LayoutFormatter formatter = allAfterFormat[i];
                content = formatter.format(content);
            }

        return content;
    }

    /**
     * Format a number-based citation marker for the given number.
     * @param number The citation numbers.
     * @return The text for the citation.
     */
    public String getNumCitationMarker(int[] number) {
        // Sort the numbers:
        int[] lNum = Arrays.copyOf(number, number.length);
        Arrays.sort(lNum);
        StringBuilder sb = new StringBuilder((String)citProperties.get("BracketBefore"));
        int combineFrom = -1, written = 0;
        for (int i = 0; i < lNum.length; i++) {
            int i1 = lNum[i];
            if (combineFrom < 0) {
                // Check if next entry is the next in the ref list:
                if ((i < lNum.length-1) && (lNum[i+1] == i1+1))
                    combineFrom = i1;
                else {
                    // Add single entry:
                    if (i>0)
                        sb.append((String)citProperties.get("CitationSeparator"));
                    sb.append(lNum[i]);
                    written++;
                }
            } else {
                // We are building a list of combined entries.
                // Check if it ends here:
                if ((i == lNum.length-1) || (lNum[i+1] != i1+1)) {
                    if (written>0)
                        sb.append((String)citProperties.get("CitationSeparator"));
                    sb.append(combineFrom);
                    sb.append((String)citProperties.get("GroupedNumbersSeparator"));
                    sb.append(i1);
                    combineFrom = -1;
                    written++;
                }
                // If it doesn't end here, just keep iterating.
            }

        }
        sb.append((String)citProperties.get("BracketAfter"));
        return sb.toString();
    }

    /**
     * Format the marker for the in-text citation according to this bib style.
     *
     * @param entry The JabRef BibtexEntry providing the data.
     * @param inParenthesis Signals whether a parenthesized citation or an in-text citation is wanted.
     * @param uniquefier String to add behind the year in case it's needed to separate similar
     *   entries.
     * @return The formatted citation.
     */
    public String getCitationMarker(BibtexEntry entry, BibtexDatabase database, boolean inParenthesis,
                                    String uniquefier) {
        return getCitationMarker(new BibtexEntry[] {entry}, database, inParenthesis, new String[] {uniquefier});
    }

    /**
     * Format the marker for the in-text citation according to this bib style. Uniquefier letters are added as
     * provided by the uniquefiers argument. If successive entries within the citation are uniquefied from each other,
     * this method will perform a grouping of these entries.
     *
     * @param entries The array of JabRef BibtexEntry providing the data.
     * @param inParenthesis Signals whether a parenthesized citation or an in-text citation is wanted.
     * @param uniquefiers Strings to add behind the year for each entry in case it's needed to separate similar
     *   entries.
     * @return The formatted citation.
     */
    public String getCitationMarker(BibtexEntry[] entries, BibtexDatabase database, boolean inParenthesis,
                                    String[] uniquefiers) {

        // Look for groups of uniquefied entries that should be combined in the output.
        // E.g. (Olsen, 2005a, b) should be output instead of (Olsen, 2005a; Olsen, 2005b).
        int piv = -1;
        String tmpMarker = null;
        if (uniquefiers != null) {
            for (int i = 0; i < uniquefiers.length; i++) {
                if ((uniquefiers[i] != null) && (uniquefiers[i].length() > 0)) {
                    if (piv == -1) {
                        piv = i;
                        tmpMarker = getAuthorYearParenthesisMarker(new BibtexEntry[] {entries[i]}, database,
                            (String)citProperties.get("AuthorField"),
                            (String)citProperties.get("YearField"),
                            (Integer)citProperties.get("MaxAuthors"),
                            (String)citProperties.get("AuthorSeparator"),
                            (String)citProperties.get("AuthorLastSeparator"),
                            (String)citProperties.get("EtAlString"),
                            (String)citProperties.get("YearSeparator"),
                            (String)citProperties.get("BracketBefore"),
                            (String)citProperties.get("BracketAfter"),
                            (String)citProperties.get("CitationSeparator"), null);
                        //System.out.println("piv="+piv+" tmpMarker='"+tmpMarker+"'");
                    }
                    else {
                        // See if this entry can go into a group with the previous one:
                        String thisMarker = getAuthorYearParenthesisMarker(new BibtexEntry[] {entries[i]}, database,
                            (String)citProperties.get("AuthorField"),
                            (String)citProperties.get("YearField"),
                            (Integer)citProperties.get("MaxAuthors"),
                            (String)citProperties.get("AuthorSeparator"),
                            (String)citProperties.get("AuthorLastSeparator"),
                            (String)citProperties.get("EtAlString"),
                            (String)citProperties.get("YearSeparator"),
                            (String)citProperties.get("BracketBefore"),
                            (String)citProperties.get("BracketAfter"),
                            (String)citProperties.get("CitationSeparator"), null);
                        //System.out.println("i="+i+" thisMarker='"+thisMarker+"'");
                        if (!thisMarker.equals(tmpMarker)) {
                            // No match. Update piv to exclude the previous entry. But first check if the
                            // previous entry was part of a group:
                            if ((piv > -1) && (i > piv+1)) {
                                // Do the grouping:
                                group(entries, uniquefiers, piv, i-1, (String)citProperties.get("UniquefierSeparator"));
                            }
                            tmpMarker = thisMarker;
                            piv = i;
                        }
                    }
                }
                else {
                    // This entry has no uniquefier.
                    // Check if we just passed a group of more than one entry with uniquefier:
                    if ((piv > -1) && (i > piv+1)) {
                        // Do the grouping:
                        group(entries, uniquefiers, piv, i-1, (String)citProperties.get("UniquefierSeparator"));
                    }

                    piv = -1;
                }

            }
            // Finished with the loop. See if the last entries form a group:
            if (piv >= 0) {
                // Do the grouping:
                group(entries, uniquefiers, piv, uniquefiers.length-1, (String)citProperties.get("UniquefierSeparator"));
            }
        }

        if (inParenthesis)
            return getAuthorYearParenthesisMarker(entries, database,
                    (String)citProperties.get("AuthorField"),
                    (String)citProperties.get("YearField"),
                    (Integer)citProperties.get("MaxAuthors"),
                    (String)citProperties.get("AuthorSeparator"),
                    (String)citProperties.get("AuthorLastSeparator"),
                    (String)citProperties.get("EtAlString"),
                    (String)citProperties.get("YearSeparator"),
                    (String)citProperties.get("BracketBefore"),
                    (String)citProperties.get("BracketAfter"),
                    (String)citProperties.get("CitationSeparator"),
                    uniquefiers);
        else
            return getAuthorYearInTextMarker(entries, database,
                    (String)citProperties.get("AuthorField"),
                    (String)citProperties.get("YearField"),
                    (Integer)citProperties.get("MaxAuthors"),
                    (String)citProperties.get("AuthorSeparator"),
                    (String)citProperties.get("AuthorLastSeparator"),
                    (String)citProperties.get("EtAlString"),
                    (String)citProperties.get("InTextYearSeparator"),
                    (String)citProperties.get("BracketBefore"),
                    (String)citProperties.get("BracketAfter"),
                    (String)citProperties.get("CitationSeparator"),
                    uniquefiers);

    }

    /**
     * Modify entry and uniqiefier arrays to facilitate a grouped presentation of uniqiefied entries.
     * @param entries The entry array.
     * @param uniquefiers The uniquefier array.
     * @param from The first index to group (inclusive)
     * @param to The last index to group (inclusive)
     * @param separator The separator for the uniquefier letters.
     */
    private void group(BibtexEntry[] entries, String[] uniquefiers, int from, int to, String separator) {
        StringBuilder sb = new StringBuilder(uniquefiers[from]);
        for (int i=from+1; i<=to; i++) {
            sb.append(separator);
            sb.append(uniquefiers[i]);
            entries[i] = null;
        }
        uniquefiers[from] = sb.toString();
    }

    /**
     * This method produces (Author, year) style citation strings in many different forms.
     *
     * @param entries The array of BibtexEntry to get fields from.
     * @param authorField The bibtex field providing author names, e.g. "author" or "editor".
     * @param yearField The bibtex field providing the year, e.g. "year".
     * @param maxAuthors The maximum number of authors to write out in full without using etal. Set to
     *              -1 to always write out all authors.
     * @param authorSep The String to add between author names except the last two, e.g. ", ".
     * @param andString The String to add between the two last author names, e.g. " & ".
     * @param etAlString The String to represent authors that are not mentioned, e.g. " et al."
     * @param yearSep The String to separate authors from year, e.g. "; ".
     * @param startBrace The opening parenthesis.
     * @param endBrace The closing parenthesis.
     * @param citationSeparator The String to separate citations from each other.
     * @param uniquifiers Optional parameter to separate similar citations. Elements can be null if not needed.
     * @return The formatted citation.
     */
    public String getAuthorYearParenthesisMarker(BibtexEntry[] entries, BibtexDatabase database,
                                                 String authorField, String yearField,
                                                 int maxAuthors, String authorSep,
                                                 String andString, String etAlString, String yearSep,
                                                 String startBrace, String endBrace, String citationSeparator,
                                                 String[] uniquifiers) {


        StringBuffer sb = new StringBuffer(startBrace);
        for (int j=0; j<entries.length; j++) {

            BibtexEntry entry = entries[j];

            // Check if this entry has been nulled due to grouping with the previous entry(ies):
            if (entry == null)
                continue;

            if (j > 0)
                sb.append(citationSeparator);

            String author = getCitationMarkerField(entry, database, authorField);

            if (author != null) {
                AuthorList al = AuthorList.getAuthorList(author);
                sb.append(getAuthorLastName(al, 0));

                if ((al.size() > 1) && ((al.size() <= maxAuthors) || (maxAuthors < 0))) {
                    int i=1;
                    while (i < al.size()-1) {
                        sb.append(authorSep);
                        sb.append(getAuthorLastName(al, i));
                        i++;
                    }
                    sb.append(andString);
                    sb.append(getAuthorLastName(al, al.size()-1));
                } else if (al.size() > maxAuthors) {
                    sb.append(etAlString);
                }
                sb.append(yearSep);
            }
            String year = getCitationMarkerField(entry, database, yearField);
            if (year != null)
                sb.append(year);
            if ((uniquifiers != null) && (uniquifiers[j] != null))
                sb.append(uniquifiers[j]);
        }
        sb.append(endBrace);
        return sb.toString();

    }

    /**
     * This method produces "Author (year)" style citation strings in many different forms.
     *
     * @param entries The array of BibtexEntry to get fields from.
     * @param authorField The bibtex field providing author names, e.g. "author" or "editor".
     * @param yearField The bibtex field providing the year, e.g. "year".
     * @param maxAuthors The maximum number of authors to write out in full without using etal. Set to
     *              -1 to always write out all authors.
     * @param authorSep The String to add between author names except the last two, e.g. ", ".
     * @param andString The String to add between the two last author names, e.g. " & ".
     * @param etAlString The String to represent authors that are not mentioned, e.g. " et al."
     * @param yearSep The String to separate authors from year, e.g. "; ".
     * @param startBrace The opening parenthesis.
     * @param endBrace The closing parenthesis.
     * @param uniquefiers Optional parameters to separate similar citations. Can be null if not needed.
     * @return The formatted citation.
     */
    public String getAuthorYearInTextMarker(BibtexEntry[] entries, BibtexDatabase database, String authorField,
                                            String yearField, int maxAuthors, String authorSep,
                                            String andString, String etAlString, String yearSep,
                                            String startBrace, String endBrace, String citationSeparator,
                                            String[] uniquefiers) {
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<entries.length; i++) {

            // Check if this entry has been nulled due to grouping with the previous entry(ies):
            if (entries[i] == null)
                continue;

            if (i > 0)
                sb.append(citationSeparator);
            String author = getCitationMarkerField(entries[i], database, authorField);
            if (author != null) {
                AuthorList al = AuthorList.getAuthorList(author);
                if (al.size() > 0)
                    sb.append(getAuthorLastName(al, 0));
                if ((al.size() > 1) && ((al.size() <= maxAuthors) || (maxAuthors < 0))) {
                    int j=1;
                    while (j < al.size()-1) {
                        sb.append(authorSep);
                        sb.append(getAuthorLastName(al, j));
                        j++;
                    }
                    sb.append(andString);
                    sb.append(getAuthorLastName(al, al.size()-1));
                } else if (al.size() > maxAuthors) {
                    sb.append(etAlString);
                }
                sb.append(yearSep);
            }
            sb.append(startBrace);
            String year = getCitationMarkerField(entries[i], database, yearField);
            if (year != null)
                sb.append(year);
            if ((uniquefiers != null) && (uniquefiers[i] != null))
                sb.append(uniquefiers[i]);
            sb.append(endBrace);
        }
        return sb.toString();

    }

    /**
     * This method looks up a field for en entry in a database. Any number of backup fields can be used
     * if the primary field is empty.
     * @param entry The entry.
     * @param database The database the entry belongs to.
     * @param field The field, or succession of fields, to look up. If backup fields are needed, separate
     *   field names by /. E.g. to use "author" with "editor" as backup, specify "author/editor".
     * @return The resolved field content, or an empty string if the field(s) were empty.
     */
    public String getCitationMarkerField(BibtexEntry entry, BibtexDatabase database, String field) {
        String[] fields = field.split("/");
        for (int i = 0; i < fields.length; i++) {
            String s = fields[i];
            String content = BibtexDatabase.getResolvedField(s, entry, database);
            if ((content != null) && (content.trim().length() > 0))
                return content;
        }
        // No luck? Return an empty string:
        return "";
    }

    /**
     * Look up the nth author and return the proper last name for citation markers.
     * @param al The author list.
     * @param number The number of the author to return.
     * @return The author name, or an empty String if inapplicable.
     */
    public String getAuthorLastName(AuthorList al, int number) {
        StringBuilder sb = new StringBuilder();

        if (al.size() > number) {
            AuthorList.Author a = al.getAuthor(number);
            if ((a.getVon() != null) && a.getVon().length() > 0) {
                String von = a.getVon();
                sb.append(von);
                /*sb.append(von.substring(0, 1).toUpperCase());
                if (von.length() > 1)
                    sb.append(von.substring(1));*/
                sb.append(' ');
            }
            sb.append(a.getLast());
        }

        return sb.toString();
    }

    /**
     * Convenience method for checking the property for whether we use number citations or
     * author-year citations.
     * @return true if we use numbered citations, false otherwise.
     */
    public boolean isNumberEntries() {
        return (Boolean)getProperty("IsNumberEntries");
    }

    /**
     * Convenience method for checking the property for whether we sort the bibliography
     * according to their order of appearance in the text.
     * @return true to sort by appearance, false to sort alphabetically.
     */
    public boolean isSortByPosition() {
        return (Boolean)getProperty("IsSortByPosition");
    }

    /**
     * Convenience method for checking whether citation markers should be italicised.
     * Will only be relevant if isFormatCitations() returns true.
     * @return true to indicate that citations should be in italics.
     */
    public boolean isItalicCitations() {
        return (Boolean)citProperties.get("ItalicCitations");
    }

    /**
     * Convenience method for checking whether citation markers should be bold.
     * Will only be relevant if isFormatCitations() returns true.
     * @return true to indicate that citations should be in bold.
     */
    public boolean isBoldCitations() {
        return (Boolean)citProperties.get("BoldCitations");
    }

    /**
     * Convenience method for checking whether citation markers formatted
     * according to the results of the isItalicCitations() and
     * isBoldCitations() methods.
     * @return true to indicate that citations should be in italics.
     */
    public boolean isFormatCitations() {
        return (Boolean)citProperties.get("FormatCitations");
    }

    /**
     * Get a style property.
     * @param name The property name.
     * @return The property value, or null if it doesn't exist.
     */
    public Object getProperty(String name) {
        return properties.get(name);
    }

    public int compareTo(Object o) {
        OOBibStyle other = (OOBibStyle)o;
        return getName().compareTo(other.getName());
    }

    public boolean equals(Object o) {
        return styleFile.equals(((OOBibStyle)o).styleFile);
    }
}
