package com.gbc.flightbooker.db;

/**
 * Created by nooran on 2017-12-12.
 */
//@Entity(tableName="flight")
public class Flight {

    //@PrimaryKey(autoGenerate=true)
    private int flightId = 0;

    //@ColumnInfo(name="airline")
    private String airline;

    //@ColumnInfo(name="departuretime")
    private String departureTime;

    //@ColumnInfo(name="arrivaltime")
    private String arrivalTime;

    //@ColumnInfo(name="origin")
    private String origin;

    //@ColumnInfo(name="destination")
    private String destination;

    //@ColumnInfo(name="duration")
    private double duration;

    //@ColumnInfo(name="cost")
    private double cost;

    //overloaded constructor
    public Flight(int flightId, String airline, String departureTime, String arrivalTime, String origin, String destination, double duration, double cost) {
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
    public Flight(String airline, String departureTime, String arrivalTime, String origin, String destination, double duration, double cost) {
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

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
