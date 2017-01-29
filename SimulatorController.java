/**
 * Created by Lenovo T420 on 27-1-2017.
 */
package Parkeersimulator;

public class SimulatorController {
    private Simulator simulator;
    private AbstrView carParkView;
    private AbstrView simulatorView;
    private AbstrView statisticView;

    public SimulatorController()
    {
        simulator = new Simulator(3, 6, 30);

        carParkView = new CarParkView(simulator);
        simulatorView = new SimulatorView(simulator, (CarParkView) carParkView);
        statisticView = new StatisticView();
        simulator.addView(carParkView);
        simulator.addView(simulatorView);
        //simulator.addView(statisticView);
    }

    public void addView() {
        simulator.addView(statisticView);
    }


    public void removeView(AbstrView view){
        simulator.removeView(view);
        view.disableView();
    }
    public int getOpenAdHocSpots() {
        return simulator.getOpenAdHocSpots();
    }

    public int getOpenPassSpots() {
        return simulator.getOpenPassSpots();
    }

    public SimulatorView getSimulatorView(){
        return (SimulatorView) simulatorView;
    }
    public StatisticView getStatisticView(){
        return (StatisticView) statisticView;
    }
}
