<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>The string editor</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>The string editor</h1>

    <p><em>Opened from the main window
    by <b>BibTeX -> Edit strings</b> or pressing a button in the toolbar.</em></p>

    <p><em>Strings</em> are the <em>bibtex</em> equivalent to
    constants in a programming language. Each string is defined
    with a unique <em>name</em> and a <em>content</em>. Elsewhere
    in the database, the name can be used to represent the
    content.</p>

    <p>For instance, if many entries are from a journal with an
    abbreviation that may be hard to remember, such as 'J. Theor.
    Biol.' (Journal of Theroretical Biology), a string named JTB
    could be defined to represent the journal's name. Instead of
    repeating the exact journal name in each entry, the characters
    '#JTB#' (without quotes) are put into the <em>journal</em>
    field of each, ensuring the journal name is written identically
    each time.</p>

    <p>A string reference can appear anywhere in a field, always by
    enclosing the string's name in a pair of '#' characters. This
    syntax is specific for JabRef, and differs slightly from the
    <em>bibtex</em> notation that is produced when you save your
    database. Strings can by default be used for all standard
    BibTeX fields, and in <b>Preferences -&gt; General -&gt;
    File</b> you can opt to enable strings for non-standard fields
    as well. In the latter case you can specify a set of fields
    that are excepted from string resolving, and here it is
    recommended to include the 'url' field and other fields that
    may need to contain the '#' character and that may be processed
    by BibTeX/LaTeX.</p>

    <p>A string may in the same way be referred in the content of
    another string, provided the referred string is defined
    <em>before</em> the referring one.</p>

    <p>While the order of strings in your BibTeX file is important
    in some cases, you don't have to worry about this when using
    JabRef. The strings will be displayed in alphabetical order in
    the string editor, and stored in the same order, except when a
    different ordering is required by BibTeX.</p>
  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
