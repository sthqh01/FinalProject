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

/**
 *
 * @author danielhuynh
 */
public class Defending implements TowerState {

    private final TargetSelector closestTargetSelector;
    private final Tower towerEntity;

    public Defending(Tower towerEntity, EntityManager agentManager) {
        this.towerEntity = towerEntity;
        this.closestTargetSelector = new ClosestTargetSelector(towerEntity, agentManager);
    }

    @Override
    public void execute() {
        Entity target = this.closestTargetSelector.selectTarget();
        if (target != null && (Math.pow(towerEntity.getSightRadius(), 2)
                > ((ClosestTargetSelector) this.closestTargetSelector).getClosestSquaredDistance())) {
            if (this.towerEntity.getTimeSinceLastShoot() >= this.towerEntity.getFireDelay()) {
                ((Tower) this.towerEntity).shoot(target);
                this.towerEntity.setTimeSinceLastShoot(0);
            } else {
                int timeSinceLastShoot = this.towerEntity.getTimeSinceLastShoot();
                this.towerEntity.setTimeSinceLastShoot(timeSinceLastShoot += this.towerEntity.getDelta());
            }
        }
    }
}
