/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.main;

import edu.moravian.entity.Agent;
import edu.moravian.entity.Entity;
import edu.moravian.entity.graph.NavGraph;
import edu.moravian.entity.graph.PathFinder;
import edu.moravian.math.CoordinateTranslator;
import edu.moravian.math.Point2D;
import edu.moravian.view.SpriteRenderer;
import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author danielhuynh
 */
public class Play extends BasicGame
{
    private final int screenWidth, screenHeight;
    private double topLeftCornerWX, topLeftCornerWY;
    private boolean goUpKey, goDownKey, goLeftKey, goRightKey
            , exit, logging, viewObstacle, viewMapGraph, viewPathGraph, viewConsideredEdgeGraph;
    private CoordinateTranslator coordinateTranslator;
    private TiledMap tiledMap;
    private NavGraph navGraph;
    private PathFinder pathFinder;
    private SpriteSheet playerSpriteSheet
            , agentSpriteSheet;
    private Animation playerAnimation
            , agentAnimation;
    private SpriteRenderer agentRenderer;
    private Entity agentEntity;

    public Play(String title, int screenWidth, int screenHeight) {
        super(title);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void init(GameContainer gc) throws SlickException 
    {
        this.topLeftCornerWX = 0;
        this.topLeftCornerWY = 0;
        this.tiledMap = new TiledMap("resource/map0.tmx");
        this.coordinateTranslator = CoordinateTranslator.getInstance(
                tiledMap.getWidth(), tiledMap.getHeight()
                , screenWidth, screenHeight, 0, 0);
        this.agentSpriteSheet = new SpriteSheet("resource/agentSheet.png", 32, 32);
        this.agentAnimation = new Animation(this.agentSpriteSheet, 100);
        
        this.pathFinder = new PathFinder(new NavGraph(tiledMap));
        
        this.agentEntity = new Agent(new Point2D(10,90), 0.1, this.pathFinder, new Point2D(1,21));
        this.agentRenderer = new SpriteRenderer(this.agentAnimation, this.agentEntity);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        agentEntity.update(delta);
        agentRenderer.update();
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException 
    {
        tiledMap.render(0, 0);
        agentRenderer.render(grphcs);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
