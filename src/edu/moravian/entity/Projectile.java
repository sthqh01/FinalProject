/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity;

import edu.moravian.helper.CollisionDetector;
import edu.moravian.math.Point2D;

/**
 *
 * @author danielhuynh
 */
public class Projectile extends MovingEntity
{
    private final Entity target;
    public Projectile(Point2D mapLocation, double speed, Entity target) {
        super(mapLocation, speed);
        this.target = target;
    }

    @Override
    public void update(int delta) 
    {
        this.delta = delta;
        if (CollisionDetector.checkCollision(this.mapLocation, this.target.getMapLocation())) {
            this.isAlive = false;
            ((MovingEntity) this.target).setIsAlive(false);
        } else {
            this.move(this.target.getMapLocation());
        }
    }
}
