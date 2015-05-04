/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data;

import edu.moravian.data.object.AgentData;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ini4j.Profile.Section;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author danielhuynh
 */
public final class AgentParameterLoader extends ParameterLoader {

    public AgentParameterLoader(String fileName) throws IOException {
        super(fileName);
        this.loadData();
    }

    @Override
    protected void loadData() {
        for (Object sectionName : this.ini.keySet()) {
            Section section = (Section) this.ini.get(sectionName);
            String agentName = null;
            int maxHitPoints = 0;
            double speed = 0;
            Animation animation = null;
            for (Object key : section.keySet()) {
                agentName = (String) sectionName;
                maxHitPoints = (int) section.get("maxHitPoints", int.class);
                speed = (double) section.get("speed", double.class);
                String spriteSheetSource = (String) section.get("spriteSheetSource");
                SpriteSheet spriteSheet;
                animation = null;
                try {
                    spriteSheet = new SpriteSheet(spriteSheetSource, 32, 32);
                    animation = new Animation(spriteSheet, 100);
                } catch (SlickException ex) {
                    Logger.getLogger(AgentParameterLoader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.dataList.add(new AgentData(agentName, maxHitPoints, speed, animation));
        }
    }
}
