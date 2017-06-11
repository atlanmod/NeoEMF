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

package fr.inria.atlanmod.common.io.serializer;

import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case that checks the behavior of {@link Serializer}s.
 */
public class SerializerTest {

    @Test
    public void testConstructor() throws Exception {
        Constructor<?> constructor = Serializers.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();

        constructor.setAccessible(true);

        assertThat(catchThrowable(constructor::newInstance))
                .isInstanceOf(InvocationTargetException.class)
                .hasCauseExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testSerializeDeserialize() {
        ClassDescriptor object = ClassDescriptor.of("name", "uri");

        byte[] bytes = Serializers.forGenerics().serialize(object);

        ClassDescriptor result = Serializers.<ClassDescriptor>forGenerics().deserialize(bytes);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeWithStream() throws IOException {
        ClassDescriptor object = ClassDescriptor.of("name", "uri");

        ClassDescriptor result;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Serializers.forGenerics().serialize(object, baos);

            byte[] bytes = baos.toByteArray();

            try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
                result = Serializers.<ClassDescriptor>forGenerics().deserialize(bais);
            }
        }

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeNonSerializable() {
        assertThat(catchThrowable(() -> Serializers.forGenerics().serialize(new Object())))
                .isInstanceOf(IllegalArgumentException.class);
    }
}