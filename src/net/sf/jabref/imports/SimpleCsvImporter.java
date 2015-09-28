package net.sf.jabref.imports;

import java.io.*;
import java.util.*;

import net.sf.jabref.*;
import net.sf.jabref.imports.ImportFormat;
import net.sf.jabref.imports.ImportFormatReader;

public class SimpleCsvImporter extends ImportFormat {

  public String getFormatName() {
    return "Simple CSV Importer";
  }

  public boolean isRecognizedFormat(InputStream stream) throws IOException {
    return true; // this is discouraged except for demonstration purposes
  }
  
  public List<BibtexEntry> importEntries(InputStream stream, OutputPrinter status) throws IOException {    
    List<BibtexEntry> bibitems = new ArrayList<BibtexEntry>();
    BufferedReader in = new BufferedReader(ImportFormatReader.getReaderDefaultEncoding(stream));
      
    String line = in.readLine();
    while (line != null) {
      if (!"".equals(line.trim())) {
        String[] fields = line.split(";");
        BibtexEntry be = new BibtexEntry(BibtexEntryType.TECHREPORT);
        be.setField("year", fields[0]);
        be.setField("author", fields[1]);
        be.setField("title", fields[2]);
        bibitems.add(be);
        line = in.readLine();
      }     
    }
    return bibitems;      
  }
}
