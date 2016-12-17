package fr.inria.atlanmod.neoemf.data.berkeleydb.serializer;

import fr.inria.atlanmod.neoemf.data.structure.ClassInfo;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import org.apache.commons.lang3.SerializationUtils;

/**
 * Created by sunye on 17/12/2016.
 */
public class CISerializer {

    public static byte[] serialize(ClassInfo ci) {
        return SerializationUtils.serialize(ci);
    }

    public static ClassInfo deserialize(byte[] data) {
        return (ClassInfo) SerializationUtils.deserialize(data);
    }
}
