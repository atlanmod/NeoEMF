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

import fr.inria.atlanmod.common.annotation.VisibleForReflection;
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
public class ClassCachingStore extends AbstractCachingStore<Id, Optional<ClassBean>> {

    /**
     * Constructs a new {@code ClassCachingStore} on the given {@code store}.
     *
     * @param store the inner store
     */
    @VisibleForReflection
    protected ClassCachingStore(Store store) {
        super(store);
    }

    @Nonnull
    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public Optional<ClassBean> metaclassOf(Id id) {
        return cache.get(id, super::metaclassOf);
    }

    @Override
    public void metaclassFor(Id id, ClassBean metaclass) {
        cache.put(id, Optional.of(metaclass));
        super.metaclassFor(id, metaclass);
    }
}
