package com.gbc.flightbooker.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.gbc.flightbooker.utilities.FlightGenerator;
import com.gbc.flightbooker.utilities.Helper;

import java.util.Date;

/**
 * Created by nooran on 2017-12-12.
 */
@Entity(tableName="flight")
public class Flight {

    @PrimaryKey(autoGenerate=true)
    private int flightId = 0;

    @ColumnInfo(name="airline")
    private String airline;

    @ColumnInfo(name="departureDate")
    @TypeConverters({DateConverter.class})
    private Date departureDate;

    @ColumnInfo(name="arrivalDate")
    @TypeConverters({DateConverter.class})
    private Date arrivalDate;

    @ColumnInfo(name="origin")
    private String origin;

    @ColumnInfo(name="destination")
    private String destination;

    @ColumnInfo(name="duration")
    private String duration;

    @ColumnInfo(name="cost")
    private double cost;

    @ColumnInfo(name="connectingFlight")
    private int connectingFlight=0;

    public Flight(){}

    //overloaded constructor
    @Ignore
    public Flight(int flightId, String airline, Date departureDate, Date arrivalDate,
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
    @Ignore
    public Flight(String airline, Date departureDate, Date arrivalDate,
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
    @Ignore
    public Flight(int flightId, String airline, Date departureTime, Date arrivalTime,
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
    @Ignore
    public Flight(String airline, Date departureDate, Date arrivalDate,
                  String origin, String destination, String duration, double cost, int connectingFlight) {
        this.airline = airline;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
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

    public Date getDepartureDate() {return departureDate;}

    public void setDepartureDate( Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
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

    public void setDuration(String duration) {this.duration = duration;}

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

    @Ignore
    public String getFlightHeader()
    {
        String header = "From ";
        header += this.getOrigin() + " to " + this.getDestination() + " Duration: " + this.getDuration() + " Cost: $" + this.getCost();
        return header;
    }

    @Ignore
    public String getConnectingFlightHeader()
    {
        String header = "From ";
        String duration = "04:30:00";
        double cost = FlightGenerator.calculateCost(duration, this.getAirline());
        header += "Toronto to Miami" + " Duration: " +  duration + " Cost: $" + cost;
        return header;
    }

    @Ignore
    public String getFlightDetail()
    {
        String detail = "";
        detail += "Airline: " + this.getAirline() + "Flight Number: " + this.getFlightId() + "\n" +
                "Departure Date: " + Helper.dateToString(this.getDepartureDate()) + " at " + Helper.timeToString(this.getDepartureDate()) + "\n" +
                "Arrival Date: " + Helper.dateToString(this.getArrivalDate()) + " at " + Helper.timeToString(this.getArrivalDate()) + "\n" +
                "Duration: " + this.getDuration() + " minutes" + " Cost: " + this.getCost() + "\n";
        return detail;
    }

}
