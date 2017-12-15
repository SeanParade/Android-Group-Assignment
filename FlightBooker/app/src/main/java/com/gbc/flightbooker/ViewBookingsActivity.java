package com.gbc.flightbooker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gbc.flightbooker.db.AppDatabase;

public class ViewBookingsActivity extends AppCompatActivity {

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);

        db = AppDatabase.getDatabase(getApplicationContext());
    }
}
