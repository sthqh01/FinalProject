/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.wave;

import edu.moravian.data.ParameterLoader;
import edu.moravian.data.object.AgentData;
import edu.moravian.entity.Agent;
import edu.moravian.entity.Entity;
import edu.moravian.entity.graph.PathFinder;
import edu.moravian.math.Point2D;
import java.util.ArrayList;

/**
 *
 * @author danielhuynh
 */
public class AgentManager extends EntityManager {

    private int numAgentSoFar = 0;
    private int numAgentLimit = 20;
    private int timeSinceLastSpawn;
    private int spawnTime;
    private Point2D mapLocation, destinationMapLocation;
    private PathFinder pathFinder;
    private ParameterLoader agentParameterLoader;

    public AgentManager(Point2D mapLocation, ParameterLoader agentParameterLoader
            , PathFinder pathFinder, Point2D destinationTileLocation) {
        this.entityList = new ArrayList<>();
        this.timeSinceLastSpawn = 0;
        this.spawnTime = 1000;
        this.mapLocation = mapLocation;
        this.agentParameterLoader = agentParameterLoader;
        this.pathFinder = pathFinder;
        this.destinationMapLocation = destinationTileLocation;
    }

    @Override
    public void update(int delta) {
        for (int i = 0; i < entityList.size(); i++) {
            Agent agent = (Agent) entityList.get(i);
            if (agent.getIsAlive() == false) {
                entityList.remove(agent);
            }
        }
        for (Entity entity : entityList) {
            entity.update(delta);
        }
        if (this.numAgentSoFar < this.numAgentLimit) {
            if (this.timeSinceLastSpawn > this.spawnTime) {
                AgentData agentData = (AgentData)agentParameterLoader.getData(0);
                entityList.add(new Agent(mapLocation, agentData, pathFinder, destinationMapLocation));
                this.timeSinceLastSpawn = 0;
                this.numAgentSoFar++;
            } else {
                this.timeSinceLastSpawn += delta;
            }
        }
    }
}
