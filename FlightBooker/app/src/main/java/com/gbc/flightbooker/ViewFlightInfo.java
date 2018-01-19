package com.gbc.flightbooker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.gbc.flightbooker.db.AppDatabase;
import com.gbc.flightbooker.db.Flight;
import com.gbc.flightbooker.utilities.FlightItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewFlightInfo extends Activity {

    AppDatabase db;
    List<Flight> flightList;
    FlightItemAdapter adapter;
    Flight flight;
    String flightId;

    //Displays the flight info (and connecting flight info if there is one)
    // when a booking is selected
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flight_info);

        Bundle extras = getIntent().getExtras();
        flightId = extras.getString("flightID");
        db = AppDatabase.getDatabase(getApplicationContext());

        //Grab Flight by ID. Add it to the list.
        flightList = new ArrayList<>();
        flight = db.flightDao().fetchFlightByID(flightId);
        flightList.add(flight);


        //If there's a connecting flight, add it to the list
        if (!flight.getConnectingFlight().isEmpty())
            flightList.add(db.flightDao().fetchFlightByID(flight.getConnectingFlight()));


        adapter = new FlightItemAdapter(this, flightList);

        //Display list items
        ListView listView = findViewById(R.id.list_flights);
        listView.setAdapter(adapter);

    }
}