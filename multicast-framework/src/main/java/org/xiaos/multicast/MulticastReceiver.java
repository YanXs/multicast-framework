package org.xiaos.multicast;

import java.net.DatagramPacket;
import java.util.concurrent.Executor;

/**
 * Created by xiaoshu on 2015/12/20.
 */
public class MulticastReceiver implements Runnable {

    public MulticastReceiver() {

    }

    public void startListening(Executor taskExecutor) {
        taskExecutor.execute(null);
    }

    public void run() {

    }

}
