<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="de" xml:lang="de">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Export in eine externe SQL-Datenbank</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Zur&uuml;ck zum Inhaltsverzeichnis</a>


    <h1>Export in eine externe SQL-Datenbank</h1>

    <p> JabRef kann Inhalte einer BibTeX-Datei in eine MySQL- und PostgreSQL-Datenbank exportieren.
    Auch die Informationen zu Gruppen werden dabei ber&uuml;cksichtigt.</p>

	<p>Dazu ist es nur n&ouml;tig, eine Benutzer-Passwort-Kombination, die volle Rechte auf die Datenbank hat, zur Hand zu haben.</p>

    <h2>Export</h2>
    <ol>
      <li> W&auml;hlen Sie <b>Datei -&gt; Export in externe SQL-Datenbank</b> oder
           klicken Sie auf das entsprechende Symbol in der Symbolleiste.</li>
	  <li> Geben Sie die Informationen zur Datenbank-Verbindung ein und klicken auf <b>Verbinden</b>.</li>
    </ol>

		<p>
		JabRef baut dann die Verbindung zu dieser Datenbank auf, <b>l&ouml;scht
        existierende Tabellen</b>, erstellt neue Tabellen und f&uuml;gt in diese
        den Inhalt der Eintr&auml;ge und Gruppeninformationen ein. Falls Sie ein
        weiteres Mal eine Verbindung zu dieser Datenbank aufbauen wollen,
        m&uuml;ssen Sie die Verbindungs-Informationen nicht noch einmal eingeben.
        Wenn Sie in eine andere Datenbank exportieren wollen, k&ouml;nnen Sie die
        Verbindungs-Einstellungen unter <b>Datei -> Mit externer SQL-Datenbank
        verbinden</b> (oder durch Klicken des entsprechenden Symbols) &auml;ndern
        und anschlie&szlig;end den Export durchf&uuml;hren.
    </p>

  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
