package com.gbc.flightbooker.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.gbc.flightbooker.db.Customer;

/**
 * Created on 12/14/2017.
 */

@Dao
public interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Customer customer);

    @Query("SELECT * FROM customer")
    Customer getCustomer();

    @Update
    void updateCustomer(Customer... customers);
}
