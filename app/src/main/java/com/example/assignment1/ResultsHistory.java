package com.example.assignment1;

import android.app.Application;

import java.util.ArrayList;

public class ResultsHistory extends Application {

    /*
        to store activity state at application level
        so history is saved even when the phone rotates
        and the activity is recreated
     */
    ArrayList<String> results = new ArrayList<>(); //to store history of calculations
    boolean isHistoryVisible = false; //to store the visibility of history
}
