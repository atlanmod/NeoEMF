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

package fr.inria.atlanmod.neoemf.data.blueprints.util;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.util.PersistenceURI;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.File;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * A specific subclass of {@link PersistenceURI} that creates Blueprints specific resource {@link URI}s from
 * a {@link File} descriptor or an existing {@link URI}.
 * <p>
 * The class defines a Blueprints specific {@link URI} scheme that is used to register {@link BlueprintsPersistenceBackendFactory}
 * in {@link PersistenceBackendFactoryRegistry} and configure the {@code protocol to factory} map of an existing {@link ResourceSet}
 * with a {@link PersistentResourceFactory}.
 * 
 * @see PersistenceBackendFactoryRegistry
 * @see BlueprintsPersistenceBackendFactory
 * @see PersistentResourceFactory
 */
public class BlueprintsURI extends PersistenceURI {

    /**
     * The scheme associated to the URI. This scheme is used to register {@link BlueprintsPersistenceBackendFactory}
     * and provide a {@link PersistentResourceFactory} to an existing {@link ResourceSet}.
     * 
     * @see PersistenceBackendFactoryRegistry
     * @see BlueprintsPersistenceBackendFactory
     * @see PersistentResourceFactory
     */
    public static final String SCHEME = "neo-blueprints";

    /**
     * Creates a new {@link BlueprintsURI} from the given {@code hashCode} and {@code internalURI}. This constructor
     * is protected to avoid wrong {@link URI} instantiations. Use {@link BlueprintsURI#createURI(URI)}, {@link BlueprintsURI#createFileURI(File)}, or
     * {@link BlueprintsURI#createFileURI(URI)} instead.
     * @param hashCode the hash of the {@link URI}
     * @param internalURI the base {@link URI}
     */
    protected BlueprintsURI(int hashCode, URI internalURI) {
        super(hashCode, internalURI);
    }

    /**
     * Creates a new {@link BlueprintsURI} from the given {@code uri}. This method checks that the
     * scheme of the provided {@code uri} can be used to create a new {@link BlueprintsURI}. If not an
     * {@link IllegalArgumentException} is thrown.
     * @param uri the base {@link URI}
     * @return the created {@link BlueprintsURI}
     * @throws IllegalArgumentException if the scheme of the provided {@code uri} is not {@link BlueprintsURI#SCHEME} or {@link PersistenceURI#FILE_SCHEME} 
     * 
     * @see BlueprintsURI#createFileURI(File)
     * @see BlueprintsURI#createFileURI(URI)
     */
    public static URI createURI(URI uri) {
        if (Objects.equals(PersistenceURI.FILE_SCHEME, uri.scheme())) {
            return createFileURI(uri);
        }
        else if (Objects.equals(SCHEME, uri.scheme())) {
            return PersistenceURI.createURI(uri);
        }
        throw new IllegalArgumentException(MessageFormat.format("Can not create {0} from the URI scheme {1}", BlueprintsURI.class.getSimpleName(), uri.scheme()));
    }

    /**
     * Creates a new {@link BlueprintsURI} from the given {@link File} descriptor.
     * @param file the {@link File} to build a {@link URI} from
     * @return the created {@link BlueprintsURI}
     */
    public static URI createFileURI(File file) {
        return PersistenceURI.createFileURI(file, SCHEME);
    }

    /**
     * Creates a new {@link BlueprintsURI} from the given {@code uri} by checking the referenced file
     * exists on the file system. A {@link NullPointerException} is thrown if the file cannot be found.
     * @param uri the base {@link URI}
     * @return the created {@link BlueprintsURI}
     * @throws NullPointerException if the file referenced by the {@code uri} cannot be found
     */
    public static URI createFileURI(URI uri) {
        return createFileURI(FileUtils.getFile(uri.toFileString()));
    }
}
