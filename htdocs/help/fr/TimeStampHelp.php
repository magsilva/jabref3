<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>Horodatage des entr&eacute;es</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Retour au contenu</a>


    <h1>Horodatage des entr&eacute;es</h1>Cette fonction peut
    &ecirc;tre activ&eacute;e et configur&eacute;e sous <b>Options
    -&gt; Pr&eacute;f&eacute;rences -&gt;
    G&eacute;n&eacute;ral</b>. 

    <p>JabRef peut automatiquement ins&eacute;rer un champ qui
    contient la date &agrave; laquelle une entr&eacute;e a
    &eacute;t&eacute; ajout&eacute;e &agrave; la base de
    donn&eacute;es.</p>

    <h2>Mise en forme</h2>Le format d'horodatage est
    d&eacute;termin&eacute; par une cha&icirc;ne contenant des
    codes qui indiquent la position des diff&eacute;rentes parties
    de la date. 

    <p>Voici certains des codes disponibles (exemples donn&eacute;s
    entre parenth&egrave;ses pour le mercredi 14 septembre 2005
    &agrave; 17h45)&nbsp;:</p>

    <ul>
        <li><b>yy</b>: ann&eacute;e (05)</li>

        <li><b>yyyy</b>: ann&eacute;e (2005)</li>

        <li><b>MM</b>: mois (09)</li>

        <li><b>dd</b>: jour du mois (14)</li>

        <li><b>HH</b>: heure du jour (17)</li>

        <li><b>mm</b>: minute de l'heure (45)</li>
    </ul>Ces codes peuvent &ecirc;tre combin&eacute;s avec des
    ponctuations et des espaces. Quelques exemples&nbsp;: 

    <ul>
        <li><b>yyyy.MM.dd</b> donne <b>2005.09.14</b></li>

        <li><b>yy.MM.dd</b> donne <b>05.09.14</b></li>

        <li><b>yyyy.MM.dd HH:mm</b> donne <b>2005.09.14
        17:45</b></li>
    </ul>
    <?php include("../../footer.php"); ?>
  </div>

</body>
</html>