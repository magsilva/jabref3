<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ja" xml:lang="ja">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>文字列エディタ</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>文字列エディタ</h1>

    <p><em>これは、基本ウィンドウから<b>BibTeX→文字列を編集</b>かツールバーボタンを押すことで開きます。</em></p>

    <p><em>文字列</em>は、<em>bibtex</em>においてプログラミング言語の定数と同じ役割を果たします。各文字列は、重複のない<em>名称</em>と<em>内容</em>とで定義されます。この名称は、データベースの他の場所で内容を表すものとして使用することができます。</p>

    <p>例えば、多くの項目が「J. Theor. Biol.」(Journal of Theroretical Biology)のように、短縮形を覚えるのが難しい学術誌からのものである場合、この学術誌の名称を表すものとしてJTBという文字列を定義することができます。各項目ごとに正確な学術誌名を繰り返す代わりに、それぞれの<em>journal</em>フィールドに「#JTB#」という文字を入れれば、毎回正確に同じ学術誌名が書き込まれるようにすることができます。</p>

    <p>文字列への参照はフィールド中どこでも使用することができ、文字列の名称を必ず「#」文字の対で囲みます。この文法はJabRef特有のものであり、データベースを保存したときに生成される<em>bibtex</em>のものとは若干異なります。文字列は、既定で標準のBibTeXフィールドすべてで使用することができ、<b>設定→一般→ファイル</b>で非標準のフィールドでも文字列を有効にすることができます。後者の場合は、文字列解決から除外するフィールドを指定することができ、ここには「url」フィールドや、その他「#」文字を入れる必要がありBibTeX/LaTeXで処理される可能性のあるフィールドを含めておくことをお勧めします。</p>

    <p>文字列は、参照される文字列が参照する文字列の<em>前に</em>定義されている限り、他の文字列の内容を同じように参照することができます。</p>

    <p>BibTeXファイル中の文字列の順序は場合により重要ですが、JabRefを使用している場合には、その心配をする必要はありません。文字列エディタでは、文字列はアルファベット順に表示され、同じ順序で保存されますが、BibTeXが異なる順序を要求する場合には調整されます。</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>