package com.thoughtworks.mingle.murmurs;

import java.io.IOException;
import java.net.URL;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;


public class InMemoryMurmurPersistor implements MurmurPersistor {

	@Override
	public String saveAsNew(String body) {
		URL url = Resources.getResource("one-murmur-template.xml");
		try {
			String xml = Resources.toString(url, Charsets.UTF_8);
			return xml.replaceAll("@body@", body);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

}
