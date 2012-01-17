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
      <h3>Online documentation</h3>
      <span class="single_item"><a href="help/Contents.php">English</a></span>
      <span class="single_item"><a href="help/fr/Contents.php">French</a></span>
	  <span class="single_item"><a href="help/ja/Contents.php">Japanese</a></span>
      <span class="single_item"><a href="help/de/Contents.php">German</a></span>
	  <span class="single_item"><a href="help/in/Contents.php">Indonesian</a></span>

      <h3>Installation</h3>
      <span class="single_item"><a href="#java">Installing Java</a></span>
      <span class="single_item"><a href="#web">Web Start</a></span>
      <span class="single_item"><a href="#linux">Linux</a></span>
      <span class="single_item"><a href="#windows">Windows</a></span>
      <span class="single_item"><a href="#macosx">MacOSX</a></span>
    </div>

    <div id="main">
      <h2>Documentation</h2>

      <p>The main source of documentation is <acronym>JabRef's</acronym> built-in help
      pages, accessible from the Help menu.</p>

      <p>There is an online copy of these help documents
      (<a href="help/Contents.php">English version,</a>
      <a href="help/fr/Contents.php">French version,</a>
      <a href="help/de/Contents.php">German version</a>). 
      Note that the online copy may not
      always be in sync with the latest <acronym>JabRef</acronym> version.</p>

      <p>For German readers, a more comprehensive <a href=
      "manuals/JabRef-UserManual_de.pdf">user manual</a>
      (PDF) written by Dominik Wa&szlig;enhoven is also available.
      We hope to offer the user manual in other languages in the
      future.</p>

      <h3 id="install">Installation</h3>
      
      <h4 id="java">Installing Java</h4>

      <p>Go to the <a href=
      "http://www.oracle.com/technetwork/java/javase/downloads/index.html">Sun Java download page</a>,
      download the "jre" version for your operating system and
      install it.</p>


      <h4 id="web">Web Start</h4>

      <p>If you have Java installed you can run the latest version
      of <acronym>JabRef</acronym> directly through Java Web Start by clicking the link
      below. Make sure your browser uses the &quot;javaws&quot;
      program when opening .jnlp files. For security, Web Start will
      ask your permission before it gives the application access to
      your local computer. You can only run JabRef in this way if
      you are willing to grant this permission.</p>

      <p><a href="http://jabref.sourceforge.net/jws/jabref.jnlp">Run JabRef with Java Web Start</a></p>


      <h4 id="linux">Linux</h4>

      <p>You must have Java 1.5 or newer installed. Most current Linux
      distributions offer both Java 1.5 and 1.6 from their
      repositories. For instance, in Ubuntu 7.10, if you have enabled
      the <code>non-free</code> repository, you can install Java by
      typing the command
      </p>
      <pre>sudo apt-get install sun-java6-jre</pre>
      <p>and entering your password, or by installing the package
      <code>sun-java6-jre</code> in Synaptic.</p>

      <p>Your Linux distro might offer <acronym>JabRef</acronym> through its repositories - e.g. Ubuntu
	offers <acronym>JabRef</acronym> in its <code>Multiverse</code> repositories, and Debian offers
	<acronym>JabRef</acronym> in its <code>unstable</code>
      version. After installing the application through the package
      manager, you can typically run it from the standard application
      menu (for instance, Ubuntu places it in the <i>Office</i>
      category).</p>

      <p>If this is not the case for your distro, you need to download the file
      <code>JabRef-X.X.jar</code> (where <code>X.X</code> is the <acronym>JabRef</acronym>
      version you want) from our download page. To run it, open a terminal and type:
      </p>
      <pre>java -jar $DIR/JabRef-X.X.jar</pre>
      <p>where <code>$DIR</code> is the directory where you placed the jar file,
      and <code>JabRef-X.X.jar</code> is the name of the jar file.
      </p>


      <h4 id="windows">Windows</h4>

      <h5>Installer</h5>
      The simplest way of installing <acronym>JabRef</acronym> on Windows is by downloading
      the <code>.exe</code> installer. Simply run the installer, and it will install 
      <acronym>JabRef</acronym> and add it to the start menu.
      
      <h5>Running the jar file</h5>
      It is also possible to run <acronym>JabRef</acronym> using the <code>.jar</code> files
      available from our download page. Normally you can run it by simply double-clicking
      the <code>.jar</code> file. Alternatively you can open a CMD shell and run it in the same
      way as running a <code>.jar</code> file under Linux.

      <h4 id="macosx">Mac</h4>

      <p>Mac users should download the OSX zip file available from our download page.
      This zip file unzips into an application directory which is run similarly to other
      applications. Recent versions of Mac OS X should come with Java 1.5 or 1.6 preinstalled.
      </p>
    </div>
    <?php include("footer.php"); ?>
  </div>

</body>
</html>
