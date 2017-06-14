package fr.inria.atlanmod.neoemf.io.serializer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Serializer} for {@link Id}s.
 */
@ParametersAreNonnullByDefault
final class IdSerializer implements Serializer<Id> {

    @Override
    public void serialize(Id id, DataOutput out) throws IOException {
        out.writeUTF(id.toString());
    }

    @Nonnull
    @Override
    public Id deserialize(DataInput in) throws IOException {
        return StringId.of(
                in.readUTF()
        );
    }
}
