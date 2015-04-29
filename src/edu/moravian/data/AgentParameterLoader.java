/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data;

import edu.moravian.data.object.AgentData;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;

/**
 *
 * @author danielhuynh
 */
public class AgentParameterLoader extends ParameterLoader
{
    public AgentParameterLoader() throws IOException {
        this.ini = new Ini(new FileReader("resource/data/agentData.ini"));
        this.loadData();
    }
    
    private void loadData()
    {
        String agentName;
        int maxHitPoints;
        double speed;
        for(Object sectionName : this.ini.keySet())
        {
            Section section = (Section)this.ini.get(sectionName);
            for (Object key : section.keySet()) {
                agentName = (String)sectionName;
                maxHitPoints = (int)section.get("maxHitPoints", int.class);
                speed = (double)section.get("speed", double.class);
                this.dataList.add(new AgentData(agentName, maxHitPoints, speed));
            }
        }
    }
}
