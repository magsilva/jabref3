<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ja" xml:lang="ja">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>ヘルプ目次</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">目次に戻る</a>


    <h1>ヘルプ目次</h1>

    <h2>一般</h2>
    <ul>
      <li><a href="HelpHelp.php">ヘルプウィンドウについて</a></li>
      <li><a href="JabRefHelp.php">一般的な情報</a></li>
      <li><a href="BaseFrameHelp.php">JabRef 基本ウィンドウ</a></li>
      <li><a href="EntryEditorHelp.php">項目エディタ</a></li>
      <li><a href="Autosave.php">自動保存</a></li>
    </ul>

    <h2>フィールド</h2>
    <ul>
      <li><a href="BibtexHelp.php"><em>Bibtex</em>ヘルプ</a></li>
      <li><a href="FileLinks.php">外部ファイルリンク</a></li>
      <li><a href="ExternalFiles.php">JabRef におけるPDF/PS/URL/DOIリンク</a></li>
      <li><a href="TimeStampHelp.php">項目の時間スタンプ</a></li>
      <li><a href="OwnerHelp.php">「owner」フィールド</a></li>
      <li><a href="ContentSelectorHelp.php">フィールド内容選択メニュー</a></li>
      <li><a href="JournalAbbreviations.php">学術誌名の短縮形</a></li>
    </ul>
    
    <h2>項目の検索と整序</h2>
    <ul>
      <li><a href="GroupsHelp.php">Help on using <em>Groups</em></a></li>
      <li><a href="MarkingHelp.php">Marking entries</a></li>
      <li><a href="SearchHelp.php">Searching in JabRef</a></li>
    </ul>

    <h2>設定</h2>
    <ul>
      <li><a href="StringEditorHelp.php">文字列エディタ</a></li>
      <li><a href="DatabaseProperties.php">Database properties window</a></li>
      <li><a href="PreviewHelp.php">Entry preview setup</a></li>
      <li><a href="LabelPatterns.php">Customizing the BibTex key generator</a></li>
      <li><a href="CustomEntriesHelp.php">Customizing entry types</a></li>
      <li><a href="GeneralFields.php">Customizing general fields</a></li>
      <li><a href="Plugin.php">プラグインを使用してJabRefを拡張する</a></li>
    </ul>

    <h2>読み込み/書き出し</h2>
    <ul>
      <li><a href="CustomExports.php">Custom export filters</a></li>
      <li><a href="CustomImports.php">Custom import filters</a></li>
      <li><a href="ImportInspectionDialog.php">Import inspection window</a></li>
      <li><a href="EndNoteFilters.php">EndNote読み込み/書き出しフィルタセット</a></li>
      <li><a href="OpenOfficeHelp.php">OpenOffice.orgでJabRef書誌情報を利用するには</a></li>
      <li><a href="ACMPortalHelp.php"><em>ACM</em> Portalから項目を取得する</a></li>
      <li><a href="CiteSeerHelp.php"><em>CiteSeer</em>から項目を取得する</a></li>
      <li><a href="IEEEXploreHelp.php"><em>IEEExplore</em>から項目を取得する</a></li>
      <li><a href="MedlineHelp.php">Fetching entries from <em>Medline</em></a></li>
      <li><a href="JSTOR.php"><em>JStor</em> 検索</a></li>
      <li><a href="ScienceDirect.php"><em>ScienceDirect</em> 検索</a></li>
      <li><a href="Spires.php"><em>Spires</em> 検索</a></li>
      <li><a href="SQLExport.php">Export to an External SQL Database</a></li>
      <li><a href="XMPHelp.php">XMP metadata support in JabRef</a></li>
      <li><a href="CommandLine.php">Command line options</a></li>
      <li><a href="RemoteHelp.php">Remote operations</a></li>
    </ul>

    <h2>その他</h2>
    <ul>
      <li><a href="RevisionHistory.php">更新履歴</a></li>
      <li><a href="About.php">JabRefについて</a></li>
    </ul>

  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
