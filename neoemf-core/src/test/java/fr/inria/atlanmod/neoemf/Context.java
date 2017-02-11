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

package fr.inria.atlanmod.neoemf;

import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.PersistenceURI;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

/**
 * A utility class representing a test-case context.
 * <p>
 * All methods are a proxy to their associated method; they do not provide new functionalities.
 */
public interface Context {

    /**
     * Returns the name of this {@code Context}.
     *
     * @return the name of this context
     */
    String name();

    /**
     * Returns the {@link URI} scheme used by this {@code Context}.
     *
     * @return the {@link URI} scheme
     *
     * @see PersistenceURI#scheme()
     */
    String uriScheme();

    /**
     * Creates a new {@link URI} from the given {@code uri}, according to this {@code Context}.
     *
     * @param uri the base {@link URI}
     *
     * @return the created {@link URI}
     *
     * @see PersistenceURI#createURI(URI)
     */
    URI createURI(URI uri);

    /**
     * Creates a new {@link URI} from the given {@code file}, according to this {@code Context}.
     *
     * @param file the {@link File} to build a {@link URI} from
     *
     * @return the created {@link URI}
     *
     * @see PersistenceURI#createFileURI(File, String)
     */
    URI createFileURI(File file);

    /**
     * Creates a new persistent resource from the given {@code ePackage} on the given {@code file}.
     *
     * @param ePackage the {@link EPackage} associated to the resource
     * @param file     the file from which to load/save data
     *
     * @return a new {@link PersistentResource}
     *
     * @throws IOException if an I/O error occurs
     * @see TestHelper
     */
    PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException;

    /**
     * Creates a new transient resource from the given {@code ePackage} on the given {@code file}.
     *
     * @param ePackage the {@link EPackage} associated to the resource
     * @param file     the file from which to load/save data
     *
     * @return a new {@link PersistentResource}
     *
     * @throws IOException if an I/O error occurs
     * @see TestHelper
     */
    PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException;

    /**
     * Loads an existing resource from the given {@code ePackage} on the given {@code file}.
     *
     * @param ePackage the {@link EPackage} associated to the resource
     * @param file     the file from which to load/save data
     *
     * @return a new {@link PersistentResource}
     *
     * @throws IOException if an I/O error occurs
     * @see TestHelper
     */
    PersistentResource loadResource(EPackage ePackage, File file) throws IOException;

    /**
     * Creates a new {@link PersistenceBackend} on the given {@code uri}.
     *
     * @param file the URI of the backend
     *
     * @return a new {@link PersistenceBackend}
     *
     * @throws IOException if an I/O error occurs
     * @see TestHelper
     */
    PersistenceBackend createBackend(File file) throws IOException;

    /**
     * Returns the {@link PersistenceBackendFactory} used by this {@code Context}
     *
     * @return the {@link PersistenceBackendFactory}
     */
    PersistenceBackendFactory persistenceBackendFactory();
}
