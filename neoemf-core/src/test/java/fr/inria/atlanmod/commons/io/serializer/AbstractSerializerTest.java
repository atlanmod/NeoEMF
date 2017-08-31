package fr.inria.atlanmod.commons.io.serializer;

import fr.inria.atlanmod.commons.AbstractTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * An abstract test-case that checks the behavior of {@link Serializer} instances.
 */
public abstract class AbstractSerializerTest extends AbstractTest {

    /**
     * Serializes then deserializes the given {@code value} with the specified {@code serializer}, with the basic
     * methods.
     *
     * @param value      the sample value
     * @param serializer the serializer to use
     * @param <T>        the type of the (de)serialized value
     *
     * @return the value after processing
     *
     * @throws IOException if an I/O error occurs during the serialization
     */
    protected <T> T process(T value, Serializer<T> serializer) throws IOException {
        byte[] bytes = serializer.serialize(value);

        return serializer.deserialize(bytes);
    }

    /**
     * Serializes then deserializes the given {@code value} with the specified {@code serializer}, by using a stream.
     *
     * @param value      the sample value
     * @param serializer the serializer to use
     * @param <T>        the type of the (de)serialized value
     *
     * @return the value after processing
     *
     * @throws IOException if an I/O error occurs during the serialization
     */
    protected <T> T processWithStream(T value, Serializer<T> serializer) throws IOException {
        byte[] data;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream out = new ObjectOutputStream(baos)) {
            serializer.serialize(value, out);
            out.flush();

            data = baos.toByteArray();
        }

        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return serializer.deserialize(in);
        }
    }
}
