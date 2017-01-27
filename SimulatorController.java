/**
 * Created by Lenovo T420 on 27-1-2017.
 */
package Parkeersimulator;

public class SimulatorController {
    private Simulator simulator;
    private AbstrView carParkView;
    private AbstrView simulatorView;

    public SimulatorController()
    {
        simulator = new Simulator(3, 6, 30);
        carParkView = new CarParkView(simulator);
        simulatorView = new SimulatorView(simulator, (CarParkView) carParkView);
        simulator.addView(carParkView);
        simulator.addView(simulatorView);
    }

}
