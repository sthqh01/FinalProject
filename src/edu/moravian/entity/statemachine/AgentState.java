/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.statemachine;

import edu.moravian.entity.pathFinding.PathFinder;

/**
 *
 * @author danielhuynh
 */
public interface AgentState extends EntityState 
{   
    public PathFinder getPathFinder();
}
