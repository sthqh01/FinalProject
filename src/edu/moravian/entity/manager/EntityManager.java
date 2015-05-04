/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.manager;

import edu.moravian.entity.Entity;
import java.util.ArrayList;

/**
 *
 * @author danielhuynh
 */
public abstract class EntityManager 
{
    protected ArrayList<Entity> entityList;
    public EntityManager()
    {
        this.entityList = new ArrayList<>();
    }
    public abstract void update(int delta);
    public Iterable<Entity> getEntity()
    {
        return this.entityList;
    }
    public void add(Entity entity)
    {
        this.entityList.add(entity);
    }
    public void clear()
    {
        this.entityList.clear();
    }
}
