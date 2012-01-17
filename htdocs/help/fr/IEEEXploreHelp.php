<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" xml:lang="fr">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Recherche IEEEXplore</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Retour au contenu</a>



    <h1>Recherche IEEEXplore</h1>

    
    <p>Pour utiliser cette fonction, choisissez <b>Recherche -&gt; Recherche Web</b>.
    L'interface de recherche apparaitra dans le panneau lat&eacute;ral. S&eacute;lectionnez
    <b>IEEEXplore</b> dans le menu d&eacute;roulant.</p>
    
    <p>IEEEXplore permet l'acc&egrave;s
    &agrave; de la litterature technique en g&eacute;nie
    &eacute;lectrique, informatique et &eacute;lectronique.
    Pour lancer une recherche, entrez les mots de votre
    requ&ecirc;te et appuyez sur <b>Entr&eacute;e</b> ou sur le
    bouton <b>Rechercher</b>.</p>

    <p>La recherche est effectu&eacute;e en mode invit&eacute;, ce
    qui signifie qu'au maximum 100 r&eacute;sultats vous seront
    retourn&eacute;s.</p>

    <p>Vous pouvez choisir de t&eacute;l&eacute;charger les
    r&eacute;sum&eacute;s avec les informations de citation pour
    chaque entr&eacute;e en cochant la case <b>Inclure les
    r&eacute;sum&eacute;s</b>. Cela ne cr&eacute;era PAS de
    requ&ecirc;tes r&eacute;seau suppl&eacute;mentaires.</p>

    <p>L'option de t&eacute;l&eacute;chargement direct de
    citations BibTeX &agrave; partir de IEEEXplore ne
    fonctionne pas encore.</p>

    <p>Pour toute suggestion, envoyez s'il vous pla&icirc;t
    un courriel &agrave; Aaron Chen <b>nextAaron@gmail.com</b>.</p>    
    
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
