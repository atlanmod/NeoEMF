/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util;

import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link URI} wrapper that creates specific resource {@link URI}s from a {@link File} descriptor or an existing
 * {@link URI}. All methods are delegated to the internal {@link URI}.
 */
@ParametersAreNonnullByDefault
class FileBasedUri extends UriDecorator {

    /**
     * The {@link URI} scheme corresponding to a file.
     */
    @Nonnull
    static final String SCHEME = "file";

    /**
     * Constructs a new {@code FileBasedUri} on the specified {@code baseUri}.
     *
     * @param base the base {@link URI}
     */
    FileBasedUri(URI base) {
        super(base);
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public String toFileString() {
        return URI.createHierarchicalURI(
                SCHEME,
                base.authority(),
                base.device(),
                base.segments(),
                base.query(),
                base.fragment()
        ).toFileString();
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (nonNull(o) && o instanceof URI) {
            return Objects.equals(this.toString(), o.toString());
        }

        return super.equals(o);
    }
}
