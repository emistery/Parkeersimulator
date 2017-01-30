package Parkeersimulator;
import Parkeersimulator.Car.Car;

import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private Queue<Car> queue = new LinkedList<Car>();

    public boolean addCar(Car car) {
        return queue.add(car);
    }

    public Car removeCar() {
        return queue.poll();
    }

    public int carsInQueue(){
    	return queue.size();
    }

    public void driveAway(){
        int length = queue.size();
        queue.remove(length);
    }
}
