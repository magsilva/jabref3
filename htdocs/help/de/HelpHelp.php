<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Das Hilfefenster</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Zur&uuml;ck zum Inhaltsverzeichnis</a>

    <basefont size="4" color="#2F4958" face="arial" />

    <h1>Das Hilfefenster</h1>

    <p><em>Das Hilfefenster &ouml;ffnet sich,
    wenn man den Hilfeknopf oder die Taste F1 dr&uuml;ckt.</em></p>

    <p>Das Hilfefenster soll Ihnen Informationen zur Nutzung von
    JabRef geben.</p>

    <h2>Navigation in den Hilfedateien</h2>

    <p>Das Hilfefenster ist
    eigentlich ein kleiner HTML-Browser, die Hilfedateien sind
    normale HTML-Dateien.</p>

    <p>Wenn Sie die Hilfe &ouml;ffnen, zeigt das Hilfefenster
    unterschiedliche Dateien an, je nachdem, von welchem
    Programmfenster aus Sie die Hilfe aufgerufen haben. Falls diese
    Datei nicht das beinhaltet, was Sie erkl&auml;rt haben wollten,
    k&ouml;nnen Sie mit dem <em>Inhalt</em>-Knopf in der
    Werkzeugleiste zu einer Liste aller verf&uuml;gbaren
    Hilfedateien gehen.</p>

    <p>Au&szlig;erdem befinden sich in der Werkzeugleiste
    Kn&ouml;pfe zum Zeigen der n&auml;chsten oder vorherigen Datei.
    Sie sind &auml;hnlich den <em>Zur&uuml;ck</em>- und
    <em>Vorw&auml;rts</em>-Kn&ouml;pfen eines gew&ouml;hnlichen
    HTML-Browsers. Die Tastenk&uuml;rzel zur Navigation sind
    STRG-SHIFT und Pfeil nach links f&uuml;r <em>Zur&uuml;ck</em>
    sowie Pfeil nach rechts f&uuml;r <em>Vorw&auml;rts</em>.</p>

    <p>Sie k&ouml;nnen das Hilfefenster schlie&szlig;en, indem Sie
    ESCAPE dr&uuml;cken.</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
