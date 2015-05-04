/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data.object;

import edu.moravian.math.Point2D;

/**
 *
 * @author danielhuynh
 */
public class MapData extends Data
{
    private final String fileSource;
//    private final Point2D spawnMapLocation;
//    private final Point2D destinationMapLocation;
    public MapData(String dataName, String fileSource) {
//            , Point2D spawnMapLocation, Point2D destinationMapLocation) {
        super(dataName);
        this.fileSource = fileSource;
//        this.spawnMapLocation = spawnMapLocation;
//        this.destinationMapLocation = destinationMapLocation;
    }

    public String getFileSource() {
        return fileSource;
    }

//    public Point2D getSpawnMapLocation() {
//        return spawnMapLocation;
//    }
//
//    public Point2D getDestinationMapLocation() {
//        return destinationMapLocation;
//    }
}
