package es.ulpgc.eite.master.fullvisitcanary.detail;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
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

    //setupUI();

    model.persistCatalog(false, false, () -> updateUI());

    //String placeId = getInStateBundle().getString(PARAM_PLACE_ID);
    //AsyncTask.execute(() -> setupUI(model.getPlace(placeId)));
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
      //Place place = model.getPlace( placeId);
      //getView().setupUI(place);
      getView().setupUI(model.getPlace( placeId));
    }
  }

  private void setupUI(Place place){
    if(isViewAttached()) {

      /*
      String placeId = getInStateBundle().getString(PARAM_PLACE_ID);
      //Place place = model.getPlace(getView().getManagedContext(), placeId);
      Place place = model.getPlace( placeId);
      */

      if (place != null) {
        getView().setupUI(place);
      }

    }
  }


}
