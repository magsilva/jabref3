<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="in" xml:lang="in">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Bidang 'pemilik'</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Bidang 'pemilik'</h1>

    <p>JabRef dapat memberikan tanda pada semua entri basisdata
    yang ditambahkan atau yang diimpor dengan nama pengguna anda.
    And juga dapat mengatur aktif tidaknya fitur ini dari 
    <b>Preferensi -&gt; Umum</b>, serta merubah nama pemilik yang ingin
    anda gunakan untuk menandai semua entri. Nama pemilik bawaan adalah
    nama pengguna anda.</p>

    <p>Nama pemilik dituliskan dalam bidang bernama 'pemilik',
    yang pada pengaturan bawaan akan muncul dalam tab <b>Umum</b>
    pada Penyunting Entri.</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
