<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>JabRef reference manager</title>
  <link href='css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">

    <?php include("navigation.php"); ?>

    <div id="rightpanel">
      <h3>Version</h3>

      <ul class="history_column">
	<li class="single_item"><a href="#v2.5b">2.5b</a></li>
	<li class="single_item"><a href="#v2.4.2">2.4.2</a></li>
	<li class="single_item"><a href="#v2.4.1">2.4.1</a></li>
	<li class="single_item"><a href="#v2.4">2.4</a></li>
	<li class="single_item"><a href="#v2.4b">2.4b</a></li>
	<li class="single_item"><a href="#v2.3.1">2.3.1</a></li>
	<li class="single_item"><a href="#v2.3">2.3</a></li>
	<li class="single_item"><a href="#v2.3b3">2.3b3</a></li>
	<li class="single_item"><a href="#v2.3b2">2.3b2</a></li>
	<li class="single_item"><a href="#v2.3b">2.3b</a></li>
	<li class="single_item"><a href="#v2.2">2.2</a></li>
	<li class="single_item"><a href="#v2.2b2">2.2b2</a></li>
	<li class="single_item"><a href="#v2.2b">2.2b</a></li>
	<li class="single_item"><a href="#v2.1">2.1</a></li>
	<li class="single_item"><a href="#v2.1b2">2.1b2</a></li>
	<li class="single_item"><a href="#v2.1b">2.1b</a></li>
	<li class="single_item"><a href="#v2.0.1">2.0.1</a></li>
	<li class="single_item"><a href="#v2.0">2.0</a></li>
	<li class="single_item"><a href="#v2.0b2">2.0b2</a></li>
	<li class="single_item"><a href="#v2.0b">2.0b</a></li>
	<li class="single_item"><a href="#v1.8.1">1.8.1</a></li>
	<li class="single_item"><a href="#v1.8">1.8</a></li>
	<li class="single_item"><a href="#v1.8b2">1.8b2</a></li>
	<li class="single_item"><a href="#v1.8b">1.8b</a></li>
	<li class="single_item"><a href="#v1.7.1">1.7.1</a></li>
	<li class="single_item"><a href="#v1.7">1.7</a></li>
	<li class="single_item"><a href="#v1.7b2">1.7 beta2</a></li>
	<li class="single_item"><a href="#v1.7b">1.7 beta</a></li>
	<li class="single_item"><a href="#v1.6">1.6</a></li>
	<li class="single_item"><a href="#v1.6beta">1.6 beta</a></li>
	<li class="single_item"><a href="#v1.55">1.55</a></li>
	<li class="single_item"><a href="#v1.5">1.5</a></li>
	<li class="single_item"><a href="#v1.4">1.4</a></li>
	<li class="single_item"><a href="#v1.3.1">1.3.1</a></li>
	<li class="single_item"><a href="#v1.3">1.3</a></li>
	<li class="single_item"><a href="#v1.2">1.2</a></li>
	<li class="single_item"><a href="#v1.19">1.19</a></li>
	<li class="single_item"><a href="#v1.1">1.1</a></li>
	<li class="single_item"><a href="#v1.0">1.0</a></li>
      </ul>
    </div>

    <div id="main">

      <h2>Revision history</h2>

      <div class="aversion" id="v2.5b">
        <h3>Version 2.5beta (April 21st, 2009)</h3>

        <div class="version_desc">
          <ul>
            <li> Added Simplified Chinese translation.</li>
            <li> Added simple plugin manager.</li>
            <li> Added ~/.jabref/plugins as user-specific plugin directory.</li>
            <li> Added \r marker to WrapFileLinks formatter that outputs file links without expanding relative links.</li>
            <li> Added [authorAlpha] key marker that formats authors according to the &quot;alpha&quot; BibTeX style. Patch submitted by Oliver Kopp.</li>
            <li> Table sort order set by clicking and Ctrl-clicking table columns is now immediately set as default sort order in preferences.</li>
            <li> Changed LyX pipe setting so it works whether &quot;.in&quot; is included or omitted.</li>
            <li> Modified ISI importer so the words &quot;of&quot;, &quot;and&quot; and &quot;the&quot; will not be capitalized in the title, journal or publisher fields.</li>
            <li> When adding new local file link, browse dialog now appears immediately when opening file link editor, saving one mouse click.</li>
            <li> Added &quot;Remove all broken links&quot; option in the resolver dialog for broken links when synchronizing file links.</li>
            <li> Added rename option to Set/clear field dialog, to move contents from one field to another.</li>
            <li> Added Back and Forward actions, for switching between recently edited BibTeX entries.</li>
            <li> Added option under &quot;Entry table&quot; to designate fields as numeric for sorting purposes.</li>
            <li> Added possibility for custom export filters to define their own name formatters. This is done by adding a file named &quot;&lt;filtername&gt;.formatters&quot;. This file defines one formatter on each line, with each line containing the name of the formatter and the formatter definition, separated by a colon (:).</li>
            <li> Added menu items for increasing/decreasing table font size, with shortcut keys Ctrl-plus and Ctrl-minus.</li>
            <li> Added options to automatically mark entries imported into an existing database, and to unmark previously marked entries when importing.</li>
            <li> Added &quot;:(x)&quot; modifier to key generator, specifying that the arbitrary string x should be used as a fallback value if the value returned by the field marker is empty.</li>
            <li> Added &quot;:upper&quot; modifier to key generator, to force uppercase for a field marker.</li>
            <li> Added buttons in External programs tab in Preferences for modifying settings for &quot;Push to&quot;-features, and removed obsolete fields.</li>
            <li> Added support for DOI field in Endnote importer.</li>
            <li> Added support for language and publication status fields in Medline import (publication status stored in &quot;medline-pst&quot; field).</li>
            <li> Enabled cookie handling for downloading full-text articles.</li>
            <li> Improved handling of invalid BibTeX keys containing white space. Parser will now try to piece together the key and avoid disturbing the continued parsing. Patch submitted by Stephan Lau.</li>
            <li> Cosmetic change to the entry type label to the left in the entry editor.</li>
            <li> Changed name handling so a single-entry name without a capital initial letter, such as &quot;unknown&quot;, will be treated as a solitary last name rather than a von particle.</li>
            <li> Changed table selection coloring so entries that are grayed out or marked can be distinguished from normal entries when selected.</li>
            <li> Changed handling of &quot;affiliation&quot; in Medline import - now makes sure to escape # characters before storing.</li>
            <li> Modified ACM portal fetcher due to web site changes.</li>
            <li> Improvements to IEEEXplore fetcher - better handling of month and page fields.</li>
            <li> Changed behaviour of source panel when an entry contains imbalanced # characters - the panel can now show the entry in its invalid form, allowing the user to fix the problem.</li>
            <li> Improved handling of PDF files without XMP metadata - other metadata will now be retained. Patch submitted by Felix Langner.</li>
            <li> Fixed bug in parsing file field - double spaces in file names would be reduced to single spaces, breaking the file link. Fix submitted by Uwe Kuehn.</li>
            <li> Fixed NullPointerException when downloading external file and file directory is undefined.</li>
            <li> Fixed bug in HTMLConverter.</li>
            <li> Fixed NullPointerException in key generator for incomplete names.</li>
            <li> Fixed bug in removing custom export filters.</li>
            <li> Fixed bug 2225371: restart is no longer required after adding a new custom export filter. </li>
            <li> Fixed bug in &quot;Move/rename file&quot; feature in file field editor with regard to undefined file directory.</li>
            <li> Fixed bug in Ris importer.</li>
            <li> Fixed NullPointerException in Endnote importer.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v2.4.2">
        <h3>Version 2.4.2 (November 1st, 2008)</h3>

        <div class="version_desc">
          <ul>
            <li>Added missing layout formatters FirstPage and LastPage.</li>
            <li>Fixed a bug regarding ParamLayoutFormatter loaded from plugin.</li>
            <li>Fixed crash during initalization of journal abbreviation list.</li>
            <li>Added option to have JabRef search for external file when &quot;Open file&quot; function is chosen for an entry without any linked files. This is similar to what was always done in JabRef 2.3.1 and earlier.</li>
            <li>Improved regular expression file search, so the regular expression can contain field markers as used for BibTeX key generation in addition to just regular fields. It is no longer possible to call arbitrary layout formatters, but the modifiers &quot;upper&quot;, &quot;lower&quot; and &quot;abbr&quot; (for case conversion and abbreviation) can be used.</li>
            <li>Ris importer now imports PB as &quot;school&quot; instead of &quot;publisher&quot; for THES entries.</li>
            <li>Fixed bug 2157664: Current edit is now treated as an undoable edit in itself.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v2.4.1">
        <h3>Version 2.4.1 (October 8th, 2008)</h3>

        <div class="version_desc">
          <ul>
            <li>Fixed bug: layout formatter arguments were not set when using a ParamLayoutFormatter loaded from plugin.</li>
            <li>Fixed bug: when abbreviating first names, for authors with first names connected with &quot;-&quot;, only the first letter is shown.</li>
            <li>Enhanced ExportFormatTemplate plugins with an optional property &quot;encoding&quot; which overrides the default encoding with the given one.</li>
            <li>Fixed menu colors under Windows Vista.</li>
            <li>Fixed bug 2137771: Missing file extension when downloading.</li>
            <li>Fixed bug 2105329: Ensure that newly added entry is visible in table.</li>
            <li>Fixed bug 1908222: Preference &quot;Fit table horizontally on screen&quot; doesn't work</li>
            <li>Fixed bug 2119059: Handling of the A1 tag in the RIS format.</li>
            <li>Added missing help file for ACM digital library fetcher.</li>
            <li>Added option for whether to use IEEE LaTeX journal abbreviation list.</li>
            <li>Added tooltip to menu items in push-to-application popup menu.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v2.4">
        <h3>Version 2.4 (September 1st, 2008)</h3>

        <div class="version_desc">
          <ul>
            <li>Added mappings for some special characters that need to be sanitized when generating BibTeX keys.</li>
            <li>Added ACM Digital Library fetcher by Aaron Chen.</li>
            <li>Added new entry types (conference, patent, standard, electronic).</li>
            <li>Improvements to IEEEXplore fetcher.</li>
            <li>Added explanatory text to indicate that &quot;ps&quot; and &quot;pdf&quot; files are legacy features.</li>
            <li>Fixed unexpected behaviour of &quot;Open PDF or PS&quot; menu item - now looks in &quot;file&quot; field, and does not launch search for external files.</li>
            <li>Fixed bug 1827568: &quot;Save database&quot; might not store current edit in entry editor.</li>
            <li>Fixed bug 2027944: updating custom export definitions requires restart.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v2.4b">
        <h3>Version 2.4beta (March 12th, 2008)</h3>

        <div class="version_desc">
          <ul>
            <li>Added "protection" flag in Database properties. When this flag is set, JabRef will refuse to save the database when the file has been externally modified, until the changes have been reviewed and partly or completely accepted.</li>
            <li>Fixed bug where the external update notification in the side pane would remain even if the referred database was closed.</li>
            <li>Added two new search modes - display search results in a dialog, and global search.</li>
            <li>Support for fetching from the command line using --fetch (contributed by Jan F. Boldt and David Kaltschmidt).</li>
            <li>Support for fetching from Spires (contributed by Fedor Bezrukov).</li>
            <li>Support for fetching from JSTOR (contributed by Tobias Langner, Juliane Doege, Sebastian de Hoog and Christoph Jacob)</li>
            <li>Added context menu for file list editor with options to move/rename linked file as well as to automatically move the file to file directory and optionally rename after BibTeX key.</li>
            <li>JabRef can now be extended by plugins (using the Java Plugin Framework JPF as the underlying technology). Currently extension points exist for:
              <ul>
                <li>ImportFormat</li>
                <li>ExportFormat based on Templates</li>
                <li>ExportFormat based on IExportFormat (contributed by Kariem Hussein)</li>
                <li>LayoutFormatter</li>
                <li>EntryFetcher</li>
                <li>PushToApplication</li>
              </ul>
               JabRef uses JPFCodeGenerator (which was written just for JabRef) to generate helper classes for making JPF easier. Current version used: 0.4 http://forge.spline.inf.fu-berlin.de/projects/jpfcodegen/
            </li>
            <li>Print warnings if insufficient Java version is used or if JRE is not from Sun.</li>
            <li>Memory Stick Mode: JabRef automatically loads configuration settings from jabref.xml and also writes them there if enabled.</li>
            <li>Improved handling of crossrefs. Fields are now resolved in preview and export.</li>
            <li>Updated dependencies, now using: JempBox-0.2</li>
            <li>Improved handling of ArXiv URIs.</li>
            <li>Changed default preference value: now using import inspection dialog also when just a single entry is imported.</li>
            <li>[ 1620792 ] Fixed: JabRef randomly hangs during Medline fetch</li>
            <li>[ 1738920 ] Fixed: Windows Position in Multi-Monitor environment</li>
            <li>[ 1795355 ] Fixed: LatexFieldFormatter omits "{" on beginning of optional field</li>
            <li>[ 1297576 ] New feature: Printing of entry preview (use right-click menu)</li>
            <li>[ 1717849 ] Fixed: Bug in aux import (contributed by Kai Eckert)</li>
            <li>[ 1749613 ] Fixed: About translation</li>
            <li>[ 1709449 ] Fixed: Clicking a DOI from context menu fails</li>
            <li>[ 1869331 ] Fixed: Uninstall after silent install removes Windows start menu</li>
            <li>[ 1723219 ] Fixed: Strange message (LyX) while installing 2.3</li>
            <li>Added handling of unknown file types when synchronizing the file field.</li>
            <li>Changed the way customized external file types are stored. Types are now stored in a way analogous to a diff from the default types. This allows default types added in later versions to appear immediately, even if the user has customized the list.</li>
            <li>Moved file preferences from General tab to new File tab.</li>
            <li>Reduced horizontal size of preferences dialog.</li>
            <li>Fixed handling of quotes when using the "abbr" modifier for key generator (Debian bug #448917).</li>
            <li>Download file procedure now strips query string in order to find correct file extension (Debian bug #448027).    </li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v2.3.1">
        <h3>Version 2.3.1 (November 29th, 2007)</h3>

        <div class="version_desc">
          <ul>
            <li>Removed default applications for file types under Mac OS X, and made use of the standard "/usr/bin/open &lt;filename&gt;" way to open files except when an application name is explicitly set.</li>
            <li>[ 1620792 ] Fixed: JabRef randomly hangs during Medline fetch</li>
          </ul>
        </div>
      </div>


      <div class="aversion" id="v2.3">
        <h3>Version 2.3 (November 8th, 2007)</h3>

        <div class="version_desc">
          <ul>
            <li>Added handling of unknown file types when synchronizing the file field.</li>
            <li>Changed the way customized external file types are stored. Types are now stored in a way analogous to a diff from the default types. This allows default types added in later versions to appear immediately, even if the user has customized the list.</li>
            <li>Moved file preferences from General tab to new File tab.</li>
            <li>Reduced horizontal size of preferences dialog.</li>
            <li>Fixed handling of quotes when using the "abbr" modifier for key generator (Debian bug #448917).</li>
            <li>Download file procedure now strips query string in order to find correct file extension (Debian bug #448027).</li>
          </ul>
        </div>
      </div>


      <div class="aversion" id="v2.3b3">
        <h3>Version 2.3beta3 (October 6th, 2007)</h3>

        <div class="version_desc">
          <ul>
	    <li>Export formats that output the character encoding now use common names for encodings instead of Java-specific names.</li>
	    <li>Added "Open" button in external link dialog box to test or use the link.</li>
	    <li>Added formatter WrapFileLinks which iterates over file links, producing a formatted string for each containing any desired information about the file link.</li>
	    <li>Applied Fedor Bezrukov patch (setting User-Agent in URLDownload to solve ArXiv problem).</li>
	    <li>Applied Aaron Chen's patch for fixing bugs in IEEExplore fetcher.</li>
	    <li>Applied Edward Valeev's patch for handling article numbers replacing pages in Refer/Endnote import.</li>
	    <li>Added toolbar button to entry editor for writing XMP-metadata.</li>
	    <li>Added paste and drag &amp; drop support to file list editor.</li>
	    <li>Added "authorLast" and "editorLast" markers for using last author's last name in BibTeX keys.</li>
	    <li>Added support for file field in Write XMP action.</li>
	    <li>Numeric fields (year, volume, number, pmid, citeseercitationcount) are now sorted as numbers when possible.</li>
	    <li>Improvements to the Ovid import filter.</li>
	    <li>Modified AuthorLastFirstAbbreviator and AuthorAbbreviator to accept names in both last-first and first-first format, but always return in last-first format. These two formatters are identical.</li>
	    <li>[ 1648789 ] Fixed: Problem on writing XMP when option to leave out some fields was active.</li>
	    <li>[ 1561990 ] Fixed: Exporting to WinEdt - apostrophe.</li>
	    <li>Fixed bug in entry editor: source panel edits were not properly stored when clicking a different entry in the main table.</li>
	    <li>Fixed problem with "Synchronize file links" not honouring database specific file directory.</li>
	    <li>Fixed problem with file type selection in external file link editor not being up-to-date.</li>
	    <li>Fixed problem with wrong enable/disable behaviour of Clear search button when switching between tabs.</li>
	    <li>Fixed argument parsing in AbstractParamLayoutFormatter so \t and \n can be used for tabs and newlines.</li>
          </ul>
        </div>
      </div>


      <div class="aversion" id="v2.3b2">
        <h3>Version 2.3beta2 (August 29th, 2007)</h3>

        <div class="version_desc">
          <ul>
	    <li>When user chooses to save to an existing file, and answers that the file should not be overwritten, a new file dialog now appears instead of the operation cancelling.</li>
	    <li>Removed antialias setting for main table, because it interferes with proper rendering on LCDs when running under JRE 6. Removed non-optional antialias settings for entry editor for the same reason.</li>
	    <li>Changed external link handling so remote (http) links can be sent to the external application. Applications like Evince and Gimp can open remote links.</li>
	    <li>Replaced Simle HTML export filter with improved version by Mark Schenk.</li>
	    <li>Introduced ParamLayoutFormatter interface for layout formatters that can take an argument by the following syntax: \format[MyFormatter(argument)]{\field}. Implementing classes contain a setArgument(String) method that receives the argument (if any) before the format() method is called.</li>
	    <li>Timestamp and owner fields are now set also when appending a bib file, and new options have been introduced to control whether imported/appended/pasted entries should have these fields overwritten if already set.</li>
	    <li>Added operations for adding file links in import inspection window, and made file and URL icons in the table clickable.</li>
	    <li>Removed PDF and PS columns and operations in import inspection window (replaced by operations on the "file" field).</li>
	    <li>File field column in main table now shows file type icon instead of generic icon.</li>
	    <li>Modified Endnote export to take "file" field into account, and to resolve full paths to PDF files.</li>
	    <li>Added "Auto" button to automatically set "owner" field to the default username.</li>
	    <li>Added \encoding tag for begin/end layouts in export filters to print the name of the character encoding used for the export. The tag is not available in entry layouts.</li>
	    <li>Added \% as a supported LaTeX command, producing '%'.</li>
	    <li>Fixed bug in HTMLChars: commands like {\aa} and {\o} were not processed properly, even though defined in Globals.HTMLCHARS.</li>
	    <li>Fixed bug that made it possible to accidentally close database without saving, when error occurs during the save operation.</li>
          </ul>
        </div>
      </div>


      <div class="aversion" id="v2.3b">
        <h3>Version 2.3beta (June 28th, 2007)</h3>

        <div class="version_desc">
          <ul>
	    <li>Added MIS Quarterly export format.</li>
	    <li>Added support for COPAC file format.</li>
	    <li>Added RemoveTilde LayoutFormatter to deal with Bibtex non-breakable spaces.</li>
	    <li>Added autocompletion feature for author/editors and other fields.</li>
	    <li>Added feature to save all open databases.</li>
	    <li>Added support for pushing citations to Vim when Vim server is enabled.</li>
	    <li>Added missing option for specifying the path to LEd.exe.</li>
	    <li>Added -s/--nosplash command line option for disabling the splash screen.</li>
	    <li>Added new field marker [auth.etal] for key generation.</li>
	    <li>Added support in XMP metadata handling for bibtex string resolution.</li>
	    <li>When opening databases, already open files are now skipped.</li>
	    <li>Option to use native instead of Swing file dialogs is now available on all OSes.</li>
	    <li>Synchronize external links now searches entire database, not selected entries.</li>
	    <li>Medline import now adds &lt;Affiliation&gt; information into the "institution" field.</li>
	    <li>Improved handling of external links. The 'file' field can now specify a list of external links, and arbitrary file types are supported. Old-style PDF and PS links can be moved automatically into the 'file' field.</li>
	    <li>Fixed bug in RIS and Refer/Endnote imports. Entries with editors but no authors are now imported properly.</li>
	    <li>Fixed missing export formatter AuthorFirstFirstCommas.</li>
	    <li>Fixed minor bug in Harvard export with missing space between year and title.</li>
	    <li>Fixed bug that caused lockup when connection to IEEExplore fails.</li>
	    <li>Fixed wrong dependency in OAI2/ArXiv Fetcher.</li>
	    <li>Fixed problem with foreign characters in OAI2/ArXiv Fetcher.</li>
	    <li>Fixed problem with key generation in OAI2/ArXiv Fetcher.</li>
	    <li>Fixed bug in duplicate search that made misc entries never get detected as duplicates.</li>
	    <li>Fixed bug in XMP reimport from DublinCore related to month strings.</li>
          </ul>
        </div>
      </div>


      <div class="aversion" id="v2.2">
        <h3>Version 2.2 (January 30th, 2007)</h3>

        <div class="version_desc">
          <ul>
	    <li>Added progress bar to indicate progress when synchronizing PS/PDF links.</li>
	    <li>Option to autogenerate key for imported entries now also affects entries imported
          without using the import inspection window.</li>
	    <li>Modified quick jump behaviour so sequences of letters can be found. Timeout or
          ESC resets the search.</li>
	    <li>Improved XMP support:
              <ul>
		<li>JabRef now reads and write DublinCore and Legacy Document Properties;
	            Caution needs to be used though since, JabRef does overwrite existing values.</li>
		<li>XMP privacy filter can be used to prevent sensible fields to be exported.</li>
              </ul>
	    </li>  
	    <li>Support for OAI2 identifiers with subcategories, e.g. math.RA/0601001</li>
	    <li>Fixed bug that made explicit groups appear empty after updating group tree from external change.</li>
	    <li>New windows installer thanks to Uwe St&ouml;hr.</li>
	    <li>[ 1641247 ] Fixed: No update of preview after generating bibtex key</li>
	    <li>[ 1631548 ] Fixed: Absolute paths should be stored for last open files.</li>
	    <li>[ 1598777 ] Fixed: Month sorting</li>
	    <li>[ 1570570 ] New Feature: Deselect all duplicates on import</li>
	    <li>[ 1574773 ] Fixed: sanitizeUrl() breaks ftp:// and file:///</li>
	    <li>[ 1609991 ] Fixed: Silverplatter Import: Publisher and Year confused</li>
	    <li>[ 1608391 ] Fixed: Medline Search Editbox size gets to big</li>
          </ul>
        </div>
      </div>


      <div class="aversion" id="v2.2b2">
        <h3>Version 2.2beta2 (November 26th, 2006)</h3>

        <div class="version_desc">
          <ul>
	    <li>Redesigned export functions to simplify GUI and provide &quot;Export selected entries&quot; functionality. Export filter is now chosen using the file type dropdown menu in the file dialog.</li>
	    <li>Fixed bug that caused UnsupportedEncodingException on Windows when saving.</li>
	    <li>Added warning dialog when exporting failed.</li>
	    <li>Added fix for exporting special chars in RTF like &eacute;&oacute;&uacute;...</li>
	    <li>Added NameFormat LayoutFormatter based on Bibtex method name.format$</li>
	    <li>Added Fetch from ArXiv.org to Web Search<br />
        	[1587342] Quering ArXiv (and any OAI2 Repository)</li>
	    <li>[1594123] Fixed: Failure to import big numbers in Bibtex</li>
	    <li>[1594169] Fixed: Entry editor navigation between panels faulty</li>
	    <li>[1588028] Fixed: Export HTML table has relative DOI URL</li>
	    <li>[1601651] Fixed: PDF subdirectory - missing first character</li>
          </ul>
        </div>
      </div>


      <div class="aversion" id="v2.2b">
        <h3>Version 2.2beta (October 13th, 2006)</h3>

        <div class="version_desc">
          <ul>
            <li>Added Mark Schenk's advanced HTML export filter.</li>
            <li>Added options to copy/move/link to dragged linkable file (pdf, ps, etc.).</li>
            <li>Removed unnecessary output text when scanning for external file changes.</li>
            <li>Changed layout of entry editor to solve problem with collapsing text fields.</li>
            <li>Added first version of support for XMP-metadata in PDFs.
              <ul>
		<li>"Import into..."</li>
                <li>Integrate with copy/move/link.</li>
                <li>Added option to write all/selected PDFs in database</li>
              </ul>
            </li>
            <li>Added tooltips to database tabs showing the file's full path.</li>
            <li>Added function for setting or clearing specific fields in selected or all entries.</li>
            <li>Setting a relative PDF/PS path in Database Properties now makes JabRef look for the directory relative to the bib file's location.</li>
            <li>Removed Oxford comma from AuthorList...Comma-LayoutFormatters.</li>
            <li>Added LayoutFormatters that print the Oxford comma.</li>
            <li>Added missing space between abbreviated author first names: William Andrew Paul => W. A. Paul (used to be W.A. Paul)</li>
            <li>Added LayoutFormatter for HTML paragraphs.</li>
            <li>Changing Database Properties now causes the database to be marked as changed.</li>
            <li>Improved simple search. Words are now treated as separate search terms, and phrases can be indicated with "double quotes".</li>
            <li>When a letter key is pressed in the table, the first entry starting with the same letter (in the current sort column) is selected.</li>
            <li>Bib files dragged into JabRef now appear in the Recent files menu.</li>
            <li>Fixed bug in import dialog.</li>
            <li>Better support for ISI files in general (should basically be better than INSPEC) including IEEE parsing.</li>
            <li>Added regular expression search for auto-linking. Search is now more flexible by default.</li>
            <li>Search operation also now search relative to the JabRef directory.</li>
            <li>General improvements in Inspec ISI handling.</li>
            <li>New LayoutFormatter: AuthorOrgSci - first author is in "last, first" all others in "first last". First names are abbreviated.</li>
            <li>New LayoutFormatter: NoSpaceBetweenAbbreviations - spaces between multiple abbreviated first names are removed.</li>
            <li>Improved PDF link resolver to produce proper URIs.</li>
            <li>[1503956] Fixed: Help text instead of Help icon.</li>
            <li>[1542552] Fixed: Wrong author import from ISI file.</li>
            <li>[1534537] Fixed: resize groups interface</li>
            <li>[1465610] Fixed: (Double-)Names containing hyphen (-) not handled correctly</li>
            <li>[1436014] Fixed: No comma added to separate keywords</li>
            <li>[1548875] Fixed: download pdf produces unsupported filename</li>
            <li>[1545601] Fixed: downloading pdf corrupts pdf field text</li>
            <li>[1285977] Fixed: Impossible to properly sort a numeric field</li>
            <li>[1535044] Fixed: Month sorting</li>
            <li>[1540646] Fixed: Default sort order: bibtexkey</li>
            <li>[1553552] Fixed: Not properly detecting changes to flag as changed</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v2.1">
        <h3>Version 2.1 (August 9th, 2006)</h3>

        <div class="version_desc">
          <ul>
            <li>Added capability to drag files and WWW links into JabRef. BibTeX files will be opened normally, other files imported, and WWW links downloaded and imported.</li>
            <li>The -v command line option now makes the application only print version number and quit immediately.</li>
            <li>Duplicates within a set of imported entries are now found and shown in the import inspection window.</li>
            <li>Mac users can now choose between native and Swing file chooser under Preferences -> Advanced.</li>
            <li>Fixed missing duplicate warning when importing without import inspection window.</li>
            <li>Fixed error in ISI import which allowed empty fields to be set, causing errors when saving and reloading entries.</li>
            <li>Fixed missing time and owner stamps when importing entries (only in 2.1b/2.1b2).</li>
            <li>Fixed erroneous updates in entry editor if user switches to a different entry while downloading PDF/PS file.</li>
            <li>Fixed bug (only in 2.1b2) in calling file open/save dialog on Mac OS X.</li>
            <li>Fixed bug that made duplicate resolver dialog pop under import inspection dialog.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v2.1b2">
        <h3>Version 2.1beta2 (June 29th, 2006)</h3>

        <div class="version_desc">
          <ul>
            <li>Added option to toggle floating of marked entries to the top of the table.</li>
            <li>Added option to open, append and import multiple BibTeX files.</li>
            <li>Improved support for LaTeX special characters in HTML and RTF representations.</li>
            <li>Fixed bug that made entry selection get lost when an edit modifies the entry's position in the main table.</li>
            <li>Fixed bug in BibTeXML export (entry type name missing in tag).</li>
            <li>Fixed name handling in (non-compliant) Endnote files giving all author names on the same line.</li>
            <li>Fixed problem with keyword handling in Medline import.</li>
            <li>Fixed (harmless) NullPointerException when medline fetch is cancelled.</li>
            <li>Fixed bug in parser that made {&quot;} illegal in fields quoted with &quot;.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v2.1b">
        <h3>Version 2.1beta (May 9th, 2006)</h3>

        <div class="version_desc">
          <ul>
            <li>Known issue: font sizes cannot be changed, but should follow OS settings on Windows and Mac.</li>
            <li>Known issue: some HTML may be handled inappropriately when downloading from IEEEXplore.</li>
            <li>Changed Paste function so timestamp and owner fields are updated.</li>
            <li>Added "Other" item to the "New entry ..." menu.</li>
            <li>Changed sorting order so marked entries are displayed at the top of the table.</li>
            <li>Added IEEEXplore search and download feature.</li>
            <li>Added option to store entries in their original order. Unsorted table view now shows entries in their original order.</li>
            <li>Added option to autogenerate keys for entries with missing keys before each save.</li>
            <li>Improved handling of external URLs to prevent problems with special characters.</li>
            <li>Medline and Endnote imports no longer automatically add curly braces around capital letters in title.</li>
            <li>Float search now scrolls table to the top.</li>
            <li>Fixed lockup problem in preview with uneven number of # characters in fields.</li>
            <li>Fixed problem with default content selector fields reappearing after removal.</li>
            <li>Fixed problem with unsupported character encodings being selectable and causing unreported save errors.</li>
            <li>Fixed bug that blocked last line in table columns setup from being removed.</li>
            <li>Fixed missing export formats from command line.</li>
            <li>Fixed bug that made Review tab impossible to remove from entry editor.</li>
            <li>Fixed bug in Medline XML import that assigned wrong PMID for some entries.</li>
            <li>Fixed bug in custom export that collapsed sequences of two or more backslashes into a single backslash in output.</li>
            <li>Fixed bug (misspelled formatter name) in Harvard RTF export.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v2.0.1">
        <h3>Version 2.0.1 (February 2nd, 2006)</h3>

        <div class="version_desc">
          <ul>
            <li>Fixed bug that made a hidden entry editor under some circumstances store a field value to the wrong entry.</li>
            <li>Fixed synchronization bug that made the opening of a new database sometimes fail.</li>
            <li>Fixed bug in setting external journal lists when no personal list is set.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v2.0">
        <h3>Version 2.0 (January 30th, 2006)</h3>

        <div class="version_desc">
          <ul>
            <li>Changed sorting selection interface for main table for more intuitive operation.</li>
            <li>Made import operation update &quot;working directory&quot;.</li>
            <li>Removed hard-coded author/editor name rearrangement in OpenOffice and OpenDocument exports.</li>
            <li>Fixed hangup when parsing RIS files from Nature.</li>
            <li>Fixed NullPointerException when generating database from AUX file on Mac OS X.</li>
            <li>Fixed bug in routine for adding braces around capital letters.</li>
            <li>Fixed bug in switching preview layouts.</li>
            <li>Fixed bug in link handling in entry preview.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v2.0b2">
        <h3>Version 2.0b2 (January 15th, 2006)</h3>

        <div class="version_desc">
          <ul>
            <li>Changed field order in OpenDocument export
	    to comply with bibliography requirement.</li>
            <li>Added support for inserting citations into
	    Emacs using gnuserv/gnuclient.</li>
            <li>Added option to set a regular expression
	    replace operation on generated bibtex keys.</li>
            <li>Added support for custom importers inside
	    a jar file.</li>
            <li>Fixed bug in opening URLs containing &quot;&amp;&quot;
	    on Windows.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v2.0b">
        <h3>Version 2.0b (January 4rd, 2006)</h3>

        <div class="version_desc">
          <ul>
            <li>Added a system for registering custom import
            formats in the form of Java classes.</li>

            <li>Added export option for OpenDocument
            spreadsheet.</li>

            <li>Added new options for name formatting in main
            table.</li>

            <li>Added REPEC-NEP import filter.</li>

            <li>Added option for non-field parameters for export
            formatters. If the parameters does not start with the
            backslash character, it will be passed unchanged to the
            formatter in place of any field value.</li>

            <li>Added export formatter CurrentDate, which returns
            the current date, and takes a format string as
            parameter.</li>

            <li>Added customization of table colors, accessible
            from Tools -&gt; Preferences -&gt; Entry table.</li>

            <li>Added new Appearance tab to preferences dialog to
            gather appearance related options.</li>

            <li>Added toggling of abbreviated and full journal
            names. The list of journals is realized as an external
            file, but editable from a Manage Journal Abbreviations
            panel. Secondary lists can also be linked, but not
            edited. There is a download option to quickly get a
            list available on the internet, and we will provide one
            or more lists for download from the SourceForge web
            server.</li>

            <li>Added functionality for new JabRef instances to
            detect a running instance, and send command line
            parameters to the running instance for processing.</li>

            <li>Added handling of HTML links in preview panel.</li>

            <li>Added confirmation dialog when database is saved
            and the chosen encoding doesn't support all characters.
            Gives options to save, cancel or try a different
            encoding.</li>

            <li>The encoding used when opening a database is now
            remembered, and used when database is stored.</li>

            <li>Added review field for research comments, paper
            reviews, etc.</li>

            <li>Added option to disable wrapping for certain
            fields. Wrapping of pdf, ps, doi and url is disabled by
            default.</li>

            <li>Antialiasing option now affects entry editor text
            fields as well as table text.</li>

            <li>Changed to monospaced font in source editor
            field.</li>

            <li>Removed option to put double braces around BibTeX
            fields, but retained option to remove double braces
            when loading. Added option to put braces around capital
            letters of a chosen set of fields.</li>

            <li>Improved Ovid import.</li>

            <li>Fixed problem with ordering of BibTeX strings that
            are referred to by other strings.</li>

            <li>Fixed problem with loading 16-bit encoded bib files
            with 8-bit encoding as default, and vice versa.</li>

            <li>Fixed multiplying authors bug when author/editor
            field appears both in Required and Optional tabs.</li>

            <li>Fixed startup freeze when trying to load certain
            malformed bib files.</li>

            <li>Fixed bug related to multi-line fields in RIS
            import.</li>

            <li>Fixed bug related to looking up a fully qualified
            DOI</li>

            <li>Included review field to preview panel 1, which
            shows the abstract</li>

            <li>Fixed bug where booktitle field was not displayed
            in preview for conference proceedings, similar to
            journal field</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v1.8.1">
        <h3>Version 1.8.1 (September 15th, 2005)</h3>

        <div class="version_desc">
          <ul>
            <li>Added automatic timestamp with configurable format
            for entries.</li>

            <li>Added new key generator modifier ":abbr" to
            abbreviate field contents.</li>

            <li>Added Help button in Preferences -&gt; Entry
            preview</li>

            <li>Added Big5, Big5_HKSCS and GBK encodings for
            Chinese.</li>

            <li>Improved marking feature. Username is now used for
            marking, so different users can mark entries separately
            without interference.</li>

            <li>Improved handling of names such as "Firstname de la
            Lastname jr.", with different handling for presentation
            and sorting purposes. Key generation now should always
            use the genuine last name, and not produce keys like
            "de2001".</li>

            <li>Removed duplicate warning from inspection dialog
            for non-selected entries.</li>

            <li>Fixed setting of sheet name in OpenOffice.org Calc
            export.</li>

            <li>Fixed bug that prevented PDF/PS opening by F4 from
            automatically finding files in subdirectories below the
            main PDF/PS dir.</li>

            <li>Fixed BibTeX parser bug that made it choke on short
            comment strings.</li>

            <li>Fixed bug that made regexp search fail for fields
            containing newline characters.</li>

            <li>Fixed platform-dependent handling of newline
            characters.</li>

            <li>Fixed bug that prevented command line import with
            explicit format.</li>

            <li>Fixed bug that made it impossible to disable table
            antialiasing.</li>

            <li>Fixed AuthorAndsCommaReplacer.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v1.8">
        <h3>Version 1.8 (July 30th, 2005)</h3>

        <div class="version_desc">
          <ul>
            <li>Search panel now starts visible if it was visible
            at last shutdown.</li>

            <li>Added option to disable import inspection window
            when only one entry is being imported.</li>

            <li>Fixed parser bug that caused problems for bib files
            containing extra characters after the last entry.</li>

            <li>Fixed missing month in JStor import.</li>

            <li>Fixed some bugs in "new from plain-text"
            wizard</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v1.8b2">
        <h3>Version 1.8b2 (July 13th, 2005)</h3>

        <div class="version_desc">
          <ul>
            <li>Changed handling of external updates so the
            notification is suppressed when there are no actual
            changes.</li>

            <li>Changed autodetecting import so BibTeX files are
            handled more similarly to other formats.</li>

            <li>Enabled sorting by icon columns.</li>

            <li>Added option to remove BibTeX source panel from
            entry editor.</li>

            <li>Added command line option to not load any files at
            startup. Will override any autoloading or other command
            line options that normally load or import files.</li>

            <li>Added handling of duplicates to import inspection
            window.</li>

            <li>Made entry preview be updated whenever the
            selection is expanded by one row, to make it more
            useful while selecting a set of entries.</li>

            <li>Changed BibTeX field parsing to avoid inserting
            line breaks at wrong places.</li>

            <li>Improved handling of page numbers in Medline
            import.</li>

            <li>Fixed import of file links when importing SixPack
            files.</li>

            <li>Fixed bug that prevented closing of search
            interface when no databases are open.</li>

            <li>Fixed OpenOffice Calc export to be compatible with
            OpenOffice.org 2.0 beta.</li>

            <li>Fixed bugs in Refer/Endnote import filter.</li>

            <li>Fixed bug that caused changes to be lost in some
            cases when the same field is present in multiple entry
            editor tabs.</li>

            <li>Fixed bug that prevented command line autodetecting
            import from working with BibTeX files.</li>

            <li>Fixed minor bug in routine for removing double
            braces.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v1.8b">
        <h3>Version 1.8b (June 6th, 2005)</h3>

        <div class="version_desc">
          <ul>
            <li>Added option to store bib file with double
            braces.</li>

            <li>Follow @input tags in aux files to indicate nested
            aux files for generating subdatabases.</li>

            <li>Improved wrapping and formatting of bib files, to
            preserve paragraph separator (empty line).</li>

            <li>Added test buttons for customization of preview
            layouts.</li>

            <li>Added inspection dialog for previewing and
            generating keys for imported entries, and deciding
            which ones to keep and discard. The dialog allows
            inspection during long import processes such as Medline
            search.</li>

            <li>Restructured layout to use a common side pane with
            all tabs.</li>

            <li>Added import filter for Cambridge Scientific
            Abstracts (CSA) format.</li>

            <li>Added PS directory with similar options as
            PDF.</li>

            <li>Added [authshort] and [edtrshort] key field
            markers. Patch submitted by Kolja Brix.</li>

            <li>Made the program remember preview enable
            setting.</li>

            <li>Improved handling of illegal regular expressions in
            search.</li>

            <li>Removed options to search only Required, Optional
            and/or General fields, in order to simplify search
            interface.</li>

            <li>Changed keys for Next and Previous tab to CTRL-PGDN
            and CTRL-PGUP to match shortcuts in a certain popular
            web browser.</li>

            <li>Minor change to Preferences -&gt; Table columns to
            enable insertion of column in position 2.</li>

            <li>Fixed parser bug that caused problems for files
            with &gt;10000 entries.</li>

            <li>Fixed bug in entry editor that caused the source
            edit panel to remove marking from entries.</li>

            <li>Fixed export bug that made "\begin" tags fail if
            encountered immediately after an "\end" tag.</li>

            <li>Fixed export bug that made whitespace disappear
            after empty field values.</li>

            <li>Fixed ISI import bug that handled SO fields with
            line breaks wrong.</li>

            <li>Fixed bug occuring when choosing not to import
            duplicate imported entry.</li>

            <li>Fixed problem with detecting popup trigger on Mac
            OSX with one button mouse.</li>

            <li>Fixed erroneous relative paths for PDF files when
            PDF directory is not set.</li>

            <li>Fixed entry preview to resolve string
            references.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v1.7.1">
        <h3>Version 1.7.1 (April 11th, 2005)</h3>

        <div class="version_desc">
          <ul>
            <li>Removed unnecessary stack traces when opening
            external viewer.</li>

            <li>Fixed bug that made Ctrl-E destroy current edit in
            entry editor.</li>

            <li>Fixed problem when copying modified BibTeX key gave
            old value.</li>

            <li>Fixed missing 'booktitle' import in CONF entries in
            RIS import.</li>

            <li>Fixed bug that made group tree disappear for
            certain keyword expressions.</li>

            <li>Fixed bug that made standard BibTeX fields be saved
            without line wrapping.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v1.7">
        <h3>Version 1.7 (March 20th, 2005)</h3>

        <div class="version_desc">
          <ul>
            <li>When fetching from Medline or CiteSeer, fetched
            entry is now opened in editor.</li>

            <li>Added French translations of help files.</li>

            <li>Added color highlighting of focused text field in
            entry editor.</li>

            <li>Added option in context menu of entry editor for
            changing capitalization of field contents.</li>

            <li>Added name conversion "Smith, RA" -&gt; "Smith, R.
            A." to Medline import.</li>

            <li>Updated task bar icon.</li>

            <li>Fixed bug that left some search settings items
            inappropriately disabled.</li>

            <li>Fixed bug that caused requirement for restart to
            register change in default key pattern.</li>

            <li>Fixed bug that caused crash some times after
            adding/removing field content selectors.</li>

            <li>Fixed bug in recognizing custom entry types when
            reading bib files.</li>

            <li>Fixed bug that allowed a deleted entry to stay
            visible in the entry editor.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v1.7b2">
        <h3>Version 1.7b2 (March 7th, 2005)</h3>

        <div class="version_desc">
          <ul>
            <li>Made side pane resizable.</li>

            <li>Replaced entry customization dialog with improved
            interface.</li>

            <li>Added standard entry type "conference", similar to
            "inproceedings".</li>

            <li>Added "default" label pattern to avoid the need to
            set similar patterns manually.</li>

            <li>Added line wrapping to metadata in saved .bib file
            to avoid long lines.</li>

            <li>Added "bibtexkey" as implicit fourth sort
            criterion, to minimize problem with almost similar
            entries swapping places.</li>

            <li>Added OpenOffice.org Calc export filter that
            exports spreadsheet compatible with the OpenOffice.org
            bibliography feature.</li>

            <li>Added a couple of previously unsupported
            publication types in SciFinder.</li>

            <li>Added extra shortcuts CTRL-+ and CTRL-- for
            switching tabs in entry editor, since original
            shortcuts CTRL[-SHIFT]-TAB is used by some window
            managers.</li>

            <li>Updated Endnote export filter.</li>

            <li>Fixed focus and key binding problems for switching
            tabs/entries in entry editor</li>

            <li>Fixed bug that prevented required fields tab from
            appearing for entries with only bibtex key as required
            field.</li>

            <li>Fixed bug that prevented the user from changing the
            default encoding.</li>

            <li>Fixed bugs in RIS import.</li>

            <li>Fixed bug 1112050; freeze when reading certain
            author fields with mismatched braces.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v1.7b">
        <h3>Version 1.7 beta (January, 27th, 2005)</h3>

        <div class="version_desc">
          <ul>
            <li>Changed routine for rearranging author names
            (Lastname, Firstname) so bracketed expressions are
            treated as units.</li>

            <li>Grouping controls now allow group hierarchies, and
            now allow explicit and search expression groups as well
            as keyword groups.</li>

            <li>Added full customization of general fields
            tabs</li>

            <li>Added option to preserve formatting for non-BibTeX
            fields.</li>

            <li>Added integrity check for database.</li>

            <li>Added export filters EndNote and Harvard RTF.</li>

            <li>Added automatic import format chooser for import
            (same as wildcard import below).</li>

            <li>Added wildcard (*) option for command-line import,
            to try to automatically determine the correct
            format.</li>

            <li>Improved layout of Preferences dialog.</li>

            <li>Improved several import filters.</li>

            <li>Added authIniN/edtrIniN key formatters.</li>

            <li>Marked entries now float to the top when no
            searching or grouping reordering is active.</li>

            <li>Fixed problem with opening file dialog on Win NT -
            AWT dialog is now opened if Swing dialog fails.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v1.6">
        <h3>Version 1.6 (November 27th, 2004)</h3>

        <div class="version_desc">
          <ul>
            <li>Improved handling of entry selection in various
            ways. E.g. selected entry is now scrolled into view
            when opening entry editor and entry is kept selected
            when importing CiteSeer fields.</li>

            <li>Fixed update bug between autogenerated bibtex key
            and source panel.</li>

            <li>Fixed missing . at the end of last author name in
            RIS import.</li>

            <li>Fixed NullPointerException on certain special
            characters when exporting to BibTeXML.</li>

            <li>Fixed bug that sometimes prevented the unmarking of
            entries.</li>

            <li>Fixed problem with formatting one-name
            authors.</li>

            <li>Various other bugfixes.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v1.6beta">
        <h3>Version 1.6 beta (November 8th, 2004)</h3>

        <div class="version_desc">
          In an effort to produce better and more stable releases,
          we have decided to release a beta versions prior to
          future versions. We start out with today's beta release
          of version 1.6. Users can now choose whether to download
          and use the beta version for earlier access to new
          features, or whether to wait for the final, better-tested
          version. The final 1.6 version is expected to be released
          in a few weeks from today.

          <ul>
            <li>Added mnemonic keys to menus and menu items.</li>

            <li>Added MODS and tab-separated file export</li>

            <li>Added facility for detecting and handling external
            changes to open bib files.</li>

            <li>Added scan option for exact duplicates with
            automatic removal.</li>

            <li>Bibtex strings are now sorted automatically.</li>

            <li>Bibtex strings are now resolved when exporting
            database.</li>

            <li>Fixed problem with field content selector getting
            too wide. Limited width.</li>

            <li>Fixed PDF/PS view routine to remove problems with
            filenames containing spaces.</li>

            <li>Fixed bug that interfered with Fetch Medline by ID,
            and fixed parsing problem with certain Medline
            entries.</li>

            <li>Fixed bug that interfered with Push to WinEdt.</li>

            <li>Fixed HTML output so \c{c} is handled
            correctly.</li>

            <li>command line option for generating a sub-database
            from LaTex aux file entries</li>

            <li>simple copy/paste menu in entry editor</li>

            <li>experimental: text-input-area with underlying
            infotext in plaintext import dialog</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v1.55">
        <h3>Version 1.55 (October 12th, 2004)</h3>

        <div class="version_desc">
          <ul>
            <li>Made method for opening PDF files robust with
            regard to file separators (/ and \), so bib files can
            easier be used across platforms.</li>

            <li>Improved Medline fetcher. Can now fetch entries
            based on a search expression.</li>

            <li>Added features for fetching fields and referring
            publications from the CiteSeer database.</li>

            <li>Added option to always save database ordered by
            author/editor/year, and made this the default
            setting.</li>

            <li>When a single entry is selected, right-click menu
            now shows a checkbox menu for group memberships instead
            of the regular "Add to" and "Remove from" menus.</li>

            <li>Improved [shorttitle] and [veryshorttitle] special
            key pattern fields.</li>

            <li>Added dialog box for conveniently setting the
            fields of an entry from a plain text description.</li>

            <li>Added formatter "ResolvePDF" to create correct
            PDF/PS links in HTML export when relative file names
            are used.</li>

            <li>Added Abstract panel in entry editor.</li>

            <li>Added alternate preview with abstract, and made
            both previews configurable.</li>

            <li>Added the option to create a subset of a database
            based on an .aux file.</li>

            <li>Added 'Cancel' button to duplicate resolver dialog,
            so the process can be stopped.</li>

            <li>Added check for spaces in custom entry type
            names.</li>

            <li>Added several new key pattern markers.</li>

            <li>Added toolbar buttons for Mark/Unmark to improve
            discoverability.</li>

            <li>Fixed bug that made export filters ignore the
            chosen character encoding.</li>

            <li>Fixed bug that interfered with author name
            formatting.</li>

            <li>Fixed various bugs in import filters.</li>

            <li>Fixed bug that prevented confirmation dialog when
            deleting entry from the entry editor.</li>

            <li>Fixed bug that made the next entry be opened
            instead of the current, when pressing Enter in
            table.</li>

            <li>Fixed bug that made it possible to leave a source
            edit when the source didn't validate properly. Also
            made it impossible to change table selection until
            source validates.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v1.5">
        <h3>Version 1.5 (August 3rd, 2004)</h3>

        <div class="version_desc">
          <ul>
            <li>Added Highlight and select options to the group
            interface, and made it possible to change the number of
            visible lines in the list.</li>

            <li>Added option to allow or disallow direct table
            editing.</li>

            <li>Added optional confirmation dialog for deleting
            entries.</li>

            <li>Added optional warning dialog for duplicate BibTeX
            keys.</li>

            <li>Added warnings to bibtex parser, and prevented
            failure to load files with duplicate string
            definitions.</li>

            <li>Added JStor import filter.</li>

            <li>Changed the look and feel to JGoodies Forms on
            non-Macintosh platforms.</li>

            <li>Optimized performance in several areas.</li>

            <li>Numerous usability improvements.</li>

            <li>Fixed RIS import bug caused by short lines.</li>

            <li>Fixed bug in SciFinder import.</li>

            <li>Fixed bug that made entire field get cleared when
            removing from group.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v1.4">
        <h3>Version 1.4 (June 6th, 2004)</h3>

        <div class="version_desc">
          Version 1.4 provides command line options for importing
          and exporting files, advanced search, custom export
          filters, and several bug fixes and minor changes.

          <ul>
            <li>Added advanced search feature.</li>

            <li>Added facility for using custom export
            formats.</li>

            <li>Added command line options for importing/exporting
            files, importing/exporting, user preferences, loading
            session and for suppressing the GUI (using RitOpt for
            parsing options).</li>

            <li>Added automatic stripping of \url{} when opening
            url.</li>

            <li>Improved export filters.</li>

            <li>Fixed focus bug which sometimes interfered with
            cut/copy/paste.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v1.3.1">
        <h3>Version 1.3.1 (May 9, 2004)</h3>

        <div class="version_desc">
          Release 1.3.1 resolves a couple of severe bugs in 1.3.
          All users who have downloaded 1.3 should upgrade (the
          bugs in 1.3 impaired functionality but did not pose a
          security risk).
        </div>
      </div>

      <div class="aversion" id="v1.3">
        <h3>Version 1.3.* (May 9, 2004)</h3>

        <div class="version_desc">
          This release adds entry preview, detection of duplicate
          entries, persistent entry marking, new import and export
          filters, better linking to PDF/PS/URL/DOI, and better Mac
          OS X compatibility. There are several other changes and
          improvements, and many bugfixes.
        </div>
      </div>

      <div class="aversion" id="v1.2">
        <h3>Version 1.2 (March 21, 2004)</h3>

        <div class="version_desc">
          The icon set has been replaced. Notable new features are
          French language support, key generator customization,
          string replace, and BibTeXML import. Several bugs have
          been fixed.
        </div>
      </div>

      <div class="aversion" id="v1.19">
        <h3>Version 1.19 (Feb 19,2004)</h3>

        <div class="version_desc">
          <ul>
            <li>Entry editor is now opened for an entry that causes
            an error when saving.</li>

            <li>Added options to display names in harmonised format
            in the main table.</li>

            <li>Added Docbook and (experimental) HTML export.</li>

            <li>Enabled 'doi' and 'pii' fields for Medline
            import.</li>

            <li>Enabled antialiasing fonts.</li>

            <li>Added the Kunststoff look&amp;feel to give a more
            pleasant user interface.</li>

            <li>Added font selector for the main table.</li>

            <li>Improved table column customization.</li>

            <li>Added word selector feature for selected fields
            (keywords, journal).</li>

            <li>Added support for German and Norwegian
            language.</li>

            <li>Generalized copy cite key features for multiple
            entries.</li>

            <li>Added quick load/save session feature.</li>

            <li>Fixed issue/number bug in ISI import.</li>

            <li>Added "Open pdf/ps" item in right-click menu.</li>

            <li>Fixed bug causing external viewer to only work in
            General fields.</li>

            <li>Added functionality for using Browse buttons for
            file-related fields (ps, pdf, etc.).</li>

            <li>Added Browse buttons to External programs tab in
            Preferences.</li>

            <li>Fixed bug related to Save database and Source field
            which caused changes to disappear.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v1.1">
        <h3>Version 1.1 (Jan 02, 2004)</h3>

        <div class="version_desc">
          <ul>
            <li>Added bibtex key uniqueness checking, and prevented
            autogeneration from generating non-unique keys.</li>

            <li>Added command line option to load file on
            startup.</li>

            <li>Fixed problem with autogeneration creating invalid
            keys.</li>

            <li>Improved Refer/Endnote import.</li>

            <li>Added feature for importing entries and/or strings
            and/or group definitions from another BibTeX
            database.</li>

            <li>Added menu choices to import into open
            database.</li>

            <li>Added toolbar button for closing current
            database.</li>

            <li>Fixed shortcut key for "Store field" in entry
            editor, and fixed problem that made shortcut for "Save
            database" unavailable from entry editor.</li>

            <li>Added option to fetch Medline entries automatically
            by ID, based on a patch submitted by Mike Smoot.</li>

            <li>Fixed bug in RIS import.</li>

            <li>Added options to copy key and copy "\cite{key}" to
            right-click menu.</li>

            <li>Fixed bug that caused some General fields not to
            get displayed.</li>

            <li>Enabled customization of General fields.</li>

            <li>Enabled customization of existing entry types, and
            definition of new types.</li>
          </ul>
        </div>
      </div>

      <div class="aversion" id="v1.0">
        <h3>Version 1.0 (Nov 29, 2003)</h3>

        <div class="version_desc">
          First release of JabRef
        </div>
      </div>
    </div>
    <?php include("footer.php"); ?>
  </div>

</body>
</html>
