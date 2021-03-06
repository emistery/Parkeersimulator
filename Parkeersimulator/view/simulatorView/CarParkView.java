package Parkeersimulator.view.simulatorView;

import Parkeersimulator.model.car.Car;
import Parkeersimulator.model.Location;
import Parkeersimulator.model.Simulator;
import Parkeersimulator.view.abstractView.AbstractView;

import javax.swing.*;
import java.awt.*;

public class CarParkView extends AbstractView {
    private Dimension size;
    private Image carParkImage;

    /**
     * Constructor for objects of class CarPark
     */
    public CarParkView(Simulator simulator) {
        super(simulator);
        size = new Dimension(0, 0);
        setBackground(Color.GREEN);
        setBorder(BorderFactory.createTitledBorder("The Parking Garage"));
    }

    /**
     * Overridden. Tell the GUI manager how big we would like to be.
     */
    public Dimension getPreferredSize() {return new Dimension(800, 400);}

    /**
     * Overriden. The car park view component needs to be redisplayed. Copy the
     * internal image to screen.
     */
    public void paintComponent(Graphics g) {
        if (carParkImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(carParkImage, 0, 0, null);
        }
        else {
            // Rescale the previous image.
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }

    public void updateView() {
        // Create a new car park image if the size has changed.
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }
        Graphics graphics = carParkImage.getGraphics();
        for(Location location : simulator.getLocations()){
                    Car car = simulator.getCarAt(location);
                    Color color;
                    if(car == null && !location.getIsReserved()){
                        color = Color.white;
                    }else if(car == null && location.getIsReserved()){
                        color = Color.black;
                    }else{
                        color = car.getColor();
                    }
                    drawPlace(graphics, location, color);
                }
        repaint();
    }
    public void disableView() {

    }
    public void enableView(){

    }
    /**
     * Paint a place on this car park view in a given color.
     */
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
 //ruimte tussen 1e vakje van elke verdieping       //ruimte tussen de rijen                       //net iets breder dan een blokje
                location.getFloor() * (getWidth()/3) + (1 + (int)Math.floor(location.getRow() * 0.5)) *(getWidth()/11) + (location.getRow() % 2) * (getWidth()/40),
                60 + location.getPlace() * (getHeight()/40),
                getWidth()/40 - 1,
                getHeight()/40 - 1);

    }
}