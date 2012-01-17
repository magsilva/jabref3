<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="in">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>JabRef</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>

    <h1>JabRef</h1>

    <p><strong>Catatan:</strong> Program <em>bibtex</em> sering
    disebut pada berkas bantuan. <em>Bibtex</em> adalah suatu piranti
    yang dibuat oleh Oren Patashnik untuk membuat referensi bibliografi
    dalam dokumen LaTeX. Format basisdata yang sama juga digunakan 
    pada CiteMaker yang merupakan piranti pembuat bibliografi pada
    Adobe FrameMaker.</p>

    <p>JabRef adalah program untuk menyunting basisdata <em>bibtex</em>.
    Program ini menggunakan format berkas internal, yang berarti
    anda dapat memuat dan menyimpan basisdata anda langsung dalam
    format <em>bibtex</em> .bib. Anda juga dapat mengimpor berbagai format
    basisdata bibliograf ke JabRef.</p>

    <p>JabRef mempunyai fitur untuk mengurutkan dan mencari isi dari
    basisdata. Anda dapat dengan mudah menambah entri tanpa
    harus hafal informasi utama yang diperlukan, demikian pula
    kunci <em>bibtex</em> dapat dibuat secara otomatis. JabRef akan sangat
    berguna bagi yang menggunakan <em>bibtex</em> atau CiteMaker, 
    tetapi juga bermanfaat bagi yang menggunakan sistem referensi lain
    atau sangat membantu bagi yang sekedar ingin merapikan sumber
    pustaka.</p>

    <p><a href="BaseFrameHelp.php">Jendela utama JabRef</a></p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
