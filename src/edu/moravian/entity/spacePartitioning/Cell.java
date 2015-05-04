/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.spacePartitioning;

import edu.moravian.entity.Entity;
import edu.moravian.math.Point2D;
import java.util.ArrayList;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Daniel Huynh
 */
public class Cell {
    private ArrayList<Entity> entityList;
    private Point2D topLeftMapLocation, bottomRightMapLocation;
    private Rectangle boundingBox;
    public Cell(Point2D topLeftMapLocation, Point2D bottomRightMapLocation)
    {
        this.entityList = new ArrayList<>();
        this.topLeftMapLocation = topLeftMapLocation;
        this.bottomRightMapLocation = bottomRightMapLocation;
        this.boundingBox = new Rectangle((float)topLeftMapLocation.getX(), (float) topLeftMapLocation.getY()
                , (float)(bottomRightMapLocation.getX()-topLeftMapLocation.getX())
                , (float)(bottomRightMapLocation.getY()-topLeftMapLocation.getY()));
    }
    public Iterable<Entity> getEntity()
    {
        return this.entityList;
    }
    public void add(Entity entity)
    {
        this.entityList.add(entity);
    }
    public void remove(Entity entity)
    {
        this.entityList.remove(entity);
    }
    public int size()
    {
        return this.entityList.size();
    }
    public void clear()
    {
        this.entityList.clear();
    }
    public boolean isEmpty()
    {
       return this.entityList.isEmpty();
    }
    public Rectangle getBoundingBox()
    {
        return this.boundingBox;
    }
}
