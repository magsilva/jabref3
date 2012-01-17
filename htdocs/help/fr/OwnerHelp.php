<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" xml:lang="fr">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Le champ 'owner' (propri&eacute;taire)</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Retour au contenu</a>



    <h1>Le champ 'owner' (propri&eacute;taire)</h1>

    <p>JabRef peut
    &eacute;ventuellement signer toutes les nouvelles
    entr&eacute;es ajout&eacute;es ou import&eacute;es dans une
    base de donn&eacute;es avec votre nom. Vous pouvez activer ou
    d&eacute;sactiver cette fonction dans le menu <b>Options/
    Pr&eacute;f&eacute;rences -&gt; G&eacute;n&eacute;ral</b>, et
    vous pouvez aussi changer le nom utilis&eacute; pour signer vos
    entr&eacute;es. Le nom utilis&eacute; par d&eacute;faut est
    votre nom d'utilisateur.</p>

    <p>Ce nom sera ajout&eacute; dans un champ nomm&eacute;
    'owner', qui, par d&eacute;faut, est visible dans l'onglet
    <b>Champs G&eacute;n&eacute;raux</b> de l'&eacute;diteur
    d'entr&eacute;es.</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
