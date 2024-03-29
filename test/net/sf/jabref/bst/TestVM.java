package net.sf.jabref.bst;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Vector;

import junit.framework.TestCase;
import net.sf.jabref.BibtexEntry;
import net.sf.jabref.bst.VM;
import net.sf.jabref.bst.VMException;
import net.sf.jabref.bst.VM.BstEntry;
import net.sf.jabref.bst.VM.StackFunction;
import net.sf.jabref.imports.BibtexParser;
import net.sf.jabref.imports.ParserResult;

import org.antlr.runtime.RecognitionException;


public class TestVM extends TestCase {

	public void testAbbrv() throws RecognitionException, IOException {
		VM vm = new VM(new File("src/tests/net/sf/jabref/bst/abbrv.bst"));
		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		v.add(t1BibtexEntry());
		
		String expected = "\\begin{thebibliography}{1}\\bibitem{canh05}K.~Crowston, H.~Annabi, J.~Howison, and C.~Masango.\\newblock Effective work practices for floss development: A model and  propositions.\\newblock In {\\em Hawaii International Conference On System Sciences (HICSS)}, 2005.\\end{thebibliography}";
		
		assertEquals(expected.replaceAll("\\s", ""), vm.run(v).replaceAll("\\s", ""));
	}

	public void testVMSimple() throws RecognitionException, IOException {

		VM vm = new VM("" + "ENTRY  { " + "  address " + "  author " + "  title " + "  type "
			+ "}  {}  { label }" + "INTEGERS { output.state before.all"
			+ " mid.sentence after.sentence after.block }"
			+ "FUNCTION {init.state.consts}{ #0 'before.all := "
			+ " #1 'mid.sentence :=  #2 'after.sentence :=  #3 'after.block := } "
			+ "STRINGS { s t } " + "READ");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		v.add(t1BibtexEntry());

		vm.run(v);

		assertEquals(2, vm.getStrings().size());
		assertEquals(7, vm.getIntegers().size());
		assertEquals(1, vm.getEntries().size());
		assertEquals(5, (vm.getEntries().get(0)).getFields().size());
		assertEquals(38, vm.getFunctions().size());

	}

	public void testLabel() throws RecognitionException, IOException {

		VM vm = new VM("" + "ENTRY  { title }  {}  { label } "
			+ "FUNCTION { test } { label #0 = title 'label := #5 label #6 pop$ } " + "READ "
			+ "ITERATE { test }");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		v.add(t1BibtexEntry());

		vm.run(v);

		assertEquals("Effective work practices for floss development: A model and propositions", vm
			.getStack().pop());

	}

	public void testQuote() throws RecognitionException, IOException {

		VM vm = new VM("FUNCTION {a}{ quote$ quote$ * } EXECUTE {a}");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		vm.run(v);

		assertEquals("\"\"", vm.getStack().pop());
	}

	public void testVMFunction1() throws RecognitionException, IOException {

		VM vm = new VM("FUNCTION {init.state.consts}{ #0 'before.all := } ");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		vm.run(v);

		assertEquals(38, vm.getFunctions().size());

		assertTrue(vm.getFunctions().get("init.state.consts") instanceof StackFunction);

		StackFunction fun = (StackFunction) vm.getFunctions().get("init.state.consts");
		assertEquals(3, fun.getTree().getChildCount());
	}

