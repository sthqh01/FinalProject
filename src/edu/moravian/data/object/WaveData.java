/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data.object;

/**
 *
 * @author danielhuynh
 */
public class WaveData extends Data
{
    private final int spawnTime;
    private final int numAgentLimit;
    public WaveData(String waveName, int spawnTime, int numAgentLimit)
    {
        super(waveName);
        this.spawnTime = spawnTime;
        this.numAgentLimit = numAgentLimit;
    }

    public int getSpawnTime() {
        return spawnTime;
    }
    
    public int numAgentLimit()
    {
        return this.numAgentLimit;
    }
}
