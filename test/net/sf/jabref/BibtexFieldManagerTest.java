package net.sf.jabref;

import static org.junit.Assert.*;

import org.junit.Test;

public class BibtexFieldManagerTest {

	@Test
	public void testSingleton() {
		assertNotNull(BibtexFieldManager.singleton);
	}

	@Test
	public void testGetField() {
		BibtexField field = BibtexFieldManager.singleton.getField("title");
		assertNotNull(field);
		assertEquals("title", field.getFieldName());
		assertTrue(field.isStandard());
	}

	@Test
	public void testGetAllFieldNames() {
		String[] fields = BibtexFieldManager.singleton.getAllFieldNames();
		assertTrue(fields.length > 0);
		for (String fieldName : fields) {
			BibtexField field = BibtexFieldManager.singleton.getField(fieldName);
			assertTrue(field.isPublic());
		}
	}

	@Test
	public void testGetFieldName() {
		assertEquals("abstract", BibtexFieldManager.singleton.getFieldName(0));
	}
}
