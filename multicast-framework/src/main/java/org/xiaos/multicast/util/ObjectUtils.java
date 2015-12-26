package org.xiaos.multicast.util;

import java.io.*;

public class ObjectUtils {

    public static byte[] objectToBytes(Object object) throws IOException {
        byte[] bytes = null;
        ByteArrayOutputStream bo = null;
        ObjectOutputStream oo = null;
        try {
            bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            oo.writeObject(object);
            bytes = bo.toByteArray();
        } finally {
            if (oo != null)
                oo.close();
            if (bo != null)
                bo.close();
        }
        return bytes;
    }

    public static Object bytesToObject(byte[] bytes) throws Exception {
        Object obj = null;
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        try {
            bi = new ByteArrayInputStream(bytes);
            oi = new ObjectInputStream(bi);
            obj = oi.readObject();
        } finally {
            if (oi != null)
                oi.close();
            if (bi != null)
                bi.close();
        }
        return obj;
    }
}
