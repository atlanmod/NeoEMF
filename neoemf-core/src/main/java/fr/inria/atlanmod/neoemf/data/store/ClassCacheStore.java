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

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that caches {@link ClassBean}s.
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
    @SuppressWarnings("MethodDoesntCallSuperMethod")
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
