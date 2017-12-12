package com.gbc.flightbooker.db;

import java.util.ArrayList;

/**
 * Created by nooran on 2017-12-12.
 */
//@Entity(tableName="booking")
public class Booking {

    //@PrimaryKey(autoGenerate=true)
    private int bookingId = 0;

    //@ColumnInfo(name="customerId")
    private int customerId;

    private int[] flightIds;

    //@ColumnInfo(name="departureDate")
    private String departureDate;

    //@ColumnInfo(name="arrivalDate")
    private String arrivalDate;

    //@ColumnInfo(name="totalCost")
    private double totalCost;

    //overloaded constructor
    public Booking(int bookingId, int customerId, int[] flightIds, String departureDate, String arrivalDate, double totalCost) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.flightIds = flightIds;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.totalCost = totalCost;
    }

    //constructor without booking id
    public Booking(int customerId, int[] flightIds, String departureDate, String arrivalDate, double totalCost) {
        this.customerId = customerId;
        this.flightIds = flightIds;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.totalCost = totalCost;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int[] getFlightIds() {
        return flightIds;
    }

    public void setFlightIds(int[] flightIds) {
        this.flightIds = flightIds;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
