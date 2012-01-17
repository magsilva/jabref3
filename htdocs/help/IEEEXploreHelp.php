<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Search IEEEXplore</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>

    <h1>Search IEEEXplore</h1>

    <p>To use this feature, choose <b>Search -&gt; Web search</b>, and the search
    interface will appear in the side pane. Select <b>IEEEXplore</b> in the dropdown menu.</p>

    <p>IEEEXplore delivers access to technical literature in electrical engineering, computer
    science, and electronics. To start a search, enter the words of your query, and press
    <b>Enter</b> or the <b>Fetch</b> button.</p>

    <p>The search is done in guest mode, which means that a maximum
    of 100 results will be returned.</p>

    <p>You may opt to download the abstracts along with the cite
    information for each entry, by checking the <b>Include
    abstracts</b> checkbox. This will NOT cause more network queries.</p>

    <p>The option to download BibTeX citations directly from IEEEXplore is not working yet.</p>

    <p>Please email Aaron Chen <b>nextAaron@gmail.com</b> for any suggestions.</p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
