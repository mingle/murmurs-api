package com.thoughtworks.mingle.murmurs;

import com.google.common.base.Preconditions;
import com.google.common.io.CharSource;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Visitor;
import org.simpleframework.xml.strategy.VisitorStrategy;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class MurmursLoader {

    private static final Logger logger = Logger.getLogger(MurmursLoader.class.getCanonicalName());

    public Murmur loadOneFromXml(String xml) {
        Serializer serializer = new Persister();
        try {
            return serializer.read(Murmur.class, xml, false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Murmur> loadMultipleFromXml(final CharSource source) {
        Preconditions.checkNotNull(source);
        try {
            Murmurs murmurs = new Persister().read(Murmurs.class, source.openBufferedStream(), false);
            return murmurs.getMurmurs();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
