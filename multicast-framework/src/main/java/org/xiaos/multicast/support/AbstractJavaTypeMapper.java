package org.xiaos.multicast.support;


import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.beans.factory.InitializingBean;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



public abstract class AbstractJavaTypeMapper{
    public static final String DEFAULT_CLASSID_FIELD_NAME = "__TypeId__";
    public static final String DEFAULT_CONTENT_CLASSID_FIELD_NAME = "__ContentTypeId__";
    public static final String DEFAULT_KEY_CLASSID_FIELD_NAME = "__KeyTypeId__";

    private Map<String, Class<?>> idClassMapping = new HashMap<String, Class<?>>();
    private final Map<Class<?>, String> classIdMapping = new HashMap<Class<?>, String>();

    public String getClassIdFieldName() {
        return DEFAULT_CLASSID_FIELD_NAME;
    }

    public String getContentClassIdFieldName() {
        return DEFAULT_CONTENT_CLASSID_FIELD_NAME;
    }

    public String getKeyClassIdFieldName() {
        return DEFAULT_KEY_CLASSID_FIELD_NAME;
    }

    public void setIdClassMapping(Map<String, Class<?>> idClassMapping) {
        this.idClassMapping = idClassMapping;
    }

    protected void addHeader(MessageProperties properties, String headerName,
                             Class<?> clazz) {
        if (classIdMapping.containsKey(clazz)) {
            properties.getHeaders().put(headerName, classIdMapping.get(clazz));
        }
        else {
            properties.getHeaders().put(headerName, clazz.getName());
        }
    }

    protected String retrieveHeader(MessageProperties properties,
                                    String headerName) {
        Map<String, Object> headers = properties.getHeaders();
        Object classIdFieldNameValue = headers.get(headerName);
        String classId = null;
        if (classIdFieldNameValue != null) {
            classId = classIdFieldNameValue.toString();
        }
        if (classId == null) {
            throw new MessageConversionException(
                    "failed to convert Message content. Could not resolve "
                            + headerName + " in header");
        }
        return classId;
    }

    private void validateIdTypeMapping() {
        Map<String, Class<?>> finalIdClassMapping = new HashMap<String, Class<?>>();
        for (Map.Entry<String, Class<?>> entry : idClassMapping.entrySet()) {
            String id = entry.getKey();
            Class<?> clazz = entry.getValue();
            finalIdClassMapping.put(id, clazz);
            classIdMapping.put(clazz, id);
        }
        this.idClassMapping = finalIdClassMapping;
    }

    public Map<String, Class<?>> getIdClassMapping() {
        return Collections.unmodifiableMap(idClassMapping);
    }
}
