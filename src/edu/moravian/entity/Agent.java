/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity;

import edu.moravian.data.object.AgentData;
import edu.moravian.data.object.Data;
import edu.moravian.data.object.RenderableData;
import edu.moravian.entity.statemachine.agent.Seeking;
import edu.moravian.entity.statemachine.StateMachine;
import edu.moravian.entity.pathFinding.PathFinder;
import edu.moravian.entity.statemachine.EntityState;
import edu.moravian.helper.ResourceManager;
import edu.moravian.sound.SoundManager;
import edu.moravian.math.CoordinateTranslator;
import edu.moravian.math.Point2D;
import org.newdawn.slick.Animation;

/**
 *
 * @author danielhuynh
 */
public class Agent extends MovingEntity
{
    private final CoordinateTranslator coordinateTranslator;
    private final int maxHitPoints;
    private int hitPoints;
    private final PathFinder pathFinder;
    private final Point2D destinationMapLocation;
    private final StateMachine stateMachine;
    private final EntityState seekingState;
    private boolean isAtDestination;
    private final ResourceManager resourceManager;
    private final SoundManager soundManager;
    private final Animation animation;
    
    public Agent(Data agentData, PathFinder pathFinder
            , Point2D spawnMapLocation, Point2D destinationMapLocation) {
        super(spawnMapLocation, agentData, ((AgentData)agentData).getSpeed());
        this.coordinateTranslator = CoordinateTranslator.getInstance();
        this.pathFinder = pathFinder;
        this.maxHitPoints = ((AgentData)agentData).getMaxHitPoints();
        this.hitPoints = this.maxHitPoints;
        this.destinationMapLocation = destinationMapLocation;
        Point2D agentTileLocation = this.coordinateTranslator.mapToTile(this.mapLocation);
        Point2D destinationTileLocation = this.coordinateTranslator.mapToTile(this.destinationMapLocation);
        this.pathFinder.generatePath(agentTileLocation, destinationTileLocation);
        this.seekingState = new Seeking(this, this.pathFinder.getPathArray());
        this.stateMachine = new StateMachine(this);
        this.stateMachine.setCurrentState(seekingState);
        this.resourceManager = ResourceManager.getResourceManager();
        this.soundManager = SoundManager.getSoundManager();
        this.animation = (Animation)((RenderableData)agentData).getRenderable();
    }

    @Override
    public void update(int delta) 
    {
        this.delta = delta;
        this.stateMachine.update();
        if(this.isAtDestination)
        {
            this.resourceManager.minusPlayerLives();
            this.soundManager.playAgentArrivalSound();
        }
        if(this.hitPoints <= 0) {
            this.isAlive = false;
            this.resourceManager.addGold(25);
            this.soundManager.playAgentDeathSound();
        }
    }
    
    public void gotHit(int damage)
    {
        this.hitPoints -= damage;
    }
    
    public void setIsAtDestination()
    {
        this.isAtDestination = true;
    }
    
    public Animation getAnimation()
    {
        return this.animation;
    }
}
