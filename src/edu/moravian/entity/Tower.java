/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity;

import edu.moravian.entity.statemachine.EntityState;
import edu.moravian.entity.statemachine.StateMachine;
import edu.moravian.entity.statemachine.tower.Defending;
import edu.moravian.entity.wave.EntityManager;
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
    public Tower(Point2D mapLocation, EntityManager agentManager, EntityManager projectileManager) {
        super(mapLocation);
        this.stateMachine = new StateMachine(this);
        this.defendingState = new Defending(this, agentManager, projectileManager);
        this.stateMachine.setCurrentState(this.defendingState);
        this.sightRadius = 84;
    }

    @Override
    public void update(int delta) {
        this.stateMachine.update();
    }
    
    public int getSightRadius()
    {
        return this.sightRadius;
    }
}
