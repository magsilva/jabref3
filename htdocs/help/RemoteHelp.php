<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Remote operation</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Remote operation</h1>

    <p>This feature can be toggled and
    configured under <b>Preferences -&gt; Advanced</b>.</p>

    <p><i>Note that activating this feature under Windows XP SP2
    (and possibly other configurations) may prompt a message box
    stating that certain features of the program have been blocked
    by the Windows firewall. You can safely tell the firewall to
    keep blocking - the firewall will not interfere with remote
    operation of JabRef.</i></p>

    <p>If listening for remote operation is enabled, JabRef will at
    startup attempt to start listening to a specific port. This
    means that other applications can send information to JabRef
    through this port. JabRef will only accept local connections,
    to avoid the risk of interference from outside.</p>

    <p>Binding to this port makes it possible for a second JabRef
    instance to discover that the first one is running. In this
    case, unless specifically instructed to run in stand-alone
    mode, the second JabRef instance will pass its command line
    options through the port to the first JabRef instance, and then
    immediately quit.</p>

    <p>The first JabRef instance will read the command line
    options, and perform the indicated actions, such as reading or
    importing a file, or importing a file to the currently shown
    database. If a file is imported using the command-line option
    <code>--importToOpen</code>, the imported entries will be added
    to the currently shown database. If no database is open, a new
    one will be created.</p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
