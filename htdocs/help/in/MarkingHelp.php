<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="in">
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Menandai entri</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>
	
    <h1>Menandai entri</h1>

    <p>Anda mempunyai pilihan bagaimana <em>menandai</em>
    entri. Pintasan tombol ketik CTRL-M untuk menandai entri,
    sedangkan CTRL-SHIFT-M untuk melepaskan tanda entri. Anda juga
    bisa melakukan pada menu.</p>

    <p>Penandaan tidak sama dengan memilih entri.
    Entri yang ditandai mempunyai perilaku berbeda dengan yang tidak ditandai
    dalam dua hal. Yang pertama, jika entri sudah ditandai akan selalu
    mempunyai warna latar tabel entri berbeda. Yang kedua, entri yang 
    sudah ditandai selalu berada di lapis paling atas pada senarai entri,
    apabila tidak anda urutkan menurut grup atau setelah pencarian kata.</p>

    <p>Warna penandaan entri tergantung dari tingkat entri, dengan 
    urutan warna mulai dari kuning sampai merah. Ada menu untuk pengaturan
    warna di tiap tingkat entri, serta untuk pengaturan warna untuk tingkat
    entri tertentu.</p>

    <p>Penandaan entri sangat berguna misalnya jika anda ingin melakukan
    pencarian tanpa ingin kehilangan hasil pencarian sebelumnya, atau
    contoh lainnya, anda ingin mengingat atau menandai artikel atau buku
    yang penting dan perlu ditambah/diperbaiki datanya.</p>
    
    <p>Sebagai tambahan dari penandaan entri secara manual, JabRef dapat 
    melakukan penandaan otomatis pada entri yang diimpor yang terakhir sekaligus
    melepaskan tanda entri yang dibuat sebelumnya. Penandaan entri otomatis ini
    akan mempunyai warna yang berbeda dengan warna hasil penandaan secara manual.
    Warna penandaan otomatis adalah hijau. Fitur ini bisa diatur dari menu
    <b>Pengaturan -> Preferensi -> Umum</b></p>

    <p>Penandaan entri berhubungan dengan nama pengguna anda. Nama yang digunakan
    dalam bidang "owner" dari entri yang anda buat atau anda import adalah nama
    pengguna anda untuk sistem operasi, namun demikian anda bisa merubahnya
    melalui menu <b>Pengaturan -> Preferensi -> Umum</b>). Pengguna lain
    dari berkas basisdata dapat menandai secara independen. Jika anda menyimpan
    berkas basisdata, penandaan yang anda lakukan juga akan disimpan.</p>


  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
