/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.manager;

import edu.moravian.entity.Entity;
import edu.moravian.entity.Explosion;

/**
 *
 * @author danielhuynh
 */
public class ExplosionManager extends EntityManager
{
    @Override
    public void update(int delta) {
        for(Entity entity : this.entityList)
            entity.update(delta);
        for(int i = 0; i < this.entityList.size(); i++)
        {
            Explosion explosion = (Explosion) this.entityList.get(i);
            if(explosion.isDone())
                this.entityList.remove(explosion);
        }            
    }
}
