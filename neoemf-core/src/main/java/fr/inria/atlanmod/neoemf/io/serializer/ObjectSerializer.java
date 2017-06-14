package fr.inria.atlanmod.neoemf.io.serializer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.WillNotClose;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;
import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * A {@link Serializer} for any object, using Java serialization.
 *
 * @param <T> the type of (de)serialized objects
 */
@ParametersAreNonnullByDefault
final class ObjectSerializer<T> implements Serializer<T> {

    @Override
    public void serialize(T t, @WillNotClose DataOutput out) throws IOException {
        checkNotNull(t);
        checkNotNull(out);
        checkArgument(Serializable.class.isInstance(t),
                "Serializer requires a Serializable payload but received an object of type " + t.getClass().getName());

        if (ObjectOutput.class.isInstance(out)) {
            serialize(t, ObjectOutput.class.cast(out));
        }
        else if (OutputStream.class.isInstance(out)) {
            try (ObjectOutput os = new ObjectOutputStream(OutputStream.class.cast(out))) {
                serialize(t, os);
            }
        }
        else {
            throw new IllegalStateException(String.format("Unknown stream of type %s", out.getClass().getName()));
        }
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public T deserialize(@WillNotClose DataInput in) throws IOException {
        checkNotNull(in);

        if (ObjectInput.class.isInstance(in)) {
            return deserialize(ObjectInput.class.cast(in));
        }
        else if (InputStream.class.isInstance(in)) {
            try (ObjectInput is = new ObjectInputStream(InputStream.class.cast(in))) {
                return deserialize(is);
            }
        }
        else {
            throw new IllegalStateException(String.format("Unknown stream of type %s", in.getClass().getName()));
        }
    }

    /**
     * Write an object of type {@code T} to the given {@code out}.
     * <p>
     * <b>Note:</b> The {@code value} <b>must</b> implement {@link Serializable}.
     *
     * @param t      the object to serialize
     * @param out the output out
     *
     * @throws IOException if an I/O error occurs during the serialization
     */
    public void serialize(T t, @WillNotClose ObjectOutput out) throws IOException {
        out.writeObject(t);
    }

    /**
     * Reads and assembles an object of type {@code T} from the given {@code in}.
     *
     * @param in the input in
     *
     * @return the deserialized object
     *
     * @throws IOException if an I/O error occurs during the deserialization
     */
    @SuppressWarnings("unchecked")
    public T deserialize(@WillNotClose ObjectInput in) throws IOException {
        try {
            return (T) in.readObject();
        }
        catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }
}
