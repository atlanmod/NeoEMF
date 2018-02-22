/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link Store} wrapper that caches containers.
 */
@ParametersAreNonnullByDefault
public class ContainerCachingStore extends AbstractCachingStore<Id, Optional<SingleFeatureBean>> {

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        return cache.get(id, super::containerOf);
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        Optional<SingleFeatureBean> currentContainer = cache.get(id);
        if (nonNull(currentContainer) && currentContainer.filter(container::equals).isPresent()) {
            return;
        }

        cache.put(id, Optional.of(container));
        super.containerFor(id, container);
    }

    @Override
    public void removeContainer(Id id) {
        cache.put(id, Optional.empty());
        super.removeContainer(id);
    }
}
