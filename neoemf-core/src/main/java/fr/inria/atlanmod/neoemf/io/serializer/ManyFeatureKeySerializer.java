package fr.inria.atlanmod.neoemf.io.serializer;

import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Serializer} for {@link ManyFeatureKey}s.
 */
@ParametersAreNonnullByDefault
final class ManyFeatureKeySerializer implements Serializer<ManyFeatureKey> {

    @Override
    public void serialize(ManyFeatureKey key, DataOutput out) throws IOException {
        out.writeUTF(key.id().toString());
        out.writeUTF(key.name());
        out.writeInt(key.position());
    }

    @Nonnull
    @Override
    public ManyFeatureKey deserialize(DataInput in) throws IOException {
        return ManyFeatureKey.of(
                StringId.of(in.readUTF()),
                in.readUTF(),
                in.readInt()
        );
    }
}
