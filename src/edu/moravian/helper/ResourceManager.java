/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.helper;

/**
 *
 * @author Daniel Huynh
 */
public class ResourceManager {
    private static ResourceManager instance;
    private int gold;
    private int playerLives;

    public int getPlayerLives() {
        return playerLives;
    }
    
    public void minusPlayerLives() {
        this.playerLives -= 1;
    }
    
    private ResourceManager()
    {
        this.gold = 1000;
        this.playerLives = 10;
    }
    
    public static ResourceManager getResourceManager()
    {
        if(instance == null)
            instance = new ResourceManager();
        return instance;
    }
    
    public synchronized int getGold()
    {
        return this.gold;
    }
    
    public synchronized void addGold(int gold)
    {
        this.gold += gold;
    }
    
    public synchronized void minusGold(int gold)
    {
        this.gold -= gold;
    }
    
    public synchronized void reset()
    {
        this.gold = 1000;
        this.playerLives = 10;
    }
}
