package com.opentracks.opentracks.db;

import java.util.ArrayList;

import com.opentracks.opentracks.model.TrackDetails;


/*
SpatiaLiteAPI is a Singleton API, which stores and returns a set of hard-coded TrackDetails
TODO: Link against actual SpatiaLite Database library
TODO: Dynamically cache downloaded TrackDetails
*/

public class SpatiaLiteAPI {

    // Singleton pattern
    private static SpatiaLiteAPI instance;
    public static SpatiaLiteAPI getInstance()
    {
        if(instance == null)
        {
            instance = new SpatiaLiteAPI();
            instance.init();
        }
        return instance;
    }

    // hard-coded tracks
    private ArrayList<TrackDetails> tracks = new ArrayList<TrackDetails>();

    public SpatiaLiteAPI(){

    }



    public void init()
    {
        addDummyTrack01();
        addDummyTrack02();
    }

    private void addDummyTrack01()
    {
        ArrayList<double[]> path = new ArrayList<double[]>();
        double[] pathPoints = new double[]{
                11702.239687, 6634.551331,
                11701.434596, 6634.388093,
                11700.557141, 6634.540018,
                11700.195783, 6634.274416,
                11700.087012, 6634.013648
        };
        for (int i = 0; i < pathPoints.length; i += 2) {
            path.add(new double[]{pathPoints[i], pathPoints[i + 1]});
        }

        TrackDetails tdetails = new TrackDetails();
        tdetails.name = "Beas Kund";
        tdetails.rating = 4.0f;
        tdetails.location = "Himachal Pradesh";
        tdetails.duration = 48;
        tdetails.path = path;

        tracks.add(tdetails);
    }

    private void addDummyTrack02()
    {
        ArrayList<double[]> path = new ArrayList<double[]>();
        double[] pathPoints = new double[]{
                11704.705074062222, 6639.711917716629,
                11704.409665991112, 6639.5097694619035,
                11704.036620515555, 6639.068848221179,
                11704.10253880889, 6638.467288531317,
                11703.992674986668, 6636.732669525168,
                11704.433345422223, 6637.637942721179,
                11704.71288832, 6639.709474464618,
        };
        for (int i = 0; i < pathPoints.length; i += 2) {
            path.add(new double[]{pathPoints[i], pathPoints[i + 1]});
        }

        TrackDetails tdetails = new TrackDetails();
        tdetails.name = "Manali Hike";
        tdetails.rating = 3.0f;
        tdetails.location = "Himachal Pradesh";
        tdetails.duration = 3;
        tdetails.path = path;

        tracks.add(tdetails);
    }

    // TODO: add GPS location and radius/zoom as method parameter
    public ArrayList<TrackDetails> getNearbyTracks()
    {
        return tracks;
    }
}
