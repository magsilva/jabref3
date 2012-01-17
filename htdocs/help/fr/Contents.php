<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" xml:lang="fr">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Contenu de l'aide</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Retour au contenu</a>


    <h1>Contenu de l'aide</h1>

    <h2>G&eacute;n&eacute;ral</h2>
    <ul>
      <li><a href="HelpHelp.php">A propos de la fen&ecirc;tre d'aide</a></li>
      <li><a href="JabRefHelp.php">Informations g&eacute;n&eacute;rales</a></li>
      <li><a href="BaseFrameHelp.php">La fen&ecirc;tre principale de JabRef</a></li>
      <li><a href="EntryEditorHelp.php">&Eacute;diteur d'entr&eacute;es</a></li>
      <li><a href="Autosave.php">Sauvegarde automatique</a></li>
    </ul>

    <h2>Champs</h2>
    <ul>
      <li><a href="BibtexHelp.php">Aide sur <em>BibTeX</em></a></li>
      <li><a href="FileLinks.php">Liens de fichiers externes</a></li>
      <li><a href="ExternalFiles.php">Liens PDF/PS/URL/DOI dans JabRef</a></li>
      <li><a href="TimeStampHelp.php">Horodatage des entr&eacute;es</a></li>
      <li><a href="OwnerHelp.php">Le champ 'owner' (propri&eacute;taire)</a></li>
      <li><a href="ContentSelectorHelp.php">S&eacute;lecteur de contenu de champ</a></li>
      <li><a href="JournalAbbreviations.php">Abr&eacute;viations de journaux</a></li>
    </ul>

    <h2>Recherche et tri des entr&eacute;es</h2>
    <ul>
      <li><a href="GroupsHelp.php">Utilisation des <em>Groupes</em></a></li>
      <li><a href="MarkingHelp.php">&Eacute;tiqueter des entr&eacute;es</a></li>
      <li><a href="SearchHelp.php">Recherche dans JabRef</a></li>
    </ul>

    <h2>Configuration</h2>
    <ul>
      <li><a href="StringEditorHelp.php">&Eacute;diteur de cha&icirc;nes</a></li>
      <li><a href="DatabaseProperties.php">Propri&eacute;t&eacute;s de la base de donn&eacute;es</a></li>
      <li><a href="PreviewHelp.php">Configuration de l'aper&ccedil;u des entr&eacute;es</a></li>
      <li><a href="LabelPatterns.php">Personnalisation du g&eacute;n&eacute;rateur de clefs BibTeX</a></li>
      <li><a href="CustomEntriesHelp.php">Personnalisation des types d'entr&eacute;es</a></li>
      <li><a href="GeneralFields.php">Personnalisation des champs g&eacute;n&eacute;raux</a></li>
      <li><a href="Plugin.php">Etendre JabRef en utilisant des greffons</a></li>
    </ul>

    <h2>Importation/Exportation</h2>
    <ul>
      <li><a href="CustomExports.php">Filtres d'exportation personnalis&eacute;s</a></li>
      <li><a href="CustomImports.php">Filtres d'importation personnalis&eacute;s</a></li>
      <li><a href="ImportInspectionDialog.php">Fen&ecirc;tre de v&eacute;rification des importations</a></li>
      <li><a href="EndNoteFilters.php">Filtres d'importation/exportation d'EndNote</a></li>
      <li><a href="OpenOfficeIntegration.php">Utiliser JabRef avec OpenOffice.org ou LibreOffice</a></li>
      <li><a href="ACMPortalHelp.php">R&eacute;cup&eacute;ration des entr&eacute;es depuis le portail <em>ACM</em></a></li>
      <li><a href="CiteSeerHelp.php">R&eacute;cup&eacute;ration des entr&eacute;es depuis <em>CiteSeerX</em></a></li>
      <li><a href="IEEEXploreHelp.php">R&eacute;cup&eacute;ration des entr&eacute;es depuis <em>IEEExplore</em></a></li>
      <li><a href="MedlineHelp.php">R&eacute;cup&eacute;ration des entr&eacute;es depuis <em>Medline</em></a></li>
      <li><a href="JSTOR.php">R&eacute;cup&eacute;ration des entr&eacute;es depuis <em>JStor</em></a></li>
      <li><a href="ScienceDirect.php">R&eacute;cup&eacute;ration des entr&eacute;es depuis <em>ScienceDirect</em></a></li>
      <li><a href="Spires.php">R&eacute;cup&eacute;ration des entr&eacute;es depuis <em>Spires</em></a></li>
      <li><a href="SQLExport.php">Exporter vers une base de donn&eacute;es SQL externe</a></li>
      <li><a href="XMPHelp.php">Support des m&eacute;tadonn&eacute;es XMP dans JabRef</a></li>
      <li><a href="CommandLine.php">Options de la ligne de commande</a></li>
      <li><a href="RemoteHelp.php">Op&eacute;rations &agrave; distance</a></li>
    </ul>

    <h2>Divers</h2>
    <ul>
      <li><a href="RevisionHistory.php">Historique des r&eacute;visions (en anglais)</a></li>
      <li><a href="About.php">About JabRef</a></li>
    </ul>

  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
