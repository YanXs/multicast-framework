package org.xiaos.multicast.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xiaos.multicast.MessageProperties;
import org.xiaos.multicast.MulticastMessage;
import org.xiaos.multicast.exceptions.MessageConversionException;

import java.io.IOException;

public class JsonMulticastMessageConverter extends AbstractMessageConverter {
    private static Logger log = LoggerFactory.getLogger(JsonMulticastMessageConverter.class);
    private ObjectMapper jsonObjectMapper = new ObjectMapper();
    private JavaTypeMapper javaTypeMapper;

    public JsonMulticastMessageConverter() {
        initializeJsonObjectMapper();
    }

    public void setJsonObjectMapper(ObjectMapper jsonObjectMapper) {
        this.jsonObjectMapper = jsonObjectMapper;
    }

    public JavaTypeMapper getJavaTypeMapper() {
        return javaTypeMapper;
    }

    public void setJavaTypeMapper(JavaTypeMapper javaTypeMapper) {
        this.javaTypeMapper = javaTypeMapper;
    }

    /**
     * Subclass and override to customize.
     */
    protected void initializeJsonObjectMapper() {
        jsonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public Object fromMessage(MulticastMessage message) {
        Object content = null;
        MessageProperties properties = message.getMessageProperties();
        if (properties != null) {
            String contentType = properties.getContentType();
            if (contentType != null && contentType.contains("json")) {
                String encoding = properties.getContentEncoding();
                if (encoding == null) {
                    encoding = getDefaultCharset();
                }
                try {
                    JavaType targetJavaType = getJavaTypeMapper()
                            .toJavaType(message.getMessageProperties());
                    content = convertBytesToObject(message.getBody(),
                            encoding, targetJavaType);
                } catch (IOException e) {
                    throw new MessageConversionException("Failed to convert Message content", e);
                }
            } else {
                log.warn("Could not convert incoming message with content-type ["
                        + contentType + "]");
            }
        }
        if (content == null) {
            content = message.getBody();
        }
        return content;
    }

    private Object convertBytesToObject(byte[] body, String encoding,
                                        JavaType targetJavaType) throws JsonParseException,
            JsonMappingException, IOException {
        String contentAsString = new String(body, encoding);
        return jsonObjectMapper.readValue(contentAsString, targetJavaType);
    }

    private Object convertBytesToObject(byte[] body, String encoding,
                                        Class<?> targetClass) throws JsonParseException,
            JsonMappingException, IOException {
        String contentAsString = new String(body, encoding);
        return jsonObjectMapper.readValue(contentAsString, jsonObjectMapper.constructType(targetClass));
    }

    @Override
    protected MulticastMessage createMessage(Object objectToConvert,
                                             MessageProperties messageProperties)
            throws MessageConversionException {
        byte[] bytes = null;
        try {
            String jsonString = jsonObjectMapper
                    .writeValueAsString(objectToConvert);
            bytes = jsonString.getBytes(getDefaultCharset());
        } catch (IOException e) {
            throw new MessageConversionException(
                    "Failed to convert Message content", e);
        }
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        messageProperties.setContentEncoding(getDefaultCharset());
        messageProperties.setContentLength(bytes.length);
        getJavaTypeMapper().fromJavaType(jsonObjectMapper.constructType(objectToConvert.getClass()),
                messageProperties);

        return new MulticastMessage(bytes, messageProperties);

    }
}
