package fr.inria.atlanmod.neoemf.data.berkeleydb.serializer;

import fr.inria.atlanmod.neoemf.core.Id;
import org.apache.commons.lang3.SerializationUtils;

/**
 * Created by sunye on 17/12/2016.
 */
public class IdSerializer {

    public static byte[] serialize(Id id) {
        return SerializationUtils.serialize(id);
    }

    public static Id deserialize(byte[] data) {
        return (Id) SerializationUtils.deserialize(data);
    }
}