	public void testVMExecuteSimple() throws RecognitionException, IOException {

		{
			VM vm = new VM("" + "INTEGERS { variable.a } "
				+ "FUNCTION {init.state.consts}{ #5 'variable.a := } "
				+ "EXECUTE {init.state.consts}");

			Vector<BibtexEntry> v = new Vector<BibtexEntry>();
			vm.run(v);
			assertEquals(new Integer(5), vm.getIntegers().get("variable.a"));
		}
		{
			VM vm = new VM("FUNCTION {a}{ #5 #5 = " + "#1 #2 = " + "#3 #4 < " + "#4 #3 < "
				+ "#4 #4 < " + "#3 #4 > " + "#4 #3 > " + "#4 #4 > " + "\"H\" \"H\" = "
				+ "\"H\" \"Ha\" = } " + "EXECUTE {a}");

			Vector<BibtexEntry> v = new Vector<BibtexEntry>();
			vm.run(v);

			assertEquals(VM.FALSE, vm.getStack().pop());
			assertEquals(VM.TRUE, vm.getStack().pop());
			assertEquals(VM.FALSE, vm.getStack().pop());
			assertEquals(VM.TRUE, vm.getStack().pop());
			assertEquals(VM.FALSE, vm.getStack().pop());
			assertEquals(VM.FALSE, vm.getStack().pop());
			assertEquals(VM.FALSE, vm.getStack().pop());
			assertEquals(VM.TRUE, vm.getStack().pop());
			assertEquals(VM.FALSE, vm.getStack().pop());
			assertEquals(VM.TRUE, vm.getStack().pop());
			assertEquals(0, vm.getStack().size());
		}
	}

	public void testVMIfSkipPop() throws RecognitionException, IOException {
		VM vm = new VM("FUNCTION {not}	{   { #0 }	    { #1 }  if$	}"
			+ "FUNCTION {and}	{   'skip$	    { pop$ #0 }	  if$	}"
			+ "FUNCTION {or}	{   { pop$ #1 }	    'skip$	  if$	}" + "FUNCTION {test} { "
			+ "#1 #1 and #0 #1 and #1 #0 and #0 #0 and " + "#0 not #1 not "
			+ "#1 #1 or #0 #1 or #1 #0 or #0 #0 or }" + "EXECUTE {test}");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		vm.run(v);
		assertEquals(VM.FALSE, vm.getStack().pop());
		assertEquals(VM.TRUE, vm.getStack().pop());
		assertEquals(VM.TRUE, vm.getStack().pop());
		assertEquals(VM.TRUE, vm.getStack().pop());
		assertEquals(VM.FALSE, vm.getStack().pop());
		assertEquals(VM.TRUE, vm.getStack().pop());
		assertEquals(VM.FALSE, vm.getStack().pop());
		assertEquals(VM.FALSE, vm.getStack().pop());
		assertEquals(VM.FALSE, vm.getStack().pop());
		assertEquals(VM.TRUE, vm.getStack().pop());
		assertEquals(0, vm.getStack().size());
	}

	public void testVMArithmetic() throws RecognitionException, IOException {
		{
			VM vm = new VM("FUNCTION {test} { " + "#1 #1 + #5 #2 - }" + "EXECUTE {test}");

			Vector<BibtexEntry> v = new Vector<BibtexEntry>();
			vm.run(v);
			assertEquals(new Integer(3), vm.getStack().pop());
			assertEquals(new Integer(2), vm.getStack().pop());
			assertEquals(0, vm.getStack().size());
		}
		{
			VM vm = new VM("FUNCTION {test} { " + "#1 \"HELLO\" + #5 #2 - }" + "EXECUTE {test}");

			Vector<BibtexEntry> v = new Vector<BibtexEntry>();

			try {
				vm.run(v);
				fail();
			} catch (VMException e) {

			}
		}
	}

	public void testNumNames() throws RecognitionException, IOException {
		{
			VM vm = new VM("FUNCTION {test} { \"Johnny Foo and Mary Bar\" num.names$ }"
				+ "EXECUTE {test}");

			Vector<BibtexEntry> v = new Vector<BibtexEntry>();
			vm.run(v);
			assertEquals(new Integer(2), vm.getStack().pop());
			assertEquals(0, vm.getStack().size());
		}
		{
			VM vm = new VM("FUNCTION {test} { \"Johnny Foo { and } Mary Bar\" num.names$ }"
				+ "EXECUTE {test}");

			Vector<BibtexEntry> v = new Vector<BibtexEntry>();
			vm.run(v);
			assertEquals(new Integer(1), vm.getStack().pop());
			assertEquals(0, vm.getStack().size());
		}
	}

