package org.xiaos.multicast;

/**
 * Created by xiaoshu on 2015/12/20.
 */
public class MulticastFrameworkFactory {

    private MulticastFrameworkFactory() {
    }

    public static MulticastSender newMulticastSender(String multiAddress, int port) {
        return new MulticastSender(multiAddress, port);
    }

    public static MulticastReceiver newMulticastReceiver(String multiAddress, int port) {
        return new MulticastReceiver(multiAddress, port);
    }
}
