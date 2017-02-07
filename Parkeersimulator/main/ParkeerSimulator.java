/**
 * Created by Lenovo T420 on 27-1-2017.
 */
package Parkeersimulator.main;

import Parkeersimulator.controller.RunController;
import Parkeersimulator.model.Simulator;
import Parkeersimulator.view.simulatorView.SettingLabels;
import Parkeersimulator.view.abstractView.AbstractView;
import Parkeersimulator.view.simulatorView.CarParkView;
import Parkeersimulator.view.simulatorView.SimulatorView;
import Parkeersimulator.view.statisticView.StatisticView;

public class ParkeerSimulator {
    private Simulator simulator;
    private AbstractView simulatorView;
    private AbstractView statisticView;

    public ParkeerSimulator()
    {
        simulator = new Simulator(3, 6, 30);

        AbstractView carParkView = new CarParkView(simulator);
        simulatorView = new SimulatorView((CarParkView) carParkView, simulator);
        statisticView = new StatisticView(simulator);
        simulator.addView(carParkView);
        simulator.addView(simulatorView);

        RunController runController = getSimulatorView().getRunController();
        SettingLabels settingLabels = getSimulatorView().getSettingLabels();

        runController.setParkeerSimulator(this);
        settingLabels.updateView();
        getStatisticView().setParkeerSimulator(this);

        simulator.addView(settingLabels);

        simulator.addView(statisticView);
        }

    public SimulatorView getSimulatorView(){
        return (SimulatorView) simulatorView;
    }
    public StatisticView getStatisticView(){
        return (StatisticView) statisticView;
    }
}
