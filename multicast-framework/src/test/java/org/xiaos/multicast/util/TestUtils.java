package org.xiaos.multicast.util;

import org.junit.Test;

public class TestUtils {

    @Test
    public void testUtils() throws Exception {
        byte[] bytes = new byte[100];
        Object o = ObjectUtils.bytesToObject(bytes);
    }
}
