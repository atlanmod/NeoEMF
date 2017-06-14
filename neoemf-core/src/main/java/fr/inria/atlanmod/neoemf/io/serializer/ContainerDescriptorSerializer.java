package fr.inria.atlanmod.neoemf.io.serializer;

import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Serializer} for {@link ContainerDescriptor}s.
 */
@ParametersAreNonnullByDefault
final class ContainerDescriptorSerializer implements Serializer<ContainerDescriptor> {

    @Override
    public void serialize(ContainerDescriptor container, DataOutput out) throws IOException {
        out.writeUTF(container.id().toString());
        out.writeUTF(container.name());
    }

    @Nonnull
    @Override
    public ContainerDescriptor deserialize(DataInput in) throws IOException {
        return ContainerDescriptor.of(
                StringId.of(in.readUTF()),
                in.readUTF()
        );
    }
}
