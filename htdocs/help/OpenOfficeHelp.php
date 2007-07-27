<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>Using JabRef bibliographies in OpenOffice.org</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Using JabRef bibliographies in OpenOffice.org</h1>JabRef
    can export your database in both the OpenOffice.org 1.1
    <b>.sxc</b> spreadsheet format, and the OpenDocument
    <b>.ods</b> spreadsheet format used by OpenOffice.org 2.0. 

    <p>In both cases the exported spreadsheet will contain one data
    sheet, listing entries in rows and the various fields in
    columns. The order and names of the columns is set to be
    compatible with OpenOffice.org's bibliography functions (OOo
    1.1 in the case of the <b>.sxc</b> export, and OOo 2.0 in the
    case of the <b>.ods</b> format).</p>

    <h2>Using the exported file as bibliography database in
    OpenOffice 2.0 or newer</h2>Use the following steps to set up a
    spreadsheet exported from JabRef as bibliography database in
    OpenOffice.org: 

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
    OpenOffice 1.1.x</h2>

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
