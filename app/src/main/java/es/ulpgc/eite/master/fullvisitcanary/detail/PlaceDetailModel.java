package es.ulpgc.eite.master.fullvisitcanary.detail;

import android.content.Context;
import android.util.Log;

import java.util.Map;

import es.ulpgc.eite.master.fullvisitcanary.data.Place;
import es.ulpgc.eite.master.spidercatalog.model.Product;
import es.ulpgc.eite.master.spidercatalog.repository.CatalogRepository;
import es.ulpgc.mvp.arch.BaseModel;

public class PlaceDetailModel
    extends BaseModel<PlaceDetailContract.Presenter> implements PlaceDetailContract.Model {


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


  public Place getPlace(String placeId) {
    Product product = repository.getProduct(placeId);
    return convertProductToPlace(product);
  }


  private Place convertProductToPlace(Product product) {

    String imageUrl = null;
    if (!product.getImageUrls().isEmpty()) {
      imageUrl = product.getImageUrls().get(0);
    }

    Map<String, String> params = product.getParams();
    String location = params.get("location");
    //String latitude = params.get("longitud");
    //String longitude = params.get("latitud");

    Place place = new Place(
        product.getId(), product.getName(), product.getDescription(), imageUrl, location);

    return place;
  }

}
