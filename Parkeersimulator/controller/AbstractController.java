package Parkeersimulator.controller;

/**
 * Created by Gebruiker on 6-2-2017.
 */
//TODO: impement this class. see: Life project

import Parkeersimulator.model.Simulator;
import javax.swing.*;

public abstract class AbstractController extends JPanel {
    protected Simulator simulator;

    public AbstractController(Simulator simulator) {
        this.simulator=simulator;
    }
}
