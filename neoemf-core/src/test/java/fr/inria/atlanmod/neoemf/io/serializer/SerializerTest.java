/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.serializer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case that checks the behavior of {@link fr.inria.atlanmod.neoemf.io.serializer.Serializer}s.
 */
public class SerializerTest {

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
    private static <T> T process(T value, fr.inria.atlanmod.neoemf.io.serializer.Serializer<T> serializer) throws IOException {
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
    private static <T> T processWithStream(T value, fr.inria.atlanmod.neoemf.io.serializer.Serializer<T> serializer) throws IOException {
        T result;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream out = new ObjectOutputStream(baos)) {
            serializer.serialize(value, out);
            out.flush();

            byte[] bytes = baos.toByteArray();

            try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
                result = serializer.deserialize(in);
            }
        }

        return result;
    }

    @Test
    public void testConstructor() throws Exception {
        Constructor<?> constructor = fr.inria.atlanmod.neoemf.io.serializer.Serializers.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();

        constructor.setAccessible(true);

        assertThat(catchThrowable(constructor::newInstance))
                .isInstanceOf(InvocationTargetException.class)
                .hasCauseExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testSerializeDeserializeObject() throws IOException {
        fr.inria.atlanmod.neoemf.io.serializer.Serializer<ClassDescriptor> serializer = fr.inria.atlanmod.neoemf.io.serializer.Serializers.forObjects();

        ClassDescriptor object = ClassDescriptor.of("name0", "uri0");
        ClassDescriptor result = process(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeObjectWithStream() throws IOException {
        fr.inria.atlanmod.neoemf.io.serializer.Serializer<ClassDescriptor> serializer = fr.inria.atlanmod.neoemf.io.serializer.Serializers.forObjects();

        ClassDescriptor object = ClassDescriptor.of("name0", "uri0");
        ClassDescriptor result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeId() throws IOException {
        fr.inria.atlanmod.neoemf.io.serializer.Serializer<Id> serializer = fr.inria.atlanmod.neoemf.io.serializer.Serializers.forIds();

        Id object = StringId.of("id0");
        Id result = process(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeIdWithStream() throws IOException {
        fr.inria.atlanmod.neoemf.io.serializer.Serializer<Id> serializer = fr.inria.atlanmod.neoemf.io.serializer.Serializers.forIds();

        Id object = StringId.of("id0");
        Id result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeContainer() throws IOException {
        fr.inria.atlanmod.neoemf.io.serializer.Serializer<ContainerDescriptor> serializer = fr.inria.atlanmod.neoemf.io.serializer.Serializers.forContainers();

        ContainerDescriptor object = ContainerDescriptor.of(StringId.of("id0"), "name0");
        ContainerDescriptor result = process(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeContainerWithStream() throws IOException {
        fr.inria.atlanmod.neoemf.io.serializer.Serializer<ContainerDescriptor> serializer = fr.inria.atlanmod.neoemf.io.serializer.Serializers.forContainers();

        ContainerDescriptor object = ContainerDescriptor.of(StringId.of("id0"), "name0");
        ContainerDescriptor result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeClass() throws IOException {
        fr.inria.atlanmod.neoemf.io.serializer.Serializer<ClassDescriptor> serializer = fr.inria.atlanmod.neoemf.io.serializer.Serializers.forMetaclasses();

        ClassDescriptor object = ClassDescriptor.of("name0", "uri0");
        ClassDescriptor result = process(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeClassWithStream() throws IOException {
        fr.inria.atlanmod.neoemf.io.serializer.Serializer<ClassDescriptor> serializer = fr.inria.atlanmod.neoemf.io.serializer.Serializers.forMetaclasses();

        ClassDescriptor object = ClassDescriptor.of("name0", "uri0");
        ClassDescriptor result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeFeatureKey() throws IOException {
        fr.inria.atlanmod.neoemf.io.serializer.Serializer<FeatureKey> serializer = fr.inria.atlanmod.neoemf.io.serializer.Serializers.forFeatureKeys();

        FeatureKey object = FeatureKey.of(StringId.of("id0"), "name0");
        FeatureKey result = process(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeFeatureKeyWithStream() throws IOException {
        fr.inria.atlanmod.neoemf.io.serializer.Serializer<FeatureKey> serializer = fr.inria.atlanmod.neoemf.io.serializer.Serializers.forFeatureKeys();

        FeatureKey object = FeatureKey.of(StringId.of("id0"), "name0");
        FeatureKey result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeManyFeatureKey() throws IOException {
        fr.inria.atlanmod.neoemf.io.serializer.Serializer<ManyFeatureKey> serializer = fr.inria.atlanmod.neoemf.io.serializer.Serializers.forManyFeatureKeys();

        ManyFeatureKey object = ManyFeatureKey.of(StringId.of("id0"), "name0", 0);
        ManyFeatureKey result = process(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeManyFeatureKeyWithStream() throws IOException {
        Serializer<ManyFeatureKey> serializer = fr.inria.atlanmod.neoemf.io.serializer.Serializers.forManyFeatureKeys();

        ManyFeatureKey object = ManyFeatureKey.of(StringId.of("id0"), "name0", 0);
        ManyFeatureKey result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeNonSerializable() {
        assertThat(catchThrowable(() -> fr.inria.atlanmod.neoemf.io.serializer.Serializers.forObjects().serialize(new Object())))
                .isInstanceOf(IllegalArgumentException.class);
    }
}