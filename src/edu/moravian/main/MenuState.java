/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.main;

import edu.morvian.gui.RootPanelHelper;
import it.twl.util.BasicTWLGameState;
import it.twl.util.RootPane;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Daniel Huynh
 */
public class MenuState extends BasicTWLGameState
{
    private Image backgroundImage;
    private RootPanelHelper rootPanelHelper;
    
    @Override
    protected RootPane createRootPane() {
        RootPane rp = super.createRootPane();
        rp.setTheme("");
        rootPanelHelper.createMenuPanel(rp);
        rootPanelHelper.createAnnoucementPanel(rp);
        return rp;
    }
    
    @Override
    protected void layoutRootPane() {
        rootPanelHelper.getMenuPanel().setPosition(1024, 0);
        rootPanelHelper.getMenuPanel().setSize(176, 703);
        
        rootPanelHelper.getAnnouncementPanel().setPosition(200,200);
        rootPanelHelper.getAnnouncementPanel().setSize(400, 200);        
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.backgroundImage = new Image("resource/background.jpg");
        this.rootPanelHelper = new RootPanelHelper(sbg, gc);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        this.backgroundImage.draw();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if (((GameState)sbg.getState(1)).getIsLost())
            this.rootPanelHelper.setAnnouncement("You lost!");
        if (((GameState)sbg.getState(1)).getIsWon())
            this.rootPanelHelper.setAnnouncement("You won!");
    }
}
