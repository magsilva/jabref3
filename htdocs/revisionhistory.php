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

      <div class="single_item">
        <a href="#v2.2b">2.2b</a>
      </div>

      <div class="single_item">
        <a href="#v2.1">2.1</a>
      </div>

      <div class="single_item">
        <a href="#v2.1b2">2.1b2</a>
      </div>

      <div class="single_item">
        <a href="#v2.1b">2.1b</a>
      </div>

      <div class="single_item">
        <a href="#v2.0.1">2.0.1</a>
      </div>

      <div class="single_item">
        <a href="#v2.0">2.0</a>
      </div>

      <div class="single_item">
        <a href="#v2.0b2">2.0b2</a>
      </div>

      <div class="single_item">
        <a href="#v2.0b">2.0b</a>
      </div>

      <div class="single_item">
        <a href="#v1.8.1">1.8.1</a>
      </div>

      <div class="single_item">
        <a href="#v1.8">1.8</a>
      </div>

      <div class="single_item">
        <a href="#v1.8b2">1.8b2</a>
      </div>

      <div class="single_item">
        <a href="#v1.8b">1.8b</a>
      </div>

      <div class="single_item">
        <a href="#v1.7.1">1.7.1</a>
      </div>

      <div class="single_item">
        <a href="#v1.7">1.7</a>
      </div>

      <div class="single_item">
        <a href="#v1.7b2">1.7 beta2</a>
      </div>

      <div class="single_item">
        <a href="#v1.7b">1.7 beta</a>
      </div>

      <div class="single_item">
        <a href="#v1.6">1.6</a>
      </div>

      <div class="single_item">
        <a href="#v1.6beta">1.6 beta</a>
      </div>

      <div class="single_item">
        <a href="#v1.55">1.55</a>
      </div>

      <div class="single_item">
        <a href="#v1.5">1.5</a>
      </div>

      <div class="single_item">
        <a href="#v1.4">1.4</a>
      </div>

      <div class="single_item">
        <a href="#v1.3.1">1.3.1</a>
      </div>

      <div class="single_item">
        <a href="#v1.3">1.3</a>
      </div>

      <div class="single_item">
        <a href="#v1.2">1.2</a>
      </div>

      <div class="single_item">
        <a href="#v1.19">1.19</a>
      </div>

      <div class="single_item">
        <a href="#v1.1">1.1</a>
      </div>

      <div class="single_item">
        <a href="#v1.0">1.0</a>
      </div>
    </div>

    <div id="main">

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
