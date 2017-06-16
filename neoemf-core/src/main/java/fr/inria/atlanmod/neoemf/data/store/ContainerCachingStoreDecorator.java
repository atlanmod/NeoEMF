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

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that caches containers.
 */
@ParametersAreNonnullByDefault
public class ContainerCachingStoreDecorator extends AbstractCachingStoreDecorator<Id, Optional<SingleFeatureKey>> {

    /**
     * Constructs a new {@code ContainerCachingStoreDecorator} on the given {@code store}.
     *
     * @param store the inner store
     */
    @SuppressWarnings("unused") // Called dynamically
    protected ContainerCachingStoreDecorator(Store store) {
        super(store);
    }

    @Nonnull
    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public Optional<SingleFeatureKey> containerOf(Id id) {
        return cache.get(id, super::containerOf);
    }

    @Override
    public void containerFor(Id id, SingleFeatureKey container) {
        cache.put(id, Optional.of(container));
        super.containerFor(id, container);
    }

    @Override
    public void unsetContainer(Id id) {
        cache.put(id, Optional.empty());
        super.unsetContainer(id);
    }
}
