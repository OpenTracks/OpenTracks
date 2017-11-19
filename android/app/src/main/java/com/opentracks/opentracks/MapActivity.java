package com.opentracks.opentracks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.opentracks.opentracks.model.TrackDetails;
import com.qozix.tileview.markers.MarkerLayout.MarkerTapListener;
import com.qozix.tileview.paths.CompositePathView.DrawablePath;
import com.qozix.tileview.TileView;

import java.util.ArrayList;


public class MapActivity extends Activity implements MarkerTapListener {

    TileView mapView;
    TextView textSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
    }

    private void setupUI()
    {
        // TEXT: search bar
        textSearch = findViewById(R.id.text_search);
        textSearch.setText("Manali Test Map");

        // BUTTON: focus gps position
        ImageButton btn_gps = (ImageButton) findViewById(R.id.btn_gps);
        btn_gps.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                mapView.scrollToAndCenter(11700.557141, 6634.540018);
            }
        });

        // BUTTON: track list launcher
        Button btn_list = (Button) findViewById(R.id.btn_tracklist);
        btn_list.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent toList = new Intent(view.getContext(), TrackListActivity.class);
                startActivity(toList);
            }
        });

        setupTileView();
        addDummyDataToMap();
    }

    private void setupTileView()
    {
        mapView = new TileView(this);
        mapView.setSize(256 * 16, 256 * 24);
        // use tile indicies as bounds to prevent gps drift
        mapView.defineBounds(11696.0, 6628.0, 11712.0, 6652.0);

        // most detailed map (=highest zoom number) first
        mapView.addDetailLevel(1.000000f, "tile_14_%d_%d.png", 256, 256);
        mapView.addDetailLevel(0.500000f, "tile_13_%d_%d.png", 256, 256);
        mapView.addDetailLevel(0.250000f, "tile_12_%d_%d.png", 256, 256);

        // add tileview to empty placeholder
        FrameLayout lmap = (FrameLayout) findViewById(R.id.l_map);
        lmap.setBackgroundColor(R.color.colorWhite);
        lmap.addView(mapView);
    }

    private void addDummyDataToMap()
    {
        ArrayList<double[]> path = new ArrayList<double[]>();
        double[] pathPoints = new double[]{
                11702.239687, 6634.551331,
                11701.434596, 6634.388093,
                11700.557141, 6634.540018,
                11700.195783, 6634.274416,
                11700.087012, 6634.013648
        };

        // create beas kund path
        for (int i = 0; i < pathPoints.length; i += 2) {
            path.add(new double[]{pathPoints[i], pathPoints[i + 1]});
        }
        DrawablePath pathView = mapView.drawPath(path, null);

        // configure marker
        ImageView marker = new ImageView(this);
        marker.setImageResource(R.drawable.marker);
        mapView.addMarker(marker, 11700.087012, 6634.013648, -0.5f, -0.5f);
        mapView.setMarkerTapListener(this);
    }


    // configure and launch track detail activity
    public void onMarkerTap(View view, int x, int y) {
        Intent toDetails = new Intent(this, TrackDetailsActivity.class);
        Bundle b = new Bundle();

        // send data over parelable models
        TrackDetails tdetails = new TrackDetails();
        tdetails.name = "Beas Kund";
        tdetails.rating = 4.0f;
        tdetails.location = "Himachal Pradesh";
        b.putParcelable(TrackDetailsActivity.EXTRA_TRACK_DETAILS, tdetails);

        toDetails.putExtras(b);
        startActivity(toDetails);
    }
}
