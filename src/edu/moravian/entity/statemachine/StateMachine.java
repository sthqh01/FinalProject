/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.statemachine;
import edu.moravian.entity.Entity;


/**
 *
 * @author danielhuynh
 */
public class StateMachine 
{
    private final Entity entity;
    private EntityState currentState, previousState, globalState;
    public StateMachine(Entity entity)
    {
        this.entity = entity;
        this.currentState = null;
        this.previousState = null;
        this.globalState = null;
    }
    
    public void setCurrentState(EntityState state)
    {
        this.currentState = state;
    }

    public void setPreviousState(EntityState state) {
        this.previousState = state;
    }

    public void setGlobalState(EntityState state) {
        this.globalState = state;
    }
    
    public void update()
    {
        if(currentState!=null)
            currentState.execute();
    }
    
    public void changeState(EntityState newState)
    {
        previousState = currentState;
        currentState = newState;
    }
    
    public void revertToPreviousState()
    {
        this.changeState(previousState);
    }

    public EntityState getCurrentState() {
        return currentState;
    }

    public EntityState getPreviousState() {
        return previousState;
    }

    public EntityState getGlobalState() {
        return globalState;
    }
    
    public String getCurrentStateString()
    {
        return "Seeking";
    }
        
    public boolean isInState(AgentState state)
    {
        if(this.currentState.equals(state))
        {
            return true;
        }
        return false;
    }
}
