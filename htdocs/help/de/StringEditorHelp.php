<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>Der String-Editor</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Zur&uuml;ck zum Inhaltsverzeichnis</a>


    <h1>Der String-Editor</h1><em>Zugriff &uuml;ber das Men&uuml;
    <b>BibTeX -&gt; Strings bearbeiten</b> oder durch Klick auf die
    Schaltfl&auml;che <b>Strings bearbeiten</b></em>. 

    <p><em>Strings</em> sind das <em>BibTeX</em>-&Auml;quivalent zu
    Konstanten in einer Programmiersprache. Jeder String wird durch
    einen eindeutigen <em>Namen</em> und einen <em>Inhalt</em>
    festgelegt. Der Name kann an anderer Stelle in der Datei
    benutzt werden, um den Inhalt wiederzugeben.</p>

    <p>Ein Beispiel: Wenn viele Eintr&auml;ge aus einer Zeitschrift
    stammen, deren Abk&uuml;rzung schwer zu behalten ist, wie etwa
    'J. Theor. Biol.' (Journal of Theroretical Biology),
    k&ouml;nnte ein String mit dem Namen 'JTB' angelegt werden, um
    den Namen der Zeitschrift zu repr&auml;sentieren. Statt nun in
    jedem Eintrag den exakten Namen der Zeitschrift einzutragen,
    gen&uuml;gt die Zeichenfolge <code>#JTB#</code> im Feld
    <em>journal</em>, und es ist sichergestellt, dass der Name
    jedesmal in identischer Schreibweise ausgegeben wird.</p>

    <p>Der Verweis auf einen String kann an jeder Stelle eines
    Feldes erscheinen, wobei der Name des Strings immer von einem
    Paar '#'-Zeichen eingeschlossen werden muss. Diese Syntax gilt
    nur f&uuml;r JabRef und weicht ein wenig von der
    <em>BibTeX</em>-Syntax ab, die erzeugt wird, wenn Sie Ihre
    Datei speichern. Strings k&ouml;nnen f&uuml;r alle
    Standard-BibTeX-Felder verwendet werden. Unter <b>Optionen
    -&gt; Einstellungen -&gt; Allgemein</b> k&ouml;nnen Sie im
    Bereich <b>Datei</b> festlegen, ob Strings auch in
    Nicht-Standard-Feldern benutzt werden d&uuml;rfen. In diesem
    Fall k&ouml;nnen Sie Felder bestimmen, die von der
    Aufl&ouml;sung der Strings ausgenommen werden; hierbei wird
    empfohlen, das Feld 'url' und andere Felder anzugeben, die das
    Zeichen '#' enthalten k&ouml;nnen und die von BibTeX/LaTeX
    abgearbeitet werden k&ouml;nnen.</p>

    <p>In derselben Weise kann man auch im Inhalt eines Strings auf
    einen anderen String verweisen, vorausgesetzt, dass der String,
    auf den verwiesen wird, bereits <em>vorher</em> definiert
    ist.</p>

    <p>W&auml;hrend die Reihenfolge der Strings in Ihrer
    BibTeX-Datei in einigen F&auml;llen wichtig ist, brauchen Sie
    sich bei der Benutzung von JabRef dar&uuml;ber keine Gedanken
    zu machen. Die Strings werden in alphabetischer Reihenfolge im
    String-Editor aufgelistet und in derselben Reihenfolge
    gespeichert, au&szlig;er wenn eine andere Reihenfolge von
    BibTeX verlangt wird.</p>
    <?php include("../../footer.php"); ?>
  </div>

</body>
</html>