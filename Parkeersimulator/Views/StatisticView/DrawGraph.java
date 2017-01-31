package Parkeersimulator.Views.StatisticView;

/**
 * Created by Emiel on 30-1-2017.
 */
import Parkeersimulator.Simulator;
import Parkeersimulator.Views.AbstrView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


@SuppressWarnings("serial")
public class DrawGraph extends JPanel implements AbstrView {
    //hoogte van grafiek
    private static final int MAX_SCORE = 400;
    //grootte van het veld
    private static final int PREF_W = 800;
    private static final int PREF_H = 650;
    //border gap
    private static final int BORDER_GAP = 30;
    //kleuren voor de graphs en points
    private static final Color ADHOC_GRAPH_COLOR = Color.red;
    private static final Color PASS_GRAPH_COLOR = Color.BLUE;
    private static final Color GRAPH_POINT_COLOR = Color.black;
    //de graphs
    private static final Stroke ADHOC_GRAPH_STROKE = new BasicStroke(3f);
    private static final Stroke PASS_GRAPH_STROKE = new BasicStroke(3f);
    //breedte van de points
    private static final int GRAPH_POINT_WIDTH = 6;
    //NOT SURE hoogte van de balkjes op de y-as
    private static final int Y_HATCH_CNT = 10;
    //interval tussen de points
    private int pointInterval = 1;
    private List<Integer> adHocCars;
    private List<Integer> passCars;
    private Simulator simulator;


    public DrawGraph(ArrayList<Integer> scores, ArrayList<Integer> passList) {
        adHocCars = scores;
        passCars = passList;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int bug =1;
        if(adHocCars.size()==0){
             bug = 0;
        }
        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (adHocCars.size() - bug);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - bug);

        //een lijst met punten van de adHoc auto's
        List<Point> graphPoints = new ArrayList<Point>();
        for (int i = 0; i < adHocCars.size(); i++) {
            int x1 = (int) (i * xScale + BORDER_GAP);
            int y1 = (int) ((MAX_SCORE - adHocCars.get(i)) * yScale + BORDER_GAP);
            graphPoints.add(new Point(x1, y1));
        }

        // create x and y axes
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

        // create hatch marks for y axis.
        for (int i = 0; i < Y_HATCH_CNT; i++) {
            int x0 = BORDER_GAP;
            int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
            int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < adHocCars.size() - 1; i++) {
            int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (adHocCars.size() - 1) + BORDER_GAP;
            int x1 = x0;
            int y0 = getHeight() - BORDER_GAP;
            int y1 = y0 - GRAPH_POINT_WIDTH;
            g2.drawLine(x0, y0, x1, y1);
        }

        //tekent de grafiek van adHoc auto's
        Stroke oldStroke = g2.getStroke();
        g2.setColor(ADHOC_GRAPH_COLOR);
        g2.setStroke(ADHOC_GRAPH_STROKE);
        drawLine(graphPoints, g2);

        //tekent puntjes op de grafiek
        g2.setStroke(oldStroke);
        g2.setColor(GRAPH_POINT_COLOR);
        drawPoints(graphPoints, g2);

        //een lijst met punten van de ParkingPass auto's
        graphPoints = new ArrayList<Point>();
        for (int i = 0; i < passCars.size(); i++) {
            int x1 = (int) (i * xScale + BORDER_GAP);
            int y1 = (int) ((MAX_SCORE - passCars.get(i)) * yScale + BORDER_GAP);
            graphPoints.add(new Point(x1, y1));
        }

        //tekent de grafiek van ParkingPass auto's
        Stroke oldsStroke = g2.getStroke();
        g2.setColor(PASS_GRAPH_COLOR);
        g2.setStroke(PASS_GRAPH_STROKE);

        drawLine(graphPoints, g2);

        //tekent puntjes op de grafiek
        g2.setStroke(oldsStroke);
        g2.setColor(GRAPH_POINT_COLOR);
        drawPoints(graphPoints, g2);
    }

    public void drawLine(List<Point> graphPoints, Graphics g2) {
        for (int i = 0; (i < graphPoints.size()-1); i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }
    }
    public void drawPoints(List<Point> graphPoints, Graphics g2) {
        for (int i = 0; i < graphPoints.size(); i++) {
            if (i % pointInterval == 0) {
                int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
                if (x < 0) {
                    x = 30 - GRAPH_POINT_WIDTH / 2;
                }
                int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;
                int ovalW = GRAPH_POINT_WIDTH;
                int ovalH = GRAPH_POINT_WIDTH;

                g2.fillOval(x, y, ovalW, ovalH);
            }
        }
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }


    public DrawGraph createAndShowGui(ArrayList<Integer> adHoc, ArrayList<Integer> pass) {
        /*
        this.simulator = simulator;
        //    Random random = new Random();
        int maxDataPoints = 100;
        int maxScore = simulator.getNumberOfPlaces();

        for (int i = 0; i < maxDataPoints; i++) {
            if(i >= maxDataPoints){
                clearGraph();
            }
            int filledSpots = simulator.getNumberOfPlaces() - simulator.getNumberOfOpenSpots();
            scores.add(filledSpots);
            */

       /* Random random = new Random();
        int maxDataPoints = 16;
        int maxScore = 20;
        for (int i = 0; i < maxDataPoints ; i++) {
            scores.add(random.nextInt(maxScore));
        }*/

        DrawGraph mainPanel = new DrawGraph(adHoc, pass);
        return mainPanel;
    }

    private void clearGraph(){
        adHocCars.clear();
    }

    public void updateView(int tick, int adHocSpots, int passSpots, int cars, double earnings, double missedEarnings, int missedCars, String displayTime){



    }

    public void disableView(){

    }
    public void enableView(){

    }
}