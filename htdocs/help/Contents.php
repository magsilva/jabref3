<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Help contents</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Help contents</h1>

    <h2>General</h2>
    <ul>
      <li><a href="HelpHelp.php">About the Help window</a></li>
      <li><a href="JabRefHelp.php">General information</a></li>
      <li><a href="BaseFrameHelp.php">The JabRef main window</a></li>
      <li><a href="EntryEditorHelp.php">Entry editor</a></li>
      <li><a href="Autosave.php">Autosave</a></li>
    </ul>

    <h2>Fields</h2>
    <ul>
      <li><a href="BibtexHelp.php"><em>Bibtex</em> help</a></li>
      <li><a href="FileLinks.php">External file links</a></li>
      <li><a href="ExternalFiles.php">PDF/PS/URL/DOI links in JabRef</a></li>
      <li><a href="TimeStampHelp.php">Entry time stamps</a></li>
      <li><a href="OwnerHelp.php">The 'owner' field</a></li>
      <li><a href="ContentSelectorHelp.php">Field content selector</a></li>
      <li><a href="JournalAbbreviations.php">Journal abbreviations</a></li>
    </ul>
    
    <h2>Finding and sorting entries</h2>
    <ul>
      <li><a href="GroupsHelp.php">Help on using <em>Groups</em></a></li>
      <li><a href="MarkingHelp.php">Marking entries</a></li>
      <li><a href="SearchHelp.php">Searching in JabRef</a></li>
    </ul>

    <h2>Setup</h2>
    <ul>
      <li><a href="StringEditorHelp.php">String editor</a></li>
      <li><a href="DatabaseProperties.php">Database properties window</a></li>
      <li><a href="PreviewHelp.php">Entry preview setup</a></li>
      <li><a href="LabelPatterns.php">Customizing the BibTex key generator</a></li>
      <li><a href="CustomEntriesHelp.php">Customizing entry types</a></li>
      <li><a href="GeneralFields.php">Customizing general fields</a></li>
      <li><a href="Plugin.php">Extend JabRef using plugins</a></li>
    </ul>

    <h2>Import/Export</h2>
    <ul>
      <li><a href="CustomExports.php">Custom export filters</a></li>
      <li><a href="CustomImports.php">Custom import filters</a></li>
      <li><a href="ImportInspectionDialog.php">Import inspection window</a></li>
      <li><a href="EndNoteFilters.php">The EndNote import/export filter set</a></li>
      <li><a href="OpenOfficeIntegration.php">Using JabRef with OpenOffice.org or LibreOffice</a></li>
      <li><a href="ACMPortalHelp.php">Fetching entries from <em>ACM</em> Portal</a></li>
      <li><a href="CiteSeerHelp.php">Fetching entries from <em>CiteSeerX</em></a></li>
      <li><a href="IEEEXploreHelp.php">Fetching entries from <em>IEEExplore</em></a></li>
      <li><a href="MedlineHelp.php">Fetching entries from <em>Medline</em></a></li>
      <li><a href="JSTOR.php"><em>JStor</em> search</a></li>
      <li><a href="ScienceDirect.php"><em>ScienceDirect</em> search</a></li>
      <li><a href="Spires.php"><em>Spires</em> search</a></li>
      <li><a href="SQLExport.php">Export to an External SQL Database</a></li>
      <li><a href="XMPHelp.php">XMP metadata support in JabRef</a></li>
      <li><a href="CommandLine.php">Command line options</a></li>
      <li><a href="RemoteHelp.php">Remote operations</a></li>
    </ul>

    <h2>Miscellaneous</h2>
    <ul>
      <li><a href="RevisionHistory.php">Revision history</a></li>
      <li><a href="About.php">About JabRef</a></li>
    </ul>

  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
