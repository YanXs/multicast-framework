package org.xiaos.multicast.exceptions;

/**
 * Created by xiaoshu on 2015/12/22.
 */
public class MessageConversionException extends MulticastException{
    public MessageConversionException(String message) {
        super(message);
    }

    public MessageConversionException(String msg, Throwable cause){
        super(msg, cause);
    }
}
