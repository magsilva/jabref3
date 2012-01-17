<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Import-Kontrollfenster</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Zur&uuml;ck zum Inhaltsverzeichnis</a>


    <h1>Import-Kontrollfenster</h1>

    <p>Beim Importieren neuer
    Eintr&auml;ge aus einem unterst&uuml;tzten Format oder beim
    Herunterladen von Eintr&auml;gen aus dem Internet werden diese
    Eintr&auml;ge zun&auml;chst im Import-Kontrollfenster gezeigt.
    Zu diesem Zeitpunkt wurden noch keine Eintr&auml;ge zu einer
    ge&ouml;ffneten Datei hinzugef&uuml;gt.</p>

    <p>Im Kontrollfenster k&ouml;nnen Sie Eintr&auml;ge entfernen,
    die nicht &uuml;bernommen werden sollen, und einige einfache
    Arbeiten durchf&uuml;hren wie etwa das Generieren von BibTeX
    keys f&uuml;r die Eintr&auml;ge oder das Zuordnen von
    Eintr&auml;gen zu <a href="GroupsHelp.php">Gruppen</a>. Wenn
    Sie in eine bestehende Datei importieren, ist es oftmals
    leichter, diese Arbeiten durchzuf&uuml;hren, bevor die neuen
    Eintr&auml;ge zwischen die bereits bestehenden sortiert
    wurden.</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
