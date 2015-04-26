/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.wave;

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
    private int limit = 20;
    private int timeSinceLastSpawn;
    private int spawnTime;
    private Point2D mapLocation, destinationTileLocation;
    private double speed;
    private PathFinder pathFinder;

    public AgentManager(Point2D mapLocation, double speed, PathFinder pathFinder, Point2D destinationTileLocation) {
        this.entityList = new ArrayList<>();
        this.timeSinceLastSpawn = 0;
        this.spawnTime = 1000;
        this.mapLocation = mapLocation;
        this.speed = speed;
        this.pathFinder = pathFinder;
        this.destinationTileLocation = destinationTileLocation;
    }

    @Override
    public void update(int delta) {
        for (int i = 0; i < entityList.size(); i++) {
            Agent agent = (Agent) entityList.get(i);
            if (((Agent) entityList.get(i)).getIsAlive() == false) {
                entityList.remove(agent);
            }
        }
        for (Entity entity : entityList) {
            entity.update(delta);
        }
        if (this.numAgentSoFar < this.limit) {
            if (this.timeSinceLastSpawn > this.spawnTime) {
                entityList.add(new Agent(mapLocation, speed, pathFinder, destinationTileLocation));
                this.timeSinceLastSpawn = 0;
                this.numAgentSoFar++;
            } else {
                this.timeSinceLastSpawn += delta;
            }
        }
    }

    @Override
    public Iterable<Entity> getEntity() {
        return entityList;
    }
}
