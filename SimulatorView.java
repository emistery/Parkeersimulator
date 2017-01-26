package Parkeersimulator;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Runnable;

public class SimulatorView extends JFrame {
    private CarParkView carParkView;
    private Buttons controller;
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;

    private Car[][][] cars;
    private ArrayList<Location> locations= new ArrayList<>();

    private JLabel tickLabel = new JLabel("0");

    public SimulatorView(Simulator simulator, int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        fillLocation(numberOfFloors, numberOfRows, numberOfPlaces);

        carParkView = new CarParkView(this);
        controller = new Buttons(simulator);



        Container contentPane = getContentPane();
        contentPane.add(tickLabel, BorderLayout.NORTH);
        contentPane.add(controller, BorderLayout.SOUTH);
        contentPane.add(carParkView, BorderLayout.CENTER);

        pack();
        setVisible(true);

        updateView();

    }

    public void updateView() {
        carParkView.updateView();
        tickLabel.repaint();

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

    public int getNumberOfOpenSpots(){
    	return numberOfOpenSpots;
    }

    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
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


    public Location getFirstFreeLocation() {
        for(Location location : locations){
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
        return null;
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

    public void tick() {
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
    

    public void tick(int tick) {

        tickLabel.setText("Tick: "+tick);

    }
}
