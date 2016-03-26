package com.sikora.tomasz.iotservice.daomanager;

import com.sikora.tomasz.iotservice.model.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tomasz Sikora for personal use.
 */
public interface DeviceRepository extends MongoRepository<Device, Integer>
{

    public Device findByDevId(int devId);

}
