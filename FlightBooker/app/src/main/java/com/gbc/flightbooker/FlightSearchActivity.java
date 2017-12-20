package com.gbc.flightbooker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.gbc.flightbooker.utilities.Helper;
import com.gbc.flightbooker.db.AppDatabase;
import com.gbc.flightbooker.utilities.FlightGenerator;

import java.util.Date;



public class FlightSearchActivity extends Activity {

    AppDatabase db;
    EditText e1,e2;
    String e1Txt, e2Txt;
    CalendarView cal;
    Date departureDate;
    Button searchBtn;
    Date d;
    String feedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_search);

        db = AppDatabase.getDatabase(getApplicationContext());

        e1 = findViewById(0);
        e1Txt =  Helper.txtFromEditText(e1);
        e2 = findViewById(0);
        e2Txt =  Helper.txtFromEditText(e2);
        cal = findViewById(0);
        d = new Date();


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Flight Search Activity\n", "Search button pressed");
                departureDate = new Date(cal.getDate());
                feedback = "";

                if(e1Txt.isEmpty())
                    feedback = "Origin City is Empty! ";
                if(e2Txt.isEmpty())
                    feedback += "Destination City Empty! ";
                if (d.after(departureDate))
                    feedback += "Departure date is before today! ";

                if(!feedback.equals("")){
                    Toast.makeText(FlightSearchActivity.this,
                            feedback, Toast.LENGTH_SHORT).show();
                }else {
                    try{

                    }catch (Exception e){
                        Toast.makeText(FlightSearchActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    startActivity(new Intent(v.getContext(), FlightSelectionActivity.class));
                }
            }
        });

    }
}
