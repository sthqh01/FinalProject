/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.view;

import edu.moravian.data.object.RenderableData;
import edu.moravian.entity.Agent;
import edu.moravian.entity.Entity;
import edu.moravian.entity.Explosion;
import edu.moravian.entity.Projectile;
import edu.moravian.entity.Tower;
import edu.moravian.math.Point2D;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Daniel Huynh
 */
public class SpriteRenderer {

    private int drawX, drawY;
    private Renderable renderable;
    private final Entity spriteEntity;
    private double prevAngle;

    public SpriteRenderer(Entity spriteEntity) throws SlickException {
        RenderableData renderableData = (RenderableData) spriteEntity.getEntityData();
        this.renderable = renderableData.getRenderable();
        this.spriteEntity = spriteEntity;
        this.prevAngle = 0;
    }

    public void update() {
        Point2D spriteMapCoor = spriteEntity.getMapLocation();
        this.drawX = (int) spriteMapCoor.getX();
        this.drawY = (int) spriteMapCoor.getY();
    }

    public void render(Graphics grphcs) {
        if (spriteEntity instanceof Tower) {
            Entity target = ((Tower) spriteEntity).getCurrentTarget();
            if (target != null) {
                double angle = Math.atan2(target.getMapLocation().getY() - spriteEntity.getMapLocation().getY(), target.getMapLocation().getX() - spriteEntity.getMapLocation().getX());
                angle = angle * (180 / Math.PI) + 90 - prevAngle;
                prevAngle = angle;
                grphcs.rotate(drawX, drawY, (float) angle);
                this.renderable.draw(drawX - 16, drawY - 16);
                grphcs.rotate(drawX, drawY, -(float) angle);
            } else {
                this.renderable.draw(drawX - 16, drawY - 16);
            }
        } else if (spriteEntity instanceof Projectile) {
            Entity target = ((Projectile) spriteEntity).getTarget();
            if (target != null) {
                double angle = Math.atan2(target.getMapLocation().getY() - spriteEntity.getMapLocation().getY(), target.getMapLocation().getX() - spriteEntity.getMapLocation().getX());
                angle = angle * (180 / Math.PI) + 90 - prevAngle;
                prevAngle = angle;
                grphcs.rotate(drawX, drawY, (float) angle);
                this.renderable.draw(drawX - 16, drawY - 16);
                grphcs.rotate(drawX, drawY, -(float) angle);
            } else {
                this.renderable.draw(drawX - 16, drawY - 16);
            }
        } else {
            if(spriteEntity instanceof Explosion)
                this.renderable = ((Explosion)spriteEntity).getAnimation();
            else if(spriteEntity instanceof Agent)
                this.renderable = ((Agent)spriteEntity).getAnimation();
            renderable.draw(drawX - 16, drawY - 16);
        }
    }
}
