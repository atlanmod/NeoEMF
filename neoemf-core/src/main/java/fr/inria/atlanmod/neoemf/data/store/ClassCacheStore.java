/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that caches {@link fr.inria.atlanmod.neoemf.data.bean.ClassBean}s.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public class ClassCacheStore extends AbstractCacheStore<Id, Optional<ClassBean>> {

    /**
     * Constructs a new {@code ClassCacheStore} on the given {@code store}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    protected ClassCacheStore(Store store) {
        super(store);
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        return cache.get(id, super::metaClassOf);
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        if (cache.contains(id)) {
            return false;
        }

        cache.put(id, Optional.of(metaClass));
        return super.metaClassFor(id, metaClass);
    }
}
