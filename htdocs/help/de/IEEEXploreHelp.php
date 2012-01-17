<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>IEEEXplore durchsuchen</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Zur&uuml;ck zum Inhaltsverzeichnis</a>

    <basefont size="4" color="#2F4958" face="arial" />

    <h1>IEEEXplore durchsuchen</h1>

    <p>IEEEXplore bietet Zugang zu
    Literatur in den Bereichen Elektrotechnik, Informatik und
    Elektronik.</p>

    <p>JabRef kann Literaturangaben der IEEEXplore-Datenbank
    herunterladen. Um diese Funktion zu nutzen, w&auml;hlen Sie
    <b>Internet -&gt; IEEEXplore abfragen</b> und geben in dem
    Dialog, der im linken Bildschirmbereich erscheint, Ihre
    Suchausdr&uuml;cke ein. Dr&uuml;cken Sie anschlie&szlig;end die
    <b>Enter</b>-Taste oder den <b>Abrufen</b>-Button.</p>

    <p>Die Suche wird im G&auml;ste-Modus durchgef&uuml;hrt, es
    werden also maximal 100 Ergebnisse angezeigt.</p>

    <p>Sie k&ouml;nnen die <i>abstracts</i> zusammen mit den
    Literaturangaben herunterladen, indem Sie einen Haken bei
    <b>Abstracts ber&uuml;cksichtigen</b> setzen. Das verursacht
    eine deutlich h&ouml;here Anzahl von Netzwerk-Anfragen; um die
    Webseite von IEEEXplore nicht &uuml;berm&auml;&szlig;ig zu
    belasten, l&auml;dt JabRef <i>abstracts</i> nur bei einer
    Suche, deren Ergebnis eine bestimmte Trefferanzahl nicht
    &uuml;bersteigt.</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
