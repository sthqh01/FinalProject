/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.graph;

import edu.moravian.math.Point2D;

/**
 *
 * @author danielhuynh
 */
public class Edge 
{
    private final Point2D from;
    private final Point2D to;
    public Edge(Point2D from, Point2D to)
    {
        this.from = from;
        this.to = to;
    }
    public Point2D getFrom() {
        return from;
    }
    public Point2D getTo() {
        return to;
    }
}
