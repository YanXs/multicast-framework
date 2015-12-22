package org.xiaos.multicast;


import java.io.Serializable;

public class MessageProperties implements Serializable{

    public static final String CONTENT_TYPE_BYTES = "application/octet-stream";

    public static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";

    public static final String CONTENT_TYPE_SERIALIZED_OBJECT = "application/x-java-serialized-object";

    public static final String CONTENT_TYPE_JSON = "application/json";

    public static final String CONTENT_TYPE_JSON_ALT = "text/x-json";

    public static final String CONTENT_TYPE_XML = "application/xml";

    public static final String SPRING_BATCH_FORMAT = "springBatchFormat";

    public static final String BATCH_FORMAT_LENGTH_HEADER4 = "lengthHeader4";

    public static final String SPRING_AUTO_DECOMPRESS = "springAutoDecompress";

    private volatile String contentType = CONTENT_TYPE_BYTES;

    private volatile String contentEncoding;

    private volatile long contentLength;

    private String messageId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }
}
