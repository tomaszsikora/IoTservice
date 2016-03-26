import com.sikora.tomasz.iotservice.IoTServiceApplication
import com.sikora.tomasz.iotservice.daomanager.DeviceRepository
import com.sikora.tomasz.iotservice.daomanager.RecordsRepository
import com.sikora.tomasz.iotservice.service.MainService
import com.sikora.tomasz.iotservice.service.MessageRoute
import org.apache.camel.CamelContext
import org.apache.camel.Produce
import org.apache.camel.ProducerTemplate
import org.apache.camel.builder.NotifyBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.TimeUnit

import static org.junit.Assert.assertTrue

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = [IoTServiceApplication.class] )
class MainServiceIntegrationTest extends Specification
{

    @Autowired
    CamelContext camelContext;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    RecordsRepository recordsRepository;

    @Autowired
    MainService service;
    @Autowired
    MessageRoute messageRoute;
    @Produce(uri = "activemq:readings")
    protected ProducerTemplate template;

    @DirtiesContext
    @Unroll
    def "Registered device sends msg: #message to service"()
    {

        deviceIsSendingMessage(message)
        expect:
        recordSaved() == validAndProcessed

        where:
        message                     |   validAndProcessed
        "d:123;v:123"               |   true
        "d:123;v:-123"              |   true
        "d:123;v:0"                 |   true
        "d:123;v: 123 "             |   true
        "d : 1 2 3 ; v : 3 0 "      |   true
        "d:abc;v:123"               |   false
        "d:123;v:abc"               |   false
        "d:123;v:123;v:123"         |   false
        "v:123;d:123"               |   false
        "y:123;v:123"               |   false
        "d:123;v:${Long.MAX_VALUE}" |   false
        "d:123;v:-12.5"             |   false

    }

    @DirtiesContext
    def "Not registered device sends message to service"()
    {
        when:
        deviceIsSendingMessage("d:321;v:123" )
        then:
        recordSaved() == true
        deviceRepository.count() == 1
    }

    @DirtiesContext
    def "Device sends 5 messages with average 5"()
    {
        when:
        deviceIsSendingMessage("d:123;v:5" )
        deviceIsSendingMessage("d:123;v:4" )
        deviceIsSendingMessage("d:123;v:6" )
        deviceIsSendingMessage("d:123;v:4" )
        deviceIsSendingMessage("d:123;v:6" )
        then:
        service.calculateAvarage(123) == 5
    }
    @DirtiesContext
    def "Device sends 20 messages, last 10 average should be 5"()
    {
        when:
        10.times {deviceIsSendingMessage("d:123;v:123")}
        10.times {deviceIsSendingMessage("d:123;v:5" )}
        then:
        recordsRepository.count() == 20
        service.calculateAvarage(123) == 5
    }

    @DirtiesContext
    def "Calculate average on empty record repository"()
    {
        expect:
        recordsRepository.count() == 0
        service.calculateAvarage(123) == 0
    }




    def deviceIsSendingMessage(String msg)
    {
        NotifyBuilder notify = new NotifyBuilder(camelContext).whenDone(1).create();
        template.sendBody(msg)
        boolean done = notify.matches(10, TimeUnit.SECONDS);
        assertTrue("Should be processed in 10s", done);
    }
    def recordSaved()
    {
        return recordsRepository.count() == 1
    }


}