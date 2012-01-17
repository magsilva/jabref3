#!/usr/bin/perl

use constant WAITAFTEREACHERROR => 1;

#online web site
use constant STARTDIR => "htdocs";

#single help
#use constant STARTDIR => "htdocs/help/ja";

#JabRef help
#  never validates as no HTML head is used and no DOCTYPE is declared.
#use constant STARTDIR => "jabref/src/help/da";

use File::Find;
use strict;

sub wait_for_keypress {
    return unless WAITAFTEREACHERROR;
    print "Press 'Return' to continue. (Enter \"exit\" to exit the whole process)\n";
    my $input = <STDIN>;
	exit 0 if $input =~ /exit/;
}

sub verifyFile {
	return unless -f;
	#my $fullfilename = $File::Find::name;
	my $filename = $_;
	return unless ($filename =~ /(\.php)|(\.html)$/);

	system("tidy", "-eq", "-utf8", "$filename");

	if ($? == -1) {
		print ("Failed to execute tidy.");
	} elsif ($? & 127) {
		printf "child died with signal %d, %s coredump\n",
		($? & 127), ($? & 128) ? 'with' : 'without';
	} elsif ($? != 0) {
		#some error occured
		
		# html/php line offset is 11. I.e., if tidy outputs "276", the line in the .html is "265"
		print "Above file was $File::Find::name and has errors\n\n";
		wait_for_keypress;
	}	
}

#Debug call
#find(\&verifyFile, ("htdocs/contact.php"));

find(\&verifyFile, (STARTDIR));
