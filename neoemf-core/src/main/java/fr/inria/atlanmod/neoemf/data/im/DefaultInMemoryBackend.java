/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.im;

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

import io.reactivex.functions.Action;

/**
 * A {@link InMemoryBackend} that stores all elements in {@link Map}s.
 */
@ParametersAreNonnullByDefault
public class DefaultInMemoryBackend extends AbstractInMemoryBackend {

    /**
     * The number of instances of this class, used to identify each instance.
     */
    @Nonnull
    @Nonnegative
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
     * An in-memory map that stores all feature values for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the associated {@link SingleFeatureBean}. Many-feature values are grouped in collections.
     */
    @Nonnull
    private final ChronicleMap<SingleFeatureBean, Object> features;

    /**
     * Constructs a new {@code DefaultInMemoryBackend}.
     */
    public DefaultInMemoryBackend() {
        final int id = COUNTER.getAndIncrement();
        final String prefix = "default/";

        containers = ChronicleMapBuilder.of(Id.class, SingleFeatureBean.class)
                .name(prefix + id + "/containers")
                .entries(DataSizes.ENTRIES)
                .averageKeySize(DataSizes.ID)
                .averageValueSize(DataSizes.FEATURE)
                .keyMarshaller(new BeanMarshaller<>(serializers.forId()))
                .valueMarshaller(new BeanMarshaller<>(serializers.forSingleFeature()))
                .create();

        instances = ChronicleMapBuilder.of(Id.class, ClassBean.class)
                .name(prefix + id + "/instances")
                .entries(DataSizes.ENTRIES)
                .averageKeySize(DataSizes.ID)
                .averageValueSize(DataSizes.CLASS)
                .keyMarshaller(new BeanMarshaller<>(serializers.forId()))
                .valueMarshaller(new BeanMarshaller<>(serializers.forClass()))
                .create();

        features = ChronicleMapBuilder.of(SingleFeatureBean.class, Object.class)
                .name(prefix + id + "/features")
                .entries(DataSizes.ENTRIES)
                .averageKeySize(DataSizes.FEATURE)
                .averageValueSize(DataSizes.FEATURE_VALUE)
                .keyMarshaller(new BeanMarshaller<>(serializers.forSingleFeature()))
                .create();
    }

    @Nonnull
    @Override
    protected Action blockingClose() {
        return () -> {
            containers.close();
            instances.close();
            features.close();
        };
    }

    @Nonnull
    @Override
    protected ChronicleMap<Id, SingleFeatureBean> allContainers() {
        return containers;
    }

    @Nonnull
    @Override
    protected ChronicleMap<Id, ClassBean> allInstances() {
        return instances;
    }

    @Nonnull
    @Override
    protected ChronicleMap<SingleFeatureBean, Object> allFeatures() {
        return features;
    }
}
