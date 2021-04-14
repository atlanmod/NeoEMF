/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.bean.serializer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import org.atlanmod.commons.io.serializer.AbstractSerializerTest;
import org.atlanmod.commons.io.serializer.BinarySerializer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link BinarySerializer} implementations.
 */
@ParametersAreNonnullByDefault
class BeanSerializerTest extends AbstractSerializerTest {

    @Test
    void testSerializeDeserializeId() throws IOException {
        BinarySerializer<Id> serializer = BeanSerializerFactory.getInstance().forId();

        Id object = Id.getProvider().fromLong(42);
        Id result = process(object, serializer);

        assertThat(result).isEqualTo(object);
    }

    @Test
    void testSerializeDeserializeIdWithStream() throws IOException {
        BinarySerializer<Id> serializer = BeanSerializerFactory.getInstance().forId();

        Id expected = Id.getProvider().fromLong(42);
        Id result = processWithStream(expected, serializer);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testSerializeDeserializeClass() throws IOException {
        BinarySerializer<ClassBean> serializer = BeanSerializerFactory.getInstance().forClass();

        ClassBean object = ClassBean.of("name0", "uri0");
        ClassBean result = process(object, serializer);

        assertThat(result).isEqualTo(object);
    }

    @Test
    void testSerializeDeserializeClassWithStream() throws IOException {
        BinarySerializer<ClassBean> serializer = BeanSerializerFactory.getInstance().forClass();

        ClassBean object = ClassBean.of("name0", "uri0");
        ClassBean result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(object);
    }

    @Test
    void testSerializeDeserializeFeatureKey() throws IOException {
        BinarySerializer<SingleFeatureBean> serializer = BeanSerializerFactory.getInstance().forSingleFeature();

        SingleFeatureBean object = SingleFeatureBean.of(Id.getProvider().fromLong(42), 10);
        SingleFeatureBean result = process(object, serializer);

        assertThat(result).isEqualTo(object);
    }

    @Test
    void testSerializeDeserializeFeatureKeyWithStream() throws IOException {
        BinarySerializer<SingleFeatureBean> serializer = BeanSerializerFactory.getInstance().forSingleFeature();

        SingleFeatureBean object = SingleFeatureBean.of(Id.getProvider().fromLong(42), 10);
        SingleFeatureBean result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(object);
    }

    @Test
    void testSerializeDeserializeManyFeatureKey() throws IOException {
        BinarySerializer<ManyFeatureBean> serializer = BeanSerializerFactory.getInstance().forManyFeature();

        ManyFeatureBean object = ManyFeatureBean.of(Id.getProvider().fromLong(42), 10, 0);
        ManyFeatureBean result = process(object, serializer);

        assertThat(result).isEqualTo(object);
    }

    @Test
    void testSerializeDeserializeManyFeatureKeyWithStream() throws IOException {
        BinarySerializer<ManyFeatureBean> serializer = BeanSerializerFactory.getInstance().forManyFeature();

        ManyFeatureBean object = ManyFeatureBean.of(Id.getProvider().fromLong(42), 10, 44);
        ManyFeatureBean result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(object);
    }
}
