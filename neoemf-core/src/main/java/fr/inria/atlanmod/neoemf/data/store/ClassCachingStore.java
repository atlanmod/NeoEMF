/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that caches {@link fr.inria.atlanmod.neoemf.data.bean.ClassBean}s.
 */
@ParametersAreNonnullByDefault
public class ClassCachingStore extends AbstractCachingStore<Id, Optional<ClassBean>> {

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
