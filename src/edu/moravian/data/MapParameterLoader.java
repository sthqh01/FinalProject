/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data;

import edu.moravian.data.object.MapData;
import java.io.IOException;
import org.ini4j.Profile.Section;

/**
 *
 * @author danielhuynh
 */
public final class MapParameterLoader extends ParameterLoader {

    public MapParameterLoader(String fileName) throws IOException {
        super(fileName);
        this.loadData();
    }

    @Override
    protected void loadData() {
        for (Object sectionName : this.ini.keySet()) {
            Section section = (Section) this.ini.get(sectionName);
            String mapName = null;
            String fileSource = null;
            for (Object key : section.keySet()) {
                mapName = (String) sectionName;
                fileSource = (String) section.get("fileSource");
            }
            this.dataList.add(new MapData(mapName, fileSource));
        }
    }
}
