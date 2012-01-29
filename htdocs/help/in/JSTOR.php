<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="in" xml:lang="in">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Pencarian JStor</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Kembali ke Daftar Isi</a>


  <h1>Pencarian JStor</h1>

    <p>Pencarian JStor tergantung dari proyek BibSonomy scraper
    (http://scraper.bibsonomy.org/) untuk mengambil informasi dari halaman
    web JStor.</p>

    <p>Penjelasan dibawah ini diambil dari halaman web JStor dan bisa tidak akurat
    apabila ada perubahan di JStor:</p>
    <ul>
      <li>Pengaturan bawaan adalah anda mencari nama penulis, judul, dan teks lengkap dari
          semua isi jurnal (artikel, ulasan buku, dll.)</li>
      <li>Gunakan tanda petik ganda untuk pencarian frasa 
          (misal, &quot;punctuated equilibrium&quot;)</li>
      <li>Gunakan ti: untuk mencari judul artikel,
          au: untuk mencari penulis (misal, ti:&quot;two-person cooperative games&quot;,
          au:&quot;john nash&quot;)</li>
      <li>Gunakan AND, OR, NOT untuk pencarian gabungan 
          (misal, ti:&quot;two-person cooperative games&quot; AND au:&quot;john nash&quot;)</li>
    </ul>

    <p>JStor adalah organisasi bukan-mencari-untung yang mempunyai dua misi
    yaitu untuk membuat dan memelihara arsip dari berbagai jurnal ternama, serta
    memberikan akses seluas-luasnya kepada masyarakat. JSTOR memberikan kemudahan
    bagi peneliti untuk memperoleh dokumen pindai dari berbagai jurnal dengan
    kualitas gambar yang beresolusi tinggi, seperti hasil dari cetakan aslinya.
    JSTOR memiliki arsip jurnal dari berbagai displin ilmu.</p>

    <p>JStor memberikan akses di berbagai topik:</p>
    <ul>
      <li>seni &amp; sains</li>
      <li>biologi</li>
      <li>bisnis</li>
      <li>ekologi &amp; botani</li>
      <li>kesehatan &amp; sains umum</li>
      <li>bahasa &amp; sastra</li>
      <li>matematika &amp; statistika</li>
      <li>musik</li>
    </ul>

    <p>Setiap pencarian dibatasi maksimum 200 hasil.</p>

  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
