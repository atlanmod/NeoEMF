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
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithLists;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * A {@link TransientBackend} that is bounded to a unique {@link Id}.
 */
@ParametersAreNonnullByDefault
public final class BoundedTransientBackend implements TransientBackend, ManyValueWithLists {

    /**
     * A map that holds all created instances of {@code BoundedTransientBackend}.
     */
    @Nonnull
    private static final Map<Id, Backend> REGISTRY = new ConcurrentHashMap<>();

    /**
     * A shared in-memory map that stores the container of {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the object {@link Id}.
     */
    @Nonnull
    private static final Map<Id, ContainerDescriptor> CONTAINERS = new ConcurrentHashMap<>();

    /**
     * A shared in-memory map that stores the metaclass for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the object {@link Id}.
     */
    @Nonnull
    private static final Map<Id, ClassDescriptor> INSTANCES = new ConcurrentHashMap<>();

    /**
     * The owner of this back-end.
     */
    @Nonnull
    private final Id owner;

    /**
     * An in-memory map that stores structural feature values for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by their name.
     */
    @Nonnull
    private final Map<String, Object> features = new HashMap<>();

    /**
     * Whether this back-end is closed.
     */
    private boolean isClosed = false;

    /**
     * Constructs a new {@code BoundedTransientBackend} with the given {@code owner}.
     *
     * @param owner the identifier of the owner of this back-end
     */
    private BoundedTransientBackend(Id owner) {
        this.owner = owner;
    }

    /**
     * Retrieves or creates a new {@code BoundedTransientBackend} with the given {@code owner}.
     *
     * @param owner the identifier of the owner of this back-end
     *
     * @return a {@code BoundedTransientBackend}
     */
    public static Backend forId(Id owner) {
        return REGISTRY.computeIfAbsent(owner, BoundedTransientBackend::new);
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

        // Clear the features associated with the owner
        features.clear();

        // Remove the container from the owner if present (accessible only by the owner)
        containerFor(owner, null);

        // Don't remove the metaclass for cross-references

        // Unregister the current back-end
        REGISTRY.remove(owner);
    }

    @Override
    public void copyTo(DataMapper target) {
        // No need to copy anything
    }

    @Nonnull
    @Override
    public Optional<ContainerDescriptor> containerOf(Id id) {
        checkNotNull(id);

        return Optional.ofNullable(CONTAINERS.get(id));
    }

    @Override
    public void containerFor(Id id, @Nullable ContainerDescriptor container) {
        checkNotNull(id);

        if (nonNull(container)) {
            CONTAINERS.put(id, container);
        }
        else {
            CONTAINERS.remove(id);
        }
    }

    @Nonnull
    @Override
    public Optional<ClassDescriptor> metaclassOf(Id id) {
        checkNotNull(id);

        return Optional.ofNullable(INSTANCES.get(id));
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        checkNotNull(id);
        checkNotNull(metaclass);

        INSTANCES.put(id, metaclass);
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

    /**
     * Checks that the {@code id} is the same as the owner of that store.
     *
     * @param id the identifier to check the ownership
     *
     * @throws IllegalArgumentException if the {@code id} is not {@link #owner}
     */
    private void checkOwner(Id id) {
        checkArgument(Objects.equals(owner, checkNotNull(id)), "%s is not the owner of this back-end (%s)", id, owner);
    }
}
