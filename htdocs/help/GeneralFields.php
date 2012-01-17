<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Customizing general fields</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Customizing general fields</h1>

    <p>You can add an arbitrary number of tabs to the entry editor.
    These will be present for all entry types. To customize these
    tabs, go to "Options -&gt; Set up general fields".</p>

    <p>You specify one tab on each line. The line should start with
    the name of the tab, followed by a colon (:), and the fields it
    should contain, separated by semicolons (;).</p>

    <p>E.g.<br />
    <code>General:url;keywords;doi;pdf<br />
     Abstract:abstract;annote</code></p>

    <p>which would give one tab named "General" containing the
    fields <em>url</em>, <em>keywords</em>, <em>doi</em> and
    <em>pdf</em>, and another tab named "Abstract" containing the
    fields <em>abstract</em> and <em>annote</em>.</p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
