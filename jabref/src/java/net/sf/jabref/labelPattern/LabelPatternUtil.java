/*
 * Created on 13-Dec-2003
 */
package net.sf.jabref.labelPattern;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.jabref.BibtexDatabase;
import net.sf.jabref.BibtexEntry;
import net.sf.jabref.Globals;
import net.sf.jabref.imports.ImportFormatReader;
import net.sf.jabref.Util;
import net.sf.jabref.export.layout.format.RemoveLatexCommands;

/**
 *
 * @author Ulrik Stervbo (ulriks AT ruc.dk)
 */
/**
 * This is the utility class of the LabelPattern package.
 * @author Ulrik Stervbo (ulriks AT ruc.dk)
 */
public class LabelPatternUtil {
  //this is SO crappy, but i have no idea of converting unicode into a String
  // the content of the AL is build with the buildLetters()
  private static ArrayList letters = builtLetters();
  public static ArrayList DEFAULT_LABELPATTERN;
  static {
      updateDefaultPattern();
  }
  //"[auth][year]");

  private static BibtexDatabase _db;

  public static void updateDefaultPattern() {
      DEFAULT_LABELPATTERN = split(Globals.prefs.get("defaultLabelPattern"));
  }
  
  /**
   * This method takes a string of the form [field1]spacer[field2]spacer[field3]...,
   * where the fields are the (required) fields of a BibTex entry. The string is split
   * into firlds and spacers by recognizing the [ and ].
   *
   * @param keyPattern a <code>String</code>
   * @return an <code>ArrayList</code> The first item of the list
   * is a string representation of the key pattern (the parameter),
   * the second item is the spacer character (a <code>String</code>).
   */
  public static ArrayList split(String labelPattern) {
    // A holder for fields of the entry to be used for the key
    ArrayList _alist = new ArrayList();

    // Before we do anything, we add the parameter to the ArrayLIst
    _alist.add(labelPattern);

    //String[] ss = labelPattern.split("\\[|\\]");
    StringTokenizer tok = new StringTokenizer(labelPattern, "[]", true);
    while (tok.hasMoreTokens()) {
      _alist.add(tok.nextToken());

    }
    return _alist;

    /*
       // Regular expresion for identifying the fields
       Pattern pi = Pattern.compile("\\[\\w*\\]");
       // Regular expresion for identifying the spacer
       Pattern ps = Pattern.compile("\\].()*\\[");

       // The matcher for the field
       Matcher mi = pi.matcher(labelPattern);
       // The matcher for the spacer char
       Matcher ms = ps.matcher(labelPattern);

       // Before we do anything, we add the parameter to the ArrayLIst
       _alist.add(labelPattern);

       // If we can find the spacer character
       if(ms.find()){
     String t_spacer = ms.group();
      // Remove the `]' and `[' at the ends
      // We cant imagine a spacer of omre than one character.
      t_spacer = t_spacer.substring(1,2);
      _alist.add(t_spacer);
       }

       while(mi.find()){
     // Get the matched string
     String t_str = mi.group();
      int _sindex = 1;
      int _eindex = t_str.length() -1;
      // Remove the `[' and `]' at the ends
      t_str = t_str.substring(_sindex, _eindex);
     _alist.add(t_str);
       }

       return _alist;*/
  }

