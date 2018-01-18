package com.gbc.flightbooker.utilities;

import android.util.Log;
import android.widget.EditText;

import com.gbc.flightbooker.db.AppDatabase;
import com.gbc.flightbooker.db.Flight;

import java.util.Base64;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by nooran on 2017-12-16.
 */



public class Helper {

    //convert from cal to string
    public static String calToString(Calendar cal)
    {
        String calendarString = "";
        calendarString+= cal.get(Calendar.MONTH)+1 + "/" +
                cal.get(Calendar.DAY_OF_MONTH)  + "/" +
                cal.get(Calendar.YEAR) + " " +
                cal.getTime();
        return calendarString;
    }

    //add time to a date
    public static Date addTime(Date date, String time)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int [] times = timeSplit(time);
        cal.add(Calendar.HOUR, times[0]);
        cal.add(Calendar.MINUTE, times[1]);
        return cal.getTime();
    }

    //convert from string to date
    public static Date stringToDate(String dateString)
    {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd/mm/yyyy").parse(dateString);
        }
        catch(ParseException e)
        {
            Log.d("Helper class\n", "stringToTime caught an error: " + e.getMessage());
        }
        return date;
    }
    //convert string to time
    public static Date stringToTime(String timeString)
    {
        Date date = new Date();
        try
        {
            date = new SimpleDateFormat("HH:mm").parse(timeString);
        }
        catch(ParseException e)
        {
            Log.d("Helper class\n", "stringToTime caught an error: " + e.getMessage());
        }
        return date;
    }
    //convert date to string
    public static String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
        return df.format(date);
    }
    //convert time to string
    public static String timeToString(Date date){
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(date);
    }
    //split string time to int hours and minutes
    public static int[] timeSplit(String time)
    {
        String [] timeStrings = time.split(":");
        int [] times = new int[2];
        for(int i =0; i<2; i++)
        {
            times[i] = Integer.parseInt(timeStrings[i]);
        }
        return times;
    }

    public static String txtFromEditText(EditText et)
    //call getText, toString and trim on an Edit text
    {
        return et.getText().toString().trim();
    }
    public static List<Flight> flightsToDestinationByDay(String dest, Date day, AppDatabase db, String sortType)
    //Fetch flights to a destination given a certain date while ignoring time
    {
        Calendar departureDate = Calendar.getInstance();
        Calendar departureDateEnd = Calendar.getInstance();
        departureDate.setTime(day);
        departureDateEnd.setTime(day);
        departureDate.set(Calendar.HOUR_OF_DAY, 0);
        departureDateEnd.set(Calendar.HOUR_OF_DAY, 0);
        // make the search range from n days to n+1 days
        departureDateEnd.add(Calendar.DAY_OF_MONTH, 1);
        // sorted by cost
        if(sortType.equals("cost"))
            return db.flightDao().fetchFlightByCityDateCost(dest, departureDate.getTime(), departureDateEnd.getTime());
        // sorted by duration
        if(sortType.equals("duration"))
            return db.flightDao().fetchFlightByCityDateDuration(dest, departureDate.getTime(), departureDateEnd.getTime());

        return db.flightDao().fetchFlightByCityDate(dest, departureDate.getTime(), departureDateEnd.getTime());


    }
    public static String calculateTotalDuration(Flight flight1, Flight flight2)
    {
        String layover = "00:45:00";
        String duration1 = flight1.getDuration();
        String duration2 = flight2.getDuration();

        Date flightDuration = stringToTime(duration1);

        Date newTime = addTime(flightDuration, layover);
        newTime = addTime(newTime, duration2);

        return timeToString(newTime);

    }
}

