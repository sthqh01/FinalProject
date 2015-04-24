/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.graph;

import edu.moravian.math.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 * @author danielhuynh
 */
public class PathFinder 
{
    private final NavGraph navGraph;

    private final ArrayList<Edge> consideredEdgeList;
    private final ArrayList<Edge> pathEdgeList;
    private Vertex target;
    
    public PathFinder (NavGraph navGraph)
    {
        this.navGraph = navGraph;
        this.consideredEdgeList = new ArrayList<>();
        this.pathEdgeList = new ArrayList<>();
    }
    
    public void generatePath(Point2D from, Point2D to)
    {
        this.consideredEdgeList.clear();
        Vertex fromVertex = new Vertex(from);
        Vertex toVertex = new Vertex(to);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        fromVertex.minDistance = 0;
        priorityQueue.add(fromVertex);
        Vertex currentVertex, nextVertex;
        while(!priorityQueue.isEmpty())
        {
            currentVertex = priorityQueue.poll();
            this.target = currentVertex;
            if (currentVertex.equals(toVertex))
            {
                this.target = currentVertex;
                break;
            }
            for (Point2D next : navGraph.getNeighbors(currentVertex.data))
            {
                consideredEdgeList.add(new Edge(currentVertex.data, next));
                nextVertex = new Vertex(next);
                double newDistance = currentVertex.minDistance 
                        + getDistance(currentVertex, nextVertex);
                if(!this.isInPriorityQueue(priorityQueue, nextVertex) &&
                        newDistance < nextVertex.minDistance)
                {
                    priorityQueue.remove(nextVertex);
                    nextVertex.minDistance = newDistance;
                    nextVertex.huristicMinDistance = newDistance 
                            + huristic(nextVertex, toVertex);
                    priorityQueue.add(nextVertex);
                    nextVertex.previousVertex = currentVertex;
                }
            }
        }
    }
    
    public void generatePathIgnoringObstacles(Point2D from, Point2D to)
    {
        this.consideredEdgeList.clear();
        Vertex fromVertex = new Vertex(from);
        Vertex toVertex = new Vertex(to);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        fromVertex.minDistance = 0;
        priorityQueue.add(fromVertex);
        Vertex currentVertex, nextVertex;
        while(!priorityQueue.isEmpty())
        {
            currentVertex = priorityQueue.poll();
            this.target = currentVertex;
            if (currentVertex.equals(toVertex))
            {
                this.target = currentVertex;
                break;
            }
            for (Point2D next 
                    : navGraph.getNeighborsIgnoringObstacles(currentVertex.data))
            {
                nextVertex = new Vertex(next);
                double newDistance = currentVertex.minDistance 
                        + getDistance(currentVertex, nextVertex);
                if(!this.isInPriorityQueue(priorityQueue, nextVertex) &&
                        newDistance < nextVertex.minDistance)
                {
                    priorityQueue.remove(nextVertex);
                    nextVertex.minDistance = newDistance;
                    nextVertex.huristicMinDistance = newDistance +
                            huristic(nextVertex, toVertex);
                    priorityQueue.add(nextVertex);
                    nextVertex.previousVertex = currentVertex;
                }
            }
        }
    }
    
    public Iterable<Point2D> getPath()
    {
        ArrayList<Point2D> path = new ArrayList<>();
        for(Vertex vertex = this.target; vertex != null
                ; vertex = vertex.previousVertex)
        {
            Point2D p = vertex.data;
            path.add(p);
        }
        Collections.reverse(path);
        this.pathEdgeList.clear();
        Point2D previousPoint = null;
        Point2D currentPoint;
        int i = 0;
        for(Point2D point : path)
        {
            currentPoint = point;
            if(i==0)
            {
                previousPoint = currentPoint;
                i++;
            }
            else
            {
                pathEdgeList.add(new Edge(previousPoint, currentPoint));
                previousPoint = currentPoint;
            }
        }
        return path;
    }
    
    public ArrayList<Point2D> getPathArray()
    {
        ArrayList<Point2D> path = new ArrayList<>();
        for(Vertex vertex = this.target; vertex != null
                ; vertex = vertex.previousVertex)
        {
            Point2D p = vertex.data;
            path.add(p);
        }
        Collections.reverse(path);
        this.pathEdgeList.clear();
        Point2D previousPoint = null;
        Point2D currentPoint;
        int i = 0;
        for(Point2D point : path)
        {
            currentPoint = point;
            if(i==0)
            {
                previousPoint = currentPoint;
                i++;
            }
            else
            {
                pathEdgeList.add(new Edge(previousPoint, currentPoint));
                previousPoint = currentPoint;
            }
        }
        return path;
    }
    
    public Iterable<Edge> getConsideredEdges()
    {
        return consideredEdgeList;
    }
    
    public Iterable<Edge> getPathEdges()
    {
        return this.pathEdgeList;
    }
    
    private double getDistance(Vertex a, Vertex b)
    {
        if((a.data.getX() == b.data.getX()) || (a.data.getY() == b.data.getY()))
            return 1;
        return 1.5;
    }
    
    private double huristic(Vertex a, Vertex b)
    {
        return Math.abs(a.data.getX()-b.data.getX()) 
                + Math.abs(a.data.getY()-b.data.getY());
    }
    
    private double huristic2(Vertex a, Vertex b)
    {
        double x = a.data.getX()-b.data.getX();
        double y = a.data.getY()-b.data.getY();
        return x*x + y*y;
    }
    
    private boolean isInPriorityQueue(PriorityQueue frontier, Vertex vertex)
    {
        Vertex[] vertexArray = (Vertex[]) frontier.toArray(new Vertex[0]);
        for (Vertex vertexArray1 : vertexArray) {
            if (vertexArray1.equals(vertex)) {
                return true;
            }
        }
        return false;
    }
    
    public void clear()
    {
        this.consideredEdgeList.clear();
        this.pathEdgeList.clear();
    }
    
    public Vertex getTarget() {
        return this.target;
    }
    
    public NavGraph getNavGraph()
    {
        return this.navGraph;
    }
}
