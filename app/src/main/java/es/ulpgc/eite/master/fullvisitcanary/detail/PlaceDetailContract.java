package es.ulpgc.eite.master.fullvisitcanary.detail;

import android.content.Context;

import es.ulpgc.eite.master.fullvisitcanary.data.Place;
import es.ulpgc.eite.master.spidercatalog.repository.CatalogRepository;
import es.ulpgc.mvp.arch.BaseContract;


interface PlaceDetailContract {

  interface Presenter extends BaseContract.Presenter<View> {

  }

  interface View extends BaseContract.View {

    Context getManagedContext();
    void setupUI(Place place);
  }

  interface Model extends BaseContract.Model {

    void persistCatalog(
        boolean clearFirst, boolean fromJSON,
        CatalogRepository.CatalogPersistenceCallback readyCallback );

    void initRepository(Context managedContext);
    Place getPlace( String placeId);
    //Place getPlace(Context managedContext, String placeId);
  }

}
