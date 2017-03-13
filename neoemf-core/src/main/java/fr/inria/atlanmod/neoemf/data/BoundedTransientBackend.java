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
import fr.inria.atlanmod.neoemf.data.mapper.ClassMapperUnsupported;
import fr.inria.atlanmod.neoemf.data.mapper.ContainerMapperUnsupported;
import fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithLists;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * A lightweight {@link TransientBackend} that stores in memory the features associated to a unique {@link Id}. This
 * back-end does not support containers and metaclasses mapping.
 */
@ParametersAreNonnullByDefault
public class BoundedTransientBackend implements TransientBackend, ClassMapperUnsupported, ContainerMapperUnsupported, ManyValueWithLists {

    /**
     * The owner of this back-end.
     */
    private final Id ownerId;

    /**
     * An in-memory map that stores structural feature values for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by their name.
     */
    private final Map<String, Object> features = new HashMap<>();

    /**
     * Whether this back-end is closed.
     */
    private boolean isClosed = false;

    /**
     * Constructs a new {@code BoundedTransientBackend} with the given {@code owner}.
     *
     * @param ownerId the identifier of the owner of this back-end
     */
    public BoundedTransientBackend(Id ownerId) {
        this.ownerId = ownerId;
    }

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
    public void close() {
        if (isClosed) {
            return;
        }

        isClosed = true;

        features.clear();
    }

    @Override
    public boolean exists(Id id) {
        return Objects.equals(ownerId, id) && !features.isEmpty();
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(FeatureKey key) {
        checkOwner(key.id());

        return Optional.ofNullable(cast(features.get(key.name())));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        checkOwner(key.id());
        checkNotNull(value);

        return Optional.ofNullable(cast(features.put(key.name(), value)));
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        checkOwner(key.id());

        features.remove(key.name());
    }

    @Override
    public <V> boolean hasValue(FeatureKey key) {
        checkOwner(key.id());

        return features.containsKey(key.name());
    }

    /**
     * Checks that the {@code id} is the same as the owner of that store.
     *
     * @param id the identifier to check the ownership
     *
     * @throws IllegalArgumentException if the {@code id} is not {@link #ownerId}
     */
    private void checkOwner(Id id) {
        checkArgument(Objects.equals(ownerId, checkNotNull(id)), "%s is not the owner of this back-end (%s)", id, ownerId);
    }
}
