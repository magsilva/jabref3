<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>OpenOffice plugin for JabRef</title>
  <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="main">
    <div id="container">

      <?php include("navigation.php"); ?>

      <h1>OpenOffice plugin for JabRef</h1>

      <h2>Introduction</h2>

      <p>This plugin offers an interface for inserting citations and formatting a Bibliography in an OpenOffice writer document from JabRef.</p>

      <h2>How to use the plugin</h2>

      <p>The plugin can be used with JabRef 2.4 or newer. If your JabRef version doesn't have a plugin manager (versions 2.4.x), you need to put the plugin jar file in a directory named <code>plugins</code> below the directory where the JabRef jar file is installed (typically under <code>C:\Program files\Jabref 2.x</code> if you are running under Windows). The plugin should be loaded next time you start JabRef, and an item named <b>OpenOffice.org panel</b> should appear in JabRef's <b>Plugins</b> menu.</p>

      <p>The plugin should work with OpenOffice versions 2.4.x and 3.x, provided it is installed with Java support (this is usually the case on Windows, while in some Linux distributions you need to install a separate package named <code>openoffice.org-java-common</code> or something similar).</p>

      <h2>Updates</h2>
      <ul>
	<li>2010-05-11: Version 0.7.4: Added option to sort alphabetically for citations with multiple entries.</li>
        <li>2010-03-04: Version 0.7.3: Added support for &lt;smallcaps&gt; tag to indicate small caps in reference list. Several bug fixes.</li>
        <li>2010-02-02: Version 0.7.2: Added MaxAuthorsFirst property to override MaxAuthors the first time each citation appears.</li>
        <li>2009-10-07: Version 0.7.1: Several important bug fixes.</li>
        <li>2009-08-26: Version 0.7: BibTeX fields are now preprocessed to handle LaTeX \textit and \textbf commands and character sequences. <b>NOTE: it is no longer necessary to run FormatChars on fields.</b></li>
        <li>2009-08-23: Version 0.6: Improved handling of undefined BibTeX keys. Added option to not sync automatically when adding citation.</li>
        <li>2009-08-05: Version 0.5: Fixed connection problem on Mac. Fixed bug in merge function.</li>
        <li>2009-06-03: Version 0.4: Added support for superscript/subscript tags in reference list, subscripted citations and different brackets for numbering in the reference list.</li>
        <li>2009-05-17: Version 0.3: Added MinimumGroupingCount property. Some GUI changes.</li>
        <li>2009-04-02: Version 0.2.1: Fixed bug in sorting of reference list.</li>
        <li>2009-03-01: Version 0.2: Better sorting of citations in captions, tables and footnotes.</li>
        <li>2009-02-25: Version 0.1.9: Added support for bold/italic citation markers, and for citations in table cells and footnotes.</li>
        <li>2008-12-21: Version 0.1.2: Added invisible citations. Merged citations are now sorted.</li>
        <li>2008-11-19: Version 0.1.1: Improved handling of OpenOffice shutdown and reconnect.</li>
        <li>2008-10-25: Version 0.1: User interface improvements. Can now select which Writer document to work with.</li>
        <li>2008-10-19: Version 0.0.9: Enabled connection to OpenOffice.org 3. Streamlined connection process.</li>
        <li>2008-09-03: Version 0.0.8: No major changes, but packaged with JabRef 2.4.</li>
        <li>2008-02-20: Version 0.0.7: New interface for style selection. Styles can now specify paragraph format.</li>
        <li>2008-02-13: Version 0.0.6: Sorting and grouping of number citation markers.</li>
        <li>2008-02-06: Version 0.0.5: Modified style file format. Fixed bug in handling names with elements like "van der".</li>
        <li>2008-01-09: Version 0.0.4: Style file is now automatically reloaded if modified.</li>
        <li>2007-12-17: Version 0.0.3: From this version, we bypass OO's built-in bibliographic system.</li>
        <li>2007-12-04: Version 0.0.2</li>
        <li>2007-12-01: Version 0.0.1</li>
      </ul>

      <h2>Downloads</h2>

      <p><a href="plugins/net.sf.jabref.oo.ooplugin-0.7.4.jar">The plugin.</a></p>

      <p><a href="plugins/JabRef-oo-0.7.4-src.zip">Plugin source code.</a> The source code tree includes four OpenOffice.org jars and JabRef 2.6. The plugin is built using an included Ant build file.</p>

      <p><a href="plugins/example_style_file.jstyle">Example style file</a></p>

      <p>The plugin is distributed under the terms of the GNU <a href="http://www.gnu.org/licenses/old-licenses/gpl-2.0.html">General Public License</a>, version 2 or later.</p>

      <h2>Using the OpenOffice interface</h2>

      <p>To communicate with OpenOffice, the OO plugin must first connect to a running OpenOffice instance. You need to start OpenOffice and enter your document before connecting from JabRef. The plugin needs to know the location of your OpenOffice executable (<b>soffice.exe</b> on Windows, and <b>soffice</b> on other platforms), and the directory where several OpenOffice jar files reside. If you connect by clicking the <b>Connect</b> button, the plugin will try to automatically determine these locations. If this does not work, you need to connect using the <b>Manual connect</b> button, which will open a window asking you for the needed locations.</p>

      <p>After the connection has been established, you can insert citations by selecting one or more entries in JabRef and using the <b>Push to OpenOffice</b> button in the dropdown menu of JabRef's toolbar, or by using the appropriate button in the OpenOffice plugin panel in the side pane. This will insert citations for the selected entries at the current cursor position in the OpenOffice document, and update the bibliography to contain the full reference.</p>

      <p><b>Note:</b> JabRef does not use OpenOffice's built-in bibliography system, because of the limitations of that system. A document containing citations inserted from JabRef will not generally be compatible with other reference managers such as Bibus and Zotero.</p>

      <p>Two different types of citations can be inserted - either a citation in parenthesis, "(Author 2007)", or an in-text citation, "Author (2007)". This distinction is only meaningful if author-year citations are used instead of numbered citations, but the distinction will be preserved if you switch between the two styles.</p>

      <p>If you modify entries in JabRef after inserting their citations into OpenOffice, you will need to synchronize the bibliography. The <b>Sync OO bibliography</b> button will update all entries of the bibliography, provided their BibTeX keys have not been altered (JabRef encodes the BibTeX key into the reference name for each citation to keep track of which BibTeX key the original JabRef entry has).</p>

      <h3>The style file</h3>

      <p>You need to select a style file before connecting to OpenOffice - an external file which is selected using a standard file dialog. The style file defines the format of citations and the format of the bibliography. You can use standard JabRef export formatters to process entry fields before they are sent to OpenOffice. Through the style file, the intention is to give as much flexibility in citation styles as possible.</p>

      <p>Here is an example style file:</p>
