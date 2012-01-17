<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="in">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Pengaturan pratampilan entri</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Pengaturan pratampilan entri</h1>

    <p>Pratampilan entri menggunakan cara yang sama seperti
    yang digunakan pada fasilitas
    <a href="CustomExports.php">Penapis ekspor suaian</a>.
    Ketika melakukan pratampilan, entri diproses menggunakan
    salah satu dari dua pilihan tataletak (anda bisa merubah
    tataletak yang dipilih dengan menekan F9) untuk menghasilkan
    kode HTML yang kemudian dimunculkan di panel pratampilan.
    Untuk memodifikasi penampilan dan isi dari pratampilan, anda
    perlu menyunting tataletak pratampilan menggunakan sintak
    yang dijelaskan di berkas bantuan 
    <a href="CustomExports.php">Penapis ekspor suaian</a>.</p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
