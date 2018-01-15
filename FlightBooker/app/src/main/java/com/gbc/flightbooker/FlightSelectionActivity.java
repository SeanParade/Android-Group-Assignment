package com.gbc.flightbooker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.gbc.flightbooker.db.AppDatabase;
import com.gbc.flightbooker.db.Flight;
import com.gbc.flightbooker.utilities.ExpandableFlightListAdapter;
import com.gbc.flightbooker.utilities.Helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.sql.Timestamp;

public class FlightSelectionActivity extends Activity {

    AppDatabase db;
    List<Flight> flights;
    String sortType, dest;
    Date depDate;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> flightHeader;
    HashMap<String, List<Flight>> flightChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_selection);

        db = AppDatabase.getDatabase(getApplicationContext());
        Bundle extras = getIntent().getExtras();
        try {
            sortType = extras.getString("sorttype");
            dest = extras.getString("dest");
            depDate = new Date(extras.getLong("depDate"));
            flights = Helper.flightsToDestinationByDay(dest,depDate,db,sortType);

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Something went wrong. \nPlease try again.", Toast.LENGTH_SHORT).show();
        }



    //get Listview
        expListView = findViewById(R.id.expFlightList);
    //put flights into function that populates the expandable list view

        prepareListData(flights);

        listAdapter = new ExpandableFlightListAdapter(this, flightHeader, flightChild);
        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener(){
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id)
            {
                if(expListView.isGroupExpanded(groupPosition))
                {
                    parent.collapseGroup(groupPosition);
                    return true;
                }
                parent.expandGroup(groupPosition);
                return true;
            }
        });

    }


    private void prepareListData(List<Flight> flights)
    {
        flightHeader = new ArrayList<>();
        flightChild = new HashMap<>();

        for( Flight flight: flights)
        {
            List<Flight> flightDetails = new ArrayList<>();
            String flightHeaderString = "";
            if(!flight.getConnectingFlight().isEmpty()) //if flight has a connecting flight
            {
                //get connecting flight from database
                Flight connectingFlight = db.flightDao().fetchFlightByID(flight.getConnectingFlight());

                //gen header string
                flightHeaderString = connectingFlightHeader(flight, connectingFlight);
                //add string to flightheader arraylist
                flightHeader.add(flightHeaderString);
                //add both flights to second list
                flightDetails.add(flight);
                flightDetails.add(connectingFlight);

            }
            else
            {
                String id = flight.getFlightId();
                //check if id is for connecting flight
                if(!id.substring(id.length()-2).equals("-c")) {
                    //gen string flight header
                    flightHeaderString = flight.getFlightHeader();
                    //add to flightheader arraylist
                    flightHeader.add(flightHeaderString);
                    //add flight to second list
                    flightDetails.add(flight);
                }
            }
            //add second list to hashmap
            flightChild.put(flightHeaderString, flightDetails);
        }
    }

    private String connectingFlightHeader(Flight flight1, Flight flight2)
    {
        Double cost = flight1.getCost() + flight2.getCost();
        String duration = "04:30:00";
        String header = "From ";
        header += flight1.getOrigin() + " To " + flight2.getDestination() + " Duration: " + duration + " Cost: $" + cost;
        return header;
    }

}
