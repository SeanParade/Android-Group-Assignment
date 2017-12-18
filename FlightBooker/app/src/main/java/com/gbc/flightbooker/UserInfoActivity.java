package com.gbc.flightbooker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gbc.flightbooker.db.AppDatabase;
import com.gbc.flightbooker.utilities.BookingItemAdapter;

public class UserInfoActivity extends AppCompatActivity {

    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        db = AppDatabase.getDatabase(getApplicationContext());

    }
}
