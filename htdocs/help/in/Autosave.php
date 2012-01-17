<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="in" xml:lang="in">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Menyimpan otomatis</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>

    <h1>Menyimpan otomatis</h1>

    <p>Fitur otomatis sangat membantu anda agar tidak kehilangan data jika tiba-tiba komputer
    mati. Ketika menyimpan otomatis diaktifkan, JabRef akan menyimpan secara teratur (interval
    waktu penyimpanan dapat diatur) dengan terlebih dahulu melihat apakah basisdata ada
    perubahan sejak penyimpanan terakhir. Jika ditemukan adanya perubahan, JabRef akan
    menyimpan salinan basisdata dengan nama  <code>.$[berkas]$</code>, dimana 
    <code>[berkas]</code> adalah nama berkas basisdata yang diperiksa. Berkas hasil 
    penyimpanan otomatis berada pada lokasi yang sama dengan berkas bib.</p>

    <p>Berkas penyimpanan otomatis akan dihapus ketika anda menyimpan sendiri basisdata anda,
    kemudian anda keluar JabRef secara normal. Namun demikian, jika JabRef ditutup karena
    komputer mati sendiri, maka berkas simpanan otomatis tetap ada. Pada kasus ini, berkas
    darurat ini akan dideteksi ketika anda membuka basisdata, sehingga anda akan diberikan
    pilihan untuk memanggil kembali berkas hasil penyimpanan otomatis.</p>

    <p>Penyimpanan otomatis merupakan fitur bawaan, dengan interval setiap 5 menit.
    Jika anda menginginkan, anda dapat mematikan dialog yang muncul menanyakan untuk
    melakukan penyimpanan otomatis. Pada kasus ini, JabRef akan memanggil berkas hasil
    simpanan otomatis secara diam-diam tanpa menanyakan pemberitahuan pada anda.</p>
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
