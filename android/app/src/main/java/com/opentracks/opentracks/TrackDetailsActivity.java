package com.opentracks.opentracks;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.opentracks.opentracks.model.TrackDetails;


public class TrackDetailsActivity extends Activity implements View.OnClickListener {

    final static String EXTRA_TRACK_DETAILS = "TRACK_DETAILS";

    TextView text_name;
    RatingBar rating_track;
    TextView text_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackdetails);

        // UI: bind unique elements
        text_name = findViewById(R.id.text_trackname);
        rating_track = findViewById(R.id.rating_track);
        text_location = findViewById(R.id.text_tracklocation);

        // UI RATING: change rating appearance
        LayerDrawable stars = (LayerDrawable) rating_track.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        // UI BUTTON: back
        findViewById(R.id.btn_detailback).setOnClickListener(this);


        Bundle b = getIntent().getExtras();
        TrackDetails details = b.getParcelable(EXTRA_TRACK_DETAILS);
        setDetails(details);
    }

    public void setDetails(TrackDetails details)
    {
        text_name.setText(details.name);
        rating_track.setRating(details.rating);
        text_location.setText(details.location);
    }

    public void onClick(View view) {
        finish();
    }
}
