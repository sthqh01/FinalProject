/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity;

import edu.moravian.data.object.TowerData;
import edu.moravian.entity.statemachine.EntityState;
import edu.moravian.entity.statemachine.StateMachine;
import edu.moravian.entity.statemachine.tower.Defending;
import edu.moravian.entity.wave.EntityManager;
import edu.moravian.entity.wave.ProjectileManager;
import edu.moravian.math.Point2D;

/**
 *
 * @author danielhuynh
 */
public class Tower extends Entity
{
    private final StateMachine stateMachine;
    private final EntityState defendingState;
    private int sightRadius;
    private final EntityManager projectileManager;
    private final int fireDelay;
    private int timeSinceLastShoot;
    private int damage = 50;
    public Tower(Point2D mapLocation, TowerData towerData, EntityManager agentManager, EntityManager projectileManager) {
        super(mapLocation);
        this.stateMachine = new StateMachine(this);
        this.defendingState = new Defending(this, agentManager);
        this.stateMachine.setCurrentState(this.defendingState);
        this.fireDelay = towerData.getFireDelay();
        this.timeSinceLastShoot = fireDelay;
        this.damage = towerData.getDamage();
        this.sightRadius = towerData.getSighRadius();
        this.projectileManager = projectileManager;
    }
    
    public void shoot(Entity target)
    {
        ((ProjectileManager)this.projectileManager).add(new Projectile(this.getMapLocation(), 0.2, this.damage, target));
    }

    @Override
    public void update(int delta) {
        this.delta = delta;
        this.stateMachine.update();
    }
    
    public int getSightRadius()
    {
        return this.sightRadius;
    }
    
    public int getDelta()
    {
        return this.delta;
    }
    
    public void setTimeSinceLastShoot(int timeSinceLastShoot) {
        this.timeSinceLastShoot = timeSinceLastShoot;
    }

    public int getFireDelay() {
        return fireDelay;
    }

    public int getTimeSinceLastShoot() {
        return timeSinceLastShoot;
    }
}
