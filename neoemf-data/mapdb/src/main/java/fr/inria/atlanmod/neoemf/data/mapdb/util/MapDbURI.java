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

package fr.inria.atlanmod.neoemf.data.mapdb.util;

import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory;
import fr.inria.atlanmod.neoemf.util.PersistenceURI;

import org.eclipse.emf.common.util.URI;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * A specific {@link PersistenceURI} that creates MapDB specific resource {@link URI}s from a {@link File} descriptor or
 * an existing {@link URI}.
 * <p>
 * The class defines a MapDB specific {@link URI} scheme that is used to register {@link
 * fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory} in {@link BackendFactoryRegistry}
 * and configure the {@code protocol-to-factory} map of an existing {@link org.eclipse.emf.ecore.resource.ResourceSet}
 * with a {@link fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory}.
 *
 * @see BackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
 */
@ParametersAreNonnullByDefault
public class MapDbURI extends PersistenceURI {

    /**
     * The scheme associated to the {@link URI}. This scheme is used to register {@link
     * fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory} and provide a {@link
     * fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory} to an existing {@link
     * org.eclipse.emf.ecore.resource.ResourceSet}.
     *
     * @see BackendFactoryRegistry
     * @see fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory
     * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
     */
    @Nonnull
    public static final String SCHEME = formatScheme(MapDbBackendFactory.getInstance());

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private MapDbURI() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Create a new {@code MapDbURI} from the given {@code uri}. This method checks that the scheme of the provided
     * {@code uri} can be used to create a new {@code MapDbURI}.
     *
     * @param uri the base {@link URI}
     *
     * @return the created {@link URI}
     *
     * @throws NullPointerException     if the {@code uri} is {@code null}
     * @throws IllegalArgumentException if the scheme of the provided {@code uri} is not {@link #SCHEME} or {@link
     *                                  #FILE_SCHEME}
     * @see #createFileURI(File)
     * @see #createFileURI(URI)
     */
    @Nonnull
    public static URI createURI(URI uri) {
        return UriBuilder.newBuilder(SCHEME).fromUri(uri);
    }

    /**
     * Creates a new {@code MapDbURI} from the given {@link File} descriptor.
     *
     * @param file the {@link File} to build a {@link URI} from
     *
     * @return the created {@link URI}
     *
     * @throws NullPointerException if the {@code file} is {@code null}
     */
    @Nonnull
    public static URI createFileURI(File file) {
        return UriBuilder.newBuilder(SCHEME).fromFile(file);
    }

    /**
     * Creates a new {@code MapDbURI} from the given {@code uri} by checking the referenced file exists on the file
     * system.
     *
     * @param uri the base {@link URI}
     *
     * @return the created {@link URI}
     *
     * @throws NullPointerException if the {@code uri} is {@code null} or if the file referenced by the {@code uri}
     *                              cannot be found
     */
    @Nonnull
    public static URI createFileURI(URI uri) {
        return createFileURI(new File(checkNotNull(uri).toFileString()));
    }
}
