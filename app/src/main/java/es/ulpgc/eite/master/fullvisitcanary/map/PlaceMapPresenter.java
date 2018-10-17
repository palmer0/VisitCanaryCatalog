package es.ulpgc.eite.master.fullvisitcanary.map;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.List;

import es.ulpgc.eite.master.fullvisitcanary.data.Place;
import es.ulpgc.eite.master.fullvisitcanary.detail.PlaceDetailPresenter;
import es.ulpgc.mvp.arch.BasePresenter;


/**
 * Created by Luis on 16/10/17.
 */

public class PlaceMapPresenter
    extends BasePresenter<PlaceMapContract.View, PlaceMapContract.Model>
    implements PlaceMapContract.Presenter {


  @SuppressLint("LongLogTag")
  @Override
  public void onPresenterCreated() {
    super.onPresenterCreated();
    Log.d("VisitCanary.Map.Presenter", "onPresenterCreated");

    if(isViewAttached()) {
      model.initRepository(getView().getManagedContext());
    }
  }

  @SuppressLint("LongLogTag")
  @Override
  public void onPresenterResumed() {
    super.onPresenterResumed();
    Log.d("VisitCanary.Map.Presenter", "onPresenterResumed");

    if(isViewAttached()) {
      getView().setupUI();
    }
  }

  
  @SuppressLint("LongLogTag")
  @Override
  public void onPresenterDestroy() {
    super.onPresenterDestroy();
    Log.d("VisitCanary.Map.Presenter", "onPresenterDestroy");
  }


  @Override
  protected PlaceMapContract.Model initModel() {
    return new PlaceMapModel();
  }

  @SuppressLint("LongLogTag")
  public void mapReady() {
    Log.d("VisitCanary.Map.Presenter", "mapReady");

    model.persistCatalog(false, false, () -> updateUI());
    //AsyncTask.execute(() -> updateUI(model.getPlaces()));
  }

  private void updateUI(List<Place> places) {
    if (isViewAttached()) {
      getView().updateUI(places);
    }
  }

  @SuppressLint("LongLogTag")
  private void updateUI() {
    Log.d("VisitCanary.Map.Presenter", "updateUI");

    if (isViewAttached()) {
      getView().updateUI(model.getPlaces());
    }
  }

  public void placeClicked(String placeId) {
    if(isViewAttached()) {
      getOutStateBundle().putString(PlaceDetailPresenter.PARAM_PLACE_ID, placeId);
      getView().openDetailActivity();
    }
  }

  public void menuClicked() {
    if(isViewAttached()) {
      getView().openListActivity();
    }
  }

}
