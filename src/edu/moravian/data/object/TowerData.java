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
public class TowerData extends RenderableData
{
    private final int cost;
    private final int sightRadius;
    private final int fireDelay;
    private final int damage;
    private final double upgradeChangePercentage;
    private final int maxLevel;
    public TowerData(String towerName, int cost, int sightRadius, int fireDelay
            , int damage, double upgradeChangePercentage, int maxLevel, Renderable renderable)
    {
        super(towerName, renderable);
        this.cost = cost;
        this.sightRadius = sightRadius;
        this.fireDelay = fireDelay;
        this.damage = damage;
        this.upgradeChangePercentage = upgradeChangePercentage;
        this.maxLevel = maxLevel;
    }
    
    public int getCost()
    {
        return this.cost;
    }

    public int getSightRadius() {
        return sightRadius;
    }

    public int getFireDelay() {
        return fireDelay;
    }

    public int getDamage() {
        return damage;
    }
    
    public double getPercentageChangeUpgrade()
    {
        return this.upgradeChangePercentage;
    }
    
    public int getMaxLevel()
    {
        return this.maxLevel;
    }
}
