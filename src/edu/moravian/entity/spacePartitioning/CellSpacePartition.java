/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.spacePartitioning;

import edu.moravian.entity.Entity;
import edu.moravian.entity.manager.EntityManager;
import edu.moravian.math.Point2D;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Daniel Huynh
 */
public class CellSpacePartition {

    private final int mapWidth, mapHeight, numCellsX, numCellsY, cellSizeX, cellSizeY;
    private final ArrayList<Cell> cellList;
    private final EntityManager agentManager;

    public CellSpacePartition(int mapWidth, int mapHeight, int cellsX, int cellsY, EntityManager agentManager) {
        this.cellList = new ArrayList<>();
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.numCellsX = cellsX;
        this.numCellsY = cellsY;
        this.cellSizeX = this.mapWidth / this.numCellsX;
        this.cellSizeY = this.mapHeight / this.numCellsY;
        this.createCells();
        this.agentManager = agentManager;
    }

    private void createCells() {
        for (int y = 0; y < numCellsY; ++y) {
            for (int x = 0; x < numCellsX; ++x) {
                double left = x * cellSizeX;
                double right = left + cellSizeX;
                double top = y * cellSizeY;
                double bot = top + cellSizeY;
                this.cellList.add(new Cell(new Point2D(left, top), new Point2D(right, bot)));
            }
        }
    }

    public void update() {
        for (Cell cell : this.cellList) {
            cell.clear();
        }
        for (Entity entity : agentManager.getEntity()) {
            int index = this.mapPositionToIndex(entity.getMapLocation());
            this.cellList.get(index).add(entity);
        }
    }

    public int mapPositionToIndex(Point2D entityMapLocation) {
        int index = (int) (numCellsX * entityMapLocation.getX() / mapWidth)
                + ((int) ((numCellsY) * entityMapLocation.getY() / mapHeight) * numCellsX);

        //if the entity's position is equal to vector2d(m_dSpaceWidth, m_dSpaceHeight)
        //then the index will overshoot. We need to check for this and adjust
        if (index > (int) cellList.size() - 1) {
            index = (int) cellList.size() - 1;
        }
        return index;
    }

    public Iterable<Entity> getConsideredTargets(Point2D entityMapLocation, int queryRadius) {
        ArrayList<Entity> targetList = new ArrayList<>();
        Circle entityBoudingCircle = new Circle((float) entityMapLocation.getX(), (float) entityMapLocation.getY(), (float) queryRadius);
        for (Cell cell : this.cellList) {
            if (this.isOverlapped(cell.getBoundingBox(), entityBoudingCircle)
                    && !cell.isEmpty()) {
                for (Entity entity : cell.getEntity()) {
                        targetList.add(entity);
                    }
                }
            }
        return targetList;
    }
    
    public Entity getClosestTarget(Point2D entityMapLocation, int queryRadius)
    {
        Entity closestEntity = null;
        int shortestSquaredDistance = Integer.MAX_VALUE;
        Circle entityBoudingCircle = new Circle((float) entityMapLocation.getX(), (float) entityMapLocation.getY(), (float) queryRadius);
        for (Cell cell : this.cellList) {
            if (this.isOverlapped(cell.getBoundingBox(), entityBoudingCircle)
                    && !cell.isEmpty()) {
                for (Entity entity : cell.getEntity()) {
                    int squaredDistance = this.getSquaredDistance(entityMapLocation, entity.getMapLocation());
                    if (squaredDistance < shortestSquaredDistance && squaredDistance <= queryRadius * queryRadius) {
                        shortestSquaredDistance = squaredDistance;
                        closestEntity = entity;
                    }
                }
            }
        }
        return closestEntity;
    }
    
    public boolean isOverlapped(Rectangle boundingBox, Circle boundingCircle)
    {
        Point2D boudingBoxCenter 
                = new Point2D(boundingBox.getCenterX(), boundingBox.getCenterY());
        Point2D boudingCircleCenter
                = new Point2D(boundingCircle.getCenterX(), boundingCircle.getCenterY());
        int squaredDistanceOfTwoCenters 
                = this.getSquaredDistance(boudingBoxCenter, boudingCircleCenter);
        if(squaredDistanceOfTwoCenters 
                < Math.pow(boundingBox.getBoundingCircleRadius()+boundingCircle.getRadius(), 2))
            return true;
        return false;
    }

    private int getSquaredDistance(Point2D entityMapLocation, Point2D targetEntityMapLocation) {
        return (int) (((targetEntityMapLocation.getX() - entityMapLocation.getX())
                * (targetEntityMapLocation.getX() - entityMapLocation.getX()))
                + ((targetEntityMapLocation.getY() - entityMapLocation.getY())
                * (targetEntityMapLocation.getY() - entityMapLocation.getY())));
    }
    
    public void render(Graphics grphcs)
    {
        for(Cell cell : this.cellList)
        {
            grphcs.draw(cell.getBoundingBox());
        }
    }
    
    public EntityManager getAgentManager()
    {
        return this.agentManager;
    }
}
