package Parkeersimulator.Cars;

import java.util.Random;
import java.awt.*;

public class AdHocCar extends Car {
	private static final Color COLOR=Color.red;
	
    public AdHocCar() {
    	Random random = new Random();
    	//auto blijft minimaal 15 minuten, maximaal 3:15 uur(3*60 minuten + 15) random.nextFloat() geeft een getal tussen 0.0 en 1.0
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }
    
    public Color getColor(){
    	return COLOR;
    }
}
