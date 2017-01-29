package fr.inria.atlanmod.neoemf.data.berkeleydb.serializer;


import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FeatureKeySerializerTest {

    @Test
    public void testSerialize() {
        FeatureKeySerializer serializer = new FeatureKeySerializer();
        FeatureKey fkIn = FeatureKey.of(new StringId("obj1"), "feature");
        FeatureKey fkOut = serializer.deserialize(serializer.serialize(fkIn));

        assertThat(fkIn).isEqualTo(fkOut);
    }

    @Test
    public void testSerializeMFK() {
        FeatureKeySerializer serializer = new FeatureKeySerializer();
        MultivaluedFeatureKey fkIn = FeatureKey.of(new StringId("obj1"), "feature").withPosition(0);
        MultivaluedFeatureKey fkOut = (MultivaluedFeatureKey) serializer.deserialize(serializer.serialize(fkIn));

        assertThat(fkIn).isEqualTo(fkOut);
    }


    @Test
    public void testSerializeMFKBis() {
        FeatureKeySerializer serializer = new FeatureKeySerializer();
        FeatureKey fk = FeatureKey.of(new StringId("obj1"), "feature");
        MultivaluedFeatureKey fk1 = fk.withPosition(0);
        MultivaluedFeatureKey fk2 = fk.withPosition(1);

        byte[] ser1 = serializer.serialize(fk1);
        byte[] ser2 = serializer.serialize(fk2);
        byte[] ser3 = serializer.serialize(fk);


        assertThat(Arrays.equals(ser1, ser2)).isFalse();
        assertThat(Arrays.equals(ser1, ser3)).isFalse();
        assertThat(Arrays.equals(ser2, ser3)).isFalse();
    }
}
