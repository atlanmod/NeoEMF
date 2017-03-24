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

package fr.inria.atlanmod.neoemf.data.hbase.util;

import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.hbase.HBaseBackendFactory;
import fr.inria.atlanmod.neoemf.util.URIBuilder;

import org.eclipse.emf.common.util.URI;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link URIBuilder} that creates HBase specific resource {@link URI}s.
 *
 * @see BackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.data.hbase.HBaseBackendFactory
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
 */
@ParametersAreNonnullByDefault
public class HBaseURI extends URIBuilder {

    /**
     * The scheme associated to the URI.
     */
    @Nonnull
    public static final String SCHEME = formatScheme(HBaseBackendFactory.getInstance());

    /**
     * Constructs a new {@code HBaseURI}.
     */
    private HBaseURI() {
        super();
    }

    /**
     * Creates a new {@code HBaseURI} with the pre-configured scheme.
     *
     * @return a new builder
     */
    @Nonnull
    public static URIBuilder newBuilder() {
        return new HBaseURI().withScheme(SCHEME);
    }

    @Nonnull
    @Override
    public URI fromUri(URI uri) {
        throw new UnsupportedOperationException("HBaseURI does not support file-based URIs");
    }

    @Nonnull
    @Override
    public URI fromFile(File file) {
        throw new UnsupportedOperationException("HBaseURI does not support file-based URIs");
    }
}