<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="content-type" content=
  "text/html; charset=us-ascii" />
  <title>JabRef reference manager</title>
  <link href='css/style.css' rel='stylesheet' type='text/css' />
</head>

<body>
  <div id="container">

    <?php include("navigation.php"); ?>

    <div id="rightpanel">
      <h3>Installation</h3><span class="single_item"><a href=
      "#java">Installing Java</a></span> <span class="single_item"><a href=
      "#web">Web Start</a></span> <span class="single_item"><a href=
      "#linux">Linux</a></span> <span class="single_item"><a href=
      "#windows">Windows</a></span> <span class=
      "single_item"><a href="#macosx">MacOSX</a></span>
    </div>

    <div id="main">
      <h3 id="doc">Documentation</h3>

      <p>The main source of documentation is JabRef's built-in help
      pages, accessible from the Help menu.</p>

      <p>There is an <a href="help/Contents.html">online copy of
      these help documents</a>. Note that the online copy may not
      always be in sync with the latest JabRef version.</p>

      <p>For German readers, a more comprehensive <a href=
      "help/manual_pdf/JabRef-UserManual_de.pdf">user manual</a>
      (PDF) written by Dominik Wa&szlig;enhoven is also available.
      We hope to offer the user manual in other languages in the
      future.</p>

      <h3 id="java">Installing Java</h3>

      <p>Go to the <a href=
      "http://java.sun.com/j2se/1.4/download.html">Sun Java download page</a>,
      download the "sdk" version for your operating system and
      install it.</p>


      <h3 id="web">Web Start</h3>

      <p>If you have Java installed you can run the latest version
      of JabRef directly through Java Web Start by clicking the link
      below. Make sure your browser uses the &quot;javaws&quot;
      program when opening .jnlp files. For security, Web Start will
      ask your permission before it gives the application access to
      your local computer. You can only run JabRef in this way if
      you are willing to grant this permission.</p>

      <p><a href="http://jabref.sourceforge.net/jws/jabref.jnlp">Run JabRef with Java Web Start</a></p>


      <h3 id="linux">Linux</h3>

      <p>I am assuming you have downloaded the rpm version:
      j2sdk-1_4_0_03-linux-i586-rpm.bin</p>

      <ol>
        <li>login as root</li>

        <li>change to directory where you have downloaded the
        binary</li>

        <li>make the file executable:
          <pre>chmod +x j2sdk-1_4_0_03-linux-i586-rpm.bin</pre>
        </li>

        <li>run the executable:
          <pre>./j2sdk-1_4_0_03-linux-i586-rpm.bin</pre>
        </li>

        <li>After you accept the licence, an rpm file will be
        extracted and placed in the same directory</li>

        <li>install the extracted binary:
          <pre>rpm -Uvh j2sdk-1_4_0_03-linux-i586-rpm</pre>
        </li>

        <li>check if java is in your path:
          <pre>which java</pre>
	  if you get a message saying something like:
          <pre>/usr/bin/which: no java in ....</pre>
	  then you can either make a link to the java executable or add
	  the directory of the executable to the path. The java executable
	  will most likely be in
          <pre>/usr/java/j2sdk1.4.1_01/bin/</pre>
	  directory. So you can make a link to this as follows
          <pre>ln -s /usr/java/j2sdk1.4.1_01/bin/java /usr/bin/java</pre>
        </li>

        <li>now download the
          <pre>JabRef.jar</pre>
	  binary and type in
          <pre>java -jar JabRef.jar</pre>
        </li>
      </ol>


      <h3 id="windows">Windows</h3>

      <h4>Method 1: Mouse click way</h4>Now we have the .msi
      install file. If java is not installed it issues a complaint.
      This simply installs Jabref and a shortcut in the start menu.

      <h4>Method 2: Batch file way</h4>First try to simply
      double-click the JabRef.jar file - this works on some
      systems. If JabRef does not start immediately (i.e. Windows
      does not know what to do with a .jar file), proceed like
      this:

      <p>In the directory where you placed JabRef.jar, create a
      batchfile "jabref-start.bat":</p>
      <pre>start javaw -jar JabRef.jar</pre>
      Double-click on the batchfile to start JabRef.

      <h4>Method 3: Command line way</h4>

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
	  not want this window, start JabRef with
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
      launch JabRef</p>


      <h3 id="macosx">Mac</h3>

      <p>One user reported that if you have java 1.4.1 already
      installed on Mac OS 10.2.4 then double clicking on the
      JabRef.jar executes the program.</p>

    </div>
    <?php include("footer.php"); ?>
  </div>

</body>
</html>
