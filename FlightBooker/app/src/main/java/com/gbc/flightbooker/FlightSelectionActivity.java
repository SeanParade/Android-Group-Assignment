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
        //get flights from database sorted by cost
        //flights =
    }
    else if(isSortedByDuration)
    {
        //get flights from database sorted by duration
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
            List<String> flightDetails = new ArrayList<String>();
            String flightHeaderString = "";
            if(flight.getConnectingFlight()!=0) //if flight has a connecting flight
            {
                flightHeaderString = flight.getConnectingFlightHeader();
                flightHeader.add(flightHeaderString);

                //add flight details for both flights to second list

            }
            else
            {
                flightHeaderString = flight.getFlightHeader();
                flightHeader.add(flightHeaderString);
                flightDetails.add(flight.getFlightDetail());

            }
            //add second list to hashmap
            flightChild.put(flightHeaderString, flightDetails);
        }
    }

}
