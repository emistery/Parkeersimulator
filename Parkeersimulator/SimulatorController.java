/**
 * Created by Lenovo T420 on 27-1-2017.
 */
package Parkeersimulator;

import Parkeersimulator.Views.AbstrView;
import Parkeersimulator.Views.CarParkView;
import Parkeersimulator.Views.SimulatorView;
import Parkeersimulator.Views.StatisticView;

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
        //simulator.addView(statisticView);
    }

    public void addView() {
        simulator.addView(statisticView);
    }

    public void addView(AbstrView view){
        simulator.addView(view);
        view.enableView();
    }

    public void removeView(AbstrView view){
        simulator.removeView(view);
        view.disableView();
    }

    public SimulatorView getSimulatorView(){
        return (SimulatorView) simulatorView;
    }
    public StatisticView getStatisticView(){
        return (StatisticView) statisticView;
    }
}
