package com.thoughtworks.mingle.murmurs;

import com.ocpsoft.pretty.time.PrettyTime;
import com.thoughtworks.mingle.api.DateValue;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.net.URI;
import java.util.Date;

@Root
public class Murmur {

    private static final PrettyTime prettyTime = new PrettyTime();

    @Element(required = false)
    private String body;

    @Element(required = false)
    private DateValue created_at;

//    private String jabber_user_name;
//    private boolean is_truncated;
//    private String stream;

    @Element(required = false)
    private Author author;

    public Murmur() {}

   /**
     * For posting to Mingle
     *
     * @param body
     */
    public Murmur(String body) {
        this.body = body;
    }

    public static URI constructUri(long id) {
        return URI.create(id + ".murmur");
    }

    public static int getIdFromUri(String uri) {
        return Integer.parseInt(uri.split("\\.")[0]);
    }

    public String getAuthor() {
        return author.getName();
    }

    public String getShortBody() {
        String body = this.getBody();
        if (body.length() > 128) {
            return body.substring(0, 128);
        }
        return body;
    }

    public String getBody() {
        return body;
    }

    public Date getCreatedAt() {
        return created_at.get();
    }

    public String getCreatedAtFormatted() {
        return prettyTime.format(this.created_at.get());
    }

    public String getIconPathUri() {
        return author.getIconUrl();
    }

    public String toString() {
        return String.format("%s: %s", this.author, this.body);
    }

    public String getTagline() {
        return String.format("-%s (%s)", getAuthor(), getCreatedAtFormatted());
    }

    public void saveAsNew(MurmurPersistor persistor) {
        String xml = persistor.saveAsNew(getBody().trim());
        Murmur newMurmur = new MurmursLoader().loadOneFromXml(xml);
        this.author = newMurmur.author;
        this.body = newMurmur.body;
        this.created_at = newMurmur.created_at;
    }


}
