package Parkeersimulator;

import Parkeersimulator.Cars.Car;
import Parkeersimulator.Views.AbstrView;

import java.util.ArrayList;

/**
 * Created by Lenovo T420 on 31-1-2017.
 */
public class SimulatorStats {
    private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 10;
    private int tick = 0;

    private int weekDayArrivals= 100; // average number of arriving cars per hour
    private int weekendArrivals = 200; // average number of arriving cars per hour
    private int weekDayPassArrivals= 50; // average number of arriving cars per hour
    private int weekendPassArrivals = 5; // average number of arriving cars per hour

    private int enterSpeed = 3; // number of cars that can enter per minute
    private int paymentSpeed = 7; // number of cars that can pay per minute
    private int exitSpeed = 5; // number of cars that can leave per minute

    //spul van simulatorview
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private int openAdHocSpots;
    private int openPassSpots;

    private Car[][][] cars;
    private ArrayList<Location> locations = new ArrayList<Location>();
    private ArrayList<AbstrView> views = new ArrayList<AbstrView>();

    private double earnings;
    private double price = 0.03;

    public SimulatorStats(){

    }
}

