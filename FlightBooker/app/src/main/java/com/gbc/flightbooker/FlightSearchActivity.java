package com.gbc.flightbooker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.gbc.flightbooker.db.Flight;
import com.gbc.flightbooker.utilities.FlightGenerator;
import com.gbc.flightbooker.utilities.Helper;
import com.gbc.flightbooker.db.AppDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class FlightSearchActivity extends Activity {

    AppDatabase db;
    EditText origin, destination;
    String originTxt, destinationTxt, feedback;
    DatePicker datePicker;
    Date departureDate, d;
    Button searchBtn;
    RadioGroup sortByRadioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search);

        db = AppDatabase.getDatabase(getApplicationContext());

        origin = findViewById(R.id.edit_origin);
        originTxt =  Helper.txtFromEditText(origin);
        destination = findViewById(R.id.edit_destination);
        destinationTxt =  Helper.txtFromEditText(destination);
        datePicker = findViewById(R.id.date_picker);
        sortByRadioGroup = findViewById(R.id.radio_group);

        d = new Date();


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Flight Search Activity\n", "Search button pressed");
                departureDate = getPickerDate(datePicker);
                feedback = "";

                if(originTxt.isEmpty())
                    feedback = "Origin City is Empty! ";
                if(destinationTxt.isEmpty())
                    feedback += "Destination City Empty! ";
                if (d.after(departureDate))
                    feedback += "Departure date is before today! ";

                if(!feedback.equals("")){
                    Toast.makeText(FlightSearchActivity.this,
                            feedback, Toast.LENGTH_SHORT).show();
                }else {
                    try{
                        List<Flight> flights = FlightGenerator.generateFlights(originTxt, destinationTxt, departureDate);
                        List<Flight> existingFlights = db.flightDao().fetchFlightByCityDate(originTxt, departureDate.toString());

                        if(existingFlights.isEmpty()){
                            db.flightDao().insertAll(flights);
                            Log.d("Flight Search Activity", "Flights inserted.");
                        }

                        startActivity(new Intent(v.getContext(), FlightSelectionActivity.class));

                    }catch (Exception e){
                        Toast.makeText(FlightSearchActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    public static Date getPickerDate (DatePicker datePicker ) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
