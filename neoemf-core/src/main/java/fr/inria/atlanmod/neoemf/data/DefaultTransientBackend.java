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
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * A {@link TransientBackend} that stores all elements in {@link ConcurrentHashMap}s.
 */
@ParametersAreNonnullByDefault
public class DefaultTransientBackend extends AbstractTransientBackend {

    /**
     * An in-memory map that stores the container of {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the object {@link Id}.
     */
    @Nonnull
    private final Map<Id, ContainerDescriptor> containers = new ManyToOneMap<>();

    /**
     * An in-memory map that stores the metaclass for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the object {@link Id}.
     */
    @Nonnull
    private final Map<Id, ClassDescriptor> instances = new ManyToOneMap<>();

    /**
     * An in-memory map that stores structural feature values for {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s,
     * identified by the associated {@link FeatureKey}.
     */
    @Nonnull
    private final Map<FeatureKey, Object> features = new HashMap<>();

    @Override
    protected void safeClose() {
        containers.clear();
        instances.clear();
        features.clear();
    }

    @Override
    public void copyTo(DataMapper target) {
        // TODO Implement this method
    }

    @Nonnull
    @Override
    protected Map<Id, ContainerDescriptor> allContainers() {
        return containers;
    }

    @Nonnull
    @Override
    protected Map<Id, ClassDescriptor> allInstances() {
        return instances;
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
}
