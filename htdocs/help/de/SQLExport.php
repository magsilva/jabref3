<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de">
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

    <basefont size="4" color="#2F4958" face="arial" />

    <h1>Export in eine externe SQL-Datenbank</h1>

    <p> JabRef kann Inhalte einer BibTeX-Datei in eine MySQL-Datenbank exportieren.
    Auch die Informationen zu Gruppen werden dabei ber&uuml;cksichtigt.</p>

    <h2>Einstellungen</h2>
    <ol>
      <li> Erstellen Sie mit einem beliebigen MySQL-Administrationsprogramm
           eine leere MySQL-Datenbank.</li>
      <li> Vergewissern Sie sich, dass es einen Benutzer ('user') f&uuml;r diese
           Datenbank gibt, der <em>alle Rechte</em> hat.</li>
    </ol>

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
