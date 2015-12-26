package org.xiaos.multicast.test;

import org.junit.Before;
import org.junit.Test;
import org.xiaos.multicast.MessageType;
import org.xiaos.multicast.MulticastFrameworkFactory;
import org.xiaos.multicast.MulticastMessage;
import org.xiaos.multicast.MulticastSender;

import java.io.IOException;

public class CreateMulticastSenderTest {

    private MulticastSender multicastSender;

    @Before
    public void before() {
        multicastSender = MulticastFrameworkFactory.newMulticastSender("230.0.0.5", 8888);
    }

    @Test
    public void sendSimpleMessaage() throws IOException, InterruptedException {
        MulticastMessage message = new MulticastMessage(MessageType.REFRESH_CACHE);
        multicastSender.send(message);
    }
}
