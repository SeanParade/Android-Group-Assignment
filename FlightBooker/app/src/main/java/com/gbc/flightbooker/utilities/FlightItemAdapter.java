package com.gbc.flightbooker.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gbc.flightbooker.R;
import com.gbc.flightbooker.db.Flight;

import java.util.List;

/**
 * Created by Dylan on 2017-12-18.
 */

public class FlightItemAdapter extends ArrayAdapter<Flight> {


    List<Flight> flightList;
    LayoutInflater inflater;

    public FlightItemAdapter(Context context, List<Flight> objects){
        super(context, R.layout.list_flight, objects);

        flightList = objects;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_flight, parent, false);
        }
        TextView tvAirline = convertView.findViewById(R.id.airline);
        TextView tvDeparture = convertView.findViewById(R.id.departure);
        TextView tvArrival = convertView.findViewById(R.id.arrival);
        TextView tvOrigin = convertView.findViewById(R.id.origin);
        TextView tvDestination = convertView.findViewById(R.id.destination);
        TextView tvDuration = convertView.findViewById(R.id.duration);
        TextView tvCost = convertView.findViewById(R.id.cost);

        Flight flight = flightList.get(position);

        tvAirline.setText(flight.getAirline());
        tvDeparture.setText(flight.getDepartureDate());
        tvArrival.setText(flight.getArrivalDate());
        tvOrigin.setText(flight.getOrigin());
        tvDestination.setText(flight.getDestination());
        tvDuration.setText(flight.getDuration());
        tvCost.setText(Double.toString(flight.getCost()));

        return convertView;
    }
}
