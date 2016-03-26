package com.sikora.tomasz.iotservice.e2e

import com.sikora.tomasz.iotservice.IoTServiceApplication
import geb.spock.GebReportingSpec
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration

/**
 * Created by Tomasz Sikora for personal use.
 */
@DirtiesContext
@WebAppConfiguration
@IntegrationTest("server.port:8080")
@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = [IoTServiceApplication.class] )
class MainScenario extends GebReportingSpec {
    def baseUrl = "localhost:8080"
    def "User adds new device"()
    {
        when:
        to MainPage
        and:
        at MainPage
        then:
        1==1


    }
    //def "Device is sending records"()
    //def "User wants to read recent readings and charts"()
}
