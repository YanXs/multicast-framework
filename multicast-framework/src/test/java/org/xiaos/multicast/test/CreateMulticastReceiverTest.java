package org.xiaos.multicast.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.xiaos.multicast.MulticastFrameworkFactory;
import org.xiaos.multicast.MulticastReceiver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateMulticastReceiverTest {

    private MulticastReceiver multicastReceiver;

    @Before
    public void before() {
        multicastReceiver = MulticastFrameworkFactory.newMulticastReceiver("230.0.0.5", 8888);
    }

    @Test
    public void processIncomingMessage() {
        multicastReceiver.startListening();
    }
}
