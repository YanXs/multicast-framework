package org.xiaos.multicast;

/**
 * Created by xiaoshu on 2015/12/20.
 */
public class MulticastFrameworkFactory {

    private MulticastFrameworkFactory() {
    }

    public static MulticastFramework newMulticastSender(String multiAddress, int port) {
        return new MulticastFramework(multiAddress, port, true);
    }

    public static MulticastFramework newMulticastReceiver(String multiAddress, int port) {
        return new MulticastFramework(multiAddress, port, false);
    }
}
