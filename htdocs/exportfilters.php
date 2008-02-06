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
      <h3 id="downloadlist">Download export filters</h3>
      <ul>
        <li><a href="http://jabref.sourceforge.net/help/CustomExports.php">Mark Schenk's HTML export filters</a></li>
        <li><a href="exportfilters/Marten_Kooiker_export_filters_v1.0.zip">Marten Kooiker's RTF export filters</a></li>
      </ul>
    </div>

    <div id="main">
      <h2 id="abbrev_lists">Export filters</h2>

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

      <h3>Mark Schenk's HTML export filters</h3>
      <a href="http://www.markschenk.com/tools/jabref/">Mark Schenk's
      HTML export filters</a> provide HTML listings of your reference
      list. The exported HTML comes complete with scripts for quick
      filtering of the list. <i>Note: some of Mark Schenk's filters
      are distributed with JabRef as standard export filters.</i>

      <h3>Marten Kooiker's RTF export filters</h3>
      Marten Kooiker has put together a number of export filters outputting
      RTF references formatted for specific journals.
      <p><a href="exportfilters/Marten_Kooiker_export_filters_v1.0.zip">Download filters</a></p>
    </div>
    <?php include("footer.php"); ?>
  </div>

</body>
</html>
