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


    <h3>MySQL</h3>
    <ol>
      <li> En utilisant votre outil d'administration MySQL favori, cr&eacute;er une base de donn&eacute;es MySQL vide.</li>
      <li> Assurez-vous qu'il existe un utilisateur ayant <em>tous les privil&egrave;ges</em> pour cette base de donn&eacute;es.</li>
    </ol>

    <h3>PostgreSQL</h3>
    <ol>
      <li> En utilisant votre outil d'administration PostgreSQL favori (PGAdminIII, PHPpgadmin, etc.), cr&eacute;er une base de donn&eacute;es PostgreSQL vide.
           Autrement, la ligne de commande <em>createdb</em> peut &ecirc;tre utilis&eacute;e comme suit&nbsp;:<br />
      <b>createdb -h [HOST] -U [USERNAME] [DBNAME] -E UTF8 --lc-ctype=en_US.utf-8 -T template0</b>

	<p>l'hypoth&egrave;se &eacute;tant que le langage est l'anglais et le lieu est US.
           Si un mot de passe est n&eacute;cessaire, le serveur en demandera un &agrave; partir de la ligne de commande.
           Voir la page du manuel de createdb pour plus d'informations.</p>

      </li>
      <li> Assurez-vous qu'un utilisateur de cette base dispose de <em>tous les privil&egrave;ges</em>.</li>
    </ol>




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