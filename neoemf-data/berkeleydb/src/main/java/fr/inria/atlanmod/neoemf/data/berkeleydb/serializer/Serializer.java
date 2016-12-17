package fr.inria.atlanmod.neoemf.data.berkeleydb.serializer;

import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import java.io.*;

/**
 * Created by sunye on 17/12/2016.
 */
public class Serializer {


    public static byte[] serialize(Object obj) {
        byte[] data;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            out.flush();
        } catch (IOException e) {
            NeoLogger.error(e);
        }
        data = bos.toByteArray();
        return data;
    }

    public static Object deserialize(byte[] data) {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
         Object result = null;
        try {
            ObjectInput in = new ObjectInputStream(bis);
            result = in.readObject();
        } catch (IOException e) {
            NeoLogger.error(e);
        } catch (ClassNotFoundException e) {
            NeoLogger.error(e);
        }
        return result;
    }
}
