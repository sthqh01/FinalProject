/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity;

import edu.moravian.data.object.Data;
import edu.moravian.math.Point2D;
import edu.moravian.math.Vector2D;

/**
 *
 * @author danielhuynh
 */
public abstract class MovingEntity extends Entity
{
    private Vector2D velocity, heading, side;
    protected double speed;
    protected boolean isAlive;

    public MovingEntity(Point2D mapLocation, Data entityData, double speed) {
        super(mapLocation, entityData);
        this.speed = speed;
        this.isAlive = true;
    }

    @Override
    public abstract void update(int delta);
    
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
    
    public void setIsAlive(boolean isAlive)
    {
        this.isAlive = isAlive;
    }
    
    public boolean getIsAlive()
    {
        return this.isAlive;
    }
}