<pre>
NAME
Example style file for JabRef-oo plugin.

JOURNALS
Journal name 1
Journal name 2

PROPERTIES
Title=References
IsSortByPosition=false
IsNumberEntries=false
ReferenceParagraphFormat=Default
ReferenceHeaderParagraphFormat=Heading 1

CITATION
AuthorField=author/editor
YearField=year
MaxAuthors=3
MaxAuthorsFirst=3
AuthorSeparator=,
AuthorLastSeparator= &amp;
EtAlString= et al.
YearSeparator=
InTextYearSeparator=
BracketBefore=[
BracketAfter=]
BracketBeforeInList=[
BracketAfterInList=]
CitationSeparator=;
UniquefierSeparator=,
GroupedNumbersSeparator=-
MinimumGroupingCount=3
FormatCitations=false
ItalicCitations=false
BoldCitations=false
SuperscriptCitations=false
SubscriptCitations=false
MultiCiteChronological=false

LAYOUT
article=\format[AuthorLastFirst,AuthorAbbreviator,AuthorAndsReplacer]{\author}
(&lt;b&gt;\year\uniq&lt;/b&gt;). &lt;i&gt;\title&lt;/i&gt;, \journal \volume\begin{pages} :
\format[FormatPagesForHTML]{\pages}\end{pages}.

book=\format[AuthorLastFirst,AuthorAbbreviator,AuthorAndsReplacer]{\author}\begin{editor}
\format[AuthorLastFirst,AuthorAbbreviator,AuthorAndsReplacer]{\editor} (Ed.)\end{editor},
&lt;b&gt;\year\uniq&lt;/b&gt;. &lt;i&gt;\title&lt;/i&gt;. \publisher, \address.

incollection=\format[AuthorLastFirst,AuthorAbbreviator,AuthorAndsReplacer]{\author}
(&lt;b&gt;\year\uniq&lt;/b&gt;). &lt;i&gt;\title&lt;/i&gt;. In: \format[AuthorLastFirst,
AuthorAbbreviator,AuthorAndsReplacer]{\editor} (Ed.), &lt;i&gt;\booktitle&lt;/i&gt;, \publisher.

