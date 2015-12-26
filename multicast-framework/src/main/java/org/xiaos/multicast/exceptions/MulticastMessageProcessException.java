package org.xiaos.multicast.exceptions;

public class MulticastMessageProcessException extends Exception {
    public MulticastMessageProcessException(String message) {
        super(message);
    }

    public MulticastMessageProcessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
