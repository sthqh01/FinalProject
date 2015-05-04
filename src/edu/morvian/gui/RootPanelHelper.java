package edu.morvian.gui;

import de.matthiasmann.twl.BoxLayout;
import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.DialogLayout.Group;
import de.matthiasmann.twl.Label;
import edu.moravian.entity.Tower;
import edu.moravian.entity.manager.AgentManager;
import edu.moravian.entity.manager.EntityManager;
import edu.moravian.helper.EntitySelector;
import edu.moravian.helper.ResourceManager;
import edu.moravian.main.GameState;
import it.twl.util.RootPane;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Daniel Huynh
 */
public class RootPanelHelper {

    private DialogLayout grid;
    private DialogLayout menuPanel;
    private DialogLayout towerBuilderPanel;
    private DialogLayout selectedEntityInfoPanel;
    private DialogLayout statusBar;
    private DialogLayout announcementPanel;
    private BoxLayout box;

    private Label waveLabel, goldLabel, livesLabel, selectedEntityLabel
            , towerLevelLabel, towerSightLabel, towerFireRateLabel
            , towerDamageLabel, towerUpgradeCost
            , announcement;

    private boolean buildMode;
    private int towerType;
    private final EntityManager agentManager;
    private final ResourceManager resourceManager;
    private final EntitySelector entitySelector;
    private StateBasedGame stateBasedGame;
    private GameContainer gameContainer;

    public RootPanelHelper(EntityManager agentManager, ResourceManager resourceManager, EntitySelector entitySelector) {
        this.agentManager = agentManager;
        this.resourceManager = resourceManager;
        this.entitySelector = entitySelector;
    }
    
    public RootPanelHelper(StateBasedGame stateBasedGame, GameContainer gameContainer)
    {
        this.agentManager = null;
        this.resourceManager = null;
        this.entitySelector = null;
        this.stateBasedGame = stateBasedGame;
        this.gameContainer = gameContainer;
    }

    public void createStatusBar(RootPane rp) {
        statusBar = new DialogLayout();
        statusBar.setTheme("statusbar");

        this.waveLabel = new Label();
        this.goldLabel = new Label();
        this.livesLabel = new Label();

        Group hz = statusBar.createParallelGroup(statusBar
                .createSequentialGroup().addMinGap(DialogLayout.DEFAULT_GAP)
                .addWidget(waveLabel));
        Group vz = statusBar.createParallelGroup(statusBar
                .createSequentialGroup().addMinGap(DialogLayout.DEFAULT_GAP)
                .addWidget(goldLabel));
        Group bz = statusBar.createParallelGroup(statusBar
                .createSequentialGroup().addMinGap(DialogLayout.DEFAULT_GAP)
                .addWidget(livesLabel));

        Group horizontal = statusBar.createSequentialGroup(hz, vz, bz);

        Group hz2 = statusBar.createSequentialGroup(waveLabel);
        Group vz2 = statusBar.createSequentialGroup(goldLabel);
        Group bz2 = statusBar.createSequentialGroup(livesLabel);

        Group vertical = statusBar.createParallelGroup(hz2, vz2, bz2);

        statusBar.setHorizontalGroup(horizontal);
        statusBar.setVerticalGroup(vertical);

        rp.add(statusBar);
    }

