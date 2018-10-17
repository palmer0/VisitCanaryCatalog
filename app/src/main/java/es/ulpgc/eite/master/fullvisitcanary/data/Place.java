package es.ulpgc.eite.master.fullvisitcanary.data;

public class Place {

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


  @Override
  public String toString() {
    return title;
  }
}