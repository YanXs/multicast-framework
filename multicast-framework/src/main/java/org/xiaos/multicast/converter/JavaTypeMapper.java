package org.xiaos.multicast.converter;

import com.fasterxml.jackson.databind.JavaType;
import org.xiaos.multicast.core.MessageProperties;


public interface JavaTypeMapper {

    void fromJavaType(JavaType javaType, MessageProperties properties);

    JavaType toJavaType(MessageProperties properties);
}
