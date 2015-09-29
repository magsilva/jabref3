package net.sf.jabref.imports;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FieldContentParserTest
{
	private FieldContentParser parser;
	
	@Before
	public void setUp() {
		parser = new FieldContentParser();
	}

	@Test
	public void testFormatStringBuilderString() {
		StringBuilder bf = new StringBuilder("Mapping approach for model transformation of MDA based on xUML");
		String expectedResult = "Mapping approach for model transformation of MDA based on xUML";
		bf = parser.format(bf, "title");
		assertEquals(expectedResult, bf.toString());
	}
	
	@Test
	public void testFormatStringBuilderString_WithExtraSpaces() {
		StringBuilder bf = new StringBuilder("Mapping approach     for model transformation of MDA based on xUML");
		String expectedResult = "Mapping approach for model transformation of MDA based on xUML";
		bf = parser.format(bf, "title");
		assertEquals(expectedResult, bf.toString());
	}

	@Test
	public void testFormatStringBuilderString_WithTrailingSpace() {
		StringBuilder bf = new StringBuilder("Mapping approach for model transformation of MDA based on xUML ");
		String expectedResult = "Mapping approach for model transformation of MDA based on xUML";
		bf = parser.format(bf, "title");
		assertEquals(expectedResult, bf.toString());
	}
	
	@Test
	public void testFormatStringBuilderString_WithNewLine() {
		StringBuilder bf = new StringBuilder("Mapping approach\nfor model transformation of MDA based on xUML");
		String expectedResult = "Mapping approach for model transformation of MDA based on xUML";
		bf = parser.format(bf, "title");
		assertEquals(expectedResult, bf.toString());
	}
	
	@Test
	public void testFormatStringBuilderString_WithNewLinesAsParagraph1() {
		StringBuilder bf = new StringBuilder("Mapping approach\n\n\n\nfor model transformation of MDA based on xUML");
		String expectedResult = "Mapping approach\n\nfor model transformation of MDA based on xUML";
		bf = parser.format(bf, "title");
		assertEquals(expectedResult, bf.toString());
	}

	@Test
	public void testFormatStringBuilderString_WithNewLinesAsParagraph2() {
		StringBuilder bf = new StringBuilder("Mapping approach\n\nfor model transformation of MDA based on xUML");
		String expectedResult = "Mapping approach\n\nfor model transformation of MDA based on xUML";
		bf = parser.format(bf, "title");
		assertEquals(expectedResult, bf.toString());
	}
	
	@Test
	public void testFormatStringBuilderString_WindowsNewLines() {
		StringBuilder bf = new StringBuilder("Mapping approach\n\rfor model transformation of MDA based on xUML");
		String expectedResult = "Mapping approach for model transformation of MDA based on xUML";
		bf = parser.format(bf, "title");
		assertEquals(expectedResult, bf.toString());
	}

	@Test
	public void testFormatStringBuilderString_MacOSNewLines() {
		StringBuilder bf = new StringBuilder("Mapping approach\rfor model transformation of MDA based on xUML");
		String expectedResult = "Mapping approach for model transformation of MDA based on xUML";
		bf = parser.format(bf, "title");
		assertEquals(expectedResult, bf.toString());
	}
	
	@Test
	public void testFormatStringBuilderString_WithBracesWithinText() {
		StringBuilder bf = new StringBuilder("Mapping approach for model transformation of {MDA} based on {xUML}");
		String expectedResult = "Mapping approach for model transformation of {MDA} based on {xUML}";
		bf = parser.format(bf, "title");
		assertEquals(expectedResult, bf.toString());
	}

	@Test
	public void testFormatStringBuilderString_WithStringsConcatenation() {
		StringBuilder bf = new StringBuilder("{Mapping approach} # {for model transformation}");
		String expectedResult = "{Mapping approach} # {for model transformation}";
		bf = parser.format(bf, "title");
		assertEquals(expectedResult, bf.toString());
	}
	
	@Test
	public void testFormatStringBuilderString_WithStringsConcatenationAndSpacesWithBrances() {
		StringBuilder bf = new StringBuilder("{Mapping approach } # { for model transformation}");
		String expectedResult = "{Mapping approach } # { for model transformation}";
		bf = parser.format(bf, "title");
		assertEquals(expectedResult, bf.toString());
	}

	
	@Test
	public void testFormatStringBuilderString_WithStringsConcatenationAndSpacesOutsideBraces() {
		StringBuilder bf = new StringBuilder("{Mapping approach }     #   { for model transformation}");
		String expectedResult = "{Mapping approach } # { for model transformation}";
		bf = parser.format(bf, "title");
		assertEquals(expectedResult, bf.toString());
	}

	@Test
	public void testFormatStringBuilderString_WithStringsConcatenationAndNewLineBeforeConcat() {
		StringBuilder bf = new StringBuilder("{Mapping approach }\n# { for model transformation}");
		String expectedResult = "{Mapping approach } # { for model transformation}";
		bf = parser.format(bf, "title");
		assertEquals(expectedResult, bf.toString());
	}

	@Test
	public void testFormatStringBuilderString_WithStringsConcatenationAndComma() {
		StringBuilder bf = new StringBuilder("{New York, NY, } # USA,");
		String expectedResult = "{New York, NY, } # USA,";
		bf = parser.format(bf, "title");
		assertEquals(expectedResult, bf.toString());
	}
}
