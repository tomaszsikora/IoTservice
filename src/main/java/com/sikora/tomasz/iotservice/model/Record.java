package com.sikora.tomasz.iotservice.model;

import org.springframework.data.annotation.Id;

/**
 * Created by Tomasz Sikora for personal use.
 */
public class Record
{
    @Id
    private String id;
    private int devId;

    public Record(int devId, int value)
    {
        this.devId = devId;
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    private int value;

    public int getDevId()
    {
        return devId;
    }

    public void setDevId(int devId)
    {
        this.devId = devId;
    }
    


}
