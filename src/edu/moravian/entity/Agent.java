/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity;

import edu.moravian.data.object.AgentData;
import edu.moravian.entity.statemachine.agent.Seeking;
import edu.moravian.entity.statemachine.StateMachine;
import edu.moravian.entity.graph.PathFinder;
import edu.moravian.entity.statemachine.EntityState;
import edu.moravian.math.CoordinateTranslator;
import edu.moravian.math.Point2D;

/**
 *
 * @author danielhuynh
 */
public class Agent extends MovingEntity
{
    private final CoordinateTranslator coordinateTranslator;
    private final int maxHitPoints;
    private int hitPoints;
    private final PathFinder pathFinder;
    private final Point2D destinationMapLocation;
    private final StateMachine stateMachine;
    private final EntityState seekingState;
    public Agent(Point2D spawnMapLocation, AgentData agentData, PathFinder pathFinder
            , Point2D destinationMapLocation) {
        super(spawnMapLocation, agentData.getSpeed());
        this.coordinateTranslator = CoordinateTranslator.getInstance();
        this.pathFinder = pathFinder;
        this.maxHitPoints = agentData.getMaxHitPoints();
        this.hitPoints = this.maxHitPoints;
        this.destinationMapLocation = destinationMapLocation;
        Point2D agentTileLocation = this.coordinateTranslator.mapToTile(this.mapLocation);
        Point2D destinationTileLocation = this.coordinateTranslator.mapToTile(this.destinationMapLocation);
        this.pathFinder.generatePath(agentTileLocation, destinationTileLocation);
        this.seekingState = new Seeking(this, this.pathFinder.getPathArray());
        this.stateMachine = new StateMachine(this);
        this.stateMachine.setCurrentState(seekingState);
    }

    @Override
    public void update(int delta) 
    {
        this.delta = delta;
        this.stateMachine.update();
        if(this.hitPoints <= 0)
            this.isAlive = false;
    }
    
    public void gotHit(int damage)
    {
        this.hitPoints -= damage;
    }
}
