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

    public void updateView() {
    }

    public void disableView() {
    }

    public void enableView() {
    }
}
