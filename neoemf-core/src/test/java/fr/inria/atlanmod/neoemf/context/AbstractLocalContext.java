/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.util.UriFactory;

import org.eclipse.emf.common.util.URI;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link AbstractContext} for testing local database.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractLocalContext extends AbstractContext {

    @Nonnull
    @Override
    public URI createUri(URI uri) {
        return UriFactory.forScheme(uriScheme()).createLocalUri(uri);
    }

    @Nonnull
    @Override
    public URI createUri(File file) {
        return UriFactory.forScheme(uriScheme()).createLocalUri(file);
    }
}
