/**
 * Created by Lenovo T420 on 27-1-2017.
 */
package Parkeersimulator.view.abstractView;
import Parkeersimulator.model.Simulator;

import javax.swing.*;

public abstract class AbstractView extends JPanel {
    protected Simulator simulator;

    public AbstractView(Simulator simulator) {
        this.simulator = simulator;
    }

    public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings, double missedEarnings, int missedCars, String displayTime) {
    }

    public void disableView() {
    }

    public void enableView() {
    }
}
