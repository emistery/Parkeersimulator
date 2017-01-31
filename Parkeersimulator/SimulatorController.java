/**
 * Created by Lenovo T420 on 27-1-2017.
 */
package Parkeersimulator;

import Parkeersimulator.Views.AbstrView;
import Parkeersimulator.Views.BaseView.CarParkView;
import Parkeersimulator.Views.BaseView.SimulatorView;
import Parkeersimulator.Views.StatisticView.StatisticView;

public class SimulatorController {
    private Simulator simulator;
    private AbstrView carParkView;
    private AbstrView simulatorView;
    private AbstrView statisticView;
    private AbstrView DrawGraph;

    public SimulatorController()
    {
        simulator = new Simulator(3, 6, 30);

        carParkView = new CarParkView(simulator);

        simulatorView = new SimulatorView(simulator, (CarParkView) carParkView);
        statisticView = new StatisticView(simulator);
        simulator.addView(carParkView);
        simulator.addView(simulatorView);
        }

    public void addView(AbstrView view){
        view.enableView();
    }

    public void removeView(AbstrView view){
        view.disableView();
    }
    public Simulator getSimulator(){
        return simulator;
    }
    public SimulatorView getSimulatorView(){
        return (SimulatorView) simulatorView;
    }
    public StatisticView getStatisticView(){
        return (StatisticView) statisticView;
    }

    public int getAdHocCars(){
        return simulator.getAdHocCars();
    }
    public int getPassCars(){
        return simulator.getPassCars();
    }
}
