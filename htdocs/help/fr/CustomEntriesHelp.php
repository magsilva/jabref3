<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Personnaliser les types d'entr&eacute;es</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>
	

    <h1>Personnaliser les types d'entr&eacute;es</h1>

    <p>Pour acc&eacute;der &agrave; cette fonction, cliquez sur le
    menu <strong>Options --&gt; Personnaliser les types
    d'entr&eacute;es</strong>.</p>

    <p>Lors de la personnalisation d'un type d'entr&eacute;e, vous
    d&eacute;finissez &agrave; la fois son apparence dans
    l'&eacute;diteur d'entr&eacute;es et ce qui est
    n&eacute;cessaire pour que JabRef consid&egrave;re une
    entr&eacute;e comme compl&egrave;te. Vous pouvez aussi bien
    changer les types d'entr&eacute;e existants qu'en
    d&eacute;finir de nouveaux.</p>

    <p>Notez qu'aucune des modifications que vous effectuez dans
    cette fen&ecirc;tre ne sera stock&eacute;e tant que vous
    n'aurez pas cliqu&eacute; sur <b>Apply</b> ou <b>OK</b>. Si
    vous cliquez sur <b>Cancel</b> ou si vous fermez simplement la
    fen&ecirc;tre, les changements non-appliqu&eacute;s seront
    perdus.</p>

    <h2>Utiliser la fen&ecirc;tre de personnalisation des
    entr&eacute;es</h2>

    <p>La fen&ecirc;tre de personnalisation des
    entr&eacute;es est divis&eacute;e en 3 panneaux principaux. Le
    panneau de gauche vous permet de s&eacute;lectionner
    l'entr&eacute;e &agrave; modifier, et d'en ajouter de
    nouvelles. Le panneau central est utilis&eacute; pour
    param&eacute;trer les champs requis du type d'entr&eacute;e
    s&eacute;lectionn&eacute;. Le panneau de droite est
    utilis&eacute; pour param&eacute;trer les champs optionnels du
    type d'entr&eacute;e s&eacute;lectionn&eacute;.</p>

    <h3>Ajouter et supprimer des types d'entr&eacute;es</h3>

    <p>Les types d'entr&eacute;es actuellement disponibles sont
    list&eacute;s dans le panneau de gauche. Lorsque vous
    s&eacute;lectionnez un type d'entr&eacute;e, les autres
    panneaux sont mis &agrave; jour afin d'afficher les champs
    requis et optionnels pour ce type d'entr&eacute;e.</p>

    <p>Pour ajouter un nouveau type d'entr&eacute;es, vous devez
    entrer son nom dans le champ de texte situ&eacute; sous la
    liste des types et cliquer sur <b>Ajouter</b> Le nouveau type
    d'entr&eacute;es sera ajout&eacute; &agrave; la liste et
    s&eacute;lectionn&eacute; pour pouvoir &ecirc;tre
    modifi&eacute;.</p>

    <p>Pour supprimer un type d'entr&eacute;es personnalis&eacute;,
    s&eacute;lectionnez-le et cliquez sur <b>Supprimer</b>. Cette
    op&eacute;ration n'est possible que pour les types
    d'entr&eacute;es personnalis&eacute;s qui ne sont pas de
    simples modifications des types standards. Il n'est pas
    possible de supprimer les types standards.</p>

    <p>Pour qu'un type standard modifi&eacute; reprenne ses valeurs
    par d&eacute;faut, s&eacute;lectionnez-le et cliquez sur
    <b>D&eacute;faut</b>. Cette op&eacute;ration n'est possible que
    pour les types d'entr&eacute;es personnalis&eacute;s qui
    modifient un type standard.</p>

    <h2>Editer les types d'entr&eacute;es</h2>

    <p>Quand un type
    d'entr&eacute;es est s&eacute;lectionn&eacute;, ses champs
    requis et optionnels sont list&eacute;s dans les panneaux du
    centre et de droite. La m&eacute;thode d'&eacute;dition des
    listes de champs est la m&ecirc;me pour les champs requis et
    optionnels.</p>

    <p>Pour ajouter un nouveau champ, &eacute;ditez le champ de
    texte situ&eacute; sous la liste, ou s&eacute;lectionnez un nom
    de champ &agrave; partir du menu d&eacute;roulant, puis cliquez
    sur <b>Ajouter</b>. Le nom du champ s&eacute;lectionn&eacute;
    sera ajout&eacute; &agrave; la fin de la liste.</p>

    <p>Pour supprimer un ou plusieurs champs,
    s&eacute;lectionnez-les dans la liste et cliquez sur
    <b>Supprimer</b>.</p>

    <p>Pour changer l'ordre des champs, s&eacute;lectionner le nom
    d'un champ et cliquer sur les boutons en forme de
    fl&egrave;ches pour le d&eacute;placer vers le haut ou vers le
    bas de la liste.</p> 
    
    <h3>Les champs et/ou </h3>
    <p>Certains types d'entr&eacute;es ont une condition et/ou
    dans leurs champs requis. Par exemple, une entr&eacute;e <em>book</em>
    est compl&egrave;te quand au moins un des champs <em>author</em> ou
    <em>editor</em> est rempli.
    Pour indiquer une telle condition dans un type d'entr&eacute;e
    personnalis&eacute;, vous devez ajouter un champ nomm&eacute;
    par une s&eacute;rie de champs alternatifs s&eacute;par&eacute;s
    par des slashs (/), comme par exemple <em>author/editor</em>
    pour indiquer la condition mentionn&eacute;e ci-dessus
    pour l'entr&eacute;e de type <em>book</em>.</p>
    
    
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
