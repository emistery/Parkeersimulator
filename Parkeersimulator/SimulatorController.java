/**
 * Created by Lenovo T420 on 27-1-2017.
 */
package Parkeersimulator;

import Parkeersimulator.Cars.Car;
import Parkeersimulator.Views.AbstrView;
import Parkeersimulator.Views.BaseView.CarParkView;
import Parkeersimulator.Views.BaseView.SimulatorView;
import Parkeersimulator.Views.StatisticView.StatisticView;

import java.util.ArrayList;

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
        simulatorView = new SimulatorView((CarParkView) carParkView);
        statisticView = new StatisticView(simulator);
        simulator.addView(carParkView);
        simulator.addView(simulatorView);
        }

    //add/remove view to/from the list of Views that should be updated
    public void addView(AbstrView view){
        simulator.addView(view);
    }
    public void removeView(AbstrView view){
        simulator.removeView(view);
    }

    //call the enable/disable method on a view
    public void enableView(AbstrView view){
        view.enableView();
    }
    public void disableView(AbstrView view){
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
    public CarParkView getCarParkView(){
        return (CarParkView) carParkView;
    }
    public void runSimulator(){ simulator.run();}
    public void runSimulator(int tick){simulator.run(tick);}
    public int getAdHocCars(){
        return simulator.getAdHocCars();
    }
    public int getPassCars(){
        return simulator.getPassCars();
    }
    public double getDayEarnings(){
        return simulator.getDayEarnings();
    }
    public int getTickPause(){return simulator.getTickPause();}
    public int getAdHocQueue(){
        return simulator.getAdHocQueueSize();
    }
    public int getPassQueue(){
        return simulator.getPassQueueSize();
    }
    public ArrayList<Location> getLocations(){return simulator.getLocations();}
    public Car getCarAt(Location location){return simulator.getCarAt(location);}

    public void setTickPause(int tickPause){simulator.setTickPause(tickPause);}


}
