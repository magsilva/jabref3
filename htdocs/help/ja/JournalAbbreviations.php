<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ja">
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>学術誌名の短縮形</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>
	
    <h1>学術誌名の短縮形</h1>

    <p>この機能は、<b>オプション→誌名短縮形を管理</b>で設定することができます。</p>

    <p>JabRefでは、学術誌名が誌名表に登録されていれば、短縮形と非短縮形を切り替えることができます。誌名表は複数持つことができ、これらはハードディスク上の外部テキストファイルとして取り扱われます。</p>

    <h2>この機能を使うには</h2>

    <p>誌名変換機能は、項目エディタ内か<b>ツール</b>メニューから使用することができます。項目エディタ内には、<i>journal</i>フィールド脇に<b>短縮形/非短縮形の切替</b>というラベルの付いたボタンがあります。これを押すと、現在の誌名が、以下の3つのモードの間で切り替わります。</p>

    <ul>
        <li>完全名 (例) "Aquacultural Engineering"</li>

        <li>ISO式短縮形 (例) "Aquacult. Eng."</li>

        <li>MEDLINE式短縮形 (例) "Aquacult Eng"</li>
    </ul>現在の誌名が誌名表にない場合には、フィールドは変更されません。

    <p>多くの項目の誌名を一括して変換するには、好きな数だけ項目を選択してから、<b>ツール→学術誌名を短縮形に (ISO)</b>、<b>ツール→学術誌名を短縮形に (MEDLINE)</b>、または<b>ツール→学術誌名を非短縮形に</b>のいずれかを選択します。これら3つのアクションは、選択項目のうち、誌名表に掲載されているすべての誌名を、略語化したり解除したりします。</p>

    <h2>誌名表を準備する</h2>

    <p>誌名表は、JabRefからリンクした外部テキストファイルの形で、複数持つことができます。そのうち優先表は、JabRef内から編集することができます。</p>

    <h3>個人用の誌名短縮表</h3>

    <p>個人用の誌名表は、<b>誌名短縮形の管理</b>ウィンドウの上部で取り扱うことができます。個人用誌名表を作成するには、<b>新規ファイル</b>を選択して、手動でファイル名を入力するか<b>一覧</b>ボタンを使用してください。手始めとなるファイルが既にある場合には、<b>既存ファイル</b>を選択して、<b>一覧</b>ボタンからファイルを選択してください。画面の表が更新されて、選択した表の内容が表示されます。</p>

    <p>この表と右側のツールボタンを用いて、学術誌項目を追加したり削除したり編集したりすることができます。それぞれの項目については、学術誌の完全な名称とISO式短縮形を指定しなくてはなりません(例えば"Aquacultural Engineering"と"Aquacult. Eng.")。項目を編集するには、表中の行をダブルクリックしてください。</p>

    <p>ファイルが選択されていて、表に1項目以上が含まれていれば、<b>OK</b>をクリックすると、表の内容が選択したファイルに保存され、JabRefの誌名表が更新されます。</p>

    <h3>外部誌名表</h3>

    <p>個人用誌名表の他に、複数の外部の誌名表にリンクすることができます。これらのリンクは、<b>誌名短縮形の管理</b>ウィンドウの下部で設定することができます。外部誌名表は、個人用誌名表と類似しています。唯一の違いは、JabRefには、外部誌名表を編集するインタフェースが備わっていないことです。</p>

    <p>必要に応じて外部誌名表を新たに追加するには、<b>＋</b>ボタンをクリックして、インタフェースにファイル行を追加してください。その上で、ウィンドウ下部のファイル行横にある<b>一覧</b>か<b>ダウンロード</b>ボタンを使用してください。</p>

    <ul>
        <li><b>一覧</b>ボタンを使うと、コンピューター上の既存のファイルを選択することができます。</li>

        <li><b>ダウンロード</b>ボタンを使うと、URLを入力してインターネットから誌名表をダウンロードし、コンピューター上のローカルファイルとして保存して、JabRefから誌名表としてリンクすることができます。URLは、既定でJabRefのウェブページにある誌名表のアドレスになっています。この誌名表は完全ではありませんが、将来的に改善されていくでしょう。</li>
    </ul>個人用誌名表にある項目は、外部誌名表のいずれかに存在する、同じ完全名の項目に優先します。同様に、外部誌名表間の優先順位は、ウィンドウに挙げられている順で優先されます。
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
