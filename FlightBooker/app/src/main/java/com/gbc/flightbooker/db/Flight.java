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

    @ColumnInfo(name="departure")
    private String departureDate;

    @ColumnInfo(name="arrival")
    private String arrivalDate;

    @ColumnInfo(name="origin")
    private String origin;

    @ColumnInfo(name="destination")
    private String destination;

    @ColumnInfo(name="duration")
    private String duration;

    @ColumnInfo(name="cost")
    private double cost;

    @ColumnInfo(name="link")
    private int connectingFlight=0;

    //overloaded constructor
    public Flight(int flightId, String airline, String departureDate, String arrivalDate, 
                  String origin, String destination, String duration, double cost) {
        this.flightId = flightId;
        this.airline = airline;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.cost = cost;
    }

    //constructor without flight id
    public Flight(String airline, String departureDate, String arrivalDate,
                  String origin, String destination, String duration, double cost) {
        this.airline = airline;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.cost = cost;
    }
    //overloaded with connecting flight
    public Flight(int flightId, String airline, String departureTime, String arrivalTime,
                  String origin, String destination, String duration, double cost, int connectingFlight) {
        this.flightId = flightId;
        this.airline = airline;
        this.departureDate = departureTime;
        this.arrivalDate = arrivalTime;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.cost = cost;
        this.connectingFlight = connectingFlight;
    }
    //connecting flight, no flight id
    public Flight(String airline, String departureTime, String arrivalTime,
                  String origin, String destination, String duration, double cost, int connectingFlight) {
        this.airline = airline;
        this.departureDate = departureTime;
        this.arrivalDate = arrivalTime;
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.cost = cost;
        this.connectingFlight = connectingFlight;
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

    public String getDepartureTime() {return departureDate;}

    public void setDepartureTime( String departureTime) {
        this.departureDate = departureTime;
    }

    public String getArrivalTime() {
        return arrivalDate;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalDate = arrivalTime;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {this.duration = duration;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getConnectingFlight() {
        return connectingFlight;
    }
    public void setConnectingFlight(int connectingFlight) {
        this.connectingFlight = connectingFlight;
    }
}
