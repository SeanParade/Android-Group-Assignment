
package com.gbc.flightbooker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gbc.flightbooker.db.AppDatabase;
import com.gbc.flightbooker.db.Customer;

public class MenuActivity extends Activity {
    private static final String TAG = "Main Menu";
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        db = AppDatabase.getDatabase(getApplicationContext());

        // Drop flights table to clear testing data
        db.flightDao().deleteAllFlights();
        //Make a fake user if they don't exist
        if(db.customerDao().getCustomer() == null){
            Log.d("Entry Point", "Creating test user");
            Customer c = new Customer("John", "Smith",
                    "123 Testing St. Toronto, ON M6G1Z5", "416-555-5555",
                    "5555555555555555");
            db.customerDao().insert(c);
        }

        Button bookflightBtn = findViewById(R.id.book_flight);
        Button viewBookingsBtn = findViewById(R.id.view_bookings);
        Button userInformationBtn = findViewById(R.id.user_information);

        bookflightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Book Flight pressed");
                startActivity(new Intent(v.getContext(), FlightSearchActivity.class));
            }
        });

        viewBookingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "View bookings pressed");
                startActivity(new Intent(v.getContext(), ViewBookingsActivity.class));
            }
        });

        userInformationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "User Information pressed");
                startActivity(new Intent(v.getContext(), UserInfoActivity.class));
            }
        });



    }


}
