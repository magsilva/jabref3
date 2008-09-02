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

      <h3>Frequently Asked Questions</h3>
      <span class="single_item"><a href="#general_usage">General usage</a></span>
      <span class="single_item"><a href="#linux"><acronym>JabRef</acronym> &amp; Linux</a></span>
      <span class="single_item"><a href="#windows"><acronym>JabRef</acronym> &amp; Windows</a></span>
      <span class="single_item"><a href="#developers">Developers</a></span>
      <span class="single_item"><a href="#other">Other</a></span>

    </div>

    <div id="main">

      <h2>Frequently Asked Questions</h2>

      <h3 id="general_usage">General usage</h3>

      <p class="question">Q: How do I prevent <acronym>JabRef</acronym> from introducing line breaks in certain fields (such as "title") when saving the .bib file?</p>
      <p class="answer">A: Open Tools -> Preferences. In the "General" panel, you will find an option called "Do not wrap the following fields when saving". This option contains a semicolon-separated list of field names. Any field you add to this list will always be stored without introduction of line breaks.</p>

      <p class="question">Q: Is it possible to open files, e.g. from my web browser, in the running instance of JabRef instead of opening a new instance?</p>
      <p class="answer">A: Yes, if you activate the "Remote operation" option under <b>Preferences -> Advanced</b>. This option allows new instances of JabRef to detect the instance already running, and pass files to that instead of opening a new window.</p>

      <p class="question">Q: I want to link external files with paths relative to my .bib file, so I can easily move my database along with its files to another directory. Is this possible?</p>
      <p class="answer">A: Yes. You need to override the default file directory for this specific database. Go to <b>File -> Database properties</b> and change the <b>Default file directory</b> setting. If you simply enter &quot;.&quot; (a dot, without the quotes), the file directory will be the same as the .bib file directory. To place your files in a subdirectory called <b>subdir</b>, you can enter <b>&quot;./subdir&quot;</b>. Files will automatically be linked with relative paths if the files are placed in the default file directory or in a directory below it.</p>

      <h3 id="linux"><acronym>JabRef</acronym> &amp; Linux</h3>

      <p class="question">Q: Does <acronym>JabRef</acronym> run under free Java (Classpath, Kaffee, GCJ, etc.)?</p>
      <p class="answer">A: As far as we know, it has not yet succeeded running JabRef on these free JVMs, due of our dependencies. At the time of writing this (2006-09-13), version 0.92 of Classpath was used. However, <acronym>JabRef</acronym> is reported to run nicely on the <a href="http://fedoraproject.org/wiki/Features/IcedTea">IcedTea</a> runtime, which is based on the <a href="http://openjdk.java.net/">OpenJDK</a> built with <a href="http://www.gnu.org/software/classpath/">GNU Classpath</a> to fill in missing classes. Please let us know if newer versions give different results. If you have an idea or the expertise to make <acronym>JabRef</acronym> work under Classpath let us know.</p>

      <p class="question">Q: <acronym>JabRef</acronym> does not start under Linux! What can I do?</p>
      <p class="answer">A: <acronym>JabRef</acronym> works fine under Linux if you use a Java Runtime Environment (JRE) from Sun version 1.5 and newer. If running <acronym>JabRef</acronym> fails to start nevertheless do the following for troubleshooting:</p>
      <p class="answer">Run </p><pre>java --version</pre><p class="answer"> from the command line. If this does not report to be a product from Sun Microsystems (for instance tells you that it is a GCJ VM) even if you have installed the Sun JVM then you need to change your setup. This is highly dependent on your
