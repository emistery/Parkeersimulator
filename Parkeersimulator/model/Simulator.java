package Parkeersimulator.model;
//-----MODEL-----
//needs methods for views and controllers to subscribe to state changes
import Parkeersimulator.model.car.AdHocCar;
import Parkeersimulator.model.car.Car;
import Parkeersimulator.model.car.ParkingPassCar;
import Parkeersimulator.model.car.ReservationCar;
import Parkeersimulator.view.abstractView.AbstractView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.lang.Runnable;

/**
 * A simulation of a car park
 */
public class Simulator implements Runnable {
    //set true while the simulator is running
    private boolean running;

    //types of car
	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	private static final String RESERVE = "3";
	
    //all the queues
    private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    //current time
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    //pause between and number of ticks
    private int tickPause = 1;
    private int tick = 0;


    private int weekDayArrivals= 100; // average number of arriving car per hour
    private int weekendArrivals = 200; // average number of arriving car per hour
    private int weekDayPassArrivals= 50; // average number of arriving car per hour
    private int weekendPassArrivals = 5; // average number of arriving car per hour
    private int thursdayArrivals = 150;


    private int enterSpeed = 3; // number of car that can enter per minute
    private int paymentSpeed = 7; // number of car that can pay per minute
    private int exitSpeed = 3; // number of car that can leave per minute


    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private int openAdHocSpots;
    private int openPassSpots;

    private Car[][][] cars;
    private ArrayList<Location> locations = new ArrayList<>();
    private ArrayList<AbstractView> views = new ArrayList<>();
    private ArrayList<Car> missedCars = new ArrayList<>();
    private ArrayList<Car> missedPassCars = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();

    //
    private double earnings;
    private double missedEarnings;
    private double dayEarnings;
    private double price = 0.03;
    private int totalMissedCars;
    private String displayTime;

    /**
     * creates a new simulator, creates entrances, exits and a new car park that can hold car
     * @param numberOfFloors sets the number of floors of the simulator
     * @param numberOfRows sets the number of rows of the simulator
     * @param numberOfPlaces sets the number of places of the simulator
     */
    public Simulator(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();

        entranceCarQueue.setMaxSize(15);
        entrancePassQueue.setMaxSize(15);

        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
        this.openAdHocSpots = 2*numberOfRows*numberOfPlaces;
        this.openPassSpots = numberOfRows*numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        fillLocation(numberOfFloors, numberOfRows, numberOfPlaces);
    }


    //Added getters & setters
    //Get methods

    public int getDay(){
        return day;
    }

    public int getHour(){
        return hour;
    }

    public int getMinute(){
        return minute;
    }

    public int getTickPause(){
        return tickPause;
    }

    public int getTick(){
        return tick;
    }

    public int getWeekDayArrivals(){
        return weekDayArrivals;
    }

    public int getWeekendArrivals(){
        return weekendArrivals;
    }

    public int getWeekDayPassArrivals(){
        return weekDayPassArrivals;
    }

    public int getWeekendPassArrivals(){
        return weekendPassArrivals;
    }

    public int getThursdayArrivals() {return thursdayArrivals;}

    public int getEnterSpeed(){
        return enterSpeed;
    }

    public int getPaymentSpeed(){
        return paymentSpeed;
    }

    public int getExitSpeed(){
        return exitSpeed;
    }

    public int getOpenAdHocSpots() {
        return openAdHocSpots;
    }

    public int getOpenPassSpots() {
        return openPassSpots;
    }

    public int getTotalPlaces(){
        return numberOfFloors*numberOfRows*numberOfPlaces;
    }
    public int getAdHocCars(){
        return (2*numberOfRows*numberOfPlaces) - openAdHocSpots;
    }
    public int getPassCars(){
        return numberOfRows * numberOfPlaces - openPassSpots;
    }

    public int getMissedPassCars(){
        return missedPassCars.size();
    }

    //Set methods

    public void setDay(int day){
        this.day = day;
    }

    public void setHour(int hour){
        this.hour = hour;
    }

    public void setMinute(int minute){
        this.minute = minute;
    }

    public void setTickPause(int tickPause){
        this.tickPause = tickPause;
    }

    public void setTick(int tick){
        this.tick = tick;
    }

    public void setWeekDayArrivals(int weekDayArrivals){
        this.weekDayArrivals = weekDayArrivals;
    }

    public void setWeekendArrivals(int weekendArrivals){
        this.weekendArrivals = weekendArrivals;
    }

    public void setWeekDayPassArrivals(int weekDayPassArrivals ){
        this.weekDayPassArrivals = weekDayPassArrivals;
    }

