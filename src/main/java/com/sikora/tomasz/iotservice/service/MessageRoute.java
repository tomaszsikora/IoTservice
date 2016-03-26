package com.sikora.tomasz.iotservice.service;

import org.apache.camel.CamelExchangeException;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Tomasz Sikora for personal use.
 */
@Component
public class MessageRoute extends RouteBuilder
{
    @Autowired
    MessageValidator messageValidator;

    @Autowired
    MainService service;

    @Override
    public void configure() throws Exception
    {
        onException(CamelExchangeException.class)
                .handled(true)
                .log(LoggingLevel.WARN, "ExchangeException", exceptionMessage().toString());

        from("activemq:readings")
                .process(messageValidator)
                .bean(service, "addDeviceIfNotExist")
                .bean(service, "persistRecord");
    }

}
