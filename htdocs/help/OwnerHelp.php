<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>The 'owner' field</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>The 'owner' field</h1>

    <p>JabRef can optionally mark all new
    entries added or imported to a database with your username. You
    can disable or enable this feature by entering <b>Preferences
    -&gt; General</b>, and you can also change the name used to
    mark your entries. The default name used is your user name.</p>

    <p>The name will be added in a field called 'owner', which by
    default is visible in the <b>General fields</b> tab in the
    Entry Editor.</p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