inbook=\format[AuthorLastFirst,AuthorAbbreviator,AuthorAndsReplacer]{\author}
(&lt;b&gt;\year\uniq&lt;/b&gt;). &lt;i&gt;\chapter&lt;/i&gt;. In: \format[AuthorLastFirst,
AuthorAbbreviator,AuthorAndsReplacer]{\editor} (Ed.), &lt;i&gt;\title&lt;/i&gt;, \publisher.

phdthesis=\format[AuthorLastFirst,AuthorAbbreviator,AuthorAndsReplacer]{\author}
(&lt;b&gt;\year\uniq&lt;/b&gt;). &lt;i&gt;\title&lt;/i&gt;, \school.

default=\format[AuthorLastFirst,AuthorAbbreviator,AuthorAndsReplacer]{\author}
(&lt;b&gt;\year\uniq&lt;/b&gt;). &lt;i&gt;\title&lt;/i&gt;, \journal \volume\begin{pages} :
\format[FormatPagesForHTML]{\pages}\end{pages}.

</pre>

        <p>(Note that the layout for each entry type must be constrained to a single line in the style file - above, the lines are broken up to improve readability.)</p>

        <p>The <b>PROPERTIES</b> section describes global properties for the bibliography:</p>
        <ul>
            <li><code>Title</code>: determines the header text for the bibliography.</li>
            <li><code>IsSortByPosition</code>: determines how the bibliography is sorted. If true, the entries
                will be sorted according to the order in which they are cited. If false, the entries will be
                sorted alphabetically by authors.</li>
            <li><code>IsNumberEntries</code>: determines the type of citations to use. If true, number
                citations will be used. The citation numbers will be included in the bibliography as well.
                If false, author-year citations will be used.</li>
        </ul>

        <p>The <b>CITATION</b> section describes the format of the citation markers inserted into the text.
        If numbered entries are used, only the <code>BracketBefore</code> and <code>BracketAfter</code> properties
        are relevant - they define which characters the citation number is wrapped in. The citation is composed
        as follows:<br />
            [BracketBefore][Number][BracketAfter]<br />
        where [Number] is the number of the citation, determined according to the ordering of the bibliography and/or
        the position of the citation in the text. If a citation refers to several entries, these will be separated
        by the string given in the property <code>CitationSeparator</code> (for instance, if <code>CitationSeparator</code>=;,
        the citation could look like <code>[2;4;6]</code>). If two or more of the entries have a series of consecutive
        numbers, the numbers can be grouped (for instance <code>[2-4]</code> for 2, 3 and 4 or <code>[2;5-7]</code> for
        2, 5, 6 and 7). The property <code>GroupedNumbersSeparator</code> (default <code>-</code>) determines which string separates the first and last
        of the grouped numbers. The integer property <code>MinimumGroupingCount</code> (default 3) determines what number of
        consecutive numbers are required before entries are grouped. If <code>MinimumGroupingCount</code>=3, the numbers
        2 and 3 will not be grouped, while 2, 3, 4 will be. If <code>MinimumGroupingCount</code>=0, no grouping will be
        done regardless of the number of consecutive numbers.
        </p>
        <p>If numbered entries are not used, author-year citations will be created based on the citation properties.
        A parenthesis citation is composed as follows:<br />
            [BracketBefore][Author][YearSeparator][Year][BracketAfter]<br />
        where [Author] is the result of looking up the field or fields given in the <code>AuthorField</code> property,
        and formatting a list of authors. The list can contain up to <code>MaxAuthors</code> names - if more are present,
        the list will be composed as the first author plus the text specified in the property <code>EtAlString</code>.
	If the property <code>MaxAuthorsFirst</code> is given, it overrides <code>MaxAuthors</code> the first time each
        citation appears in the text.</p>
	<p>If several, slash-separated, fields are given in the <code>AuthorField</code> property, they will be looked up
        successively if the first field is empty for the given BibTeX entry. In the example above, the "author" field will
        be used, but if empty, the "editor" field will be used as a backup.</p>
        <p>The names in the author list will be separated by the text given by the <code>AuthorSeparator</code>
        property, except for the last two names, which will be separated by the text given by <code>AuthorLastSeparator</code>.
        </p>
        <p>[Year] is the result of looking up the field or fields given in the [YearField] property.</p>
        <p>An in-text citation is composed as follows:<br />
            [Author][InTextYearSeparator][BracketBefore][Year][BracketAfter]<br />
        where [Author] and [Year] are resolved in exactly the same way as for the parenthesis citations.
        </p>
        <p>If two different cited sources have the same authors and publication year, and author-year citations are used,
        their markers will need modification in order to be distinguishable. This is done automatically by appending a
        letter after the year for
        each of the publications; 'a' for the first cited reference, 'b' for the next, and so on.
        For instance, if the author "Olsen" has two cited papers from 2005, the citation markers will be modified to
        <code>(Olsen, 2005a)</code> and <code>(Olsen, 2005b)</code>. In the bibliography
        layout, the placement of the "uniquefier" letter is indicated explicitly by inserting the virtual field
        <code>uniq</code>.</p>
        <p>If several entries that have been "uniquefied" are cited together, they will be grouped in the citation
        marker. For instance, of the two entries in the example above are cited together, the citation marker will
        be <code>(Olsen, 2005a, b)</code> rather than <code>Olsen, 2005a; Olsen, 2005b)</code>. The grouped uniquefier
        letters (a and b in our example) will be separated by the string specified by the <code>UniquefierSeparator</code>
        property.
        </p>
	<p>Author-year citations referring more than one entry will by default be sorted chronologically. If you wish them
 	to be sorted alphabetically, the citation property <code>MultiCiteChronological</code> should be set to <code>false.</code>.</p>
        <p>The property <code>FormatCitations</code> determines whether the citation markers should be formatted with
        regards to italics, boldness, superscript and subscript. If <code>FormatCitations</code> is false, no such formatting
        will be done. If true, the citations will be italicized or not depending on the <code>ItalicCitations</code> property, set to bold
        or not depending on the <code>BoldCitations</code> property, and similar for the <code>SuperscriptCitations</code> and
        <code>SubscriptCitations</code> properties.</p>
        <p>The <b>LAYOUT</b> section describes how the bibliography entry for each entry type in JabRef
        should appear. Each line should start with either the name of a BibTeX entry type, or the word
        <code>default</code>, followed by a '='. The <code>default</code> layout will be used for all
        entry types for which an explicit layout hasn't been given.</p>
        <p>The remainder of each line defines the layout, with normal text and spaces appearing literally
        in the bibliography entry. Information from the BibTeX entry is inserted by adding <code>\field</code> markers
        with the appropriate field name (e.g. <code>\author</code> for inserting the author names). Formatting
        information for the field can be included here, following JabRef's standard export layout syntax.
        Refer to <a href="http://jabref.sourceforge.net/help/CustomExports.php">JabRef's documentation on custom export filters</a>
        for more information about which formatters are available.</p>
        <p>If author-year citations are used, you have to explicitly specify the position of the "uniquefier" letter
        that is added to distinguish similar-looking citations. This is done by including a marker for the virtual field
        <code>uniq</code>, typically right after the year (as shown in the example style file). The <code>uniq</code>
        field is automatically set correctly for each entry before its reference text is laid out.
        </p>
        <p>To indicate formatting in the bibliography, you can use the HTML-like tag pairs &lt;b&gt; &lt;/b&gt;,
         &lt;i&gt; &lt;/i&gt;,  &lt;sup&gt; &lt;/sup&gt; and  &lt;sub&gt; &lt;/sub&gt; to specify bold text,
         italic text, superscript and subscript, respectively.</p>
        <p>If you are using numbered citations, the number for each entry will be automatically inserted at the start
        of each entry in the reference list. By default, the numbers will be enclosed in the same brackets defined for
        citations. The optional citation properties <code>BracketBeforeInList</code> and
        <code>BracketAfterInList</code> override <code>BracketBefore</code> and <code>BracketAfter</code> if set. These
        can be used if you want different types of brackets (or no brackets) in the reference list. Note that these need
        not be brackets as such - they can be any combination of characters.</p>

      <h2>Known issues</h2>

      <ul>
        <li>When running with JabRef versions older than 2.6, institutional authors wrapped in braces, e.g. <code>{World Bank}</code>, are handled as if no braces were added. This happens because the OO-specific formatter, which removes the braces, can only be run after (instead of before) all other formatters in JabRef 2.6 or newer.</li>
        <li>Make sure to save your Writer document in OpenDocument format (odt). Saving to Word format will lose your reference marks.</li>
        <li>There is currently no support for footnote based citations.</li>
        <li>The cursor may be poorly positioned after inserting a citation.</li>
        <li>Copy-pasting the example style file directly from this page can give an unparseable file. To avoid this, instead download the example file from the link in the download section.</li>
      </ul>

      <h2>Contact</h2>

      <p>If you have tested this plugin, and want to give your feedback, or if you want to contribute to the development of the plugin, please contact me at the e-mail address mortenalver at users.sourceforge.net.</p>

    </div>
    <?php include("footer.php"); ?>
  </div>

</body>
</html>