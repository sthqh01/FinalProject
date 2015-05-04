/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data.object;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Renderable;

/**
 *
 * @author danielhuynh
 */
public abstract class RenderableData extends Data
{
    private final Renderable renderable;
    public RenderableData(String dataName, Renderable renderable) {
        super(dataName);
        this.renderable = renderable;
    }
    
    public Renderable getRenderable()
    {
        if(this.renderable instanceof Animation)
            return ((Animation)this.renderable).copy();
        return this.renderable;
    }    
}
