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

      <h3 id="tools_rightpanel">External tools</h3>
      <ul>
        <li><a href="#OOtool">JabRef formatter</a></li>
        <li><a href="#PocketBib">PocketBib</a></li>
      </ul>

      <h3 id="plugins_rightpanel">Plugins</h3>
      <ul>
        <li><a href="#BibSonomy">BibSonomy plugin</a></li>
        <li><a href="#PushEmacsclient">Push to Emacsclient</a></li>
        <li><a href="#Lehner">Christoph Lehner's plugins</a></li>
        <li><a href="#renamer">Rename files plugin</a></li>
      </ul>

      <h3 id="fetchers_rightpanel">Entry fetchers</h3>
      <ul>
        <li><a href="#ADS">JabRef ADS fetcher</a></li>
        <li><a href="#ADS_HUG">Modified JabRef ADS fetcher</a></li>
        <li><a href="#GVK">GVK/SRU fetcher</a></li>
      </ul>

      <h3 id="exportfilters_rightpanel">Export filters</h3>
      <ul>
        <li><a href="#Marks_filters">Mark Schenk's HTML export filters</a></li>
        <li><a href="#Martens_filters">Marten Kooiker's RTF export filters</a></li>
        <li><a href="#Edward_Elgar_filters">Edward Elgar export filters</a></li>
        <li><a href="#RIS_export">RIS export format</a></li>
        <li><a href="#Chicago_Manual">Chicago Manual of Style export filters</a></li>
        <li><a href="#Turabian">Turabian export filters</a></li>
      </ul>

      <h3 id="journallists_rightpanel">Journal lists</h3>
      <ul>
        <li><a href="journals/journal_abbreviations_general.txt">General list</a></li>
        <li><a href="journals/journal_abbreviations_entrez.txt">Entrez journals abbreviation list</a> by Emmanuel Charpentier. <i>Note: provides Medline (dotless) abbr. only.</i></li>
        <li><a href="journals/journal_abbreviations_medicus.txt">Index Medicus abbreviation list</a> by Guy Tsafnat.
        <i>Note: provides Medline (dotless) abbr. only.</i></li>
        <li><a href="journals/journal_abbreviations_lifescience.txt">Life Science abbreviation list</a> by Z&eacute; Roberto Ribeiro</li>
        <li><a href="journals/journal_abbreviations_meteorology.txt">Meteorology journal list</a> by Thijs Heus.</li>
        <li><a href="journals/journal_abbreviations_sociology.txt">Sociology journal list</a> by Ronggui Huang.</li>
      </ul>

    </div>

    <div id="main">

      <h2 id="exttools">External tools</h2>

      <h3 id="OOtool">JabRef formatter</h3>
      <p><em>by Felix Wilke</em></p>

      <p>The JabRef formatter is a LibreOffice spreadsheet that lets you build
      HTML, RTF or OpenOffice/LibreOffice export filters, as well as jstyle files
      for direct integration with OpenOffice or LibreOffice.</p>

      <a href="tools/jabref_formatter.ods">JabRef formatter</a>

      <h3 id="PocketBib">PocketBib</h3>
      <p><em>by Graham Dennis</em></p>

      <p>PocketBib is a BibTeX library reader for iPads and iPhones that's compatible with JabRef.  With PocketBib you can search, browse and read the linked papers in your library, and synchronise them over Dropbox.</p>

      <a href="http://www.grahamdennis.me/pocketbib/">PocketBib</a>

      <h2 id="plugins">Plugins</h2>

      <p>JabRef offers a plugin framework that allows the application to 
      be extended with further import and export filters, web search modules 
      (fetchers), layout formatters, cite-while-you-write modules and general
      sidepane components.</p>

      <h3 id="Installing_plugins">Installing plugins</h3>

      <p>If you are using JabRef 2.5 or later, you can install plugins
      using the Plugin Manager.
      Go to <b>Plugins -> Manage Plugins</b>. Click <b>Install plugin</b>,
      and choose the <i>jar</i> file containing the plugin.</p>

      <p>For JabRef versions 2.4.x, plugins must be installed manually.
      This is fortunately quite simple - you must create a directory
      named <strong>plugins</strong> right below the directory where
      JabRef is installed (specifically, the directory where the file
      named <strong>JabRef-X.jar</strong> is located, where X is the
      version number), and drop your plugins there.
      All plugins (in the form of jar files) in this directory will be
      loaded automatically when you start up JabRef.</p>

      <h3>Plugins available for download</h3>

      <h4 id="BibSonomy">BibSonomy plugin</h4>
      <p><em>by <a href="http://www.bibsonomy.org/help/contact/team">BibSonomy team</a></em></p>

      <p><a href="http://www.bibsonomy.org/help/doc/jabref-plugin/index.html">Home page (see also for source code)</a></p>

      <p>BibSonomy is a free online platform for collaborative sharing of
      bookmarks and scientific references, based on the BibTeX-format. Its
      features comprise e.g. the comfortable maintenance of online
      publication lists. With this plugin, it is possible to 
      <a href="http://www.bibsonomy.org/help/doc/jabref-plugin/fetch.html">fetch</a>,
      <a href="http://www.bibsonomy.org/help/doc/jabref-plugin/export.html">store</a> and
      <a href="http://www.bibsonomy.org/help/doc/jabref-plugin/delete.html">delete</a>
      BibTeX entries from BibSonomy; in this way, JabRef can be used
      as a client application to remotely manage the online bibliography
      stored centrally at <a href="http://www.bibsonomy.org">bibsonomy.org</a>.</p>


      <h4 id="PushEmacsclient">Push to Emacsclient</h4>

      <p>Version 1.0<br />
      <a href="plugins/net.sf.jabref.external.pushtoemacsclient-1.0.jar">Plugin</a> -
      <a href="plugins/PushToEmacsclient.zip">Source code</a></p>

      <p>This plugin provides the same functionality as the built-in Push to Emacs feature,
      except that it uses <em>emacsclient</em> instead of <em>gnuclient</em>
      to communicate with Emacs. This requires a recent version of Emacs to work.<br />
      Since JabRef 2.8, this behavior is built-in.</p>


      <h4 id="Lehner">Christoph Lehner's plugins</h4>
      <p><em>by Christoph Lehner</em></p>

      <p><a href="http://www.lhnr.de/ext/">Home page (see also for source code)</a></p>

      <p>The page contains the following plugins:</p>
      <ul>
      <li>Plugin for automatic download of PDF files from arXiv or DOI links</li>
      <li>Plugin to browse and import new preprints from arXiv</li>
      <li>Plugin to import articles from INSPIRE</li>
      </ul>


      <h4 id="renamer">Rename file plugin</h4>
      <p><em>by Sergey Kor</em></p>
      <p>
      This plugin allows to automatically rename/copy/delete attached files.
      Files can be renamed according to their BibTeX entry.
      The rename pattern uses the same syntax as a BibTeX key generator of JabRef. 
      <a href="https://github.com/korv/Jabref-plugins/downloads">Download</a><br/>
      JabRef 2.9 will contain the rename functionality as part of the &ldquo;Clean up&rdquo; functionality.
      </p>


      <h3>Developing plugins</h3>
      <p>A plugin is basically an implementation of one or more of JabRef's
      interfaces, packaged in a jar file with an XML file describing the
      plugin and which extension points it connects to.
      See our <a href="http://jabref.wiki.sourceforge.net/Getting+started+with+JabRef+plugin+development">
      Wiki page on getting started writing plugins</a>.</p>


      <h2>Entry fetchers</h2>

      <h3 id="ADS">JabRef ADS fetcher</h3>
      <p><em>by Ryo Igarashi</em></p>

      <p>JabRef plugin for fetching BibTeX entry from &ldquo;The SAO/NASA Astrophysics Data System&rdquo; using Digital Object Identifiers (DOI)
      <a href="https://launchpad.net/jabref-ads">More information</a></p>


      <h3 id="ADS_HUG">Modified JabRef ADS fetcher</h3>
      <p><em>by Alexander Hug</em></p>

      <p>This ADS fetcher is modified so one can ask for several parameters like authors, title, year etc. Most useful keywords are predefined in 
      combo-boxes, but may be overwritten by any valid keyword, as described on the ADS homepage and may also be looked up by the &ldquo;help&rdquo; button. 
      you may enter either &ldquo;hug, a&rdquo; and select &ldquo;Author&rdquo; from the combo-box, OR you enter &ldquo;author=hug, a&rdquo; and select &ldquo;None or special keyword&rdquo;.</p>

      <p>Version 0.1<br />
      <a href="plugins/ADSsearch-0.1.jar">Plugin</a> -
      <a href="plugins/ADSsearch-0.1-src.zip">Source code</a></p>


      <h3 id="GVK">GVK (Gemeinsamer Verbundkatalog) fetcher</h3>
      <p><em>by Gemeinsamen Bibliotheksverbund</em></p>
      <p><a href="http://www.gbv.de/wikis/cls/Jabref-GVK-Plugin">Home page (see also for source code)</a></p>
      <p>This plugin searches and fetcher entries from the GVK (Gemeinsamer Verbundkatalog).</p>


      <h2 id="export_filters">Export filters</h2>

      <p><acronym>JabRef</acronym> allows you to create custom export filters. This functionality is described in the help file
      on <a href="http://jabref.sourceforge.net/help/CustomExports.php">Custom export filters</a>. Some users have created export filters that
      can be useful to many others, and on this page we provide links or direct downloads for some of these export filters.</p>

      <p>If you have created one or more export filters that you want to share with other users, please notify us,
      and we can provide a download from this page, or link to your own page.</p>


      <h3 id="Marks_filters">Mark Schenk's HTML export filters</h3>
      <p><a href="http://www.markschenk.com/tools/jabref/">Mark Schenk's
      HTML export filters</a> provide HTML listings of your reference
      list. The exported HTML comes complete with scripts for quick
      filtering of the list.</p>
      <p><i>Note: some of Mark Schenk's filters
      are distributed with JabRef as standard export filters.</i></p>


      <h3 id="Martens_filters">Marten Kooiker's RTF export filters</h3>
      <p>Marten Kooiker has put together a number of export filters outputting
      RTF references formatted for specific journals.</p>
      <p><a href="exportfilters/Marten_Kooiker_export_filters_v1.0.zip">Download Marten Kooiker's RTF export filters</a></p>


      <h3 id="Edward_Elgar_filters">Edward Elgar export filters</h3>
      <p>Christian Bartolomaeus has made an export filter matching the author guidelines of Edward Elgar.
      The export filter requires definition of two name formatters - please read the included README file.</p>
      <p><a href="exportfilters/Edward_Elgar_export_filters_v1.0.zip">Download Edward Elgar export filters</a></p>


      <h3 id="RIS_export">RIS export format</h3>
      <p><em>by Morten O. Alver</em></p>

      <p>Version 1.0<br />
      <a href="plugins/net.sf.jabref.export.RISExport-1.0.jar">Plugin</a> -
      <a href="plugins/RISExport-1.0-src.zip">Source code</a></p>

      <p>This plugin provides RIS export format functionality.</p>


      <h3 id="Chicago_Manual">Chicago Manual of Style export filters</h3>
      <p><em>by Juan Jose Baldrich</em></p>

      <a href="plugins/net.sf.jabref.export.Chicago(English)-1.2.jar">Plugin (English)</a> -
      <a href="plugins/net.sf.jabref.export.Chicago(Espanol)-1.2.jar">Plugin (Espa&ntilde;ol)</a>

      <p>Export filters for preparation of bibliographies according to the Chicago Manual of Style.
      <a href="plugins/Chicago_export_filters.pdf">More information</a></p>


      <h3 id="Turabian">Turabian export filters</h3>
      <p><em>by Juan Jose Baldrich</em></p>

      <a href="plugins/net.sf.jabref.export.Turabian(English)-1.2.jar">Plugin (English)</a> -
      <a href="plugins/net.sf.jabref.export.Turabian(Espanol)-1.2.jar">Plugin (Espa&ntilde;ol)</a>

      <p>Export filters for preparation of bibliographies according to the Turabian style.</p>


      <h2 id="endnote_filters">Endnote filter set</h2>

      <p>The default EndNote Import filter does not properly parse authors. The
      EndNote <b>Import from JabRef</b> filter does. Additionally, this filter will
      recognize a field endnotereftype that will override the default
      mapping. To install the filter, download and extract the file
      <a href="plugins/EndNote.zip">EndNote.zip</a>. Then follow the directions in the file Readme.txt.</p>

      <h3 id="Modifying_EndNote_Reference_Types">Modifying EndNote Reference Types</h3>
      <p>Several fields used by BibTeX are not part of EndNote's default
      Reference Types. While import from JabRef and export to JabRef will
      work fine without modifying reference types, the field names will not
      display correctly in EndNote (e.g. an entry window will call the pdf
      field Custom 1 instead of pdf. Additionally, these fields cannot be
      added to new entries in EndNote, since they will not appear in the
      entry window. To modify the EndNote Reference Types, extract
      EndNote.zip and follow the directions in the file readme.txt.</p>

      <h3 id="Export_to_JabRef">Export to JabRef</h3>
      <p>EndNote comes with an export style BibTeX. However, this does not
      support all BibTeX types and fields, and does not support the
      additional General Fields supported by JabRef (pdf, owner, key, etc.)
      If you wish to have these fields supported, extract EndNote.zip and
      follow the directions in the file readme.txt.</p>


      <h2 id="abbrev_lists"><acronym>JabRef</acronym> journal abbreviation lists</h2>

      <p><acronym>JabRef</acronym> can help you refactor your reference list by
      automatically abbreviating or unabbreviating journal names. This
      requires that you keep one or more lists of journal names and their
      respective abbreviations. to set up these lists, choose 
      <b>Options -> Manage journal abbreviations</b>.</p>


      <h3 id="downloadlists">Downloadable journal lists</h3>
      <p><acronym>JabRef</acronym> includes no list of journals by default. Instead, the
      management window allows you to edit a personal list of
      journals, and to reference one or more external lists. To make this
      process more efficient for you, we offer a couple of lists from this
      web page, which can be downloaded by clicking a <b>Download</b> button
      in the <b>External files</b> section of the management window and
      entering the correct URL. You will then be asked to provide a local
      filename for the list.</p>

      <p>The download URL defaults to the general list linked below, which
      is a large list of journals, not limited to a single subject area. In
      addition there are (or will be) other, smaller, lists that contain
      journals from specific fields. To get one of these, just copy the URL
      from one of the links below, click <b>Download</b> in <acronym>JabRef</acronym>, and
      paste the URL into the query dialog that appears.</p>

      <h3 id="contribution">Contribution to journal abbreviation lists</h3>
      <p>We want to expand both the general list and the selection of
      smaller lists, so if you have set up a representative list for your
      own subject area, we would appreciate it if you share your list by
      opening a new entry in our 
      <a href="http://sourceforge.net/tracker/?group_id=92314&amp;atid=600308">Patches</a>
      section on SourceForge. You can either add your list as an attachment to the 
      patch entry, or keep the list on you own home page and link to it.
      We will then add your list to this page.</p>

    <?php include("footer.php"); ?>

    </div>
  </div>

</body>
</html>
