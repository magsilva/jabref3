<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>S&eacute;lecteur de contenu de champ</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Retour au contenu</a>



    <h1>S&eacute;lecteur de contenu de champ</h1>

    <p>Cette op&eacute;ration vous permet d'enregistrer une
    s&eacute;lection de mots et de phrases que vous utilisez
    souvent dans votre base de donn&eacute;es. Par d&eacute;faut,
    c'est possible avec les champs <em>Journal</em>,
    <em>Author</em>, <em>Keywords</em> et <em>Publisher</em> mais
    vous pouvez aussi ajouter des s&eacute;lecteurs &agrave;
    d'autres champs, en utilisant la partie sup&eacute;rieure de la
    fen&ecirc;tre <i>G&eacute;rer les s&eacute;lecteurs</i> (menu
    <strong>Outils --&gt; G&eacute;rer les s&eacute;lecteurs de
    contenu</strong>).</p>

    <p>La s&eacute;lection des mots est sp&eacute;cifique &agrave;
    la base de donn&eacute;es et sauvegard&eacute;e avec vos
    r&eacute;f&eacute;rences dans le fichier .bib.</p>

    <p>Pour ajouter un nouveau mot, vous pouvez simplement
    l'&eacute;crire dans la boite de s&eacute;lection des mots et
    appuyer sur la touche Enter. A partir de la fen&ecirc;tre
    <i>G&eacute;rer les s&eacute;lecteurs</i>, vous pouvez aussi
    supprimer les mots que vous avez ajout&eacute;s.</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
