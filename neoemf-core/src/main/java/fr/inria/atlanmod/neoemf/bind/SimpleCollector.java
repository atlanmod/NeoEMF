/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.bind;

import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An {@link URLCollector} that delegate its analysis.
 */
@ParametersAreNonnullByDefault
public class SimpleCollector implements URLCollector {

    /**
     * The supplier of {@link URL}s.
     */
    @Nonnull
    private final Supplier<Collection<URL>> urlSupplier;

    /**
     * Constructs a new {@code SimpleCollector}.
     *
     * @param urlSupplier the supplier of {@link URL}s
     */
    public SimpleCollector(Supplier<Collection<URL>> urlSupplier) {
        this.urlSupplier = checkNotNull(urlSupplier);
    }

    @Nonnull
    @Override
    public Set<URL> get() {
        return new HashSet<>(urlSupplier.get());
    }
}
