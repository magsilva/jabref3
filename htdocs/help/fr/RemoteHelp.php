<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Acc&egrave;s &agrave; distance</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>



    <h1>Acc&egrave;s &agrave; distance</h1>

    <p>Cette fonction peut
    &ecirc;tre activ&eacute;e et configur&eacute;e dans
    <b>Pr&eacute;f&eacute;rences -&gt; Avanc&eacute;</b>.</p>

    <p><i>Notez qu'activer cette fonction sous Windows XP SP2 (et
    probablement d'autres configurations) peut afficher une
    bo&icirc;te de message disant que certaines fonctions du
    programme ont &eacute;t&eacute; bloqu&eacute;es par le pare-feu
    de Windows. Vous pouvez en toute s&eacute;curit&eacute; dire au
    pare-feu de continuer &agrave; bloquer&nbsp;; le pare-feu
    n'interf&eacute;rera pas avec l'acc&egrave;s &agrave; distance
    de JabRef.</i></p>

    <p>Si l'acc&egrave;s &agrave; distance est activ&eacute;,
    JabRef se mettra &agrave; &eacute;couter un port
    sp&eacute;cifique lors de son d&eacute;marrage. Cela signifie
    que les autres applications peuvent envoyer des informations
    &agrave; JabRef &agrave; travers ce port. JabRef n'acceptera
    que des connexions locales, afin d'&eacute;viter le risque
    d'interf&eacute;rence &agrave; partir de
    l'ext&eacute;rieur.</p>

    <p>La r&eacute;servation du port permet &agrave; une seconde
    instance de JabRef de d&eacute;couvrir qu'une premi&egrave;re
    instance tourne d&eacute;j&agrave;. Dans ce cas, &agrave; moins
    qu'elle soit sp&eacute;cifiquement configur&eacute;e pour
    tourner en mode autonome, la seconde instance de JabRef passera
    ses options de sa ligne de commande &agrave; la premi&egrave;re
    instance en utilisant le port, puis se terminera
    imm&eacute;diatement.</p>

    <p>La premi&egrave;re instance de JabRef lira les options de la
    ligne de commande et effectuera les actions indiqu&eacute;es,
    tel que la lecture ou l'importation d'un fichier, ou la fusion
    d'un fichier avec la base de donn&eacute;es actuellement
    affich&eacute;e. Si un fichier est import&eacute; en utilisant
    l'option de ligne de commande <code>--importToOpen</code>, les
    entr&eacute;es import&eacute;es seront ajout&eacute;es &agrave;
    la base de donn&eacute;es actuellement affich&eacute;e. Si
    aucune base de donn&eacute;es n'est ouverte, une nouvelle base
    sera cr&eacute;&eacute;e.</p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
