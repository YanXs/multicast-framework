package org.xiaos.multicast.converter;

import org.xiaos.multicast.core.MessageProperties;
import org.xiaos.multicast.exceptions.MessageConversionException;

import java.util.Map;

public abstract class AbstractJavaTypeMapper implements JavaTypeMapper {
    public static final String DEFAULT_CLASSID_FIELD_NAME = MessageProperties.DEFAULT_CLASSID_FIELD_NAME;
    public static final String DEFAULT_CONTENT_CLASSID_FIELD_NAME = MessageProperties.DEFAULT_CONTENT_CLASSID_FIELD_NAME;
    public static final String DEFAULT_KEY_CLASSID_FIELD_NAME = MessageProperties.DEFAULT_KEY_CLASSID_FIELD_NAME;

    public String getClassIdFieldName() {
        return DEFAULT_CLASSID_FIELD_NAME;
    }

    public String getContentClassIdFieldName() {
        return DEFAULT_CONTENT_CLASSID_FIELD_NAME;
    }

    public String getKeyClassIdFieldName() {
        return DEFAULT_KEY_CLASSID_FIELD_NAME;
    }

    public String parseMessagePropertiesHeader(MessageProperties messageProperties, String headerName){
        Map<String, Object> headers = messageProperties.getHeaders();
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
}
