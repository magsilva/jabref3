<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="in">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Jendela propereti basisdata</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>

    <h1>Jendela propereti basisdata</h1>

     <p><em>Dibuka dari meu utama <b>Berkas -> Properti basisdata</b></em></p>

     Jendela properti basisdata membantu anda untuk mengatur beberapa hal yang terkait
     dengan basisdata.

     <h2>Enkoding basisdata</h2>

     <p>Pengaturan ini menentukan enkoding karakter yang akan digunakan JabRef
     ketika menulis berkas basisdata ke cakra. Perubahan ini akan menindih pengaturan
     yang dilakukan melalui dialog preferensi untuk basisdata. JabRef menuliskan enkoding
     di bagian atas dari berkas bib, agar ketika anda membuka berkas akan terlebih dahulu
     mengenal enkoding karakter yang digunakan.</p>

     <h2>Mengganti direktori berkas</h2>

     <p>Pengaturan ini digunakan untuk menyatakan lokasi yang digunakan untuk tautan berkas umum
     (yang dinyatakan dalam bidang <em>berkas</em>), serta untuk legasi tautan PDF/PS
     (bidang <em>pdf</em> dan <em>ps</em> digunaan di JabRef sebelum versi 2.3, 
     tetapi di versi selanjutnya sampai sekarang diganti dengan tautan berkas umum).</p>

     <p>Lokasi relatif dapat digunakan juga. Lokasi relatif ini maksudnya berkas dapat dinyatakan
     relatif terhadap berkas bib. Cara mengaturnya adalah dengan menggunakan "." 
     (tanpa tanda petik) yang berarti berkas berada dalam direktori yang sama dengan
     lokasi berkas bib.</p>

     <p>Pengaturan ini menindih pengaturan direktori umum melalui dialog Preferensi. 
     Apabila tidak ada kotak isian dibiarkan kosong, direktori umum akan digunakan.</p>

     <h2>Proteksi basisdata</h2>

     <p>Pengaktifan pilihan ini akan memaksa memeriksa perubahan yang dilakukan secara eksternal
     pada berkas basisdata sebelum menyimpan. Jika tidak diaktifkan, pengguna mempunyai bisa
     menyimpan berkas walaupun pengguna lain telah melakukan perubahan data dalam basisdata, tanpa
     melakukan pemeriksaan perubahan - serta ada peringatan tentang adanya perubahan.
     Jika pilihan proteksi ini aktif, pengguna hanya bisa menyimpan berkas setelah 
     perubahan eksternal diperiksa (pengguna bisa mengabaikan perubahan individual 
     ketika melakukan pemeriksaan).</p>

     <p><b>Catatan:</b> fitur ini bukan berhubungan dengan keamanan, namun hanya untuk 
     menghindari penindihan perubahan yang sudah dilakukan oleh pengguna lain.
     Fitur ini tidak akan mengenali perubahan pengguna yang tidak sah.
  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
