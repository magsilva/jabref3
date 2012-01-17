<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Field content selector</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Field content selector</h1>

    <p>This feature allows you to store
    a selection of often-used words or phrases that you use often
    in your database. By default it is enabled for the fields
    <em>Journal</em>, <em>Author</em>, <em>Keywords</em> and
    <em>Publisher</em>, but you can also add selectors to other
    fields, in the upper part of the <em>Manage</em> dialog (menu
    <strong>Tools --&gt; Manage content selectors</strong>).</p>

    <p>The word selection is database-specific, and is saved along
    with your references in the .bib file.</p>

    <p>To add a new word, you can simply write it into the selector
    combo box and press Enter. Using the <em>Manage</em> dialog you
    can also remove words that you have added.</p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
