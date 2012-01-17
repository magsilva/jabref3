<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Allgemeine Felder festlegen</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>

    <basefont size="4" color="#2F4958" face="arial" />

    <h1>Allgemeine Felder festlegen</h1>

    <p>Sie k&ouml;nnen dem Eintragseditor eine beliebige Anzahl von
    Tabs hinzuf&uuml;gen, die bei allen Eintragstypen sichtbar
    sind. W&auml;hlen Sie dazu den Men&uuml;eintrag "Optionen -&gt;
    Allgemeine Felder festlegen".</p>

    <p>Jede Zeile repr&auml;sentiert einen Tab. Der Anfang der
    Zeile steht f&uuml;r den Namen des Tabs, gefolgt von einem
    Doppelpunkt (:). Anschlie&szlig;end listen Sie die einzelnen
    Felder auf, die der Tab enthalten soll, und trennen sie jeweils
    durch ein Semikolon (;).</p>

    <p>Das Beispiel</p>

    <p><code>Allgemeine Felder:url;keywords;doi;pdf<br />
     Zusammenfassung:abstract;annote</code></p>

    <p>ergibt somit einen Tab mit dem Namen "Allgemeine Felder",
    der die Felder <em>url</em>, <em>keywords</em>, <em>doi</em>
    und <em>pdf</em> enth&auml;lt, sowie einen zweiten Tab namens
    "Zusammenfassung" mit den Feldern <em>abstract</em> und
    <em>annote</em>.</p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
