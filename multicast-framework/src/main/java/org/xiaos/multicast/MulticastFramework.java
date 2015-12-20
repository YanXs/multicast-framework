package org.xiaos.multicast;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.util.Assert;
import org.xiaos.multicast.exceptions.MulticastException;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiaoshu on 2015/12/20.
 */
public class MulticastFramework {

    private static final long DEFAULT_RECEIVE_TIMEOUT = 2000;

    private static final int DEFAULT_RECEIVE_RETRY = 3;

    private static final int DEFAULT_TIME_TO_LIVE = 1;

    private String multiAddress;

    private int port;

    private boolean isMultiSource;

    private long receiveTimeout = DEFAULT_RECEIVE_TIMEOUT;

    private int receiveRetry = DEFAULT_RECEIVE_RETRY;

    private int timeToLive = DEFAULT_TIME_TO_LIVE;

    private volatile Executor taskExecutor = Executors.newSingleThreadExecutor();

    private MulticastSender sender;

    private MulticastReceiver receiver;

    public void setMultiAddress(String multiAddress) {
        this.multiAddress = multiAddress;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setReceiveTimeout(long receiveTimeout) {
        this.receiveTimeout = receiveTimeout;
    }

    public void setReceiveRetry(int receiveRetry) {
        this.receiveRetry = receiveRetry;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void setIsMultiSource(boolean isMultiSource) {
        this.isMultiSource = isMultiSource;
    }

    public void setTaskExecutor(Executor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public MulticastFramework(String multiAddress, int port, boolean isMultiSource) {
    }

    public void convertAndSend(Object object) {

    }

    public void startListening() throws MulticastException {
        if (isMultiSource)
            throw new MulticastException("multisender should not start listening!");


    }
}