  /**
   * Generates a BibTeX label according to the pattern for a given entry type, and
   * returns the <code>Bibtexentry</code> with the unique label.
   * @param table a <code>LabelPattern</code>
   * @param database a <code>BibtexDatabase</code>
   * @param entryId a <code>String</code>
   * @return modified Bibtexentry
   */
  public static BibtexEntry makeLabel(LabelPattern table,
                                      BibtexDatabase database,
                                      BibtexEntry _entry) {
    _db = database;
    ArrayList _al;
    String _spacer, _label;
    StringBuffer _sb = new StringBuffer();
    boolean forceUpper = false, forceLower = false;

    try {
      // get the type of entry
      String _type = _entry.getType().getName().toLowerCase();
      // Get the arrayList corrosponding to the type
      _al = table.getValue(_type);
      int _alSize = _al.size();
      boolean field = false;
      for (int i = 1; i < _alSize; i++) {
        String val = _al.get(i).toString();
        if (val.equals("[")) {
          field = true;
        }
        else if (val.equals("]")) {
          field = false;
        }
        else if (field) {
	    /* Edited by Seb Wills <saw27@mrao.cam.ac.uk> on 13-Apr-2004
	       Added new pseudo-fields "shortyear" and "veryshorttitle", and
	       and ":lower" modifier for all fields (in a way easily extended to other modifiers).
	       Helpfile help/LabelPatterns.html updated accordingly.
	    */
	    // check whether there is a modifier on the end such as ":lower"
	    String modifier = null;
	    int _mi = val.indexOf(":");
	    if(_mi != -1 && _mi != val.length()-1 && _mi != 0) { // ":" is in val and isn't first or last character
		modifier=val.substring(_mi+1);
		val=val.substring(0,_mi);
	    }
	    StringBuffer _sbvalue = new StringBuffer();

	    try {
		/*if (val.equals("uppercase")) {
		    forceUpper = true;
		}
		else if (val.equals("lowercase")) {
		    forceLower = true;
		    }*/
                if (val.startsWith("auth")) {
                  // Gather all author-related checks, so we don't have to check all all the time.
                  if (val.equals("auth")) {
                    _sbvalue.append(firstAuthor(_entry.getField("author").toString()));
                  }
                  else if (val.equals("authors")) {
                    _sbvalue.append(allAuthors(_entry.getField("author").toString()));
                  }
                  else if (val.equals("authorIni")) {
                    _sbvalue.append(oneAuthorPlusIni(_entry.getField("author").toString()));
                  }
                  else if (val.matches("authIni[\\d]+")) {
                    int num = Integer.parseInt(val.substring(7));
					_sbvalue.append(authIniN(_entry.getField("author").toString(),num));
                  }
				  else if (val.equals("auth.auth.ea")) {
                    _sbvalue.append(authAuthEa(_entry.getField("author").toString()));
                  }
                  else if (val.matches("auth[\\d]+_[\\d]+")) {
                    String[] nums = val.substring(4).split("_");
                    _sbvalue.append(authN_M(_entry.getField("author").toString(),
                                            Integer.parseInt(nums[0]),
                                            Integer.parseInt(nums[1]) - 1));
                  }
                  // authN.  First N chars of the first author's last name.
                  else if (val.matches("auth\\d+")) {
                    int num = Integer.parseInt(val.substring(4));
                    String fa = firstAuthor(_entry.getField("author").toString());
                    if ( num > fa.length() )
                      num = fa.length();
                    _sbvalue.append(fa.substring(0,num));
                  }

                  else {
                    // This "auth" business was a dead end, so just use it literally:
                    _sbvalue.append(_entry.getField(val).toString());
                  }
                }
                else if (val.startsWith("ed")) {
                  // Gather all markers starting with "ed" here, so we don't have to check all all the time.
                  if (val.equals("edtr")) {
                    _sbvalue.append(firstAuthor(_entry.getField("editor").toString()));
                  }
                  else if (val.equals("editors")) {
                    _sbvalue.append(allAuthors(_entry.getField("editor").toString()));
                  }
                  else if (val.equals("editorIni")) {
                      _sbvalue.append(oneAuthorPlusIni(_entry.getField("editor").toString()));
                  }
                  else if (val.matches("edtrIni[\\d]+")) {
                    int num = Integer.parseInt(val.substring(7));
					_sbvalue.append(authIniN(_entry.getField("editor").toString(),num));
                  }
                  else if (val.matches("edtr[\\d]+_[\\d]+")) {
                    String[] nums = val.substring(4).split("_");
                    _sbvalue.append(authN_M(_entry.getField("editor").toString(), Integer.parseInt(nums[0]),
                                            Integer.parseInt(nums[1])-1));
                  }
                  else if (val.equals("edtr.edtr.ea")) {
                    _sbvalue.append(authAuthEa(_entry.getField("editor").toString()));
                  }
                  // authN.  First N chars of the first author's last name.
                  else if (val.matches("edtr\\d+")) {
                    int num = Integer.parseInt(val.substring(4));
                    String fa = firstAuthor(_entry.getField("editor").toString());
                    if ( num > fa.length() )
                      num = fa.length();
                    _sbvalue.append(fa.substring(0,num));
                  }
                  else {
                    // This "ed" business was a dead end, so just use it literally:
                    _sbvalue.append(_entry.getField(val).toString());
                  }
                }
                else if (val.equals("firstpage")) {
                  _sbvalue.append(firstPage(_entry.getField("pages").toString()));
                }
                else if (val.equals("lastpage")) {
                  _sbvalue.append(lastPage(_entry.getField("pages").toString()));
                }
                else if (val.equals("shorttitle")) {
                  _sbvalue.append(getTitleWords(3, _entry));
                }
                else if (val.equals("shortyear")) {
                  String ss = _entry.getField("year").toString();
                  if (ss.length() > 2) {
                    _sbvalue.append(ss.substring(ss.length() - 2));
                  }
                  else {
                    _sbvalue.append(ss);
                  }
                }

                else if(val.equals("veryshorttitle")) {
                  _sbvalue.append(getTitleWords(1, _entry));
                }

		// we havent seen any special demands
		else {
                  _sbvalue.append(_entry.getField(val).toString());
		}
	    }
	    catch (Exception ex) {
		Globals.logger("Key generator warning: field '" + val + "' empty.");
	    }
	    // apply modifier if present
	    if(modifier != null) {
		if(modifier.equals("lower")) {
		    _sb.append(_sbvalue.toString().toLowerCase());
		}
		else {
		    Globals.logger("Key generator warning: unknown modifier '"+modifier+"'.");
		}
	    } else {
		// no modifier
		_sb.append(_sbvalue);
	    }

        }
        else {
          _sb.append(val);
        }
      }
    }

    catch (Exception e) {
      System.err.println(e);
    }

    /**
     * Edited by Morten Alver 2004.02.04.
     *
     * We now have a system for easing key duplicate prevention, so
     * I am changing this method to conform to it.
     *

        // here we make sure the key is unique
       _label = makeLabelUnique(_sb.toString());
       _entry.setField(Globals.KEY_FIELD, _label);
       return _entry;
     */

    // Remove all illegal characters from the key.
    _label = Util.checkLegalKey(_sb.toString());

    if (forceUpper) {
      _label = _label.toUpperCase();
    }
    if (forceLower) {
      _label = _label.toLowerCase();
    }
      
    
    String oldKey = _entry.getCiteKey();
    int occurences = _db.getNumberOfKeyOccurences(_label);
    if ((oldKey != null) && oldKey.equals(_label))
        occurences--; // No change, so we can accept one dupe.
    
    // Try new keys until we get a unique one:
    //if (_db.setCiteKeyForEntry(_entry.getId(), _label)) {
    
    if (occurences == 0) {
        // No dupes found, so we can just go ahead.
        if (!_label.equals(oldKey))
            _db.setCiteKeyForEntry(_entry.getId(), _label);
    }
    else {

        // The key is already in use, so we must modify it.
        char c = 'b';
        String modKey = _label + "a";
        occurences = _db.getNumberOfKeyOccurences(modKey);
        if ((oldKey != null) && oldKey.equals(modKey))
            occurences--;
        //while (_db.setCiteKeyForEntry(_entry.getId(), modKey)) {
        while (occurences > 0) {
            modKey = _label + ( (char) (c++));

            occurences = _db.getNumberOfKeyOccurences(modKey);
            if ((oldKey != null) && oldKey.equals(modKey))
                occurences--;
        }

        if (!modKey.equals(oldKey))  {
            System.out.println("LabelPatternUtil: "+modKey);
            System.out.println(_db.setCiteKeyForEntry(_entry.getId(), modKey));
        }
    }

    return _entry;
    /** End of edit, Morten Alver 2004.02.04.  */

  }


