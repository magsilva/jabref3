<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>EndNote Exportfilter</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Zur&uuml;ck zum Inhaltsverzeichnis</a>


    <h1>EndNote Exportfilter</h1>

    <h2>Exportieren aus JabRef</h2>JabRef kann Dateien so
    exportieren, dass EndNote sie lesen kann. Um diese Funktion zu
    nutzen, w&auml;hlen Sie <b>Datei -&gt; Exportieren</b>,
    w&auml;hlen als Dateityp <b>Endnote (txt)</b> und dann den
    Namen der Export-Datei. 

    <h2>Importieren in EndNote</h2>Der
    Standard-EndNote-Importfilter kann nicht richtig mit mehreren
    Autoren oder Editoren umgehen. Es gibt zwei M&ouml;glichkeiten,
    um diese Schwierigkeit zu umgehen: 

    <ol>
        <li>Benutzen Sie den eingebauten Filter und bessern Sie die
        Datei sp&auml;ter aus. Um die Datei in EndNote zu
        &ouml;ffnen, erstellen Sie eine neue Datei oder &ouml;ffnen
        eine bestehende Datei in EndNote. Dann w&auml;hlen Sie
        <b>Datei -&gt; Importieren</b>, klicken mit der Maus auf
        <b>Datei w&auml;hlen</b>, w&auml;hlen die exportierte Datei
        aus und dr&uuml;cken auf <b>Ausw&auml;hlen</b>.
        Anschlie&szlig;end dr&uuml;cken Sie auf <b>Import
        Optionen</b> und w&auml;hlen <b>EndNote Import</b>. Mit
        einem Klick auf <b>Importieren</b> starten Sie den
        Importvorgang. Anschlie&szlig;end gehen Sie zum
        Men&uuml;punkt <b>Bearbeiten -&gt; Text &auml;ndern</b> und
        &auml;ndern <b>Any Field</b> in <b>Author</b>. Geben Sie "
        and " in das Suchfeld ein (ohne Anf&uuml;hrungszeichen)
        sowie ein RETURN-Zeichen in das Feld &Auml;ndern
        (Option-Return unter Mac OS X, Strg-Return unter Windows
        XP). Dann klicken Sie auf <b>&Auml;ndern</b>. Wiederholen
        Sie das Ganze f&uuml;r das Feld <b>Secondary Author</b>
        (Zweiter Autor).</li>

        <li>Installieren Sie den <i>EndNote Import from JabRef
        Filter</i> in <i>EndNote Extras</i>. Folgen Sie den
        Anweisungen in <i>Erweitert</i> (unten). Um die Datei in
        EndNote zu &ouml;ffnen, erstellen Sie eine neue Datei oder
        &ouml;ffnen eine bestehende Datei in EndNote. Dann
        w&auml;hlen Sie <b>Datei -&gt; Importieren</b>, klicken auf
        <b>Datei w&auml;hlen</b>, w&auml;hlen die exportierte Datei
        aus und dr&uuml;cken auf <b>Ausw&auml;hlen</b>.
        Anschlie&szlig;end dr&uuml;cken Sie auf <b>Import
        Optionen</b> und w&auml;hlen <b>EndNote Import from
        JabRef</b>. (Falls dieser Eintrag nicht erscheint,
        w&auml;hlen Sie Weitere Filter. Wenn er dann immer noch
        nicht erscheint, wurde der Filter nicht korrekt
        installiert.) Klicken Sie schlie&szlig;lich auf
        <b>Importieren</b>, um den Importvorgang zu starten.</li>
    </ol>

    <h2>Anmerkungen</h2>Der EndNote Exportfilter ordnet
    BibTeX-Eintragstypen folgenden EndNote-Referenztypen zu: 
    <pre>
BibTeX-Eintragstyp -&gt; Endnote Referenztyp
------------------------------------------
misc, other -&gt; Generic
unpublished -&gt; Manuscript
manual -&gt; Computer Program
article -&gt; Journal Article
book -&gt; Book
booklet -&gt; Personal Communication
inbook,incollection -&gt; Book Section
inproceedings -&gt; Conference Proceedings
techreport -&gt; Report
mastersthesis, phdthesis -&gt; Thesis
</pre>

    <h2>Mehrere Autoren</h2>In der Standardeinstellung geht der
    Exportfilter davon aus, dass Eintr&auml;ge in den Feldern
    author oder editor, die geklammert sind, mehrere Autoren
    enthalten und ersetzt die Klammern durch ein angeh&auml;ngtes
    Komma. Dadurch werden Eintr&auml;ge, die LaTeX-Befehle mit
    Klammern enthalten, als Eintrag mit mehreren Autoren gewertet
    und demzufolge unpassend formatiert. 

    <h2>Erweiterte Benutzung: Endnote Extras</h2>

    <h3>Installieren des EndNote Import from JabRef Filters</h3>Der
    vorgegebene EndNote-Importfilter kann das Feld author nicht
    richtig analysieren. Der EndNote Import from JabRef Filter kann
    dies. Au&szlig;erdem erkennt dieser Filter ein Feld
    <code>endnotereftype</code>, das die vorgegebene Zuordnung
    &uuml;berschreibt. Um den Filter zu installieren, extrahieren
    Sie die EndNote Extras (<b>Extras -&gt; EndNote Filter-Set
    entpacken</b>) und entpacken die Zip-Datei, die dabei erstellt
    wird. Dann folgen Sie den Angaben in der Datei
    <code>readme.txt</code>. 

    <h3>&Auml;ndern der EndNote Referenztypen</h3>Einige Felder,
    die von BibTeX genutzt werden, geh&ouml;ren nicht zu EndNotes
    vorgegebenen Referenztypen. W&auml;hrend der Import in JabRef
    und der Export nach JabRef ohne ein &Auml;ndern der
    Referenztypen funktioniert, werden die Feldnamen in EndNote
    nicht korrekt dargestellt (z.B. wird das pdf-Feld <i>Custom
    1</i> hei&szlig;en statt <i>pdf</i>). Dar&uuml;ber hinaus
    k&ouml;nnen diese Felder bei neuen Eintr&auml;gen in EndNote
    nicht genutzt werden, weil sie nicht im Eintragsdialog
    erscheinen. Um die EndNote-Referenztypen anzupassen,
    m&uuml;ssen Sie die EndNote Extras extrahieren und den
    Anweisungen in der Datei <code>readme.txt</code> folgen. 

    <h3>Exportieren nach JabRef</h3>EndNote hat einen Export-Stil
    BibTeX, der allerdings nicht alle Eintragstypen und Felder von
    BibTeX und auch nicht die zus&auml;tzlich von JabRef genutzten
    Allgemeinen Felder (<i>pdf, owner, key</i> usw.)
    unterst&uuml;tzt. Falls Sie diese Felder nutzen wollen,
    extrahieren Sie die EndNote Extras (<b>Extras -&gt; EndNote
    Filter-Set entpacken</b>) und folgen den Anweisungen in der
    Datei <code>readme.txt</code>.
    <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
