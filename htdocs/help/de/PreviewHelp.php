<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Eintragsvorschau einstellen</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Zur&uuml;ck zum Inhaltsverzeichnis</a>


    <h1>Eintragsvorschau einstellen</h1>

    <p>Die Eintragsvorschau wird
    mit denselben Mechanismen erstellt, die auch bei den
    <a href="CustomExports.php">Exportfiltern</a> angewendet
    werden. Bei der Vorschau durchl&auml;uft ein Eintrag eins von
    zwei m&ouml;glichen Layouts (die sie mit F9 wechseln
    k&ouml;nnen) und erstellt HTML-Code, der im Vorschaudialog
    angezeigt wird. Aussehen und Inhalt der Vorschau k&ouml;nnen
    mit derselben Syntax ver&auml;ndert werden, die in der Hilfe
    zur Anpassung der <a href="CustomExports.php">Exportfilter</a>
    beschrieben wird.</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
