package org.xiaos.multicast.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.xiaos.multicast.core.MessageProperties;
import org.xiaos.multicast.exceptions.MessageConversionException;

import java.util.Map;

public class JsonJavaTypeMapper extends AbstractJavaTypeMapper {

    public void fromJavaType(JavaType javaType, MessageProperties properties) {
        properties.addHeader(getClassIdFieldName(), javaType.getRawClass());
        if (javaType.isContainerType() && !javaType.isArrayType()) {
            properties.addHeader(getContentClassIdFieldName(), javaType
                    .getContentType().getRawClass());
        }

        if (javaType.getKeyType() != null) {
            properties.addHeader(getKeyClassIdFieldName(), javaType
                    .getKeyType().getRawClass());
        }
    }

    public JavaType toJavaType(MessageProperties properties) {
        JavaType classType = getClassIdType(parseMessagePropertiesHeader(properties, getClassIdFieldName()));
        if (!classType.isContainerType() || classType.isArrayType())
            return classType;

        JavaType contentClassType = getClassIdType(parseMessagePropertiesHeader(properties,
                getContentClassIdFieldName()));

        if (classType.getKeyType() == null){
            return CollectionType.construct(classType.getRawClass(), contentClassType);
        }

        JavaType keyType = getClassIdType(parseMessagePropertiesHeader(properties,
               getKeyClassIdFieldName()));

        return MapType.construct(classType.getRawClass(), keyType, contentClassType);
    }



    private JavaType getClassIdType(String classId){
        return null;
    }
}
