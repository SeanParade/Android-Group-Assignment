package com.gbc.flightbooker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.gbc.flightbooker.db.AppDatabase;
import com.gbc.flightbooker.db.Flight;
import com.gbc.flightbooker.utilities.FlightGenerator;
import com.gbc.flightbooker.utilities.Helper;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class FlightSearchActivity extends Activity {

    AppDatabase db;
    EditText origin, destination;
    String originTxt, destinationTxt, feedback;
    DatePicker datePicker;
    Date today, departureDate;
    Button searchBtn;
    RadioGroup sortByRadioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search);

        db = AppDatabase.getDatabase(getApplicationContext());
        origin = findViewById(R.id.edit_origin);
        destination = findViewById(R.id.edit_destination);
        datePicker = findViewById(R.id.date_picker);
        sortByRadioGroup = findViewById(R.id.radio_group);
        searchBtn = findViewById(R.id.submit_button);


        today = new Date();


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Flight Search Activity\n", "Search button pressed");
                originTxt =  Helper.txtFromEditText(origin);
                destinationTxt =  Helper.txtFromEditText(destination);
                // create time stamp from date picker
                departureDate = getPickerDate(datePicker);

                feedback = "";

                if(originTxt.equals(""))
                    feedback = "Origin City is Empty! ";
                if(destinationTxt.equals(""))
                    feedback += "Destination City Empty! ";
                if (today.after(departureDate))
                    feedback += "Departure date is before today! ";

                if(!feedback.equals("")){
                    Toast.makeText(FlightSearchActivity.this,
                            feedback, Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        try
                        {
                            List<Flight> existingFlights =
                                    Helper.flightsToDestinationByDay(destinationTxt,departureDate,db,"cost");
                            List<Flight> flights = FlightGenerator.generateFlights(originTxt, destinationTxt, departureDate);
                            List<Flight> tester = db.flightDao().fetchAllFlights();
                            if(existingFlights.isEmpty())
                            {
                                db.flightDao().insertAll(flights);
                                Log.d("FlightSearchActivity", "Flights inserted.");
                            }

                            int selectedID = sortByRadioGroup.getCheckedRadioButtonId();
                            RadioButton selected = findViewById(selectedID);
                            String sortType = selected.getText().toString().toLowerCase();
                            Intent intent = new Intent(v.getContext(), FlightSelectionActivity.class);
                            intent.putExtra("sorttype", sortType);
                            intent.putExtra("dest", destinationTxt);
                            intent.putExtra("depDate", departureDate.getTime());
                            startActivity(intent);

                        }
                        catch (Exception e)
                        {
                            Toast.makeText(FlightSearchActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            Log.d("FlightSearchActivity", "Exception :( \n" + e.getMessage());
                        }
                    }
            }
        });

    }

    //Returns the selected date from DatePicker View in a Date object
    public static Date getPickerDate (DatePicker datePicker ) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
