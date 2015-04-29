/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data;

import edu.moravian.data.object.MapData;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;

/**
 *
 * @author danielhuynh
 */
public class MapParameterLoader extends ParameterLoader
{
    public MapParameterLoader() throws IOException
    {
        this.ini = new Ini(new FileReader("resource/data/mapData.ini"));
        this.loadData();
    }
    
    private void loadData()
    {
        String mapName, fileSource;
        for(Object sectionName : this.ini.keySet())
        {
            Section section = (Section)this.ini.get(sectionName);
            for (Object key : section.keySet()) {
                mapName = (String)sectionName;
                fileSource = (String)section.get("fileSource");
                this.dataList.add(new MapData(mapName, fileSource));
            }
        }
    }
}
