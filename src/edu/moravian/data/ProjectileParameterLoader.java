/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data;

import edu.moravian.data.object.ProjectileData;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ini4j.Profile;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author danielhuynh
 */
public class ProjectileParameterLoader extends ParameterLoader {

    public ProjectileParameterLoader(String fileName) throws IOException {
        super(fileName);
        this.loadData();
    }

    @Override
    protected void loadData() {
        for (Object sectionName : this.ini.keySet()) {
            Profile.Section section = (Profile.Section) this.ini.get(sectionName);
            String projectileName = null;
            double speed = 0;
            int damage = 0, projectileID = 0;
            Image image = null;
            for (Object key : section.keySet()) {
                projectileName = (String) sectionName;
                speed = (double) section.get("speed", double.class);
                damage = (int) section.get("damage", int.class);
                projectileID = (int) section.get("projectileID", int.class);
                String imageSource = (String) section.get("imageSource");
                image = null;
                try {
                    image = new Image(imageSource);
                } catch (SlickException ex) {
                    Logger.getLogger(ProjectileParameterLoader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.dataList.add(new ProjectileData(projectileName, speed, damage, projectileID, image));
        }
    }
}
