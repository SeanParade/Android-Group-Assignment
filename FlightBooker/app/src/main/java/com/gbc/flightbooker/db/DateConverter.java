package com.gbc.flightbooker.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by nooran on 2017-12-20.
 */

public class DateConverter {

    @TypeConverter
    public static Date fromTimestamp(Timestamp value){
        if(value!=null)
        {
            try{
                return new Date(value.getTime());
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
        else {
            return null;
        }
    }

    @TypeConverter
    public static Long fromDate(Date value)
    {
        if(value!=null)
        {
            try{
                return value.getTime();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
