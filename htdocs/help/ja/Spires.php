<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ja" xml:lang="ja">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Spires 検索</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">目次に戻る</a>


    <h1>Spires 検索</h1>

    <p>Spires検索は、検索クエリをSpiresウェブサーチにそのまま渡しますので、<em>find</em>あるいは<em>fin</em>コマンドを省く点以外は、そのままクエリを構成してください。このヘルプページは、検索クエリへの簡単な導入を行うだけですので、Spires検索の詳細なヘルプについては、http://www.slac.stanford.edu/spires/hep/help/index.shtmlを参照してください。</p>

    <p>論理演算子として<em>and</em>および<em>or</em>を使用して結合することで、複数の部分を持つクエリを構成することができます。各部分は、検索するフィールド型を示す文字または単語の後に空白を置き、その後に検索する文字列を置くことで構成されます。</p>

    <p>以下の一覧は、使用することができるフィールド指示子の一部を示したものです。</p>
    <ul>
        <li><em>a</em>または<em>author</em>: 著者名を検索</li>
        <li><em>t</em>または<em>title</em>: タイトルを検索</li>
        <li><em>j</em>: 学術誌名。ここでは、当該学術誌のよく使われる短縮形か5文字CODEN短縮形を使用することができます。巻号とページもコンマ区切りで含めることができます。例えば、<em>j Phys. Rev.,D54,1</em>とすると、Phys. Rev., volume D54, page 1を検索します。</li>
        <li><em>k</em>: キーワードを検索</li>
    </ul>

    <p>クエリの例:</p>
    <ul>
        <li><em>a smith and a jones</em>: 著者に"smith"と"jones"が含まれる文献を検索</li>
        <li><em>a smith or a jones</em>: 著者が"smith"か"jones"であるような文献を検索</li>
        <li><em>a smith and not t processor</em>: 著者"smith"を検索するが、タイトルに"processor"があるものを省略</li>
    </ul>

  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
