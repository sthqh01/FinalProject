/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data;

import edu.moravian.data.object.WaveData;
import java.io.IOException;
import org.ini4j.Profile.Section;

/**
 *
 * @author danielhuynh
 */
public final class WaveParameterLoader extends ParameterLoader {

    public WaveParameterLoader(String fileName) throws IOException {
        super(fileName);
        this.loadData();
    }

    @Override
    protected void loadData() {
        for (Object sectionName : this.ini.keySet()) {
            Section section = (Section) this.ini.get(sectionName);
            String waveName = null;
            int spawnTime = 0;
            int numAgentLimit = 0;

            for (Object key : section.keySet()) {
                waveName = (String) sectionName;
                spawnTime = (int) section.get("spawnTime", int.class);
                numAgentLimit = (int) section.get("numAgentLimit", int.class);
            }
            this.dataList.add(new WaveData(waveName, spawnTime, numAgentLimit));

        }
    }
}
