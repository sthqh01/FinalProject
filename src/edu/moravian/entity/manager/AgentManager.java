/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.manager;

import edu.moravian.data.ParameterLoader;
import edu.moravian.data.object.WaveData;
import edu.moravian.entity.pathFinding.PathFinder;
import edu.moravian.math.Point2D;

/**
 *
 * @author danielhuynh
 */
public class AgentManager extends EntityManager {
    private int numWaveSoFar = 0;
    private int numWaveLimit = 3;
    private final Point2D spawnMapLocation, destinationMapLocation;
    private final PathFinder pathFinder;
    private final ParameterLoader agentParameterLoader, waveParameterLoader;
    private final AgentFactory agentFactory;
    private Wave currentWave;
    
    public AgentManager(ParameterLoader agentParameterLoader
            , ParameterLoader waveParameterLoader
            , PathFinder pathFinder
            , Point2D mapLocation, Point2D destinationTileLocation) {
        this.spawnMapLocation = mapLocation;
        this.agentParameterLoader = agentParameterLoader;
        this.waveParameterLoader = waveParameterLoader;
        this.pathFinder = pathFinder;
        this.destinationMapLocation = destinationTileLocation;
        
        this.agentFactory = new AgentFactory(this.agentParameterLoader, this.pathFinder
                , this.spawnMapLocation, this.destinationMapLocation);
        WaveData waveData = (WaveData)this.waveParameterLoader.getData(0);
        this.currentWave = new Wave(this.numWaveSoFar, this.agentFactory, waveData);
        this.entityList = this.currentWave.getWave();
    }

    @Override
    public void update(int delta) {
        if(this.numWaveSoFar < this.numWaveLimit-1 && this.currentWave.isDone())
        {
            WaveData waveData = (WaveData)this.waveParameterLoader.getData(0);
            this.currentWave = new Wave(this.numWaveSoFar, this.agentFactory, waveData);
            this.entityList = this.currentWave.getWave();
            this.numWaveSoFar++;
        }
        else
        {
            this.currentWave.update(delta);
            this.entityList = this.currentWave.getWave();
        }
    }
    
    public int getNumWaveSoFar()
    {
        return this.numWaveSoFar + 1;
    }
    
    public int getNumWaveLimit()
    {
        return this.numWaveLimit;
    }
    
    public void reset()
    {
        this.numWaveSoFar = 0;
        WaveData waveData = (WaveData)this.waveParameterLoader.getData(0);
        this.currentWave = new Wave(this.numWaveSoFar, this.agentFactory, waveData);
    }
    
    public boolean isDone()
    {
       return this.numWaveSoFar == (this.numWaveLimit-1) && this.currentWave.isDone();
    }
}
