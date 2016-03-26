package com.sikora.tomasz.iotservice.service;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Tomasz Sikora for personal use.
 */
public class MessageValidatorTest
{
    @Test
    public void verifyFormat()
    {
        assertTrue(MessageValidator.isValidMsgFormat("d:123;v:123"));
    }
    @Test
    public void extractDevId()
    {
        assertThat(MessageValidator.extractDevId("d:123;v:f"), is(123));
    }
    @Test
    public void extractValue()
    {
        assertThat(MessageValidator.extractValue("d:123;v:-4321"), is(-4321));

    }
}