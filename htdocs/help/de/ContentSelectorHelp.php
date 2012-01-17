<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Wortauswahl verwalten</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Zur&uuml;ck zum Inhaltsverzeichnis</a>


    <h1>Wortauswahl verwalten</h1>

    <p>Diese Funktion erm&ouml;glicht
    es, eine Auswahl von oft benutzten W&ouml;rtern oder Phrasen zu
    speichern. In der Standardeinstellung ist sie f&uuml;r die
    Felder <em>Journal</em>, <em>Keywords</em> und
    <em>Publisher</em> aktiviert, aber Sie k&ouml;nnen die Funktion
    auch in anderen Feldern verwenden, indem Sie diese Felder im
    oberen Teil des Dialogs <em>Extras -&gt; Wortauswahl
    verwalten</em> hinzuf&uuml;gen.</p>

    <p>Die Wortauswahl gilt nur f&uuml;r die jeweilige Datei und
    wird daher zusammen mit den Literaturangaben in der .bib-Datei
    gespeichert.</p>

    <p>Jedes Feld mit Wortauswahl erh&auml;lt im Eintrags-Editor
    einen gesonderten Bereich mit einer Auswahlliste und der
    Schaltfl&auml;che <em>Verwalten</em>. Wenn Sie die Auswahlliste
    mit der Maus anklicken, erscheint eine Liste der f&uuml;r
    dieses Feld verf&uuml;gbaren W&ouml;rter. Sobald Sie auf das
    gew&uuml;nschte Wort klicken, wird es in das entsprechende Feld
    geschrieben.</p>

    <p>Um ein neues Wort hinzuzuf&uuml;gen, schreiben Sie es
    einfach in das Auswahlfeld und dr&uuml;cken ENTER. Im Dialog
    <em>Verwalten</em> k&ouml;nnen Sie hinzugef&uuml;gte
    W&ouml;rter auch wieder l&ouml;schen.</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
