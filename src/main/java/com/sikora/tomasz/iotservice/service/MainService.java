package com.sikora.tomasz.iotservice.service;

import com.sikora.tomasz.iotservice.daomanager.DeviceRepository;
import com.sikora.tomasz.iotservice.daomanager.RecordsRepository;
import com.sikora.tomasz.iotservice.model.Device;
import com.sikora.tomasz.iotservice.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tomasz Sikora for personal use.
 */
@Service
public class MainService
{
    @Autowired
    RecordsRepository recordsRepository;

    @Autowired
    DeviceRepository deviceRepository;

    public void addDeviceIfNotExist(Record record)
    {
        if(!deviceRepository.exists(record.getDevId()))
        {
            deviceRepository.insert(new Device(record.getDevId(),"unknown"));
        }
    }
    public void persistRecord(Record record)
    {
        recordsRepository.insert(record);
    }

    public double calculateAvarage(int devId)
    {
        return recordsRepository.findTop10ByDevIdOrderByIdDesc(devId)
                .stream().mapToInt(r -> r.getValue()).average().orElse(0);
    }
    public List<Record> getLatestRecordsOfDevice(int devId)
    {
        return recordsRepository.findTop10ByDevIdOrderByIdDesc(devId);
    }
}
