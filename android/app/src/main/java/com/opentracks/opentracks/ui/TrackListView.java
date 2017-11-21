package com.opentracks.opentracks.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import com.opentracks.opentracks.R;
import com.opentracks.opentracks.TrackDetailsActivity;
import com.opentracks.opentracks.model.TrackDetails;


/*

TrackListView is an item of the TrackListAcitivy ScrollView. It consists of:
    TextView name
    RatingBar rating
    TextView duration
    TextView difficulty

    Bitmap preview

*/

public class TrackListView extends View {

    public TrackDetails details;

    private Paint mTextPaint;

    public TrackListView(Context context, TrackDetails details, OnClickListener onClickCallback)
    {
        super(context);

        this.details = details;
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(R.color.colorPrimaryDark);
        mTextPaint.setTextSize(42);

        setOnClickListener(onClickCallback);
    }

    // TODO: remove hard-coded dimensions
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(700, 180);
    }

    // TODO: proper layouting
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(details.name, 30, 60, mTextPaint);
        canvas.drawText(String.valueOf(details.duration), 30, 100, mTextPaint);
        canvas.drawText(details.location, 30, 140, mTextPaint);
    }

}