    public void createSelectedEntityInfoPanel(RootPane rp) {
        selectedEntityInfoPanel = new DialogLayout();
        selectedEntityInfoPanel.setTheme("panel");

        Button towerUpgradeButton = new Button("Upgrade");
        towerUpgradeButton.setTheme("button");
        towerUpgradeButton.addCallback(new Runnable() {
            public void run() {
                if (entitySelector.getSelectedEntity() != null) {
                    Tower selectedTower = (Tower) entitySelector.getSelectedEntity();
                    if (!selectedTower.isAtMaxLevel() && selectedTower.getUpgradeCost()
                            <= resourceManager.getGold()) {
                        selectedTower.upgrade();
                        resourceManager.minusGold(selectedTower.getUpgradeCost());
                    }
                }
            }
        });

        this.towerLevelLabel = new Label();
        this.towerDamageLabel = new Label();
        this.towerSightLabel = new Label();
        this.towerUpgradeCost = new Label();
        this.towerFireRateLabel = new Label();

        selectedEntityInfoPanel.setHorizontalGroup(selectedEntityInfoPanel.createParallelGroup()
                .addGroup(selectedEntityInfoPanel.createSequentialGroup()
                        .addGroup(selectedEntityInfoPanel.createParallelGroup()
                                .addGroup(selectedEntityInfoPanel.createSequentialGroup()
                                        .addGroup(selectedEntityInfoPanel.createParallelGroup()
                                                .addWidget(towerSightLabel)
                                                .addWidget(towerDamageLabel)
                                                .addWidget(towerLevelLabel)
                                                .addWidget(towerFireRateLabel)
                                                .addWidget(towerUpgradeCost)
                                                .addWidget(towerUpgradeButton)))))
        );

        selectedEntityInfoPanel.setVerticalGroup(selectedEntityInfoPanel.createParallelGroup()
                .addGroup(selectedEntityInfoPanel.createSequentialGroup()
                        .addWidget(towerLevelLabel)
                        .addWidget(towerDamageLabel)
                        .addWidget(towerSightLabel)
                        .addWidget(towerFireRateLabel)
                        .addWidget(towerUpgradeCost)
                        .addWidget(towerUpgradeButton)
                )
        );
        rp.add(selectedEntityInfoPanel);
    }

    public void createTowerBuilderPanel(RootPane rp) {
        this.towerBuilderPanel = new DialogLayout();
        this.towerBuilderPanel.setTheme("panel2");

        Label label = new Label();
        label.setText("Tower Selection: ");
        Button tower0BuilderButton = new Button();
        tower0BuilderButton.setTheme("button");
        tower0BuilderButton.addCallback(new Runnable() {
            public void run() {
                buildMode = buildMode == false;
                towerType = 0;
            }
        });

        Button tower1BuilderButton = new Button();
        tower1BuilderButton.setTheme("button1");
        tower1BuilderButton.addCallback(new Runnable() {
            public void run() {
                buildMode = buildMode == false;
                towerType = 1;
            }
        });

        Button tower2BuilderButton = new Button();
        tower2BuilderButton.setTheme("button2");
        tower2BuilderButton.addCallback(new Runnable() {
            public void run() {
                buildMode = buildMode == false;
                towerType = 2;
            }
        });

        towerBuilderPanel.setHorizontalGroup(
                towerBuilderPanel.createParallelGroup()
                .addGroup(towerBuilderPanel.createSequentialGroup()
                        .addGroup(towerBuilderPanel.createParallelGroup()
                                .addWidget(label)
                                .addWidget(tower0BuilderButton)
                                .addWidget(tower1BuilderButton)
                                .addWidget(tower2BuilderButton)))
        );
        towerBuilderPanel.setVerticalGroup(
                towerBuilderPanel.createParallelGroup()
                .addGroup(towerBuilderPanel.createSequentialGroup()
                        .addWidget(label)
                        .addWidget(tower0BuilderButton)
                        .addGap(30, 30, 30)
                        .addWidget(tower1BuilderButton)
                        .addGap(30, 30, 30)
                        .addWidget(tower2BuilderButton))
        );
        rp.add(this.towerBuilderPanel);
    }

