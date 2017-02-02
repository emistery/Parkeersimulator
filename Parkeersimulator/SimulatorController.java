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
    
    public int getWeekDayArrivals(){return simulator.getWeekDayArrivals();}
    public int getWeekendArrivals(){return simulator.getWeekendArrivals();}
    public int getWeekDayPassArrivals(){return simulator.getWeekDayPassArrivals();}
    public int getWeekendPassArrivals(){return simulator.getWeekendPassArrivals();}
    public int getEnterSpeed(){return simulator.getEnterSpeed();}
    public int getPaymentSpeed(){return simulator.getPaymentSpeed();}
    public int getExitSpeed(){return simulator.getExitSpeed();}

    public void setWeekDayArrivals(int newValue){simulator.setWeekDayArrivals(newValue);}
    public void setWeekendArrivals(int newValue){simulator.setWeekendArrivals(newValue);}
    public void setWeekDayPassArrivals(int newValue){simulator.setWeekDayPassArrivals(newValue);}
    public void setWeekendPassArrivals(int newValue){simulator.setWeekendPassArrivals(newValue);}
    public void setEnterSpeed(int newValue){simulator.setEnterSpeed(newValue);}
    public void setPaymentSpeed(int newValue){simulator.setPaymentSpeed(newValue);}
    public void setExitSpeed(int newValue){simulator.setExitSpeed(newValue);}

    public void setTickPause(int tickPause){simulator.setTickPause(tickPause);}


}
