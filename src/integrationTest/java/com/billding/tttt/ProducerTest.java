package com.billding.tttt;

import com.billding.meta.ComponentRunTimes;
import com.billding.meta.TestInstanceCreator;
import com.billding.tttt.external_services.KafkaCluster;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProducerTest {

    @DataProvider(name = "producers")
    public static Object[][] testData() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes();

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new Producer(
                        new KafkaCluster(
                            new Network(componentRunTimes.getNetwork())
                        ),
                        new ChaoticWorld(),
                        componentRunTimes.getProducer()
                    ));
    }

    @Test(dataProvider = "producers")
    public void test_specific(String developer, Producer producer) {
        final int testCaseRunTime = producer.submitEvent();
    }
}
