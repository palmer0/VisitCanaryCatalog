package es.ulpgc.eite.master.fullvisitcanary.list;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.List;

import es.ulpgc.eite.master.fullvisitcanary.data.Place;
import es.ulpgc.eite.master.fullvisitcanary.detail.PlaceDetailPresenter;
import es.ulpgc.mvp.arch.BasePresenter;


/**
 * Created by Luis on 16/10/17.
 */

public class PlaceListPresenter
    extends BasePresenter<PlaceListContract.View, PlaceListContract.Model>
    implements PlaceListContract.Presenter {


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
    //AsyncTask.execute(() -> updateUI(model.getPlaces()));
  }


  @SuppressLint("LongLogTag")
  @Override
  public void onPresenterDestroy() {
    super.onPresenterDestroy();
    Log.d("VisitCanary.List.Presenter", "onPresenterDestroy");
  }


  @Override
  protected PlaceListContract.Model initModel() {
    return new PlaceListModel();
  }


  private void updateUI(List<Place> places) {
    if (isViewAttached()) {
      getView().updateUI(places);
    }
  }


  private void updateUI() {
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
      getView().openMapActivity();
    }
  }

}
