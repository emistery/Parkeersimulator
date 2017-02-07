package Parkeersimulator.controller;

import Parkeersimulator.model.Simulator;
import Parkeersimulator.view.statisticView.AbstractView.AbstractView;
import javax.swing.*;
//TODO: this should be removed view: life project
/**
 * Created by Lenovo T420 on 2-2-2017.
 */
public class Controls extends AbstractController {
    private RunButtons buttons;
    private SettingLabels settingLabels;

    public Controls(Simulator simulator){
        super(simulator);

        buttons = new RunButtons(simulator);
        settingLabels = new SettingLabels(simulator);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(buttons);
        add(settingLabels);
    }

    public SettingLabels getSettingLabels(){return settingLabels;}
    public RunButtons getButtons(){
        return buttons;
    }
}