	public void testVMStringOps1() throws RecognitionException, IOException {
		{
			VM vm = new VM(
				"FUNCTION {test} { \"H\" \"allo\" * \"Johnny\" add.period$ \"Johnny.\" add.period$"
					+ "\"Johnny!\" add.period$ \"Johnny?\" add.period$ \"Johnny} }}}\" add.period$"
					+ "\"Johnny!}\" add.period$ \"Johnny?}\" add.period$ \"Johnny.}\" add.period$ }"
					+ "EXECUTE {test}");

			Vector<BibtexEntry> v = new Vector<BibtexEntry>();
			vm.run(v);
			assertEquals("Johnny.}", vm.getStack().pop());
			assertEquals("Johnny?}", vm.getStack().pop());
			assertEquals("Johnny!}", vm.getStack().pop());
			assertEquals("Johnny.}", vm.getStack().pop());
			assertEquals("Johnny?", vm.getStack().pop());
			assertEquals("Johnny!", vm.getStack().pop());
			assertEquals("Johnny.", vm.getStack().pop());
			assertEquals("Johnny.", vm.getStack().pop());
			assertEquals("Hallo", vm.getStack().pop());
			assertEquals(0, vm.getStack().size());
		}
	}

	public void testSubstring() throws RecognitionException, IOException {
		VM vm = new VM("FUNCTION {test} " + "{ \"123456789\" #2  #1  substring$ " + // 2
			"  \"123456789\" #4 global.max$ substring$ " + // 456789
			"  \"123456789\" #1  #9  substring$ " + // 123456789
			"  \"123456789\" #1  #10 substring$ " + // 123456789
			"  \"123456789\" #1  #99 substring$ " + // 123456789

			"  \"123456789\" #-7 #3  substring$ " + // 123
			"  \"123456789\" #-1 #1  substring$ " + // 9
			"  \"123456789\" #-1 #3  substring$ " + // 789
			"  \"123456789\" #-2 #2  substring$ " + // 78

			"} EXECUTE {test} ");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		vm.run(v);
		assertEquals("78", vm.getStack().pop());
		assertEquals("789", vm.getStack().pop());
		assertEquals("9", vm.getStack().pop());
		assertEquals("123", vm.getStack().pop());

		assertEquals("123456789", vm.getStack().pop());
		assertEquals("123456789", vm.getStack().pop());
		assertEquals("123456789", vm.getStack().pop());
		assertEquals("456789", vm.getStack().pop());
		assertEquals("2", vm.getStack().pop());
		assertEquals(0, vm.getStack().size());
	}

	public void testEmpty() throws RecognitionException, IOException {
		VM vm = new VM("ENTRY {title}{}{} READ STRINGS { s } FUNCTION {test} " + "{ s empty$ " + // FALSE
			"\"\" empty$ " + // FALSE
			"\"   \" empty$ " + // FALSE
			" title empty$ " + // FALSE
			" \" HALLO \" empty$ } ITERATE {test} ");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		v.add(bibtexString2BibtexEntry("@article{a, author=\"AAA\"}"));
		vm.run(v);
		assertEquals(VM.FALSE, vm.getStack().pop());
		assertEquals(VM.TRUE, vm.getStack().pop());
		assertEquals(VM.TRUE, vm.getStack().pop());
		assertEquals(VM.TRUE, vm.getStack().pop());
		assertEquals(VM.TRUE, vm.getStack().pop());
		assertEquals(0, vm.getStack().size());
	}

	public void testDuplicateEmptyPopSwapIf() throws RecognitionException, IOException {
		VM vm = new VM("FUNCTION {emphasize} " + "{ duplicate$ empty$ " + "  { pop$ \"\" } "
			+ "  { \"{\\em \" swap$ * \"}\" * } " + "  if$ " + "} " + "FUNCTION {test} {"
			+ "  \"\" emphasize " + "  \"Hello\" emphasize " + "}" + "EXECUTE {test} ");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		vm.run(v);
		assertEquals("{\\em Hello}", vm.getStack().pop());
		assertEquals("", vm.getStack().pop());
		assertEquals(0, vm.getStack().size());
	}

