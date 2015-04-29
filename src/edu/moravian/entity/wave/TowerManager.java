/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.wave;

import edu.moravian.entity.Entity;
import edu.moravian.entity.Tower;
import java.util.ArrayList;

/**
 *
 * @author danielhuynh
 */
public class TowerManager extends EntityManager
{
    public TowerManager()
    {
        this.entityList = new ArrayList<>();
    }
    
    public void add(Tower tower)
    {
        this.entityList.add(tower);
    }
    
    @Override
    public void update(int delta) {
        for(Entity entity : this.entityList)
            entity.update(delta);
    }
}
