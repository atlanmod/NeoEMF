/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.im;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.FeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;

import org.atlanmod.commons.annotation.Singleton;
import org.atlanmod.commons.annotation.Static;
import org.atlanmod.commons.log.Log;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.atlanmod.commons.Preconditions.checkEqualTo;

/**
 * A {@link InMemoryBackend}, bound to a unique {@link fr.inria.atlanmod.neoemf.core.Id}, that stores all elements in
 * {@link Map}s.
 * <p>
 * This {@link fr.inria.atlanmod.neoemf.data.Backend} is fully transient and used when a {@link
 * fr.inria.atlanmod.neoemf.core.PersistentEObject} is not owned by a {@link org.eclipse.emf.ecore.resource.Resource},
 * persistent or not.
 * <p>
 * For performance reason, each instance is bound to a unique {@link Id}, but data are stored in maps shared between all
 * instances.
 */
@ParametersAreNonnullByDefault
public final class BoundInMemoryBackend extends AbstractInMemoryBackend {

    /**
     * The number of instances of this class.
     *
     * @see #internalClose()
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

    @Override
    protected void internalClose() {
        COUNTER.decrementAndGet();

        dataHolder.close(owner);
    }

    @Override
    public void copyTo(DataMapper target) {
        throw new UnsupportedOperationException(String.format("%s does not support copy", getClass().getName()));
    }

    @Nonnull
    @Override
    protected Map<Id, SingleFeatureBean> containers() {
        return dataHolder.containers;
    }

    @Nonnull
    @Override
    protected Map<Id, ClassBean> instances() {
        return dataHolder.instances;
    }

    @Nonnull
    @Override
    protected Map<SingleFeatureBean, Object> features() {
        return dataHolder.features;
    }

    @Override
    protected void checkFeature(FeatureBean feature) {
        super.checkFeature(feature);
        checkEqualTo(feature.owner(), owner, "%s is not the owner of this back-end (%s)", feature.owner(), owner);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean feature, V value) {
        Optional<V> previousValue = super.valueFor(feature, value);

        dataHolder.featuresById
                .computeIfAbsent(feature.owner(), id -> new HashSet<>())
                .add(feature.id());

        return previousValue;
    }

    @Override
    public void removeValue(SingleFeatureBean feature) {
        super.removeValue(feature);

        dataHolder.featuresById
                .computeIfAbsent(feature.owner(), id -> new HashSet<>())
                .remove(feature.id());
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

        BoundInMemoryBackend that = (BoundInMemoryBackend) o;
        return Objects.equals(owner, that.owner);
    }

    /**
     * An object that holds shared data for all instances of {@code BoundInMemoryBackend}.
     */
    @Singleton
    @ParametersAreNonnullByDefault
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
                    .name(String.format("%s/%s", prefix, "containers"))
                    .entries(Sizes.ENTRIES)
                    .averageKeySize(Sizes.ID)
                    .averageValueSize(Sizes.FEATURE)
                    .keyMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forId()))
                    .valueMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forSingleFeature()))
                    .create();

            instances = ChronicleMapBuilder.of(Id.class, ClassBean.class)
                    .name(String.format("%s/%s", prefix, "instances"))
                    .entries(Sizes.ENTRIES)
                    .averageKeySize(Sizes.ID)
                    .averageValueSize(Sizes.CLASS)
                    .keyMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forId()))
                    .valueMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forClass()))
                    .create();

            features = ChronicleMapBuilder.of(SingleFeatureBean.class, Object.class)
                    .name(String.format("%s/%s", prefix, "features"))
                    .entries(Sizes.ENTRIES)
                    .averageKeySize(Sizes.FEATURE)
                    .averageValueSize(Sizes.FEATURE_VALUE)
                    .keyMarshaller(new BeanMarshaller<>(SERIALIZER_FACTORY.forSingleFeature()))
                    .create();

            featuresById = new ConcurrentHashMap<>();
        }

        /**
         * Cleans the data related to the specified {@code id}.
         *
         * @param id the identifier of the data to clean
         */
        public void close(Id id) {
            // Remove the container from the id if present (accessible only by the id)
            containers.remove(id);
            instances.remove(id);

            // Unregister the current back-end and clear all features associated with the id
            final Set<Integer> relatedFeatures = featuresById.remove(id);
            if (nonNull(relatedFeatures)) {
                relatedFeatures.forEach(n -> features.remove(SingleFeatureBean.of(id, n)));
            }

            // Cleans all shared in-memory maps: they will no longer be used
            if (COUNTER.get() == 0L) {
                closeAll();
            }
        }

        /**
         * Cleans all data, and closes every maps.
         */
        private void closeAll() {
            if (!isClosed()) {
                Log.info("Cleaning BoundInMemoryBackend#DataHolder");

                containers.close();
                instances.close();
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
