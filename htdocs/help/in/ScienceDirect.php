<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="in">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Pencarian ScienceDirect</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


  <h1>Pencarian ScienceDirect</h1>

    <p>Pencarian ScienceDirect tergantung pada proyek BibSonomy (http://scraper.bibsonomy.org/)
    untuk mengambil informasi dari halaman web ScienceDirect.</p>

    <p>Pengambil data dari situs web ScienceDirect, akan menghasilkan
        100 data. Semuanya kemudian bisa diimpor masuk ke JabRef.</p>

  <?php include("../../footer.php"); ?>
  </div>

</body>
</html>
