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

package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptions;
import fr.inria.atlanmod.neoemf.option.PersistenceOptions;
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
     * Returns the name of this context.
     *
     * @return the name
     */
    String name();

    /**
     * Returns the {@link BackendFactory} used by this context.
     *
     * @return the factory
     */
    BackendFactory factory();

    /**
     * Returns the {@link PersistenceOptions} used by this context.
     *
     * @return a new options builder
     *
     * @see PersistenceOptions
     */
    AbstractPersistenceOptions<?> optionsBuilder();

    /**
     * Returns the {@link URI} scheme used by this context.
     *
     * @return the {@link URI} scheme
     */
    String uriScheme();

    /**
     * Creates a new {@link URI} from the given {@code uri}, according to this context.
     *
     * @param uri the base {@link URI}
     *
     * @return the created {@link URI}
     *
     * @see PersistenceURI#createURI(URI)
     */
    URI createUri(URI uri);

    /**
     * Creates a new {@link URI} from the given {@code file}, according to this context.
     *
     * @param file the {@link File} to build a {@link URI} from
     *
     * @return the created {@link URI}
     *
     * @see PersistenceURI#createFileURI(File, String)
     */
    URI createUri(File file);

    /**
     * Creates a new persistent resource from the given {@code ePackage} on the given {@code file}.
     *
     * @param ePackage the {@link EPackage} associated to the resource
     * @param file     the file from which to load/save data
     *
     * @return a new {@link PersistentResource}
     *
     * @throws IOException if an I/O error occurs
     * @see ContextualResourceBuilder
     */
    default PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException {
        return new ContextualResourceBuilder(this, ePackage).persistent().file(file).createResource();
    }

    /**
     * Creates a new transient resource from the given {@code ePackage} on the given {@code file}.
     *
     * @param ePackage the {@link EPackage} associated to the resource
     * @param file     the file from which to load/save data
     *
     * @return a new {@link PersistentResource}
     *
     * @throws IOException if an I/O error occurs
     * @see ContextualResourceBuilder
     */
    default PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException {
        if (!factory().supportsTransient()) {
            return createPersistentResource(ePackage, file);
        }
        else {
            return new ContextualResourceBuilder(this, ePackage).file(file).createResource();
        }
    }

    /**
     * Loads an existing resource from the given {@code ePackage} on the given {@code file}.
     *
     * @param ePackage the {@link EPackage} associated to the resource
     * @param file     the file from which to load/save data
     *
     * @return a new {@link PersistentResource}
     *
     * @throws IOException if an I/O error occurs
     * @see ContextualResourceBuilder
     */
    default PersistentResource loadResource(EPackage ePackage, File file) throws IOException {
        return new ContextualResourceBuilder(this, ePackage).file(file).loadResource();
    }

    /**
     * Creates a new {@link Backend} on the given {@code uri}.
     *
     * @param file the URI of the backend
     *
     * @return a new {@link Backend}
     *
     * @throws IOException if an I/O error occurs
     * @see ContextualResourceBuilder
     */
    default Backend createBackend(File file) throws IOException {
        return new ContextualResourceBuilder(this, null).file(file).createPersistentBackend();
    }
}
