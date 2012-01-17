<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>JStor durchsuchen</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>

<basefont size="4" color="#2F4958" face="arial" />

  <h1>JStor durchsuchen</h1>

    <p>Folgende Beschreibung stammt von der JStor-Webseite:</p>
    <ul>
      <li>Standardm&auml;&szlig;ig wird nach Autor, Titel und Volltext gesucht (articles, book reviews, etc.)</li>
      <li>Benutze Anf&uuml;hrungszeichen, um nach einer Phrase zu suchen (z.B. &quot;punctuated equilibrium&quot;)</li>
      <li>Benutze ti: um nach einem Titel zu suchen, au: um nach einem Author zu suchen (z.B. ti:&quot;two-person cooperative games&quot;, au:&quot;john nash&quot;)</li>
      <li>Benutze AND, OR, NOT ,um Bezeichnungen zu kombinieren (z.B. ti:&quot;two-person cooperative games&quot; AND au:&quot;john nash&quot;)</li>
    </ul>

    <p>JSTOR ist eine gemeinn&uuml;tzige Organisation mit einer doppelten Mission: Aufbau und Unterhalt eines verl&auml;sslichen Archivs von wichtigen wissenschaftlichen Zeitschriften und Bereitstellung eines m&ouml;glichst breiten Zugriffs auf diese Zeitschriften. JSTOR bietet Forschern die M&ouml;glichkeit, hochaufl&ouml;sende gescannte Bilder von Zeitschriftenausgaben und Seiten genau so abzurufen, wie sie urspr&uuml;nglich entworfen, gedruckt und illustriert wurden. Die in JSTOR gespeicherten Inhalte umfassen zahlreiche Fachgebiete. Eine <a href="http://about.jstor.org/content-collections">Liste</a> enth&auml;lt die derzeit verf&uuml;gbaren Titel und Sammlungen.</p>

    <p>JStor bietet Zugang zu Literatur in den Bereichen:</p>
    <ul>
      <li>Kunst &amp; Wissenschaft</li>
      <li>Biologie</li>
      <li>Business</li>
      <li>&Ouml;kologie &amp; Botanik</li>
      <li>Gesundheit &amp; Allgemeine Wissenschaften</li>
      <li>Sprachen &amp; Literatur</li>
      <li>Mathematik &amp; Statistik</li>
      <li>Musik</li>
    </ul>

    <p>Die Suche liefert max. 200 Ergebnisse.</p>

  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
