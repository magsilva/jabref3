<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>Filtres d'exportation personnalis&eacute;s</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Retour au contenu</a>


    <h1>Filtres d'exportation personnalis&eacute;s</h1>

    <p>JabRef vous permet de d&eacute;finir et d'utiliser vos
    propres filtres d'exportation de la m&ecirc;me mani&egrave;re
    que les filtres d'exportation standards. Un filtre
    d'exportation est d&eacute;fini par un ou plusieurs <i>fichiers
    gabarit</i> qui, avec l'aide d'un certain nombre de routines
    internes de formatage, d&eacute;finissent le format des
    fichiers export&eacute;s. Vos fichiers gabarit doivent
    &ecirc;tre pr&eacute;par&eacute;s avec un &eacute;diteur de
    texte &agrave; l'ext&eacute;rieur de JabRef.</p>

    <h2>Ajout d'un filtre d'exportation personnalis&eacute;</h2>

    <p>La seule obligation pour avoir un filtre d'exportation
    valide est l'existence d'un fichier avec l'extension
    <b>.layout</b>. Pour ajouter un nouveau filtre d'exportation,
    on utilise le menu <b>Options -&gt; G&eacute;rer les
    exportations personnalis&eacute;es</b>, et on clique sur
    <b>Ajouter nouvelle</b>. Une nouvelle boite de dialogue
    appara&icirc;t et vous permet de sp&eacute;cifier le nom du
    nouveau filtre d'exportation (ce nom appara&icirc;tra ensuite
    comme l'un des choix du menu d&eacute;roulant "Type de fichier"
    de la fen&ecirc;tre de dialogue affect&eacute;e au menu
    <b>Fichier -&gt; Exporter</b> de la fen&ecirc;tre principale de
    JabRef), le chemin du fichier <b>.layout</b>, et l'extension de
    fichier pr&eacute;f&eacute;r&eacute;e par le filtre
    d'exportation (c'est cette extension qui sera
    sugg&eacute;r&eacute;e dans la boite de dialogue lorsque le
    filtre sera utilis&eacute;).</p>

    <h2>Cr&eacute;ation d'un filtre d'exportation</h2>

    <p>Pour voir des exemples de constitution de filtres
    d'exportation, recherchez le r&eacute;pertoire contenant les
    fichiers gabarit des filtres d'exportation standards sur notre
    page de t&eacute;l&eacute;chargement.</p>

    <h3>Les fichiers gabarit</h3>

    <p>On suppose que l'on veut cr&eacute;er un filtre
    d'exportation pour une sortie HTML.</p>

    <p>Bien que le filtre d'exportation ne n&eacute;cessite qu'un
    seul fichier <b>.layout</b>, qui dans ce cas pourrait s'appeler
    <i>html.layout</i>, vous pouvez d&eacute;sirer ajouter deux
    autres fichiers appel&eacute;s <i>html.begin.layout</i> et
    <i>html.end.layout</i>. Le premier contient le d&eacute;but de
    la sortie et le dernier la fin. JabRef recherche ces deux
    fichiers quelque soit le fichier d'exportation utilis&eacute;
    et, s'il les trouve, les recopie tel quel dans la sortie avant
    et apr&egrave;s l'&eacute;criture des entr&eacute;es
    individuelles.</p>

    <p>Il faut noter que ces fichiers doivent &ecirc;tre dans le
    m&ecirc;me r&eacute;pertoire que le fichier <i>html.layout</i>,
    et que leur nom doit comporter <b>.begin</b> pour l'un et
    <b>.end</b> pour l'autre.</p>

    <p>Dans notre exemple de fichier d'exportation, cela pourrait
    ressembler &agrave;</p>

    <p><i>html.begin.layout</i>&nbsp;:<br />
    <code>&lt;HTML&gt;<br />
     &lt;BODY&gt; text="#275856"&gt;<br />
    &lt;basefont size="4" color="#2F4958"
    face="arial"&gt;</code></p>

    <p><i>html.end.layout</i>&nbsp;:<br />
    <code>&lt;/BODY&gt;<br />
     &lt;/HTML&gt;</code></p>

    <p>Le fichier <i>html.layout</i> fournit le gabarit par
    d&eacute;faut pour l'exportation d'une seule entr&eacute;e. Si
    vous devez utiliser diff&eacute;rents gabarits pour les
    diff&eacute;rentes entr&eacute;es, vous pouvez le faire en
    ajoutant des fichiers <b>.layout</b> sp&eacute;cifiques. Les
    fichiers doivent aussi &ecirc;tre dans le m&ecirc;me
    r&eacute;pertoire que le gabarit principal et ils sont
    nomm&eacute;s en ins&eacute;rant <b>.entrytype</b> dans le nom
    du fichier gabarit principal. Le nom de l'entr&eacute;e doit
    &ecirc;tre en minuscules. Dans notre exemple, on peut vouloir
    ajouter un gabarit diff&eacute;rent pour les livres et cela se
    fera via le fichier <i>html.book.layout</i>. Pour une
    th&egrave;se, on ajoutera le fichier
    <i>html.phdthesis.layout</i>. Ces fichiers sont similaires au
    gabarit principal, si ce n'est qu'ils sont utilis&eacute;s pour
    des entr&eacute;es sp&eacute;cifiques. A noter que le gabarit
    g&eacute;n&eacute;ral peut ais&eacute;ment &ecirc;tre
    cr&eacute;&eacute; suffisamment g&eacute;n&eacute;ral pour
    &ecirc;tre utilisable avec la plupart des entr&eacute;es dans
    la majorit&eacute; des filtres d'exportation.</p>

    <h3>Le format des fichiers gabarit</h3>

    <p>Les fichiers gabarit utilisent un simple langage de balisage
    dans lequel les commandes sont identifi&eacute;es par
    l'antislash (\) les pr&eacute;c&eacute;dant. Tout texte non
    identifi&eacute; comme faisant partie d'une entr&eacute;e est
    recopi&eacute; tel quel dans le fichier de sortie.</p>

    <h3>Les commandes relatives aux champs</h3>

    <p>Les mots pr&eacute;c&eacute;d&eacute;s d'un antislash, par
    exemple <code>\author</code>, <code>\editor</code>,
    <code>\title</code> ou <code>\year</code>, sont
    interpr&eacute;t&eacute;s comme des r&eacute;f&eacute;rences
    aux champs correspondants et le contenu du champ est
    copi&eacute; directement dans la sortie.</p>

    <h3>Les formateurs de champs</h3>

    <p>Souvent, on a besoin de faire subir au contenu d'un champ un
    pr&eacute;-traitement avant de le copier dans le fichier de
    sortie. Cela est r&eacute;alis&eacute; en utilisant un
    <i>formateur de champ</i> - une classe java contenant une seule
    m&eacute;thode qui manipule le contenu du champ.</p>

    <p>Le formateur est utilis&eacute; en ins&eacute;rant la
    commande <code>\format</code> suivie du nom du formateur entre
    crochets et du nom du champ entre accolades, par exemple</p>

    <p><code>\format[ToLowerCase]{\author}</code></p>

    <p>Vous pouvez aussi indiquer plusieurs formateurs
    s&eacute;par&eacute;s par des virgules. Ils seront alors
    appel&eacute;s s&eacute;quentiellement de la gauche vers la
    droite, par exemple&nbsp;:</p>

    <p><code>\format[ToLowerCase,HTMLChars]{\author}</code></p>

    <p>va d'abord appliquer le formateur <b>ToLowerCase</b> puis
    <b>HTMLChars</b> sur le r&eacute;sultat. Vous pouvez lister un
    nombre arbitraire de formateurs de cette mani&egrave;re.</p>

    <p>Le param&egrave;tre des formateurs, entre les accolades,
    n'est pas obligatoirement une commande de champ. Vous pouvez y
    ins&eacute;rer du texte normal, qui sera alors pass&eacute;
    comme argument au formateur. Cela peut-&ecirc;tre utile avec
    certains formateurs, par exemple le formateur CurrentDate (voir
    ci-dessous).</p>

    <p>JabRef fournit les formateurs suivants, certains d'entre eux
    d&eacute;pendant d'autres formateurs&nbsp;:</p>

    <ul>
        <li><code>HTMLChars</code>&nbsp;: remplace les
        caract&egrave;res sp&eacute;ciaux sp&eacute;cifiques
        &agrave; TeX (par exemple&nbsp;: {\^a} ou {\"{o}}) par leur
        repr&eacute;sentation HTML.</li>

        <li><code>HTMLParagraphs</code>&nbsp;: interpr&egrave;te
        deux retours-chariot cons&eacute;cutifs (comme \n \n) comme
        le d&eacute;but d'un nouveau paragraphe et cr&eacute;e les
        balises html de paragraphes appropri&eacute;es.</li>

        <li><code>XMLChars</code>&nbsp;: remplace les
        caract&egrave;res sp&eacute;ciaux sp&eacute;cifiques
        &agrave; TeX (par exemple&nbsp;: {\^a} ou {\"{o}}) par leur
        repr&eacute;sentation XML.</li>

        <li><code>CreateDocBookAuthors</code>&nbsp;: formate le
        contenu du champ author selon le style DocBook.</li>

        <li><code>CreateDocBookEditors</code>&nbsp;: &agrave;
        documenter.</li>

        <li><code>CurrentDate</code>&nbsp;: renvoie la date
        actuelle. Sans argument, ce formateur renvoie la date et
        l'heure actuelle au format "yyyy.MM.dd hh:mm:ss z" (date,
        heure et fuseau horaire). En donnant une cha&icirc;ne de
        format diff&eacute;rent comme argument, le format de la
        date peut-&ecirc;tre adapt&eacute;. Par exemple,
        <code>\format[CurrentDate]{yyyy.MM.dd}</code> renverra
        uniquement la date, comme par exemple 2005.11.30.</li>

        <li><code>AuthorFirstFirst</code>&nbsp;: formate le contenu
        des champs author/editor en mettant les pr&eacute;noms en
        premier.</li>

        <li><code>AuthorFirstFirstCommas</code>&nbsp;: formate le
        contenu des champs author/editor en mettant les
        pr&eacute;noms en premier et des virgules comme
        s&eacute;parateurs.</li>

        <li><code>AuthorFirstAbbrLastCommas</code>&nbsp;: &agrave;
        documenter.</li>

        <li><code>AuthorFirstAbbrLastOxfordCommas</code>&nbsp;:
        &agrave; documenter.</li>

        <li><code>AuthorFirstLastOxfordCommas</code>&nbsp;:
        &agrave; documenter.</li>

        <li><code>AuthorLastFirst</code>&nbsp;: formate le contenu
        des champs author/editor en mettant le nom de famille en
        premier.</li>

        <li><code>AuthorLastFirstAbbreviator</code>&nbsp;:
        r&eacute;duit les pr&eacute;noms de tous les auteurs
        &agrave; leurs initiales. Ce formateur n&eacute;cessite
        d'avoir pr&eacute;alablement utilis&eacute;
        AuthorLastFirst.</li>

        <li><code>AuthorLastFirstCommas</code>&nbsp;: &agrave;
        documenter.</li>

        <li><code>AuthorLastFirstOxfordCommas</code>&nbsp;:
        &agrave; documenter.</li>

        <li><code>AuthorLastFirstAbbrCommas</code>&nbsp;: &agrave;
        documenter.</li>

        <li><code>AuthorLastFirstAbbrOxfordCommas</code>&nbsp;:
        &agrave; documenter.</li>

        <li><code>AuthorAndsReplacer</code>&nbsp;: remplace "and"
        par ";" entre les premiers noms et par "&amp;" entre les
        deux derniers.</li>

        <li><code>AuthorAndsCommaReplacer</code>&nbsp;: remplace
        "and" entre les noms par une virgule (",") et "&amp;" entre
        les deux derniers.</li>

        <li><code>AuthorOrgSci</code>&nbsp;: premier auteur selon
        "nom, pr&eacute;nom" et tous les autres selon
        "pr&eacute;nom nom". Les pr&eacute;noms sont
        abr&eacute;g&eacute;s.</li>

        <li><code>AuthorAbbreviator</code>&nbsp;: A
        documenter.</li>

        <li><code>AuthorNatBib</code>&nbsp;: Formats des noms
        d'auteurs dans le style NatBib, avec les noms propres
        s&eacute;par&eacute;s par "and" s'il y a deux auteurs, ou
        le premier nom suivi de "et al." s'il y en a plus de
        deux.</li>

        <li><code>NoSpaceBetweenAbbreviations</code> : Les espaces
        entre les initiales des pr&eacute;noms sont
        supprim&eacute;s.</li>

        <li><code>FormatPagesForHTML</code>&nbsp;: remplace "--"
        par "-".</li>

        <li><code>FormatPagesForXML</code>&nbsp;: remplace "--" par
        un tiret XML.</li>

        <li><code>RemoveBrackets</code>&nbsp;: supprime toutes les
        accolades "{" ou "}".</li>

        <li><code>RemoveBracketsAddComma</code>&nbsp;: &agrave;
        documenter.</li>

        <li><code>RemoveWhitespace</code>&nbsp;: &agrave;
        documenter.</li>

        <li><code>RemoveLatexCommands</code>&nbsp;: supprime toutes
        les commandes LaTeX comme <code>\em</code>,
        <code>\textbf</code>, etc. Lorsqu'il est utilis&eacute;
        avec <code>HTMLChars</code> ou <code>XMLChars</code>, ce
        formateur doit &ecirc;tre appel&eacute; en dernier.</li>

        <li><code>RemoveTilde</code>&nbsp;: remplace le
        caract&egrave;re tilde (utilis&eacute; dans LaTeX comme un
        espace ins&eacute;cable) par un espace normal. Utile en
        combinaison avec NameFormatter comme discut&eacute; dans la
        prochaine section.</li>

        <li><code>ToLowerCase</code>&nbsp;: bascule tous les
        caract&egrave;res en minuscules.</li>

        <li><code>CompositeFormat</code>&nbsp;: &agrave;
        documenter.</li>

        <li><code>GetOpenOfficeType</code>&nbsp;: &agrave;
        documenter.</li>

        <li><code>RTFChars</code>&nbsp;: &agrave; documenter.</li>

        <li><code>ResolvePDF</code>&nbsp;: &agrave;
        documenter.</li>
    </ul>

    <p>Si aucun des formateurs disponibles ne peut faire ce que
    vous d&eacute;sirez, vous pouvez ajouter le votre &agrave;
    l'interface
    <code>net.sf.jabref.export.layout.LayoutFormatter</code>. Si
    vous ins&eacute;rez votre propre classe dans
    <code>net.sf.jabref.export.layout.format</code>, vous pouvez
    appeler votre formateur en utilisant son nom de classe, comme
    pour les formateurs standards. Sinon, vous devez appeler le
    formateur par son nom complet (incluant le nom du package).
    Dans les deux cas, le formateur doit &ecirc;tre dans votre
    chemin de classe lorsque vous lancez JabRef</p>

    <h2 id="NameFormatter">Utiliser des formateurs de nom
    personnalis&eacute;</h2>

    <p>Avec JabRef 2.2, il est maintenant possible de
    d&eacute;finir des formateurs de nom personnalis&eacute;s et
    utilisant la syntaxe des fichiers de style BibTeX. Cela permet
    une flexibilit&eacute; totale, mais c'est fastidieux &agrave;
    &eacute;crire</p>

    <p>Vous pouvez d&eacute;finir votre propre formateur dans
    l'onglet "Formateur de nom" des pr&eacute;f&eacute;rences en
    utilisant le format suivant et en l'utilisant ensuite avec le
    nom que vous avez d&eacute;fini comme de n'importe quel autre
    formateur</p>
    <code>&lt;cas1&gt;@&lt;gamme11&gt;@&lt;format&gt;@&lt;gamme12&gt;@&lt;format&gt;@&lt;gamme13&gt;...@@<br />

     &lt;cas2&gt;@&lt;gamme21&gt;@... et ainsi de suite.</code> 

    <p>Ce format commence par s&eacute;parer la tache de formatage
    de la liste d'auteurs dans des cas d&eacute;pendant du nombre
    d'auteurs qu'il y a (c'est ainsi car certains formats
    diff&egrave;rent en fonction du nombre d'auteurs). Chaque cas
    individuel est s&eacute;par&eacute; par @@ et contient les
    instructions sur la fa&ccedil;on de formater chaque auteur dans
    le cas consid&eacute;r&eacute;. Ces instructions sont
    s&eacute;par&eacute;es par un @.</p>

    <p>Les cas sont identifi&eacute;s en utilisant des entiers (1,
    2, 3, etc.) ou le caract&egrave;re * (correspondant &agrave;
    n'importe quel nombre d'auteurs) et sp&eacute;cifieront le
    formateur &agrave; appliquer s'il y a un nombre
    inf&eacute;rieur ou &eacute;gal d'auteurs.</p>

    <p>Les gammes sont soit
    <code>&lt;entier&gt;..&lt;entier&gt;</code>,
    <code>&lt;entier&gt;</code> ou le caract&egrave;re
    <code>*</code> en utilisant un index bas&eacute; sur 1 pour
    indexer les auteurs d'une liste donn&eacute;e d'auteurs. Les
    index entiers peuvent &ecirc;tre n&eacute;gatif afin de
    signifier qu'ils commencent par la fin de la liste o&ugrave; -1
    est le dernier auteur.</p>

    <p>Par exemple, avec une liste d'auteurs comme "Joe Doe and
    Mary Jane and Bruce Bar and Arthur Kay":</p>

    <ul>
        <li>1..3 affectera Joe, Mary and Bruce</li>

        <li>4..4 affectera Arthur</li>

        <li>* les affectera tous</li>

        <li>2..-1 affectera Mary, Bruce and Arthur</li>
    </ul>

    <p>Les cha&icirc;nes de <code>&lt;format&gt;</code> utilisent
    le format du formateur BibTeX&nbsp;:</p>

    <p>Les quatre lettres v, f, l et j indiquent les parties du nom
    von, first, last et jr qui sont utilis&eacute;es entre
    accolades. Une unique lettre v, f, l ou j indique que le nom
    doit &ecirc;tre abr&eacute;g&eacute;. Si l'une de ces lettres
    ou paires de lettres sont rencontr&eacute;es, JabRef retournera
    tous les noms respectifs (potentiellement
    abr&eacute;g&eacute;s), mais l'expression totale entre
    accolades est uniquement imprim&eacute;e si la partie du nom
    existe.</p>

    <p>Par exemple, si le format est "{ll} {vv {von Part}} {ff}" et
    si les noms sont "Mary Kay and John von Neumann", alors JabRef
    retournera "Kay Mary" (avec deux espaces entre le nom propre et
    le pr&eacute;nom) et "Neuman von von Part John".</p>

    <p>Je donne ici deux exemples, mais je pr&eacute;f&egrave;rerai
    vous diriger vers la documentations BibTeX.</p>

    <p>Exemple court&nbsp;: <code>"{ll}, {f.}"</code> va convertir
    <code>"Joe Doe"</code> en <code>"Doe, J."</code></p>

    <p>Exemple long&nbsp;:</p>

    <blockquote>
        <p>Pour convertir&nbsp;:</p>

        <p><code>"Joe Doe and Mary Jane and Bruce Bar and Arthur
        Kay"</code></p>

        <p>en</p>

        <p><code>"Doe, J., Jane, M., Bar, B. and Kay,
        A."</code></p>

        <p>vous devrez utiliser</p>

        <p><code>1@*@{ll}, {f}.@@2@1@{ll}, {f}.@2@ and {ll},
        {f}.@@*@1..-3@{ll}, {f}., @-2@{ll}, {f}.@-1@ and {ll},
        {f}.</code></p>
    </blockquote>

    <p>Si quelqu'un souhaite &eacute;crire un meilleur didacticiel
    sur ce sujet, envoyez un courriel sur l'une des listes de
    diffusion de JabRef&nbsp;!</p>

    <h3>Les sorties conditionnelles</h3>

    <p>Certaines informations dans les sorties ne prennent de sens
    que si un certain champ est utilis&eacute;. Par exemple, disons
    que l'on veuille faire suivre le nom de l'&eacute;diteur par le
    texte <code>(Ed.)</code>. Cela peut &ecirc;tre
    r&eacute;alis&eacute; avec le code suivant&nbsp;:</p>

    <p><code>\format[HTMLChars,AuthorFirstFirst]{\editor}
    (Ed.)</code></p>

    <p>Cependant, si le champs <code>editor</code> n'a pas
    &eacute;t&eacute; renseign&eacute; - il n'a pas de sens pour
    l'entr&eacute;e export&eacute;e - le texte <code>(Ed.)</code>
    doit &ecirc;tre ignor&eacute;. Cela peut &ecirc;tre
    effectu&eacute; en utilisant les commandes <code>\begin</code>
    et <code>\end</code>&nbsp;:</p>

    <p><code>\begin{editor}<br />
    \format[HTMLChars,AuthorFirstFirst]{\editor} (Ed.)<br />
     \end{editor}</code></p>

    <p>Les commandes <code>\begin</code> et <code>\end</code>
    assure que le texte contenu entre les deux commandes ne sera
    imprim&eacute; que si et seulement si le champ
    sp&eacute;cifi&eacute; entre accolades est renseign&eacute;
    dans l'entr&eacute;e que l'on veut exporter.</p>

    <p><b>Note&nbsp;:</b> L'utilisation des commandes
    <code>\begin</code> et <code>\end</code> est une mani&egrave;re
    astucieuse de cr&eacute;er des gabarits qui sont communs
    &agrave; une grande vari&eacute;t&eacute; d'entr&eacute;es.</p>

    <h3>Les sorties group&eacute;es</h3>

    <p>Si vous d&eacute;sirez s&eacute;parer vos entr&eacute;es en
    groupes bas&eacute;s sur un certain champ, vous pouvez utiliser
    les commandes de sorties group&eacute;es. La sortie
    group&eacute;e est assez similaire aux sorties conditionnelles,
    except&eacute; que le texte sp&eacute;cifi&eacute; n'est
    imprim&eacute; que si le champ indiqu&eacute; dans les
    accolades change de valeur.</p>

    <p>Par exemple, on suppose que l'on d&eacute;sire faire des
    groupes &agrave; partir de mots-clefs. Avant l'exportation, on
    s'assure que les entr&eacute;es sont tri&eacute;es selon les
    mots-clefs. Ensuite, on utilise les commandes suivantes pour
    les grouper par mot-clefs&nbsp;:</p>

    <p><code>\begingroup{keywords}New Category:
    \format[HTMLChars]{\keywords}<br />
     \endgroup{keywords}</code></p>

    <h2>Partage de votre travail</h2>

    <p>Avec les fichiers gabarit externes, il est relativement
    simple de partager des formats d'exportation entre
    utilisateurs. Si vous &eacute;crivez un filtre d'exportation
    pour un format non support&eacute; par JabRef, ou si vous
    am&eacute;liorez un filtre d&eacute;j&agrave; existant, nous
    vous encourageons &agrave; d&eacute;poser votre travail sur
    notre page SourceForge.net. La m&ecirc;me chose est possible
    pour les nouvelles classes de formateur que vous avez
    &eacute;crites. Nous serons heureux de distribuer une
    collection des fichiers gabarit soumis ou de les ajouter
    &agrave; la s&eacute;rie des filtres d'exportation standard ou
    des formateurs.</p>
    <?php include("../../footer.php"); ?>
  </div>

</body>
</html>