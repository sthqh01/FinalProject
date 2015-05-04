/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data.object;

import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;

/**
 *
 * @author Daniel Huynh
 */
public class SoundData extends Data {

    public Music backgroundMusic;
    public Sound agentDeathSound, agentArrivalSound;

    public SoundData(String dataName, Music backgroundMusic, Sound agentDeathSound, Sound agentArrivalSound) {
        super(dataName);
        this.backgroundMusic = backgroundMusic;
        this.agentDeathSound = agentDeathSound;
        this.agentArrivalSound = agentArrivalSound;
    }

    public Music getBackgroundMusic() {
        return this.backgroundMusic;
    }
    
    public Sound getAgentDeathSound()
    {
        return this.agentDeathSound;
    }
    
    public Sound getAgentArrivalSound()
    {
        return this.agentArrivalSound;
    }
}
