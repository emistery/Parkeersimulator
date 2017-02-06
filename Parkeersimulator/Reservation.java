package Parkeersimulator;

import Parkeersimulator.Cars.Car;

/**
 * Created by Gebruiker on 6-2-2017.
 */
public class Reservation {
    private Car car;
    private Location location;
    private int timeOfArrival;

    public Reservation(Car car, Location location, int timeOfArrival){
        this.car = car;
        this.location = location;
        this.timeOfArrival = timeOfArrival;
    }
    public Car getCar() {
        return car;
    }

    public Location getLocation() {
        return location;
    }

    public int getTimeOfArrival() {
        return timeOfArrival;
    }
}
