package fr.inria.atlanmod.neoemf.data.berkeleydb.serializer;

import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import org.apache.commons.lang3.SerializationUtils;

/**
 * Created by sunye on 17/12/2016.
 */
public class FKSerializer {

    public static byte[] serialize(FeatureKey fk) {
        return SerializationUtils.serialize(fk);
    }

    public static FeatureKey deserialize(byte[] data) {
        return (FeatureKey) SerializationUtils.deserialize(data);
    }
}
