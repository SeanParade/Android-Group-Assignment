package com.gbc.flightbooker.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gbc.flightbooker.db.Customer;

import java.util.List;

/**
 * Created on 12/14/2017.
 */

@Dao
public interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Customer> customer);

    @Delete
    void deleteCustomer(Customer... customer);

    @Query("SELECT * FROM customer")
    List<Customer> fetchAllCustomer();

    @Update
    void updateCustomer();
}
