package org.xiaos.multicast;

import java.io.Serializable;

/**
 * Created by xiaoshu on 2015/12/20.
 */
public class MulticastMessage implements Serializable {

    private final byte[] body;

    private final MessageProperties messageProperties;

    private final MessageType messageType;

    public MulticastMessage(byte[] body, MessageProperties messageProperties) {
        this.body = body;
        this.messageProperties = messageProperties;
        messageType = MessageType.MESSAGE_CONTAINER;
    }

    public MulticastMessage(MessageType messageType) {
        body = null;
        messageProperties = null;
        if (messageType.equals(MessageType.MESSAGE_CONTAINER))
            throw new IllegalArgumentException(messageType.toString() + " is not supposed here!");
        this.messageType = messageType;
    }

    public byte[] getBody() {
        return this.body;
    }

    public MessageProperties getMessageProperties() {
        return messageProperties;
    }

    public MessageType getMessageType() {
        return messageType;
    }
}
