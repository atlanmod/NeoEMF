/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.util.UriFactory;

import org.eclipse.emf.common.util.URI;

import java.io.File;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link AbstractContext} for testing a remote database.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractRemoteContext extends AbstractContext {

    /**
     * Returns the host of the testing database.
     *
     * @return the host
     */
    @Nonnull
    protected abstract String getHost();

    /**
     * Returns the port of the testing database.
     *
     * @return the port
     */
    @Nonnegative
    protected abstract int getPort();

    @Nonnull
    @Override
    public URI createUri(URI uri) {
        return UriFactory.forScheme(uriScheme()).createRemoteUri(getHost(), getPort(), uri);
    }

    @Nonnull
    @Override
    public URI createUri(File file) {
        return UriFactory.forScheme(uriScheme()).createRemoteUri(getHost(), getPort(), file.getName());
    }
}
