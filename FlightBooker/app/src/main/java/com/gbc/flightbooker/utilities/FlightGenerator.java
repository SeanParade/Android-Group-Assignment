package com.gbc.flightbooker.utilities;

import com.gbc.flightbooker.db.Flight;
import com.gbc.flightbooker.utilities.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nooran on 2017-12-16.
 */

public class FlightGenerator {

    //public final String origin1 = "Toronto, Ontario";
    //public final String origin2 = "Miami, Florida";

    //list of airlines
    public final List<String> airlines = Arrays.asList("Test Jet", "Fake Airline", "Air Blanada");


    //returns an arraylist of departure datetimes
    public ArrayList<String> departureTimes (String departure)
    {
        ArrayList<String> departureTimes = new ArrayList<String>();

        ArrayList<String> times = new ArrayList<String>();
        times.add("08:00");
        times.add("10:30");
        times.add("14:30");

        for(String time: times)
        {
            departureTimes.add(Helper.addTime(departure, time));
        }
        return departureTimes;
    }
    //generate flights based on the origin, destination and departure date
public ArrayList<Flight> generateFlights(String origin, String destination, String departure)
{
    ArrayList<Flight> flights = new ArrayList<Flight>();
    ArrayList<String> departureTimes = departureTimes(departure);
    for(String time : departureTimes)
    {

        if(origin.equals("some origin") && destination.equals("some destination"))
        {
            //create another connecting flight
            //add connecting flight id to original flight
            //add flight to flights arraylist
        }
        else {
            String duration = calculateDuration(origin, destination);
            String arrival = calculateArrival(departure, duration);
            String airline = airlines.get(departureTimes.indexOf(time));
            double cost = calculateCost(duration, airline);

            Flight flight = new Flight(airline, time, arrival, origin,
                    destination, duration, cost);
            flights.add(flight);
        }

    }

    return flights;
}
//calculate arrival time based on departure datetime and duration of flight
public String calculateArrival(String departure, String duration)
{
    return Helper.addTime(departure, duration);
}
//calculate duration based on origin and destination
public String calculateDuration(String origin, String destination)
{
    String duration = "";

    //if statements to determine duration

    return duration;
}
//calculate cost based on duration of flight and rate of airline
public double calculateCost(String duration, String airline)
{
    double cost = 0;
    int[] times = Helper.timeSplit(duration);

    if(airline.equals("Test Jet"))
    {
        //calculate cost based on number of hours, minutes and rate
    }
    else if(airline.equals("Air Blanada"))
    {
        //calculate cost based on number of hours, minutes and rate
    }
    else
    {
        //calculate cost based on hours, minutes and airline rate
    }
    return cost;
}



}
