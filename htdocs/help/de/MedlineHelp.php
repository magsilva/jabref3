<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>Eintr&auml;ge von Medline abrufen</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Zur&uuml;ck zum Inhaltsverzeichnis</a>


    <h1>Eintr&auml;ge von Medline abrufen</h1>MEDLINE ist die
    wichtigste Datenbank der <em>U.S. National Library of
    Medicine</em>. Sie enth&auml;lt Literaturangaben von
    Zeitschriftenartikeln der Lebenswissenschaften, vornehmlich der
    Biomedizin. 

    <p>JabRef kann Literaturangaben von der Medline-Datenbank
    herunterladen. Um diese Funktion zu nutzen, w&auml;hlen Sie
    <b>Extras -&gt; Medline abrufen</b>, so dass der Medline-Dialog
    im linken Seitenfeld erscheint.</p>

    <p>Es gibt zwei M&ouml;glichkeiten, die Auswahl der
    Eintr&auml;ge vorzunehmen, die heruntergeladen werden
    sollen:</p>

    <ol>
        <li>Geben Sie eine oder mehr Medline IDs (getrennt durch
        Komma/Semikolon) in das Textfeld ein.</li>

        <li>Geben Sie Namen oder W&ouml;rter ein, nach denen
        gesucht werden soll. Sie k&ouml;nnen dazu die Operatoren
        <em>and</em> und <em>or</em> sowie Klammern benutzen, um
        Ihren Suchbegriff zu verfeinern.</li>
    </ol>In beiden F&auml;llen dr&uuml;cken Sie dann <b>Enter</b>
    oder die Schaltfl&auml;che <b>Abrufen</b>. Wenn Sie eine
    Textsuche durchf&uuml;hren, wird Ihnen die Anzahl der
    gefundenen Eintr&auml;ge angezeigt, und Sie k&ouml;nnen
    w&auml;hlen, wie viele Sie herunterladen m&ouml;chten. 

    <p>Die abgerufenen Eintr&auml;ge werden Ihrer zu diesem
    Zeitpunkt aktivierten Datei zugeordnet.</p>

    <h2>Benutzung eines Proxy-Servers</h2>Wenn Sie einen
    HTTP-Proxy-Server benutzen m&uuml;ssen, &uuml;bergeben Sie den
    Servernamen und die Portnummer an Java. Diese
    Umgebungseinstellungen sind dokumentiert unter 

    <p>
    http://java.sun.com/j2se/1.4.2/docs/guide/net/properties.html</p>

    <p><code>java -Dhttp.proxyHost="hostname"
    -Dhttp.proxyPort="portnumber"</code></p>
    <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
