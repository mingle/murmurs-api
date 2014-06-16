package com.thoughtworks.mingle.murmurs;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class Murmurs {

     @ElementList(inline = true)
     private List<Murmur> murmurs;


    public List<Murmur> getMurmurs() {
        return murmurs;
    }

}
