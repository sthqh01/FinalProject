/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity;

import edu.moravian.data.object.Data;
import edu.moravian.data.object.TowerData;
import edu.moravian.entity.statemachine.EntityState;
import edu.moravian.entity.statemachine.StateMachine;
import edu.moravian.entity.statemachine.tower.Defending;
import edu.moravian.entity.manager.EntityManager;
import edu.moravian.entity.manager.ProjectileManager;
import edu.moravian.entity.spacePartitioning.CellSpacePartition;
import edu.moravian.math.Point2D;

/**
 *
 * @author danielhuynh
 */
public class Tower extends Entity
{
    private final StateMachine stateMachine;
    private final EntityState defendingState;
    private int cost, sightRadius, damage, fireDelay;
    private final EntityManager projectileManager;
    private int timeSinceLastShoot;
    private Entity currentTarget;
    private final CellSpacePartition cellSpacePartition;
    private final Data towerData, projectileData;
    private int currentLevel;
    
    public Tower(Point2D mapLocation, Data towerData, Data projectileData
            , CellSpacePartition cellSpacePartition, EntityManager projectileManager) {
        super(mapLocation, towerData);
        this.towerData = towerData;
        this.stateMachine = new StateMachine(this);
        this.cellSpacePartition = cellSpacePartition;
        this.defendingState = new Defending(this, this.cellSpacePartition);
        this.stateMachine.setCurrentState(this.defendingState);
        this.fireDelay = ((TowerData)towerData).getFireDelay();
        this.timeSinceLastShoot = fireDelay;
        this.cost = ((TowerData)towerData).getCost();
        this.damage = ((TowerData)towerData).getDamage();
        this.sightRadius = ((TowerData)towerData).getSightRadius();
        this.projectileManager = projectileManager;
        this.projectileData = projectileData;
        this.currentLevel = 1;
    }
    
    public void shoot(Entity target)
    {
        ((ProjectileManager)this.projectileManager).add(new Projectile(this.getMapLocation(), this.projectileData, target));
    }
    
    public Iterable<Entity> getConsideredTarget()
    {
        return this.cellSpacePartition.getConsideredTargets(mapLocation, sightRadius);
    }

    @Override
    public void update(int delta) {
        this.delta = delta;
        this.stateMachine.update();
    }
    
    public int getCost()
    {
        return this.cost;
    }
    
    public int getDamage()
    {
        return this.damage;
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
    
    public Entity getCurrentTarget()
    {
        return this.currentTarget;
    }
    
    public void setCurrentTarget(Entity currentTarget)
    {
        this.currentTarget = currentTarget;
    }
    
    public void upgrade()
    {
        if(!this.isAtMaxLevel()) {
            double upgradeChangePercentage
                    = ((TowerData)this.towerData).getPercentageChangeUpgrade();
            this.damage += this.damage*upgradeChangePercentage;
            this.sightRadius += this.sightRadius*upgradeChangePercentage;
            this.fireDelay -= this.fireDelay*upgradeChangePercentage;
            this.cost += this.cost*upgradeChangePercentage;
            this.currentLevel += 1;
        }
    }
    
    public int getUpgradeCost()
    {
        double upgradeChangePercentage
                = ((TowerData)this.towerData).getPercentageChangeUpgrade();
        return (int)(this.cost*(1+upgradeChangePercentage));
    }
    
    public int getCurrentLevel()
    {
        return this.currentLevel;
    }
    
    public boolean isAtMaxLevel()
    {
        return this.currentLevel == ((TowerData)this.towerData).getMaxLevel();
    }
}
