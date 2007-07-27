<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>Zeitstempel</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Zur&uuml;ck zum Inhaltsverzeichnis</a>


    <h1>Zeitstempel</h1>Die Benutzung des Zeitstempels kann unter
    <b>Einstellungen -&gt; Allgemeines</b> an- und abgestellt sowie
    ver&auml;ndert werden. 

    <p>JabRef kann automatisch ein Feld setzen, das das Datum
    enth&auml;lt, an dem der Eintrag zur Datei hinzugef&uuml;gt
    wurde.</p>

    <h2>Formatierung</h2>Die Formatierung des Zeitstempels wird von
    einem Ausdruck mit Bezeichnern bestimmt, die die Position
    verschiedener Bestandteile des Datums angeben. 

    <p>Hier einige der verf&uuml;gbaren Bezeichner (die Beispiele
    in Klammern beziehen sich auf Mittwoch, den 14. September 2005,
    17:45 Uhr):</p>

    <ul>
        <li><b>yy</b>: Jahr (05)</li>

        <li><b>yyyy</b>: Jahr (2005)</li>

        <li><b>MM</b>: Monat (09)</li>

        <li><b>dd</b>: Tag (14)</li>

        <li><b>HH</b>: Stunde (17)</li>

        <li><b>mm</b>: Minute (45)</li>
    </ul>Diese Bezeichner k&ouml;nnen mit Interpunktion und
    Leerzeichen kombiniert werden. Hier einige Beispiele: 

    <ul>
        <li><b>yyyy.MM.dd</b> ergibt <b>2005.09.14</b></li>

        <li><b>yy.MM.dd</b> ergibt <b>05.09.14</b></li>

        <li><b>yyyy.MM.dd HH:mm</b> ergibt <b>2005.09.14
        17:45</b></li>
    </ul>
    <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