distribution, so we cannot give exact advise for everybody. </p>
      <p class="answer">Under Debian/Ubuntu it works like this (you need to have admin privileges):</p>
      <p class="answer"></p><pre>sudo update-alternatives --config java</pre><p class="answer"></p>
      <p class="answer">In the dialog that appears select the Sun JDK or JRE. Alternatively you can also search for the java executable and run that directly. In Ubuntu you can find Java at </p><pre>/usr/lib/jvm/java-1.5.0-sun/jre/bin/java</pre><p class="answer"></p>
      <p class="answer">If you do not have root-access on the machine you can install the Sun JRE in your home but need to make sure that you run the correct java executable. For instance if you installed the JRE into a folder called
      </p><pre>java</pre><p class="answer"> in your home try </p><pre>~/java/jre/bin/java -jar JabRef-2.1.jar</pre><p class="answer"></p>

      <p class="question">Q: I am on Debian/Ubuntu and clicking on the <acronym>JabRef</acronym> icon works, but I cannot start <acronym>JabRef</acronym> from the command line. What is wrong?</p>
      <p class="answer">A: You have several Java Virtual Machines installed and under the command line the wrong one is chosen. Have a look at the previous question that tells you how to change the virtual machine used.</p>

      <p class="answer">For Ubuntu you may also have a look at the <a href="https://help.ubuntu.com/community/Java">Ubuntu page on Java</a>.</p>

      <h3 id="windows"><acronym>JabRef</acronym> &amp; Windows</h3>

      <p class="question">Q: My virus-scanner tells me that <acronym>JabRef</acronym> is a virus. Is your server compromised?</p>
      <p class="answer">A: No. But the windows installer uses <a href="http://nsis.sourceforge.net/Main_Page">the Nullsoft Scriptable Install System (NSIS)</a> version 2.18 (as of <acronym>JabRef</acronym> 2.1) which unfortunately creates installers that are thought to be viruses by some virus-scanners.</p>
      <p class="answer">See the <a href="http://forums.winamp.com/showthread.php?postid=1977648">discussion
      in the NSIS forum</a> or <a href="http://nsis.sourceforge.net/NSIS_False_Positives">the list of false
      positives with NSIS</a> for details.</p>
      <p class="answer">As a work-around and if you are afraid to use the installer you can always use the platform independent jar and run it from the command line:</p>
      <p class="answer"></p><pre>java -jar JabRef-X.jar</pre><p class="answer"></p>
      <p class="answer">where 'X' is the JabRef version.</p>
      <p class="answer">Since JabRef is open source, you can of course also download the source code and compile it yourself.</p>
      <p class="answer">If you want to do anything about this issue, please report this bug with your virus scanner.</p>

      <h3 id="developers">Developers</h3>

      <p class="question">Q: How can I join the <acronym>JabRef</acronym> project?</p>
      <p class="answer">A: Sign up with sourceforge and write an email to our project leader Morten O. Alver. Good ways to start with the project is to help with fixing bugs (maybe some that trouble you yourself ;-), writing FAQs, helping users in the forum and translating <acronym>JabRef</acronym> into another language.</p>

      <p class="question">Q: I have a patch that I want to contribute. Where can I do that?</p>
      <p class="answer">A: Please send patches to the Patch-Tracker on Sourceforge and write a quick entry on the user or developer list.</p>

      <p class="question">Q: What is the branching strategy used in <acronym>JabRef</acronym>'s SVN repository?</p>
      <p class="answer">A: The trunk is used for developments targeted for the next release. When we release the first beta of a new version, e.g. 2.4, we make a branch (beta_2.4). From that moment, all changes that are to be part of the 2.4 version must be made in the branch, while changes that are for the following version must be made in the trunk. This lets us polish the 2.4 version while at the same time checking in features for version 2.5. When 2.4 is finished, it is built from the beta_2.4 branch, and then all changes that have been made in the beta_2.4 branch since it was created are merged into the trunk. At this point the trunk has all the changes of the 2.4 version, in addition to whatever has been done in the trunk in the meantime.</p>

      <h3 id="other">Other</h3>

      <p class="question">Q: My question is not answered here. What can I do?</p>
      <p class="answer">A: After consulting the <a href="http://jabref.sourceforge.net/documentation.php">documentation</a>
      and checking whether your question has been answered in the <a href="https://sourceforge.net/forum/forum.php?forum_id=318825">forum</a>, check the <a href="https://sourceforge.net/tracker/?group_id=92314&amp;atid=600306">bug-tracker</a> if this has been discussed before (filter for Any Bugs). If you cannot find anything in an reasonable amount of time write a message in the forum, problems usually get resolved rather quickly (at least we hope so ;-).</p>

      <p class="question">Q: There is a mistake in this FAQ, a dead link or I have written a better/new explanation for a question!</p>
      <p class="answer">A: Let us know as soon as possible! => <a href="https://sourceforge.net/forum/forum.php?forum_id=318825"><acronym>JabRef</acronym> User Forum</a></p>

    </div>
    <?php include("footer.php"); ?>
  </div>

</body>
</html>
