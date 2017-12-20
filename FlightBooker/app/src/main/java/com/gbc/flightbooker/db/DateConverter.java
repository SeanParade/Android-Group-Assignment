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
    static DateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");

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

}
