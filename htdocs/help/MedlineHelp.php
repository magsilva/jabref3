<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>Fetching Medline entries</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Fetching Medline entries</h1>MEDLINE is the U.S. National
    Library of Medicine's premier bibliographic database. It
    contains references to journal articles in life sciences with a
    concentration on biomedicine. 

    <p>JabRef can download citations from the Medline database. To
    use this feature, choose <b>Web search -&gt; Fetch Medline</b>,
    and the Medline interface will appear in the side pane.</p>

    <p>There are two ways of specifying which entries to
    download:</p>

    <ol>
        <li>Enter one or more Medline IDs (separated by
        comma/semicolon) in the text field.</li>

        <li>Enter a set of names and/or words to search for. You
        can use the operators <em>and</em> and <em>or</em> and
        parentheses to refine your search expression.</li>
    </ol>In both cases, press <b>Enter</b> or the <b>Fetch</b>
    button. If you use a text search, you will be prompted with the
    number of entries found, and given a choice of how many to
    download. 

    <p>The entries fetched will be added to your currently active
    database.</p>

    <h2>Using a Proxy Server</h2>If you need to use an http proxy
    server, pass the server name and port number to java at
    runtime. These environment settings are documented at 

    <p>
    http://java.sun.com/j2se/1.4.2/docs/guide/net/properties.html</p>

    <p><code>java -Dhttp.proxyHost="hostname"
    -Dhttp.proxyPort="portnumber"</code></p>
    <?php include("../footer.php"); ?>
  </div>

</body>
</html>
