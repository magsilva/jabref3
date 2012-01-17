<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>ScienceDirect search</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


  <h1>ScienceDirect search</h1>

  <p>To use this feature, choose <b>Search -&gt; Web search</b>, and the
   search interface will appear in the side pane. Select <b>ScienceDirect</b> in the dropdown menu.</p>

    <p>The ScienceDirect search relies on the BibSonomy scraper project (http://scraper.bibsonomy.org/) to
    extract information from the ScienceDirect web page.</p>

    <p>This fetcher attempts to run a quick search in the ScienceDirect web site, resulting in
        up to 100 hits. All hits are then imported.</p>

  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