    public void setWeekendPassArrivals (int weekendPassArrivals){
        this.weekendPassArrivals = weekendPassArrivals;
    }
    public void setThursdayArrivals(int thursdayArrivals) {this.thursdayArrivals = thursdayArrivals;}

    public void setEnterSpeed(int enterSpeed){
        this.enterSpeed = enterSpeed;
    }

    public void setPaymentSpeed(int paymentSpeed){
        this.paymentSpeed = paymentSpeed;
    }

    public void setExitSpeed(int exitSpeed){
        this.exitSpeed = exitSpeed;
    }

    // default methods

    /**
     * runs the simulation for 10.000 steps
     */
    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }

    /**
     * runs the simulation x steps
     * @param numberOfTick amount of steps taken
     */
    public void run(int numberOfTick) {
        Long beginTime = System.currentTimeMillis();
        (new Thread(() -> {
            for (int i = 0; i < numberOfTick; i++) {
                tick();
            }
            Long executionTime = System.currentTimeMillis()-beginTime;
            double average = round((double)executionTime/numberOfTick, 2);
            System.out.println("execution of "+ numberOfTick+" steps took "+executionTime+" milliseconds with an average of "+ average+" ms/tick");
            System.out.println("total tickpause is "+numberOfTick*tickPause );
        })).start();

    }

    /**
     * 1 step in the simulation
     * time advances, views are updated, car leave and arrive
     */
    private void tick() {
        this.tick++;
        tickker();
    	advanceTime();
        randomReservation();
    	handleExit();
    	updateViews();
    	// Pause.
        if(tickPause!=0) {
            try {
                Thread.sleep(tickPause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    	handleEntrance();
        System.out.println(""+paymentCarQueue.carsInQueue());
    }

    /**
     * advances the time in the simulation with 1 minute
     */
    private void advanceTime(){
        // Advance the time by one minute
        int minute = getMinute();
        int hour = getHour();
        int day = getDay();
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            //reset the day earnings to 0
            dayEarnings = 0.00;
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }
        setMinute(minute);
        setHour(hour);
        setDay(day);
        displayDay();

    }

    /**
     * returns the earnings of the day
     * @return earnings of current day
     */
    public double getDayEarnings(){
        return dayEarnings;
    }

    /**
     * executes the methods used to let car enter the car park
     */
    private void handleEntrance(){
        //PassCar gets the fist spot, as it has priority

    	carsArriving();
    	carsEntering(entrancePassQueue);
    	carsEntering(entranceCarQueue);
    }

    /**
     * executes the methods used to let car out of the car park and pay
     */
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }

    /**
     * subscribes a view to the simulation to be notified of updates
     * @param view the view to be subscribed
     */
    public void addView(AbstractView view)
    {
        (new Thread(() -> {
            while(running) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            views.add(view);

        })).start();
    }

    /**
     * removes a view from the list of views to be notified of updates
     * @param view the view to be removed
     */
    public void removeView(AbstractView view){
        (new Thread(() -> {
            while(running) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Iterator it = views.iterator();
            while (it.hasNext()) {
                AbstractView bla = (AbstractView) it.next();
                if (view == bla) {
                    it.remove();
                }
            }
        })).start();
    }

    /**
     * notifies the subscribed view to state changes and calls their updateView method
     */
    private void updateViews() {
        running=true;
        for(AbstractView view : views){
            view.updateView(tick, openAdHocSpots, openPassSpots, numberOfOpenSpots, earnings, missedEarnings, totalMissedCars, displayTime);
        }
        running=false;
    }

    private void makeReservation(Car car, Location location, int timeOfArrival){
        reservations.add(new Reservation(car, location, timeOfArrival));
    }

    private void randomReservation() {
        Random random = new Random();
        if (random.nextInt(10) == random.nextInt(10)) {
            Location reserveLocation = getFirstFreePassLocation();
            reserveLocation.setIsReserved(true);

            int timeOfArrival = tick + random.nextInt(100);

            makeReservation(new ReservationCar(), reserveLocation, timeOfArrival);
        }
    }

    /**
     * gets the amount of car arriving this minute and adds them to the simulation
     */

    private void carsArriving(){
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals, thursdayArrivals);
        addArrivingCars(numberOfCars, AD_HOC);
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals, weekDayPassArrivals);
        addArrivingCars(numberOfCars, PASS);
        addArrivingCars(0, RESERVE);
    }

    /**
     * handles the car entering the car park from a que
     * @param queue a que with car
     */
    private void carsEntering(CarQueue queue) {
        int i = 0;
        // Remove car from the front of the queue and assign to a parking space.
        if (spotsAvailable()) {

            while (queue.carsInQueue() > 0 &&
                    spotsAvailable() &&
                    i < enterSpeed) {
                Car car = queue.removeCar();
                if (car instanceof AdHocCar && openAdHocSpots > 0) {
                    Location freeLocation = getFirstFreeLocation();
                    setCarAt(freeLocation, car);
                } else if (car instanceof ParkingPassCar && openPassSpots > 0) {
                    Location freePassLocation = getFirstFreePassLocation();
                    setCarAt(freePassLocation, car);
                } else if (car instanceof ReservationCar /*&& openPassSpots > 0*/) {
                    setCarAt(car.getLocation(), car);
                }
                i++;
            }
        }
    }

    /**
     * returns true if ANY spots are available
     * @return true if ANY spots are available
     */
    public boolean spotsAvailable(){
        for(Location location : locations){
            if (getCarAt(location) == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * gets the first free location in the car park
     * @return the first free location in the car park
     */
    public Location getFirstFreeLocation() {
        for(Location location : locations){
            if (getCarAt(location) == null && !location.checkPassLocation()) {
                return location;
            }
        }
        return null;
    }

    /**
     *gets the first free location for car with a parking pass
     * @return the first free location for car with a parking pass
     */
    public Location getFirstFreePassLocation(){
        for(Location location : locations){
            if (getCarAt(location) == null && location.checkPassLocation() && !location.getIsReserved() ) {
                return location;
            }
        }
        return null;
    }

    public Location getFirstReservedLocation(){
        for(Location location : locations){
            if (location.checkPassLocation() && location.getIsReserved()) {
                return location;
            }
        }
        return null;
    }

    /**
     * returns the car at a given location or null if the spot is free
     * @param location the location of which the car must be found
     * @return the car at the given location
     */
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    /**
     * adds leaving car to the payment queue
     */
    private void carsReadyToLeave(){
        // Add leaving car to the payment queue.
        Car car = getFirstLeavingCar();
        while (car!=null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentCarQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car);
        	}
            car = getFirstLeavingCar();
        }
    }

    /**
     * let car pay
     */
    private void carsPaying(){
        // Let car pay.
    	int i=0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            handlePayment(car);
            carLeavesSpot(car);
            i++;

    	}
    }

    /**
     * let car leave
     */
    private void carsLeaving(){
        // Let car leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
    	}	
    }

    /**
     * calculates the amount of car that will arrive this minute
     * @param weekDay average number of car on a weekday
     * @param weekend average number of car in the weekend
     * @param thursday average number of car on thursday(koopavond)
     * @return the amount of car that wil arrive this minute
     */
    private int getNumberOfCars(int weekDay, int weekend, int thursday){
        Random random = new Random();

        // Get the average number of car that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;
        if(day==3){
            averageNumberOfCarsPerHour=thursday;
        }

        // Calculate the number of car that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }

    /**
     * adds the arriving car to the ques or lets the car drive of
     * @param numberOfCars
     * @param type
     */
    private void addArrivingCars(int numberOfCars, String type){
        int amountOfCars = numberOfCars;
        // Add the car to the back of the queue.
    	switch(type) {
    	case AD_HOC:
    	    while(entranceCarQueue.carsInQueue() <= entranceCarQueue.getMaxSize() && amountOfCars>0){
    	        entranceCarQueue.addCar(new AdHocCar());
    	        amountOfCars--;
            }
    	    if(entranceCarQueue.carsInQueue() >= entranceCarQueue.getMaxSize()) {
                for (int i = 0; i < amountOfCars; i++) {
                    missedCars.add(new AdHocCar());
                }
            }
            break;
    	case PASS:
    	    if(entrancePassQueue.carsInQueue() >= entrancePassQueue.getMaxSize()) {
    	        for (int i = 0; i < amountOfCars; i++) {
    	            missedPassCars.add(new ParkingPassCar());
                }
            } else {
            for (int i = 0; i < amountOfCars; i++) {
            	entrancePassQueue.addCar(new ParkingPassCar());
            }}
            break;
    	case RESERVE:
    	    for(Reservation reservation : reservations){
    	        if(tick == reservation.getTimeOfArrival()){
    	            entrancePassQueue.addCar(reservation.getCar());
                }
            }
    	}
    }

    /**
     * let a car leave its spot
     * @param car the car that is leaving the car park
     */
    private void carLeavesSpot(Car car){
    	removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }
    //SIMULATORVIEW-----------------------------------------
    public int getNumberOfOpenSpots(){
        return numberOfOpenSpots;
    }
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public ArrayList<Location> getLocations(){
        return locations;
    }

    /**
     * sets a car on a given location
     * @param location the location on which a car must be placed
     * @param car the car to be placed
     * @return true if the action was successful
     */
    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            if(car instanceof AdHocCar) {
                cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
                car.setLocation(location);
                openAdHocSpots--;
                numberOfOpenSpots--;
            }else if(car instanceof ParkingPassCar && !location.getIsReserved()){
                cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
                car.setLocation(location);
                openPassSpots--;
                numberOfOpenSpots--;
                return true;
            }else if(car instanceof ReservationCar && location.getIsReserved()){
                location.setIsReserved(false);
                cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
                car.setLocation(location);
                openPassSpots--;
                numberOfOpenSpots--;
                return true;
            }
        }
        return false;
    }

    /**
     * gets car at given location and handles it leaving its spot
     * @param location the location on which the car stands
     * @return the car that has left its spot
     */
    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        numberOfOpenSpots++;
        if(car instanceof AdHocCar) {
            openAdHocSpots++;
        }else if(car instanceof ParkingPassCar){
            openPassSpots++;
        }else if(car instanceof ReservationCar){
            openPassSpots++;
        }
        return car;
    }

    /**
     * creates an ArrayList with all the locations used
     * @param numberOfFloors number of floors of the carpark
     * @param numberOfRows number of rows each floor has
     * @param numberOfPlaces number of places each row has
     */
    public void fillLocation(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        for (int floor = 0; floor < numberOfFloors; floor++) {
            for (int row = 0; row < numberOfRows; row++) {
                for (int place = 0; place < numberOfPlaces; place++) {
                    Location location = new Location(floor, row, place);
                    locations.add(location);
                }
            }
        }
    }

    /**
     * gets the first car to leave its spot
     * @return the first car that is going to leave
     */
    public Car getFirstLeavingCar() {
        for(Location location : locations){
            Car car = getCarAt(location);
            if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                return car;
            }
        }
        return null;
    }

    /**
     * advances the time for all the car
     */
    public void tickker() {
        for(Location location : locations){
            Car car = getCarAt(location);
            if (car != null) {
                car.tick();
            }
        }
    }

    /**
     * returns true if the location exists
     * @param location the location that needs to be tested
     * @return true if the location exists
     */
    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        return !(floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces);
    }

    /**
     * handles the payment procedure for a car
     * @param car the car that is paying
     */
    private void handlePayment(Car car){
        int totalMinutes = car.getTotalMinutes();
        double profit = totalMinutes * price;
        dayEarnings += profit;
        earnings += profit;
        earnings = round(earnings, 2);
    }

    public double getEarnings(){
        return earnings;
    }

    /**
     * rounds a value to given decimal places
     * @param value value to be rounded
     * @param places places after the comma
     * @return the rounded number
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     * calculates the amount of money a missed car would have spend and adds this to the missed earnings
     * @param car the car which drove of to another car park
     */
    public void missedEarnings(Car car){
        int totalMinutes = car.getTotalMinutes();
        double profit = totalMinutes * price;
        missedEarnings += profit;
        missedEarnings = round(missedEarnings, 2);
    }

    /**
     * Calculates the amount of money which could have been earned if the queue wasn't too long.
     * @return total amount of money lost on too long entrance queues
     */
    public double calculateMissedEarnings(){
        for(Car car : missedCars){
            missedEarnings(car);
            totalMissedCars++;
        }
        missedCars.clear();

        return missedEarnings;
    }

    /**
     * returns the current day
     * @return current day
     */
    public String displayDay(){
        String currentDay ="day";
        switch(day){
            case 00:
                currentDay = "Maandag";
            break;
            case 01:
                currentDay = "Dinsdag";
            break;
            case 02:
                currentDay = "Woensdag";
            break;
            case 03:
                currentDay = "Donderdag";
            break;
            case 04:
                currentDay = "Vrijdag";
            break;
            case 05:
                currentDay = "Zaterdag";
            break;
            case 06:
                currentDay = "Zondag";
            break;
        }
        Integer.toString(hour, minute);
        displayTime = ("Dag: " + currentDay + " Tijd: " + hour + ":" + minute);
        return displayTime;
    }

    public int getAdHocQueueSize(){
        return entranceCarQueue.getLength();
    }

    public int getPassQueueSize(){
        return entrancePassQueue.getLength();
    }

}
