package com.thoughtworks.mingle.api;

import org.simpleframework.xml.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValue {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private String rawValue;

    @Text
    public void setRawValue(String raw) {
      this.rawValue = raw;
    }

    @Text
    public String getRawValue() {
        return this.rawValue;
    }

    public Date get() {
        try {
            return DATE_FORMAT.parse(this.rawValue);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
