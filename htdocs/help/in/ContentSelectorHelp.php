<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="in">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Pengisian kata dalam bidang</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Pengisian kata dalam bidang</h1>

    <p>Fitur ini membantu anda untuk menyimpan
    kata-kata atau teminologi yang sering anda gunakan dalam
    pengisian basisdata anda. Bidang yang memiliki fitur ini
    adalah <em>Journal</em>, <em>Author</em>, <em>Keywords</em> and
    <em>Publisher</em>. Anda bisa menambah sendiri bidang lain yang
    anda inginkan. Dalam dialog <em>Pengaturan pengisian kata</em> (menu
    <strong>AlatBantuan --&gt; Pengaturan pengisian kata</strong>).</p>

    <p>Pemilihan kata adalah spesifik-basisdata. Kata-kata 
    disimpan bersama dengan koleksi referensi anda dalam berkas
    .bib anda</p>

    <p>Untuk menambahkan kata, anda cukup menuliskan pada kotak
    sebelah kanan kemudian tekan Enter. Dalam dialog
    <em>Pengaturan pengisian kata</em> ini anda juga
    bisa menghapus kata-kata yang anda tambahkan.</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
