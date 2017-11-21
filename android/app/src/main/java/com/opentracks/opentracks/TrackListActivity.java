package com.opentracks.opentracks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.opentracks.opentracks.R;
import com.opentracks.opentracks.db.SpatiaLiteAPI;
import com.opentracks.opentracks.model.TrackDetails;
import com.opentracks.opentracks.ui.TrackListView;
import com.opentracks.opentracks.ui.TrackMapView;

import java.util.ArrayList;

public class TrackListActivity extends Activity implements View.OnClickListener{

    TableLayout tableView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracklist);

        // configure scroll view
        tableView = findViewById(R.id.scroll_table);
        tableView.removeAllViews();
        fillList(SpatiaLiteAPI.getInstance().getNearbyTracks());

        findViewById(R.id.btn_listback).setOnClickListener(this);
    }

    private void fillList(ArrayList<TrackDetails> tracks)
    {
        for(int i = 0; i < tracks.size(); i++)
        {
            TableRow row = new TableRow(getApplicationContext());
            row.addView(new TrackListView(getApplicationContext(), tracks.get(i), this));
            tableView.addView(row);
        }
    }

    public void onClick(View view)
    {
        if(view instanceof TrackListView)
        {
            onClickItem((TrackListView)view);
        }
        finish();
    }


    public void onClickItem(TrackListView view) {
        Intent toDetails = new Intent(this, TrackDetailsActivity.class);
        Bundle b = new Bundle();

        // send data over parelable models
        b.putParcelable(TrackDetailsActivity.EXTRA_TRACK_DETAILS, view.details);

        toDetails.putExtras(b);
        startActivity(toDetails);
    }
}
