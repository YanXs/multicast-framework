package org.xiaos.multicast.core;

public class CollectionLikeMessagePostProcessor implements MessagePostProcessor{

    public static final String DEFAULT_CONTENT_CLASSID_FIELD_NAME = MessageProperties.DEFAULT_CONTENT_CLASSID_FIELD_NAME;

    private Class contentType;

    public Class getContentType() {
        return contentType;
    }

    public void setContentType(Class contentType) {
        this.contentType = contentType;
    }

    public CollectionLikeMessagePostProcessor(Class contentType){
        this.contentType = contentType;
    }

    public MulticastMessage postProcessMessage(MulticastMessage message) {
        MessageProperties messageProperties = message.getMessageProperties();
        messageProperties.addHeader(DEFAULT_CONTENT_CLASSID_FIELD_NAME, contentType);
        return message;
    }
}
