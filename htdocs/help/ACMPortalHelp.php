<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Search ACM Portal</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Search ACM Portal</h1>

    <p>The ACM Portal includes two databases: the ACM Digital Library that is a full text collection of every article published by ACM, including over 50 years of archives, and the Guide to Computing Literature that is a bibliographic collection from major publishers in computing with over one million entries.</p>

    <p>JabRef can download citations from the ACM Portal database.
    To use this feature, choose <b>Search -&gt; Web search</b>, and the search interface will appear in the
    side pane. Select <b>ACM Portal</b> in the dropdown menu.</p>

    <p>To start a search, enter the words of your query, and press
    <b>Enter</b> or the <b>Fetch</b> button.</p>

    <p>You may choose which database to search and you may opt to download the abstracts along with the cite information for each entry, by checking the <b>Include abstracts</b> checkbox.</p>

	<p>To avoid excessive pressure on
    the ACM Portal web site, JabRef will refuse to download
    entries for more than a given number.
	</p>

	<p>At this point, frequent connections to ACM Portal will get your IP banned for hours. So JabRef will wait 5 seconds between each connection and the download process will be very slow.
	</p>

	<p>Please email Aaron Chen <b>nextAaron@gmail.com</b> for any suggestions.</p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
