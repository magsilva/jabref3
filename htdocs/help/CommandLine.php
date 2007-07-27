<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>Command line options</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Command line options</h1>Although JabRef is primarily a GUI
    based application, it offers several command line options that
    may be useful, and can even perform file conversion operations
    without opening the graphical interface. 

    <p>You can always specify one or more BibTeX files to load by
    simply listing their filenames. Take care to specify all
    options before your list of file names. You must make sure the
    first file name is not misunderstood as being an argument for
    an option - this simply means that if a boolean option like
    <code>-n</code> or <code>-l</code> immediately precedes a file
    name, add the word "true" as an argument. For instance, the
    command line:</p>

    <p><code>jabref -o filetoexport.xml,docbook -n true
    original.bib</code></p>

    <p>will correctly load the file <code>original.bib</code>,
    export it in docbook format to <code>filetoexport.xml</code>,
    and suppress the GUI. The word <em>true</em> prevents the file
    name from being interpreted as an argument to the -n
    option.</p>

    <h2>Help: -h</h2>This option causes JabRef to display a summary
    of the command line options, and quit immediately. 

    <h2>No-GUI mode: -n</h2>This option suppresses both the JabRef
    window and the splash window normally shown at startup. It
    causes the program to quit immediately once the command line
    options have been processed. 

    <p>This option is useful for performing file conversion
    operations from the command line or a script.</p>

    <h2>Load session: -l</h2>This option causes JabRef to load the
    saved session, if any, before opening the main window. 

    <h2>Import file: -i filename[,format]</h2>This option causes
    JabRef to import or load the specified file. If only a filename
    is specified, or the filename is followed by a comma and a *
    character, JabRef will attempt to detect the file format
    automatically. This should work for all BibTeX files and files
    in any import format supported by JabRef. If the filename is
    followed by a comma and the name of an import format, the given
    import filter will be used. Use the <code>-h</code> option to
    get a list of availiable import formats. 

    <p>If an output option is also specified, an import will always
    be processed before it, and the imported or loaded file will be
    given to the export filter. If the GUI is not suppressed using
    the <code>-n</code> option, any imported or loaded file will
    show up in the main window.</p>

    <p>The -i option can be specified only once, and for one file
    only.</p>

    <h2>Export file: -o filename[,format]</h2>This option causes
    JabRef to save or export a file imported or loaded by the same
    command line. If a file is imported using the <code>-i</code>
    option, that database will be exported. Otherwise, the
    <em>last</em> file specified (and successfully loaded) without
    the <code>-i</code> option will be exported. 

    <p>If only a filename is specified, it will be saved in BibTeX
    format. If the filename is followed by a comma and an export
    format, the given export filter will be used. A custom export
    filter can be used in this way, and will be preferred if the
    export name matches both a custom and a standard export
    filter.</p>

    <p>Use the <code>-h</code> option to get a list of availiable
    export formats.</p>

    <p>If the <code>-n</code> option has not been invoked, any
    export operation will be performed before the JabRef window is
    opened, and the imported database will show up in the
    window.</p>

    <p>The -o option can be specified only once, and for one file
    only.</p>

    <h2>Export preferences: -x filename</h2>Using this option, you
    can have JabRef export all user preferences to an XML file.
    After exporting, JabRef will start normally. 

    <h2>Import preferences: -p filename</h2>This option causes
    JabRef to import user preferences exported using the
    <code>-x</code> option. After importing, JabRef will start
    normally. 

    <h2>Export only used items: -a
    filename[.aux],newBibFile[.bib]</h2>Sometimes it is helpful, to
    have a bibtex file that contains only the used bibtex entries.
    A list of these used entries is located in an aux file. Jabref
    can parse this file to generate a new bibtex file, which
    contains only the known and used entries. That will mean, if an
    entry is not defined in the standard bibtex file, it cannot be
    located in the new file.
    <?php include("../footer.php"); ?>
  </div>

</body>
</html>
