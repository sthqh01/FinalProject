/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity;

import edu.moravian.entity.statemachine.agent.Seeking;
import edu.moravian.entity.statemachine.StateMachine;
import edu.moravian.entity.graph.PathFinder;
import edu.moravian.entity.statemachine.EntityState;
import edu.moravian.math.Point2D;

/**
 *
 * @author danielhuynh
 */
public class Agent extends MovingEntity
{
    private final PathFinder pathFinder;
    private final Point2D destinationTileLocation;
    private final StateMachine stateMachine;
    private final EntityState seekingState;
    public Agent(Point2D mapLocation, double speed, PathFinder pathFinder
            , Point2D destinationTileLocation) {
        super(mapLocation, speed);
        this.pathFinder = pathFinder;
        this.speed = speed;
        this.destinationTileLocation = new Point2D(1,20);
        Point2D agentTileLocation = new Point2D((int)this.mapLocation.getX()/32
                    , (int)this.mapLocation.getY()/32);
        this.pathFinder.generatePath(agentTileLocation, this.destinationTileLocation);
        this.seekingState = new Seeking(this, this.pathFinder.getPathArray());
        this.stateMachine = new StateMachine(this);
        this.stateMachine.setCurrentState(seekingState);
    }

    @Override
    public void update(int delta) 
    {
        this.delta = delta;
        this.stateMachine.update();
    }
}
