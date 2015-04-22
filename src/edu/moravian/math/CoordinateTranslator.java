/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.math;

import java.awt.Point;

/**
 *
 * @author danielhuynh
 */
public class CoordinateTranslator {
    private static CoordinateTranslator instance;
    final int worldWidth, worldHeight, screenWidth, screenHeight, wllX, wllY;

    private CoordinateTranslator(int worldWidth, int worldHeight, int screenWidth, int screenHeight, int wllX, int wllY) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.wllX = wllX;
        this.wllY = wllY;
    }
    
    public static synchronized CoordinateTranslator getInstance(int worldWidth, int worldHeight
            , int screenWidth, int screenHeight, int wllX, int wllY)
    {
        if(CoordinateTranslator.instance == null)
            CoordinateTranslator.instance = new CoordinateTranslator(worldWidth
                    , worldHeight, screenWidth, screenHeight, wllX, wllY);
        return instance;
    }
    
    public static synchronized CoordinateTranslator getInstance()
    {
        return instance;
    }

    public synchronized Point2D screenToWorld(int x, int y) {
        double worldX = x * worldWidth / screenWidth + wllX;
        double worldY = worldHeight + wllY - ((worldHeight * y) / screenHeight);
        Point2D returnPoint = new Point2D(worldX, worldY);
        return returnPoint;
    }

    public synchronized Point2D screenToWorld(Point p) {
        double worldX = p.getX() * worldWidth / screenWidth + wllX;
        double worldY = worldHeight + wllY - ((worldHeight * p.getY()) / screenHeight);
        Point2D returnPoint = new Point2D(worldX, worldY);
        return returnPoint;
    }

    public synchronized Point worldToScreen(double x, double y) {
        int screenX = (int) ((x - wllX) * screenWidth / worldWidth);
        int screenY = (int) ((worldHeight - y + wllY) * screenHeight / worldHeight);
        Point returnPoint = new Point(screenX, screenY);
        return returnPoint;
    }

    public synchronized Point worldToScreen(Point2D p) {
        int screenX = (int) ((p.getX() - wllX) * screenWidth / worldWidth);
        int screenY = (int) ((worldHeight - p.getY() + wllY) * screenHeight / worldHeight);
        Point returnPoint = new Point(screenX, screenY);
        return returnPoint;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
    
    public synchronized Point2D tileToMap(int x, int y)
    {
        int mapX = 15 + (x*32);
        int mapY = 15 + (y*32);
        return new Point2D(mapX, mapY);
    }
    
    public synchronized Point2D tileToMap(Point2D point)
    {
        int mapX = (int) (15 + (point.getX()*32));
        int mapY = (int) (15 + (point.getY()*32));
        return new Point2D(mapX, mapY);
    }
    
    public synchronized Vector2D pointToLocalSpace(Vector2D point, Vector2D entityHeading
            , Vector2D entitySide, Vector2D entityPosition)
    {
        double Tx = (-1)*entityPosition.dot(entityHeading);
        double Ty = (-1)*entityPosition.dot(entitySide);
        C2DMatrix matTransform = new C2DMatrix();
        matTransform._11(entityHeading.getX());
        matTransform._12(entitySide.getX());
        matTransform._21(entityHeading.getY());
        matTransform._22(entitySide.getY());
        matTransform._31(Tx);
        matTransform._32(Ty);
        Vector2D transPoint = matTransform.transformVector2D(point);
        return transPoint;
    }
    
    public synchronized Vector2D vectorToWorldSpace(Vector2D vector,
            Vector2D entityHeading,
            Vector2D entitySide) 
    {
        //create a transformation matrix
        C2DMatrix matTransform = new C2DMatrix();
        //rotate
        matTransform.rotate(entityHeading, entitySide);
        //now transform the vertices
        Vector2D TransVec = matTransform.transformVector2D(vector);
        return TransVec;
    }
//    public Point worldToMap(double x, double y)
//    {
//        int mapX = (int) ((x - wllX)*screenWidth/worldWidth);
//        int mapY = (int) (((worldHeight - y + wllY)*screenHeight/worldHeight) + worldHeight);
//        Point returnPoint = new Point(mapX, mapY);
//        return returnPoint;
//    }
}
