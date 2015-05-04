/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity;

import edu.moravian.data.object.Data;
import edu.moravian.data.object.ExplosionData;
import edu.moravian.data.object.RenderableData;
import edu.moravian.math.Point2D;
import org.newdawn.slick.Animation;

/**
 *
 * @author danielhuynh
 */
public class Explosion extends Entity
{
    private int timeSinceSpawn = 0;
    private final int explosionTime;
    private boolean isDone = false;
    private Animation animation;
    public Explosion(Point2D mapLocation, Data explosionData) {
        super(mapLocation, explosionData);
        this.explosionTime = ((ExplosionData)explosionData).getExplosionTime();
        this.animation = (Animation)((RenderableData)explosionData).getRenderable();
    }

    @Override
    public void update(int delta) {
        if(timeSinceSpawn >= explosionTime)
            this.isDone = true;
        else
            timeSinceSpawn += delta;
    }
    
    public boolean isDone()
    {
        return this.isDone;
    }
    
    public Animation getAnimation()
    {
        return this.animation;
    }
}
