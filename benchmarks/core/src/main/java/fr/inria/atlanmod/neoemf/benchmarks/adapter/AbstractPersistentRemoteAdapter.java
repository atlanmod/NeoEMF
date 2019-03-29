/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import org.eclipse.emf.common.util.URI;

import java.nio.file.Path;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link AbstractPersistentAdapter} connected to a remote {@link fr.inria.atlanmod.neoemf.data.Backend}.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractPersistentRemoteAdapter extends AbstractPersistentAdapter {

    /**
     * Returns the host of the remote server.
     *
     * @return the host
     */
    @Nonnull
    protected abstract String getHost();

    /**
     * Returns the port of the remote server.
     *
     * @return the port
     */
    @Nonnegative
    protected abstract int getPort();

    @Nonnull
    @Override
    public URI createUri(Path directory, String fileName) {
        return getUriFactory().createRemoteUri(getHost(), getPort(), fileName.replace('.', '-'));
    }
}
