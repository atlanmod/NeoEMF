package fr.inria.atlanmod.commons.io.serializer;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case that checks the behavior of {@link Serializer} instances.
 */
public class SerializerTest extends AbstractSerializerTest {

    @Test
    public void testSerializeDeserializeObject() throws IOException {
        Serializer<List<Integer>> serializer = SerializerFactory.getInstance().forAny();

        List<Integer> object = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> result = process(object, serializer);

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    public void testSerializeDeserializeObjectWithStream() throws IOException {
        Serializer<List<Integer>> serializer = SerializerFactory.getInstance().forAny();

        List<Integer> object = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> result = process(object, serializer);

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    public void testSerializeNonSerializable() {
        assertThat(catchThrowable(() -> new ObjectSerializer<>().serialize(new Object())))
                .isInstanceOf(IllegalArgumentException.class);
    }
}