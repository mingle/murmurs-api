package com.thoughtworks.mingle.murmurs;

import org.simpleframework.xml.Default;

@Default
public class Author {

    private String name;
    private String icon_url;

    public String getName() {
        return name;
    }

    public String getIconUrl() {
        return icon_url;
    }


}
