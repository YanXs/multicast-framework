package org.xiaos.multicast;

public enum MessageType {
    REFRESH_CACHE("refresh_cache"), MESSAGE_CONTAINER("message_container");

    private String messageType;

    private MessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return this.messageType;
    }
}
