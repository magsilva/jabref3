<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="in">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Pencarian melalui Portal ACM</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Pencarian melalui Portal ACM</h1>

    <p>Portal ACM Portal mempunyai dua basisdata: Pustaka ACM Dijital yang mempunyai koleksi
    teks lengkap dari setiap publikasi artikel oleh ACM termasuk arsip yang berumur 50 tahun,
    serta Panduan untuk Computing Literature yang merupakan koleksi bibliografi dari penerbit yang
    terkenal dalam bidang komputer yang totalnya ada lebih dari satu juta entri.</p>

    <p>JabRef dapat melakukan muaturun acuan dari postal basisdata ACM.
    Untuk menggunakan fitur ini, pilih <b>Pencarian Web -&gt; Pencarian
    Portal ACM</b>, kemudian dialog pencarian akan muncul di sebelah kiri
    jendela.</p>

    <p>Untuk memulai pencarian, tulislah kata yang ingin anda cari, kemudian tekan
    <b>Enter</b> atau tekan tombol<b>Mencari</b>.</p>

    <p>Anda bisa memilih basisdata yang digunakan serta ada pilihan termasuk abstrak untuk setiap
    entri, dengan memilih kotak pilihan <b>Termasuk abstrak</b>.</p>

    <p>Untuk mengurangi beban yang terlalu berat
    pada Portal ACM, JabRef akan menolak mengambil
    entri lebih dari jumlah tertentu.
    </p>

    <p>Perlu anda ketahui, koneksi yang terlalu sering ke Portal ACM
    akan menyebabkan IP anda ditolak selama beberapa jam. Oleh karena itu JabRef
    akan menunggu 5 detik antara tiap koneksi sehingga proses muaturun akan sedikit lambat
    </p>

   <p>Silahkan hubungi Aaron Chen <b>nextAaron@gmail.com</b> apabila ada saran.</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
