<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Eintr&auml;ge markieren</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>

    <basefont size="4" color="#2F4958" face="arial" />

    <h1>Eintr&auml;ge markieren</h1>

    <p>Mit der Tastenkombination
    STRG-M k&ouml;nnen Sie Eintr&auml;ge <em>markieren</em> und
    diese Markierung mit STRG-SHIFT-M wieder aufheben. Diese
    Aktionen sind auch im Men&uuml; "Bearbeiten" zu finden.</p>

    <p>Das Markieren ist nicht dasselbe wie das Ausw&auml;hlen
    eines Eintrags. Markierte Eintr&auml;ge verhalten sich in
    zweierlei Hinsicht anders als die restlichen Eintr&auml;ge:</p>

    <ol>
        <li>Markierte Eintr&auml;ge werden immer mit einem gelben
        Hintergrund in der Tabelle angezeigt.</li>

        <li>Markierte Eintr&auml;ge werden an den Anfang der
        Eintragsliste verschoben, falls Ihre Tabelle nicht nach
        Gruppen oder aufgrund einer Suche sortiert ist.</li>
    </ol>

    <p>Wenn Sie Ihre Datei speichern, werden die Markierungen
    beibehalten.</p>

    <p>Das Markieren von Eintr&auml;gen ist z.B. n&uuml;tzlich,
    wenn Sie verschiedene Suchvorg&auml;nge durchf&uuml;hren
    wollen, ohne das erste Suchergebnis zu verlieren, oder wenn Sie
    sich merken wollen, welche Artikel oder B&uuml;cher Sie noch
    einmal genauer ansehen m&uuml;ssen.</p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
