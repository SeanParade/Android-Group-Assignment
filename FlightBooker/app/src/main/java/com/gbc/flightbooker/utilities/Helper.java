package com.gbc.flightbooker.utilities;

<<<<<<< HEAD
=======
import android.widget.EditText;

import com.gbc.flightbooker.db.Flight;
>>>>>>> 647a9bf342c1ebb46f776343a0347a6e8a952794

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

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
    public static String addTime(String dateString, String time)
    {
        Date date = stringToDate(dateString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int [] times = timeSplit(time);
        cal.add(Calendar.HOUR, times[0]);
        cal.add(Calendar.MINUTE, times[1]);
        return calToString(cal);
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
            //do nothing?
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
            //do nothing
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
        int [] times = new int[1];
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
}
