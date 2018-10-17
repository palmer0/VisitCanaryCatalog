package es.ulpgc.eite.master.fullvisitcanary.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PlaceStore {


    private List<Place> places = new ArrayList();


    public PlaceStore(
            List<String> titles, List<String> descs,
            List<String> pics, List<String> locs) {

        for (int position = 0; position < titles.size(); position++) {
            String title = titles.get(position);
            String description = descs.get(position);
            String picture = pics.get(position);
            String location = locs.get(position);
            addPlace(createPlace(position, title, description, picture, location));

        }
    }

    public JSONArray toJSONArray () {
        JSONArray array = new JSONArray();
        for(Place place: places) {
            JSONObject obj = place.toJSONObject();
            Log.d("JSONObject", obj.toString());
            array.put(obj);
        }

        return array;
    }


    private void addPlace(Place place) {
        places.add(place);
    }

    private Place createPlace(
            int position, String title, String desc, String pic, String loc) {

        return new Place(String.valueOf(position), title, desc, pic, loc);
    }


    public List<Place> getPlaces(){
        return places;
    }

    public Place getPlaceByPosition(int position) {
        return places.get(position);
    }

    public Place getPlaceById(String id) {
        Integer position = Integer.valueOf(id);
        return getPlaceByPosition(position);
    }


}