  static String getTitleWords(int number, BibtexEntry _entry) {
    String ss = (new RemoveLatexCommands()).format(_entry.getField("title").toString());
    StringBuffer _sbvalue = new StringBuffer(),
        current;
    int piv=0, words = 0;

    // sorry for being English-centric. I guess these
    // words should really be an editable preference.
    mainl: while ((piv < ss.length()) && (words < number)) {
      current = new StringBuffer();
      // Get the next word:
      while ((piv<ss.length()) && !Character.isWhitespace(ss.charAt(piv))) {
        current.append(ss.charAt(piv));
        piv++;
        //System.out.println(".. "+piv+" '"+current.toString()+"'");
      }
      piv++;
      // Check if it is ok:
      String word = current.toString().trim();
      if (word.length() == 0)
        continue mainl;
      for(int _i=0; _i< Globals.SKIP_WORDS.length; _i++) {
        if (word.equalsIgnoreCase(Globals.SKIP_WORDS[_i])) {
          continue mainl;
        }
      }

      // If we get here, the word was accepted.
      if (_sbvalue.length() > 0)
        _sbvalue.append(" ");
      _sbvalue.append(word);
      words++;
    }

    return _sbvalue.toString();
  }

  /**
   * This method returns a truely unique label (in the BibtexDatabase), by taking a
   * label and add the letters a-z until a unique key is found.
   * @param key a <code>String</code>
   * @return a unique label
   */
  public static String makeLabelUnique(String label) {
    // First I tried to make this recursive, but had to give up. I needed to
    // do too many chacks of different kinds.
    String _orgLabel = label;
    String _newLabel = label;
    int lettersSize = letters.size();

    for (int i = 0; i < lettersSize; i++) {
      if (isLabelUnique(_newLabel)) {
        // Hurray! the key is unique! lets get outta here
        break;
      }
      else {
        // though luck! lets add a new letter...
        _newLabel = _orgLabel + letters.get(i).toString();
      }
    }
    return _newLabel;

  }

