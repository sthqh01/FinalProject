/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity;

import edu.moravian.data.object.Data;
import edu.moravian.math.Point2D;

/**
 *
 * @author Daniel Huynh
 */
public abstract class Entity {

    private static int nextID = 0;
    protected int ID;
    protected Point2D mapLocation;
    protected int delta;
    protected Data entityData;

    public Entity(Point2D mapLocation, Data entityData) {
        this.ID = this.nextID();
        this.mapLocation = mapLocation;
        this.entityData = entityData;
    }

    public abstract void update(int delta);

    public Point2D getMapLocation() {
        return mapLocation;
    }

    private int nextID() {
        return nextID++;
    }

    public int getID() {
        return ID;
    }
    
    public Data getEntityData()
    {
        return this.entityData;
    }
}
