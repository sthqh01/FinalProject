/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.manager;

import edu.moravian.data.object.WaveData;
import edu.moravian.entity.Agent;
import edu.moravian.entity.Entity;
import java.util.ArrayList;

/**
 *
 * @author danielhuynh
 */
public class Wave extends EntityManager
{
    private final int waveID;
    private final int spawnTime;
    private int timeSinceLastSpawn;
    private int numAgentSoFar;
    private final int numAgentLimit;
    private final AgentFactory agentFactory;
    public Wave(int waveID, AgentFactory agentFactory, WaveData waveData)
    {
        this.waveID = 0;
        this.spawnTime = waveData.getSpawnTime();
        this.timeSinceLastSpawn = 0;
        this.numAgentSoFar = 0;
        this.numAgentLimit = waveData.numAgentLimit();
        this.agentFactory = agentFactory;
    }
    
    @Override
    public void update(int delta) {
        for (int i = 0; i < entityList.size(); i++) {
            Agent agent = (Agent) entityList.get(i);
            if (agent.getIsAlive() == false) {
                entityList.remove(agent);
            }
        }
        for (Entity entity : entityList) {
            entity.update(delta);
        }
        if (this.numAgentSoFar < this.numAgentLimit) {
            if (this.timeSinceLastSpawn > this.spawnTime) {
                entityList.add(this.agentFactory.makeAgent(0));
                this.timeSinceLastSpawn = 0;
                this.numAgentSoFar++;
            } else {
                this.timeSinceLastSpawn += delta;
            }
        }
    }
    
    public ArrayList<Entity> getWave()
    {
        return this.entityList;
    }
    
    public boolean isDone()
    {
        return this.numAgentSoFar == this.numAgentLimit
                && this.entityList.isEmpty();
    }
}