  /**
   * Tests whether a given label is unique.
   * @param label a <code>String</code>
   * @return <code>true</code> if and only if the <code>label</code> is unique
   */
  public static boolean isLabelUnique(String label) {
    boolean _isUnique = true;
    BibtexEntry _entry;
    int _dbSize = _db.getEntryCount();
    // run through the whole DB and check the key field
    // if this could be made recursive I would be very happy
    // it kinda sux that we have to run through the whole db.
    // The idea here is that if we meet NO match, the _duplicate
    // field will be true

    for (int i = 0; i < _dbSize; i++) {
      _entry = _db.getEntryById(String.valueOf(i));

      // oh my! there is a match! we better set the uniqueness to false
      // and leave this for-loop all together
      if (_entry.getField(Globals.KEY_FIELD).equals(label)) {
        _isUnique = false;
        break;
      }
    }

    return _isUnique;

  }

  /**
   * Gets the last name of the first author/editor
   * @param authorField a <code>String</code>
   * @return the sur name of an author/editor
   */
  private static String firstAuthor(String authorField) {
    String author = "";
    // This code was part of 'ApplyRule' in 'ArticleLabelRule'
    String[] tokens = ImportFormatReader.fixAuthor_lastnameFirst(authorField).split("\\band\\b");

    if (tokens.length > 0) { // if author is empty
      String[] firstAuthor = tokens[0].replaceAll("\\s+", " ").split(" ");
      author += firstAuthor[0];

    }
    return author;
  }

  /**
   * Gets the last name of all authors/editors
   * @param authorField a <code>String</code>
   * @return the sur name of all authors/editors
   */
  private static String allAuthors(String authorField) {
    String author = "";
    // This code was part of 'ApplyRule' in 'ArticleLabelRule'
    String[] tokens = ImportFormatReader.fixAuthor_lastnameFirst(authorField).split("\\band\\b");
    int i = 0;
    while (tokens.length > i) {
      // convert lastname, firstname to firstname lastname
      String[] firstAuthor = tokens[i].replaceAll("\\s+", " ").trim().split(" ");
      // lastname, firstname
      author += firstAuthor[0];
      i++;
    }
    return author;
  }

  /**
   * Gets the first part of the last name of the first
   * author/editor, and appends the last name initial of the
   * remaining authors/editors.
   * @param authorField a <code>String</code>
   * @return the sur name of all authors/editors
   */
  private static String oneAuthorPlusIni(String authorField) {
    final int CHARS_OF_FIRST = 5;
    authorField = ImportFormatReader.fixAuthor_lastnameFirst(authorField);
    String author = "";
    // This code was part of 'ApplyRule' in 'ArticleLabelRule'
    String[] tokens = authorField.split("\\band\\b");
    int i = 1;
    if (tokens.length == 0) {
      return author;
    }
    String[] firstAuthor = tokens[0].replaceAll("\\s+", " ").split(" ");
    author = firstAuthor[0].substring(0,
                                      (int) Math.min(CHARS_OF_FIRST,
        firstAuthor[0].length()));
    while (tokens.length > i) {
      // convert lastname, firstname to firstname lastname
      author += tokens[i].trim().charAt(0);
      i++;
    }
    return author;

  }

