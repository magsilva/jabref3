<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ja" xml:lang="ja">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>ScienceDirect 検索</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


  <h1>ScienceDirect 検索</h1>

    <p>ScienceDirect検索は、ScienceDirectウェブページから情報を抽出するにあたって、BibSonomy scraperプロジェクト&nbsp;(http://scraper.bibsonomy.org/) に依存しています。</p>

    <p>この取得子は、ScienceDirectウェブサイトのクイックサーチを実行し、最大100ヒットまで表示します。そして全ヒット分が取り込まれます。</p>

  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
