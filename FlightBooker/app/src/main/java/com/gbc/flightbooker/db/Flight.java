package com.gbc.flightbooker.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by nooran on 2017-12-12.
 */
@Entity(tableName="flight")
public class Flight {

    @PrimaryKey(autoGenerate=true)
    private int flightId = 0;

    @ColumnInfo(name="airline")
    private String airline;

    @ColumnInfo(name="departuretime")
    private int departureTime;

    @ColumnInfo(name="arrivaltime")
    private int arrivalTime;

    @ColumnInfo(name="origin")
    private String origin;

    @ColumnInfo(name="destination")
    private String destination;

    @ColumnInfo(name="duration")
    private int duration;

    @ColumnInfo(name="cost")
    private double cost;

    //overloaded constructor
    public Flight(int flightId, String airline, int departureTime, int arrivalTime, String origin, String destination, int duration, double cost) {
        this.flightId = flightId;
        this.airline = airline;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.cost = cost;
    }

    //constructor without flight id
    public Flight(String airline, int departureTime, int arrivalTime, String origin, String destination, int duration, double cost) {
        this.airline = airline;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.cost = cost;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
