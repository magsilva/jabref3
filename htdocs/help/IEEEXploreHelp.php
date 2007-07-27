<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>Search IEEEXplore</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Search IEEEXplore</h1>IEEEXplore delivers access to
    technical literature in electrical engineering, computer
    science, and electronics. 

    <p>JabRef can download citations from the IEEEXplore database.
    To use this feature, choose <b>Web search -&gt; Search
    IEEEXplore</b>, and the search interface will appear in the
    side pane.</p>

    <p>To start a search, enter the words of your query, and press
    <b>Enter</b> or the <b>Fetch</b> button.</p>

    <p>The search is done in guest mode, which means that a maximum
    of 100 results will be returned.</p>

    <p>You may opt to download the abstracts along with the cite
    information for each entry, by checking the <b>Include
    abstracts</b> checkbox. This causes a significantly larger
    amount of network queries, so to avoid excessive pressure on
    the IEEEXplore web site, JabRef will refuse to download
    abstracts for searches returning more than a given number of
    hits.</p>
    <?php include("../footer.php"); ?>
  </div>

</body>
</html>
