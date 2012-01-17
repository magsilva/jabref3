<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<?php
  header('Content-type: application/xhtml+xml; charset=utf-8');
?>
<head>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
  <title>Spires search</title>
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Spires search</h1>

    <p>To use this feature, choose <b>Search -&gt; Web search</b>, and the
    search interface will appear in the side pane. Select <b>Spires</b> in the dropdown menu.</p>

    <p>The spires search function merely passes your search queries onto the Spires web search, so you
    should build your queries in the same way, except omitting the <em>find</em> or <em>fin</em> command. This help page will only give a brief introduction to the search queries. More extensive help on searching Spires can be found on the page http://www.slac.stanford.edu/spires/hep/help/index.shtml.</p>

    <p>Your query can be composed of several parts, combined using <em>and</em> and <em>or</em> as
    logical operators. Each part is composed of a letter or word indicating the type of field to search,
    followed by a space and the text to search for.</p>

    <p>The following list shows some of the field indicators that can be used:</p>
    <ul>
        <li><em>a</em> or <em>author</em>: search author names</li>
        <li><em>t</em> or <em>title</em>: search in title</li>
        <li><em>j</em>: journal. Here either the common abbreviation or the 5 letter CODEN abbreviation for
        a journal can be used. Volume and page can also be included, separated by commas. For instance,
        <em>j Phys. Rev.,D54,1</em> looks in the journal Phys. Rev., volume D54, page 1.</li>
        <li><em>k</em>: search in keywords</li>
    </ul>

    <p>Example queries:</p>
    <ul>
        <li><em>a smith and a jones</em>: search for references with authors "smith" and "jones"</li>
        <li><em>a smith or a jones</em>: search for references with either author "smith" or author "jones"</li>
        <li><em>a smith and not t processor</em>: search for author "smith" and omit references with "processor" in the title</li>
    </ul>

  <?php include("../footer.php"); ?>
  </div>

</body>
</html>
