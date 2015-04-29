/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.main;

import edu.moravian.data.AgentParameterLoader;
import edu.moravian.data.MapParameterLoader;
import edu.moravian.data.ParameterLoader;
import edu.moravian.data.TowerParameterLoader;
import edu.moravian.data.object.MapData;
import edu.moravian.entity.graph.NavGraph;
import edu.moravian.entity.graph.PathFinder;
import edu.moravian.entity.wave.AgentManager;
import edu.moravian.entity.wave.EntityManager;
import edu.moravian.entity.wave.ProjectileManager;
import edu.moravian.entity.wave.TowerManager;
import edu.moravian.helper.TowerBuilder;
import edu.moravian.math.CoordinateTranslator;
import edu.moravian.math.Point2D;
import edu.moravian.view.SpriteRendererManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
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
    private int mouseX, mouseY;
    private double topLeftCornerWX, topLeftCornerWY;
    private boolean goUpKey, goDownKey, goLeftKey, goRightKey
            , exit, logging, viewObstacle, viewMapGraph, viewPathGraph, viewConsideredEdgeGraph;
    private CoordinateTranslator coordinateTranslator;
    private TiledMap tiledMap;
    private PathFinder pathFinder;
    private SpriteSheet towerSpriteSheet
            , agentSpriteSheet;
    private Animation towerAnimation
            , agentAnimation;
    private Image projectileImage;
    private EntityManager agentManager
            , towerManager
            , projectileManager;
    private SpriteRendererManager agentRendererManager
            , towerRendererManager
            , projectileRendererManager;
    private TowerBuilder towerBuilder;
    private ParameterLoader agentParameterLoader, mapParameterLoader, towerParameterLoader;
    public Play(String title, int screenWidth, int screenHeight) {
        super(title);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void init(GameContainer gc) throws SlickException 
    {
        try {
            this.agentParameterLoader = new AgentParameterLoader();
            this.mapParameterLoader = new MapParameterLoader();
            this.towerParameterLoader = new TowerParameterLoader();
        } catch (IOException ex) {
            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.topLeftCornerWX = 0;
        this.topLeftCornerWY = 0;
        this.tiledMap = new TiledMap(((MapData)mapParameterLoader.getData(0)).getFileSource());
        this.coordinateTranslator = CoordinateTranslator.getInstance(
                tiledMap.getWidth(), tiledMap.getHeight()
                , screenWidth, screenHeight, 0, 0);
        this.agentSpriteSheet = new SpriteSheet("resource/agentSheet.png", 32, 32);
        this.agentAnimation = new Animation(this.agentSpriteSheet, 100);
        
        this.towerSpriteSheet = new SpriteSheet("resource/towerSheet.png", 32, 32);
        this.towerAnimation = new Animation(this.towerSpriteSheet, 100);
        
        this.projectileImage = new Image("resource/projectile.png");
        this.pathFinder = new PathFinder(new NavGraph(tiledMap));
        
        this.agentManager = new AgentManager(new Point2D(10,90)
                , agentParameterLoader, this.pathFinder, new Point2D(10,672));
        this.agentRendererManager = 
                new SpriteRendererManager(this.agentAnimation, this.agentManager);
        
        this.towerManager = new TowerManager();
        this.towerRendererManager = 
                new SpriteRendererManager(this.towerAnimation, this.towerManager);
        
        this.projectileManager = new ProjectileManager();
        this.projectileRendererManager = 
                new SpriteRendererManager(this.projectileImage, this.projectileManager);
        
        this.towerBuilder = new TowerBuilder(this.tiledMap, this.towerParameterLoader
                , this.towerManager, this.agentManager, this.projectileManager);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input input = gc.getInput();
        mouseX = input.getMouseX();
        mouseY = input.getMouseY();
        this.towerBuilder.update(coordinateTranslator.mapToTile(mouseX, mouseY), input.isMousePressed(0));
        this.agentManager.update(delta);
        this.towerManager.update(delta);
        this.projectileManager.update(delta);
        this.agentRendererManager.update();
        this.towerRendererManager.update();
        this.projectileRendererManager.update();
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException 
    {
        tiledMap.render(0, 0);
        this.agentRendererManager.render(grphcs);
        this.towerRendererManager.render(grphcs);
        this.projectileRendererManager.render(grphcs);
        this.towerBuilder.render(grphcs);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
