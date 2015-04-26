///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package edu.moravian.entity.statemachine;
//
//import edu.moravian.entity.Agent;
//import edu.moravian.entity.Entity;
////import edu.moravian.entity.Obstacle;
////import edu.moravian.entity.ObstacleManager;
//import edu.moravian.math.CoordinateTranslator;
//import edu.moravian.math.Point2D;
//import edu.moravian.math.Vector2D;
//
///**
// *
// * @author Daniel Huynh
// */
//public class SteeringBehavior 
//{
//    private final Entity agentEntity;
//    private final CoordinateTranslator coordinateTranslator ;
//    private final double speed;
//    public SteeringBehavior(Entity agentEntity)
//    {
//        this.agentEntity = agentEntity;
//        this.coordinateTranslator = CoordinateTranslator.getInstance();
//        this.speed = ((Agent)agentEntity).getSpeed();
//    }
//    
//    public Vector2D seek(Point2D targetLocation)
//    {
//        Vector2D desiredVelocity = targetLocation.minus(
//                agentEntity.getMapLocation()).times(this.speed);
//        desiredVelocity.normalize();
//        return desiredVelocity.times(this.speed);
//    }
//    
//    public Vector2D obstacleAvoidance(ObstacleManager obstacleManager)
//    {
//        Vector2D heading = ((Agent)agentEntity).getHeading();
//        Vector2D side = ((Agent)agentEntity).getSide();
//        double minDetectionBoxLength = 18;
//        Vector2D steeringForce = new Vector2D(0, 0);
//        double detectionBoxLength = minDetectionBoxLength + speed*minDetectionBoxLength;
//        this.tagObstaclesWithinViewRange(obstacleManager, detectionBoxLength);
//        Obstacle closestIntersectingObstacle = null;
//        double distanceToClosestIntersectingObstacle = Double.MAX_VALUE;
//        Vector2D localPositionOfClosestObstacle = null;
//        for(Obstacle obstacle : obstacleManager.getObstacle())
//        {
//            if(obstacle.isTag())
//            {
//                Vector2D obstaclePosition = 
//                        new Vector2D(obstacle.getMapLocation().getX()
//                                , obstacle.getMapLocation().getY());
//                Vector2D agentPosition =
//                        new Vector2D(this.agentEntity.getMapLocation().getX()
//                                , this.agentEntity.getMapLocation().getY());
//                Vector2D localPosition = 
//                        coordinateTranslator.pointToLocalSpace(obstaclePosition
//                                , heading, side, agentPosition);
//                if(localPosition.getX() >= 0)
//                {
//                    double expandedRadius = obstacle.getRadius() + detectionBoxLength;
//                    if(Math.abs(localPosition.getY()) < expandedRadius)
//                    {
//                        double cX = localPosition.getX();
//                        double cY = localPosition.getY();
//                        
//                        double sqrtPart = Math.sqrt(expandedRadius*expandedRadius - cY*cY);
//                        double ip = cX - sqrtPart;
//                        
//                        if(ip <= 0.0)
//                        {
//                            ip = cX + sqrtPart;
//                        }
//                        
//                        if(ip < distanceToClosestIntersectingObstacle)
//                        {
//                            distanceToClosestIntersectingObstacle = ip;
//                            closestIntersectingObstacle = obstacle;
//                            localPositionOfClosestObstacle = localPosition;
//                        }
//                    }
//                }
//            }
//        }
//        if(closestIntersectingObstacle != null)
//        {
//            double multiplier = 1 +
//                    (detectionBoxLength - localPositionOfClosestObstacle.getX()) 
//                    / detectionBoxLength;
//            double steeringForceY = (closestIntersectingObstacle.getRadius()-
//                       localPositionOfClosestObstacle.getY())*multiplier;
//            double BrakingWeight = 0.3;
//            double steeringForceX = (closestIntersectingObstacle.getRadius() - 
//                       localPositionOfClosestObstacle.getX())*BrakingWeight;
//            steeringForce = new Vector2D(steeringForceX, steeringForceY);
//        }
//        steeringForce = coordinateTranslator.vectorToWorldSpace(steeringForce
//                , heading, side);
//        return steeringForce;
//    }
//    
//    private void tagObstaclesWithinViewRange(ObstacleManager obstacleManager, double radius)
//    {
//       for(Obstacle obstacle : obstacleManager.getObstacle())
//       {
//           obstacle.untag();
//           Vector2D to = obstacle.getMapLocation().minus(agentEntity.getMapLocation());
//           double range = radius + obstacle.getRadius();
//           if(to.magnitudeSq() < range*range)
//           {
//               obstacle.tag();
//           }
//       }
//    }
//}
