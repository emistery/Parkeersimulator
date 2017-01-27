package Parkeersimulator;
//-----MODEL-----
//needs methods for views and controllers to subscribe to state changes

import java.util.ArrayList;
import java.util.Random;
import java.lang.Runnable;

public class Simulator implements Runnable {

	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	

    private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    private SimulatorView simulatorView;


    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 1;
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
    private Car[][][] cars;
    private ArrayList<Location> locations= new ArrayList<>();

    private CarParkView carParkView;

    public Simulator(int numberOfFloors, int numberOfRows, int numberOfPlaces ) {
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();


        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        fillLocation(numberOfFloors, numberOfRows, numberOfPlaces);

        carParkView = new CarParkView(this);
        simulatorView = new SimulatorView(this, carParkView);

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

    public int getEnterSpeed(){
        return enterSpeed;
    }

    public int getPaymentSpeed(){
        return paymentSpeed;
    }

    public int getExitSpeed(){
        return exitSpeed;
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

    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }
    public void run(int numberOfTick) {

        (new Thread(() -> {
            for (int i = 0; i < numberOfTick; i++) {
                tick();
            }
        })).start();

    }

    private void tick() {
        this.tick++;
        simulatorView.tick(tick);
        tickker();
    	advanceTime();
    	handleExit();
    	updateViews();
    	// Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	handleEntrance();
    }
    //Kan verplaatst worden naar controller
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
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }
        setMinute(minute);
        setHour(hour);
        setDay(day);

    }
    private void handleEntrance(){
        //PassCar krijgt eerste parkeerplek toegewezen
    	carsArriving();
    	carsEntering(entrancePassQueue);
    	carsEntering(entranceCarQueue);  	
    }
    
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    private void updateViews(){

        // Update the car park view.
        simulatorView.updateView();
        carParkView.updateView();

    }
    
    private void carsArriving(){
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);    	
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);    	
    }

    private void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 &&
    			numberOfOpenSpots>0 &&
    			i<enterSpeed) {
            Car car = queue.removeCar();
            if(car.getHasToPay() == true)
            {
                Location freeLocation = getFirstFreeLocation();
                setCarAt(freeLocation, car);
            }else{
                Location freePassLocation = getFirstFreePassLocation();
                setCarAt(freePassLocation, car);
            }
            i++;
        }
    }

    public Location getFirstFreeLocation() {
        for(Location location : locations){
            if (getCarAt(location) == null && !location.checkPassLocation()) {
                return location;
            }
        }
        return null;
    }

    public Location getFirstFreePassLocation(){
        for(Location location : locations){
            if (getCarAt(location) == null && location.checkPassLocation() ) {
                return location;
            }
        }
        return null;
    }


    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
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

    private void carsPaying(){
        // Let cars pay.
    	int i=0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(car);
            i++;
    	}
    }
    
    private void carsLeaving(){
        // Let cars leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
    	}	
    }
    
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }
    
    private void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
    	switch(type) {
    	case AD_HOC: 
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new AdHocCar());
            }
            break;
    	case PASS:
            for (int i = 0; i < numberOfCars; i++) {
            	entrancePassQueue.addCar(new ParkingPassCar());
            }
            break;	            
    	}
    }
    
    private void carLeavesSpot(Car car){
    	removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }
    //ALLES VAN SIMULATORVIEW-----------------------------------------
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

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }

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
        return car;
    }
    //creates an ArrayList with all the locations used
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




    public Car getFirstLeavingCar() {
        for(Location location : locations){
            Car car = getCarAt(location);
            if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                return car;
            }
        }
        return null;
    }

    public void tickker() {
        for(Location location : locations){
            Car car = getCarAt(location);
            if (car != null) {
                car.tick();
            }
        }
    }


    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }




}
