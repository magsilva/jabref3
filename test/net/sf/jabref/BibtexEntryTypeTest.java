package net.sf.jabref;

import static org.junit.Assert.*;

import org.junit.Test;

public class BibtexEntryTypeTest {

	@Test
	public void testCompareTo() {
		assertEquals(0, BibtexEntryType.ARTICLE.compareTo(new BibtexEntryType() {
			public String getName() {
				return "Article";
			}

			public String[] getRequiredFields() {
				return new String[] { "author", "title", "crossref", "volume", "number", "month", "year", "pages"};
			}

			
			public String[] getOptionalFields() {
				return new String[] { "abstract", "keywords", "doi", "url"};
			}
		}));
	}

	@Test
	public void testEquals() {
		assertEquals(BibtexEntryType.ARTICLE, new BibtexEntryType() {
			public String getName() {
				return "Article";
			}

			public String[] getRequiredFields() {
				return new String[] { "author", "title", "crossref", "volume", "number", "month", "year", "pages"};
			}

			
			public String[] getOptionalFields() {
				return new String[] { "abstract", "keywords", "doi", "url"};
			}
		});
	}
	
	@Test
	public void testIsRequired() {
		assertTrue(BibtexEntryType.ARTICLE.isRequired("author"));
		assertFalse(BibtexEntryType.ARTICLE.isRequired("abstract"));
		assertFalse(BibtexEntryType.ARTICLE.isRequired("banana"));
	}

	@Test
	public void testIsOptional() {
		assertFalse(BibtexEntryType.ARTICLE.isOptional("author"));
		assertTrue(BibtexEntryType.ARTICLE.isOptional("abstract"));
		assertFalse(BibtexEntryType.ARTICLE.isOptional("banana"));
	}

	@Test
	public void testGetName() {
		assertEquals("Article", BibtexEntryType.ARTICLE.getName());
	}

	@Test
	public void testGetRequiredFields() {
		String[] fields = BibtexEntryType.ARTICLE.getRequiredFields();
		assertNotNull(fields);
		assertEquals("author", fields[0]);
		assertEquals("pages", fields[fields.length - 1]);
	}

	@Test
	public void testGetOptionalFields() {
		String[] fields = BibtexEntryType.ARTICLE.getOptionalFields();
		assertNotNull(fields);
		assertEquals("abstract", fields[0]);
		assertEquals("url", fields[fields.length - 1]);
	}

	@Test
	public void testGetType() {
		assertEquals(BibtexEntryType.ARTICLE, BibtexEntryType.getType("article"));
	}

	@Test
	public void testGetType_Unknown() {
		assertEquals(BibtexEntryType.MISC, BibtexEntryType.getType("banana"));
	}

	@Test
	public void testRemoveType() {
		BibtexEntryType type = BibtexEntryType.getType("article");
		BibtexEntryType.removeType("article");
		assertEquals(BibtexEntryType.ARTICLE, BibtexEntryType.getType("article"));
	}
}
