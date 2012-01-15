<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja">
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>フィールド内容選択メニュー</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>
	

    <h1>フィールド内容選択メニュー</h1>

    <p>この機能は、使用しているデータベースでしばしば用いられる頻出語や頻出句を選んで保存することができるようにするものです。この機能は、既定では<em>Journal</em>・<em>Author</em>・<em>Keywords</em>・<em>Publisher</em>の各フィールドに対して有効になっていますが、<em>選択メニューを設定</em>ダイアログ(<strong>ツール→内容選択メニューを管理</strong>メニュー)の上部で、他のフィールドにも内容選択メニューを追加することができます。</p>

    <p>設定した単語は、データベース毎に固有のものであり、文献データとともに.bibファイルに保存されます。</p>

    <p>単語を新しく付け加えるには、<em>選択メニューを設定</em>ダイアログのキーワード欄に書き込んでEnter鍵を押してください。削除ボタンを使えば、単語を削除することができます。</p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
