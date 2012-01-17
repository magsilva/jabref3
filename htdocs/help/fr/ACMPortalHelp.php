<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>R&eacute;cup&eacute;ration des entr&eacute;es depuis le portail ACM</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Retour au contenu</a>


    <h1>R&eacute;cup&eacute;ration des entr&eacute;es depuis le portail <em>ACM</em></h1>

    <p>Pour utiliser cette fonction, choisissez <b>Recherche -&gt; Recherche Web</b>.
    L'interface de recherche apparaitra dans le panneau lat&eacute;ral. S&eacute;lectionnez
    <b>ACM Portal</b> dans le menu d&eacute;roulant.</p>    
    
    <p>Pour d&eacute;marrer la recherche, entrer les mots de votre requ&ecirc;te et appuyer sur 
    <b>Entr&eacute;e</b> ou sur le bouton <b>Rechercher</b>.</p>
    
    <p>Le portail ACM inclut deux bases de donn&eacute;es&nbsp;: le biblioth&egrave;que num&eacute;rique ACM ("ACM Digital Library")
    qui est la collection compl&egrave;te de tous les articles publi&eacute;s par ACM, incluant plus de 50 ans d'archives, et
    le Guide sur la Litt&eacute;rature Informatique ("Guide to Computing Literature") qui est une collection
    bibliographique provenant de grands &eacute;diteurs en informatique et contenant plus d'un million d'entr&eacute;es.
    </p>
    
    <p>Vous pouvez choisir la base de donn&eacute;es sur laquelle portera la recherche.
    Vous pouvez d&eacute;cider de t&eacute;l&eacute;charger les r&eacute;sum&eacute;s
    avec les autres informations de chaque entr&eacute;e en validant la
    case &agrave; cocher <b>Inclure les r&eacute;sum&eacute;s</b>.</p>
    
    <p>Pour &eacute;viter de mettre une pression excessive sur le site internet du
    portail ACM, JabRef refusera de t&eacute;l&eacute;charger
    plus qu'un certain nombre d'entr&eacute;es.</p>

    <p>&Agrave; ce moment, des connexions fr&eacute;quentes au portail ACM vont causer
    l'interdiction de votre adresse IP pour plusieurs heures. Aussi, JabRef attendra
    5&nbsp;secondes entre chaque connexion, et le processus de t&eacute;l&eacute;chargement
    sera tr&egrave;s lent.</p>

    <p>Pour toute suggestion, envoyez s'il vous pla&icirc;t
    un courriel &agrave; Aaron Chen <b>nextAaron@gmail.com</b>.</p>    
    
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
