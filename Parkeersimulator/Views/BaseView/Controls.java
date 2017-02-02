package Parkeersimulator.Views.BaseView;

import Parkeersimulator.Views.AbstrView;
import javax.swing.*;

/**
 * Created by Lenovo T420 on 2-2-2017.
 */
public class Controls extends JPanel implements AbstrView {
    private RunButtons buttons;

    public Controls(){
        buttons = new RunButtons();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(buttons);
    }


    public RunButtons getButtons(){
        return buttons;
    }
    public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings, double missedEarnings, int missedCars, String displayTime){

    }
    public void disableView(){}
    public void enableView(){}

}
