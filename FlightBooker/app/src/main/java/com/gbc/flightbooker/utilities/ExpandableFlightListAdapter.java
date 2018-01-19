package com.gbc.flightbooker.utilities;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gbc.flightbooker.MenuActivity;
import com.gbc.flightbooker.R;
import com.gbc.flightbooker.db.AppDatabase;
import com.gbc.flightbooker.db.Booking;
import com.gbc.flightbooker.db.Flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nooran on 2017-12-20.
 */

public class ExpandableFlightListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> flightHeader;
    private HashMap<String, List<Flight>> flightChild;
    Booking booking;
    AppDatabase db;

    public ExpandableFlightListAdapter(Context context, List<String> flightHeader,
                                       HashMap<String, List<Flight>> flightChild)
    {
        this.context = context;
        this.flightHeader = flightHeader;
        this.flightChild = flightChild;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition)
    {
        return this.flightChild.get(this.flightHeader.get(listPosition)).get(expandedListPosition);

    }
    @Override
    public long getChildId(int listPosition, int expandedListPosition)
    {
        return expandedListPosition;
    }
    @Override
    public View getChildView(int listPosition, int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent)
    {
        Flight flightDetails =  (Flight)getChild(listPosition, expandedListPosition);

        if(convertView== null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedTextView = convertView.findViewById(R.id.expandedListItem);
        expandedTextView.setText(flightDetails.getFlightDetail());
        return convertView;
    }
    @Override
    public int getChildrenCount(int listPosition)
    {
        return this.flightChild.get(this.flightHeader.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition)
    {
        return this.flightHeader.get(listPosition);
    }

    @Override
    public int getGroupCount()
    {
        return this.flightHeader.size();
    }
    @Override
    public long getGroupId(int listPosition)
    {
        return listPosition;
    }

    @Override
    public View getGroupView(final int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent)
    {
        String headerTitle = (String)getGroup(listPosition);
        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }

        TextView listHeader = (TextView) convertView.findViewById(R.id.expandedListHeader);
        listHeader.setText(headerTitle);

        /*
        * When a Flight is selected from the Flight Search activity
        * The list expands and shows the detail for the flight(s)
        * This lets you book that flight when you click the Book Flight button
        */
        Button btn1 = (Button) convertView.findViewById(R.id.btn_book_flight);

        //When group expanded make button visible
        if (isExpanded) {
            btn1.setVisibility(View.VISIBLE);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                    public void onClick(View view) {

                    //Grab header from the flight that was clicked on
                    String header = flightHeader.get(listPosition);

                    //Get the list of flights by searching the hashmap keys
                    //The key for the hashmap is a string that contains the flight header
                    List<Flight> flights = flightChild.get(header);
                    db = AppDatabase.getDatabase(view.getContext());

                    //Check if booking exists
                    //Prevents duplicates
                    List<Booking> isBooked = db.bookingDao().fetchBookingByID(flights.get(0).getFlightId());
                    //If not booked
                   if (isBooked.isEmpty()) {

                       //Create new booking object
                       //If there is a connecting flight get the arrival date, add the costs of both flights
                       //and add the connecting Flight ID to the booking obejct
                       //Push to DB and let the user know they were successful
                        booking = new Booking();
                        booking.setCustomerId(1);
                        booking.setFlightId(flights.get(0).getFlightId());
                        booking.setFlightHeader(header);
                        booking.setDepartureDate(flights.get(0).getDepartureDate());
                        if (flights.size() == 2) {
                            booking.setArrivalDate(flights.get(1).getArrivalDate());
                            booking.setTotalCost(flights.get(0).getTotalCost() + flights.get(1).getTotalCost());
                            booking.setConnectingFlight(flights.get(0).getConnectingFlight());
                        } else {
                            booking.setArrivalDate(flights.get(0).getArrivalDate());
                            booking.setTotalCost(flights.get(0).getTotalCost());
                        }

                        db.bookingDao().insert(booking);

                        Toast.makeText(view.getContext(), "Flight booked!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(view.getContext(), "You have already booked that flight", Toast.LENGTH_SHORT).show();
                    }




                }
            });
        }
        //Hide button when group is closed
        else if (!isExpanded) {
            btn1.setVisibility(View.INVISIBLE);
        }



        return convertView;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }
    @Override
    public boolean isChildSelectable(int listPosition, int childPosition)
    {
        return false;
    }

}
