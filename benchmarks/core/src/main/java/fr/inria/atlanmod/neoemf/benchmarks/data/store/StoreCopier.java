/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.data.store;

import org.eclipse.emf.common.util.URI;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface StoreCopier {

    /**
     * Copies all the contents from the {@code source}.
     *
     * @param uri the URI of the datastore to copy
     *
     * @return the URI the created datastore
     */
    @Nonnull
    URI copy(URI uri) throws IOException;
}
