package org.xiaos.multicast;

import org.xiaos.multicast.util.ObjectUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class DatagramPacketWrapper {

    private final int MAX_BUFFER_SIZE = 8 * 1024;
    private DatagramPacket requestDatagramPacket;
    private DatagramPacket receivedDatagramPacket;

    public DatagramPacketWrapper(Object message, InetAddress inetAddress, int port) throws IOException {
        byte[] messageToSend = ObjectUtils.objectToBytes(message);
        requestDatagramPacket = new DatagramPacket(messageToSend, messageToSend.length, inetAddress, port);
    }

    public DatagramPacketWrapper() {
        byte[] buffer = new byte[MAX_BUFFER_SIZE];
        receivedDatagramPacket = new DatagramPacket(buffer, buffer.length);
    }

    public DatagramPacket getReceivedDatagramPacket() {
        return receivedDatagramPacket;
    }

    public void setReceivedDatagramPacket(DatagramPacket receivedDatagramPacket) {
        this.receivedDatagramPacket = receivedDatagramPacket;
    }

    public DatagramPacket getRequestDatagramPacket() {
        return requestDatagramPacket;
    }

    public void setRequestDatagramPacket(DatagramPacket requestDatagramPacket) {
        this.requestDatagramPacket = requestDatagramPacket;
    }
}
