<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>Recherche</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Retour au contenu</a>


    <h1>Recherche</h1>Il y a trois modes diff&eacute;rents de
    recherche dans JabRef. 

    <p><em>CTRL-F</em> ouvre ou active l'interface de recherche.
    Appuyer sur <em>CTRL-F</em> plusieurs fois modifie le mode de
    recherche. En recherche incr&eacute;mentale, <em>CTRL-F</em>
    affiche l'occurrence suivante de la cha&icirc;ne &agrave;
    rechercher.</p>

    <p><em>CTRL-MAJ-F</em> ouvre ou active l'interface de
    recherche, et s&eacute;lectionne la recherche
    incr&eacute;mentale. En recherche incr&eacute;mentale, appuyer
    sur <em>CTRL-MAJ-F</em> affiche aussi l'occurrence suivante de
    la cha&icirc;ne &agrave; rechercher.</p>

    <h2>Recherche incr&eacute;mentale</h2>En recherche
    incr&eacute;mentale, le programme effectue une recherche chaque
    fois qu'une lettre est tap&eacute;e. La ligne de statut vous
    informe du r&eacute;sultat de cette recherche. Entrer le
    raccourci clavier de la recherche affiche l'occurrence suivante
    de la cha&icirc;ne &agrave; rechercher. Si aucune autre
    occurrence ne peut &ecirc;tre trouv&eacute;e, la ligne de
    statut vous en informe. R&eacute;p&eacute;ter alors la
    recherche fera red&eacute;marrer la recherche au d&eacute;but.
    L'ordre de recherche se fait toujours selon l'ordre de tri
    actuel de votre base de donn&eacute;es. Pour quitter la
    recherche incr&eacute;mentale, appuyez sur ESC ou cliquez sur
    "Vider". 

    <h2>Recherche normale</h2>Dans une recherche normale, le
    programme recherche dans votre base les occurrences de votre
    cha&icirc;ne de recherche, apr&egrave;s que vous ayez
    appuy&eacute; sur Entr&eacute;e. Toutes les entr&eacute;es qui
    ne correspondent pas sont masqu&eacute;es, ne laissant alors
    appara&icirc;tre que les entr&eacute;es correspondant aux
    crit&egrave;res (mode filtre), ou sont gris&eacute;es (mode
    flottante). Pour ne plus afficher les r&eacute;sultats de la
    recherche, appuyer sur ESC ou cliquer sur "Vider". 

    <h2><a name="advanced"
       id="advanced">Sp&eacute;cification des champs,
       op&eacute;rateurs logiques</a></h2>Afin de rechercher
       uniquement des champs sp&eacute;cifiques et/ou d'inclure des
       op&eacute;rateurs logiques dans l'expression &agrave;
       rechercher, une syntaxe particuli&egrave;re est disponible.
       Par exemple, pour rechercher les entr&eacute;es dont
       l'auteur est "Miller", entrez (except&eacute; en mode de
       recherche incr&eacute;mentale)&nbsp;: 

    <p>author = miller</p>

    <p>A la fois la sp&eacute;cification du champ et le terme
    &agrave; rechercher peuvent &ecirc;tre des expressions
    r&eacute;guli&egrave;res. Si un terme &agrave; rechercher
    contient des espaces, le mettre entre guillemets. Il ne faut
    <i>jamais</i> utiliser d'espace dans la sp&eacute;cification du
    champ&nbsp;! Par exemple, pour rechercher les entr&eacute;es
    &agrave; propos de traitement d'images, entrez&nbsp;:</p>

    <p>title|keywords = "traitement d'images"</p>

    <p>Vous pouvez utiliser "and", "or", "not", et les
    parenth&egrave;ses de la fa&ccedil;on habituelle&nbsp;:</p>

    <p>(author = miller or title|keywords = "traitement d'images")
    and not author = brown</p>

    <p>En fait, le signe "=" signifie "contient". La recherche
    d'une correspondance exacte est possible en utilisant "matches"
    ou "==". Utilisez "!=" pour tester si le terme &agrave;
    rechercher n'est <i>pas</i> contenu dans le champ (un
    &eacute;quivalent de "not ... contains ..."). Si vous
    sp&eacute;cifiez un type de champ dans l'expression &agrave;
    rechercher, la s&eacute;lection des types de champs (requis,
    optionels, g&eacute;n&eacute;raux) apparaissant dans les
    param&egrave;tres de recherche n'est pas prise en compte. Pour
    chercher des entr&eacute;es d'un certain type, un pseudo-champ
    nomm&eacute; "entrytype" est disponible&nbsp;:</p>

    <p>entrytype = thesis</p>

    <p>recherchera les entr&eacute;es dont le type (tel
    qu'affich&eacute; dans la colonne "Entrytype") contient le mot
    "thesis" (et qui sera donc "phdthesis" ou "mastersthesis"). Le
    pseudo-champ "bibtexkey" permet de rechercher dans les clefs de
    citation, tel que&nbsp;:</p>

    <p>bibtexkey = miller2005</p>

    <h2>Param&egrave;tres de recherche</h2>Le bouton
    <em>Param&egrave;tres</em> ouvre un menu qui permet de basculer
    en mode "sensible &agrave; la casse", d'utiliser des
    expressions r&eacute;guli&egrave;res lors de la recherche, et
    de d&eacute;finir si les r&eacute;sultats de la recherche
    doivent &ecirc;tre s&eacute;lectionn&eacute;s dans la table.
    <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
