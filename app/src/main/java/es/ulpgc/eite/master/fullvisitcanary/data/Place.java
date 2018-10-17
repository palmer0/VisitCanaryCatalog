package es.ulpgc.eite.master.fullvisitcanary.data;

import org.json.JSONException;
import org.json.JSONObject;

public class Place {

  public static final String KEY_TITLE = "title";
  public static final String KEY_DESC = "description";
  public static final String KEY_PIC= "picture";
  public static final String KEY_LOC= "location";

  public final String id;
  public final String title;
  public final String description;
  public final String picture;
  public final String location;


  public Place(
      String id, String title,
      String description, String picture, String location) {

    this.id = id;
    this.title = title;
    this.description = description;
    this.picture = picture;
    this.location = location;
  }

  public JSONObject toJSONObject () {

    JSONObject obj = new JSONObject() ;

    try {

      obj.put(KEY_TITLE, title);
      obj.put(KEY_DESC, description);
      obj.put(KEY_PIC, picture);
      obj.put(KEY_LOC, location);

    } catch (JSONException e) {

    }

    return obj;
  }


  @Override
  public String toString() {
    return title;
  }
}