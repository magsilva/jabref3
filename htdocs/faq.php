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
      <span class="single_item"><a href="#linux"><acronym>JabRef</acronym> &amp; Linux</a></span>
      <span class="single_item"><a href="#windows"><acronym>JabRef</acronym> &amp; Windows</a></span>
      <span class="single_item"><a href="#developers">Developers</a></span>
      <span class="single_item"><a href="#general_usage">General usage</a></span>
      <span class="single_item"><a href="#other">Other</a></span>

    </div>

    <div id="main">

      <h2>Frequently Asked Questions</h2>

      <h3 id="linux"><acronym>JabRef</acronym> &amp; Linux</h3>

      <p class="question">Q: Does <acronym>JabRef</acronym> run under free Java (Classpath, Kaffee, GCJ, etc.)?</p>
      <p class="answer">A: Unfortunately one of our dependencies currently crashes when running with Java APIs other than Suns. At the time of writing this (2006-09-13), version 0.92 of Classpath was used. Please let us know if a newer version fails or works for you. If you have an idea/the expertise to make <acronym>JabRef</acronym> work under Classpath let us know.</p>

      <p class="question">Q: <acronym>JabRef</acronym> does not start under Linux! What can I do?</p>
      <p class="answer">A: <acronym>JabRef</acronym> works fine under Linux if you use a Java Runtime Environment (JRE) from Sun version 1.5 and newer. The stable version <acronym>JabRef</acronym> 2.2 also works fine with Sun JRE 1.4.2. If running <acronym>JabRef</acronym> fails to start nevertheless do the following for troubleshooting:</p>
      <p class="answer">Run <pre>java --version</pre> from the command line. If this does not report to be a product from Sun Microsystems (for instance tells you that it is a GCJ VM) even if you have installed the Sun JVM then you need to change your setup. This is highly dependent on your  
distribution, so we cannot give exact advise for everybody. </p>
      <p class="answer">Under Debian/Ubuntu it works like this (you need to have admin privileges):</p>
      <p class="answer"><pre>sudo update-alternatives --config java</pre></p>
      <p class="answer">In the dialog that appears select the Sun JDK or JRE. Alternatively you can also search for the java executable and run that directly. In Ubuntu you can find Java at <pre>/usr/lib/jvm/java-1.5.0-sun/jre/bin/java</pre></p>
      <p class="answer">If you do not have root-access on the machine you can install the Sun JRE in your home but need to make sure that you run the correct java executable. For instance if you installed the JRE into a folder called  
      <pre>java</pre> in your home try <pre>~/java/jre/bin/java -jar JabRef-2.1.jar</pre></p>

      <p class="question">Q: I am on Ubuntu and clicking on the <acronym>JabRef</acronym> icon works, but I cannot start <acronym>JabRef</acronym> from the command line. What is wrong?</p>
      <p class="answer">A: You have several Java Virtual Machines installed and under the command line the wrong one is chosen. Have a look at the previous question that tells you how to change the virtual machine used.</p>

      <h3 id="windows"><acronym>JabRef</acronym> &amp; Windows</h3>

      <p class="question">Q: My virus-scanner tells me that <acronym>JabRef</acronym> is a virus. Is your server compromised?</p>
      <p class="answer">A: No. But the windows installer uses <a href="http://nsis.sourceforge.net/">the Nullsoft Scriptable Install System (NSIS)</a> version 2.18 (as of <acronym>JabRef</acronym> 2.1) which unfortunately creates installers that are thought to be viruses by some virus-scanners.</p>
      <p class="answer">See the <a href="http://forums.winamp.com/showthread.php?postid=1977648">discussion  
      in the NSIS forum</a> or <a href="http://nsis.sourceforge.net/NSIS_False_Positives">the list of false  
      positives with NSIS</a> for details.</p>
      <p class="answer">As a work-around and if you are afraid to use the installer you can always use the platform independent jar and run it from the command line:</p>
      <p class="answer"><pre>java -jar JabRef-2.1.jar</pre></p>
      <p class="answer">If you want to do anything about, please report this bug with your virus scanner.</p>

      <h3 id="developers">Developers</h3>

      <p class="question">Q: How can I join the <acronym>JabRef</acronym> project?</p>
      <p class="answer">A: Sign up with sourceforge and write an email to our project leader Morten O. Alver. Good ways to start with the project is to help with fixing bugs (maybe some that trouble you yourself ;-), writing FAQs, helping users in the forum and translating <acronym>JabRef</acronym> into another language.</p>

      <p class="question">Q: I have a patch that I want to contribute. Where can I do that?</p>
      <p class="answer">A: Please send patches to the Patch-Tracker on Sourceforge and write a quick entry on the user or developer list.</p>

      <h3 id="general_usage">General usage</h3>

      <p class="question">Q: How do I prevent <acronym>JabRef</acronym> from introducing line breaks in certain fields (such as "title") when saving the .bib file?</p>
      <p class="answer">A: Open Tools -> Preferences. In the "General" panel, you will find an option called "Do not wrap the following fields when saving". This option contains a semicolon-separated list of field names. Any field you add to this list will always be stored without introduction of line breaks.</p>

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
