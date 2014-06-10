package com.thoughtworks.mingle.murmurs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.io.CharSource;
import com.thoughtworks.mingle.murmurs.Murmur.Stream.Origin;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class MurmursLoader {
  
  private final XStream xstream;
  {
	  xstream = new XStream(new DomDriver());
	  xstream.addImplicitCollection(Murmur.Murmurs.class, "murmurs", Murmur.class);
	  xstream.alias("murmurs", Murmur.Murmurs.class);
	  addCommonAliases(xstream);
  }

  private static final DateConverter DATE_CONVERTER = new DateConverter("yyyy-MM-dd'T'HH:mm:ss'Z'",
      new String[] { "yyyy-MM-dd'T'HH:mm:ssZ" });
  
  private static void addCommonAliases(XStream xstream) {
    xstream.alias("murmur", Murmur.class);
    xstream.alias("author", Author.class);
    xstream.alias("stream", Murmur.Stream.class);
    xstream.alias("origin", Origin.class);
    xstream.registerLocalConverter(Murmur.class, "created_at",
        MurmursLoader.DATE_CONVERTER);
    xstream.registerLocalConverter(Author.class, "last_login_at",
        MurmursLoader.DATE_CONVERTER);
  }

  public Murmur loadOneFromXml(String xml) {
	return (Murmur) getXstream().fromXML(xml);		
  }
  
  public List<Murmur> loadMultipleFromXml(CharSource source) {
	 Preconditions.checkNotNull(source);
    Murmur.Murmurs murmurs = null;
    BufferedReader reader = null;
	try {
		reader = source.openBufferedStream();
		murmurs = (Murmur.Murmurs) getXstream().fromXML(reader);
	} catch (IOException e) {
		throw new RuntimeException(e);
	} 
    if (murmurs == null) {
      return Collections.EMPTY_LIST;
    }
    return murmurs.getMurmurs();
  }

	private XStream getXstream() {
		return xstream;
	}

}
