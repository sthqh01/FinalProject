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
public class ProjectileData extends RenderableData
{
    private final int projectileDataID;
    private final double speed;
    private final int damage;
    public ProjectileData(String dataName, double speed, int damage
            , int projectileID, Renderable renderable) 
    {
        super(dataName, renderable);
        this.speed = speed;
        this.projectileDataID = projectileID;
        this.damage = damage;
    }

    public double getSpeed() {
        return this.speed;
    }
    
    public int getDamage()
    {
        return this.damage;
    }
    
    public int getProjectileDataId()
    {
        return this.projectileDataID;
    }
}
