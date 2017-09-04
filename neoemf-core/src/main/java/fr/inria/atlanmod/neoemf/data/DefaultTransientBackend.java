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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link TransientBackend} that stores all elements in {@link Map}s.
 */
@ParametersAreNonnullByDefault
// TODO Replace the one-to-one relations by a many-to-one relations ('containers' and 'instances')
public class DefaultTransientBackend extends AbstractTransientBackend {

    /**
     * The number of instances of this class.
     */
    private static final AtomicInteger COUNTER = new AtomicInteger();

    /**
     * An in-memory map that stores the container of {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the object {@link Id}.
     */
    @Nonnull
    private final ChronicleMap<Id, SingleFeatureBean> containers;

    /**
     * An in-memory map that stores the meta-class for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the object {@link Id}.
     */
    @Nonnull
    private final ChronicleMap<Id, ClassBean> instances;

    /**
     * An in-memory map that stores single-feature values for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the associated {@link SingleFeatureBean}.
     */
    @Nonnull
    private final ChronicleMap<SingleFeatureBean, Object> features;

    public DefaultTransientBackend() {
        final int id = COUNTER.getAndIncrement();

        containers = ChronicleMapBuilder.of(Id.class, SingleFeatureBean.class)
                .name("default/" + id + "/containers")
                .keyMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forId()))
                .averageKeySize(24)
                .valueMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forSingleFeature()))
                .averageValueSize(24 + 16)
                .entries(1_000_000)
                .create();

        instances = ChronicleMapBuilder.of(Id.class, ClassBean.class)
                .name("default/" + id + "/instances")
                .keyMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forId()))
                .averageKeySize(24)
                .valueMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forClass()))
                .averageValueSize(16 + 64)
                .entries(1_000_000)
                .create();

        features = ChronicleMapBuilder.of(SingleFeatureBean.class, Object.class)
                .name("default/" + id + "/features")
                .keyMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forSingleFeature()))
                .averageKeySize(24 + 16)
                .averageValueSize(64)
                .entries(10_000_000)
                .create();
    }

    @Override
    protected void innerClose() {
        containers.clear();
        containers.close();

        instances.clear();
        instances.close();

        features.clear();
        features.close();
    }

    @Nonnull
    @Override
    protected Map<Id, SingleFeatureBean> allContainers() {
        return containers;
    }

    @Nonnull
    @Override
    protected Map<Id, ClassBean> allInstances() {
        return instances;
    }

    @Nonnull
    @Override
    protected Map<SingleFeatureBean, Object> allFeatures() {
        return features;
    }
}
