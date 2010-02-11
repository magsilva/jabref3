#!/bin/bash

# Create binary distribution:
mkdir jabref-oo-$1
cp example_style_file.jstyle jabref-oo-$1/
cp CHANGELOG jabref-oo-$1/
cp LICENSE jabref-oo-$1/
cp gpl-2.0.txt jabref-oo-$1/
cp lib/JabRef*.jar jabref-oo-$1
mkdir jabref-oo-$1/plugins
ant clean jar
mv dist/plugins/net.sf.jabref.oo.ooplugin-$1.jar jabref-oo-$1/plugins/
zip -r JabRef-oo-$1.zip jabref-oo-$1
rm -rf jabref-oo-$1

# Create source distribution:
mkdir jabref-oo-$1-src
mkdir jabref-oo-$1-src/dist
cp *.xml jabref-oo-$1-src/
cp *.html jabref-oo-$1-src/
cp example_style_file.jstyle jabref-oo-$1-src/
cp CHANGELOG jabref-oo-$1-src/
cp LICENSE jabref-oo-$1-src/
cp gpl-2.0 jabref-oo-$1-src/
cp -r lib jabref-oo-$1-src/
cp -r net jabref-oo-$1-src/
cp -r images jabref-oo-$1-src/
zip -r JabRef-oo-$1-src.zip jabref-oo-$1-src
rm -rf jabref-oo-$1-src
