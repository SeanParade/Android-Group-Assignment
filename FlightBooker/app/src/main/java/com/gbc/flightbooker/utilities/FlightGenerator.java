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

    //origin cities
    public final String ORIGIN1 = "Toronto, Canada";
    public final String ORIGIN2 = "Philadelphia, U.S.A.";
    //destination cities
    public final String DESTIN1 = "Miami, U.S.A.";
    public final String DESTIN2 = "Chicago, U.S.A.";
    public final String DESTIN3 = "Los Angeles, U.S.A.";
    //airlines
    public final String AIRLINE1 = "Test Jet";
    public final String AIRLINE2 = "Air Blanada";
    public final String AIRLINE3 = "Fake Airline";
    //list of airlines
    public final List<String> airlines = Arrays.asList(AIRLINE1, AIRLINE2, AIRLINE3);


    //returns an arraylist of departure datetimes
    public ArrayList<String> departureTimes(String departure) {
        ArrayList<String> departureTimes = new ArrayList<String>();

        ArrayList<String> times = new ArrayList<String>();
        times.add("08:00");
        times.add("10:30");
        times.add("14:30");

        for (String time : times) {
            departureTimes.add(Helper.addTime(departure, time));
        }
        return departureTimes;
    }

    //generate flights based on the origin, destination and departure date
    public ArrayList<Flight> generateFlights(String origin, String destination, String departure) {
        ArrayList<Flight> flights = new ArrayList<Flight>();
        ArrayList<String> departureTimes = departureTimes(departure);
        for (String time : departureTimes) {

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

    //calculate arrival time based on departure datetime and duration of flight
    public String calculateArrival(String departure, String duration) {
        return Helper.addTime(departure, duration);
    }

    //calculate duration based on origin and destination
    public String calculateDuration(String origin, String destination) {
        String duration = "";
        //if statements to determine duration
        if (origin.equals(ORIGIN1) && destination.equals(DESTIN1))//toronto to miami
        {
            //3 hours 15 minutes
            duration = "03:15:00";
        } else if (origin.equals(ORIGIN1) && destination.equals(DESTIN2)) //toronto to chicago
        {
            //4 hours 30 minutes
            duration = "04:30:00";
        } else if (origin.equals(ORIGIN2) && destination.equals(DESTIN3)) //phili to LA
        {
            //6 hours 25 minutes
            duration = "06:25:00";
        } else //toronto to phili
        {
            //1 hour 30 minutes
            duration = "01:30:00";
        }

        return duration;
    }

    //calculate cost based on duration of flight and rate of airline
    public double calculateCost(String duration, String airline) {
        double cost = 0;
        int rate = 0;
        int[] times = Helper.timeSplit(duration);

        if (airline.equals(AIRLINE1)) {
            rate = 40;
        } else if (airline.equals(AIRLINE2)) {
            rate = 45;
        } else {
            rate = 35;
        }
        //multiply hours by rate
        cost = times[0] * rate;
        return cost;
    }


}
