package edu.moravian.main;

import it.twl.util.BasicTWLGameState;
import it.twl.util.RootPane;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import edu.moravian.data.AgentParameterLoader;
import edu.moravian.data.ExplosionParameterLoader;
import edu.moravian.data.MapParameterLoader;
import edu.moravian.data.ParameterLoader;
import edu.moravian.data.ProjectileParameterLoader;
import edu.moravian.data.SoundParameterLoader;
import edu.moravian.data.TowerParameterLoader;
import edu.moravian.data.WaveParameterLoader;
import edu.moravian.data.object.MapData;
import edu.moravian.entity.pathFinding.NavGraph;
import edu.moravian.entity.pathFinding.PathFinder;
import edu.moravian.entity.manager.AgentManager;
import edu.moravian.entity.manager.EntityManager;
import edu.moravian.entity.manager.ExplosionManager;
import edu.moravian.entity.manager.ProjectileManager;
import edu.moravian.entity.manager.TowerManager;
import edu.moravian.entity.spacePartitioning.CellSpacePartition;
import edu.moravian.helper.EntitySelector;
import edu.moravian.helper.ResourceManager;
import edu.moravian.sound.SoundManager;
import edu.moravian.helper.TowerBuilder;
import edu.moravian.math.CoordinateTranslator;
import edu.moravian.math.Point2D;
import edu.moravian.view.SpriteRendererManager;
import edu.morvian.gui.RootPanelHelper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Input;
import org.newdawn.slick.tiled.TiledMap;

public class GameState extends BasicTWLGameState {

    private final int screenWidth, screenHeight;
    private int mouseX, mouseY;    
    private boolean exit, displayCell, isWon, isLost;
    private CoordinateTranslator coordinateTranslator;
    private TiledMap tiledMap;
    private PathFinder pathFinder;
    private EntityManager agentManager, towerManager
            , projectileManager, explosionManager;
    private SpriteRendererManager agentRendererManager, towerRendererManager
            , projectileRendererManager, explosionRendererManager;
    private TowerBuilder towerBuilder;
    private ParameterLoader agentParameterLoader, mapParameterLoader
            , towerParameterLoader, waveParameterLoader, projectileParameterLoader
            , explosionParameterLoader, soundParameterLoader;
    private ResourceManager resourceManager;
    private SoundManager soundManager;
    private RootPanelHelper rootPanelHelper;
    private EntitySelector entitySelector;
    private CellSpacePartition cellSpacePartition;

    @Override
    protected RootPane createRootPane() {
        RootPane rp = super.createRootPane();
        rp.setTheme("");
        rootPanelHelper.createSelectedEntityInfoPanel(rp);
        rootPanelHelper.createTowerBuilderPanel(rp);
        rootPanelHelper.createStatusBar(rp);
        return rp;
    }
    
    @Override
    protected void layoutRootPane() {
        rootPanelHelper.getSelectedEntityInfoPanel().setPosition(1024, 0);
        rootPanelHelper.getSelectedEntityInfoPanel().setSize(176, 200);
        rootPanelHelper.getStatusBar().setPosition(0, 0);
        rootPanelHelper.getStatusBar().setSize(1024, 32);
        
        rootPanelHelper.getTowerBuilderPanel().setPosition(1024, 201);
        rootPanelHelper.getTowerBuilderPanel().setSize(176, 502);
    }

