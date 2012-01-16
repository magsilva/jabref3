#!/usr/bin/perl

use constant HELPDIR_JABREF => "jabref/src/help";
use constant HELPDIR_WEB    => "htdocs/help";

#build.xml for getting string replacements @version@ and @year@
use constant BUILDXML       => "jabref/build.xml";

#required at cygwin
use constant FORCE_WINDOWS_NEWLINES => 1;

use warnings;
use strict;

#enable "given/when"
use feature ":5.10";

sub handleDir;
sub handleFile;
sub loadPreferences;

our $jabref_version;
our $jabref_year;
our $jabref_placeholder_version;
our $jabref_placeholder_year;

loadPreferences();

#Debug call for a single file
#handleFile("jabref/src/help/About.html", "htdocs/help/About.php", "en");
#exit;


# handle English
handleDir(HELPDIR_JABREF, HELPDIR_WEB, "en");

#handle other languages (contained in sub directories)

my $helpdirJabRef;

opendir($helpdirJabRef, HELPDIR_JABREF) or die $!;

my $sourcedir;
my $targetdir;
my $lang;

while (my $subdir = readdir($helpdirJabRef)) {
	$sourcedir = HELPDIR_JABREF . "/$subdir";
	next unless (-d $sourcedir);
	next if ($subdir =~ /\.\.?/);
	
	$targetdir = HELPDIR_WEB . "/$subdir";
	$lang = $subdir;
	
	handleDir($sourcedir, $targetdir, $lang);
}
close($helpdirJabRef);

exit 0;



# Parameters:
#    sourcedir
#    targetdir
#    language
sub handleDir {
	my $sourcedir = shift;
	my $targetdir = shift;
	my $lang = shift;
	
	print("Handling $sourcedir...\n");
	
	if (!-d $targetdir) {
		mkdir($targetdir);
	}

	my $dh;
	opendir($dh, $sourcedir) or die $!;
	while (my $infilename = readdir($dh)) {
		next unless ($infilename =~ /\.html$/);
		my $outfilename =  $infilename;
		$outfilename =~ s/\.html/\.php/g;
		my $sourcefilename = $sourcedir . "/" . $infilename;
		my $targetfilename = $targetdir . "/" . $outfilename;
		handleFile($sourcefilename, $targetfilename, $lang);
	}
	close($dh);	
}

#
# Parameters:
#    infilename: source file (html)
#    outfile:    target file (php)
#    lang:       language (ISO-format)
#
sub handleFile {
  my $infilename  =  shift;
  my $outfilename = shift;
  my $lang = shift;
  
  my $replace_placeholders = ($infilename =~ /About.html$/);
  
  #Debug output
  #print("handleFile:\n$infilename\n$outfilename\n$lang\n$replace_placeholders\n\n");

  open(my $infileH, "<", $infilename) or die "cannot open < $infilename: $!";
  my @infile = <$infileH>;
  
  my @outfile=();

  # Determine title out of first h1 heading
  my $title="";
  my $line;
  foreach $line(@infile) {
    if ($line =~ /\<h1\>(.*)\<\/h1\>/) {
      $title=$1;
	  # title is found, go to the normal handling
	  last;
    }
  }
  
  #remove html tags from title
  #even if <em> is not allowed in h1 elements, JabRef doc uses that
  $title =~ s#<(.|\n)*?>##g;
  
  my $header=<<HTML;
<?xml version=\"1.0\" encoding=\"UTF-8\"?>
<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\"
    \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">
<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"$lang\">
<head>
  <meta http-equiv=\"content-type\" content=\"application/xhtml+xml; charset=UTF-8\" />
  <title>$title</title>
  <link href=\"/css/style.css\" rel=\"stylesheet\" type=\"text/css\" />
</head>

<body>
  <div id=\"container\">
    <?php include(\"../navigation.php\"); ?>
    <a href=\"Contents.php\">Back to contents</a>
	
HTML

  my $footer=<<HTML;
  <?php include(\"../footer.php\"); ?>
  </div>\n\n</body>\n</html>
HTML

  push(@outfile, $header);

  my $status=0;
  # 0 out of html
  # 1 in html
  # 2 within basefont
  
  foreach $line(@infile) {
    #Debug states
	#print "$status / $line";

    if ($status==0 && $line =~ /\<body/) {
      $status=1;
    } elsif ($status==1 && $line =~ /\<\/body\>/) {
      $status=0;
    } elsif ($status==1) {
	  if ($replace_placeholders) {
	    $line =~ s/$jabref_placeholder_version/$jabref_version/;
	    $line =~ s/$jabref_placeholder_year/$jabref_year/;
	  }	  
	  if (!($line =~ /href=\"http:\/\//)) {
		#line does NOT contain a href to some http address
		#we assume that line is NOT a reference to an external site
		#replace "html" extension with "php" extension
		#still allow links as "...html#part".
		$line =~  s/href=\"([^\"]*)\.html/href=\"$1\.php/g;
	  }
      push(@outfile, $line);
    #} elsif ($status==0 && $line =~ /\<basefont/) {
    #  $status=2;
    #} elsif ($status==2 && $line =~ /\/\>/) {
    #  $status=1;
    }
  }

  push(@outfile, $footer);

  open(OUTFILE,">$outfilename");
  
  if (FORCE_WINDOWS_NEWLINES) {
	foreach my $line (@outfile) {
		$line =~ s/\r?\n|\r/\r\n/g;
	}
  }

  print OUTFILE @outfile;

  close(OUTFILE);
  
  close($infileH);
}

#extracts info out of build.xml
#	<property name="jabref.version" value="2.8b" />
#	<property name="jabref.year" value="2012" />
#	<property name="jabref.placeholder.version" value="@version@" />
#	<property name="jabref.placeholder.year" value="@year@" />
sub loadPreferences {
  open(my $buildXML, "<", BUILDXML) or die "cannot open < " . BUILDXML . ": $!";
  my @buildxml = <$buildXML>;
  close($buildXML);
  foreach my $line (@buildxml) {
    #check for one-line property declaration name / value
    if ($line =~ /property name="([^"]*)" value="([^"]*)"/) {
	  #copy value from value to local variable
	  #a non-hardcoded version using "eval" would also be possible
	  #the SLOC count would be equal to the count of the following (easier) given/when construct.
	  given($1) {
		when ("jabref.version") {
		  $jabref_version = $2;
		}
	    when ("jabref.year") {
		  $jabref_year = $2;
		}
		when ("jabref.placeholder.version") {
		  $jabref_placeholder_version = $2;
		}
		when ("jabref.placeholder.year") {
		  $jabref_placeholder_year = $2;
		}
      }
    }
  }
}