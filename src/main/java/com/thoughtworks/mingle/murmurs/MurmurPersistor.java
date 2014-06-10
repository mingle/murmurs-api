package com.thoughtworks.mingle.murmurs;

import java.io.InputStream;

public interface MurmurPersistor {

	InputStream saveAsNew(String body);
	
}
