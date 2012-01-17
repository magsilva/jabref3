<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>自動保存</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>

    <h1>自動保存</h1>

    <p>自動保存機能は、お使いのコンピューターやJabRefがクラッシュした場合に、データが失われることを回避するのに役立ちます。自動保存が有効になっている場合には、JabRefは、最後に保存されてからお使いのデータベースが変更されたかどうかを、定期的に(間隔は設定可能)点検します。各データベースについて、JabRefはそのコピーを<code>.$[ファイル]$</code>という名称のファイルに保存します。ここで<code>[ファイル]</code>は該当するデータベースのファイル名です。自動保存ファイルは、bibファイルと同じディレクトリに作られます。</p>
    <p>自動保存ファイルは、能動的にデータベースを保存したときやJabRefを正常に終了したときには削除されます。しかしながら、JabRefがクラッシュによって終了した場合には、自動保存ファイルは残されたままになります。この場合には、次にデータベースを開こうとした際に自動保存ファイルが検出され、データベースを自動保存ファイルから回復するかどうか尋ねられます。</p>
    <p>自動保存は、既定では有効にされており、5分ごとに保存されます。望むならば、自動保存を使用する前に尋ねるオプションを無効にすることができます。この場合には、JabRefは、通知することなく静かにデータベースを回復します。</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
