package fr.inria.atlanmod.neoemf.io.serializer;

import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Serializer} for {@link SingleFeatureKey}s.
 */
@ParametersAreNonnullByDefault
final class SingleFeatureKeySerializer implements Serializer<SingleFeatureKey> {

    @Override
    public void serialize(SingleFeatureKey key, DataOutput out) throws IOException {
        out.writeUTF(key.id().toString());
        out.writeUTF(key.name());
    }

    @Nonnull
    @Override
    public SingleFeatureKey deserialize(DataInput in) throws IOException {
        return SingleFeatureKey.of(
                StringId.of(in.readUTF()),
                in.readUTF()
        );
    }
}
