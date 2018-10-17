package es.ulpgc.eite.master.fullvisitcanary.data;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.ulpgc.eite.master.fullvisitcanary.R;


public class PlaceRepository {

  private static PlaceRepository instance;
  private PlaceStore store;

  public PlaceRepository(Context managedContext) {
    //fillPlaceStoreFromResources( managedContext);
    fillPlaceStoreFromAssets( managedContext);
  }


  public static PlaceRepository getInstance(Context managedContext) {
    if(instance == null) {
      instance = new PlaceRepository(managedContext);
    }

    return instance;
  }


  public Place getPlace(String placeId) {
    return store.getPlaceById(placeId);
  }

  public List<Place> getPlaces() {
    return store.getPlaces();
  }


  private void fillPlaceStoreFromAssets(Context managedContext){
    List<String> titles = new ArrayList();
    List<String> descriptions = new ArrayList();
    List<String> pictures = new ArrayList();
    List<String> locations = new ArrayList();

    JSONArray array = loadJSONFromAssets(managedContext, "places.json");
    for(int index=0; index < array.length(); index++){
      try {

        JSONObject obj = array.getJSONObject(index);
        String title = obj.getString(Place.KEY_TITLE);
        String description = obj.getString(Place.KEY_DESC);
        String picture = obj.getString(Place.KEY_PIC);
        String location = obj.getString(Place.KEY_LOC);

        locations.add(location);
        titles.add(title);
        pictures.add(picture);
        descriptions.add(description);

      } catch (JSONException e) {

      }
    }

    store = new PlaceStore(titles, descriptions, pictures, locations);

    Log.d("VisitCanary.List.Model", "fillPlaceStoreFromAssets: " + store.toJSONArray());
  }

  private void fillPlaceStoreFromResources(Context managedContext){
    Resources res = managedContext.getResources();
    List<String> titles =
        Arrays.asList(res.getStringArray(R.array.places_titles));
    List<String> descriptions =
        Arrays.asList(res.getStringArray(R.array.places_descriptions));
    List<String> pictures =
        Arrays.asList(res.getStringArray(R.array.places_pictures));
    List<String> locations =
        Arrays.asList(res.getStringArray(R.array.places_locations));

    store = new PlaceStore(titles, descriptions, pictures, locations);

    Log.d("VisitCanary.List.Model", "fillPlaceStoreFromResources: " + store.toJSONArray());
  }

  private JSONArray loadJSONFromAssets(Context managedContext, String filename) {
    try {

      InputStream is = managedContext.getAssets().open(filename);
      int size = is.available();
      byte[] buffer = new byte[size];
      is.read(buffer);
      is.close();

      String json = new String(buffer, "UTF-8");
      JSONArray array = new JSONArray(json);
      return array;

    } catch (Exception ex) {

    }

    //return new JSONArray();
    return null;
  }

}
