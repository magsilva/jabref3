<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>Personnalisation des champs g&eacute;n&eacute;raux</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Retour au contenu</a>


    <h1>Personnalisation des champs g&eacute;n&eacute;raux</h1>

    <p>Vous pouvez ajouter un nombre arbitraire d'onglets dans
    l'&eacute;diteur d'entr&eacute;es. Ceux-ci seront alors
    pr&eacute;sents pour tous les types d'entr&eacute;es. Pour
    personnaliser ces onglets, allez dans "Options -&gt; Configurer
    les champs g&eacute;n&eacute;raux".</p>

    <p>Vous devez sp&eacute;cifier un onglet par ligne. La ligne
    doit commencer par le nom de l'onglet suivi par 2 points (:)
    puis par les champs s&eacute;par&eacute;s par un point-virgule
    (;), devant &ecirc;tre contenus.</p>

    <p>Exemple&nbsp;:</p><code>General:url;keyword;doi;pdf<br />
    Abstract:abstract;annote</code> 

    <p>produit un onglet appel&eacute; "General" contenant les
    champs url, keywords, doi et pdf et un onglet appel&eacute;
    "Abstract" contenant les champs abstract et annote.</p>
    <?php include("../../footer.php"); ?>
  </div>

</body>
</html>