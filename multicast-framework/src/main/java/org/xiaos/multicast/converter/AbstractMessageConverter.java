package org.xiaos.multicast.converter;


import org.xiaos.multicast.core.MessageProperties;
import org.xiaos.multicast.core.MulticastMessage;

import java.util.UUID;

public abstract class AbstractMessageConverter implements MulticastMessageConverter{
    public static final String DEFAULT_CHARSET = "UTF-8";

    private volatile String defaultCharset = DEFAULT_CHARSET;

    private boolean createMessageIds = false;

    public void setCreateMessageIds(boolean createMessageIds) {
        this.createMessageIds = createMessageIds;
    }

    protected boolean isCreateMessageIds() {
        return createMessageIds;
    }

    public final MulticastMessage toMessage(Object object, MessageProperties messageProperties) {
        if (messageProperties==null) {
            messageProperties = new MessageProperties();
        }
        MulticastMessage message = createMessage(object, messageProperties);
        messageProperties = message.getMessageProperties();
        if (this.createMessageIds && messageProperties.getMessageId()==null) {
            messageProperties.setMessageId(UUID.randomUUID().toString());
        }
        return message;
    }

    public String getDefaultCharset() {
        return defaultCharset;
    }

    public void setDefaultCharset(String defaultCharset) {
        this.defaultCharset = defaultCharset;
    }

    protected abstract MulticastMessage createMessage(Object object, MessageProperties messageProperties);

    public abstract Object fromMessage(MulticastMessage message);
}
