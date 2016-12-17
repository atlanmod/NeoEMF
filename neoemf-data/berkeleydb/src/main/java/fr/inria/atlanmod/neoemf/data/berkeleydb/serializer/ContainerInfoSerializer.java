package fr.inria.atlanmod.neoemf.data.berkeleydb.serializer;

import fr.inria.atlanmod.neoemf.data.structure.ContainerInfo;
import org.apache.commons.lang3.SerializationUtils;

/**
 * Created by sunye on 17/12/2016.
 */
public class ContainerInfoSerializer {

    public static byte[] serialize(ContainerInfo ci) {
        return SerializationUtils.serialize(ci);
    }

    public static ContainerInfo deserialize(byte[] data) {
        return (ContainerInfo) SerializationUtils.deserialize(data);
    }
}
