/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.im;

import fr.inria.atlanmod.commons.Stopwatch;
import fr.inria.atlanmod.commons.annotation.Singleton;
import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.io.serializer.Serializer;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.FeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.serializer.BeanSerializerFactory;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.query.AsyncQueryDispatcher;
import fr.inria.atlanmod.neoemf.data.query.QueryDispatcher;

import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.functions.Action;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static java.util.Objects.isNull;

/**
 * A {@link InMemoryBackend}, bound to a unique {@link Id}, that stores all elements in {@link Map}s.
 */
@ParametersAreNonnullByDefault
public final class BoundInMemoryBackend extends AbstractInMemoryBackend {

    /**
     * The number of instances of this class.
     *
     * @see #blockingClose()
     * @see DataHolder#close(Id)
     */
    @Nonnull
    @Nonnegative
    // FIXME May be inconsistent if a PersistentEObject is released by the garbage collector
    private static final AtomicLong COUNTER = new AtomicLong();

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
     * Constructs a new {@code BoundInMemoryBackend} with the given {@code owner}.
     *
     * @param owner the identifier of the owner of this back-end
     */
    public BoundInMemoryBackend(Id owner) {
        super(owner.toHexString());

        if (dataHolder.isClosed()) {
            COUNTER.set(0L);
            dataHolder.init();
        }

        COUNTER.incrementAndGet();

        this.owner = owner;
    }

    @Nonnull
    @Override
    protected QueryDispatcher dispatcher() {
        return dataHolder.scheduler;
    }

    @Nonnull
    @Override
    protected Action blockingClose() {
        return () -> {
            COUNTER.decrementAndGet();
            dataHolder.close(owner);
        };
    }

    @Override
    public void copyTo(DataMapper target) {
        throw new UnsupportedOperationException(String.format("%s does not support copy", getClass().getName()));
    }

    @Nonnull
    @Override
    protected ChronicleMap<Id, SingleFeatureBean> allContainers() {
        return dataHolder.containers;
    }

    @Nonnull
    @Override
    protected ChronicleMap<Id, ClassBean> allInstances() {
        return dataHolder.instances;
    }

    @Nonnull
    @Override
    protected ChronicleMap<SingleFeatureBean, Object> allFeatures() {
        return dataHolder.features;
    }

    @Override
    protected void checkKey(FeatureBean key) {
        super.checkKey(key);
        checkArgument(key.owner().equals(owner), "%s is not the owner of this back-end (%s)", key.owner(), owner);
    }

    @Nonnull
    @Override
    public <V> Completable valueFor(SingleFeatureBean key, V value) {
        Action setFunc = () -> dataHolder.featuresById
                .computeIfAbsent(key.owner(), id -> new HashSet<>())
                .add(key.id());

        return super.valueFor(key, value)
                .doOnComplete(setFunc)
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeValue(SingleFeatureBean key) {
        Action unregisterFunc = () -> dataHolder.featuresById
                .computeIfAbsent(key.owner(), id -> new HashSet<>())
                .remove(key.id());

        return super.removeValue(key)
                .doOnComplete(unregisterFunc)
                .cache();
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BoundInMemoryBackend that = BoundInMemoryBackend.class.cast(o);
        return Objects.equals(owner, that.owner);
    }

    /**
     * An object that holds shared data for all instances of {@code BoundInMemoryBackend}.
     */
    @Singleton
    @ParametersAreNonnullByDefault
    private static final class DataHolder {

        /**
         * The {@link BeanSerializerFactory} to use for creating the {@link Serializer} instances.
         */
        @Nonnull
        protected final BeanSerializerFactory serializers = BeanSerializerFactory.getInstance();

        /**
         * The asynchrous dispatcher of this backend.
         * <p>
         * Only one dispatcher for all instances of {@code BoundInMemoryBackend}.
         */
        @Nonnull
        private final QueryDispatcher scheduler = new AsyncQueryDispatcher(BoundInMemoryBackend.class.getSimpleName());

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
         * An in-memory map that stores all feature values for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
         * identified by the associated {@link SingleFeatureBean}. Many-feature values are grouped in collections.
         */
        private ChronicleMap<SingleFeatureBean, Object> features;

        /**
         * An in-memory map that stores all features created for an {@link Id}.
         */
        private Map<Id, Set<Integer>> featuresById;

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
            final String prefix = "bound";

            containers = ChronicleMapBuilder.of(Id.class, SingleFeatureBean.class)
                    .name(prefix + "/containers")
                    .entries(DataSizes.ENTRIES)
                    .averageKeySize(DataSizes.ID)
                    .averageValueSize(DataSizes.FEATURE)
                    .keyMarshaller(new BeanMarshaller<>(serializers.forId()))
                    .valueMarshaller(new BeanMarshaller<>(serializers.forSingleFeature()))
                    .create();

            instances = ChronicleMapBuilder.of(Id.class, ClassBean.class)
                    .name(prefix + "/instances")
                    .entries(DataSizes.ENTRIES)
                    .averageKeySize(DataSizes.ID)
                    .averageValueSize(DataSizes.CLASS)
                    .keyMarshaller(new BeanMarshaller<>(serializers.forId()))
                    .valueMarshaller(new BeanMarshaller<>(serializers.forClass()))
                    .create();

            features = ChronicleMapBuilder.of(SingleFeatureBean.class, Object.class)
                    .name(prefix + "/features")
                    .entries(DataSizes.ENTRIES)
                    .averageKeySize(DataSizes.FEATURE)
                    .averageValueSize(DataSizes.FEATURE_VALUE)
                    .keyMarshaller(new BeanMarshaller<>(serializers.forSingleFeature()))
                    .create();

            featuresById = new HashMap<>();
        }

        /**
         * Cleans the data related to the specified {@code id}, and closes every maps if necessary.
         *
         * @param id the identifier of the data to clean
         */
        public void close(Id id) {
            if (COUNTER.get() == 0L) {
                // Cleans all shared in-memory maps: they will no longer be used
                closeAll();
            }
            else {
                // Remove the container from the id if present (accessible only by the id)
                containers.remove(id);

                // Unregister the current back-end and clear all features related to the id
                Optional.ofNullable(featuresById.remove(id))
                        .ifPresent(rf -> rf.forEach(n -> features.remove(SingleFeatureBean.of(id, n))));
            }
        }

        /**
         * Closes every maps.
         */
        private void closeAll() {
            Log.debug("BoundInMemoryBackend is closing...");
            final Stopwatch stopwatch = Stopwatch.createStarted();

            try {
                containers.close();
                instances.close();
                features.close();

                featuresById.clear();
            }
            finally {
                Log.debug("BoundInMemoryBackend is now closed. Took {0}", stopwatch.stop().elapsed());
            }
        }

        /**
         * Checks whether the maps are closed.
         *
         * @return {@code true} if the maps are closed, or not initialized yet.
         */
        public boolean isClosed() {
            // If the 'containers' map is closed, we consider that all maps are closed
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
            static final DataHolder INSTANCE = new DataHolder();
        }
    }
}
