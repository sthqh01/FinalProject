/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.statemachine.tower;

import edu.moravian.entity.Entity;
import edu.moravian.entity.statemachine.TowerState;
import edu.moravian.entity.Tower;
import edu.moravian.entity.wave.EntityManager;
import edu.moravian.entity.wave.ProjectileManager;
import edu.moravian.main.Play;

/**
 *
 * @author danielhuynh
 */
public class Defending implements TowerState 
{
    private final TargetSelector closestTargetSelector;
    private final Tower towerEntity;
    private final EntityManager projectileManager;
    public Defending(Tower towerEntity, EntityManager agentManager, EntityManager projectileManager)
    {
        this.towerEntity = towerEntity;
        this.closestTargetSelector = new ClosestTargetSelector(towerEntity, agentManager);
        this.projectileManager = projectileManager;
    }
    
    @Override
    public void execute() {
        Entity target = this.closestTargetSelector.selectTarget();
        if(target!=null && (Math.pow(towerEntity.getSightRadius(), 2) 
                > ((ClosestTargetSelector)this.closestTargetSelector).getClosestSquaredDistance()))
        {
            ((ProjectileManager)this.projectileManager).add(towerEntity.getMapLocation(), target);
        }
    }
    
    private void shoot()
    {
    }
}
