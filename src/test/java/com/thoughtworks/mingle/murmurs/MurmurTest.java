package com.thoughtworks.mingle.murmurs;

import org.junit.Test;
import static org.junit.Assert.*;

public class MurmurTest {

	@Test
	public void testSaveAsNew() {
		Murmur m = new Murmur("hello world");
		InMemoryMurmurPersistor persistor = new InMemoryMurmurPersistor();
		m.saveAsNew(persistor);
		assertEquals("Bill \"Color Hash\"", m.getAuthor());
	}

}
