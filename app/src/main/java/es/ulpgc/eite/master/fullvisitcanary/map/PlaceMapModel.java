package es.ulpgc.eite.master.fullvisitcanary.map;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import es.ulpgc.eite.master.fullvisitcanary.data.Place;
import es.ulpgc.eite.master.spidercatalog.model.Category;
import es.ulpgc.eite.master.spidercatalog.model.Product;
import es.ulpgc.eite.master.spidercatalog.repository.CatalogRepository;
import es.ulpgc.mvp.arch.BaseModel;

public class PlaceMapModel
    extends BaseModel<PlaceMapContract.Presenter> implements PlaceMapContract.Model {

  //private PlaceRepository repository;
  private CatalogRepository repository;


  @Override
  public void onPresenterCreated() {
    super.onPresenterCreated();
    Log.d("VisitCanary.Map.Model", "onPresenterCreated");

  }

  public void initRepository(Context managedContext) {
    //repository = PlaceRepository.getInstance(managedContext);
    repository = CatalogRepository.getInstance(managedContext, "visitcanary");
  }

  public void persistCatalog(
      boolean clearFirst, boolean fromJSON,
      CatalogRepository.CatalogPersistenceCallback readyCallback ) {

    repository.persistCatalog(clearFirst, fromJSON, readyCallback);
  }

  /*
  public List<Place> getPlaces(Context managedContext) {
    return PlaceRepository.getInstance(managedContext).getPlaces();
  }
  */


  public List<Place> getPlaces() {
    //return repository.getPlaces();

    List<Place> places = new ArrayList();

    List<? extends Category> categories =
        repository.getCategories(repository.getRootCategory());

    for(Category category: categories){
      Log.d("VisitCanary.Map.Model", "category: " + category);

      Map<String, String> params = category.getProduct().getParams();
      if(params.get("type").equals("mapScene")){

        List<? extends Product> products = category.getProducts();
        for(Product product: products) {
          Log.d("VisitCanary.Map.Model", "product: " + product);
          places.add(convertProductToPlace(product));
        }

      }
    }

    return places;
  }

  private Place convertProductToPlace(Product product) {

    String imageUrl = null;
    if(!product.getImageUrls().isEmpty()) {
      imageUrl = product.getImageUrls().get(0);
    }

    Log.d("VisitCanary.Map.Model", "imageUrl: " + imageUrl);

    Map<String, String> params = product.getParams();
    String location = params.get("location");
    //String latitude = params.get("longitud");
    //String longitude = params.get("latitud");

    Log.d("VisitCanary.Map.Model", "location: " + location);

    Place place = new Place(
        product.getId(), product.getName(), product.getDescription(), imageUrl, location);

    return place;
  }

}
