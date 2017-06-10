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

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;
import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * A {@link TransientBackend} that is bounded to a unique {@link Id}.
 */
@ParametersAreNonnullByDefault
public final class BoundedTransientBackend extends AbstractTransientBackend {

    /**
     * A map that holds all created instances of {@code BoundedTransientBackend}.
     */
    @Nonnull
    // TODO Attach the registry to a resource to avoid conflicts
    private static final Map<Id, Backend> REGISTRY = new HashMap<>();

    /**
     * A shared in-memory map that stores the container of {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the object {@link Id}.
     */
    @Nonnull
    private static final Map<Id, ContainerDescriptor> CONTAINERS = new ManyToOneMap<>();

    /**
     * A shared in-memory map that stores the metaclass for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the object {@link Id}.
     */
    @Nonnull
    private static final Map<Id, ClassDescriptor> INSTANCES = new ManyToOneMap<>();

    /**
     * An in-memory map that stores structural feature values for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by their name.
     */
    @Nonnull
    private final Map<String, Object> features = new HashMap<>();

    /**
     * The owner of this back-end.
     */
    @Nonnull
    private final Id owner;

    /**
     * Constructs a new {@code BoundedTransientBackend} with the given {@code owner}.
     *
     * @param owner the identifier of the owner of this back-end
     */
    private BoundedTransientBackend(Id owner) {
        this.owner = owner;

        Log.debug("BoundedTransientBackend created for {0}", owner);
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

    @Override
    protected void safeClose() {
        // Clear all features associated with the owner
        features.clear();

        // Remove the container from the owner if present (accessible only by the owner)
        CONTAINERS.remove(owner);

        // Unregister the current back-end
        REGISTRY.remove(owner);

        Log.debug("BoundedTransientBackend closed for {0}", owner);

        // Cleans all shared in-memory maps: they will no longer be used
        if (REGISTRY.isEmpty()) {
            Log.debug("Cleaning BoundedTransientBackend");

            CONTAINERS.clear();
            INSTANCES.clear();
        }
    }

    @Override
    public void copyTo(DataMapper target) {
        // No need to copy anything
    }

    @Nonnull
    @Override
    protected Map<Id, ContainerDescriptor> allContainers() {
        return CONTAINERS;
    }

    @Nonnull
    @Override
    protected Map<Id, ClassDescriptor> allInstances() {
        return INSTANCES;
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
