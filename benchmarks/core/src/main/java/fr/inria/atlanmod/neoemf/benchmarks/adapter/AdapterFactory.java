/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import org.atlanmod.commons.Throwables;
import org.atlanmod.commons.collect.MoreIterables;

import java.util.ServiceLoader;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A factory that creates {@link Adapter} instances.
 */
@ParametersAreNonnullByDefault
public final class AdapterFactory {

    private AdapterFactory() {
        throw Throwables.notInstantiableClass(getClass());
    }

    /**
     * Retrieves and creates a new {@link Adapter} for the specified {@code name}.
     *
     * @param name the name of the adapter
     */
    @Nonnull
    public static Adapter createAdapter(String name) {
        return MoreIterables.stream(ServiceLoader.load(Adapter.class))
                .filter(a -> a.getClass().getAnnotation(AdapterName.class).value().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No adapter named '%s' is registered", name)));
    }
}
