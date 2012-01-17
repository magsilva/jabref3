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

    <h3>MySQL</h3>
    <ol> 
      <li> Using your favorite MySQL administration tool, create an empty MySQL database.</li>
      <li> Make sure there is a user for this database that has <em>full privileges</em>.</li>
    </ol>
    <h3>PostgreSQL</h3>
    <ol> 
      <li> Using your favorite PostgreSQL administration tool (PGAdminIII, PHPpgadmin, etc.), create an empty PostgreSQL database.  Alternately, the command line utility <em>createdb</em> may be used as follows:<br />
      <b>createdb -h [HOST] -U [USERNAME] [DBNAME] -E UTF8 --lc-ctype=en_US.utf-8 -T template0</b>

	<p>The assumption being that the language is english and the locale is US. If a password is required, the server will prompt for one from the command line.  See the createdb man page for more information.</p>
      </li>
      <li> Make sure there is a user for this database that has <em>full privileges</em>.</li>
    </ol>

    <h2>Export</h2>
    <ol>
      <li> Choose <b>File -&gt; Export to external SQL database</b>, or click the corresponding button on the toolbar.</li>
      <li> Select the database type from the drop down menu for <em>Server Type</em>.</li>
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

  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
