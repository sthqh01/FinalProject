/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data;

import edu.moravian.data.object.Data;
import java.util.ArrayList;
import org.ini4j.Ini;

/**
 *
 * @author danielhuynh
 */
public abstract class ParameterLoader 
{
    protected Ini ini;
    protected ArrayList<Data> dataList;
    public ParameterLoader()
    {
        this.dataList = new ArrayList<>();
    }
    public Data getData(int i)
    {
        return this.dataList.get(i);
    }
}
