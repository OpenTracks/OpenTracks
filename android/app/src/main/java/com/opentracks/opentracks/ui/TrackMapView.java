package com.opentracks.opentracks.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.opentracks.opentracks.R;
import com.opentracks.opentracks.model.TrackDetails;
import com.qozix.tileview.TileView;
import com.qozix.tileview.paths.CompositePathView.DrawablePath;

/*

TrackMapView is displayed in MapActivity. It consists of:
    DrawablePath track created by TileView
    Bitmap marker positioned at endpoint of track
    TextView displaying duration of track

*/

public class TrackMapView extends View{

    // resource ID of marker bitmap
    final static int id_marker = R.drawable.marker;

    public TrackDetails details;

    public Bitmap marker;
    public DrawablePath drawablePath;

    private Paint mTextPaint;

    public TrackMapView(Context context) {
        super(context);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(R.color.colorPrimaryDark);
        mTextPaint.setTextSize(50);
    }

    // Static Constructor
    public static TrackMapView addToMap(Context context, TileView map, TrackDetails details)
    {
        if(details.path == null || details.path.size() < 2){return null;}

        TrackMapView view = new TrackMapView(context);
        view.details = details;
        view.drawablePath = map.drawPath(details.path, null);
        view.marker = BitmapFactory.decodeResource(context.getResources(), id_marker);

        double[] lastWaypoint = details.path.get(details.path.size() - 1);
        map.addMarker(view, lastWaypoint[0], lastWaypoint[1], -0.5f, -1.0f);
        return view;
    }

    // TODO: remove hard-coded dimensions
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(100, 60);
    }

    // TODO: proper layouting
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(marker, -18, -45, null);

        String text = "";
        if(details.duration < 24)
        {
            text = String.valueOf(details.duration) + "h";
        }
        else
        {
            text = String.valueOf(Math.floor(details.duration / 24)) + "d";
        }

        canvas.drawText(text, 2, 25, mTextPaint);
    }

    // TODO: removePath() bug
    public void destroy(TileView map)
    {
        map.removeMarker(this);
        map.removePath(drawablePath);
    }
}
