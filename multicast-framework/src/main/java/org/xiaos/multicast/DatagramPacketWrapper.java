package org.xiaos.multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class DatagramPacketWrapper {

    private final int MAX_BUFFER_SIZE = 8 * 1024;
    private DatagramPacket requestDatagramPacket;
    private DatagramPacket receivedDatagramPacket;

    public DatagramPacketWrapper(MulticastMessage message, InetAddress inetAddress, int port){
        requestDatagramPacket = new DatagramPacket(message.getBody(), message.bodyLength(), inetAddress, port);
    }

    public DatagramPacketWrapper(){
        byte buffer[] = new byte[MAX_BUFFER_SIZE];
        receivedDatagramPacket = new DatagramPacket(buffer, buffer.length);
    }

    public DatagramPacket getRequestDatagramPacket() {
        return requestDatagramPacket;
    }

    public DatagramPacket getReceivedDatagramPacket() {
        return receivedDatagramPacket;
    }
}
