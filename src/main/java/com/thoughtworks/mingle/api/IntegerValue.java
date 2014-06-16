package com.thoughtworks.mingle.api;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class IntegerValue {

    @Attribute
    protected String type;

    private String rawValue;

    @Text
    public void setRawValue(String rawValue) {
      this.rawValue = rawValue;
    }

    @Text
    public String getRawValue() {
        return rawValue;
    }

    public Integer get() {
        return Integer.parseInt(rawValue);
    }
}
