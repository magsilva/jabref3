<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ja" xml:lang="ja">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>OpenOffice.org で JabRef 書誌情報を利用するには</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>OpenOffice.org で JabRef 書誌情報を利用するには</h1>

    <p>JabRef は、データベースを OpenOffice.org 1.1 の <b>.sxc</b> 表計算ドキュメント形式と、 OpenOffice.org 2.0 で用いられる OpenDocument <b>.ods</b> 表計算ドキュメント形式の両方に書き出すことができます。</p>

    <p>どちらの場合も、書き出した表計算ドキュメントには、各項目を行とし、各フィールドを列とした1つのデータシートだけが含まれます。列順と列名は、OpenOffice.org の書誌情報関数と互換性があるように設定されます (<b>.sxc</b> 形式の場合は OOo 1.1、<b>.ods</b> 形式の場合は OOo 2.0)。</p>

    以下は、OpenOffice.org のバージョン毎の、JabRef 書誌情報データベースの利用法です。

    <h2>書き出したファイルを OpenOffice.org 2.3 および 2.4 の書誌情報データベースとして利用するには</h2>

    <p>JabRef から書き出したスプレッドシートを OpenOffice.org の書誌情報データベースとして設定するには、以下のステップを踏んでください。</p>

    <ul>
    <li>JabRef からデータベースを <b>.ods</b> 形式で書き出します。</li>
    <li>OpenOffice.org Writer を起動します。</li>

    <li><b>編集→データベースを交換</b>を選ぶ。<b>選択</b>をクリックし、.ods 形式に書き出されたデータベースを選択します。</li>
    <li>読み込んだデータベース名の前にある <b>+</b> を展開して、表示されたファイル名をクリックし、最後に<b>指定</b>ボタンを押します。</li>
    <li><b>ツール→オプション→OpenOffice.org Base→データベース</b>を選択します。このウィンドウには、いま読み込んだデータベースが表示されているはずです。また、既定のOOo書誌情報データベース (<i>Bibliography</i>) も表示されているはずです。</li>
    <li>Bibliography データベースを編集し、その名前を <i>Bibliography-old</i> などに変えてください (OpenOffice.org Writer では複数の書誌情報データベースを選択することができません)。</li>
    <li>使用する書誌情報データベースを選択し、その名前を <i>Bibliography</i> に変えてください (名前の最初の文字が大文字になっていることに特に注意してください)。</li>
    </ul>

    これらのステップを踏むと、あなたの書誌情報データベースが OpenOffice.org で利用できるようになっているはずです。これを確認するには、<b>挿入→目次と索引→参考文献項目...</b>を選択してください。すると、BibTeX 鍵のリストが表示されるはずです。


    <h2>書き出したファイルを OpenOffice.org 2.0/2.1/2.2 の書誌情報データベースとして利用するには</h2>

    <p>JabRef から書き出したスプレッドシートを OpenOffice.org の書誌情報データベースとして設定するには、以下のステップを踏んでください。</p>

    <ul>
        <li>データベースを <b>.ods</b> 形式で書き出します。</li>

        <li>OpenOffice.org を起動します。</li>

        <li><b>ツール→オプション→OpenOffice.org Base→データベース</b>を選択します。</li>

        <li><i>Bibliography</i> データベースを編集し、その名前を <i>Bibliography-old</i> などに変えてください (OpenOffice.org Writer では複数の書誌情報データベースを選択することができません)。</li>

        <li><b>オプション</b>ウィンドウを閉じて、<b>ファイル→新規作成→データベース</b>を選びます。</li>

        <li><b>既存のデータベースに接続</b>を選び、データベース型として<b>表計算ドキュメント</b>を選択して、書き出した <b>.ods</b> ファイルを選びます。</li>

        <li><b>完了</b>をクリックして、選択を促されたら <i>Bibliography</i> を選んでください。</li>
    </ul>これらのステップを終えた後に<b>ツール→参考文献データベース</b>を選んでください。すると、あなたのデータベースｗが表示されているはずです。

    <h2>書き出したファイルを OpenOffice.org 1.1.x の書誌情報データベースとして利用するには</h2>

    <ul>
        <li>データベースを <b>.sxc</b> 形式で書き出します。</li>

        <li>OpenOffice.org を起動する</li>

        <li><b>ツール→データソース</b>を選択します。</li>

        <li><i>Bibliography</i> データベースを編集し、その名前を <i>Bibliography-old</i> などに変え、<b>適用</b>をクリックしてください。</li>

	<li><b>新規データソース</b>をクリックすると、新しい項目が表示されるので、その名前を <i>Bibliography</i> に変更してください。</li>

        <li><b>データベース型</b>を<b>表計算ドキュメント</b>に変更し、<b>データソースURL</b>行にある<b>...</b>ボタンをクリックしてください。そして書き出した <b>.sxc</b> ファイルを選択してください。</li>

        <li><b>OK</b> をクリックして<b>データソース</b>ウィンドウを閉じてください。</li>
    </ul>これらのステップを終えた後に<b>ツール→参考文献データベース</b>を選んでください。すると、あなたのデータベースが表示されているはずです。
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>