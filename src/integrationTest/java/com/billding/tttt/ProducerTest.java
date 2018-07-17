package com.billding.tttt;

import com.billding.meta.ChaoticWorld;
import com.billding.meta.ComponentRunTimes;
import com.billding.meta.TestInstanceCreator;
import com.billding.tttt.external_services.KafkaCluster;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class ProducerTest {

    @DataProvider(name = "producers")
    public static Object[][] testData() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new Producer(
                        new KafkaCluster(
                            new Network(componentRunTimes.getNetwork()),
                            componentRunTimes.getKafkaCluster()
                        ),
                        new ChaoticWorld(),
                        componentRunTimes.getProducer()
                    ));
    }

    @Test(dataProvider = "producers")
    public void test_specific(String developer, Producer producer) {
        // producer + cluster
        assertEquals(
            producer.failableAction(),
            Duration.ofMillis(35)
        );
    }
}
