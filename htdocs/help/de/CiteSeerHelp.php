<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>CiteSeer-Import</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Zur&uuml;ck zum Inhaltsverzeichnis</a>


    <h1>CiteSeer-Import</h1>CiteSeer ist eine digitale Bibliothek
    und Suchmaschine f&uuml;r wissenschaftliche Literatur,
    vornehmlich zu den Bereichen Computer und Informatik. 

    <h2>Importieren eines Eintrags von CiteSeer</h2>JabRef kann
    Informationen &uuml;ber eine bestimmte Literaturangabe aus der
    CiteSeer-Datenbank herunterladen. Um diesen Vorgang zu starten,
    f&uuml;gen Sie Ihrer Datei einen neuen Eintrag hinzu und
    belegen das Feld <em>citeseerurl</em> mit einem Link zur
    entsprechenden Inhaltsseite auf CiteSeer. Das Feld
    <em>citeseerurl</em> muss in einem der folgenden Formate
    eingegeben werden: 

    <p>http://citeseer.ist.psu.edu/DDDDDD[.html], oder<br />
     oai:CiteSeerPSU:DDDDDD, oder<br />
     DDDDDD</p>

    <p>wobei DDDDDD eine Ziffernfolge darstellt. Um diese
    Ziffernfolge (DDDDDD) f&uuml;r einen CiteSeer-Eintrag zu
    finden, gehen Sie auf die Dokumentseite der Literaturangabe des
    Formats http://citeseer.ist.psu.edu/<i>nameYearTitle</i>.html
    und klicken den (Update)-Link f&uuml;r diese Literaturangabe.
    Die URL f&uuml;r den Update-Link beinhaltet die numerische ID
    f&uuml;r diese Literaturangabe.</p>

    <p>Sobald Sie das Feld <em>citeseerurl</em> belegt haben,
    k&ouml;nnen Sie die CiteSeer-Felder herunterladen, indem Sie
    <b>BibTeX -&gt; Felder von CiteSeer importieren</b>
    ausw&auml;hlen. Achten Sie darauf, dass Sie die Zeile(n)
    ausgew&auml;hlt haben, die Sie aktualisieren wollen.</p>

    <h2>Eine Datei mit zitierenden Literaturangaben
    erzeugen</h2>Mit einem Satz von Literaturangaben k&ouml;nnen
    Sie eine Liste der Dokumente erzeugen, die die einzelnen
    Literaturangaben ihrerseits zitieren. Dazu muss jede
    Literaturangabe der entsprechenden Datenbank-Datei ein
    ausgef&uuml;lltes citeseerurl-Feld besitzen, dessen Inhalt dem
    in <b>Importieren eines Eintrags von CiteSeer</b> beschriebenen
    Format entspricht. Sie k&ouml;nnen diese Funktion nutzen, indem
    Sie <b>Zitierende Literatur von CiteSeer abrufen</b>
    ausw&auml;hlen. 

    <h2>Benutzung eines Proxy-Servers</h2>Wenn Sie einen
    HTTP-Proxy-Server benutzen m&uuml;ssen, &uuml;bergeben Sie den
    Servernamen und die Portnummer an Java. Diese
    Umgebungseinstellungen sind dokumentiert unter 

    <p>
    http://java.sun.com/j2se/1.4.2/docs/guide/net/properties.html</p>

    <p><code>java -Dhttp.proxyHost="hostname"
    -Dhttp.proxyPort="portnumber"</code></p>

    <p>&nbsp;</p>
    <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
