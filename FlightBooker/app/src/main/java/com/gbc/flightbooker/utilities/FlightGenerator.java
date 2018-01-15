package com.gbc.flightbooker.utilities;

import android.util.Log;

import com.gbc.flightbooker.db.Flight;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.hashids.*;


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
        ArrayList<Date> departureDates = new ArrayList<>();
        ArrayList<String> times = new ArrayList<>();
        times.add("08:00:00");
        times.add("10:30:00");
        times.add("14:30:00");

        for (String time : times) {
            departureDates.add(Helper.addTime(departure, time));
        }
        return departureDates;
    }

    //generate flights based on the origin, destination and departure date
    public static ArrayList<Flight> generateFlights(String origin, String destination, Date departure) throws Exception{
        ArrayList<Flight> flights = new ArrayList<>();

        // checks if origin is Toronto, then swaps it for a formatted constant
        if(origin.equalsIgnoreCase(ORIGIN.substring( 0, origin.length())))
            origin = ORIGIN;
        else{
            Log.d("Flight Generator", "Origin entry: " + origin);
            throw new Exception("Flights from that origin aren't currently supported");
        }

        // checks if origin is Chicago, then swaps it for a formatted constant
        if(destination.equalsIgnoreCase(DESTIN1.substring( 0, destination.length())))
            destination = DESTIN1;
        // checks if origin is Miami, then swaps it for a formatted constant
        else if(destination.equalsIgnoreCase(DESTIN2.substring( 0, destination.length())))
            destination = DESTIN2;
        else{
            Log.d("Flight Generator", "Destination entry: " + origin );
            throw new Exception("Flights to that Destination aren't currently supported");
        }


        ArrayList<Date> departureTimes = departureTimes(departure);
        for (Date time : departureTimes)
        {
            if(destination.equals(DESTIN1)) //direct flight
            {
                String duration = calculateDuration(origin, destination);
                Date arrival = calculateArrival(departure, duration);
                String airline = airlines.get(departureTimes.indexOf(time));
                double cost = calculateCost(duration, airline);

                Flight flight = new Flight(createRandomId(),
                        airline, departure, arrival, origin,
                        DESTIN1, duration, cost);
                flight.setTotalCost(cost);
                flight.setFinalDestination(flight.getDestination());
                flights.add(flight);
            }
            else //connecting flight
            {
                String duration = calculateDuration(origin, DESTIN1); //create direct flight first
                Date arrival = calculateArrival(departure, duration);
                String airline = airlines.get(departureTimes.indexOf(time));
                double cost = calculateCost(duration, airline);

                Flight flight1 = new Flight(createRandomId(),
                        airline, departure, arrival, ORIGIN,
                        DESTIN1, duration, cost);
                flight1.setConnectingFlight(flight1.getFlightId() + "-c");



                duration = calculateDuration(DESTIN1, DESTIN2); //create connecting flight
                Date connDeparture = Helper.addTime(arrival, "00:45"); //set departure time to after arrival of first flight
                arrival = calculateArrival(connDeparture, duration);
                airline = airlines.get(departureTimes.indexOf(time));
                cost = calculateCost(duration, airline);

                Flight flight2 = new Flight(flight1.getConnectingFlight(),
                        airline, connDeparture, arrival, DESTIN1,
                        DESTIN2, duration, cost);

                double totalCost = flight1.getCost() + flight2.getCost();
                flight1.setTotalCost(totalCost);
                flight2.setTotalCost(totalCost);
                flight1.setFinalDestination(flight2.getDestination());
                flight2.setFinalDestination(flight2.getDestination());
                flights.add(flight1);
                flights.add(flight2);
            }

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
        double cost;
        int rate;
        int[] times = Helper.timeSplit(duration);

        if (airline.equals(AIRLINE1)) {
            rate = 40;
        } else if (airline.equals(AIRLINE2)) {
            rate = 55;
        } else {
            rate = 35;
        }

        //multiply hours by rate
        cost = 100 + times[0] * rate;
        return cost;
    }



    // Flight id generator
    private static String createRandomId()
    {
        // generate random human-readable hash id
        Long seed = ThreadLocalRandom.current().nextLong(900000000000L);
        Long salt = System.currentTimeMillis() / 1000;
        Hashids hasher = new Hashids(salt.toString());

        return hasher.encode(seed);
    }
}
