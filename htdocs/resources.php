<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>JabRef reference manager</title>
  <link href='css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">

    <?php include("navigation.php"); ?>

    <div id="rightpanel">

      <h3 id="downloadlist">Downloadable journal lists</h3>
      <ul>
        <li><a href="journals/journal_abbreviations_general.txt">General list</a></li>
        <li><a href="journals/journal_abbreviations_entrez.txt">Entrez journals abbreviation list</a> by Emmanuel Charpentier. <i>Note: provides Medline (dotless) abbr. only.</i></li>
        <li><a href="journals/journal_abbreviations_medicus.txt">Index Medicus abbreviation list</a> by Guy Tsafnat. 
<i>Note: provides Medline (dotless) abbr. only.</i></li>
        <li><a href="journals/journal_abbreviations_lifescience.txt">Life Science abbreviation list</a> by Z&eacute; Roberto Ribeiro</li>
        <li><a href="journals/journal_abbreviations_meteorology.txt">Meteorology journal list</a> by Thijs Heus.</li>
        <li><a href="journals/journal_abbreviations_sociology.txt">Sociology journal list</a> by Ronggui Huang.</li>
      </ul>

      <h3 id="exportfilters">Download export filters</h3>
      <ul>
        <li><a href="http://jabref.sourceforge.net/help/CustomExports.php">Mark Schenk's HTML export filters</a></li>
        <li><a href="exportfilters/Marten_Kooiker_export_filters_v1.0.zip">Marten Kooiker's RTF export filters</a></li>
      </ul>

    </div>

    <div id="main">

      <h2 id="abbrev_lists"><acronym>JabRef</acronym> journal abbreviation lists</h2>

      <p><acronym>JabRef</acronym> can help you refactor your reference list by
      automatically abbreviating or unabbreviating journal names. This
      requires that you keep one or more lists of journal names and their
      respective abbreviations. to set up these lists, choose 
      <b>Options -> Manage journal abbreviations</b>.</p>

      <h3 id="downloadlists">Downloadable journal lists</h3>
      <p><acronym>JabRef</acronym> includes no list of journals by default. Instead, the
      management window allows you to edit a personal list of
      journals, and to reference one or more external lists. To make this
      process more efficient for you, we offer a couple of lists from this
      web page, which can be downloaded by clicking a <b>Download</b> button
      in the <b>External files</b> section of the management window and
      entering the correct URL. You will then be asked to provide a local
      filename for the list.</p>

      <p>The download URL defaults to the general list linked below, which
      is a large list of journals, not limited to a single subject area. In
      addition there are (or will be) other, smaller, lists that contain
      journals from specific fields. To get one of these, just copy the URL
      from one of the links below, click <b>Download</b> in <acronym>JabRef</acronym>, and
      paste the URL into the query dialog that appears.</p>

      <h3 id="contribution">Contribution to journal abbreviation lists</h3>
      <p>We want to expand both the general list and the selection of
      smaller lists, so if you have set up a representative list for your
      own subject area, we would appreciate it if you share your list by
      opening a new entry in our 
      <a href="http://sourceforge.net/tracker/?group_id=92314&amp;atid=600308">Patches</a>
      section on SourceForge. You can either add your list as an attachment to the 
      patch entry, or keep the list on you own home page and link to it.
      We will then add your list to this page.</p>

      <h2 id="export_filters">Export filters</h2>

      <p><acronym>JabRef</acronym> allows you to create custom export
	filters. This functionality is described in the help file
	on <a href="http://jabref.sourceforge.net/help/CustomExports.php">
	Custom export filters</a>. Some users have created export filters that
	can be useful to many others, and on this page we provide links or direct
	downloads for some of these export filters.
      </p>
      <p>If you have created one or more export filters that you want to
	share with other users, please notify us, and we can provide a download
	from this page, or link to your own page.</p>

      <h3 id="Marks_filters">Mark Schenk's HTML export filters</h3>
      <a href="http://www.markschenk.com/tools/jabref/">Mark Schenk's
      HTML export filters</a> provide HTML listings of your reference
      list. The exported HTML comes complete with scripts for quick
      filtering of the list. <i>Note: some of Mark Schenk's filters
      are distributed with JabRef as standard export filters.</i>

      <h3 id="Martens_filters">Marten Kooiker's RTF export filters</h3>
      Marten Kooiker has put together a number of export filters outputting
      RTF references formatted for specific journals.
      <p><a href="exportfilters/Marten_Kooiker_export_filters_v1.0.zip">Download filters</a></p>

    <?php include("footer.php"); ?>

    </div>
  </div>

</body>
</html>