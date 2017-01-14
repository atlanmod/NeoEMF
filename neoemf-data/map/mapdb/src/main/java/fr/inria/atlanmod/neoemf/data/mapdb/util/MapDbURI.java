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

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.util.PersistenceURI;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.File;
import java.text.MessageFormat;
import java.util.Objects;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A specific {@link PersistenceURI} that creates MapDB specific resource {@link URI}s from a {@link File} descriptor or
 * an existing {@link URI}.
 * <p>
 * The class defines a MapDB specific {@link URI} scheme that is used to register {@link MapDbPersistenceBackendFactory}
 * in {@link PersistenceBackendFactoryRegistry} and configure the {@code protocol-to-factory} map of an existing {@link
 * ResourceSet} with a {@link PersistentResourceFactory}.
 *
 * @see PersistenceBackendFactoryRegistry
 * @see MapDbPersistenceBackendFactory
 * @see PersistentResourceFactory
 */
public class MapDbURI extends PersistenceURI {

    /**
     * The scheme associated to the URI. This scheme is used to register {@link MapDbPersistenceBackendFactory}
     * and provide a {@link PersistentResourceFactory} to an existing {@link ResourceSet}.
     *
     * @see PersistenceBackendFactoryRegistry
     * @see MapDbPersistenceBackendFactory
     * @see PersistentResourceFactory
     */
    @Nonnull
    public static final String SCHEME = "neo-mapdb";

    /**
     * Constructs a new {@code MapDbURI} from the given {@code internalURI}.
     *
     * @param internalURI the base {@code URI}
     *
     * @note This constructor is protected to avoid wrong {@link URI} instantiations. Use {@link #createURI(URI)},
     * {@link #createFileURI(File)}, or {@link #createFileURI(URI)} instead.
     */
    protected MapDbURI(@Nonnull URI internalURI) {
        super(internalURI);
    }

    /**
     * Create a new {@code MapDbURI} from the given {@code uri}. This method checks that the scheme of the provided
     * {@code uri} can be used to create a new {@code MapDbURI}.
     *
     * @param uri the base {@code URI}
     *
     * @return the created {@code URI}
     *
     * @throws NullPointerException     if the {@code uri} is {@code null}
     * @throws IllegalArgumentException if the scheme of the provided {@code uri} is not {@link #SCHEME} or {@link
     *                                  #FILE_SCHEME}
     * @see #createFileURI(File)
     * @see #createFileURI(URI)
     */
    @Nonnull
    public static URI createURI(@Nonnull URI uri) {
        checkNotNull(uri);
        if (Objects.equals(PersistenceURI.FILE_SCHEME, uri.scheme())) {
            return createFileURI(uri);
        }
        else if (Objects.equals(SCHEME, uri.scheme())) {
            return PersistenceURI.createURI(uri);
        }
        throw new IllegalArgumentException(MessageFormat.format("Can not create {0} from the URI scheme {1}", MapDbURI.class.getSimpleName(), uri.scheme()));
    }

    /**
     * Creates a new {@code MapDbURI} from the given {@link File} descriptor.
     *
     * @param file the {@link File} to build a {@code URI} from
     *
     * @return the created {@code URI}
     *
     * @throws NullPointerException if the {@code file} is {@code null}
     */
    @Nonnull
    public static URI createFileURI(@Nonnull File file) {
        checkNotNull(file);
        return PersistenceURI.createFileURI(file, SCHEME);
    }

    /**
     * Creates a new {@code MapDbURI} from the given {@code uri} by checking the referenced file exists on the file
     * system.
     *
     * @param uri the base {@code URI}
     *
     * @return the created {@code URI}
     *
     * @throws NullPointerException if the {@code uri} is {@code null} or if the file referenced by the {@code uri}
     *                              cannot be found
     */
    @Nonnull
    public static URI createFileURI(@Nonnull URI uri) {
        checkNotNull(uri);
        return createFileURI(FileUtils.getFile(uri.toFileString()));
    }
}
