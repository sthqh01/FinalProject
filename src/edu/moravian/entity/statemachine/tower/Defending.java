/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.statemachine.tower;

import edu.moravian.entity.Entity;
import edu.moravian.entity.statemachine.TowerState;
import edu.moravian.entity.Tower;
import edu.moravian.entity.spacePartitioning.CellSpacePartition;

/**
 *
 * @author danielhuynh
 */
public class Defending implements TowerState {

    private final Tower towerEntity;
    private final CellSpacePartition cellSpacePartition;
    private final TargetSelector targetSelector;

    public Defending(Tower towerEntity, CellSpacePartition cellSpacePartition) {
        this.towerEntity = towerEntity;
        this.cellSpacePartition = cellSpacePartition;
        this.targetSelector = new ClosestTargetSelector(towerEntity
                , cellSpacePartition.getAgentManager());
    }

    @Override
    public void execute() {
        Entity target = this.cellSpacePartition.getClosestTarget(this.towerEntity.getMapLocation()
                , this.towerEntity.getSightRadius());
//        Entity target = this.targetSelector.selectTarget();
        this.towerEntity.setCurrentTarget(target);
        if (target != null) {
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
