/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data.object;

import org.newdawn.slick.Renderable;

/**
 *
 * @author danielhuynh
 */
public class AgentData extends RenderableData
{
    private final int maxHitPoints;
    private final double speed;
    public AgentData(String agentName, int maxHitPoints, double speed, Renderable renderable)
    {
        super(agentName, renderable);
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
