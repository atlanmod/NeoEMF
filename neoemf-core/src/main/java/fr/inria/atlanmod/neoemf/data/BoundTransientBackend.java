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
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;
import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * A {@link TransientBackend}, bound to a unique {@link Id}, that stores all elements in {@link Map}s.
 */
@ParametersAreNonnullByDefault
public final class BoundTransientBackend extends AbstractTransientBackend<String> {

    /**
     * A map that holds all features associated to their owner {@link Id}.
     *
     * @see #features
     * @see #forId(Id)
     */
    @Nonnull
    private static final Map<Id, Map<String, Object>> FEATURES_REGISTRY = new HashMap<>();

    /**
     * A shared in-memory map that stores the container of {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the object {@link Id}.
     */
    @Nonnull
    private static final Map<Id, SingleFeatureKey> CONTAINERS = new ManyToOneMap<>();

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
    private final Map<String, Object> features;

    /**
     * The owner of this back-end.
     */
    @Nonnull
    private final Id owner;

    /**
     * Constructs a new {@code BoundTransientBackend} with the given {@code owner}.
     *
     * @param owner    the identifier of the owner of this back-end
     * @param features the map used for stroring the features of this backend
     */
    private BoundTransientBackend(Id owner, Map<String, Object> features) {
        this.owner = owner;
        this.features = features;

        Log.debug("BoundTransientBackend created for {0}", owner);
    }

    /**
     * Retrieves or creates a new {@code BoundTransientBackend} with the given {@code owner}.
     *
     * @param owner the identifier of the owner of this back-end
     *
     * @return a backend, bound to the {@code owner}
     */
    public static TransientBackend forId(Id owner) {
        return new BoundTransientBackend(owner, FEATURES_REGISTRY.computeIfAbsent(owner, (o) -> new HashMap<>()));
    }

    @Override
    protected void safeClose() {
        // Remove the container from the owner if present (accessible only by the owner)
        CONTAINERS.remove(owner);

        // Unregister the current back-end and clear all features associated with the owner
        FEATURES_REGISTRY.remove(owner).clear();

        Log.debug("BoundTransientBackend closed for {0}", owner);

        // Cleans all shared in-memory maps: they will no longer be used
        if (FEATURES_REGISTRY.isEmpty()) {
            Log.debug("Cleaning BoundTransientBackend");

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
    protected Map<Id, SingleFeatureKey> allContainers() {
        return CONTAINERS;
    }

    @Nonnull
    @Override
    protected Map<Id, ClassDescriptor> allInstances() {
        return INSTANCES;
    }

    @Nonnull
    @Override
    protected Map<String, Object> allFeatures() {
        return features;
    }

    @Nonnull
    @Override
    protected String transform(SingleFeatureKey key) {
        checkArgument(Objects.equals(owner, checkNotNull(key.id())),
                "%s is not the owner of this back-end (%s)", key.id(), owner);

        return key.name();
    }
}
