/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.helper;

import edu.moravian.data.object.TowerData;
import edu.moravian.entity.Entity;
import edu.moravian.entity.Tower;
import edu.moravian.entity.manager.EntityManager;
import edu.moravian.math.CoordinateTranslator;
import edu.moravian.math.Point2D;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Daniel Huynh
 */
public class EntitySelector {
    private final EntityManager towerManager;
    private Entity selectedEntity;
    private Point2D mouseTileLocation;
    private final CoordinateTranslator coordinateTranslator;
    
    public EntitySelector(EntityManager towerManager)
    {
        this.towerManager = towerManager;
        this.coordinateTranslator = CoordinateTranslator.getInstance();
    }
    
    public void update(Point2D mouseTileLocation, boolean isMousePressed)
    {
        this.mouseTileLocation = mouseTileLocation;
        if(isMousePressed && mouseTileLocation.getX()<coordinateTranslator.getWorldWidth())
            this.selectedEntity = this.selectEntity();
    }
    
    private Entity selectEntity()
    {
        for(Entity entity : this.towerManager.getEntity()) {
            Point2D entityTileLocation 
                    = this.coordinateTranslator.mapToTile(entity.getMapLocation());
            if(entityTileLocation.equals(this.mouseTileLocation))
                return entity;
        }
        return null;
    }
    
    public Entity getSelectedEntity()
    {
        return this.selectedEntity;
    }
    
    public void render(Graphics grphcs)
    {
        if (this.selectedEntity != null) {
            Image entityAvatar = (Image) ((TowerData) (this.selectedEntity.getEntityData())).getRenderable();
            entityAvatar.draw(1067, 51);
            int radius = ((Tower) selectedEntity).getSightRadius();
            Circle selectedEntitySight = new Circle((float) this.selectedEntity.getMapLocation().getX(), (float) this.selectedEntity.getMapLocation().getY(), (float) radius);
            grphcs.draw(selectedEntitySight);

            for (Entity entity : ((Tower) this.selectedEntity).getConsideredTarget()) {
                Circle circle = new Circle((float) entity.getMapLocation().getX()
                        , (float) entity.getMapLocation().getY(), 16);
                grphcs.draw(circle);
            }
        }
    }
    
    public void reset()
    {
        this.selectedEntity = null;
    }
}
