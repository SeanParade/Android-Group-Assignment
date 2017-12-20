package com.gbc.flightbooker;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gbc.flightbooker.db.AppDatabase;
import com.gbc.flightbooker.db.Flight;

import java.util.List;

public class FlightSelectionActivity extends Activity {

    AppDatabase db;
    List<Flight> flights;
    boolean isSortedByCost;
    boolean isSortedByDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_selection);

        db = AppDatabase.getDatabase(getApplicationContext());

        Bundle extras = getIntent().getExtras();
        isSortedByCost = extras.getBoolean("cost");
        isSortedByDuration = extras.getBoolean("duration");

    if(isSortedByCost)
    {
        //flights =
    }
    else if(isSortedByDuration)
    {
        //flights =
    }

    //put flights into function that populates the expandable list view

    }
}
