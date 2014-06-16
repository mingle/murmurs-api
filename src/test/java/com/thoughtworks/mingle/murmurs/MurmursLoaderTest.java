package com.thoughtworks.mingle.murmurs;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import com.google.common.io.Resources;

public class MurmursLoaderTest {
	
	@Test
	public void testLoadMany() throws Exception {
		MurmursLoader ldr = new MurmursLoader();
		URL url = Resources.getResource("page.xml");
		String xml = Resources.toString(url, Charsets.UTF_8);
		List<Murmur> murmurs = ldr.loadMultipleFromXml(CharSource.wrap(xml));
		assertEquals(25, murmurs.size());
		Murmur murmur = murmurs.get(24);
		assertEquals("use sql to calculate project variable usage among transitions that works in both pgsql and oracle", murmur.getBody());
		assertEquals("http://www.gravatar.com/avatar/0a10dba66a8bf1409fe2ddaf7bf200fc?d=404&s=48", murmur.getIconPathUri());
	}

}
