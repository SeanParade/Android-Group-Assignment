package com.gbc.flightbooker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gbc.flightbooker.db.AppDatabase;
import com.gbc.flightbooker.db.Booking;
import com.gbc.flightbooker.utilities.BookingItemAdapter;

import java.util.List;

public class ViewBookingsActivity extends Activity {

    AppDatabase db;
    List<Booking> bookingList;
    BookingItemAdapter adapter;
    Booking booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);

        db = AppDatabase.getDatabase(getApplicationContext());

        bookingList = db.bookingDao().fetchAllBookings();
        adapter = new BookingItemAdapter(this, bookingList);

        ListView listView = findViewById(R.id.list_bookings);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                booking = bookingList.get(i);
                Intent intent = new Intent (getApplicationContext(),ViewFlightInfo.class);
                intent.putExtra("flightID", booking.getFlightId());
                startActivity(intent);
            }
        });

    }

}
