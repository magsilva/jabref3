<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title></title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>



    <h1>La fen&ecirc;tre principale de
    JabRef</h1>

    <p><em>Note&nbsp;:</em> La plupart des menus dont il
    est question dans les paragraphes suivants ont des raccourcis
    claviers, et beaucoup sont accessibles depuis la barre
    d'outils. Les raccourcis clavier sont affich&eacute;s dans les
    menus d&eacute;roulants.</p>

    <p>Ceci est la fen&ecirc;tre principale &agrave; partir de
    laquelle vous travaillez avec vos bases de donn&eacute;es. En
    dessous de la barre de menus et de la barre d'outils se trouve
    un panneau &agrave; onglets contenant un onglet pour chacune de
    vos bases de donn&eacute;es actuellement ouvertes. Quand vous
    s&eacute;lectionnez un de ces onglets, un tableau
    appara&icirc;t, listant toutes les entr&eacute;es de la base de
    donn&eacute;es, ainsi qu'une s&eacute;lection configurable de
    leurs champs.</p>

    <ul>
        <li>Vous d&eacute;cidez des champs affich&eacute;s dans ce
        tableau en cochant les champs que vous voulez voir dans la
        fen&ecirc;tre de dialogue
        <strong>Pr&eacute;f&eacute;rences</strong>.</li>

        <li>Pour &eacute;dit&eacute;r la valeur d'un champ,
        double-cliquez sur la ligne que vous voulez modifiez. Vous
        pouvez naviguer dans le tableau en utilisant les
        fl&egrave;ches du clavier.</li>

        <li>Le tableau est tri&eacute; selon une s&eacute;rie de
        champs de votre choix. L'ordre de tri par d&eacute;faut peut être
        configur&eacute; dans <strong>Options -&gt;
        Pr&eacute;f&eacute;rences -&gt; Table des
        entr&eacute;es</strong> mais pour changer plus rapidement cet
        ordre, cliquez l'ent&ecirc;te d'une colonne pour la
        d&eacute;finir comme crit&egrave;re de tri principal, ou
        inverser l'ordre du tri s'il est d&eacute;j&agrave;
        d&eacute;fini. Un clic suppl&eacute;mentaire
        d&eacute;s&eacute;lectionnera la colonne comme
        crit&egrave;re de tri. Maintenez la touche <b>CONTROL</b>
        enfonc&eacute;e et cliquez sur un autre ent&ecirc;te de
        colonne pour l'ajouter, l'inverser ou l'enlever comme
        crit&egrave;re de tri auxiliaire. Vous pouvez ajouter un
        nombre arbitraire de crit&egrave;res auxiliaires, mais uniquement
        trois niveaux seront m&eacute;moris&eacute;s pour
        le d&eacute;marrage suivant de JabRef.</li>

        <li>Vous pouvez ajuster la largeur des colonnes en faisant
        glisser les limites entre les ent&ecirc;tes.</li>

        <li>Dans la fen&ecirc;tre de dialogue
        <strong>Pr&eacute;f&eacute;rences</strong>, choisissez si
        le tableau doit &ecirc;tre redimensionn&eacute; pour
        s'ajuster &agrave; la fen&ecirc;tre. S&eacute;lectionnez
        cette option pour toujours voir l'ensemble du tableau, et
        d&eacute;s&eacute;lectionnez l&agrave; pour permettre
        l'affichage de plus d'informations.</li>

        <li>
            Les codes de couleurs vous aident &agrave; visualiser
            l'&eacute;tat de votre base de donn&eacute;es. Les
            cellules sont color&eacute;es de la fa&ccedil;on
            suivante&nbsp;:

            <ul>
                <li>Une cellule <font color="red">rouge</font> dans
                la colonne la plus &agrave; gauche signale une
                entr&eacute;e incompl&egrave;te.</li>

                <li>Une cellule <font color="#909000">jaune</font>
                dans la colonne la plus &agrave; gauche signale une
                entr&eacute;e qui ne d&eacute;finit pas par
                elle-m&ecirc;me l'ensemble des champs requis, mais
                qui contient un renvoi.</li>

                <li>Une cellule <font color="blue">bleue</font>
                correspond &agrave; un champ requis.</li>

                <li>Une cellule <font color="green">verte</font>
                correspond &agrave; un champ optionnel.</li>

                <li>Une cellule sans couleur correspond &agrave; un
                champ qui n'est pas utilis&eacute; par le programme
                <em>BibTeX</em> pour ce type d'entr&eacute;e. Le
                champ peut cependant &ecirc;tre &eacute;dit&eacute;
                dans JabRef.</li>
            </ul>
        </li>

        <li style="list-style: none">Les codes de couleurs peuvent
        &ecirc;tre modifi&eacute;s dans la fen&ecirc;tre de
        dialogue <strong>Pr&eacute;f&eacute;rences</strong>.</li>
    </ul>

    <h2>Ajouter une nouvelle entr&eacute;e</h2>

    <p>Il y a plusieurs
    fa&ccedil;ons d'ajouter une nouvelle entr&eacute;e.
    L'activation du menu <strong>BibTeX/ Nouvelle
    entr&eacute;e</strong> affiche une fen&ecirc;tre de dialogue
    o&ugrave; vous pouvez choisir le type d'entr&eacute;e &agrave;
    partir d'une liste. Pour &eacute;viter cette fen&ecirc;tre de
    dialogue, vous pouvez utilisez le menu <strong>BibTeX/ Nouvelle
    entr&eacute;e...</strong> ainsi que des raccourcis clavier pour
    les types les plus courants.</p>

    <p>Lorsqu'une nouvelle entr&eacute;e est ajout&eacute;e, par
    d&eacute;faut,  l'<a href="EntryEditorHelp.php">&eacute;diteur
    d'entr&eacute;es</a> s'ouvre. Ce comportement peut &ecirc;tre
    modifi&eacute; dans la fen&ecirc;tre de dialogue
    <strong>Pr&eacute;f&eacute;rences</strong>.</p>

    <p><em>Note&nbsp;:</em> Nous vous recommandons fortement
    d'apprendre les raccourcis clavier des types d'entr&eacute;es
    que vous utilisez le plus souvent, tel que CTRL-SHIFT-A pour
    l'ajout d'une entr&eacute;e <em>article</em>.</p>

    <h2>Editer une entr&eacute;e</h2>

    <p>Pour ouvrir l'<a href="EntryEditorHelp.php">&eacute;diteur
    d'entr&eacute;es</a> sur une entr&eacute;e existante,
    double-cliquez simplement sur la ligne correspondant &agrave;
    l'entr&eacute;e (ou appuyez sur ENTREE apr&egrave;s avoir
    s&eacute;lectionn&eacute; l'entr&eacute;e).</p>

    <h2>R&eacute;f&eacute;rencer une cha&icirc;ne <em>BibTeX</em>
    dans un champ</h2>

    <p>Dans JabRef vous &eacute;crivez le contenu de
    tous les champs de la m&ecirc;me fa&ccedil;on que dans un
    &eacute;diteur de texte, &agrave; une exception
    pr&egrave;s&nbsp;: pour r&eacute;f&eacute;rencer une
    cha&icirc;ne, entourer le nom de la cha&icirc;ne avec le
    caract&egrave;re #, tel que dans&nbsp;:<br />
    &nbsp;&nbsp;'#jan# 1997',<br />
    ce qui sera interpr&eacute;t&eacute; comme la cha&icirc;ne
    nomm&eacute;e 'jan' suivie de '1997'.</p>

    <p>Voir aussi&nbsp;: <a href="StringEditorHelp.php">Editeur de
    cha&icirc;ne</a>. </p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
