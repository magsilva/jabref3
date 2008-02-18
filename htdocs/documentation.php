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
      <span class="single_item"><a href="help/de/Contents.php">German</a></span>

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
      "help/manual_pdf/JabRef-UserManual_de.pdf">user manual</a>
      (PDF) written by Dominik Wa&szlig;enhoven is also available.
      We hope to offer the user manual in other languages in the
      future.</p>

      <h3 id="install">Installation</h3>
      
      <h4 id="java">Installing Java</h4>

      <p>Go to the <a href=
      " http://java.sun.com/javase/downloads/index.jsp">Sun Java download page</a>,
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
      the <pre>non-free</pre> repository, you can install Java by
      typing the command
      <pre>sudo apt-get install sun-java6-jre</pre> and entering your password, or by installing the package
      <pre>sun-java6-jre</pre> in Synaptic.</p>

      <p>Your Linux distro might offer <acronym>JabRef</acronym> through its repositories - e.g. Ubuntu
	offers <acronym>JabRef</acronym> in its <pre>Multiverse</pre> repositories, and Debian offers
	<acronym>JabRef</acronym> in its <pre>unstable</pre> version. If this is not the case for your
	distro, you need to download the file <pre>JabRef-X.X.jar</pre> (where <pre>X.X</pre> is the <acronym>JabRef</acronym>
	version you want) from our download page. 

	
        <li>now download the
          <pre>JabRef.jar</pre>
	  binary and type in
          <pre>java -jar JabRef.jar</pre>
        </li>
      </ol>


      <h4 id="windows">Windows</h4>

      <h5>Method 1: Mouse click way</h5>Now we have the .msi
      install file. If java is not installed it issues a complaint.
      This simply installs <acronym>JabRef</acronym> and a shortcut in the start menu.

      <h5>Method 2: Batch file way</h5>First try to simply
      double-click the JabRef.jar file - this works on some
      systems. If <acronym>JabRef</acronym> does not start immediately (i.e. Windows
      does not know what to do with a .jar file), proceed like
      this:

      <p>In the directory where you placed JabRef.jar, create a
      batchfile "jabref-start.bat":</p>
      <pre>start javaw -jar JabRef.jar</pre>
      Double-click on the batchfile to start <acronym>JabRef</acronym>.

      <h5>Method 3: Command line way</h5>

      <p>I am assuming you hava j2sdk-1_4_* (same as java_1.4)
      installed on machine.</p>

      <ol>
        <li>download JabRef.jar to some directory say,
        c:\bibtools\</li>

        <li>open a command shell: Click on Start-&gt;Run, then type
        "cmd" and press return. A DOS window should open.</li>

        <li>use the DOS window to change to the directory from the
        first, step, e.g. "cd \bibtools"</li>

        <li>now type
          <pre>java -jar JabRef.jar</pre>
	  to execute the program. The DOS window will be used to
	  display debug information and error messages. If you do
	  not want this window, start <acronym>JabRef</acronym> with
          <pre>javaw -jar JabRef.jar</pre>
	  You can then close the DOS window once JabRef is running.<br />
        </li>
      </ol>

      <p>On one of the win2k's I tried, I was able to run the
      program just by double clicking on it. If you have installed
      j2sdk-1_4_* on your machine but the above shell command does
      not work, then you need to set your system variable "PATH" to
      include the location of java's "bin" directory.</p>

      <p>If double clicking does not work then create a file called
      JabRef.bat and in that simply type the following on the first
      line:&nbsp; java -jar c:/location/to/jabref/JabRef.jar</p>

      <p>Now you can double click on the JabRef.bat and it will
      launch <acronym>JabRef</acronym>.</p>


      <h4 id="macosx">Mac</h4>

      <p>One user reported that if you have java 1.4.1 already
      installed on Mac OS 10.2.4 then double clicking on the
      JabRef.jar executes the program.</p>

    </div>
    <?php include("footer.php"); ?>
  </div>

</body>
</html>
