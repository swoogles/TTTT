package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.CodeBase;
import com.billding.meta.TestInstanceCreator;
import com.billding.tttt.external_services.KafkaCluster;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class ProducerMockedTest {
    private static final Duration runTime = Duration.ofMillis(1);
    private static final Duration mockedRunTime = Duration.ofMillis(0);


    @DataProvider(name = "mappers")
    public static Object[][] testData() {
        final ChaoticWorld chaoticWorld = mock(ChaoticWorld.class);
        when(chaoticWorld.currentTime()).thenReturn(Instant.parse("1970-01-01T00:00:00Z"));
        final KafkaCluster kafkaCluster = mock(KafkaCluster.class);
        when(kafkaCluster.failableAction()).thenReturn(mockedRunTime);
        when(kafkaCluster.getRunTime()).thenReturn(mockedRunTime);

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            CodeBase::getNumberOfProducerTests,
            (idx) -> new Producer(
                        kafkaCluster,
                        chaoticWorld,
                    runTime
                    )
        );

    }

    @Test(dataProvider = "mappers")
    public void test_specific(String developer, Producer producer) {
        assertEquals(runTime, producer.failableAction());
    }
}
