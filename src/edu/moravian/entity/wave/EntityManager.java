/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.wave;

import edu.moravian.entity.Entity;
import java.util.ArrayList;

/**
 *
 * @author danielhuynh
 */
public abstract class EntityManager 
{
    protected ArrayList<Entity> entityList;
    public abstract void update(int delta);
    public Iterable<Entity> getEntity()
    {
        return this.entityList;
    }
}
