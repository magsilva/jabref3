<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" xml:lang="fr">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Exportation vers une base de donn&eacute;es SQL externe</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Retour au contenu</a>



    <h1>Exportation vers une base de donn&eacute;es SQL externe</h1>

    <p> JabRef est capable d'exporter le contenu de la base BibTeX, ainsi que les informations sur les groupes, dans une base de donn&eacute;es externe MySQL ou PostgreSQL.</p>

    <p>You just need to be sure you have an user/password with full privileges on a MySQL or PostgreSQL server.</p>

    <h2>Exportation</h2>
    <ol>
      <li> Choisissez <b>Fichier -&gt; Exporter vers une base SQL externe</b>, ou cliquez sur le bouton correspondant dans la barre d'outils.</li>
      <li> S&eacute;lectionnez le type de base de donn&eacute;es &agrave; partir du menu d&eacute;roulant pour le<em>Type de Serveur</em>.</li>
      <li> Entrez les informations de connexion de la base de donn&eacute;es, et cliquez sur <b>Connecter</b>.</li>
    </ol>

		<p>
		JabRef se connectera alors &agrave; la base de donn&eacute;es sp&eacute;cifi&eacute;e, <b><em>effacera les tables existantes</em></b>, cr&eacute;era de nouvelles tables et remplira ces tables avec les informations sur les entr&eacute;es et sur les groupes. Notez que les informations de connexion ne vous seront pas demand&eacute;es lors des prochaines exportations.
		Si vous souhaitez exporter vers une base de donn&eacute;es diff&eacute;rente, vous pouvez changez les informations de connexion en choisissant <b>Fichier -&gt; Connecter vers une base SQL externe</b> (ou en cliquant sur le bouton correspondant dans le barre d'outils), puis en effectuant une exportation.
    </p>

  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
