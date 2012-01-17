<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Recherche Spires</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>



<h1>Recherche Spires</h1>

<p>Pour utiliser cette fonction, choisissez <b>Recherche -&gt; Recherche Web</b>.
    L'interface de recherche apparaitra dans le panneau lat&eacute;ral. S&eacute;lectionnez
    <b>Spires</b> dans le menu d&eacute;roulant.</p>

<p>La fonction de recherche Spires ne fait en fait que lancer des requ&ecirc;tes de recherche sur le serveur web
Spires&nbsp;; aussi, vous devez construire vos requ&ecirc;tes comme si vous alliez sur le serveur web, sauf que
vous ne devez pas inclure les commandes <em>find</em> et <em>fin</em>.
Cette page d'aide vous donne uniquement une br&egrave;ve introduction aux requ&ecirc;tes de recherche.
Une aide plus longue sur les recherches dans Spires est disponible sur la page
http://www.slac.stanford.edu/spires/hep/help/index.shtml (en anglais).</p>

<p>Votre requ&ecirc;te peut se composer de plusieurs parties combin&eacute;es en utilisant <em>and</em> et <em>or</em>
comme op&eacute;rateurs logiques. Chaque partie est compos&eacute;e d'une lettre ou d'un mot sp&eacute;cifiant le champ de recherche,
suivi par un espace et le texte &agrave; rechercher.</p>

<p>La liste suivante montre quelques-uns des champs de recherche qui peuvent &ecirc;tre sp&eacute;cifi&eacute;s&nbsp;:</p>
    <ul>
    <li><em>a</em> ou <em>author</em>&nbsp;: recherche sur les noms d'auteurs</li>
    <li><em>t</em> or <em>title</em>&nbsp;: recherche dans le titre</li>
    <li><em>j</em>&nbsp;: journal. Ici, soit l'abr&eacute;viation usuelle, soit l'abr&eacute;viation CODEN de 5 lettres du nom
    du journal peut &ecirc;tre utilis&eacute;. Num&eacute;ro de volume et page peuvent &ecirc;tre aussi inclus, s&eacute;par&eacute;s par des virgules.
    Par exemple, <em>j Phys. Rev.,D54,1</em> recherche dans le journal Phys. Rev., volume D54, page 1.</li>
    <li><em>k</em>: recherche dans les mots-clefs</li>
</ul>

<p>Exemples de requ&ecirc;te&nbsp;:</p>
<ul>
        <li><em>a smith and a jones</em>: recherche les r&eacute;f&eacute;rences ayant pour auteurs "smith" et "jones"</li>
        <li><em>a smith or a jones</em>: recherche les r&eacute;f&eacute;rences ayant pour auteurs "smith" ou "jones"</li>
        <li><em>a smith and not t processor</em>: recherche les r&eacute;f&eacute;rence ayant pour auteur "smith" et n'ayant
        pas le mot "processor" dans le titre</li>
</ul>

  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
