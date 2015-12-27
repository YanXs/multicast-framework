package org.xiaos.multicast.core;

import org.xiaos.multicast.core.MulticastMessage;

public interface MessagePostProcessor {
    MulticastMessage postProcessMessage(MulticastMessage message);
}
