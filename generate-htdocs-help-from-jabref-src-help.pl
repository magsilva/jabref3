#!/usr/bin/perl

if (@ARGV==0) {

  print "Please provide name of .html file as commandline argument!\n";

} else {

  my $infilename  =  $ARGV[0];
  my $outfilename =  $infilename;
  $outfilename =~ s/\.html/\.php/g;

  open(INFILE,$infilename);
  @infile=<INFILE>;
  close(INFILE);

  my @outfile=();

  my $titel="";
  foreach $line(@infile) {
    if ($line =~ /\<h1\>(.*)\<\/h1\>/) {
      $titel=$1;
    }
  }

  my $header="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n\n<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">\n<head>\n  <meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\" />\n  <title>$titel</title>\n  <link href='/css/style.css' rel='stylesheet' type='text/css' />\n</head>\n\n<body>\n  <div id=\"container\">\n    <?php include(\"../navigation.php\"); ?>\n    <a href=\"Contents.php\">Back to contents</a>\n\n";
  my $footer="    <?php include(\"../footer.php\"); ?>\n  </div>\n\n</body>\n</html>\n";

  push(@outfile, $header);

  my $status=0;
  foreach $line(@infile) {

    if ($status==1 && $line =~ /\<\/body\>/) {
      $status=0;
    }

    if ($status==1) {
      $line =~  s/href=\"(.*)\.html\"/href=\"$1\.php\"/g;
      push(@outfile, $line);
    }

    if ($status==0 && $line =~ /\<basefont/) {
      $status=2;
    }

    if ($status==2 && $line =~ /\/\>/) {
      $status=1;
    }

  }

  push(@outfile, $footer);

  open(OUTFILE,">$outfilename");
  print OUTFILE @outfile;
  close(OUTFILE);

}
