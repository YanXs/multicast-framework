package org.xiaos.multicast.core;

public class MapLikeMessagePostProcessor implements MessagePostProcessor{
    public static final String DEFAULT_CONTENT_CLASSID_FIELD_NAME = MessageProperties.DEFAULT_CONTENT_CLASSID_FIELD_NAME;
    public static final String DEFAULT_KEY_CLASSID_FIELD_NAME = MessageProperties.DEFAULT_KEY_CLASSID_FIELD_NAME;

    private static final Class<?> DEFAULT_KEY_TYPE = String.class;

    private Class keyType = DEFAULT_KEY_TYPE;

    private Class valueType;

    public MapLikeMessagePostProcessor(Class<?> valueType){
        this(DEFAULT_KEY_TYPE, valueType);
    }

    public MapLikeMessagePostProcessor(Class<?> keyType, Class<?> valueType){
        this.keyType = keyType;
        this.valueType = valueType;
    }

    public Class getKeyType() {
        return keyType;
    }

    public void setKeyType(Class keyType) {
        this.keyType = keyType;
    }

    public Class getValueType() {
        return valueType;
    }

    public void setValueType(Class valueType) {
        this.valueType = valueType;
    }

    public MulticastMessage postProcessMessage(MulticastMessage message) {
        MessageProperties messageProperties = message.getMessageProperties();
        messageProperties.addHeader(DEFAULT_KEY_CLASSID_FIELD_NAME, keyType);
        messageProperties.addHeader(DEFAULT_CONTENT_CLASSID_FIELD_NAME, valueType);
        return message;
    }
}
