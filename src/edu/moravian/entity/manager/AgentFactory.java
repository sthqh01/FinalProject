/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.manager;

import edu.moravian.data.ParameterLoader;
import edu.moravian.data.object.AgentData;
import edu.moravian.entity.Agent;
import edu.moravian.entity.pathFinding.PathFinder;
import edu.moravian.math.Point2D;
import java.util.ArrayList;

/**
 *
 * @author danielhuynh
 */
public class AgentFactory 
{
    private Point2D spawnMapLocation, destinationMapLocation;
    private ParameterLoader agentParameterLoader;
    private PathFinder pathFinder;
    public AgentFactory(ParameterLoader agentParameterLoader, PathFinder pathFinder
            , Point2D spawnMapLocation, Point2D destinationMapLocation)
    {
        this.spawnMapLocation = spawnMapLocation;
        this.destinationMapLocation = destinationMapLocation;
        this.pathFinder = pathFinder;
        this.agentParameterLoader = agentParameterLoader;
    }
    
    public Agent makeAgent(int i)
    {
        AgentData agentData = (AgentData)this.agentParameterLoader.getData(i);
        return new Agent(agentData, this.pathFinder
                , this.spawnMapLocation, this.destinationMapLocation);
    }
}
