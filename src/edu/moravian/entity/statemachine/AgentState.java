/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.entity.statemachine;

import edu.moravian.entity.graph.PathFinder;
import edu.moravian.entity.Agent;

/**
 *
 * @author danielhuynh
 */
public interface AgentState 
{   
    public void execute(Agent agentEntity);
    public PathFinder getPathFinder();
}
