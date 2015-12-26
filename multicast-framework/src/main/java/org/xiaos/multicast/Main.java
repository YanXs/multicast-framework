package org.xiaos.multicast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        MulticastReceiver multicastReceiver = MulticastFrameworkFactory.newMulticastReceiver("230.0.0.5", 8888);
        multicastReceiver.startListening();
    }
}