    public void createMenuPanel(RootPane rp) {
        this.menuPanel = new DialogLayout();
        this.menuPanel.setTheme("panel");
        
        Label titleLabel = new Label("Welcome to Daniel's");
        Label titleLabel1 = new Label("Tower Defense Game!");
        
        Button continueButton = new Button("Continue");
        continueButton.addCallback(new Runnable() {
            public void run() {
                stateBasedGame.enterState(1);
            }
        });
        
        Button playButton = new Button("New Game");
        playButton.addCallback(new Runnable() {
            public void run() {
                stateBasedGame.enterState(1);
                ((GameState)stateBasedGame.getState(1)).reset();
            }
        });
        
        Button settingButton = new Button("Setting");
        settingButton.addCallback(new Runnable() {
            public void run() {
            }
        }); 
        
        Button creditButton = new Button("Credit");
        creditButton.addCallback(new Runnable() {
            public void run() {
            }
        }); 
        
        Button exitButton = new Button("Exit");
        exitButton.addCallback(new Runnable() {
            public void run() {
                gameContainer.exit();
            }
        }); 
        
        menuPanel.setHorizontalGroup(
                menuPanel.createParallelGroup()
                .addGroup(menuPanel.createSequentialGroup()
                        .addGroup(menuPanel.createParallelGroup()
                                .addWidget(titleLabel)
                                .addWidget(titleLabel1)
                                .addWidget(continueButton)
                                .addWidget(playButton)
                                .addWidget(settingButton)
                                .addWidget(creditButton)
                                .addWidget(exitButton)))
        );
        menuPanel.setVerticalGroup(
                menuPanel.createParallelGroup()
                .addGroup(menuPanel.createSequentialGroup()
                        .addWidget(titleLabel)
                        .addWidget(titleLabel1)
                        .addWidget(continueButton)
                        .addGap(30, 30, 30)
                        .addWidget(playButton)
                        .addGap(30, 30, 30)
                        .addWidget(settingButton)
                        .addGap(30, 30, 30)
                        .addWidget(creditButton)
                        .addGap(30, 30, 30)
                        .addWidget(exitButton))
        );
        rp.add(menuPanel);
    }
    
    public void createAnnoucementPanel(RootPane rp)
    {
        this.announcementPanel = new DialogLayout();
        this.announcementPanel.setTheme("panel");
        
        announcement = new Label();
        
        announcementPanel.setHorizontalGroup(announcementPanel.createParallelGroup()
                .addGroup(announcementPanel.createSequentialGroup()
                        .addGroup(announcementPanel.createParallelGroup()
                                .addWidget(announcement)))
        );
        announcementPanel.setVerticalGroup(announcementPanel.createParallelGroup()
                .addGroup(announcementPanel.createSequentialGroup()
                        .addWidget(announcement))
        );
        rp.add(announcementPanel);
    }

    public void update() {
        int wave = ((AgentManager) this.agentManager).getNumWaveSoFar();
        this.waveLabel.setText("Current wave: " + wave + " / "
                + ((AgentManager) this.agentManager).getNumWaveLimit());

        int gold = this.resourceManager.getGold();
        int lives = this.resourceManager.getPlayerLives();
        this.goldLabel.setText("Gold: " + gold);
        this.livesLabel.setText("Lives: " + lives);
        String towerLevel = "", towerDamage = "", towerSight = "", towerFireDelay = "", towerUpgradeCost = "";
        if (this.entitySelector.getSelectedEntity() != null) {
            Tower selectedTower = (Tower) this.entitySelector.getSelectedEntity();
            towerLevel = Integer.toString(selectedTower.getCurrentLevel()) + "/3";
            towerDamage = Integer.toString(selectedTower.getDamage())+ " (+20%)";
            towerSight = Integer.toString(selectedTower.getSightRadius())+ " (+20%)";
            towerFireDelay = "1 per " + Double.toString((double)selectedTower.getFireDelay()/1000) + "s";
            towerUpgradeCost = Integer.toString(selectedTower.getUpgradeCost());
        }
        this.towerLevelLabel.setText("Level: " + towerLevel);
        this.towerDamageLabel.setText("Damage: " + towerDamage);
        this.towerSightLabel.setText("Sight: " + towerSight);
        this.towerFireRateLabel.setText("Fire rate: " + towerFireDelay);
        this.towerUpgradeCost.setText("Upgrade cost: " + towerUpgradeCost);
    }
    
    public void setBuildMode(boolean mode)
    {
        this.buildMode = false;
    }

    public boolean getBuildMode() {
        return this.buildMode;
    }

    public DialogLayout getGrid() {
        return grid;
    }

    public DialogLayout getMenuPanel() {
        return menuPanel;
    }

    public DialogLayout getSelectedEntityInfoPanel() {
        return selectedEntityInfoPanel;
    }

    public DialogLayout getStatusBar() {
        return statusBar;
    }

    public BoxLayout getBox() {
        return box;
    }

    public DialogLayout getTowerBuilderPanel() {
        return towerBuilderPanel;
    }

    public int getTowerType() {
        return this.towerType;
    }
    
    public DialogLayout getAnnouncementPanel()
    {
        return this.announcementPanel;
    }
    
    public void setAnnouncement(String announcement)
    {
        this.announcement.setText(announcement);
    }
}
