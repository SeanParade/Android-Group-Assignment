package com.gbc.flightbooker.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gbc.flightbooker.R;
import com.gbc.flightbooker.db.Booking;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class BookingItemAdapter extends ArrayAdapter<Booking> {

    List<Booking> bookingsList;
    LayoutInflater inflater;

    //Adapter that grabs all bookings and display them to the user

    public BookingItemAdapter(@NonNull Context context, @NonNull List<Booking> objects) {
        super(context, R.layout.list_booking, objects);

        bookingsList = objects;
        inflater = LayoutInflater.from(context);

    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Inflate the booking list
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_booking, parent, false);
        }

        //Get all objects in the view
        TextView tvHeader = convertView.findViewById(R.id.flight_header);
        TextView tvDepartureDate = convertView.findViewById(R.id.arrival_date);
        TextView tvArrivalDate = convertView.findViewById(R.id.departure_date);
        TextView tvTotalCost = convertView.findViewById(R.id.total_cost);

        Booking booking = bookingsList.get(position);

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        //Set booking information to each object in the view
        tvHeader.setText(booking.getFlightHeader());
        tvDepartureDate.setText(df.format(booking.getDepartureDate()));
        tvArrivalDate.setText(df.format(booking.getArrivalDate()));
        tvTotalCost.setText(Double.toString(booking.getTotalCost()));


        return convertView;
    }
}