	public void testChangeCase() throws RecognitionException, IOException {
		VM vm = new VM(
			"STRINGS { title } "
				+ "READ "
				+ "FUNCTION {format.title}"
				+ " { duplicate$ empty$ "
				+ "    { pop$ \"\" } "
				+ "    { \"t\" change.case$ } "
				+ "  if$ "
				+ "} "
				+ "FUNCTION {test} {"
				+ "  \"hello world\" \"u\" change.case$ format.title "
				+ "  \"Hello World\" format.title "
				+ "  \"\" format.title "
				+ "  \"{A}{D}/{C}ycle: {I}{B}{M}'s {F}ramework for {A}pplication {D}evelopment and {C}ase\" \"u\" change.case$ format.title "
				+ "}" + "EXECUTE {test} ");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		vm.run(v);
		assertEquals(
			"{A}{D}/{C}ycle: {I}{B}{M}'s {F}ramework for {A}pplication {D}evelopment and {C}ase",
			vm.getStack().pop());
		assertEquals("", vm.getStack().pop());
		assertEquals("Hello world", vm.getStack().pop());
		assertEquals("Hello world", vm.getStack().pop());
		assertEquals(0, vm.getStack().size());
	}

	public void testTextLength() throws RecognitionException, IOException {
		VM vm = new VM("FUNCTION {test} {" + "  \"hello world\" text.length$ "
			+ "  \"Hello {W}orld\" text.length$ " + "  \"\" text.length$ "
			+ "  \"{A}{D}/{Cycle}\" text.length$ "
			+ "  \"{\\This is one character}\" text.length$ "
			+ "  \"{\\This {is} {one} {c{h}}aracter as well}\" text.length$ "
			+ "  \"{\\And this too\" text.length$ " + "  \"These are {\\11}\" text.length$ " + "} "
			+ "EXECUTE {test} ");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		vm.run(v);
		assertEquals(new Integer(11), vm.getStack().pop());
		assertEquals(new Integer(1), vm.getStack().pop());
		assertEquals(new Integer(1), vm.getStack().pop());
		assertEquals(new Integer(1), vm.getStack().pop());
		assertEquals(new Integer(8), vm.getStack().pop());
		assertEquals(new Integer(0), vm.getStack().pop());
		assertEquals(new Integer(11), vm.getStack().pop());
		assertEquals(new Integer(11), vm.getStack().pop());
		assertEquals(0, vm.getStack().size());
	}

	public void testVMIntToStr() throws RecognitionException, IOException {
		VM vm = new VM("FUNCTION {test} { #3 int.to.str$ #9999 int.to.str$}" + "EXECUTE {test}");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		vm.run(v);
		assertEquals("9999", (String) vm.getStack().pop());
		assertEquals("3", vm.getStack().pop());
		assertEquals(0, vm.getStack().size());
	}

	public void testVMChrToInt() throws RecognitionException, IOException {
		{
			VM vm = new VM("FUNCTION {test} { \"H\" chr.to.int$ }" + "EXECUTE {test}");

			Vector<BibtexEntry> v = new Vector<BibtexEntry>();
			vm.run(v);
			assertEquals(new Integer(72), vm.getStack().pop());
			assertEquals(0, vm.getStack().size());
		}
		{
			VM vm = new VM("FUNCTION {test} { \"H\" chr.to.int$ int.to.chr$ }" + "EXECUTE {test}");

			Vector<BibtexEntry> v = new Vector<BibtexEntry>();
			vm.run(v);
			assertEquals("H", vm.getStack().pop());
			assertEquals(0, vm.getStack().size());
		}
	}

