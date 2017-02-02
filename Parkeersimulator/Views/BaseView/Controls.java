package Parkeersimulator.Views.BaseView;

import Parkeersimulator.SimulatorController;
import Parkeersimulator.Views.AbstrView;
import javax.swing.*;

/**
 * Created by Lenovo T420 on 2-2-2017.
 */
public class Controls extends JPanel implements AbstrView {
    private RunButtons buttons;
    private SettingLabels settingLabels;
    private SimulatorController controller;

    public Controls(){

        buttons = new RunButtons();
        settingLabels = new SettingLabels();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(buttons);
        add(settingLabels);
    }

    public SettingLabels getSettingLabels(){return settingLabels;}
    public RunButtons getButtons(){
        return buttons;
    }
    public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings, double missedEarnings, int missedCars, String displayTime){
    }

    public void disableView(){}
    public void enableView(){}
    public void setController(SimulatorController contr) {
        controller = contr;
        System.out.println(" controller bla");
    }

}
