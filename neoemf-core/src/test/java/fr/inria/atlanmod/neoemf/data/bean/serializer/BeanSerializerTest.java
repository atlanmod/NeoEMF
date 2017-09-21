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

package fr.inria.atlanmod.neoemf.data.bean.serializer;

import fr.inria.atlanmod.commons.io.serializer.AbstractSerializerTest;
import fr.inria.atlanmod.commons.io.serializer.Serializer;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdProvider;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link Serializer} implementations.
 */
public class BeanSerializerTest extends AbstractSerializerTest {

    @Test
    public void testSerializeDeserializeId() throws IOException {
        Serializer<Id> serializer = BeanSerializerFactory.getInstance().forId();

        Id object = IdProvider.generate("id0");
        Id result = process(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeIdWithStream() throws IOException {
        Serializer<Id> serializer = BeanSerializerFactory.getInstance().forId();

        Id object = IdProvider.generate("id0");
        Id result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeClass() throws IOException {
        Serializer<ClassBean> serializer = BeanSerializerFactory.getInstance().forClass();

        ClassBean object = ClassBean.of("name0", "uri0");
        ClassBean result = process(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeClassWithStream() throws IOException {
        Serializer<ClassBean> serializer = BeanSerializerFactory.getInstance().forClass();

        ClassBean object = ClassBean.of("name0", "uri0");
        ClassBean result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeFeatureKey() throws IOException {
        Serializer<SingleFeatureBean> serializer = BeanSerializerFactory.getInstance().forSingleFeature();

        SingleFeatureBean object = SingleFeatureBean.of(IdProvider.generate("id0"), "name0");
        SingleFeatureBean result = process(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeFeatureKeyWithStream() throws IOException {
        Serializer<SingleFeatureBean> serializer = BeanSerializerFactory.getInstance().forSingleFeature();

        SingleFeatureBean object = SingleFeatureBean.of(IdProvider.generate("id0"), "name0");
        SingleFeatureBean result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeManyFeatureKey() throws IOException {
        Serializer<ManyFeatureBean> serializer = BeanSerializerFactory.getInstance().forManyFeature();

        ManyFeatureBean object = ManyFeatureBean.of(IdProvider.generate("id0"), "name0", 0);
        ManyFeatureBean result = process(object, serializer);

        assertThat(result).isEqualTo(result);
    }

    @Test
    public void testSerializeDeserializeManyFeatureKeyWithStream() throws IOException {
        Serializer<ManyFeatureBean> serializer = BeanSerializerFactory.getInstance().forManyFeature();

        ManyFeatureBean object = ManyFeatureBean.of(IdProvider.generate("id0"), "name0", 0);
        ManyFeatureBean result = processWithStream(object, serializer);

        assertThat(result).isEqualTo(result);
    }
}
