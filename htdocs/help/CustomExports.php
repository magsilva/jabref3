<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>Custom export filters</title>
  <link href='/css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">
    <?php include("../navigation.php"); ?>
    <a href="Contents.php">Back to contents</a>


    <h1>Custom export filters</h1>JabRef allows you to define and
    use your own export filters, in the same way as the standard
    export filters are defined. An export filter is defined by one
    or more <i>layout files</i>, which with the help of a
    collection of built-in formatter routines specify the format of
    the exported files. Your layout files must be prepared in a
    text editor outside of JabRef. 

    <h2>Adding a custom export filter</h2>The only requirement for
    a valid export filter is the existence of a file with the
    extension <b>.layout</b>. To add a new custom export filter,
    open the dialog box <b>Options -&gt; Manage custom exports</b>,
    and click <b>Add new</b>. A new dialog box will appear,
    allowing you to specify a name for the export filter (which
    will appear as one of the choices in the File type dropdown
    menu of the file dialog when you use the <b>File -&gt;
    Export</b> menu choice in the JabRef window), the path to the
    <b>.layout</b> file, and the preferred file extension for the
    export filter (which will be the suggested extension in the
    file dialog when you use the export filter). 

    <h2>Creating the export filter</h2>To see examples of how
    export filters are made, look for the package containing the
    layout files for the standard export filters on our download
    page. 

    <h3>Layout files</h3>Let us assume that we are creating an HTML
    export filter. 

    <p>While the export filter only needs to consist of a single
    <b>.layout</b> file, which in this case could be called
    <i>html.layout</i>, you may also want to add two files called
    <i>html.begin.layout</i> and <i>html.end.layout</i>. The former
    contains the header part of the output, and the latter the
    footer part. JabRef will look for these two files whenever the
    export filter is used, and if found, either of these will be
    copied verbatim to the output before or after the individual
    entries are written.</p>

    <p>Note that these files must reside in the same directory as
    <i>html.layout</i>, and must be named by inserting
    <b>.begin</b> and <b>.end</b>, respectively.</p>

    <p>In our example export filter, these could look like the
    following:</p>

    <p><i>html.begin.layout</i>:<br />
    <code>&lt;HTML&gt;<br />
     &lt;BODY&gt; text="#275856"&gt;<br />
    &lt;basefont size="4" color="#2F4958"
    face="arial"&gt;</code></p>

    <p><i>html.end.layout</i>:<br />
    <code>&lt;/BODY&gt;<br />
     &lt;/HTML&gt;</code></p>

    <p>The file <i>html.layout</i> provides the <i>default</i>
    template for exporting one single entry. If you want to use
    different templates for different entry types, you can do this
    by adding entry-specific <b>.layout</b> files. These must also
    reside in the same directory as the main layout file, and are
    named by inserting <b>.entrytype</b> into the name of the main
    layout file. The entry type name must be in all lowercase. In
    our example, we might want to add a template for book entries,
    and this would go into the file <i>html.book.layout</i>. For a
    PhD thesis we would add the file <i>html.phdthesis.layout</i>,
    and so on. These files are similar to the default layout file,
    except that they will only be used for entries of the matching
    type. Note that the default file can easily be made general
    enough to cover most entry types in most export filters.</p>

    <h3>The layout file format</h3>Layout files are created using a
    simple markup format where commands are identified by a
    preceding backslash. All text not identified as part of a
    command will be copied verbatim to the output file. 

    <h3>Field commands</h3>

    <p>An arbitrary word preceded by a backslash, e.g.
    <code>\author</code>, <code>\editor</code>, <code>\title</code>
    or <code>\year</code>, will be interpreted as a reference to
    the corresponding field, which will be copied directly to the
    output.</p>

    <h3>Field formatters</h3>

    <p>Often there will be a need for some preprocessing of the
    field contents before output. This is done using a <i>field
    formatter</i> - a java class containing a single method that
    manipulates the contents of a field.</p>

    <p>A formatter is used by inserting the <code>\format</code>
    command followed by the formatter name in square braces, and
    the field command in curly braces, e.g.:</p>

    <p><code>\format[ToLowerCase]{\author}</code></p>

    <p>You can also specify multiple formatters separated by
    commas. These will be called sequentially, from left to right,
    e.g.</p>

    <p><code>\format[ToLowerCase,HTMLChars]{\author}</code></p>

    <p>will cause the formatter <b>ToLowerCase</b> to be called
    first, and then <b>HTMLChars</b> will be called to format the
    result. You can list an arbitrary number of formatters in this
    way.</p>

    <p>The argument to the formatters, withing the curly braces,
    does not have to be a field command. Instead, you can insert
    normal text, which will then be passed to the formatters
    instead of the contents of any field. This can be useful for
    some fomatters, e.g. the CurrentDate formatter (described
    below).</p>

    <p>JabRef provides the following set of formatters, some of
    which depend on the others:</p>

    <ul>
        <li><code>HTMLChars</code> : replaces TeX-specific special
        characters (e.g. {\^a} or {\"{o}}) with their HTML
        representations.</li>

        <li><code>HTMLParagraphs</code> : interprets two
        consecutive newlines (e.g. \n \n) as the beginning of a new
        paragraph and creates paragraph-html-tags accordingly.</li>

        <li><code>XMLChars</code> : replaces TeX-specific special
        characters (e.g. {\^a} or {\"{o}}) with their XML
        representations.</li>

        <li><code>CreateDocBookAuthors</code> : formats the author
        field in DocBook style.</li>

        <li><code>CreateDocBookEditors</code> : to be
        documented.</li>

        <li><code>CurrentDate</code> : outputs the current date.
        With no argument, this formatter outputs the current date
        and time in the format "yyyy.MM.dd hh:mm:ss z" (date, time
        and time zone). By giving a different format string as
        argument, the date format can be customized. E.g.
        <code>\format[CurrentDate]{yyyy.MM.dd}</code> will give the
        date only, e.g. 2005.11.30.</li>

        <li><code>AuthorFirstFirst</code> : formats author/editor
        fields with the first names first.</li>

        <li><code>AuthorFirstFirstCommas</code> : formats
        author/editor fields with the first names first, and
        deliminated by commas.</li>

        <li><code>AuthorFirstAbbrLastCommas</code> : to be
        documented.</li>

        <li><code>AuthorFirstAbbrLastOxfordCommas</code> : to be
        documented.</li>

        <li><code>AuthorFirstLastOxfordCommas</code> : to be
        documented.</li>

        <li><code>AuthorLastFirst</code> : formats author/editor
        fields with the last names first.</li>

        <li><code>AuthorLastFirstAbbreviator</code> : abbreviates
        first and middle names of all authors. This formatter
        requires AuthorLastFirst to have been run earlier.</li>

        <li><code>AuthorLastFirstCommas</code> : to be
        documented.</li>

        <li><code>AuthorLastFirstOxfordCommas</code> : to be
        documented.</li>

        <li><code>AuthorLastFirstAbbrCommas</code> : to be
        documented.</li>

        <li><code>AuthorLastFirstAbbrOxfordCommas</code> : to be
        documented.</li>

        <li><code>AuthorAndsReplacer</code> : replaces "and"
        between names with ";", and "&amp;" between the last
        two.</li>

        <li><code>AuthorAndsCommaReplacer</code> : replaces "and"
        between names with ",", and "&amp;" between the last
        two.</li>

        <li><code>AuthorOrgSci</code> : first author is in "last,
        first" all others in "first last". First names are
        abbreviated.</li>

        <li><code>AuthorAbbreviator</code> : to be documented.</li>

        <li><code>AuthorNatBib</code> : formats author names in
        NatBib style, with last names only, separating names by
        "and" if there are two authors, and giving the first author
        followed by "et al." if there are more than two
        authors.</li>

        <li><code>NoSpaceBetweenAbbreviations</code> : spaces
        between multiple abbreviated first names are removed.</li>

        <li><code>FormatPagesForHTML</code> : replaces "--" with
        "-".</li>

        <li><code>FormatPagesForXML</code> : replaces "--" with an
        XML en-dash.</li>

        <li><code>RemoveBrackets</code> : removes all curly
        brackets "{" or "}".</li>

        <li><code>RemoveBracketsAddComma</code> : to be
        documented.</li>

        <li><code>RemoveWhitespace</code> : to be documented.</li>

        <li><code>RemoveLatexCommands</code> : removes LaTeX
        commands like <code>\em</code>, <code>\textbf</code>, etc.
        If used together with <code>HTMLChars</code> or
        <code>XMLChars</code>, this formatter should be called
        last.</li>

        <li><code>RemoveTilde</code> : replaces the tilde character
        used in LaTeX as a non-breakable space by a regular space.
        Useful in combination with the NameFormatter discussed in
        the next section.</li>

        <li><code>ToLowerCase</code> : turns all characters into
        lower case.</li>

        <li><code>CompositeFormat</code> : to be documented.</li>

        <li><code>GetOpenOfficeType</code> : to be documented.</li>

        <li><code>RTFChars</code> : to be documented.</li>

        <li><code>ResolvePDF</code> : to be documented.</li>
    </ul>

    <p>If none of the available formatters can do what you want to
    achieve, you can add your own by implementing the
    <code>net.sf.jabref.export.layout.LayoutFormatter</code>
    interface. If you insert your class into the
    <code>net.sf.jabref.export.layout.format</code> package, you
    can call the formatter by its class name only, like with the
    standard formatters. Otherwise, you must call the formatter by
    its fully qualified name (including package name). In any case,
    the formatter must be in your classpath when running
    JabRef.</p>

    <h2><a name="NameFormatter"
       id="NameFormatter">Using Custom Name Formatters</a></h2>

    <p>With JabRef 2.2 it is now possible to define custom name
    formatters using the bibtex-sty-file syntax. This allows
    ultimate flexibility, but is a cumbersome to write</p>

    <p>You can define your own formatter in the preference tab
    "Name Formatter" using the following format and then use it
    with the name given to it as any other formatter</p>
    <code>&lt;case1&gt;@&lt;range11&gt;@&lt;format&gt;@&lt;range12&gt;@&lt;format&gt;@&lt;range13&gt;...@@<br />

     &lt;case2&gt;@&lt;range21&gt;@... and so on.</code> 

    <p>This format first splits the task to format a list of author
    into cases depending on how many authors there are (this is
    since some formats differ depending on how many authors there
    are). Each individual case is separated by @@ and contains
    instructions on how to format each author in the case. These
    instructions are separated by a @.</p>

    <p>Cases are identified using integers (1, 2, 3, etc.) or the
    character * (matches any number of authors) and will tell the
    formatter to apply the following instructions if there are a
    number of less or equal of authors given.</p>

    <p>Ranges are either
    <code>&lt;integer&gt;..&lt;integer&gt;</code>,
    <code>&lt;integer&gt;</code> or the character <code>*</code>
    using a 1 based index for indexing authors from the given list
    of authors. Integer indexes can be negative to denote them to
    start from the end of the list where -1 is the last author.</p>

    <p>For instance with an authorlist of "Joe Doe and Mary Jane
    and Bruce Bar and Arthur Kay":</p>

    <ul>
        <li>1..3 will affect Joe, Mary and Bruce</li>

        <li>4..4 will affect Arthur</li>

        <li>* will affect all of them</li>

        <li>2..-1 will affect Mary, Bruce and Arthur</li>
    </ul>

    <p>The <code>&lt;format&gt;</code>-strings use the Bibtex
    formatter format:</p>

    <p>The four letters v, f, l, j indicate the name parts von,
    first, last, jr which are used within curly braces. A single
    letter v, f, l, j indicates that the name should be
    abbreviated. If one of these letters or letter pairs is
    encountered JabRef will output all the respective names
    (possibly abbreviated), but the whole expression in curly
    braces is only printed if the name part exists.</p>

    <p>For instance if the format is "{ll} {vv {von Part}} {ff}"
    and the names are "Mary Kay and John von Neumann", then JabRef
    will output "Kay Mary" (with two space between last and first)
    and "Neuman von von Part John".</p>

    <p>I give two examples but would rather point you to the bibtex
    documentation.</p>

    <p>Small example: <code>"{ll}, {f.}"</code> will turn
    <code>"Joe Doe"</code> into <code>"Doe, J."</code></p>

    <p>Large example:</p>

    <blockquote>
        <p>To turn:</p>

        <p><code>"Joe Doe and Mary Jane and Bruce Bar and Arthur
        Kay"</code></p>

        <p>into</p>

        <p><code>"Doe, J., Jane, M., Bar, B. and Kay,
        A."</code></p>

        <p>you would use</p>

        <p><code>1@*@{ll}, {f}.@@2@1@{ll}, {f}.@2@ and {ll},
        {f}.@@*@1..-3@{ll}, {f}., @-2@{ll}, {f}.@-1@ and {ll},
        {f}.</code></p>
    </blockquote>

    <p>If somebody would like to write a better tutorial about
    this: Write a mail to one of the JabRef mailinglists!</p>

    <h3>Conditional output</h3>Some static output might only make
    sense if a specific field is set. For instance, say we want to
    follow the editor names with the text <code>(Ed.)</code>. This
    can be done with the following text: 

    <p><code>\format[HTMLChars,AuthorFirstFirst]{\editor}
    (Ed.)</code></p>

    <p>However, if the <code>editor</code> field has not been set -
    it might not even make sense for the entry being exported - the
    <code>(Ed.)</code> would be left hanging. This can be prevented
    by instead using the <code>\begin</code> and <code>\end</code>
    commands:</p>

    <p><code>\begin{editor}<br />
    \format[HTMLChars,AuthorFirstFirst]{\editor} (Ed.)<br />
     \end{editor}</code></p>

    <p>The <code>\begin</code> and <code>\end</code> commands make
    sure the text in between is printed if and only if the field
    referred in the curly braces is defined for the ently being
    exported.</p>

    <p><b>Note:</b> Use of the <code>\begin</code> and
    <code>\end</code> commands is a key to creating layout files
    that work well with a variety of entry types.</p>

    <h3>Grouped output</h3>If you wish to separate your entries
    into groups based on a certain field, use the grouped output
    commands. Grouped output is very similar to conditional output,
    except that the text in between is printed only if the field
    referred in the curly braces has changed value. 

    <p>For example, let's assume I wish to group by keyword. Before
    exporting the file, make sure you have sorted your entries
    based on keyword. Now use the following commands to group by
    keyword:</p>

    <p><code>\begingroup{keywords}New Category:
    \format[HTMLChars]{\keywords}<br />
     \endgroup{keywords}</code></p>

    <h2>Sharing your work</h2>With external layout files, it's
    fairly simple to share custom export formats between users. If
    you write an export filter for a format not supported by
    JabRef, or an improvement over an existing one, we encourage
    you to post your work on our SourceForge.net page. The same
    goes for formatter classes that you write. We'd be happy to
    distribute a collection of submitted layout files, or to add to
    the selection of standard export filters and formatters. 
    <?php include("../footer.php"); ?>
  </div>

</body>
</html>
