package com.gbc.flightbooker;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.gbc.flightbooker.db.AppDatabase;
import com.gbc.flightbooker.db.Flight;
import com.gbc.flightbooker.utilities.ExpandableFlightListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlightSelectionActivity extends Activity {

    AppDatabase db;
    List<Flight> flights;
    boolean isSortedByCost;
    boolean isSortedByDuration;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> flightHeader;
    HashMap<String, List<String>> flightChild;

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

    //get Listview
        expListView = (ExpandableListView)findViewById(R.id.expFlightList);
    //put flights into function that populates the expandable list view

        //prepareListData(flights);

        listAdapter = new ExpandableFlightListAdapter(this, flightHeader, flightChild);
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData(List<Flight> flights)
    {
        flightHeader = new ArrayList<String>();
        flightChild = new HashMap<String, List<String>>();

        for( Flight flight: flights)
        {
            if(flight.getConnectingFlight()!=0) //if flight has a connecting flight
            {
                //pseudocode
                //String flightHeader = Toronto to Miami + other details
                //add to flightHeader

            }
            else
            {
                //pseudocode
                //flightHeader.add(flight.flightHeaderString())
            }
            //add flight data to second List, add list to hashmap
        }
    }

}
