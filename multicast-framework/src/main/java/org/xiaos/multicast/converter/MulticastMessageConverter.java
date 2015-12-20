package org.xiaos.multicast.converter;

import org.xiaos.multicast.MulticastMessage;

/**
 * Created by xiaoshu on 2015/12/20.
 */
public interface MulticastMessageConverter {

    MulticastMessage toMessage(Object object);
}
