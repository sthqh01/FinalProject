/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity;

import edu.moravian.graph.PathFinder;
import edu.moravian.math.CoordinateTranslator;
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
    private CoordinateTranslator coordinateTranslator;
    private Point2D destinationTileLocation;
    private int step;
    public Agent(Point2D mapLocation, PathFinder pathFinder) {
        super(mapLocation);
        this.pathFinder = pathFinder;
        this.speed = 0.1;
        this.coordinateTranslator = CoordinateTranslator.getInstance();
        this.destinationTileLocation = new Point2D(1,20);
        this.step = 0;
        this.path = new ArrayList<>();
        Point2D agentTileLocation = new Point2D((int)this.mapLocation.getX()/32
                    , (int)this.mapLocation.getY()/32);
        this.pathFinder.generatePath(agentTileLocation, this.destinationTileLocation);
        for(Point2D point : pathFinder.getPath())
            path.add(point);
    }

    @Override
    public void update(int delta) 
    {
        if(step<this.path.size())
        {
            Point2D tempTargetLocation = this.path.get(step);
            int destinationMX = (int)coordinateTranslator.tileToMap(tempTargetLocation).getX();
            int destinationMY = (int)coordinateTranslator.tileToMap(tempTargetLocation).getY();
            Point2D destinationMapLocation = new Point2D(destinationMX, destinationMY);
            if(!isAtDestination(destinationMapLocation, this.mapLocation))
            {
                this.move(new Point2D(destinationMX, destinationMY), delta);
            }
            else
            {
                step++;
                if(step!=this.path.size()) {
                    tempTargetLocation = this.path.get(step);
                    destinationMX = (int)coordinateTranslator.tileToMap(tempTargetLocation).getX();
                    destinationMY = (int)coordinateTranslator.tileToMap(tempTargetLocation).getY();
                    this.move(new Point2D(destinationMX, destinationMY), delta);
                }
            }
        }
    }
    
    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void move(Point2D targetLocation, int delta)
    {
        this.velocity = this.seek(targetLocation);
        this.mapLocation = this.mapLocation.plus(this.velocity.times(delta));
        if(this.velocity.magnitudeSq() > 0.000000001)
        {
            this.heading = this.velocity;
            this.side = this.heading.getLeftOrtho();
        }
    }
    
    public Vector2D seek(Point2D targetLocation)
    {
        Vector2D desiredVelocity = targetLocation.minus(
                this.mapLocation).times(this.speed);
        desiredVelocity.normalize();
        return desiredVelocity.times(this.speed);
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
