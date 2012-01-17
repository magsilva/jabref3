<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja">
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>JabRef</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>
	
    <h1>JabRef</h1>

    <p><strong>《註》</strong><em>bibtex</em>プログラムは、各ヘルプファイル中で繰り返し言及されています。<em>Bibtex</em>は、Oren Patashniによって作られた、LaTeX文書中の書誌参照を生成するためのユーティリティです。同じデータベース形式が、Adobe FrameMaker中の書誌情報生成ユーティリティであるCiteMakerでも使用されています。</p>

    <p>JabRefは、<em>bibtex</em>データベースを操作するためのプログラムです。本プログラムは、内部ファイル形式は使用しておらず、通常はデータベースを直接<em>bibtex</em> .bib形式で読み込んだり保存したりします。一方で、様々な形式の書誌データベースをJabRefに読み込むこともできます。</p>

    <p>JabRefは、使用しているデータベース内容の全体像を得られるように、整序機能や検索機能を備えています。また、どの情報が必須だったか暗記しておくことなく、簡単に新しいデータベース項目を追加することができますし、<em>bibtex</em>キーを自動生成させることもできます。JabRefは、<em>bibtex</em>やCiteMakerを利用している方々に最適ですが、その他の参照システムを使用している方や、単に文献ソースを整理したいという方にも便利です。</p>

    <p><a href="BaseFrameHelp.php">JabRef 基本ウィンドウ</a></p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
