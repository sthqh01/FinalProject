/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data;

import edu.moravian.data.object.AgentData;
import edu.moravian.data.object.TowerData;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.ini4j.Ini;
import org.ini4j.Profile;
import org.ini4j.Profile.Section;

/**
 *
 * @author danielhuynh
 */
public class TowerParameterLoader extends ParameterLoader
{
    public TowerParameterLoader() throws IOException
    {
        this.ini = new Ini(new FileReader("resource/data/towerData.ini"));
        this.loadData();
    }
    
    private void loadData()
    {
        String towerName;
        int fireDelay, sightRadius, damage;
        for(Object sectionName : this.ini.keySet())
        {
            Section section = (Section)this.ini.get(sectionName);
            for (Object key : section.keySet()) {
                towerName = (String)sectionName;
                sightRadius = (int)section.get("sightRadius", int.class);
                fireDelay = (int)section.get("fireDelay", int.class);
                damage = (int)section.get("damage", int.class);
                this.dataList.add(new TowerData(towerName, sightRadius, fireDelay, damage));
            }
        }
    }
}