	public void testSort() throws RecognitionException, IOException {

		VM vm = new VM("" + "ENTRY  { title }  { }  { label }"
			+ "FUNCTION {presort} { cite$ 'sort.key$ := } ITERATE { presort } SORT");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		v.add(bibtexString2BibtexEntry("@article{a, author=\"AAA\"}"));
		v.add(bibtexString2BibtexEntry("@article{b, author=\"BBB\"}"));
		v.add(bibtexString2BibtexEntry("@article{d, author=\"DDD\"}"));
		v.add(bibtexString2BibtexEntry("@article{c, author=\"CCC\"}"));
		vm.run(v);

		Vector<BstEntry> v2 = vm.getEntries();
		assertEquals("a", v2.get(0).getBibtexEntry().getCiteKey());
		assertEquals("b", v2.get(1).getBibtexEntry().getCiteKey());
		assertEquals("c", v2.get(2).getBibtexEntry().getCiteKey());
		assertEquals("d", v2.get(3).getBibtexEntry().getCiteKey());
	}

	public void testBuildIn() throws RecognitionException, IOException {
		VM vm = new VM("EXECUTE {global.max$}");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		vm.run(v);

		assertEquals(new Integer(Integer.MAX_VALUE), vm.getStack().pop());
		assertTrue(vm.getStack().empty());
	}

	public void testVariables() throws RecognitionException, IOException {

		VM vm = new VM(" STRINGS { t }                          "
			+ " FUNCTION {not}	{ { #0 } { #1 }  if$ } "
			+ " FUNCTION {n.dashify} { \"HELLO-WORLD\" 't := t empty$ not } "
			+ " EXECUTE {n.dashify}                    ");

		vm.run(new Vector<BibtexEntry>());

		assertEquals(VM.TRUE, vm.getStack().pop());
	}

	public void testWhile() throws RecognitionException, IOException {

		VM vm = new VM(
			"STRINGS { t }            "
				+ "FUNCTION {not}	{   "
				+ " { #0 } { #1 }  if$ } "
				+ "FUNCTION {n.dashify}              "
				+ "{ \"HELLO-WORLD\"                 "
				+ "  't :=                           "
				+ " \"\"                                                 "
				+ "	   { t empty$ not }                 "
				+ "	   { t #1 #1 substring$ \"-\" =                      "
				+ "	     { t #1 #2 substring$ \"--\" = not "
				+ "	          { \"--\" *                                       "
				+ "	            t #2 global.max$ substring$ 't :=                 "
				+ "	          }                                                    "
				+ "	          {   { t #1 #1 substring$ \"-\" = }                "
				+ "	              { \"-\" *                                         "
				+ "	                t #2 global.max$ substring$ 't :=               "
				+ "	              }                                                  "
				+ "	            while$                                                                  "
				+ "	          }                                                                  "
				+ "	        if$                                                                  "
				+ "	      }                                                                  "
				+ "	      { t #1 #1 substring$ *                                       "
				+ "	        t #2 global.max$ substring$ 't :=                          "
				+ "	      }                                                                  "
				+ "	      if$                                                                  "
				+ "	    }                                                                  "
				+ "	  while$                                                                  "
				+ "	}                                                                  "
				+ " EXECUTE {n.dashify} ");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		vm.run(v);

		assertEquals(1, vm.getStack().size());
		assertEquals("HELLO--WORLD", vm.getStack().pop());
	}

	public void testType() throws RecognitionException, IOException {

		VM vm = new VM(
			""
				+ "ENTRY  { title }  { }  { label }"
				+ "FUNCTION {presort} { cite$ 'sort.key$ := } ITERATE { presort } SORT FUNCTION {test} { type$ } ITERATE { test }");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		v.add(bibtexString2BibtexEntry("@article{a, author=\"AAA\"}"));
		v.add(bibtexString2BibtexEntry("@book{b, author=\"BBB\"}"));
		v.add(bibtexString2BibtexEntry("@misc{c, author=\"CCC\"}"));
		v.add(bibtexString2BibtexEntry("@inproceedings{d, author=\"DDD\"}"));
		vm.run(v);

		assertEquals(4, vm.getStack().size());
		assertEquals("Inproceedings", vm.getStack().pop());
		assertEquals("Misc", vm.getStack().pop());
		assertEquals("Book", vm.getStack().pop());
		assertEquals("Article", vm.getStack().pop());
	}

