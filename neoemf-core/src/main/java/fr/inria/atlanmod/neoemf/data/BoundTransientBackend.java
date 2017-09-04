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

import fr.inria.atlanmod.commons.annotation.Singleton;
import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.FeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static java.util.Objects.isNull;

/**
 * A {@link TransientBackend}, bound to a unique {@link Id}, that stores all elements in {@link Map}s.
 */
@ParametersAreNonnullByDefault
public final class BoundTransientBackend extends AbstractTransientBackend {

    /**
     * The number of instances of this class.
     *
     * @see #innerClose()
     * @see DataHolder#cleanAndClose(Id)
     */
    @Nonnull
    private final static AtomicInteger COUNTER = new AtomicInteger();

    /**
     * The owner of this back-end.
     */
    @Nonnull
    private final Id owner;

    /**
     * A map that holds features associated to their owner {@link Id}.
     */
    @Nonnull
    private final DataHolder dataHolder = DataHolder.getInstance();

    /**
     * Constructs a new {@code BoundTransientBackend} with the given {@code owner}.
     *
     * @param owner the identifier of the owner of this back-end
     */
    private BoundTransientBackend(Id owner) {
        if (dataHolder.isClosed()) {
            COUNTER.set(0);
            dataHolder.init();
        }

        COUNTER.incrementAndGet();

        this.owner = owner;
    }

    /**
     * Retrieves or creates a new {@code BoundTransientBackend} with the given {@code owner}.
     *
     * @param owner the identifier of the owner of this back-end
     *
     * @return a backend, bound to the {@code owner}
     */
    public static TransientBackend forId(Id owner) {
        return new BoundTransientBackend(owner);
    }

    @Override
    protected void innerClose() {
        COUNTER.decrementAndGet();

        dataHolder.cleanAndClose(owner);
    }

    @Override
    public void copyTo(DataMapper target) {
        // FIXME Copy only the elements related to the owner
        throw new UnsupportedOperationException(String.format("%s does not support copy", getClass().getName()));
    }

    @Nonnull
    @Override
    protected Map<Id, SingleFeatureBean> allContainers() {
        return dataHolder.containers;
    }

    @Nonnull
    @Override
    protected Map<Id, ClassBean> allInstances() {
        return dataHolder.instances;
    }

    @Nonnull
    @Override
    protected Map<SingleFeatureBean, Object> allFeatures() {
        return dataHolder.features;
    }

    @Override
    protected void checkKey(FeatureBean key) {
        super.checkKey(key);
        checkArgument(key.owner().equals(owner), "%s is not the owner of this back-end (%s)", key.owner(), owner);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        Optional<V> previousValue = super.<V>valueFor(key, value);

        dataHolder.featuresById
                .computeIfAbsent(key.owner(), id -> new HashSet<>())
                .add(key.id());

        return previousValue;
    }

    @Override
    public <V> void unsetValue(SingleFeatureBean key) {
        super.<V>unsetValue(key);

        dataHolder.featuresById
                .computeIfAbsent(key.owner(), id -> new HashSet<>())
                .remove(key.id());
    }

    /**
     * An object that holds shared data for all instances of {@code BoundTransientBackend}.
     */
    @Singleton
    @ParametersAreNonnullByDefault
    // TODO Replace the one-to-one relations by a many-to-one relations ('containers' and 'instances')
    private static final class DataHolder {

        /**
         * A shared in-memory map that stores the container of {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
         * identified by the object {@link Id}.
         */
        private ChronicleMap<Id, SingleFeatureBean> containers;

        /**
         * A shared in-memory map that stores the meta-class for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
         * identified by the object {@link Id}.
         */
        private ChronicleMap<Id, ClassBean> instances;

        /**
         * An in-memory map that stores single-feature values for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
         * identified by the associated {@link SingleFeatureBean}.
         */
        private ChronicleMap<SingleFeatureBean, Object> features;

        /**
         * An in-memory map that stores all features created for an {@link Id}.
         */
        private Map<Id, Set<String>> featuresById;

        /**
         * Constructs a new {@code DataHolder}.
         */
        private DataHolder() {
        }

        /**
         * Returns the instance of this class.
         *
         * @return the instance of this class
         */
        @Nonnull
        public static DataHolder getInstance() {
            return Holder.INSTANCE;
        }

        /**
         * Initializes all maps.
         */
        public void init() {
            containers = ChronicleMapBuilder.of(Id.class, SingleFeatureBean.class)
                    .name("bound/all/containers")
                    .keyMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forId()))
                    .averageKeySize(24)
                    .valueMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forSingleFeature()))
                    .averageValueSize(24 + 16)
                    .entries(1_000_000)
                    .create();

            instances = ChronicleMapBuilder.of(Id.class, ClassBean.class)
                    .name("bound/all/instances")
                    .keyMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forId()))
                    .averageKeySize(24)
                    .valueMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forClass()))
                    .averageValueSize(16 + 64)
                    .entries(1_000_000)
                    .create();

            features = ChronicleMapBuilder.of(SingleFeatureBean.class, Object.class)
                    .name("bound/all/features")
                    .keyMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forSingleFeature()))
                    .averageKeySize(24 + 16)
                    .averageValueSize(64)
                    .entries(10_000_000)
                    .create();

            featuresById = new HashMap<>();
        }

        /**
         * Cleans the data related to the specified {@code id}, and closes every maps if necessary.
         *
         * @param id the identifier of the data to clean
         */
        public void cleanAndClose(Id id) {
            // Remove the container from the id if present (accessible only by the id)
            containers.remove(id);

            // Unregister the current back-end and clear all features associated with the id
            featuresById.remove(id).forEach(n -> features.remove(SingleFeatureBean.of(id, n)));

            // Cleans all shared in-memory maps: they will no longer be used
            if (COUNTER.get() == 0) {
                Log.debug("Cleaning BoundTransientBackend");

                containers.clear();
                containers.close();

                instances.clear();
                instances.close();

                features.clear();
                features.close();

                featuresById.clear();
            }
        }

        /**
         * Checks whether the maps are closed.
         *
         * @return {@code true} if the maps are closed, or not initialized yet.
         */
        public boolean isClosed() {
            // If the 'containers' map is closed, then they are all closed
            return isNull(containers) || !containers.isOpen();
        }

        /**
         * The initialization-on-demand holder of the singleton of this class.
         */
        @Static
        private static final class Holder {

            /**
             * The instance of the outer class.
             */
            private static final DataHolder INSTANCE = new DataHolder();
        }
    }
}
