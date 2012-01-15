<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="in">
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Cap waktu entri</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>
	

    <h1>Cap waktu entri</h1>

    <p>Fitur ini bisa dipilih dan diatur melalui menu
    <b>Pengaturan -&gt;
    Preferensi -&gt; Umum</b>.</p>

    <p>JabRef dapat menuliskan tanggal entri ditambahkan
    dalam basisdata.</p>

    <h2>Format</h2>

    <p>Format cap waktu dibuat dengan string yang mengandung
    tanda waktu yang memberikan informasi tentang tanggal
    dan waktu.</p>

    <p>Dibawah ini adalah daftar kode huruf (hasil penulisan
    dituliskan dalam tanda kurung) untuk menuliskan berbagai
    parameter tanggal:</p>

    <ul>
        <li><b>yy</b>: tahun (05)</li>

        <li><b>yyyy</b>: tahun (2005)</li>

        <li><b>MM</b>: bulan (09)</li>

        <li><b>dd</b>: tanggal pada bulan (14)</li>

        <li><b>HH</b>: jam pada hari (17)</li>

        <li><b>mm</b>: menit pada jam (45)</li>
    </ul>Kode waktu bisa digabungkan dengan tanda lainnya juga dengan spasi
	 kosong. Beberapa contoh penggabungan:
    <ul>
        <li><b>yyyy.MM.dd</b> memberikan hasil <b>2005.09.14</b></li>

        <li><b>yy.MM.dd</b> memberikan hasil <b>05.09.14</b></li>

        <li><b>yyyy.MM.dd HH:mm</b> memberikan hasil <b>2005.09.14
        17:45</b></li>
    </ul>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
