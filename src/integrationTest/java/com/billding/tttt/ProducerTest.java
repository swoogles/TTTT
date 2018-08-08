package com.billding.tttt;

import com.billding.meta.*;
import com.billding.tttt.external_services.KafkaCluster;
import com.billding.tttt.external_services.Network;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.billding.meta.SlowTestExecution.executeWithRunTime;
import static org.testng.Assert.assertEquals;

public class ProducerTest {
    private static final World world = DemoScenarios.getWorld();

    @DataProvider(name = "producers")
    public static Object[][] producers() {
        final ComponentRunTimes componentRunTimes = new ComponentRunTimes("runtimes");

        final TestInstanceCreator testInstanceCreator = new TestInstanceCreator();

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
        executeWithRunTime(producer);
    }
}
