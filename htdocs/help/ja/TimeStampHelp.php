<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>項目の時間スタンプ</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>項目の時間スタンプ</h1>

    <p>この機能は、<b>オプション→設定→一般</b>で入切したり設定したりすることができます。</p>

    <p>JabRefでは、データベースに項目が加えられた日付を、フィールドに自動的に入れるようにすることができます。</p>

    <h2>整形</h2>

    <p>時間スタンプの整形は、日付の各部分の位置を示す指示文字を含む文字列を用いて指定します。</p>

    <p>以下は、使用できる指示文字の一部です(括弧中の部分は2005年9月14日水曜日午後5時45分の場合の表示例を示します)。</p>

    <ul>
        <li><b>yy</b>: 西暦 (05)</li>

        <li><b>yyyy</b>: 西暦 (2005)</li>

        <li><b>MM</b>: 月 (09)</li>

        <li><b>dd</b>: 日 (14)</li>

        <li><b>HH</b>: 時 (17)</li>

        <li><b>mm</b>: 分 (45)</li>
    </ul>

    <p>これらの指示子は区切り文字や空白とともに使用することができます。以下に例を挙げます。</p>

    <ul>
        <li><b>yyyy/MM/dd</b> は <b>2005/09/14</b> となります。</li>

        <li><b>yy.MM.dd</b> は <b>05.09.14</b> となります。</li>

        <li><b>yyyy/MM/dd HH:mm</b> は <b>2005/09/14 17:45</b> となります。</li>
    </ul>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
