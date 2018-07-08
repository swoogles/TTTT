package com.billding.nonpresentation;

import com.billding.tttt.external_services.KafkaCluster;
import com.billding.tttt.external_services.Network;
import com.billding.meta.TestInstanceCreator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class KafkaClusterMockedTest {
    private static final Duration runTime = Duration.ofMillis(20);
    private static final Duration mockedRunTime = Duration.ofMillis(0);

    @DataProvider(name = "kafkaClusters")
    public static Object[][] testData() {
        Network network = mock(Network.class);
        when(network.httpOperation(200)).thenReturn(mockedRunTime);
        when(network.getRunTime()).thenReturn(mockedRunTime);

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new KafkaCluster(network, runTime)
        );

    }

    @Test(dataProvider = "kafkaClusters")
    public void test_specific(String developer, KafkaCluster kafkaCluster) {
        assertEquals(runTime, kafkaCluster.clusterAction());
    }
}
