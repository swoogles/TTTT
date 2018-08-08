package com.billding.boundary_tests;

import com.billding.meta.*;
import com.billding.tttt.Producer;
import com.billding.tttt.external_services.KafkaCluster;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProducerMockedTest {
    private static final Duration runTime = Duration.ofMillis(1);
    private static final Duration mockedRunTime = Duration.ofMillis(0);


    @DataProvider(name = "producers")
    public static Object[][] producers() {
        // TODO USE THIS instead of a mocked world.
//        final World world = DemoScenarios.getPlatonicWorld();
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");

        final World world = mock(ChaoticWorld.class);
        when(world.currentTime()).thenReturn(Instant.parse("1970-01-01T00:00:00Z"));
        // TODO use real cluster
        final KafkaCluster kafkaCluster = mock(KafkaCluster.class);
        when(kafkaCluster.fallibleAction()).thenReturn(mockedRunTime);
        when(kafkaCluster.getRunTime()).thenReturn(mockedRunTime);

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            CodeBase::getNumberOfProducerTests,
        (idx) -> new Producer(
                new KafkaCluster(
                        new Network(componentRunTimes.getNetwork()),
                        componentRunTimes.getKafkaCluster()
                ),
                world,
                componentRunTimes.getProducer()
        ));

    }

    @Test(dataProvider = "producers")
    public void test_specific(String developer, Producer producer) {
        assertTrue(producer.fallibleAction().compareTo(runTime) > 0);
    }
}
