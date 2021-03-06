package Parkeersimulator.model;

import Parkeersimulator.model.car.Car;

import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {

    private int queueSize;
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

    public void setMaxSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public int getMaxSize(){
        return queueSize;
    }

    public int getLength(){
        return queue.size();
    }
}
