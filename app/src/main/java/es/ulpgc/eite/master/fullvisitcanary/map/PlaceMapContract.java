package es.ulpgc.eite.master.fullvisitcanary.map;

import android.content.Context;

import java.util.List;

import es.ulpgc.eite.master.fullvisitcanary.data.Place;
import es.ulpgc.eite.master.spidercatalog.repository.CatalogRepository;
import es.ulpgc.mvp.arch.BaseContract;


interface PlaceMapContract {

  interface Presenter extends BaseContract.Presenter<View> {

    void placeClicked(String placeId);
    void menuClicked();
    void mapReady();
  }

  interface View extends BaseContract.View {

    Context getManagedContext();
    void updateUI(List<Place> places);
    void setupUI();
    void openDetailActivity();
    void openListActivity();
  }

  interface Model extends BaseContract.Model {

    void persistCatalog(
        boolean clearFirst, boolean fromJSON,
        CatalogRepository.CatalogPersistenceCallback readyCallback );

    void initRepository(Context managedContext);
    List<Place> getPlaces();
  }

}
