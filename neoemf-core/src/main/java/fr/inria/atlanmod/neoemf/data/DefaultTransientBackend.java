/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nonnegative;
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
    @Nonnull
    @Nonnegative
    private static final AtomicInteger INSTANCES = new AtomicInteger();

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
     * An in-memory map that stores all feature values for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the associated {@link SingleFeatureBean}. Many-feature values are grouped in collections.
     */
    @Nonnull
    private final ChronicleMap<SingleFeatureBean, Object> features;

    public DefaultTransientBackend() {
        final int id = INSTANCES.getAndIncrement();

        containers = ChronicleMapBuilder.of(Id.class, SingleFeatureBean.class)
                .name("default/" + id + "/containers")
                .entries(Sizes.ENTRIES)
                .averageKeySize(Sizes.ID)
                .averageValueSize(Sizes.FEATURE)
                .keyMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forId()))
                .valueMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forSingleFeature()))
                .create();

        instances = ChronicleMapBuilder.of(Id.class, ClassBean.class)
                .name("default/" + id + "/instances")
                .entries(Sizes.ENTRIES)
                .averageKeySize(Sizes.ID)
                .averageValueSize(Sizes.CLASS)
                .keyMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forId()))
                .valueMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forClass()))
                .create();

        features = ChronicleMapBuilder.of(SingleFeatureBean.class, Object.class)
                .name("default/" + id + "/features")
                .entries(Sizes.ENTRIES)
                .averageKeySize(Sizes.FEATURE)
                .averageValueSize(Sizes.FEATURE_VALUE)
                .keyMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forSingleFeature()))
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
    protected Map<Id, SingleFeatureBean> containers() {
        return containers;
    }

    @Nonnull
    @Override
    protected Map<Id, ClassBean> instances() {
        return instances;
    }

    @Nonnull
    @Override
    protected Map<SingleFeatureBean, Object> features() {
        return features;
    }
}
