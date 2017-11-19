package com.opentracks.opentracks.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;


/*

Every activity depends on TrackDetails, but differs in visualization
An activity should only request the parameters necessary for it to display
When requesting TrackDetails, response data should be merged into existing TrackDetails
with same ID through a request manager

*/

public class TrackDetails implements Parcelable {

    // PARCELABLE CREATOR
    public static final Parcelable.Creator<TrackDetails> CREATOR = new Parcelable.Creator<TrackDetails>() {
        @Override
        public TrackDetails createFromParcel(Parcel source) {
            return new TrackDetails(source);
        }
        @Override
        public TrackDetails[] newArray(int size) {
            return new TrackDetails[size];
        }
    };

    // TODO: replace with UUID
    public int ID;

    public String name;
    public float rating;
    public String location;

    public float duration;
    public int difficulty;
    public String imgPreviewURL;

    public ArrayList<float[]> pathPreview;

    public ArrayList<float[]> path;
    public float[] elevationProfile;


    public TrackDetails()
    {

    }

    // Constructor from Parcel
    private TrackDetails(Parcel source)
    {
        name = source.readString();
        rating = source.readFloat();
        location = source.readString();
    }

    @Override
    public int describeContents(){ return super.hashCode(); }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(name);
        parcel.writeFloat(rating);
        parcel.writeString(location);
    }
}
