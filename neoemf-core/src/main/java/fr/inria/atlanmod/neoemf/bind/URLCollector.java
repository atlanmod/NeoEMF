/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.bind;

import java.net.URL;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object that analyzes and collects {@link URL}s.
 */
@ParametersAreNonnullByDefault
@FunctionalInterface
interface URLCollector {

    /**
     * Returns a set of collected {@link URL}s.
     *
     * @return a set of {@link URL}
     */
    @Nonnull
    Set<URL> getUrls();
}
