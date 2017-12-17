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

    public final List<String> airlines = Arrays.asList("Test Jet", "Fake Airline", "Air Blanada");


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
public ArrayList<Flight> generateFlights(String origin, String destination, String departure)
{
    ArrayList<Flight> flights = new ArrayList<Flight>();
    ArrayList<String> departureTimes = departureTimes(departure);
    for(String time : departureTimes)
    {
        String duration = calculateDuration(origin, destination);
        String arrival = calculateArrival(departure, duration);
        String airline = airlines.get(departureTimes.indexOf(time));
        double cost = calculateCost(duration, airline);

        Flight flight = new Flight(airline, time, arrival, origin,
                destination, duration, cost);

        flights.add(flight);
    }

    return flights;
}
public String calculateArrival(String departure, String duration)
{
    return Helper.addTime(departure, duration);
}
public String calculateDuration(String origin, String destination)
{
    String duration = "";

    //if statements to determine duration

    return duration;
}
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
