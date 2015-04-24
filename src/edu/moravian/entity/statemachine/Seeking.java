/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.statemachine;

import edu.moravian.entity.Agent;
import edu.moravian.entity.graph.PathFinder;
import edu.moravian.math.CoordinateTranslator;
import edu.moravian.math.Point2D;
import java.util.ArrayList;

/**
 *
 * @author danielhuynh
 */
public class Seeking implements AgentState
{
    private CoordinateTranslator coordinateTranslator;
    private ArrayList<Point2D> path;
    private int step;
    public Seeking(ArrayList<Point2D> path)
    {
        this.coordinateTranslator = CoordinateTranslator.getInstance();
        this.path = path;
        this.step = 0;
    }

    @Override
    public void execute(Agent agentEntity) 
    {
        if(step<this.path.size())
        {
            Point2D tempTargetLocation = this.path.get(step);
            int destinationMX = (int)coordinateTranslator.tileToMap(tempTargetLocation).getX();
            int destinationMY = (int)coordinateTranslator.tileToMap(tempTargetLocation).getY();
            Point2D destinationMapLocation = new Point2D(destinationMX, destinationMY);
            if(!isAtDestination(destinationMapLocation, agentEntity.getMapLocation()))
            {
                agentEntity.move(new Point2D(destinationMX, destinationMY));
            }
            else
            {
                step++;
                if(step!=this.path.size()) {
                    tempTargetLocation = this.path.get(step);
                    destinationMX = (int)coordinateTranslator.tileToMap(tempTargetLocation).getX();
                    destinationMY = (int)coordinateTranslator.tileToMap(tempTargetLocation).getY();
                    agentEntity.move(new Point2D(destinationMX, destinationMY));
                }
            }
        }
    }

    @Override
    public PathFinder getPathFinder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private boolean isAtDestination(Point2D destinationMapLocation, Point2D entityMapLocation)
    {
        int deviation = 3;
        int destinationMX = (int) destinationMapLocation.getX();
        int destinationMY = (int) destinationMapLocation.getY();
        int entityMX = (int)entityMapLocation.getX();
        int entityMY = (int)entityMapLocation.getY();
        if(Math.abs(destinationMX - entityMX) <= deviation
                &&Math.abs(destinationMY - entityMY) <= deviation)
        {
            return true;
        }
        return false;
    }
}
