package com.sikora.tomasz.iotservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Tomasz Sikora for personal use.
 */
@Document
public class Device
{
    @Id
    private int devId;

    public int getDevId()
    {
        return devId;
    }

    public String getDeviceName()
    {
        return deviceName;
    }

    public DeviceType getDeviceType()
    {
        return deviceType;
    }

    private String deviceName;
    private DeviceType deviceType;

    public Device(int devId, String deviceName, DeviceType deviceType)
    {
        this.devId = devId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
    }
    public Device(int devId, String deviceName)
    {
        this.devId = devId;
        this.deviceName = deviceName;
        this.deviceType = DeviceType.UNKNOWN;
    }
    public Device()
    {
        this.devId = 0;
        this.deviceName = "unknown";
        this.deviceType = DeviceType.UNKNOWN;
    }

    @Override
    public String toString()
    {
        return deviceName + " : " + deviceType.name();
    }
}
