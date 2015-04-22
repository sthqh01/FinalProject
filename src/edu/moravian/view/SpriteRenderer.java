/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.view;

import edu.moravian.math.CoordinateTranslator;
import edu.moravian.entity.Entity;
import edu.moravian.math.Point2D;
import java.awt.Point;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Renderable;

/**
 *
 * @author Daniel Huynh
 */
public class SpriteRenderer
{
    private int drawX, drawY;
    private boolean goUp, goDown, goLeft, goRight;
    private final Renderable renderable;
    private final CoordinateTranslator coordinateTranslator;
    private final Entity spriteEntity;

    public SpriteRenderer(Renderable renderable, Entity spriteEntity)
    {
        this.renderable = renderable;
        this.coordinateTranslator = CoordinateTranslator.getInstance();
        this.spriteEntity = spriteEntity;
    }

    public void update()
    {
        Point2D spriteMapCoor = spriteEntity.getMapLocation();
        this.drawX = (int) spriteMapCoor.getX();
        this.drawY = (int) spriteMapCoor.getY();
    }
    
    public void render(Graphics grphcs)
    {
        if(goUp)
        {
            grphcs.rotate(drawX+16, drawY+16, -180);
            renderable.draw(drawX, drawY);
            grphcs.rotate(drawX+16, drawY+16, 180);
        }
        else if(goLeft)
        {
            grphcs.rotate(drawX+16, drawY+16, 90);
            renderable.draw(drawX, drawY);
            grphcs.rotate(drawX+16, drawY+16, -90);
        }
        else if(goRight)
        {
            grphcs.rotate(drawX+16, drawY+16, -90);
            renderable.draw(drawX, drawY);
            grphcs.rotate(drawX+16, drawY+16, 90);
        }
        else
        {
            renderable.draw(drawX-16, drawY-16);
        }
    }
}
