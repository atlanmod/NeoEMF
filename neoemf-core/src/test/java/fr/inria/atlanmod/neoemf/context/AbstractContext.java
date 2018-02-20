/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.bind.BindingEngine;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link Context}.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractContext implements Context {

    @Nonnull
    @Override
    public String uriScheme() {
        return BindingEngine.schemeOf(factory().getClass());
    }

    @Override
    public boolean isPersistent() {
        return true;
    }

    @Nonnull
    @Override
    public URI createUri(URI uri) {
        return UriBuilder.forScheme(uriScheme()).fromUri(uri);
    }

    @Nonnull
    @Override
    public URI createUri(File file) {
        return UriBuilder.forScheme(uriScheme()).fromFile(file);
    }

    @Nonnull
    @Override
    public PersistentResource createPersistentResource(File file) throws IOException {
        return new ContextualResourceBuilder(this).persistent().file(file).createResource();
    }

    @Nonnull
    @Override
    public PersistentResource createTransientResource(File file) throws IOException {
        return factory().supportsTransient()
                ? new ContextualResourceBuilder(this).file(file).createResource()
                : createPersistentResource(file);
    }

    @Nonnull
    @Override
    public PersistentResource loadResource(File file) throws IOException {
        if (!isPersistent()) {
            throw new UnsupportedOperationException(String.format("%s cannot load an existing resource", getClass().getSimpleName()));
        }

        return new ContextualResourceBuilder(this).file(file).loadResource();
    }

    @Nonnull
    @Override
    public DataMapper createMapper(File file) {
        return new ContextualResourceBuilder(this).file(file).createMapper();
    }

    @Nonnull
    @Override
    public String toString() {
        return name();
    }
}
