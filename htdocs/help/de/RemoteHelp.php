<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="de" xml:lang="de">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Externer Zugriff</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Zur&uuml;ck zum Inhaltsverzeichnis</a>


    <h1>Externer Zugriff</h1>

    <p>Diese Funktion kann unter
    <b>Einstellungen -&gt; Erweitert</b> eingestellt werden.</p>

    <p><i>Beachten Sie, dass das Aktivieren dieser Funktion mit
    Windows XP SP2 (und m&ouml;glicherweise auch mit anderen
    Konfigurationen) zu einer Meldung f&uuml;hren kann, die besagt,
    dass bestimmte Funktionen des Programms von der
    Windows-Firewall geblockt wurden. Sie k&ouml;nnen die Firewall
    anweisen, weiterhin zu blocken, denn die Firewall
    beeintr&auml;chtigt den Externen Zugriff von JabRef
    nicht.</i></p>

    <p>Falls das Abh&ouml;ren von externen Zugriffen aktiviert ist,
    versucht JabRef beim Programmstart, den entsprechenden Port
    abzuh&ouml;ren. Das bedeutet, dass andere Anwendungen
    Informationen durch diesen Port an JabRef senden k&ouml;nnen.
    JabRef akzeptiert dabei nur lokale Verbindungen, um das Risiko
    eines Eingriffs von au&szlig;erhalb auszuschlie&szlig;en.</p>

    <p>Mit dem externen Zugriff kann eine zweite Instanz von JabRef
    erkennen, dass eine erste Instanz bereits l&auml;uft. In diesem
    Fall leitet die zweite Instanz ihre Kommandozeilen-Optionen an
    die erste Instanz weiter und beendet sich selbst direkt im
    Anschluss - sofern die zweite Instanz nicht ausdr&uuml;cklich
    instruiert wurde, im Stand-Alone-Modus (als selbst&auml;ndige
    Instanz) zu starten.</p>

    <p>Die erste JabRef-Instanz liest die Kommandozeilenoptionen
    und f&uuml;hrt die erforderlichen Aktionen aus, z.B. das Lesen
    oder Importieren einer Datei oder das Anh&auml;ngen einer Datei
    an die aktive Datenbank. Falls eine Datei mit der Option
    <code>--importToOpen</code> importiert wird, werden die
    Eintr&auml;ge an die aktive Datei angeh&auml;ngt. Falls keine
    Datei ge&ouml;ffnet ist, wird eine neue Datei angelegt.</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
