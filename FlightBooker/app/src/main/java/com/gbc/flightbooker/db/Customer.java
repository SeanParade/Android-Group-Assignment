package com.gbc.flightbooker.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by nooran on 2017-12-12.
 */

@Entity(tableName="customer")
public class Customer {
    @PrimaryKey(autoGenerate=true)
    private int customerId = 0;

    @ColumnInfo(name="firstname")
    private String firstName;

    @ColumnInfo(name="lastname")
    private String lastName;

    @ColumnInfo(name="address")
    private String address;

    @ColumnInfo(name="phone")
    private int phone;

    @ColumnInfo(name="credit")
    private int creditCardNumber;

    //overloaded constructor
    public Customer(int customerId, String firstName, String lastName, String address, int phone, int creditCardNumber)
    {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.creditCardNumber = creditCardNumber;
    }

    //constructor without customerId
    public Customer(String firstName, String lastName, String address, int phone, int creditCardNumber)
    {
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
