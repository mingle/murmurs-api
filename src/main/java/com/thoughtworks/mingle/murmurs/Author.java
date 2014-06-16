package com.thoughtworks.mingle.murmurs;

import org.simpleframework.xml.Default;

@Default
public class Author {

    private String name;
    private String icon_url;
//  private String login;
//  private String email;
//  private boolean light;
//  private String icon_path;
//  private boolean activated;
//  private boolean admin;
//  private String version_control_user_name;
//  private String jabber_user_name;

    public String getName() {
        return name;
    }

    public String getIconUrl() {
        return icon_url;
    }


}
