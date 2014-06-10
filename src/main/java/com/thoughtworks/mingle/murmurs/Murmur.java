package com.thoughtworks.mingle.murmurs;

import java.net.URI;
import java.util.Date;
import java.util.List;

import com.ocpsoft.pretty.time.PrettyTime;

public class Murmur {

  private static final PrettyTime prettyTime = new PrettyTime();

  private Integer id;
  private String body;
  private Date created_at;
  private String jabber_user_name;
  private boolean is_truncated;
  private Stream stream;
  private Author author;
  
  /**
   * For xstream
   */
  public Murmur() {}
  
  /**
   * For posting to Mingle
   * @param body
   */
  public Murmur(String body) {
    this.body = body;
  }

  /**
   * Container class to allow the XML doc to be mapped easily
   */
  public static class Murmurs {

    private List<Murmur> murmurs;

    public List<Murmur> getMurmurs() {
      return murmurs;
    }
  }

  public static class Stream {
    private String type;
    private Origin origin;

    public static class Origin {
      private String url;
      private Integer number;
    }
  }

  public static final String[] SUMMARY_COLUMN_NAMES = { "_ID", "TAGLINE", "BODY", "ICON_PATH" };

  public static final String[] DETAIL_COLUMN_NAMES = { "_ID", "AUTHOR", "CREATED_AT", "BODY", "ICON_PATH" };

  public int getId() {
    return id;
  }

  public String getAuthor() {
    return author.getName();
  }

  public String getShortBody() {
    if (body.length() > 128) {
      return body.substring(0, 128);
    }
    return body;
  }

  public String getBody() {
    return body;
  }

  public Date getCreatedAt() {
    return created_at;
  }

  public String getCreatedAtFormatted() {
    return prettyTime.format(this.created_at);
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

  public static URI constructUri(long id) {
    return URI.create(id + ".murmur");
  }

  public static int getIdFromUri(String uri) {
    return Integer.parseInt(uri.split("\\.")[0]);
  }

  public void saveAsNew(MurmurPersistor persistor) {
    String xml = persistor.saveAsNew(getBody().trim());
    Murmur newMurmur = new MurmursLoader().loadOneFromXml(xml);
    this.author = newMurmur.author;
    this.body = newMurmur.body;
    this.created_at = newMurmur.created_at;
    this.id = newMurmur.id;
    this.is_truncated = newMurmur.is_truncated;
    this.jabber_user_name = newMurmur.jabber_user_name;
    this.stream = newMurmur.stream;
  }
}
