<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>JStor 検索</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


  <h1>JStor 検索</h1>

    <p>JStor検索は、JStorウェブページから情報を抽出するにあたって、BibSonomy scraperプロジェクト&nbsp;(http://scraper.bibsonomy.org/) に依存しています。</p>

    <p>以下のヘルプは、JStorウェブサイトから転載したものであり、JStorが変更すれば変更される可能性があります。</p>
   <ul>
      <li>既定では、すべての型の学術誌の内容 (論文・書評など) について、著者・タイトル・本文全体が検索されます</li>
      <li>フレーズを検索するには、引用符を使用してください&nbsp;(例：&quot;punctuated equilibrium&quot;)</li>
      <li>論文タイトルを検索するには、ti:を、著者を検索するにはau:を使用してください&nbsp;(例：ti:&quot;two-person cooperative games&quot;, au:&quot;john nash&quot;)</li>
      <li>語句を連結するには、AND・OR・NOTを使用してください&nbsp;(例：ti:&quot;two-person cooperative games&quot; AND au:&quot;john nash&quot;)</li>
    </ul>

    <p>Jstorは、重要な学術誌の信頼できるアーカイブを作成・維持するとともに、できるかぎり広くこれらの学術誌へのアクセスを提供するという二つのミッションを持った非営利団体です。JSTORは、スキャンで取り込んだ学術誌とそのページの高解像度画像を、元々デザインされ印刷され描かれた通りの形で、研究者に提供しています。JSTORに保管されている学術誌は、多くの学術領域にわたっています。</p>

    <p>JStorは、以下のトピックへのアクセスを提供しています。</p>
    <ul>
      <li>art &amp; sciences</li>
      <li>biology</li>
      <li>business</li>
      <li>ecology &amp; botany</li>
      <li>health &amp; general sciences</li>
      <li>languages &amp; literature</li>
      <li>mathematics &amp; statistics</li>
      <li>music</li>
    </ul>

    <p>検索は、最大で200個の結果を返します。</p>

  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
