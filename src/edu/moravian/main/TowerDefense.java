/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author danielhuynh
 */
public class TowerDefense {

    /**
     * @param args the command line arguments
     * @throws org.newdawn.slick.SlickException
     */
    public static void main(String[] args) throws SlickException 
    {
        Play play = new Play("Game", 1024, 704);
        AppGameContainer app = new AppGameContainer(play
                , play.getScreenWidth(), play.getScreenHeight(), false);
        app.setAlwaysRender(true);
        app.start();
    }
}
