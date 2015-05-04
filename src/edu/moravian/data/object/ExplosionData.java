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
public class ExplosionData extends RenderableData
{
    private final int explosionTime;
    public ExplosionData(String dataName, int explosionTime, Renderable renderable) {
        super(dataName, renderable);
        this.explosionTime = explosionTime;
    }

    public int getExplosionTime() {
        return explosionTime;
    }
}
