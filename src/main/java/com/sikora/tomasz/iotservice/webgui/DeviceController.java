package com.sikora.tomasz.iotservice.webgui;

import com.sikora.tomasz.iotservice.daomanager.DeviceRepository;
import com.sikora.tomasz.iotservice.model.Device;
import com.sikora.tomasz.iotservice.model.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Tomasz Sikora for personal use.
 */
@RestController
public class DeviceController
{

    @Autowired
    DeviceRepository deviceRepository;

    @RequestMapping("/device")
    public @ResponseBody List<Device> get() {
        return deviceRepository.findAll();
    }

    @RequestMapping("/addDevice")
    public Device add(
            @RequestParam(value="devId", required=true) Integer devId,
            @RequestParam(value="devName", required=true) String devName,
            @RequestParam(value="devType", required=true) String devType) {
        Device device = new Device(devId,devName,DeviceType.valueOf(devType));
        deviceRepository.insert(device);
        return device;
    }
}
