package com.gbc.flightbooker.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by nooran on 2017-12-12.
 */
@Entity(tableName="booking", foreignKeys = @ForeignKey(onDelete = CASCADE, entity = Flight.class,
                                                       parentColumns="flightID",
                                                       childColumns = "flight_ID"))
public class Booking {

    @PrimaryKey(autoGenerate=true)
    private int bookingId = 0;

    @ColumnInfo(name="customerId")
    private int customerId;

    @ColumnInfo(name="flight_Id")
    private int flightId;

    @ColumnInfo(name="flight_Header")
    private String flightHeader;

    @ColumnInfo(name="departureDate")
    private String departureDate;

    @ColumnInfo(name="arrivalDate")
    private String arrivalDate;

    @ColumnInfo(name="totalCost")
    private double totalCost;

    //default contructor
    public Booking(){}

    //overloaded constructor
    public Booking(int bookingId, int customerId, int flightId, String flightHeader, String departureDate, String arrivalDate, double totalCost) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.flightId = flightId;
        this.flightHeader = flightHeader;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.totalCost = totalCost;

    }

    //constructor without booking id
    public Booking(int customerId, int flightId , String flightHeader, String departureDate, String arrivalDate, double totalCost) {
        this.customerId = customerId;
        this.flightId = flightId;
        this.flightHeader = flightHeader;
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

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
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

    public String getFlightHeader() {
        return flightHeader;
    }

    public void setFlightHeader(String flightHeader) {
        this.flightHeader = flightHeader;
    }
}