  /**
   * auth.auth.ea format:
   * Isaac Newton and James Maxwell and Albert Einstein (1960)
   * Isaac Newton and James Maxwell (1960)
   *  give:
   * Newton.Maxwell.ea
   * Newton.Maxwell
   */
  private static String authAuthEa(String authorField) {
    authorField = ImportFormatReader.fixAuthor_lastnameFirst(authorField);
    StringBuffer author = new StringBuffer();

    String[] tokens = authorField.split("\\band\\b");
    if (tokens.length == 0) {
      return "";
    }
    author.append((tokens[0].split(","))[0]);
    if (tokens.length >= 2)
      author.append("."+(tokens[1].split(","))[0]);
    if (tokens.length > 2)
      author.append(".ea");

    return author.toString();
  }

  /**
   * The first N characters of the Mth author/editor.
   */
  private static String authN_M(String authorField, int n, int m) {
    authorField = ImportFormatReader.fixAuthor_lastnameFirst(authorField);
    StringBuffer author = new StringBuffer();

    String[] tokens = authorField.split("\\band\\b");
    if ((tokens.length <= m) || (n<0) || (m<0)) {
      return "";
    }
    String lastName = (tokens[m].split(","))[0].trim();
    //System.out.println(lastName);
    if (lastName.length() <= n)
      return lastName;
    else
      return lastName.substring(0, n);
  }


  /**
   * authIniN format:
   * Each author gets (N div #authors) chars, the remaining
   * (N mod #authors) chars are equally distributed to the
   * authors first in the row.
   * If (N < #authors), only the fist N authors get mentioned.
   * a) I. Newton and J. Maxwell and A. Einstein and N. Bohr (..)
   * b) I. Newton and J. Maxwell and A. Einstein 
   * c) I. Newton and J. Maxwell 
   * d) I. Newton 
   * E.g. authIni4 gives: a) NMEB, b) NeME, c) NeMa, d) Newt
   */
  private static String authIniN(String authorField, int n) {
    authorField = ImportFormatReader.fixAuthor_lastnameFirst(authorField);
    StringBuffer author = new StringBuffer();
    String[] tokens = authorField.split("\\band\\b");
    int i = 0;
    int charsAll = n / tokens.length;
    
    if (tokens.length == 0) {
      return author.toString();
    }

    while (tokens.length > i) {
      if ( i < (n % tokens.length) ) {
        author.append(authN_M(authorField,charsAll+1,i));
      } else {
        author.append(authN_M(authorField,charsAll,i));
      }
      i++;
    }

    if (author.length() <= n)
      return author.toString();
    else
      return author.toString().substring(0, n);
  }

	
  /**
   * Split the pages field into two and return the first one
   * @param pages a <code>String</code>
   * @return the first page number
   */
  private static String firstPage(String pages) {
    String[] _pages = pages.split("-");
    return _pages[0];
  }

  /**
   * Split the pages field into two and return the last one
   * @param pages a <code>String</code>
   * @return the last page number
   */
  private static String lastPage(String pages) {
    String[] _pages = pages.split("-");
    return _pages[1];
  }

  /**
   * I <b>HATE</b> this method!! I looked and looked but couldn't find a way to
   * turn 61 (or in real unicode 0061) into the letter 'a' - crap!
   * @return an <code>ArrayList</code> which shouldn't be!!
   */
  private static ArrayList builtLetters() {
    ArrayList _letters = new ArrayList();
    _letters.add("a");
    _letters.add("b");
    _letters.add("c");
    _letters.add("d");
    _letters.add("e");
    _letters.add("f");
    _letters.add("g");
    _letters.add("h");
    _letters.add("i");
    _letters.add("j");
    _letters.add("k");
    _letters.add("l");
    _letters.add("m");
    _letters.add("n");
    _letters.add("o");
    _letters.add("p");
    _letters.add("q");
    _letters.add("r");
    _letters.add("s");
    _letters.add("t");
    _letters.add("u");
    _letters.add("v");
    _letters.add("x");
    _letters.add("y");
    _letters.add("z");

    return _letters;
  }
}