    public GameState(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void init(GameContainer arg0, StateBasedGame arg1)
            throws SlickException {
        try {
            this.agentParameterLoader = new AgentParameterLoader("resource/data/agentData.ini");
            this.mapParameterLoader = new MapParameterLoader("resource/data/mapData.ini");
            this.towerParameterLoader = new TowerParameterLoader("resource/data/towerData.ini");
            this.waveParameterLoader = new WaveParameterLoader("resource/data/waveData.ini");
            this.projectileParameterLoader = new ProjectileParameterLoader("resource/data/projectileData.ini");
            this.explosionParameterLoader = new ExplosionParameterLoader("resource/data/explosionData.ini");
            this.soundParameterLoader = new SoundParameterLoader("resource/data/soundData.ini");
        } catch (IOException ex) {
            Logger.getLogger(GameState.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.resourceManager = ResourceManager.getResourceManager();
        this.soundManager = SoundManager.getSoundManager(soundParameterLoader);
        this.tiledMap 
                = new TiledMap(((MapData) mapParameterLoader.getData(0)).getFileSource());
        
        this.coordinateTranslator = CoordinateTranslator.getInstance(
                tiledMap.getWidth(), tiledMap.getHeight(), screenWidth, screenHeight, 0, 0);

        this.pathFinder = new PathFinder(new NavGraph(tiledMap));

        this.agentManager = new AgentManager(this.agentParameterLoader
                , this.waveParameterLoader,
                this.pathFinder, new Point2D(10, 90), new Point2D(10, 672));
        this.agentRendererManager
                = new SpriteRendererManager(this.agentManager);

        
        this.cellSpacePartition = new CellSpacePartition(this.tiledMap.getWidth()*32
                , this.tiledMap.getHeight()*32, 8, 8, this.agentManager);
        
        this.towerManager = new TowerManager();
        this.towerRendererManager
                = new SpriteRendererManager(this.towerManager);

        this.explosionManager = new ExplosionManager();
        this.explosionRendererManager
                = new SpriteRendererManager(this.explosionManager);

        this.projectileManager 
                = new ProjectileManager(this.explosionManager
                        , this.explosionParameterLoader);
        this.projectileRendererManager
                = new SpriteRendererManager(this.projectileManager);

        this.towerBuilder = new TowerBuilder(this.tiledMap
                , this.towerParameterLoader, this.projectileParameterLoader
                , this.towerManager, this.cellSpacePartition, this.projectileManager);
        
        this.entitySelector = new EntitySelector(this.towerManager);
        this.rootPanelHelper = new RootPanelHelper(this.agentManager
                , this.resourceManager, this.entitySelector);
    }

    @Override
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics grphcs)
            throws SlickException {
        this.tiledMap.render(0, 0);
        this.agentRendererManager.render(grphcs);
        this.towerRendererManager.render(grphcs);
        this.projectileRendererManager.render(grphcs);
        this.explosionRendererManager.render(grphcs);
        if(this.rootPanelHelper.getBuildMode())
            this.towerBuilder.render(grphcs);
        this.entitySelector.render(grphcs);
        if(displayCell)
            this.cellSpacePartition.render(grphcs);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame arg1, int delta)
            throws SlickException {
        Input input = gc.getInput();
        if(input.isKeyPressed(Input.KEY_ESCAPE)) {
            arg1.enterState(0);
        }
        if (input.isKeyPressed(Input.KEY_C)) {
            displayCell = displayCell == false;
        }
        if (input.isKeyPressed(Input.KEY_P)) {
            if(gc.isPaused())
                gc.resume();
            else
                gc.pause();
        }
        if(((AgentManager)agentManager).isDone())
        {
            this.isLost = false;
            this.isWon = true;
            this.reset();
            arg1.enterState(0);
        }
        if(this.resourceManager.getPlayerLives()<0) {
            this.isLost = true;
            this.isWon = false;
            this.reset();
            arg1.enterState(0);
        }
        mouseX = input.getMouseX();
        mouseY = input.getMouseY();
        Point2D mouseTileLocation = coordinateTranslator.mapToTile(mouseX, mouseY);
        if (mouseTileLocation.getX() < coordinateTranslator.getWorldWidth() 
                && rootPanelHelper.getBuildMode() == true) {
            this.towerBuilder.update(mouseTileLocation, input.isMousePressed(0)
                    , rootPanelHelper.getTowerType());
        }
        if(input.isMousePressed(1))
            this.rootPanelHelper.setBuildMode(false);
        this.agentManager.update(delta);
        this.cellSpacePartition.update();
        this.towerManager.update(delta);
        this.explosionManager.update(delta);
        this.projectileManager.update(delta);
        this.agentRendererManager.update();
        this.towerRendererManager.update();
        this.projectileRendererManager.update();
        this.explosionRendererManager.update();
        this.rootPanelHelper.update();
        this.entitySelector.update(mouseTileLocation, input.isMousePressed(0));
    }

    @Override
    public int getID() {
        return 1;
    }
    
    @Override
    public void keyPressed(int key, char c)
    {
        if(c == 'q' || c == 'Q')
            exit = true;
    }
    
    @Override
    public void keyReleased(int key, char c)
    {            
    }
    
    public void reset()
    {
        this.entitySelector.reset();
        this.resourceManager.reset();
        this.agentManager.clear();
        ((AgentManager)this.agentManager).reset();
        this.towerManager.clear();
        this.projectileManager.clear();
        this.explosionManager.clear();
    }
    
    public boolean getIsWon()
    {
        return this.isWon;
    }
    
    public boolean getIsLost()
    {
        return this.isLost;
    }
}