/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.wave;

import edu.moravian.entity.Entity;
import edu.moravian.entity.Projectile;
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
            Projectile projectile = (Projectile)entityList.get(i);
            if (projectile.getIsAlive() == false) {
                entityList.remove(projectile);
            }
        }
        for (Entity entity : this.entityList) {
            entity.update(delta);
        }
    }

    public void add(Projectile projectile) {
        this.entityList.add(projectile);
    }
}
