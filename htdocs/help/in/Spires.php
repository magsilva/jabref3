<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="in">
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Pencarian Spires</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>
	

    <h1>Pencarian Spires</h1>

    <p>Fungsi pencarian Spires sebenarnya meneruskan kata carian anda ke web pencarian Spires,
    sehingga anda juga harus mengikuti cara yang digunakan si situs Spires, kecuali anda tidak
    perlu menggunakan perintah <em>find</em> atau <em>fin</em>. Halamn bantuan ini memberikan
    pengantar ringkas untuk menulis perintah pencariannya. Bantuan yang lebih terperinci
    dapat ditemukan pada halaman web http://www.slac.stanford.edu/spires/hep/help/index.shtml.</p>

    <p>Pencarian anda dapat terdiri dari beberapa bagian, menggunakan <em>and</em> dan
    <em>or</em> sebagai operator logika. Setiap bagian dapat berupa huruf atau kata yang
    menyatakan tipe bidang pencarian, diikuti spasi kemudian teks yang dicari.</p>

    <p>Berikut ini adalah indikator bidang yang dapat digunakan:</p>
    <ul>
        <li><em>a</em> atau <em>author</em>: mencari nama penulis</li>
        <li><em>t</em> atau <em>title</em>: mencari judul</li>
        <li><em>j</em>: mencari jurnal. 
        Disini anda bisa menggunakan singkatan yang umum dari 5 huruf singkatan berdasarkan
        CODEN. Volume dan halaman boleh juga dituliskan, dipisah dengan koma. Contohnya,
        <em>j Phys. Rev.,D54,1</em> mencari jurnal Phys. Rev., volume D54, halaman 1.</li>
        <li><em>k</em>: mencari katakunci</li>
    </ul>

    <p>Contoh pencarian:</p>
    <ul>
        <li><em>a smith and a jones</em>: mencari referensi dari penulis "smith" dan "jones"</li>
        <li><em>a smith or a jones</em>: mencari referensi dari penulis "smith" atau "jones"</li>
        <li><em>a smith and not t processor</em>: mencari penulis "smith" tetapi yang tidak 
        mempunyai kata "processor" dalam judulnya</li>
    </ul>

  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
