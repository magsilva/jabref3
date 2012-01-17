<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ja" xml:lang="ja">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>ヘルプウィンドウ</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>ヘルプウィンドウ</h1>

    <p><em>ヘルプボタンをクリックするか、F1を押すと開きます。</em></p>

    <p>ヘルプウィンドウは、JabRefを使う上での情報を提供するためにあります。</p>

    <h2>ヘルプファイルの閲覧</h2>

    <p>ヘルプウィンドウは、実際には軽量HTMLブラウザであり、ヘルプファイルは標準的なHTMLファイルです。</p>

    <p>ヘルプウィンドウは、どのプログラムウィンドウからそれが呼ばれたのかによって、異なるファイルを既定で開きます。もしこのファイルが、あなたの関心事を説明していないときには、ツールバーの<em>目次</em>ボタンを押すと、利用できるヘルプファイルの一覧が表示されます。</p>

    <p>さらに、ツールバーには、次または前のファイルを表示するための移動ボタンがあります。これらは、標準的なウェブブラウザの<em>戻る</em>ボタンや<em>進む</em>ボタンに似たものです。<em> 戻る</em>の移動短絡キーは左矢印キーであり、<em>進む</em>の移動短絡キーは右矢印キーです。</p>

    <p>ヘルプウィンドウは、エスケープ鍵を押すと閉じます。</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
