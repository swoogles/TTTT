package com.billding.tttt;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

public class ProducerTest {

    @DataProvider(name = "producers")
    public static Object[][] testData() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes();
        final Network network = new Network(componentRunTimes.getNetwork());
        final ChaoticWorld chaoticWorld = new ChaoticWorld();
        final KafkaCluster kafkaCluster = new KafkaCluster(network);

        final TestEnvironmentParameters testEnvironmentParameters = new TestEnvironmentParameters();

        final Object[][] applications =
            IntStream.range(0, testEnvironmentParameters.getNumberOfMapperTests())
                .mapToObj(idx -> new Object[]{
                    testEnvironmentParameters.getRandomDeveloper(),
                    new Producer(
                        kafkaCluster,
                        chaoticWorld,
                        componentRunTimes.getProducer()
                    )
                }).toArray(size -> new Object[size][1]);
        return applications;
    }

    @Test(dataProvider = "producers")
    public void test_specific(String developer, Producer producer) {
        final int testCaseRunTime = producer.submitEvent();
    }
}
