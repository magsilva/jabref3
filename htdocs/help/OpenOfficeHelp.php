<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Using JabRef bibliographies in OpenOffice.org</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>
	

    <h1>Using JabRef bibliographies in OpenOffice.org</h1>

    <p>JabRef can export your database in both the OpenOffice.org 1.1
    <b>.sxc</b> spreadsheet format, and the OpenDocument
    <b>.ods</b> spreadsheet format used by OpenOffice.org 2.0.</p>

    <p>In both cases the exported spreadsheet will contain one data
    sheet, listing entries in rows and the various fields in
    columns. The order and names of the columns is set to be
    compatible with OpenOffice.org's bibliography functions (OOo
    1.1 in the case of the <b>.sxc</b> export, and OOo 2.0 in the
    case of the <b>.ods</b> format).</p>

    Depending on your version of OpenOffice.org, here are the method to use JabRef bibliographic databases:

    <h2>Using the exported file as bibliography database in
    OpenOffice.org 2.3 and 2.4</h2>

    <p>Use the following steps to set up a spreadsheet exported from
    JabRef as bibliography database in OpenOffice.org:</p>

    <ul>
    <li>From JabRef, export your database using the <b>.ods</b> format</li>
    <li>Start OpenOffice.org Writer</li>

    <li>Choose <b>Edit -&gt; Change database</b>. Click on <b>Select</b>, and select your database exported to the .ods format.</li>
    <li>Unfold the <b>+</b> located in front of the name of the imported database, then click on the displayed filename, and, finally, on the button <b>Define</b>.</li>
    <li>Choose <b>Tools -&gt; Options -&gt; OpenOffice.org Base -&gt; Database</b>. In this window, the database you just imported should be displayed. The default OOo bibliographic database should also be displayed (<i>Bibliography</i>).</li>
    <li>Edit the Bibliography database, and alter its name, such as <i>Bibliography-old</i> (in fact, OpenOffice.org Writer does not allow selecting several bibliographic databases).
    <li>Select your bibliographic database, edit it, and rename it <i>Bibliography</i> (pay a special attention to the capital letter at the beginning of the name).</li>
    </ul>

    After these steps, your bibliographic database should be ready for use with OpenOffice.org. To check about it, choose <b>Insert -&gt; Index -&gt; Bibliographic entry...</b>: the list of the BibTeX keys should be displayed.


    <h2>Using the exported file as bibliography database in
    OpenOffice.org 2.0, 2.1 and 2.2</h2>

    <p>Use the following steps to set up a spreadsheet exported from
    JabRef as bibliography database in OpenOffice.org:</p>

    <ul>
        <li>Export your database using the <b>.ods</b> format</li>

        <li>Start OpenOffice.org</li>

        <li>Choose <b>Tools -&gt; Options -&gt; OpenOffice.org Base
        -&gt; Databases</b></li>

        <li>Edit the <i>Bibliography</i> database, and change the
        name to something else, e.g. <i>Bibliography-old</i>
        (indeed, OpenOffice.orgWriter does not allow for several
        bibliographies)</li>

        <li>Close the <b>Options</b> window, and choose <b>File
        -&gt; New -&gt; Database</b></li>

        <li>Choose <b>Connect to an existing database</b>, select
        <b>Spreadsheet</b> as the database type, and choose the
        <b>.ods</b> file you exported</li>

        <li>Click <b>Finish</b>, then choose the name
        <i>Bibliography</i> when prompted</li>
    </ul>After finishing these steps, choose <b>Tools -&gt;
    Bibliography Database</b>. Your database should now be
    displayed.

    <h2>Using the exported file as bibliography database in
    OpenOffice.org 1.1.x</h2>

    <ul>
        <li>Export your database using the <b>.sxc</b> format</li>

        <li>Start OpenOffice.org</li>

        <li>Choose <b>Tools -&gt; Data sources</b></li>

        <li>Select the <i>Bibliography</i> database, and change its
        name to something else, e.g. <i>Bibliography-old</i>. Click
        <b>Apply</b>.</li>

        <li>Click <b>New Data Source</b>. A new entry will appear.
        Change its name to <i>Bibliography</i>.</li>

        <li>Change <b>Database type</b> to <b>Spreadsheet</b>.
        Click the <b>...</b> button in the <b>Data source URL</b>
        line. Choose the <b>.sxc</b> file you exported.</li>

        <li>Click <b>OK</b> to close the <b>Data Sources</b>
        window.</li>
    </ul>After finishing these steps, choose <b>Tools -&gt;
    Bibliography Database</b>. Your database should now be
    displayed.
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
