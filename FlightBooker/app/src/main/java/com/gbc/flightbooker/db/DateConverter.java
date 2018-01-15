package com.gbc.flightbooker.db;

import android.arch.persistence.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nooran on 2017-12-20.
 */

public class DateConverter {
    static DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @TypeConverter
    public static Date fromString(String value){
        if(value!=null)
        {
            try{
                return df.parse(value);
            }
            catch(ParseException e)
            {
                //?
                return null;
            }
        }
        else {
            return null;
        }
    }

    @TypeConverter
    public static String fromDate(Date value)
    {
        String dateString = "";
        if(value!=null)
        {
            try{
                dateString = df.format(value);
            }
            catch(Exception e)
            {
                return dateString;
            }

        }
        return dateString;
    }
}
