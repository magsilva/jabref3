<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="in" xml:lang="in">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Mengambil entri dari Medline</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>

    <h1>Mengambil entri dari Medline</h1>

    <p>MEDLINE adalah basisdata utama kedokteran di U.S. National Library.
    Basisdata ini berisi referensi dari artikel jurnal yang berkaitan dengan
    sains kehidupan dengan konsentrasi pada biomedicine.</p>

    <p>JabRef dapat melaukan muaturun dari basisdata Medline.
    Untuk menggunakan fitur ini, pilih <b>Pencarian Web -&gt; Pencarian Medline</b>,
    kemudaian dialog pencarian Medline akan muncul di jendela sebelah kiri.</p>

    <p>Ada dua cara untuk memilih entri yang akan dimuaturun:</p>

    <ol>
        <li>Tulis satu atau lebih dari Medline ID (dipisah dengan
        koma/titik koma) di kotak pencarian.</li>

        <li>Tulis nama atau kata yang dicari. Anda
        bisa menggunakan kata <em>and</em> dan <em>or</em> serta
        tanda kurung untuk memperhalus ekspresi pencarian.</li>
    </ol>Setelah ini, tekan <b>Enter</b> atau tekan tombol <b>Mengambil</b>.
    Apabila anda menggunakan pencarian teks, anda akan diberikan informasi
    jumlah entri yang ditemukan, dan anda bisa menentukan berapa jumlah yang
    akan dimuaturun.

    <p>Entri yang diambil kemudian akan ditambahkan dalam basisdata anda yang aktif.</p>

    <h2>Menggunakan Proxy Server</h2>

    <p>Apabila anda ingin menggunakan http proxy
    server, anda harus memasukkan nama server name serta nomor port ke java saat
    menjalankan.</p>

    <p><code>java -Dhttp.proxyHost="namakomputer"
    -Dhttp.proxyPort="nomorport"</code></p>

    <p>Pengaturan lingkungan ini didokumentasikan di 
    <a href="http://docs.oracle.com/javase/1.4.2/docs/guide/net/properties.html">Sun J2SE documentation</a>.</p>

  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>