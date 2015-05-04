/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity;

import edu.moravian.data.object.Data;
import edu.moravian.data.object.ProjectileData;
import edu.moravian.helper.CollisionDetector;
import edu.moravian.math.Point2D;
import org.newdawn.slick.Animation;

/**
 *
 * @author danielhuynh
 */
public class Projectile extends MovingEntity
{
    private final Entity target;
    private final int damage;
    public Projectile(Point2D mapLocation, Data projectileData, Entity target) {
        super(mapLocation, projectileData, ((ProjectileData)projectileData).getSpeed());
        this.target = target;
        this.damage = ((ProjectileData)projectileData).getDamage();
    }

    @Override
    public void update(int delta) 
    {
        this.delta = delta;
        if (CollisionDetector.checkCollision(this.mapLocation, this.target.getMapLocation())) {
            this.isAlive = false;
            ((Agent) this.target).gotHit(this.damage);
        } else {
            this.move(this.target.getMapLocation());
        }
    }
    
    public Entity getTarget()
    {
        return this.target;
    }
}
