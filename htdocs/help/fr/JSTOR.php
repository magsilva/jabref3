<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Recherche JStor</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>
	


  <h1>Recherche JStor</h1>

  <p>Pour utiliser cette fonction, choisissez <b>Recherche -&gt; Recherche Web</b>.
    L'interface de recherche apparaitra dans le panneau lat&eacute;ral. S&eacute;lectionnez
    <b>JSTOR</b> dans le menu d&eacute;roulant. La recherche JStor s'effectue
    gr&acirc;ce au projet d'extraction de donn&eacute;es de BibSonomy
    (http://scraper.bibsonomy.org/) pour extraire des informations des page
    web de JStor.</p>

    <p>L'aide ci-dessous a &eacute;t&eacute; copi&eacute;e depuis le site web de JStor et pourrait changer avec JStor&nbsp;:</p>
    <ul>
      <li>Par d&eacute;faut, votre recherche portera sur l'auteur, le titre et le texte complet de tous les types de contenu de p&eacute;riodiques (articles, synth&egrave;ses de livre, etc.)</li>
      <li>Utilisez des guillemets pour rechercher une phrase (e.g., &quot;punctuated equilibrium&quot;)</li>
      <li>Utilisez ti: pour rechercher le titre d'un article, au: pour rechercher un auteur (e.g.,
  ti:&quot;two-person cooperative games&quot;, au:&quot;john nash&quot;)</li>
      <li>Utilisez AND, OR, NOT pour combiner les termes (e.g., ti:&quot;two-person cooperative games&quot; AND au:&quot;john nash&quot;)</li>
    </ul>

    <p>JSTOR est une organisation &agrave; but non lucratif avec une double mission de cr&eacute;er et de maintenir une archive de journaux savants importants, et de fournir un acc&egrave;s aussi large que possible &agrave; ces journaux. JSTOR offre aux chercheurs la possibilit&eacute; de r&eacute;cup&eacute;rer des images num&eacute;ris&eacute;es de haute r&eacute;solution des num&eacute;ros et des pages de journaux, tels qu'ils ont &eacute;t&eacute; originellement con&ccedil;us, imprim&eacute;s et illustr&eacute;s. Les journaux archiv&eacute;s dans JSTOR couvrent beaucoup de disciplines.</p>

    <p>JStor offre l'acc&egrave;s aux sujets suivants&nbsp;:</p>
    <ul>
      <li>art &amp; sciences</li>
      <li>biologie</li>
      <li>affaire</li>
      <li>&eacute;cologie &amp; botanique</li>
      <li>sant&eacute; &amp; sciences g&eacute;n&eacute;rales</li>
      <li>langues &amp; litt&eacute;rature</li>
      <li>math&eacute;matiques &amp; statistiques</li>
      <li>musique</li>
    </ul>

    <p>Le nombre d'entr&eacute;es trouv&eacute;es s'affichera et le nombre d'entr&eacute;es &agrave; t&eacute;l&eacute;charger
    vous sera demand&eacute;. Une recherche renvoie un maximum de 200 r&eacute;sultats
    (8&nbsp;pages de 25&nbsp;r&eacute;sultats) afin de limiter la charge.</p>

  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
