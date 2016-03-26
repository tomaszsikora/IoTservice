package com.sikora.tomasz.iotservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * Created by Tomasz Sikora for personal use.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class IoTServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(IoTServiceApplication.class,args);
    }
}
