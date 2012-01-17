<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="in">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Pencarian IEEEXplore</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>

    <h1>Pencarian IEEEXplore</h1>

    <p>IEEEXplore mempunyai banyak koleksi yang berhubungan dengan
    pustaka teknik bidang teknik elektro, sain komputer,
    serta elektronika.</p>

    <p>JabRef dapat melakukan muaturun langsung dari basisdata IEEEXplore.
    Untuk menggunakan fitur ini, Pilih <b>Pencarian Web -&gt; Pencarian
    IEEEXplore</b>, kemudian dialog pencarian akan muncul di jendela
    sebelah kiri.</p>

    <p>Untuk memulai pencarian, tulislah kata yang ingin anda cari, kemudian tekan
    <b>Enter</b> atau tekan tombol <b>Mencari</b>.</p>

    <p>Pencarian dilakukan melalui mode tamu, yang berarti akan memberikan
    jumlah hasil yang dibatasi sampai 100 entri.</p>

    <p>Anda bisa memilih basisdata yang digunakan serta ada pilihan termasuk abstrak untuk setiap
    entri, dengan memilih kotak pilihan <b>Termasuk abstrak</b>. 
    Pilihan ini TIDAK AKAN menyebabkan beban pencarian menjadi bertambah.</p>

    <p>Pilihan untuk muaturun acuan BibTeX langsung dari IEEEXplore belum dapat digunakan.</p>

    <p>Silahkan hubungi Aaron Chen <b>nextAaron@gmail.com</b> apabila ada saran.</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
