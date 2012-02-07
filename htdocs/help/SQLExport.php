<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
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
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Export to an External SQL Database</h1>

    <p> JabRef is capable of
        exporting the contents of the BibTeX database, along with groups
    	information, to an external MySQL or PostgreSQL database.</p>

		<p>You just need to be sure you have an user/password with full privileges on a MySQL or PostgreSQL server.</p>

    <h2>Export</h2>
    <ol>
      <li> Choose <b>File -&gt; Export to external SQL database</b>, or click the corresponding button on the toolbar.</li>
      <li> Select the database type from the drop down menu for <em>Server Type</em>.</li>
      <li> Enter the database connection information, and click <b>Connect</b>.</li>
    </ol>

		<p>
		JabRef will then connect to the specified database, create new tables, and populate those tables with entries and groups information.
		You will be able to export as many JabRef bib databases as you want without losing the previously explored data.
		The system recognize a database uniquely by its full path (directory structure + filename). In case you export the same JabRef database 
		more than once, the data of that database will be update in the SQL database.
		Note that you will not be prompted for the connection information on subsequent exports.  If you would like to
		export to a different database, you can change the connection information by choosing <b>File -&gt;
		Connect to external SQL database</b> (or by clicking the associated toolbar button), and then
		performing an export.
		Since version 2.8 tables are not dropped, and user is able to store more than one JabRef database into a single SQL database.
    </p>	

	<p>When importing a database from an SQL database (<b>File -&gt; Import from external SQL database</b>), JabRef will place each database found in a different tab.</p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
