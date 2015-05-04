/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.manager;

import edu.moravian.data.ParameterLoader;
import edu.moravian.data.object.ExplosionData;
import edu.moravian.data.object.ProjectileData;
import edu.moravian.entity.Entity;
import edu.moravian.entity.Explosion;
import edu.moravian.entity.Projectile;
import java.util.ArrayList;

/**
 *
 * @author danielhuynh
 */
public class ProjectileManager extends EntityManager {
    private EntityManager explosionManager;
    private ParameterLoader explosionParameterLoader;
    public ProjectileManager(EntityManager explosionManager
            , ParameterLoader explosionParameterLoader) {
        this.explosionManager = explosionManager;
        this.explosionParameterLoader = explosionParameterLoader;
    }

    @Override
    public void update(int delta) {
        for (int i = 0; i < entityList.size(); i++) {
            Projectile projectile = (Projectile)entityList.get(i);
            if (projectile.getIsAlive() == false) {
                entityList.remove(projectile);
                int index = (int)((ProjectileData)projectile.getEntityData()).getProjectileDataId();
                ExplosionData explosionData 
                        = (ExplosionData)this.explosionParameterLoader.getData(index);
                ((ExplosionManager)this.explosionManager)
                        .add(new Explosion(projectile.getMapLocation(), explosionData));
            }
        }
        for (Entity entity : this.entityList) {
            entity.update(delta);
        }
    }
}
