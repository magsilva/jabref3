<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>File links in JabRef</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>File links in JabRef</h1>JabRef lets you link up your
    entries with files of any type stored on your system, as well
    as links to the document on the web in the form of an URL or a
    DOI identifier. Each entry can have an arbitrary number of file
    links, and each linked file can be opened quickly from JabRef. 

    <p>In BibTeX terms, the file links of an entry are encoded in a
    single field. However, from within JabRef they appear as an
    editable list of links accessed from the entry editor along
    with other BibTeX fields.</p>

    <h2>Setting up external file types</h2>For each file link, a
    file type must be chosen, to determine what icon should be used
    and what application should be called to open the file. The
    list of file types can be viewed and edited by choosing
    <b>Options -&gt; Manage external file types</b>, or by clicking
    the <b>Manage external file types</b> button in the <b>External
    programs</b> tab of the Preferences dialog. 

    <p>A file type is specified by its name, a graphical icon, a
    file extension and an application to view the files. On
    Windows, the name of the application can be omitted in order to
    use Window's default viewer instead.</p>

    <h2>Adding external links to an entry</h2>If the "file" field
    is included in <a href="GeneralFields.php">General fields</a>,
    you can edit the list of external links for an entry in the
    <a href="EntryEditorHelp.php">Entry editor</a>. The editor
    includes buttons for inserting, editing and removing links, as
    well as buttons for reordering the list of links. 

    <p>If you have a file within or below your file directory (set
    up in <b>Preferences -&gt; External programs -&gt; External
    file links -&gt; Main file directory</b>) with an extension
    matching one of the defined external file types, and a name
    containing a BibTeX entry's BibTeX key, the file can be
    autolinked by clicking on the <b>Auto</b> button in the entry
    editor. The rules for which file names can be autolinked to a
    BibTeX key can be set up in <b>Preferences -&gt; External
    programs -&gt; External file links -&gt; Use regular expression
    search</b>.</p>

    <p>If you want to download a file and link to it from a BibTeX
    entry, you can do this by clicking the <b>Download</b> button
    in the entry editor. A dialog box will appear, prompting you to
    enter the URL. The file will be downloaded to your main file
    directory, named based on the entry's BibTeX key, and finally
    linked from the entry.</p>

    <h2>Opening external files</h2>There are several ways to open
    an external file or web page. In the entry table you can select
    an entry and use the menu choice, keyboard shortcut or the
    right-click menu to open an entry's first external link.
    Alternatively, if the entry table is set up to show the
    <b>file</b> column (set up in <b>Preferences -&gt; Entry table
    -&gt; Special table columns -&gt; Show file column</b>), you
    can click on the file icon to open an entry's first link. To
    access any of an entry's links, click on the icon with the
    right mouse button (or <b>Ctrl-click</b> on Max OS X) to bring
    up a menu showing all links.
    <?php include("../footer.php"); ?>
  </div>

</body>
</html>