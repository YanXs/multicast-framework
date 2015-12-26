package org.xiaos.multicast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xiaos.multicast.exceptions.MulticastMessageProcessException;
import org.xiaos.multicast.util.ObjectUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.Executor;

/**
 * Created by xiaoshu on 2015/12/20.
 */
public class MulticastReceiver implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(MulticastReceiver.class);

    public static final int DEFAULT_RECEIVE_TIMEOUT = Integer.MAX_VALUE;

    public static final int DEFAULT_RECEIVE_RETRY = 3;

    private MulticastSocket socket;

    private String multiAddress;

    private int port;

    private int receiveTimeout = DEFAULT_RECEIVE_TIMEOUT;

    private int receiveRetry = DEFAULT_RECEIVE_RETRY;

    private Executor asynExecutor;

    private boolean processReceivedPacketAsyn;

    private volatile boolean active;

    public void setReceiveTimeout(int receiveTimeout) {
        this.receiveTimeout = receiveTimeout;
    }

    public void setReceiveRetry(int receiveRetry) {
        this.receiveRetry = receiveRetry;
    }

    public void setAsynExecutor(Executor asynExecutor) {
        this.asynExecutor = asynExecutor;
    }

    public void setProcessReceivedPacketAsyn(boolean processReceivedPacketAsyn) {
        this.processReceivedPacketAsyn = processReceivedPacketAsyn;
    }

    public boolean isActive() {
        return active;
    }

    public MulticastReceiver(String multiAddress, int port) {
        try {
            InetAddress inetAddress = InetAddress.getByName(multiAddress);
            socket = new MulticastSocket(port);
            socket.joinGroup(inetAddress);
        } catch (IOException e) {
            logger.error(e.getMessage());
            shutDown();
        }
    }

    public MulticastReceiver(String multiAddress, int port, int receiveTimeout) {
        try {
            InetAddress inetAddress = InetAddress.getByName(multiAddress);
            socket = new MulticastSocket(port);
            socket.joinGroup(inetAddress);
            socket.setSoTimeout(receiveTimeout);
        } catch (IOException e) {
            logger.error(e.getMessage());
            shutDown();
        }
    }

    public void startListening() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (isActive()) {
            DatagramPacketWrapper packetWrapper = new DatagramPacketWrapper();
            try {
                socket.receive(packetWrapper.getReceivedDatagramPacket());
                if (processReceivedPacketAsyn) {
                    if (asynExecutor == null) {
                        logger.warn("please specify an asynExecutor!");
                        processIncomingDatagramPacket(packetWrapper);
                    } else {
                        asynExecutor.execute(new ProcessDatagramPacketTask(packetWrapper));
                    }
                } else {
                    processIncomingDatagramPacket(packetWrapper);
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                --receiveRetry;
            }
        }
        shutDown();
    }

    private void processIncomingDatagramPacket(DatagramPacketWrapper packetWrapper) throws Exception {
        Object message = ObjectUtils.bytesToObject(packetWrapper.getReceivedDatagramPacket().getData());
        MulticastMessage multicastMessage = (MulticastMessage) message;
        if (multicastMessage.getMessageType() == null) {
            throw new MulticastMessageProcessException("MulticastMessage type must not be null!");
        } else if (multicastMessage.getMessageType().equals(MessageType.REFRESH_CACHE)) {
            logger.info("received " + MessageType.REFRESH_CACHE + " message");
        } else if (multicastMessage.getMessageType().equals(MessageType.MESSAGE_CONTAINER)) {
            //todo auto convert to object
        } else {
            //todo
        }
    }


    public void shutDown() {
        if (!isActive())
            return;
        active = false;
        if (socket != null)
            socket.close();
    }

    private class ProcessDatagramPacketTask implements Runnable {

        DatagramPacketWrapper datagramPacketWrapper;

        ProcessDatagramPacketTask(DatagramPacketWrapper datagramPacketWrapper) {
            this.datagramPacketWrapper = datagramPacketWrapper;
        }

        public void run() {
            try {
                processIncomingDatagramPacket(datagramPacketWrapper);
            } catch (Exception e) {
                logger.error("encountered exception while process packetDate" + e.getMessage());
            }
        }
    }
}
