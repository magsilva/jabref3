<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Recherche ScienceDirect</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


  <h1>Recherche ScienceDirect</h1>

  <p>Pour utiliser cette fonction, choisissez <b>Recherche -&gt; Recherche Web</b>.
    L'interface de recherche apparaitra dans le panneau lat&eacute;ral. S&eacute;lectionnez
    <b>ScienceDirect</b> dans le menu d&eacute;roulant.</p>

  <p>La recherche ScienceDirect est bas&eacute; sur le projet d'extraction de
    donn&eacute;es de  de BibSonomy (http://scraper.bibsonomy.org/) pour
    extraire les informations de la page web de ScienceDirect.</p>

  <p>Cette fonction lance une recherche rapide sur le site web ScienceDirect,
    r&eacute;sultant à 100&nbsp;r&eacute;sultats maximum. Tous les r&eacute;sultats sont ensuite
    import&eacute;s.</p>

  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
