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

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.text.MessageFormat;
import java.util.Objects;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

public class HBaseURI extends PersistenceURI {

    @Nonnull
    public static final String SCHEME = "neo-hbase";

    /**
     * Constructs a new {@code HBaseURI} from the given {@code internalURI}.
     * <p>
     * This constructor is protected to avoid wrong {@link URI} instantiations. Use {@link #createURI(URI)},
     * {@link #createFileURI(File)}, {@link #createFileURI(URI)} or {@link #createHierarchicalURI(String, String, URI)}
     * instead.
     *
     * @param internalURI the base {@code URI}
     */
    protected HBaseURI(@Nonnull URI internalURI) {
        super(internalURI);
    }

    /**
     *
     * @param uri
     * @return
     * @throws NullPointerException if the {@code uri} is {@code null}
     * @throws IllegalArgumentException if the scheme of the provided {@code uri} is not {@link #SCHEME} or
     *                                  {@link PersistenceURI#FILE_SCHEME}
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
     *
     * @param file
     * @return
     * @throws NullPointerException if the {@code file} is {@code null}
     */
    @Nonnull
    public static URI createFileURI(@Nonnull File file) {
        checkNotNull(file);
        return PersistenceURI.createFileURI(file, SCHEME);
    }

    /**
     *
     * @param uri
     * @return
     * @throws NullPointerException if the {@code uri} is {@code null}
     */
    @Nonnull
    public static URI createFileURI(@Nonnull URI uri) {
        checkNotNull(uri);
        return createFileURI(FileUtils.getFile(uri.toFileString()));
    }

    /**
     *
     * @param host
     * @param port
     * @param modelURI
     * @return
     * @throws NullPointerException if any of the parameters is {@code null}
     */
    @Nonnull
    public static URI createHierarchicalURI(@Nonnull String host, @Nonnull String port, @Nonnull URI modelURI) {
        checkNotNull(host);
        checkNotNull(port);
        checkNotNull(modelURI);
        return URI.createHierarchicalURI(SCHEME, host + ":" + port, null, modelURI.segments(), null, null);
    }

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