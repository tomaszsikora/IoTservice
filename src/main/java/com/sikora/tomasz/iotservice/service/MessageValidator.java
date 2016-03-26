package com.sikora.tomasz.iotservice.service;

import com.sikora.tomasz.iotservice.model.Record;
import org.apache.camel.CamelExchangeException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * Created by Tomasz Sikora for personal use.
 */
@Service
public class MessageValidator implements Processor
{
    public void process(Exchange exchange) throws Exception
    {
        String msg = exchange.getIn().getBody(String.class);
        msg = msg.replaceAll(" ","");

        if(isValidMsgFormat(msg))
        {
           try
           {
               int devId = extractDevId(msg);
               int value = extractValue(msg);
               exchange.getIn().setBody(new Record(devId,value));
           }
           catch (NumberFormatException exc)
           {
               throwInvalidMessage(exchange);
           }
        }
        else
        {
            throwInvalidMessage(exchange);
        }

    }

    static boolean isValidMsgFormat(String msg)
    {
        Pattern validMessageFormat = Pattern.compile("d:\\d+;v:[^;:]+");
        return validMessageFormat.matcher(msg).matches();
    }

    static int extractDevId(final String msg) throws NumberFormatException
    {
        String dev = msg.substring(2,msg.indexOf(";"));
        return Integer.parseInt(dev);
    }
    static int extractValue(final String msg) throws NumberFormatException
    {
        String value = msg.substring(msg.indexOf("v:")+2);
        return Integer.parseInt(value);
    }

    private void throwInvalidMessage(Exchange exchange) throws CamelExchangeException
    {
        throw new CamelExchangeException("Invalid Message",exchange);
    }
}
