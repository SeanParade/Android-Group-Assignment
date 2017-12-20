package com.gbc.flightbooker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.gbc.flightbooker.db.AppDatabase;
import com.gbc.flightbooker.db.Flight;
import com.gbc.flightbooker.utilities.FlightItemAdapter;

import java.util.List;

public class ViewFlightInfo extends Activity {

    AppDatabase db;
    List<Flight> flightList;
    FlightItemAdapter adapter;
    Flight flight;
    int flightId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flight_info);

        Bundle extras = getIntent().getExtras();
        flightId = extras.getInt("flightId");
        db = AppDatabase.getDatabase(getApplicationContext());

        //Grab Flight by ID. Add it to the list.
        flightList = db.flightDao().fetchFlightByID(flightId);
        flight = flightList.get(0);

        //If there's a connecting flight, add it to the list
        if (flight.getConnectingFlight() != 0){
            List<Flight> linkFlight = db.flightDao().fetchFlightByID(flight.getConnectingFlight());
            flightList.add(linkFlight.get(0));
        }

        adapter = new FlightItemAdapter(this, flightList);

        //Display list items
        ListView listView = findViewById(R.id.list_flights);
        listView.setAdapter(adapter);

    }
}