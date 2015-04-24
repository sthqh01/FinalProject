/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.wave;

import edu.moravian.entity.Agent;
import edu.moravian.entity.graph.PathFinder;
import edu.moravian.math.Point2D;
import java.util.ArrayList;

/**
 *
 * @author danielhuynh
 */
public class AgentFactory 
{
    private ArrayList<Agent> wave;
    private Point2D mapLocation, destinationTileLocation;
    private double speed;
    private PathFinder pathFinder;
    private int numAgent;
    public AgentFactory(Point2D mapLocation, double speed
            , PathFinder pathFinder, Point2D destinationTileLocation, int numAgent)
    {
        this.wave = new ArrayList<>();
        this.mapLocation = mapLocation;
        this.speed = speed;
        this.destinationTileLocation = destinationTileLocation;
        this.pathFinder = pathFinder;
        this.numAgent = numAgent;
    }
    
    public ArrayList<Agent> makeAgent()
    {
        for(int i = 0; i < numAgent; i++)
        {
            this.wave.add(new Agent(mapLocation, speed, pathFinder, destinationTileLocation));
        }
        return wave;
    }
}
