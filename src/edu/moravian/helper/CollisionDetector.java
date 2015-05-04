/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.helper;

import edu.moravian.entity.Entity;
import edu.moravian.math.Point2D;

/**
 *
 * @author Daniel Huynh
 */
public class CollisionDetector 
{
    public static boolean checkCollision(Entity entity1, Entity entity2)
    {
        return Math.pow(16.0+16.0, 2) > 
                (Math.pow(entity1.getMapLocation().getX()-entity2.getMapLocation().getX(), 2) 
                + Math.pow(entity1.getMapLocation().getY()-entity2.getMapLocation().getY(), 2));
    }
    
    public static boolean checkCollision(Point2D mapLocation1, Point2D mapLocation2)
    {
        return Math.pow(8.0+8.0, 2) > 
                (Math.pow(mapLocation1.getX()-mapLocation2.getX(), 2) 
                + Math.pow(mapLocation1.getY()-mapLocation2.getY(), 2));
    }
}
