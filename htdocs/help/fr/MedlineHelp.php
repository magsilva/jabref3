<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>R&eacute;cup&eacute;ration d'entr&eacute;es depuis Medline</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Retour au contenu</a>


    <h1>R&eacute;cup&eacute;ration d'entr&eacute;es depuis Medline</h1>
    MEDLINE est la principale base de donn&eacute;es
    bibliographique de la biblioth&egrave;que nationale de
    m&eacute;decine des Etats-Unis. Elle contient des
    r&eacute;f&eacute;rences &agrave; des articles de journaux sur
    les sciences de la vie avec une sp&eacute;cialisation sur la
    biom&eacute;decine. 

    <p>JabRef peut t&eacute;l&eacute;charger des citations depuis
    la base de donn&eacute;es Medline. Pour r&eacute;aliser cette
    op&eacute;ration, s&eacute;lectionnez <b>Recherche internet
    -&gt; Recherche Medline</b> et vous verrez appara&icirc;tre
    l'interface de Medline dans le panneau lat&eacute;ral.</p>

    <p>Il y a deux mani&egrave;res d'indiquer les entr&eacute;es
    &agrave; t&eacute;l&eacute;charger&nbsp;:</p>

    <ol>
        <li>Entrez un ou plusieurs ID Medline
        (s&eacute;par&eacute;s par des virgules/points-virgules)
        dans le champ texte</li>

        <li>Entrez une s&eacute;rie de noms et/ou de mots &agrave;
        rechercher. Vous pouvez utiliser les op&eacute;rateurs
        <em>and</em> et <em>or</em> et les parenth&egrave;ses pour
        raffiner l'expression de votre recherche.</li>
    </ol>

    <p>Dans les deux cas, appuyez sur la touche
    <b>Entr&eacute;e</b> du clavier ou sur le bouton
    <b>Rechercher</b>. Si vous utilisez une recherche de texte,
    vous serez inform&eacute; du nombre d'entr&eacute;es
    trouv&eacute;es et vous pourrez choisir le nombre
    d'entr&eacute;es &agrave; t&eacute;l&eacute;charger.</p>

    <p>Les entr&eacute;es recherch&eacute;es seront ajout&eacute;es
    &agrave; votre base de donn&eacute;es active.</p>

    <h2>Utilisation d'un serveur proxy</h2>

    <p>Si vous avez besoin d'utiliser un serveur de proxy, passez
    le nom du serveur et le num&eacute;ro de port au lancement de
    java. Ces param&egrave;tres d'environnement sont
    document&eacute;s sur</p>

    <p>
    http://java.sun.com/j2se/1.4.2/docs/guide/net/properties.html</p>

    <p><code>java -Dhttp.proxyHost="hostname"
    -Dhttp.proxyPort="portnumber"</code></p><br />
    <br />
    <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
