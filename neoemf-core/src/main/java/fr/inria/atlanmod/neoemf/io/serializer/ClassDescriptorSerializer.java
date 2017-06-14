package fr.inria.atlanmod.neoemf.io.serializer;

import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Serializer} for {@link ClassDescriptor}s.
 */
@ParametersAreNonnullByDefault
final class ClassDescriptorSerializer implements Serializer<ClassDescriptor> {

    @Override
    public void serialize(ClassDescriptor metaclass, DataOutput out) throws IOException {
        out.writeUTF(metaclass.name());
        out.writeUTF(metaclass.uri());
    }

    @Nonnull
    @Override
    public ClassDescriptor deserialize(DataInput in) throws IOException {
        return ClassDescriptor.of(
                in.readUTF(),
                in.readUTF()
        );
    }
}
