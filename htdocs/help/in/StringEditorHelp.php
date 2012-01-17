<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="in" xml:lang="in">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Penyunting string </title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Penyunting string </h1>

    <p><em>Dibuka dari menu utama
    <b>BibTeX -> Sunting entri</b> atau bisa juga dengan cara menekan tombol di kotak
    bantuan.</em></p>

    <p>Data <em>String</em> adalah kode <em>bibtex</em> yang setara
    dengan konstanta di bahasa pemrograman. Tiap-tiap string didefinisikan
    dengan <em>nama</em> dan <em>isi</em>. Dalam basisdata,
    nama dapat digunakan untuk mewakili isi.</p>

    <p>Sebagai contoh, jika ada beberapa entri dari salah satu jurnal
    yang mempunyai singkatan yang tidak mudah untuk diingat, misalnya
    'J. Theor. Biol.' (Journal of Theroretical Biology), maka nama string
    JTB dapat digunakan untuk menyatakan nama jurnal tadi. Anda tidak perlu
    menuliskan nama jurnal yang sama di setiap entri, tetapi cukup
    dengan menulis karakter '#JTB#' (tanpa tanda petik) pada bidang
    <em>journal</em>, sehingga nama jurnal akan tertulis dengan benar
    di setiap entri.</p>

    <p>Acuan string dapat digunakan dalam bidang. Acuan string harus
    ditulis diantara karakter '#'. Sintak ini adalah cara yang digunakan
    JabRef yang sedikit berbeda dengan notasi <em>bibtex</em> yang digunakan
    ketika anda menyimpan berkas basisdata. String merupakan cara bawaan
    yang digunakan untuk menulis di semua bidang BibTeX standar.
    Anda bisa juga menyatakan untuk bidang lain yang bukan standar, dari
    <b>Pengaturan -&gt; Preferensi -&gt; Berkas</b>. Dari dialog ini
    ada ada pilihan perkecualian untuk mengatasi masalah string yang perlu
    perkecualian pada bidang yang mungkin mengandung karakter '#' seperti 
    pada bidang 'url' dan bidang lain yang diinginkan agar tetap diproses
    oleh BibTeX/LaTeX.</p>

    <p>String bisa juga diacu dari string lain dengan cara yang sama
    dengan syarat string yang diacu terlebih dahulu didefinisikan
    <em>sebelum</em> string yang mengacu.</p>

    <p>Walaupun urutan string dalam berkas BibTeX adalah penting,
    anda tidak perlu kawatir ketika anda menggunakan JabRef.
    String dapat ditampilkan menurut urutan alfabet pada penyunting
    string, kemudian disimpan dengan dengan urutan yang sama, kecuali
    BibTeX memerlukan urutan tertentu yang berbeda.</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
