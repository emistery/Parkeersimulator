
package Parkeersimulator;

public class SimulatorRunner {

    private Simulator simulator;

    public static void main(String[] args)
    {
        SimulatorController controller = new SimulatorController();
        controller.getSimulatorView().getButtons().setController(controller);
        controller.getStatisticView().setController(controller);
        controller.getSimulator().addView(controller.getStatisticView());
    }

}
