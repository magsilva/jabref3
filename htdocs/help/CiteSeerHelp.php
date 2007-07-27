<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>Importing From CiteSeer</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Importing From CiteSeer</h1>CiteSeer is a scientific
    literature digital library and search engine that focuses
    primarily on the literature in computer and information
    science. 

    <h2>Importing An Entry From CiteSeer</h2>JabRef can download
    information about a particular citation from the CiteSeer
    database. To start this process, add a new entry into your
    database and populate the citeseerurl field with a link to its
    content page on CiteSeer. The citeseerurl field must be in one
    of the following formats: 

    <p>http://citeseer.ist.psu.edu/DDDDDD[.html], or<br />
    oai:CiteSeerPSU:DDDDDD, or<br />
    DDDDDD</p>

    <p>where DDDDD is a sequence of digits. To find the sequence of
    digits (DDDDD) for a CiteSeer entry, goto the citation's
    document page of the format
    http://citeseer.ist.psu.edu/<i>nameYearTitle</i>.html and click
    on the (Update) link for this citation. The URL for the Update
    link will contain the numeric ID for this citation.</p>

    <p>Once you have populated the citeseerurl field, you may
    download the CiteSeer fields by selecting <b>BibTex -&gt;
    Import Fields from CiteSeer</b>. Make sure you have selected
    the row(s) you wish to update.</p>

    <h2>Generating a Citation Database</h2>Given a set of
    references, you can generate a list of documents that cite the
    elements of this set. To facilitate this feature, each citation
    in a database must have a citeseerurl field with the format
    specified in <b>Importing An Entry From CiteSeer</b>. To use
    this feature, select <b>Web Search -&gt; Fetch Citations from
    CiteSeer</b>. 

    <h2>Using a Proxy Server</h2>If you need to use an http proxy
    server, pass the server name and port number to java at
    runtime. These environment settings are documented at 

    <p>
    http://java.sun.com/j2se/1.4.2/docs/guide/net/properties.html</p>

    <p><code>java -Dhttp.proxyHost="hostname"
    -Dhttp.proxyPort="portnumber"</code></p>

    <p>&nbsp;</p>
    <?php include("../footer.php"); ?>
  </div>

</body>
</html>
