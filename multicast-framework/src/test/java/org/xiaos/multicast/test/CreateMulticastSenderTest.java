package org.xiaos.multicast.test;

import org.junit.Before;
import org.junit.Test;
import org.xiaos.multicast.converter.JsonMulticastMessageConverter;
import org.xiaos.multicast.core.MessageType;
import org.xiaos.multicast.MulticastFrameworkFactory;
import org.xiaos.multicast.core.MulticastMessage;
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

    @Test
    public void sendObjectUseConverter() throws IOException {
        multicastSender.setMessageConverter(new JsonMulticastMessageConverter());
        multicastSender.convertAndSend(new SimpleBean());
    }

    private class SimpleBean{
        String id = "123";
        String name = "hello";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        SimpleBean(){}
    }
}
