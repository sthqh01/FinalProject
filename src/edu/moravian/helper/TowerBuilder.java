/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.helper;

import edu.moravian.data.ParameterLoader;
import edu.moravian.data.object.ProjectileData;
import edu.moravian.data.object.TowerData;
import edu.moravian.entity.Entity;
import edu.moravian.entity.Tower;
import edu.moravian.entity.manager.EntityManager;
import edu.moravian.entity.manager.TowerManager;
import edu.moravian.entity.spacePartitioning.CellSpacePartition;
import edu.moravian.math.CoordinateTranslator;
import edu.moravian.math.Point2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author danielhuynh
 */
public class TowerBuilder 
{
    private final TiledMap tiledMap;
    private final EntityManager towerManager
            , projectileManager;
    private final CoordinateTranslator coordinateTranslator;
    private Point2D mouseTileLocation;
    private final ParameterLoader towerParameterLoader, projectileParameterLoader;
    private final ResourceManager resourceManager;
    private final CellSpacePartition cellSpacePartition;
    public TowerBuilder(TiledMap tiledMap, ParameterLoader towerParameterLoader, ParameterLoader projectileParameterLoader
            , EntityManager towerManager, CellSpacePartition cellSpacePartition, EntityManager projectileManager)
    {
        this.cellSpacePartition = cellSpacePartition;
        this.tiledMap = tiledMap;
        this.towerManager = towerManager;
        this.projectileManager = projectileManager;
        this.towerParameterLoader = towerParameterLoader;
        this.projectileParameterLoader = projectileParameterLoader;
        this.coordinateTranslator = CoordinateTranslator.getInstance();
        this.mouseTileLocation = new Point2D(0,0);
        this.resourceManager = ResourceManager.getResourceManager();
    }
    
    private void build(Point2D mouseTileLocation, int type)
    {
        if(isBuildable(mouseTileLocation)) {
            Point2D towerMapLocation = coordinateTranslator.tileToMap(mouseTileLocation);
            TowerData towerData = (TowerData)this.towerParameterLoader.getData(type);
            if(towerData.getCost() <= this.resourceManager.getGold()) {
                ProjectileData projectileData = (ProjectileData)this.projectileParameterLoader.getData(type);
                ((TowerManager)this.towerManager).add(new Tower(towerMapLocation
                        , towerData, projectileData,this.cellSpacePartition, this.projectileManager));
                this.resourceManager.minusGold(towerData.getCost());
            }
        }
    }
    
    private boolean isBuildable(Point2D mouseTileLocation)
    {
        if(tiledMap.getTileId((int)mouseTileLocation.getX()
                , (int)mouseTileLocation.getY(), tiledMap.getLayerIndex("Object")) != 0
                && !this.isOnTopOfOtherTower(mouseTileLocation))
            return true;
        return false;
    }
    
    private boolean isOnTopOfOtherTower(Point2D mouseTileLocation)
    {
        Point2D towerTileLocation;
        for(Entity tower : this.towerManager.getEntity()) {
            towerTileLocation = coordinateTranslator.mapToTile(tower.getMapLocation());
            if(towerTileLocation.equals(mouseTileLocation))
                return true;
        }
        return false;
    }
    
    public void update(Point2D mouseTileLocation, boolean isMousePressed, int towerType)
    {
        if(isMousePressed)
            this.build(mouseTileLocation, towerType);
        this.mouseTileLocation = mouseTileLocation;
    }
    
    public void render(Graphics grphcs)
    {
        Point2D towerMapLocation = coordinateTranslator.tileToMap(this.mouseTileLocation);
        if (this.isBuildable(this.mouseTileLocation)) {
            grphcs.setColor(Color.green);
            grphcs.fillRect((int) towerMapLocation.getX() - 16
                    , (int) towerMapLocation.getY() - 16, 32, 32);
        } else {
            grphcs.setColor(Color.red);
            grphcs.fillRect((int) towerMapLocation.getX() - 16
                    , (int) towerMapLocation.getY() - 16, 32, 32);
        }
    }
}
