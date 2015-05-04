/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data;

import edu.moravian.data.object.TowerData;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ini4j.Profile.Section;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author danielhuynh
 */
public final class TowerParameterLoader extends ParameterLoader
{
    public TowerParameterLoader(String fileName) throws IOException
    {
        super(fileName);
        this.loadData();
    }
    
    @Override
    protected void loadData()
    {
        for(Object sectionName : this.ini.keySet())
        {
            Section section = (Section)this.ini.get(sectionName);
            String towerName = null;
            String imageSource = null;
            Image image = null;
            int cost = 0, sightRadius = 0, fireDelay = 0, damage = 0, maxLevel = 0;
            double upgradeChangePercentage = 0;
            for (Object key : section.keySet()) {
                towerName = (String)sectionName;
                cost = (int)section.get("cost", int.class);
                sightRadius = (int)section.get("sightRadius", int.class);
                fireDelay = (int)section.get("fireDelay", int.class);
                damage = (int)section.get("damage", int.class);
                imageSource = (String)section.get("imageSource");
                upgradeChangePercentage = (double)section.get("upgradeChangePercentage", double.class);
                maxLevel = (int)section.get("maxLevel", int.class);
                image = null;
                try {
                    image = new Image(imageSource);
                } catch (SlickException ex) {
                    Logger.getLogger(ProjectileParameterLoader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.dataList.add(new TowerData(towerName, cost, sightRadius
                    , fireDelay, damage, upgradeChangePercentage, maxLevel, image));
        }
    }
}
