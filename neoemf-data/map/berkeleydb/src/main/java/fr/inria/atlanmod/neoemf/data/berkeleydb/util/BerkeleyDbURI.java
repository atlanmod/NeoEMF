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

package fr.inria.atlanmod.neoemf.data.berkeleydb.util;

import fr.inria.atlanmod.neoemf.util.PersistenceURI;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.text.MessageFormat;
import java.util.Objects;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * ???
 */
public class BerkeleyDbURI extends PersistenceURI {

    @Nonnull
    public static final String SCHEME = "neo-berkeleydb";

    /**
     * Constructs a new {@code BerkeleyDbURI} from the given {@code internalURI}.
     *
     * @note This constructor is protected to avoid wrong {@link URI} instantiations. Use {@link #createURI(URI)} or
     * {@link #createFileURI(File)} instead.
     *
     * @param internalURI the base {@code URI}
     */
    protected BerkeleyDbURI(@Nonnull URI internalURI) {
        super(internalURI);
    }

    /**
     * Creates a new {@code BerkeleyDbURI} from the given {@code uri}.
     * <p>
     * This method checks that the scheme of the provided {@code uri} can be used to create a new {@code BerkeleyDbURI}.
     *
     * @param uri the base {@code URI}
     *
     * @return the created {@code URI}
     *
     * @throws NullPointerException if the {@code uri} is {@code null}
     * @throws IllegalArgumentException if the scheme of the provided {@code uri} is not {@link #SCHEME} or {@link #FILE_SCHEME}
     *
     * @see #createFileURI(File)
     */
    @Nonnull
    public static URI createURI(@Nonnull URI uri) {
        checkNotNull(uri);
        if (Objects.equals(PersistenceURI.FILE_SCHEME, uri.scheme())) {
            return createFileURI(FileUtils.getFile(uri.toFileString()));
        }
        else if (Objects.equals(SCHEME, uri.scheme())) {
            return PersistenceURI.createURI(uri);
        }
        else {
            throw new IllegalArgumentException(MessageFormat.format("Can not create BerkeleyDbURI from the URI scheme {0}", uri.scheme()));
        }
    }

    /**
     * Creates a new {@code BerkeleyDbURI} from the given {@link File} descriptor.
     *
     * @param file the {@link File} to build a {@code URI} from
     * @return the created {@code URI}
     * @throws NullPointerException if the {@code file} is {@code null}
     */
    @Nonnull
    public static URI createFileURI(@Nonnull File file) {
        checkNotNull(file);
        return PersistenceURI.createFileURI(file, SCHEME);
    }
}
