/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data;

import edu.moravian.data.object.SoundData;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ini4j.Profile;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 *
 * @author Daniel Huynh
 */
public class SoundParameterLoader extends ParameterLoader {

    public SoundParameterLoader(String filename) throws IOException {
        super(filename);
        this.loadData();
    }

    @Override
    protected void loadData() {
        for (Object sectionName : this.ini.keySet()) {
            Profile.Section section = (Profile.Section) this.ini.get(sectionName);
            String soundDataName = null;
            Music backgroundMusic = null;
            Sound agentDeathSound = null, agentArrivalSound = null;
            for (Object key : section.keySet()) {
                soundDataName = (String) sectionName;
                String backgroundMusicSource = (String) section.get("backgroundMusicSource");
                String agentDeathSoundSource = (String) section.get("agentDeathSoundSource");
                String agentArrivalSoundSource = (String) section.get("agentArrivalSoundSource");
                backgroundMusic = null;
                agentDeathSound = null;
                agentArrivalSound = null;
                try {
                    backgroundMusic = new Music(backgroundMusicSource);
                    agentDeathSound = new Sound(agentDeathSoundSource);
                    agentArrivalSound = new Sound(agentArrivalSoundSource);
                } catch (SlickException ex) {
                    Logger.getLogger(SoundParameterLoader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.dataList.add(new SoundData(soundDataName, backgroundMusic, agentDeathSound, agentArrivalSound));
        }
    }
}
