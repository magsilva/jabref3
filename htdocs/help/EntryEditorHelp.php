<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>The entry editor</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>The entry editor</h1><em>Opened from main window by
    double-clicking anywhere on the line of the entry, or selecting
    the entry and pressing ENTER. The panel is closed by pressing
    ESC.</em> 

    <p>In this panel you can specify all relevant information on a
    single entry. The entry editor checks the type of your entry,
    and lists all the fields that are required, and the ones that
    are optional, for referring the entry with <em>bibtex</em>. In
    addition, there are several fields termed <em>General
    fields</em>, that are common to all entry types.</p>

    <p>You can fully customize which fields should be regarded as
    required and optional for each type of entry, and which fields
    appear in the General fields tab. See
    <a href="CustomEntriesHelp.php">Customizing entry types</a>
    for more information about this.</p>

    <p>For information about how the fields should be filled out,
    see <a href="BibtexHelp.php">Bibtex help</a>.</p>

    <h2>The entry editor's panels</h2>The entry editor contains six
    panels: <em>Required fields</em>, <em>Optional fields</em>,
    <em>General</em>, <em>Abstract</em>, <em>Review</em> and
    <em>BibTeX source</em>, where <em>General</em>,
    <em>Abstract</em> and <em>Review</em> can be customized (see
    <a href="GeneralFields.php">Customizing general fields</a> for
    details). Inside the three first panels, TAB and SHIFT-TAB are
    used to switch focus between the text fields. 

    <p>Switch panels by clicking on the tabs, or navigate to the
    panel to the left or right using the following key
    combinations: CTRL-TAB or CTRL-PLUS switch to the tab to the
    right, and CTRL-SHIFT-TAB or CTRL-MINUS switch to the tab to
    the left. You can also switch to the next or previous entry by
    pressing CTRL-SHIFT-DOWN or CTRL-SHIFT-UP, respectively, or by
    clicking the appropriate toolbar button.</p>

    <p>The <em>bibtex source</em> panel shows how the entry will
    appear when the database is saved in <em>bibtex</em> format. If
    you wish, you can edit the <em>bibtex</em> source directly in
    this panel. When you move to a different panel, press CTRL-S or
    close the entry editor, JabRef will try to parse the contents
    of the source panel. If there are problems, you will be
    notified, and given the option to edit your entry further, or
    to revert to the former contents. If <strong>Show source by
    default</strong> is checked in the <strong>General
    options</strong> tab of the <strong>Preferences</strong>
    dialog, the source panel will be the one shown each time you
    open the entry editor. If you prefer editing the source rather
    than using the other four panels, you should check this
    option.</p>

    <p><strong>Tip:</strong> If your database contains fields
    unknown to JabRef, these will be visible in the source
    panel.</p>

    <p><strong>Tip:</strong> the <i>pdf</i> and <i>url</i> fields
    support Drag and Drop operations. You can drop there an url
    from your browser. either a link to a pdf file (that JabRef can
    download for you) or you can keep the link.</p>

    <h2>Field consistency checking</h2>When the contents of a field
    is changed, JabRef checks if the new contents are acceptable.
    For field types that are used by <em>bibtex</em>, the contents
    are checked with respect to the use of the '#' character. The
    hash symbol is <em>only</em> to be used in pairs, wrapping the
    name of a <em>bibtex</em> string that is referenced. Note that
    JabRef does not check if the referenced string actually exists
    (this is not trivial, since the <em>bibtex</em> style you use
    can define an arbitrary set of strings unknown to JabRef). 

    <p>If the contents are not accepted, the field will turn red,
    indicating an error. In this case the change will not be
    stored. <!--<H2>Word/name autocompletion</H2>
    The entry editor offers autocompletion of words. In the Preferences dialog
    you can enable or disable autocompletion, and choose for which fields
    autocompletion is active.
    <P>With autocompletion, JabRef records all words that appear in
    each of the chosen fields throughout your database. Whenever you write
    the beginning of one of these words, it will be suggested visually. To
    ignore the suggestion, simply write on. To accept the suggestion,
    either press <em>ENTER</em> or use your arrow keys or other keys to
    remove the selection box around the suggested characters.
    <P><em>Note:</em> the words considered for suggestion are only the ones
    appearing in the same field in entries of the same database as the one you
    are editing. There are many ways to realise this kind of feature, and if you feel
    it should have been implemented differently, we'd like to hear your suggestions!

    <H2>Copy <em>bibtex</em> key</H2>
    Pressing CTRL-K or the 'key' button causes the <em>bibtex</em> key for your entry
    to be copied to the clipboard.
    -->
    </p>

    <h2>Autogenerate <em>bibtex</em> key</h2>Press CTRL-G or the
    'gen key' button (the magic wand) to autogenerate a
    <em>bibtex</em> key for your entry based on the contents of its
    required fields. 

    <p>For more information on how JabRef generates <em>bibtex</em>
    keys, see <a href="LabelPatterns.php">Customizing the BibTex
    key generator</a>.</p>
    <?php include("../footer.php"); ?>
  </div>

</body>
</html>