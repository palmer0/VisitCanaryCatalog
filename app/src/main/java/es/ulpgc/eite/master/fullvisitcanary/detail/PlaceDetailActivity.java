package es.ulpgc.eite.master.fullvisitcanary.detail;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import es.ulpgc.eite.master.fullvisitcanary.R;
import es.ulpgc.eite.master.fullvisitcanary.data.Place;
import es.ulpgc.mvp.arch.BaseAnnotatedActivity;
import es.ulpgc.mvp.arch.Viewable;


@Viewable(presenter = PlaceDetailPresenter.class, layout = R.layout.activity_place_detail)
public class PlaceDetailActivity
    extends BaseAnnotatedActivity<PlaceDetailContract.View, PlaceDetailContract.Presenter>
    //extends BaseActivity<PlaceDetailContract.View, PlaceDetailContract.Presenter>
    implements PlaceDetailContract.View {


    @Override
    protected PlaceDetailContract.Presenter initPresenter() {
        return new PlaceDetailPresenter();
    }


    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
    }
    */

    public Context getManagedContext(){
        return getBaseContext();
    }

    public void setupUI(Place place){
        runOnUiThread(() -> updateUI(place));
    }


    private void updateUI(Place place){
        if (place != null) {
            setupToolbar(place.title);

            TextView placeDetail = findViewById(R.id.place_detail);
            placeDetail.setText(place.description);
            ImageView placePicture = findViewById(R.id.place_picture);

            /*
            int resId = getResources().getIdentifier(
                place.picture, "drawable", getPackageName());
            placePicture.setImageResource(resId);
            */

            loadImage(placePicture, place.picture);
        }
    }


    private void setupToolbar(String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle(title);
        }
    }

    public void loadImage(ImageView imageView, String imageUrl){
        RequestManager reqManager = Glide.with(imageView.getContext());
        RequestBuilder reqBuilder = reqManager.load(imageUrl);
        RequestOptions reqOptions = new RequestOptions();
        reqOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        reqBuilder.apply(reqOptions);
        reqBuilder.into(imageView);
    }

}
