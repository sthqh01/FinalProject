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
public class TowerData extends Data
{
    private final int sighRadius;
    private final int fireDelay;
    private final int damage;
    public TowerData(String towerName, int sightRadius, int fireDelay, int damage)
    {
        super(towerName);
        this.sighRadius = sightRadius;
        this.fireDelay = fireDelay;
        this.damage = damage;
    }

    public int getSighRadius() {
        return sighRadius;
    }

    public int getFireDelay() {
        return fireDelay;
    }

    public int getDamage() {
        return damage;
    }
}
