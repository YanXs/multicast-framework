package org.xiaos.multicast.support;

import org.xiaos.multicast.MulticastMessage;

public interface MessagePostProcessor {
    Object postProcessMessage(MulticastMessage message);
}
