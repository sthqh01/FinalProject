/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.sound;

import edu.moravian.data.ParameterLoader;
import edu.moravian.data.object.SoundData;

/**
 *
 * @author Daniel Huynh
 */
public class SoundManager {
    private static SoundManager instance;
    private final SoundData soundData;

    private SoundManager(ParameterLoader soundParameterLoader) {
        soundData = (SoundData) soundParameterLoader.getData(0);
        soundData.getBackgroundMusic().loop();
    }

    public static SoundManager getSoundManager(ParameterLoader soundParameterLoader) {
        if (instance == null) {
            instance = new SoundManager(soundParameterLoader);
        }
        return instance;
    }
    
    public static SoundManager getSoundManager()
    {
        return instance;
    }
    
    public void playAgentDeathSound()
    {
        soundData.getAgentDeathSound().play();
    }
    
    public void playAgentArrivalSound()
    {
        soundData.getAgentArrivalSound().play();
    }
}
