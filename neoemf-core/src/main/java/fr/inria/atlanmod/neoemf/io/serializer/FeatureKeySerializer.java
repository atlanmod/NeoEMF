package fr.inria.atlanmod.neoemf.io.serializer;

import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Serializer} for {@link FeatureKey}s.
 */
@ParametersAreNonnullByDefault
final class FeatureKeySerializer implements Serializer<FeatureKey> {

    @Override
    public void serialize(FeatureKey key, DataOutput out) throws IOException {
        out.writeUTF(key.id().toString());
        out.writeUTF(key.name());
    }

    @Nonnull
    @Override
    public FeatureKey deserialize(DataInput in) throws IOException {
        return FeatureKey.of(
                StringId.of(in.readUTF()),
                in.readUTF()
        );
    }
}
