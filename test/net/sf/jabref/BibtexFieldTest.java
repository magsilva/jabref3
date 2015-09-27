package net.sf.jabref;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BibtexFieldTest {
	
	private BibtexField field;
	
	@Before
	public void setUp() {
		field = new BibtexField("test");
	}

	@Test
	public void testBibtexFieldString() {
		assertEquals("test", field.getFieldName());
		assertTrue(field.isPublic());
		assertFalse(field.isPrivate());
		assertFalse(field.isStandard());
		assertTrue(field.isDisplayable());
		assertTrue(field.isWriteable());
	}

	@Test
	public void testSetPrivate() {
		field.setPrivate();
		assertTrue(field.isPrivate());
		assertFalse(field.isPublic());
	}

	@Test
	public void testSetPublic() {
		field.setPublic();
		assertFalse(field.isPrivate());
		assertTrue(field.isPublic());
	}
	
	@Test
	public void testSetPrivatePublicToggle() {
		field.setPrivate();
		assertTrue(field.isPrivate());
		assertFalse(field.isPublic());
		field.setPublic();
		assertFalse(field.isPrivate());
		assertTrue(field.isPublic());
	}
}
