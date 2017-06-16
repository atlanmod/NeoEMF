package fr.inria.atlanmod.neoemf.io.serializer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the behavior of {@link Serializer} instances.
 */
public abstract class AbstractSerializerTest {

    /**
     * Gets the {@link SerializerFactory} to test.
     *
     * @return the factory
     */
    protected abstract SerializerFactory factory();

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

    @Test
    public void testSerializeDeserializeObject() throws IOException {
        Serializer<List<Integer>> serializer = factory().forAny();

        List<Integer> object = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> result = process(object, serializer);

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    public void testSerializeDeserializeObjectWithStream() throws IOException {
        Serializer<List<Integer>> serializer = factory().forAny();

        List<Integer> object = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> result = process(object, serializer);

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    public void testSerializeDeserializeId() throws IOException {
        Serializer<Id> serializer = factory().forId();

        Id object = StringId.of("id0");
        Id result = process(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeIdWithStream() throws IOException {
        Serializer<Id> serializer = factory().forId();

        Id object = StringId.of("id0");
        Id result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeClass() throws IOException {
        Serializer<ClassDescriptor> serializer = factory().forClass();

        ClassDescriptor object = ClassDescriptor.of("name0", "uri0");
        ClassDescriptor result = process(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeClassWithStream() throws IOException {
        Serializer<ClassDescriptor> serializer = factory().forClass();

        ClassDescriptor object = ClassDescriptor.of("name0", "uri0");
        ClassDescriptor result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeFeatureKey() throws IOException {
        Serializer<SingleFeatureKey> serializer = factory().forSingleFeatureKey();

        SingleFeatureKey object = SingleFeatureKey.of(StringId.of("id0"), "name0");
        SingleFeatureKey result = process(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeFeatureKeyWithStream() throws IOException {
        Serializer<SingleFeatureKey> serializer = factory().forSingleFeatureKey();

        SingleFeatureKey object = SingleFeatureKey.of(StringId.of("id0"), "name0");
        SingleFeatureKey result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeManyFeatureKey() throws IOException {
        Serializer<ManyFeatureKey> serializer = factory().forManyFeatureKey();

        ManyFeatureKey object = ManyFeatureKey.of(StringId.of("id0"), "name0", 0);
        ManyFeatureKey result = process(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeManyFeatureKeyWithStream() throws IOException {
        Serializer<ManyFeatureKey> serializer = factory().forManyFeatureKey();

        ManyFeatureKey object = ManyFeatureKey.of(StringId.of("id0"), "name0", 0);
        ManyFeatureKey result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(result);
    }
}
