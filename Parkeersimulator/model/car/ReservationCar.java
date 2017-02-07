package Parkeersimulator.model.car;

import java.util.Random;
import java.awt.*;

public class ReservationCar extends Car {
    private static final Color COLOR=Color.green;
    private int delay;

    public ReservationCar() {
        super();
        Random random = new Random();
        //auto blijft minimaal 15 minuten, maximaal 3:15 uur(3*60 minuten + 15) random.nextFloat() geeft een getal tussen 0.0 en 1.0

        //waarde voor mogelijke vertraging
        delay = (int) (1 + random.nextFloat() + 20);

        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        //System.out.println("Stayminutes" + stayMinutes);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }

    public Color getColor(){
        return COLOR;
    }
}
