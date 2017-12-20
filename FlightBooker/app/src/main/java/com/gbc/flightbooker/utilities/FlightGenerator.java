package com.gbc.flightbooker.utilities;

import com.gbc.flightbooker.db.Flight;
import com.gbc.flightbooker.utilities.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by nooran on 2017-12-16.
 */

public class FlightGenerator {

    //origin cities
    private static final String ORIGIN = "Toronto, Ontario";
    //destination cities
    private static final String DESTIN1 = "Chicago, Illinois";
    private static final String DESTIN2 = "Miami, Florida";
    //airlines
    private static final String AIRLINE1 = "Test Jet";
    private static final String AIRLINE2 = "Air Blanada";
    private static final String AIRLINE3 = "Fake Airline";
    //list of airlines
    private static final List<String> airlines = Arrays.asList(AIRLINE1, AIRLINE2, AIRLINE3);


    //returns an arraylist of departure datetimes
    private static ArrayList<Date> departureTimes(Date departure) {
        ArrayList<Date> departureDates = new ArrayList<Date>();
        ArrayList<String> times = new ArrayList<String>();
        times.add("08:00");
        times.add("10:30");
        times.add("14:30");

        for (String time : times) {
            departureDates.add(Helper.addTime(departure, time));
        }
        return departureDates;
    }

    //generate flights based on the origin, destination and departure date
    public static ArrayList<Flight> generateFlights(String origin, String destination, Date departure) throws Exception{
        ArrayList<Flight> flights = new ArrayList<Flight>();

        // checks if origin is Toronto, then swaps it for a formatted constant
        if(origin.equalsIgnoreCase(ORIGIN.substring( 0, origin.length()-1)))
            origin = ORIGIN;
        else
            throw new Exception("Flights from that origin aren't currently supported");

        // checks if origin is Chicago, then swaps it for a formatted constant
        if(destination.equalsIgnoreCase(DESTIN1.substring( 0, destination.length()-1)))
            destination = DESTIN1;
        // checks if origin is Miami, then swaps it for a formatted constant
        else if(destination.equalsIgnoreCase(DESTIN2.substring( 0, destination.length()-1)))
            destination = DESTIN2;
        else
            throw new Exception("Flights to that destination aren't currently supported");


        ArrayList<Date> departureTimes = departureTimes(departure);
        for (Date time : departureTimes) {

            String duration = calculateDuration(origin, destination);
            Date arrival = calculateArrival(departure, duration);
            String airline = airlines.get(departureTimes.indexOf(time));
            //Date arrivalDate = Helper.stringToDate(arrival);
            //Date departureDate = Helper.stringToDate(time);
            double cost = calculateCost(duration, airline);

            Flight flight = new Flight(airline, departure, arrival, origin,
                    destination, duration, cost);
            flights.add(flight);
        }
        return flights;
    }

    //calculate arrival time based on departure datetime and duration of flight
    private static Date calculateArrival(Date departure, String duration) {
        return Helper.addTime(departure, duration);
    }

    //calculate duration based on origin and destination
    private static String calculateDuration(String origin, String destination) {
        String duration = "";
        //if statements to determine duration
        if (origin.equals(ORIGIN) && destination.equals(DESTIN1)) //toronto to chicago
        {
            //3 hours 15 minutes
            duration = "01:20:00";
        } else if (origin.equals(ORIGIN) && destination.equals(DESTIN2)) //toronto to Miami
        {
            //4 hours 30 minutes
            duration = "04:30:00";
        } else if (origin.equals(DESTIN1) && destination.equals(DESTIN2)) //chicago to miami
        {
            //3 hours 00 minutes
            duration = "03:00:00";
        }
        return duration;
    }

    //calculate cost based on duration of flight and rate of airline
    private static double calculateCost(String duration, String airline) {
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
