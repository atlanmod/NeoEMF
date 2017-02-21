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
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.mapper.MultiValueWithIndices;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassDescriptor;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * A {@link PersistenceBackend} that stores all elements in an in-memory key/value store.
 */
@ParametersAreNonnullByDefault
public class TransientBackend implements PersistenceBackend, MultiValueWithIndices {

    /**
     * An in-memory map that stores the container of {@link PersistentEObject}s, identified by the object {@link Id}.
     */
    private final Map<Id, ContainerDescriptor> containerMap = new ConcurrentHashMap<>();

    /**
     * An in-memory map that stores the EClass for {@link PersistentEObject}s, identified by the object {@link Id}.
     */
    private final Map<Id, MetaclassDescriptor> instanceOfMap = new ConcurrentHashMap<>();

    /**
     * An in-memory map that stores Structural feature values for {@link PersistentEObject}s, identified by the
     * associated {@link FeatureKey}.
     */
    private final Map<FeatureKey, Object> features = new ConcurrentHashMap<>();

    /**
     * An in-memory map that store the values of multi-valued features for {@link PersistentEObject}s,
     * identified by the associated {@link ManyFeatureKey}.
     */
    private final Map<ManyFeatureKey, Object> multiFeatures = new ConcurrentHashMap<>();

    /**
     * Whether this back-end is closed.
     */
    private boolean isClosed = false;

    /**
     * Casts the {@code value} as expected.
     *
     * @param value the value to cast
     * @param <V>   the expected type of the value
     *
     * @return the casted value
     */
    @SuppressWarnings("unchecked")
    private static <V> V cast(Object value) {
        return (V) value;
    }

    @Override
    public void save() {
        // Do nothing
    }

    @Override
    public void close() {
        if (isClosed) {
            return;
        }

        isClosed = true;

        new Thread(() -> {
            containerMap.clear();
            instanceOfMap.clear();
            features.clear();
            multiFeatures.clear();
        }).run();
    }

    @Override
    public boolean isClosed() {
        return isClosed;
    }

    @Override
    public boolean isDistributed() {
        return false;
    }

    @Override
    public void copyTo(PersistenceBackend target) {
        // Do nothing
    }

    @Override
    public boolean exists(Id id) {
        return metaclassOf(id).isPresent();
    }

    @Nonnull
    @Override
    public Optional<ContainerDescriptor> containerOf(Id id) {
        checkNotNull(id);

        return Optional.ofNullable(containerMap.get(id));
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
        checkNotNull(id);
        checkNotNull(container);

        containerMap.put(id, container);
    }

    @Nonnull
    @Override
    public Optional<MetaclassDescriptor> metaclassOf(Id id) {
        checkNotNull(id);

        return Optional.ofNullable(instanceOfMap.get(id));
    }

    @Override
    public void metaclassFor(Id id, MetaclassDescriptor metaclass) {
        checkNotNull(id);
        checkNotNull(metaclass);

        instanceOfMap.put(id, metaclass);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        checkNotNull(key);

        return Optional.ofNullable(cast(features.get(key)));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        checkNotNull(key);
        checkNotNull(value);

        return Optional.ofNullable(cast(features.put(key, value)));
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        checkNotNull(key);

        features.remove(key);
    }

    @Override
    public <V> boolean hasValue(FeatureKey key) {
        checkNotNull(key);

        return features.containsKey(key);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        checkNotNull(key);

        return Optional.ofNullable(cast(multiFeatures.get(key)));
    }

    @Override
    public <V> void safeValueFor(ManyFeatureKey key, @Nullable V value) {
        checkNotNull(key);

        if (nonNull(value)) {
            multiFeatures.put(key, value);
        }
        else {
            multiFeatures.remove(key);
        }
    }
}
