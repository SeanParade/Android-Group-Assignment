package com.gbc.flightbooker.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.gbc.flightbooker.db.Booking;


import java.util.List;

/**
 * Created on 12/14/2017.
 */
@Dao
public interface BookingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Booking> booking);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Booking booking);

    @Delete
    void deleteBookings(Booking... bookings);

    @Query("SELECT * FROM booking")
    List<Booking> fetchAllBookings();

    @Query("SELECT * FROM booking WHERE bookingId = :id")
    List<Booking> fetchBookingByID(int id);

    @Query("Delete FROM booking WHERE bookingId = :id")
    void deleteBookingByID(int id);

}
