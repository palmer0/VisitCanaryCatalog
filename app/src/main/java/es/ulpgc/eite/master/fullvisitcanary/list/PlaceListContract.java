package es.ulpgc.eite.master.fullvisitcanary.list;

import android.content.Context;

import java.util.List;

import es.ulpgc.eite.master.fullvisitcanary.data.Place;
import es.ulpgc.eite.master.spidercatalog.repository.CatalogRepository;
import es.ulpgc.mvp.arch.BaseContract;


interface PlaceListContract {

  interface Presenter extends BaseContract.Presenter<View> {

    void placeClicked(String placeId);
    void menuClicked();
  }

  interface View extends BaseContract.View {

    Context getManagedContext();
    void updateUI(List<Place> places);
    void openDetailActivity();
    void openMapActivity();
  }

  interface Model extends BaseContract.Model {

    void persistCatalog(
        boolean clearFirst, boolean fromJSON,
        CatalogRepository.CatalogPersistenceCallback readyCallback );

    void initRepository(Context managedContext);
    List<Place> getPlaces();
  }

}
