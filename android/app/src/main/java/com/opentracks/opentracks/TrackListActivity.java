package com.opentracks.opentracks;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.opentracks.opentracks.R;

public class TrackListActivity extends Activity implements View.OnClickListener{


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracklist);

        findViewById(R.id.btn_listback).setOnClickListener(this);
    }

    public void onClick(View view) {
        finish();
    }
}
