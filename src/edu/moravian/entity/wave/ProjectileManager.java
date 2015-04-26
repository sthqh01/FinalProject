/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.wave;

import edu.moravian.entity.Agent;
import edu.moravian.entity.Entity;
import edu.moravian.entity.Projectile;
import edu.moravian.math.Point2D;
import java.util.ArrayList;

/**
 *
 * @author danielhuynh
 */
public class ProjectileManager extends EntityManager {

    public ProjectileManager() {
        this.entityList = new ArrayList<>();
    }

    @Override
    public void update(int delta) {
        for (int i = 0; i < entityList.size(); i++) {
            Entity projectile = entityList.get(i);
            if (((Projectile) entityList.get(i)).getIsAlive() == false) {
                entityList.remove(projectile);
            }
        }
        for (Entity entity : this.entityList) {
            entity.update(delta);
        }
    }

    public void add(Point2D mapLocation, Entity target) {
        this.entityList.add(new Projectile(mapLocation, 0.3, target));
    }
}
