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

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.hbase.HBasePersistenceBackendFactory;
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
 * A specific subclass of {@link PersistenceURI} that creates MapDB specific resource {@link URI}s from a {@link File}
 * descriptor or an existing {@link URI}.
 * <p>
 * The class defines a HBase specific {@link URI} scheme that is used to register {@link HBasePersistenceBackendFactory}
 * in {@link PersistenceBackendFactoryRegistry} and configure the {@code protocol-to-factory} map of an existing {@link
 * ResourceSet} with a {@link PersistentResourceFactory}.
 *
 * @see PersistenceBackendFactoryRegistry
 * @see HBasePersistenceBackendFactory
 * @see PersistentResourceFactory
 */
public class HBaseURI extends PersistenceURI {

    /**
     * The scheme associated to the URI. This scheme is used to register {@link HBasePersistenceBackendFactory}
     * and provide a {@link PersistentResourceFactory} to an existing {@link ResourceSet}.
     *
     * @see PersistenceBackendFactoryRegistry
     * @see HBasePersistenceBackendFactory
     * @see PersistentResourceFactory
     */
    @Nonnull
    public static final String SCHEME = "neo-hbase";

    /**
     * Constructs a new {@code HBaseURI} from the given {@code internalURI}.
     *
     * @param internalURI the base {@code URI}
     *
     * @note This constructor is protected to avoid wrong {@link URI} instantiations. Use {@link #createURI(URI)},
     * {@link #createFileURI(File)}, {@link #createFileURI(URI)} or {@link #createHierarchicalURI(String, String, URI)}
     * instead.
     */
    protected HBaseURI(@Nonnull URI internalURI) {
        super(internalURI);
    }

    /**
     * Creates a new {@code HBaseURI} from the given {@code uri}.
     * <p>
     * This method checks that the scheme of the provided {@code uri} can be used to create a new {@code HBaseURI}.
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
     * @see #createHierarchicalURI(String, String, URI)
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
        throw new IllegalArgumentException(MessageFormat.format("Can not create {0} from the URI scheme {1}", HBaseURI.class.getSimpleName(), uri.scheme()));
    }

    /**
     * Creates a new {@code HBaseURI} from the given {@link File} descriptor.
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
     * Creates a new {@code HBaseURI} from the given {@code uri} by checking the referenced file exists on the file
     * system.
     *
     * @param uri the base {@code URI}
     *
     * @return the created {@code URI}
     *
     * @throws NullPointerException if the {@code uri} is {@code null}
     */
    @Nonnull
    public static URI createFileURI(@Nonnull URI uri) {
        checkNotNull(uri);
        return createFileURI(FileUtils.getFile(uri.toFileString()));
    }

    /**
     * Creates a new {@code HBaseURI} from the {@code host}, {@code port}, and {@code modelURI} by creating a
     * hierarchical {@link URI} that references the distant model resource
     *
     * @param host     the address of the HBase server (use {@code localhost} if HBase is running locally)
     * @param port     the port of the HBase server
     * @param modelURI a {@link URI} identifying the model in the database
     *
     * @return the created {@code URI}
     *
     * @throws NullPointerException if any of the parameters is {@code null}
     */
    @Nonnull
    public static URI createHierarchicalURI(@Nonnull String host, @Nonnull String port, @Nonnull URI modelURI) {
        checkNotNull(host);
        checkNotNull(port);
        checkNotNull(modelURI);
        return URI.createHierarchicalURI(SCHEME, host + ":" + port, null, modelURI.segments(), null, null);
    }

    /**
     * Format the given {@code URI} by removing HBase reserved characters.
     *
     * @param uri the {@code URI} to format
     *
     * @return the formatted {@code URI}
     */
    @Nonnull
    public static String format(@Nonnull URI uri) {
        checkNotNull(uri);
        StringBuilder strBld = new StringBuilder();
        for (int i = 0; i < uri.segmentCount(); i++) {
            strBld.append(uri.segment(i).replaceAll("-", "_"));
            if (i != uri.segmentCount() - 1) {
                strBld.append("_");
            }
        }
        return strBld.toString();
    }
}