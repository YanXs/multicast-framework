package org.xiaos.multicast;

/**
 * Created by xiaoshu on 2015/12/20.
 */
public class MulticastMessage {

    private final byte[] body;

    public MulticastMessage(byte[] body){
        this.body = body;
    }
    public byte[] getBody() {
        return this.body;
    }

    public int bodyLength() {
        return this.body.length;
    }
}
