package com.billding.tttt;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class KafkaClusterMockedTest {
    // TODO Use as constructor argument
    private static final int kafkaClusterOperationRunTime = 20;

    @DataProvider(name = "kafkaClusters")
    public static Object[][] testData() {
        Network network = mock(Network.class);
        when(network.httpOperation(200)).thenReturn(0);

        TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            TestEnvironmentParameters::getNumberOfMapperTests,
            (idx) -> new KafkaCluster(network)
        );

    }

    @Test(dataProvider = "kafkaClusters")
    public void test_specific(String developer, KafkaCluster kafkaCluster) {
        assertEquals(kafkaClusterOperationRunTime, kafkaCluster.clusterAction());
        assertEquals(kafkaClusterOperationRunTime, kafkaCluster.getOperationRunTime());
    }
}
