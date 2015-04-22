/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.graph;

import edu.moravian.math.Point2D;

/**
 *
 * @author danielhuynh
 */
public class Vertex implements Comparable<Vertex>
{
    public Point2D data;
    public double minDistance = Double.POSITIVE_INFINITY;
    public double huristicMinDistance = Double.POSITIVE_INFINITY;
    public Vertex previousVertex;
    public Vertex(Point2D point)
    {
        this.data = point;
    }
    public boolean equals(Vertex other)
    {
        return this.data.equals(other.data);
    }
    @Override
    public int compareTo(Vertex other) 
    {
        return Double.compare(huristicMinDistance, other.huristicMinDistance);
    }
}
