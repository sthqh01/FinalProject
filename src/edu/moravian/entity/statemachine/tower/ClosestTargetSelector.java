/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.statemachine.tower;

import edu.moravian.entity.Entity;
import edu.moravian.entity.Tower;
import edu.moravian.entity.manager.EntityManager;

/**
 *
 * @author danielhuynh
 */
public class ClosestTargetSelector implements TargetSelector
{
    private final Entity towerEntity;
    private final EntityManager entityManager;
    private int closestSquaredDistance;
    public ClosestTargetSelector(Entity towerEntity, EntityManager entityManager)
    {
        this.towerEntity = towerEntity;
        this.entityManager = entityManager;
        this.closestSquaredDistance = Integer.MAX_VALUE;
    }
    
    @Override
    public Entity selectTarget() 
    {
        this.closestSquaredDistance = Integer.MAX_VALUE;
        Entity closestEntity = null;
        for (Entity agentEntity : entityManager.getEntity()) {
            int distance = this.getSquaredDistance(towerEntity, agentEntity);
            if (distance < closestSquaredDistance 
                    && distance <= Math.pow(((Tower)towerEntity).getSightRadius(), 2)) {
                closestSquaredDistance = distance;
                closestEntity = agentEntity;
            }
        }
        return closestEntity;
    }
    
    private int getSquaredDistance(Entity towerEntity, Entity targetEntity)
    {
        return (int)((targetEntity.getMapLocation().getX()-towerEntity.getMapLocation().getX())
                *(targetEntity.getMapLocation().getX()-towerEntity.getMapLocation().getX()))
                + (int)((targetEntity.getMapLocation().getY()-towerEntity.getMapLocation().getY())
                *(targetEntity.getMapLocation().getY()-towerEntity.getMapLocation().getY()));
    }
    
    public int getClosestSquaredDistance()
    {
        return this.closestSquaredDistance;
    }
}
