/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity;

import edu.moravian.entity.statemachine.AgentState;
import edu.moravian.entity.statemachine.Seeking;
import edu.moravian.entity.statemachine.StateMachine;
import edu.moravian.entity.graph.PathFinder;
import edu.moravian.math.Point2D;
import edu.moravian.math.Vector2D;
import java.util.ArrayList;

/**
 *
 * @author danielhuynh
 */
public class Agent extends Entity
{
    private Vector2D velocity, heading, side;
    private double speed;
    private PathFinder pathFinder;
    private ArrayList<Point2D> path;
    private Point2D destinationTileLocation;
    private int delta;
    private StateMachine stateMachine;
    private AgentState seekingState;
    public Agent(Point2D mapLocation, double speed, PathFinder pathFinder
            , Point2D destinationTileLocation) {
        super(mapLocation);
        this.pathFinder = pathFinder;
        this.speed = speed;
        this.destinationTileLocation = new Point2D(1,20);
        Point2D agentTileLocation = new Point2D((int)this.mapLocation.getX()/32
                    , (int)this.mapLocation.getY()/32);
        this.pathFinder.generatePath(agentTileLocation, this.destinationTileLocation);
        this.seekingState = new Seeking(this.pathFinder.getPathArray());
        this.stateMachine = new StateMachine(this);
        this.stateMachine.setCurrentState(seekingState);
    }

    @Override
    public void update(int delta) 
    {
        this.delta = delta;
        this.stateMachine.update();
    }
    
    public void move(Point2D targetLocation)
    {
        this.velocity = this.seek(targetLocation);
        this.mapLocation = this.mapLocation.plus(this.velocity.times(this.delta));
        if(this.velocity.magnitudeSq() > 0.000000001)
        {
            this.heading = this.velocity;
            this.side = this.heading.getLeftOrtho();
        }
    }
    
    private Vector2D seek(Point2D targetLocation)
    {
        Vector2D desiredVelocity = targetLocation.minus(
                this.mapLocation).times(this.speed);
        desiredVelocity.normalize();
        return desiredVelocity.times(this.speed);
    }    
}
