package com.billding.tttt;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProducerTest {

    @DataProvider(name = "producers")
    public static Object[][] testData() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes();
        final Network network = new Network(componentRunTimes.getNetwork());
        final ChaoticWorld chaoticWorld = new ChaoticWorld();
        final KafkaCluster kafkaCluster = new KafkaCluster(network);

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

        return testInstanceCreator.createInstances(
            (ignored) -> 1,
            (idx) -> new Producer(
                        kafkaCluster,
                        chaoticWorld,
                        componentRunTimes.getProducer()
                    ));
    }

    @Test(dataProvider = "producers")
    public void test_specific(String developer, Producer producer) {
        final int testCaseRunTime = producer.submitEvent();
    }
}
