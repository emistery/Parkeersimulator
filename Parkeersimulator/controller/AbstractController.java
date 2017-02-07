package Parkeersimulator.controller;

/**
 * Created by Gebruiker on 6-2-2017.
 */
//TODO: impement this class. see: Life project
import javax.swing.*;

import Parkeersimulator.model.Simulator;
import Parkeersimulator.view.statisticView.AbstractView.AbstractView;

public abstract class AbstractController extends AbstractView {
    protected Simulator simulator;

    public AbstractController(Simulator simulator) {
        super(simulator);
        this.simulator=simulator;
    }
    public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings, double missedEarnings, int missedCars, String displayTime){}
    public void disableView(){}
    public void enableView(){}
}
