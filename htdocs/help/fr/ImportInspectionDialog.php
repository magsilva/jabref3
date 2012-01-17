<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" xml:lang="fr">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Fen&ecirc;tre de v&eacute;rification des importations</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Retour au contenu</a>



    <h1>Fen&ecirc;tre de v&eacute;rification des importations</h1>

    <p>Quand vous importez de nouvelles entr&eacute;es &agrave;
    partir d'un format de r&eacute;f&eacute;rence connu, ou que
    vous r&eacute;cup&eacute;rez des entr&eacute;es directement sur
    internet, elles sont tout d'abord affich&eacute;es dans une
    fen&ecirc;tre pour leur v&eacute;rification. A ce moment,
    aucune des entr&eacute;es n'a encore &eacute;t&eacute;
    ajout&eacute;e &agrave; la base de donn&eacute;es actuellement
    ouverte (si une base est ouverte).</p>

    <p>La fen&ecirc;tre de v&eacute;rification vous permet de
    supprimer les entr&eacute;es que vous ne voulez pas garder et
    d'effectuer des op&eacute;rations simples telles que la
    g&eacute;n&eacute;rations des clefs BibTeX pour ces
    entr&eacute;es ou leur ajout &agrave; des
    <a href="GroupsHelp.php">groupes</a> manuels. Si vous les
    importez dans une base de donn&eacute;es existante, il est
    souvent plus facile d'effectuer ces op&eacute;rations avant que
    les nouvelles entr&eacute;es soient ins&eacute;r&eacute;es
    parmi les entr&eacute;es d&eacute;j&agrave; pr&eacute;sentes
    dans la base de donn&eacute;es.</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
