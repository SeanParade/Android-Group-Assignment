package com.gbc.flightbooker.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by nooran on 2017-12-12.
 */

@Entity(tableName="customer")
public class Customer {
    @PrimaryKey(autoGenerate=false)
    private int customerId = 1; // customer id is always the same since there is only one customer

    @ColumnInfo(name="firstname")
    private String firstName;

    @ColumnInfo(name="lastname")
    private String lastName;

    @ColumnInfo(name="address")
    private String address;

    @ColumnInfo(name="phone")
    private String phone;

    @ColumnInfo(name="credit")
    private String creditCardNumber;

    public Customer(){}

    //overloaded constructor
    @Ignore
    public Customer(String firstName, String lastName, String address, String phone, String creditCardNumber)
    {
        this.customerId = 1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.creditCardNumber = creditCardNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
