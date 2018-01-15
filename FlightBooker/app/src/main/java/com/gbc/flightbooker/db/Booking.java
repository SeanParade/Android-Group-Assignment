package com.gbc.flightbooker.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by nooran on 2017-12-12.
 */
@Entity(tableName="booking", foreignKeys = @ForeignKey(onDelete = CASCADE, entity = Flight.class,
                                                       parentColumns="flightId",
                                                       childColumns = "flight_Id"))
public class Booking {

    @PrimaryKey(autoGenerate=true)
    private int bookingId = 0;

    @ColumnInfo(name="customerId")
    private int customerId;

    @ColumnInfo(name="flight_Id")
    private String flightId;

    @ColumnInfo(name="flight_Header")
    private String flightHeader;

    @ColumnInfo(name="departureDate")
    @TypeConverters({DateConverter.class})
    private Date departureDate;

    @ColumnInfo(name="arrivalDate")
    @TypeConverters({DateConverter.class})
    private  Date arrivalDate;

    @ColumnInfo(name="totalCost")
    private double totalCost;

    @ColumnInfo(name="connectingFlight")
    private String connectingFlight = "";

    //default constructor
    public Booking(){}

    //overloaded constructor
    @Ignore
    public Booking(int bookingId, int customerId, String flightId, String flightHeader, Date departureDate, Date arrivalDate, double totalCost) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.flightId = flightId;
        this.flightHeader = flightHeader;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.totalCost = totalCost;
    }

    //constructor without booking id
    @Ignore
    public Booking(int customerId, String flightId , String flightHeader, Date departureDate, Date arrivalDate, double totalCost) {
        this.customerId = customerId;
        this.flightId = flightId;
        this.flightHeader = flightHeader;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.totalCost = totalCost;
    }

    //constructor with connectingFlight
    //no booking id
    @Ignore
    public Booking(int customerId, String flightId , String flightHeader, Date departureDate, Date arrivalDate, double totalCost, String connectingFlight) {
        this.customerId = customerId;
        this.flightId = flightId;
        this.flightHeader = flightHeader;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.totalCost = totalCost;
        this.connectingFlight = connectingFlight;
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

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
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

    public String getConnectingFlight() {
        return connectingFlight;
    }

    public void setConnectingFlight(String connectingFlight) {
        this.connectingFlight = connectingFlight;
    }
}
