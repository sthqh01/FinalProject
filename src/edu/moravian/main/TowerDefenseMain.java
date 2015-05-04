package edu.moravian.main;

import it.twl.util.TWLStateBasedGame;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class TowerDefenseMain extends TWLStateBasedGame {
	protected TowerDefenseMain(String name) {
		super(name);
	}

	@Override
	protected URL getThemeURL() {
            try {
                return new java.io.File("resource/it/twl/examples/simple.xml").toURI().toURL();
            } catch (MalformedURLException ex) {
                Logger.getLogger(TowerDefenseMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new GameState(1200, 703));
                addState(new MenuState());
                enterState(0);
	}

	public static void main(String[] args) throws SlickException {
		TowerDefenseMain game = new TowerDefenseMain(
				"Tower Defense");
		AppGameContainer container = new AppGameContainer(game);
		container.setDisplayMode(1200, 703, false);
		container.setShowFPS(true);
                container.setTargetFrameRate(120);
//		container.setAlwaysRender(true);
		container.start();
	}
}
