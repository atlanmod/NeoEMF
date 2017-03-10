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

import fr.inria.atlanmod.neoemf.util.PersistenceURI;

import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * A specific subclass of {@link PersistenceURI} that creates MapDB specific resource {@link URI}s from a {@link File}
 * descriptor or an existing {@link URI}.
 * <p>
 * The class defines a HBase specific {@link URI} scheme that is used to register {@link
 * fr.inria.atlanmod.neoemf.data.hbase.HBaseBackendFactory} in {@link fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry}
 * and configure the {@code protocol-to-factory} map of an existing {@link org.eclipse.emf.ecore.resource.ResourceSet}
 * with a {@link fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory}.
 *
 * @see fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry
 * @see fr.inria.atlanmod.neoemf.data.hbase.HBaseBackendFactory
 * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
 */
@ParametersAreNonnullByDefault
public class HBaseURI extends PersistenceURI {

    /**
     * The scheme associated to the URI. This scheme is used to register {@link fr.inria.atlanmod.neoemf.data.hbase.HBaseBackendFactory}
     * and provide a {@link fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory} to an existing {@link
     * org.eclipse.emf.ecore.resource.ResourceSet}.
     *
     * @see fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry
     * @see fr.inria.atlanmod.neoemf.data.hbase.HBaseBackendFactory
     * @see fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory
     */
    @Nonnull
    public static final String SCHEME = "neo-hbase";

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    protected HBaseURI() {
        super();
    }

    /**
     * Creates a new {@code HBaseURI} from the given {@code uri}.
     * <p>
     * This method checks that the scheme of the provided {@code uri} can be used to create a new {@code HBaseURI}.
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
     * @see #createHierarchicalURI(String, int, URI)
     */
    @Nonnull
    public static URI createURI(URI uri) {
        checkNotNull(uri);

        if (Objects.equals(PersistenceURI.FILE_SCHEME, uri.scheme())) {
            return createFileURI(uri);
        }
        else if (Objects.equals(SCHEME, uri.scheme())) {
            return PersistenceURI.createURI(uri);
        }

        throw new IllegalArgumentException(MessageFormat.format("Can not create {0} from the URI scheme {1}", HBaseURI.class.getSimpleName(), uri.scheme()));
    }

    /**
     * Creates a new {@code HBaseURI} from the given {@link File} descriptor.
     *
     * @param file the {@link File} to build a {@link URI} from
     *
     * @return the created {@link URI}
     *
     * @throws NullPointerException if the {@code file} is {@code null}
     */
    @Nonnull
    public static URI createFileURI(File file) {
        return createFileURI(checkNotNull(file), SCHEME);
    }

    /**
     * Creates a new {@code HBaseURI} from the given {@code uri} by checking the referenced file exists on the file
     * system.
     *
     * @param uri the base {@link URI}
     *
     * @return the created {@link URI}
     *
     * @throws NullPointerException if the {@code uri} is {@code null}
     */
    @Nonnull
    public static URI createFileURI(URI uri) {
        checkNotNull(uri);

        return createFileURI(new File(uri.toFileString()));
    }

    /**
     * Creates a new {@code HBaseURI} from the {@code host}, {@code port}, and {@code modelURI} by creating a
     * hierarchical {@link URI} that references the distant model resource.
     *
     * @param host     the address of the HBase server (use {@code localhost} if HBase is running locally)
     * @param port     the port of the HBase server
     * @param modelURI a {@link URI} identifying the model in the database
     *
     * @return the created {@link URI}
     *
     * @throws NullPointerException if any of the parameters is {@code null}
     */
    @Nonnull
    public static URI createHierarchicalURI(String host, int port, URI modelURI) {
        checkNotNull(host);
        checkNotNull(port);
        checkNotNull(modelURI);
        return URI.createHierarchicalURI(SCHEME, host + ':' + port, null, modelURI.segments(), null, null);
    }

    /**
     * Format the given {@code uri} by removing HBase reserved characters.
     *
     * @param uri the {@link URI} to format
     *
     * @return the formatted {@link URI}
     */
    @Nonnull
    public static String format(URI uri) {
        return checkNotNull(uri).segmentsList().stream()
                .map(s -> s.replaceAll("-", "_"))
                .collect(Collectors.joining("_"));
    }
}