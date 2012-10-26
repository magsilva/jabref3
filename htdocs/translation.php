<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
  <title>JabRef reference manager - Translating JabRef</title>
  <link href='css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">

    <?php include("navigation.php"); ?>

    <div id="rightpanel">

      <h3>Translating JabRef</h3>
      <span class="single_item"><a href="#introduction">Introduction</a></span>
      <span class="single_item"><a href="#translation">List of translation files</a></span>
      <span class="single_item"><a href="#property">The format of the property files</a></span>
      <span class="single_item"><a href="#Popeye">Using Popeye for editing translations</a></span>
      <span class="single_item"><a href="#contribute">How to contribute your translation</a></span>
    </div>

    <div id="main">

      <h2>Translating JabRef</h2>

      <h3 id="introduction">Introduction</h3>

      <p><acronym>JabRef</acronym> comes with a set of translations into other languages, currently Chinese (simplified), Danish, English, French, German, Indonesian, Italian, Japanese, Norwegian, Turkish and Vietnamese.</p>

      <p>We are constantly looking for translators and translations into other languages. For example a Spanish or Portugese translation would be valuable and of course translations into any other language would be very much appreciated.</p>

      <p>Contributing a new language to <acronym>JabRef</acronym> is easy since <acronym>JabRef</acronym> is prepared for multilanguage support. It only requires the creation of a new set of translation files - so-called property files. These are text files containing one text entry per line that can easily be generated using the java program Popeye a from a graphical user interface.</p>

      <p>Furthermore, for a translation to be available within <acronym>JabRef</acronym>, a corresponding line must be added in the Java class GUIGlobals (found in the directory src/java/net/sf/jabref/GUIGlobals.java in the <acronym>JabRef</acronym> source code tree). The line is inserted in the static {} section where the map LANGUAGES is populated. The code must of course be recompiled after this modification.</p>

      <p>If you are interested in translating <acronym>JabRef</acronym> or if you have suggestions or questions, please contact us using the <a href="http://sourceforge.net/mailarchive/forum.php?forum_name=jabref-users">jabref-users mailing list</a>.</p>

      <h3 id="translation">List of translation files</h3>

      <p>In the <acronym>JabRef</acronym> source code tree, the property files reside in the src/resource directory. For each language there are three files (denotes the country code for the language):</p>
      <ul>
	<li>JabRef_.properties : main translation file</li>
	<li>Menu_.properties : translation of menu items, marked up for mnemonic keys</li>
	<li>IntegrityMessage_.properties : small file containing messages for the database integrity check</li>
      </ul>

      <h3 id="property">The format of the property files</h3>

      <p>Each entry is first given in English, then in the other language, with the two parts separated by an '=' character. For instance, a line can look like this in a German translation file:</p>
      <pre><code>Background_color_for_optional_fields=Hintergrundfarbe_f\u00fcr_optionale_Felder</code></pre>

      <p>Note that each space character is replaced by an underscore, both in the English and the translated version.</p>

      <p>Some entries contain "variables" that are inserted at runtime by <acronym>JabRef</acronym> - this can for instance be a file name or a file type name:</p>
      <pre><code>Synchronizing_%0_links...=Synchronisiere_%0-Links...</code></pre>

      <p>A variable is denoted by %0, %1, %2 etc. In such entries, simply repeat the same notation in the translated version.</p>

      <p>As we can see, there are several "special" characters: the percent sign and the equals sign, along with the colon character. If these characters are to be part of the actual text in an entry, they must be escaped in the English version, as with the colon in the following example:</p>
      <pre><code>Error_writing_XMP_to_file\:_%0=Fehler_beim_Schreiben_von_XMP_in_die_Datei:_%0</code></pre>

      <h3 id="Popeye">Using Popeye for editing translations</h3>

      <p>To make it easier to keep track of your translation, we recommend using an application called <a href="http://sourceforge.net/projects/popeye">Popeye</a>. This application lets you set up a project including those property files and languages that you want to follow.</p>

      <h3 id="contribute">How to contribute your translation</h3>

      <p>Before deciding to become a regular translator for <acronym>JabRef</acronym>, consider that the application is evolving constantly, meaning that translators need to keep updating their translations! We are interested in supporting as many languages as possible, but we don't want incomplete translations.</p>

      <p>If you are still interested, you will need to contact the project manager - preferably through the <a href="http://sourceforge.net/mailarchive/forum.php?forum_name=jabref-users">jabref-users mailing list</a>. Then you can send over your initial versions of the translation files, and your language can be added to the current development version of <acronym>JabRef</acronym>. You will be made a member of the project group, and given the necessary access to our source control tree. This requires you to have a user account at SourceForge. This means that you will need to learn the basics of using the git version control system. The <a href="http://sourceforge.net/scm/?type=git&group_id=92314">JabRef's git repository page at SourceForge</a> and the linked documentation are places to start learning this, although there are several other tutorials available on the web.</p>

      <p>Using git you will be able to keep your local files updated, and you need to translate new text entries as they are added to your language's files.</p>

      <p>To test your translation you must be able to compile the source tree after making your additions. This requires you to install the Java Development Kit 6.0 or newer, and <a href="http://ant.apache.org/bindownload.cgi">Apache Ant</a>. Building <acronym>JabRef</acronym> with Ant is quite easy - simply enter the root directory of the source tree with a terminal or a cmd shell (under Windows), and run the command "ant". If everything is working as it should, the <acronym>JabRef</acronym> jar file should be built in the build/lib directory below the root directory. The jar file is executable either by double-clicking or by running the command</p>
      <pre><code>java -jar build/lib/JabRef-X.jar</code></pre>
      <p>where &ldquo;X&rdquo; is the current version number in the source tree.</p>

    </div>
    <?php include("footer.php"); ?>
  </div>

</body>
</html>
