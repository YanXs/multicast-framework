package org.xiaos.multicast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xiaos.multicast.converter.MulticastMessageConverter;
import org.xiaos.multicast.core.MessagePostProcessor;
import org.xiaos.multicast.core.MessageProperties;
import org.xiaos.multicast.core.MulticastMessage;
import org.xiaos.multicast.exceptions.MulticastException;

import java.io.IOException;
import java.net.*;

/**
 * Created by xiaoshu on 2015/12/20.
 */
public class MulticastSender {

    private final Logger logger = LoggerFactory.getLogger(MulticastSender.class);

    public static final int DEFAULT_TIME_TO_LIVE = 1;
    private MulticastSocket socket;
    private InetAddress inetAddress;
    private int port;
    private int ttl = DEFAULT_TIME_TO_LIVE;
    private MulticastMessageConverter messageConverter;

    public MulticastSocket getSocket() {
        return socket;
    }

    public void setSocket(MulticastSocket socket) {
        this.socket = socket;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public MulticastMessageConverter getMessageConverter() throws MulticastException {
        if (messageConverter == null)
            throw new MulticastException("no message converter setted!");
        return messageConverter;
    }

    public void setMessageConverter(MulticastMessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    public MulticastSender(String multiAddress, int port) {
        try {
            this.inetAddress = InetAddress.getByName(multiAddress);
            this.port = port;
            this.socket = new MulticastSocket(port);
        } catch (UnknownHostException e) {
            logger.error(e.getMessage());
            close();
        } catch (IOException e) {
            logger.error(e.getMessage());
            close();
        }
    }

    public void convertAndSend(Object object) throws IOException {
        convertAndSend(this.inetAddress, this.port, object);
    }

    public void convertAndSend(Object object, MessagePostProcessor messagePostProcessor) throws IOException {
        convertAndSend(this.inetAddress, this.port, object, messagePostProcessor);
    }

    public void convertAndSend(InetAddress inetAddress, int port, final Object message) throws IOException {
        MulticastMessage requestMessage = convertMessageIfNecessary(message);
        send(requestMessage, inetAddress, port);
    }

    public void convertAndSend(InetAddress inetAddress, int port, final Object message, final MessagePostProcessor messagePostProcessor) throws IOException {
        MulticastMessage requestMessage = convertMessageIfNecessary(message);
        requestMessage = messagePostProcessor.postProcessMessage(requestMessage);
        send(requestMessage,inetAddress,port);
    }

    public void send(MulticastMessage message) throws IOException {
        send(message,this.inetAddress, this.port);
    }

    public void send(MulticastMessage requestMessage, InetAddress inetAddress, int port) throws IOException {
        DatagramPacketWrapper packetWrapper = new DatagramPacketWrapper(requestMessage, inetAddress, port);
        doSend(packetWrapper);
    }

    private MulticastMessage convertMessageIfNecessary(final Object object) {
        if (object instanceof MulticastMessage)
            return (MulticastMessage) object;
        return getMessageConverter().toMessage(object, new MessageProperties());
    }

    private void doSend(DatagramPacketWrapper datagramPacketWrapper) throws IOException {
        socket.send(datagramPacketWrapper.getRequestDatagramPacket());
    }

    private void close() {
        if (socket != null)
            socket.close();
    }
}