	public void testMissing() throws RecognitionException, IOException {

		VM vm = new VM( // 
			"ENTRY    { title }  { }  { label } " + //
				"FUNCTION {presort} { cite$ 'sort.key$ := } " + //
				"ITERATE  {presort} " + //
				"READ SORT " + //
				"FUNCTION {test}{ title missing$ cite$ } " + //
				"ITERATE  { test }");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		v.add(t1BibtexEntry());
		v.add(bibtexString2BibtexEntry("@article{test, author=\"No title\"}"));
		vm.run(v);

		assertEquals(4, vm.getStack().size());

		assertEquals("test", vm.getStack().pop());
		assertEquals(new Integer(1), vm.getStack().pop());
		assertEquals("canh05", vm.getStack().pop());
		assertEquals(new Integer(0), vm.getStack().pop());
	}

	public void testFormatName() throws RecognitionException, IOException {
		{
			VM vm = new VM(
				"FUNCTION {format}{ \"Charles Louis Xavier Joseph de la Vall{\\'e}e Poussin\" #1 \"{vv~}{ll}{, jj}{, f}?\" format.name$ }"
					+ "EXECUTE {format}");

			Vector<BibtexEntry> v = new Vector<BibtexEntry>();
			vm.run(v);
			assertEquals("de~la Vall{\\'e}e~Poussin, C.~L. X.~J?", (String) vm.getStack().pop());
			assertEquals(0, vm.getStack().size());
		}
		{
			VM vm = new VM("ENTRY  { author }  { }  { label } "
				+ "FUNCTION {presort} { cite$ 'sort.key$ := } " + "ITERATE { presort } " + "READ "
				+ "SORT " + "FUNCTION {format}{ author #2 \"{vv~}{ll}{, jj}{, f}?\" format.name$ }"
				+ "ITERATE {format}");

			Vector<BibtexEntry> v = new Vector<BibtexEntry>();
			v.add(t1BibtexEntry());
			v
				.add(bibtexString2BibtexEntry("@book{test, author=\"Jonathan Meyer and Charles Louis Xavier Joseph de la Vall{\\'e}e Poussin\"}"));
			vm.run(v);
			assertEquals("de~la Vall{\\'e}e~Poussin, C.~L. X.~J?", vm.getStack().pop());
			assertEquals("Annabi, H?", vm.getStack().pop());
			assertEquals(0, vm.getStack().size());
		}
	}

	public void testCallType() throws RecognitionException, IOException {

		VM vm = new VM(
			"ENTRY  { title }  { }  { label } FUNCTION {presort} { cite$ 'sort.key$ := } ITERATE { presort } READ SORT "
				+ "FUNCTION {inproceedings}{ \"InProceedings called on \" title * } "
				+ "FUNCTION {book}{ \"Book called on \" title * } " + " ITERATE { call.type$ }");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		v.add(t1BibtexEntry());
		v.add(bibtexString2BibtexEntry("@book{test, title=\"Test\"}"));
		vm.run(v);

		assertEquals(2, vm.getStack().size());

		assertEquals("Book called on Test", vm.getStack().pop());
		assertEquals(
			"InProceedings called on Effective work practices for floss development: A model and propositions",
			vm.getStack().pop());
		assertEquals(0, vm.getStack().size());
	}

	public void testIterate() throws RecognitionException, IOException {

		VM vm = new VM("" + "ENTRY  { " + "  address " + "  author " + "  title " + "  type "
			+ "}  {}  { label } " + "FUNCTION {test}{ cite$ } " + "READ " + "ITERATE { test }");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		v.add(t1BibtexEntry());

		v.add(bibtexString2BibtexEntry("@article{test, title=\"BLA\"}"));

		vm.run(v);

		assertEquals(2, vm.getStack().size());

		String s1 = (String) vm.getStack().pop();
		String s2 = (String) vm.getStack().pop();

		if (s1.equals("canh05")) {
			assertEquals("test", s2);
		} else {
			assertEquals("canh05", s2);
			assertEquals("test", s1);
		}
	}

