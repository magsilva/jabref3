<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Export to an External SQL Database</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Export to an External SQL Database</h1>

    <p> JabRef is capable of
        exporting the contents of the BibTeX database, along with groups
    information, to an external MySQL database.</p>

    <h2>Setup</h2>
    <ol>
      <li> Using your favorite MySQL administration tool, create an empty MySQL database.</li>
      <li> Make sure there is a user for this database that has <em>full privileges</em>.</li>
    </ol>

    <h2>Export</h2>
    <ol>
      <li> Choose <b>File -&gt; Export to external SQL database</b>, or click the corresponding button on the toolbar.</li>
			<li> Enter the database connection information, and click <b>Connect</b>.</li>
    </ol>

		<p>
		JabRef will then connect to the specified database, <b><em>drop the existing tables</em></b>, create new
		tables, and populate those tables with entries and groups information.  Note that you will
		not be prompted for the connection information on subsequent exports.  If you would like to
		export to a different database, you can change the connection information by choosing <b>File -&gt;
		Connect to external SQL database</b> (or by clicking the associated toolbar button), and then
		performing an export.
    </p>

  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
