package org.xiaos.multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * Created by xiaoshu on 2015/12/20.
 */
public class DatagramPacketWrapper {

    private final int MAX_BUFFER_SIZE = 8 * 1024;
    private DatagramPacket datagramPacket;

    public DatagramPacketWrapper(MulticastMessage message, InetAddress inetAddress, int port){
        datagramPacket = new DatagramPacket(message.getBody(), message.bodyLength(), inetAddress, port);
    }

    public DatagramPacket getDategramPacket(){
        return this.datagramPacket;
    }

}
