<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>The help window</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>
	

    <h1>The help window</h1>

    <p><em>Opened by clicking the help button or pressing F1.</em></p>

    <p>The help window is there to provide information about using
    JabRef.</p>

    <h2>Navigating the help files</h2>

    <p>The help window is in fact a lightweight HTML browser, and the
    help files are standard HTML files.</p>

    <p>When opened, the help window will default to different files
    depending on which program window it is called from. If this
    file doesn't explain the aspect you are interested in, the
    <em>Contents</em> button in the toolbar will take you to a list
    of the available help files.</p>

    <p>In addition the toolbar contains navigation buttons for
    showing the next or previous file. These are similar to the
    <em>Back</em> and <em>Forward</em> buttons of a standard web
    browser. The keyboard shortcuts for navigation are the arrow
    key left for <em>Back</em> and the arrow key right for
    <em>Forward</em>.</p>

    <p>The help window is closed by pressing ESCAPE.</p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
