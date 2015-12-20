package org.xiaos.multicast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xiaos.multicast.converter.MulticastMessageConverter;
import org.xiaos.multicast.exceptions.MulticastException;

import java.io.IOException;
import java.net.*;

/**
 * Created by xiaoshu on 2015/12/20.
 */
class MulticastSender {

    private final Logger logger = LoggerFactory.getLogger(MulticastSender.class);

    private MulticastSocket socket;
    private InetAddress inetAddress;
    private int port;
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

    public MulticastMessageConverter getMessageConverter() throws MulticastException {
        if (messageConverter == null)
            throw new MulticastException("no message converter setted!");
        return messageConverter;
    }

    public void setMessageConverter(MulticastMessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    public MulticastSender(String multiAddress, int port, int ttl) {
        try {
            this.inetAddress = InetAddress.getByName(multiAddress);
            this.port = port;
            SocketAddress socketAddress = new InetSocketAddress(inetAddress, port);
            this.socket = new MulticastSocket(socketAddress);
            socket.setTimeToLive(ttl);
        } catch (UnknownHostException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }finally {
            if (socket != null)
                socket.close();
        }
    }

    public void convertAndSend(Object object){
        convertAndSend(this.inetAddress, this.port, object);
    }

    public void convertAndSend(InetAddress inetAddress, int port, final Object message){
        //todo convert object to json or xml
        MulticastMessage requestMessage = convertMessageIfNecessary(message);
        DatagramPacketWrapper packetWrapper = new DatagramPacketWrapper(requestMessage, inetAddress, port);
        try {
            send(packetWrapper);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private MulticastMessage convertMessageIfNecessary(final Object object){
        if (object instanceof MulticastMessage)
            return (MulticastMessage)object;
        return getMessageConverter().toMessage(object);
    }

    private void send(DatagramPacketWrapper datagramPacketWrapper) throws IOException {
        socket.send(datagramPacketWrapper.getDategramPacket());
    }
}
