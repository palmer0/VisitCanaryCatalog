package es.ulpgc.eite.master.fullvisitcanary.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.eite.master.fullvisitcanary.R;
import es.ulpgc.eite.master.fullvisitcanary.data.Place;
import es.ulpgc.eite.master.fullvisitcanary.detail.PlaceDetailActivity;
import es.ulpgc.eite.master.fullvisitcanary.map.PlaceMapActivity;
import es.ulpgc.mvp.arch.BaseAnnotatedActivity;
import es.ulpgc.mvp.arch.Viewable;


@Viewable(presenter = PlaceListPresenter.class, layout = R.layout.activity_place_list)
public class PlaceListActivity
    extends BaseAnnotatedActivity<PlaceListContract.View, PlaceListContract.Presenter>
    //extends BaseActivity<PlaceListContract.View, PlaceListContract.Presenter>
    implements PlaceListContract.View {


    //private RecyclerView recyclerView;
    private PlaceListAdapter recyclerAdapter;

    @Override
    protected PlaceListContract.Presenter initPresenter() {
        return new PlaceListPresenter();
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);
    }
    */


    /*
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        presenter.listReady();
    }
    */

    public Context getManagedContext(){
        return getBaseContext();
    }

    /*
    public void setupUI() {
        Log.d("VisitCanary.List.View", "setupUI");


        RecyclerView recyclerView = findViewById(R.id.place_list);
        //recyclerView = findViewById(R.id.place_list);
        recyclerAdapter = new PlaceListAdapter(new ArrayList());
        recyclerView.setAdapter(recyclerAdapter);


        setupToolbar();
    }
    */

    public void updateUI(List<Place> places) {
        Log.d("VisitCanary.List.View", "updateUI");
        Log.d("VisitCanary.List.View", "places: " + places);

        RecyclerView recyclerView = findViewById(R.id.place_list);
        //recyclerAdapter.setPlaces(places);
        //runOnUiThread(() -> updateAdapter(places));


        runOnUiThread(() -> {
            setupToolbar();
            //setupAdapter(places);
            recyclerView.setAdapter(new PlaceListAdapter(places));
            //recyclerAdapter.setPlaces(places);
        });
    }

//    public void updateAdapter(List<Place> places) {
//
//        for(Place place: places) {
//            recyclerAdapter.addPlace(place);
//        }
//
//        //recyclerAdapter.setPlaces(places);
//
//        /*
//        RecyclerView recyclerView = findViewById(R.id.place_list);
//        recyclerAdapter = new PlaceListAdapter(places);
//        recyclerView.setAdapter(recyclerAdapter);
//        */
//    }

    /*
    public void setupUI(List<Place> places) {
        RecyclerView recyclerView = findViewById(R.id.place_list);

        runOnUiThread(() -> {
            setupToolbar();
            //setupAdapter(places);
            recyclerView.setAdapter(new PlaceListAdapter(places));
        });

    }
    */

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle(getString(R.string.title_place_list));
        }
    }

    /*
    private void setupAdapter(List<Place> places) {
        RecyclerView recyclerView = findViewById(R.id.place_list);
        recyclerView.setAdapter(new PlaceListAdapter(places));
    }
    */

    public void openDetailActivity() {
        openActivity(PlaceDetailActivity.class);
    }


    public void openMapActivity() {
        openActivity(PlaceMapActivity.class);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_map_button) {
            //goToPlaceList();
            presenter.menuClicked();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_menu, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }



    class PlaceListAdapter
            extends RecyclerView.Adapter<PlaceListAdapter.PlaceViewHolder> {

        private List<Place> places;

        public PlaceListAdapter(List<Place> places) {
            this.places = places;
        }

        public void setPlaces(List<Place> places) {
            this.places = places;
            notifyDataSetChanged();
        }

        public void addPlace(Place place) {
            places.add(place);
            notifyDataSetChanged();
        }

        @Override
        public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.place_list_content, parent, false);
            return new PlaceViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final PlaceViewHolder holder, int position) {
            holder.placeItem = places.get(position);
            holder.placeTitleView.setText(places.get(position).title);

            Log.d("VisitCanary.List.View", "place: " + holder.placeItem);

            holder.placeView.setOnClickListener(view ->
                presenter.placeClicked(holder.placeItem.id)
            );
        }

        @Override
        public int getItemCount() {
            return places.size();
        }

        class PlaceViewHolder extends RecyclerView.ViewHolder {
            public final View placeView;
            public final TextView placeTitleView;
            public Place placeItem;

            public PlaceViewHolder(View view) {
                super(view);
                placeView = view;
                placeTitleView = view.findViewById(R.id.place_title);
            }

            @Override
            public String toString() {
                return placeTitleView.getText().toString();
            }
        }
    }


}
