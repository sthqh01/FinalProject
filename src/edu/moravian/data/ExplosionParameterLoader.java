/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data;

import edu.moravian.data.object.ExplosionData;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ini4j.Profile;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author danielhuynh
 */
public class ExplosionParameterLoader extends ParameterLoader {

    public ExplosionParameterLoader(String fileName) throws IOException {
        super(fileName);
        this.loadData();
    }

    @Override
    protected void loadData() {
        for (Object sectionName : this.ini.keySet()) {
            Profile.Section section = (Profile.Section) this.ini.get(sectionName);
            String explosionName = null;
            int explosionTime = 0;
            Animation animation = null;
            for (Object key : section.keySet()) {
                explosionName = (String) sectionName;
                explosionTime = (int) section.get("explosionTime", int.class);
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
            this.dataList.add(new ExplosionData(explosionName, explosionTime, animation));
        }
    }
}
