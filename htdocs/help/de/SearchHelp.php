<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Suchfunktionen</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Zur&uuml;ck zum Inhaltsverzeichnis</a>


    <h1>Suchfunktionen</h1>

    <p><em>STRG-F</em> &ouml;ffnet oder aktiviert den Suchdialog.
    Dr&uuml;ckt man mehrmals auf <em>STRG-F</em>, so werden die
    verschiedenen Suchmodi ausgew&auml;hlt. Bei der direkten Suche
    springt man mit <em>STRG-F</em> zum n&auml;chsten Treffer.</p>

    <p><em>STRG-SHIFT-F</em> &ouml;ffnet oder aktiviert den
    Suchdialog und w&auml;hlt die direkte Suche aus. Bei der
    direkten Suche springt man mit <em>STRG-SHIFT-F</em> ebenfalls
    zum n&auml;chsten Treffer.</p>

    <h2>Direkte Suche</h2>

    <p>Bei der direkten Suche sucht das Programm
    sofort, sobald Sie einen Buchstaben eingeben. Die Statuszeile
    informiert Sie &uuml;ber den Sucherfolg. Mit den
    Tastaturk&uuml;rzeln wird das Programm dazu veranlasst, nach
    dem n&auml;chsten Vorkommen des aktuellen Suchbegriffs zu
    suchen. Falls es keine weiteren Vorkommen gibt, informiert die
    Statuszeile sie dar&uuml;ber. Bei erneuter Wiederholung startet
    die Suche vom Anfang der Datei. Die Suchreihenfolge richtet
    sich nach der aktuellen Sortierung Ihrer Datei. Um die direkte
    Suche zu verlassen, dr&uuml;cken Sie ESC oder klicken Sie auf
    "Zur&uuml;cksetzen".</p>

    <h2>Normale Suche</h2>

    <p>Hierbei sucht das Programm nach allen
    Vorkommen der W&ouml;rter ihres Suchausdrucks, sobald Sie ENTER
    dr&uuml;cken. Nur Eintr&auml;ge, die alle W&ouml;rter
    enthalten, gelten als Treffer. Um nach festen Ausdr&uuml;cken
    zu suchen, m&uuml;ssen Sie die W&ouml;rter in doppelte
    Anf&uuml;hrungszeichen einfassen. Zum Beispiel findet die
    Suchanfrage <b>progress "marine acquaculture"</b>
    Eintr&auml;ge, die sowohl das wort "progress" als auch den
    Ausdruck "marine acquaculture" aufweisen. Alle Eintr&auml;ge,
    die keine Treffer sind, werden entweder ausgeblendet, so dass
    nur die Treffer sichtbar sind (Option <b>Filter</b>), oder sie
    werden grau dargestellt, w&auml;hrend die Treffer oben
    angezeigt werden (Option <b>Oben einsortieren</b>). Um die
    Trefferanzeige zu beenden, dr&uuml;cken Sie ESC oder klicken
    auf die Schaltfl&auml;che <b>Zur&uuml;cksetzen</b> im
    Suchen-Dialog.</p>

    <h2><a name="advanced"
       id="advanced">Feldbezeichner und logische
       Operatoren</a></h2>

       <p>Um nur einige bestimmte Felder zu
       durchsuchen und/oder logische Operatoren im Suchbegriff zu
       benutzen, wird eine spezielle Syntax zur Verf&uuml;gung
       gestellt. Um beispielsweise nach Eintr&auml;gen mit dem
       Autor "Miller" zu suchen, geben Sie</p>

    <p>author = miller</p>

    <p>in das Suchfeld ein (Achtung! Das funktioniert nicht bei der
    direkten Suche!). Falls der Suchbegriff Leerzeichen
    enth&auml;lt, schlie&szlig;en Sie ihn in Anf&uuml;hrungszeichen
    ein. Benutzen Sie <i>nie</i> Leerzeichen in dem Feldbezeichner.
    Um beispielsweise nach Eintr&auml;gen &uuml;ber Karl den
    Gro&szlig;en zu suchen, geben Sie folgendes ein:</p>

    <p>title|keywords = "Karl der Gro&szlig;e"</p>

    <p>Sie k&ouml;nnen "and", "or", "not" und Klammern
    verwenden:</p>

    <p>(author = miller or title|keywords = "Karl der Gro&szlig;e")
    and not author = brown</p>

    <p>... sucht nach Eintr&auml;gen, in denen entweder der Autor
    "Miller" hei&szlig;t oder im title- oder keywords-Feld der
    Begriff "Karl der Gro&szlig;e" steht; gleichzeitig werden die
    Eintr&auml;ge mit dem Autor "Brown" nicht angezeigt.</p>

    <p>Das "="-Zeichen ist eigentlich eine Abk&uuml;rzung f&uuml;r
    "enth&auml;lt" ("contains"). Wenn man nach genauen Treffern
    suchen m&ouml;chte, muss man "==" oder "matches"
    ("&uuml;bereinstimmen") eingeben. "!=" sucht nach
    Eintr&auml;gen, bei denen der Suchbegriff <i>nicht</i>
    enthalten ist. Die Auswahl von Feldern, die durchsucht werden
    sollen (ben&ouml;tigte, optionale, allgemeine Felder), wird
    ignoriert, wenn man im Suchausdruck Feldbezeichner verwendet.
    Um nach Eintr&auml;gen eines bestimmten Typs zu suchen, gibt es
    ein Pseudofeld namens "entrytype":</p>

    <p>entrytype = thesis</p>

    <p>&hellip; findet z.B. Eintr&auml;ge, deren Typ (wie in der
    Spalte "Entrytype" dargestellt) das Wort "thesis" enth&auml;lt
    (z.B. "phdthesis" und "mastersthesis"). Ebenso erlaubt das
    Pseudofeld "bibtexkey" die Suche nach BibTeX-Keys, z.B.:</p>

    <p>bibtexkey = miller2005</p>

    <h2>Suchoptionen</h2>

    <p>Der <em>Einstellungen</em>-Knopf
    &ouml;ffnet ein Men&uuml; mit mehreren Optionen: das Beachten
    von Gro&szlig;- und Kleinschreibung, das Nutzen regul&auml;rer
    Ausdr&uuml;cke sowie das Ausw&auml;hlen der Suchergebnisse in
    der Tabelle und das Hervorheben der einzelnen Suchwörter in dem
    Editor sowie der Vorschau.</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
