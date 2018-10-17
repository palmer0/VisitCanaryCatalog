package es.ulpgc.eite.master.fullvisitcanary.detail;

import android.annotation.SuppressLint;
import android.util.Log;

import es.ulpgc.eite.master.fullvisitcanary.data.Place;
import es.ulpgc.mvp.arch.BasePresenter;

/**
 * Created by Luis on 16/10/17.
 */

public class PlaceDetailPresenter
    extends BasePresenter<PlaceDetailContract.View, PlaceDetailContract.Model>
    implements PlaceDetailContract.Presenter {

  public static final String PARAM_PLACE_ID = "place_to_visit_id";


  @SuppressLint("LongLogTag")
  @Override
  public void onPresenterCreated() {
    super.onPresenterCreated();
    Log.d("VisitCanary.List.Presenter", "onPresenterCreated");

    if(isViewAttached()) {
      model.initRepository(getView().getManagedContext());
    }
  }

  @SuppressLint("LongLogTag")
  @Override
  public void onPresenterResumed() {
    super.onPresenterResumed();
    Log.d("VisitCanary.List.Presenter", "onPresenterResumed");

    model.persistCatalog(false, false, () -> updateUI());

    //String placeId = getInStateBundle().getString(PARAM_PLACE_ID);
    //AsyncTask.execute(() -> updateUI(model.getPlace(placeId)));
  }


  @SuppressLint("LongLogTag")
  @Override
  public void onPresenterDestroy() {
    super.onPresenterDestroy();
    Log.d("VisitCanary.List.Presenter", "onPresenterDestroy");
  }


  @Override
  protected PlaceDetailContract.Model initModel() {
    return new PlaceDetailModel();
  }

  private void updateUI() {
    if (isViewAttached()) {
      String placeId = getInStateBundle().getString(PARAM_PLACE_ID);
      getView().setupUI(model.getPlace( placeId));
    }
  }

  private void updateUI(Place place){
    if(isViewAttached()) {

      if (place != null) {
        getView().setupUI(place);
      }

    }
  }


}
