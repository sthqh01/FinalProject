/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.data.object;

/**
 *
 * @author danielhuynh
 */
public abstract class Data 
{
    protected final String dataName;
    public Data(String dataName)
    {
        this.dataName = dataName;
    }
    public String getDataName()
    {
        return this.dataName;
    }
}
