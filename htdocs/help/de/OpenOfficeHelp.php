<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de">
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>JabRef-Bibliographien in OpenOffice.org benutzen</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>
	
    <basefont size="4" color="#2F4958" face="arial" />

    <h1>JabRef-Bibliographien in OpenOffice.org benutzen</h1>

    <p>JabRef kann Ihre Datei sowohl in das OpenOffice.org 1.1
    <b>.sxc</b>-Tabellenkalkulationsformat als auch in das
    OpenDocument <b>.ods</b>-Tabellenkalkulationsformat, das von
    OpenOffice.org 2.0 benutzt wird, exportieren.</p>

    <p>In beiden f&auml;llen besteht die exportierte Tabelle aus
    einem Arbeitsblatt, das die Eintr&auml;ge in Reihen und die
    unterschiedlichen Felder in Spalten enth&auml;lt. Die
    Reihenfolge und Bennung der Spalten ist kompatibel zu den
    Literaturverzeichnis-Funktionen von OpenOffice.org (OOo 1.1:
    <b>.sxc</b>, OOo 2.0: <b>.ods</b>).</p>

    <p>Je nach Ihrer Version von OpenOffice.org gibt es verschiedene
    Vorgehensweisen, um JabRef zum Verwalten Ihrer bibliographischen
    Datenbank zu benutzen:</p>

    <h2>Die exportierte Datei als bibliographische Datenbank in
    OpenOffice.org 2.3 und 2.4 benutzen</h2>

    <p>Mit folgenden Schritten k&ouml;nnen Sie eine aus JabRef exportierte
    Tabelle als bibliographische Datenbank benutzen:</p>

    <ul>
    <li>Exportieren Sie in JabRef Ihre Datei in das <b>.ods</b>-Format</li>
    <li>&Ouml;ffnen Sie OpenOffice.org Writer</li>

    <li>W&auml;hlen Sie <b>Bearbeiten -&gt; Datenbank austauschen</b>. Klicken Sie auf
    <b>Durchsuchen</b> und w&auml;hlen die Datei, die Sie in das .ods-Format
    exportiert haben.</li>
    <li>Klicken Sie auf das <b>+</b> vor dem Namen der Datenbank, anschlie&szlig;end
    auf den angezeigten Dateinamen und schlie&szlig;lich auf den Knopf <b>Festlegen</b>.</li>
    <li>W&auml;hlen Sie <b>Extras -&gt; Optionen -&gt; OpenOffice.org Base -&gt;
    Datenbanken</b>. In diesem Fenster sollte die Datenbank, die Sie gerade
    importiert haben, angezeigt werden. Die Standard-Datenbank f&uuml;r Bibliographien
    von OOo sollte ebenfalls angezeigt werden (<i>Bibliography</i>).</li>
    <li>Klicken Sie auf <b>Bearbeiten</b> und &auml;ndern Sie den Namen der Datenbank
    <i>Bibliography</i>, z.B. zu <i>Bibliography-old</i> (denn OpenOffice.org
    kann nicht mit mehreren bibliographischen Datenbanken arbeiten).</li>
    <li>W&auml;hlen Sie anschlie&szlig;end Ihre bibliographische Datenbank und benennen Sie sie um in
    <i>Bibliography</i> (achten Sie auf einen Gro&szlig;buchstaben am Anfang des Namens).</li>
    </ul>

    <p>Nach diesen Schritten sollte Ihre bibliographische Datenbank zur Benutzung
    mit OpenOffice.org bereit sein. Um das zu pr&uuml;fen, w&auml;hlen Sie <b>Einf&uuml;gen -&gt;
    Verzeichnisse -&gt; Literaturverzeichniseintrag...</b>. Im folgenden Dialog sollten
    in der Dropdownliste (unter <b>Kurzbezeichnung</b>) die BibTeX-Keys Ihrer
    Datenbank erscheinen.</p>

    <h2>Die exportierte Datei als Bibliographiedatenbank in
    OpenOffice 2.0, 2.1 oder 2.2 benutzen</h2>

    <p>Gehen Sie folgenderma&szlig;en vor, um eine Tabelle, die von JabRef
    exportiert wurde, als Bibliographiedatenbank in OpenOffice.org
    zu benutzen:</p>

    <ul>
        <li>Exportieren Sie Ihre Datenbank in das <b>.ods</b>
        -Format</li>

        <li>Starten Sie OpenOffice.org</li>

        <li>W&auml;hlen Sie <b>Extras -&gt; Optionen -&gt;
        OpenOffice.org Base -&gt; Datenbanken</b></li>

        <li>Bearbeiten Sie die <i>Bibliography</i>-Datenbank und
        &auml;ndern ihren Namen z.B. in
        <i>Bibliographie-alt</i></li>

        <li>Schlie&szlig;en Sie das Fenster <b>Optionen</b> und
        gehen Sie zu <b>Datei -&gt; Neu -&gt; Datenbank</b></li>

        <li>W&auml;hlen Sie <b>Verbindung zu einer bestehenden
        Datenbank herstellen</b>, w&auml;hlen
        <b>Tabellendokument</b> als Datenbanktyp und w&auml;hlen
        die <b>.ods</b>-Datei, die Sie exportiert haben</li>

        <li>Klicken Sie auf <b>Fertig stellen</b> und w&auml;hlen
        den Namen <i>Bibliography</i> im Speicherdialog</li>
    </ul>Anschlie&szlig;end w&auml;hlen Sie <b>Extras -&gt;
    Literaturdatenbank</b>. Ihre Datenbank sollte nun angezeigt
    werden.

    <h2>Eine exportierte Datei als Datenbank in OpenOffice 1.1.x
    benutzen</h2>

    <ul>
        <li>Exportieren Sie Ihre Datei in das
        <b>.sxc</b>-Format</li>

        <li>Starten Sie OpenOffice.org</li>

        <li>W&auml;hlen Sie <b>Extras -&gt; Datenquellen</b></li>

        <li>W&auml;hlen Sie die <i>Bibliography</i>-Datei und
        &auml;ndern ihren Namen z.B. in
        <i>Bibliographie-alt</i>.</li>

        <li>Dr&uuml;cken Sie <b>Anwenden</b>.</li>

        <li>Klicken Sie <b>Neue Datenquelle</b>. Ein neuer Eintrag
        erscheint. &Auml;ndern Sie den Namen zu
        <i>Bibliography</i>.</li>

        <li>&Auml;ndern Sie den <b>Dateityp</b> zu <b>Tabelle</b>.
        Klicken Sie den <b>...</b>-Button in der Zeile
        <b>Datenquellen URL</b>. W&auml;hlen Sie die
        <b>.sxc</b>-Datei, die Sie exportiert haben.</li>

        <li>Klicken Sie auf <b>OK</b>, um das Fenster
        <b>Datenquellen</b> zu schlie&szlig;en.</li>
    </ul>Anschlie&szlig;end w&auml;hlen Sie <b>Extras -&gt;
    Literaturdatenbank</b>. Ihre Datenbank sollte nun angezeigt
    werden.
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
