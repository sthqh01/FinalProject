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
public class AgentData extends Data
{
    private final int maxHitPoints;
    private final double speed;
    public AgentData(String agentName, int maxHitPoints, double speed)
    {
        super(agentName);
        this.maxHitPoints = maxHitPoints;
        this.speed = speed;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public double getSpeed() {
        return speed;
    }
}
