package es.ulpgc.eite.master.fullvisitcanary.list;

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

public class PlaceListModel
    extends BaseModel<PlaceListContract.Presenter> implements PlaceListContract.Model {

  private CatalogRepository repository;

  @Override
  public void onPresenterCreated() {
    super.onPresenterCreated();
    Log.d("VisitCanary.List.Model", "onPresenterCreated");

  }


  public void initRepository(Context managedContext) {
    repository = CatalogRepository.getInstance(managedContext, "visitcanary");
  }

  public void persistCatalog(
      boolean clearFirst, boolean fromJSON,
      CatalogRepository.CatalogPersistenceCallback readyCallback ) {

    repository.persistCatalog(clearFirst, fromJSON, readyCallback);
  }


  public List<Place> getPlaces() {
    List<Place> places = new ArrayList();

    List<? extends Category> categories =
        repository.getCategories(repository.getRootCategory());

    for(Category category: categories){

      Map<String, String> params = category.getProduct().getParams();
      if(params.get("type").equals("listScene")){

        List<? extends Product> products = category.getProducts();
        for(Product product: products) {
          places.add(convertProductToPlace(product));
        }

      }
    }

    return places;
  }

  private Place convertProductToPlace(Product product) {
    Log.d("VisitCanary.List.Model", "product: " + product);

    String imageUrl = null;
    if (!product.getImageUrls().isEmpty()) {
      imageUrl = product.getImageUrls().get(0);
    }

    Log.d("VisitCanary.List.Model", "imageUrl: " + imageUrl);


    Map<String, String> params = product.getParams();
    String location = params.get("location");
    //String latitude = params.get("longitud");
    //String longitude = params.get("latitud");

    Log.d("VisitCanary.List.Model", "location: " + location);

    Place place = new Place(
        product.getId(), product.getName(), product.getDescription(), imageUrl, location);

    return place;
  }

}