	public void testWidth() throws RecognitionException, IOException {

		VM vm = new VM("ENTRY  { " + "  address " + "  author " + "  title " + "  type "
			+ "}  {}  { label } " + // 
			"STRINGS { longest.label } " + //
			"INTEGERS { number.label longest.label.width } " + //
			"FUNCTION {initialize.longest.label} " + //
			"{ \"\" 'longest.label := " + //
			"  #1 'number.label := " + //
			"  #0 'longest.label.width := " + // 
			"} " + //
			" " + //
			"		FUNCTION {longest.label.pass} " + // 
			"		{ number.label int.to.str$ 'label := " + // 
			"		  number.label #1 + 'number.label := " + //
			"		  label width$ longest.label.width > " + //
			"		    { label 'longest.label := " + //
			"		      label width$ 'longest.label.width := " + // 
			"		    } " + //
			"		    'skip$ " + // 
			"		  if$ " + //
			"		} " + //
			" " + //
			"		EXECUTE {initialize.longest.label} " + // 
			" " + //
			"		ITERATE {longest.label.pass} " + //
			"FUNCTION {begin.bib} " + //
			"{ preamble$ empty$" + //
			"    'skip$" + //
			"    { preamble$ write$ newline$ }" + //
			"  if$" + //
			"  \"\\begin{thebibliography}{\"  longest.label  * \"}\" *" + //
			"}" + //
			"EXECUTE {begin.bib}" + //
			"");//

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		v.add(t1BibtexEntry());

		vm.run(v);

		assertTrue(vm.getIntegers().containsKey("longest.label.width"));
		assertEquals("\\begin{thebibliography}{1}", vm.getStack().pop());
	}

	public void testVMSwap() throws RecognitionException, IOException {

		VM vm = new VM("FUNCTION {a}{ #3 \"Hallo\" swap$ } EXECUTE { a }");

		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		vm.run(v);

		assertEquals(2, vm.getStack().size());
		assertEquals(new Integer(3), vm.getStack().pop());
		assertEquals("Hallo", vm.getStack().pop());
	}

	public static BibtexEntry bibtexString2BibtexEntry(String s) throws IOException {
		ParserResult result = BibtexParser.parse(new StringReader(s));
		Collection<BibtexEntry> c = result.getDatabase().getEntries();
		assertEquals(1, c.size());
		return c.iterator().next();
	}

	public static String bibtexEntry2BibtexString(BibtexEntry e) throws IOException {
		StringWriter sw = new StringWriter();
		e.write(sw, new net.sf.jabref.export.LatexFieldFormatter(), false);
		return sw.getBuffer().toString();
	}

	/* TEST DATA */
	public String t1BibtexString() {
		return "@inproceedings{canh05,\n"
			+ "  author = {Crowston, K. and Annabi, H. and Howison, J. and Masango, C.},\n"
			+ "  title = {Effective work practices for floss development: A model and propositions},\n"
			+ "  booktitle = {Hawaii International Conference On System Sciences (HICSS)},\n"
			+ "  year = {2005},\n" + "  owner = {oezbek},\n" + "  timestamp = {2006.05.29},\n"
			+ "  url = {http://james.howison.name/publications.html}}\n";
	}

	public void testHypthenatedName() throws RecognitionException, IOException {
		VM vm = new VM(new File("src/tests/net/sf/jabref/bst/abbrv.bst"));
		Vector<BibtexEntry> v = new Vector<BibtexEntry>();
		v.add(bibtexString2BibtexEntry("@article{canh05, author = \"Jean-Paul Sartre\" }"));
		assertTrue(vm.run(v).indexOf("J.-P. Sartre") != -1);
	}

	public BibtexEntry t1BibtexEntry() throws IOException {
		return bibtexString2BibtexEntry(t1BibtexString());
	}

}
