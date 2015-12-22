package org.xiaos.multicast;

import java.io.Serializable;

/**
 * Created by xiaoshu on 2015/12/20.
 */
public class MulticastMessage implements Serializable{

    private final byte[] body;

    private final MessageProperties messageProperties;
    public MulticastMessage(byte[] body, MessageProperties messageProperties){
        this.body = body;
        this.messageProperties = messageProperties;
    }
    public byte[] getBody() {
        return this.body;
    }

    public int bodyLength() {
        return this.body.length;
    }

    public MessageProperties getMessageProperties() {
        return messageProperties;
    }
}
