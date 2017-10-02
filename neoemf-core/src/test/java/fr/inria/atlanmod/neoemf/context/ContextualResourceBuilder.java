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

import fr.inria.atlanmod.commons.log.Level;
import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.File;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A configuration that manages the assembly and the construction of {@link PersistentResource}.
 */
@ParametersAreNonnullByDefault
final class ContextualResourceBuilder {

    /**
     * The {@link Context} of this helper.
     */
    @Nonnull
    private final Context context;

    /**
     * The {@link URI} of the resource.
     */
    private URI uri;

    /**
     * Whether the {@link PersistentResource} is persistent.
     */
    private boolean isPersistent;

    /**
     * Constructs a new {@code ContextualResourceBuilder} with the given {@code ePackage}.
     *
     * @param context the context of this builder
     *
     * @see EPackage.Registry
     */
    public ContextualResourceBuilder(Context context) {
        this.context = checkNotNull(context);
        reset();
    }

    /**
     * Resets this builder.
     */
    private void reset() {
        isPersistent = false;
        uri = null;
    }

    /**
     * Returns the default configuration of this helper.
     *
     * @return a new configuration
     */
    @Nonnull
    private Config defaultConfig() {
        return context.config()
                .log(Level.DEBUG)
                .autoSave(100)
                .cacheSizes()
                .cacheFeatures()
                .cacheContainers()
                .cacheMetaClasses();
    }

    /**
     * Defines the {@link URI} of the created resource from another.
     *
     * @param uri the base {@link URI}
     *
     * @return this builder (for chaining)
     */
    @Nonnull
    public ContextualResourceBuilder uri(URI uri) {
        checkNotNull(uri);
        this.uri = context.createUri(uri);
        return this;
    }

    /**
     * Defines the {@link URI} of the created resource from a file.
     *
     * @param file the {@link File} associated to the created resource
     *
     * @return this builder (for chaining)
     */
    @Nonnull
    public ContextualResourceBuilder file(File file) {
        checkNotNull(file);
        this.uri = context.createUri(file);
        return this;
    }

    /**
     * Defines the created resource as persistent.
     *
     * @return this builder (for chaining)
     */
    @Nonnull
    public final ContextualResourceBuilder persistent() {
        isPersistent = true;
        return this;
    }

    /**
     * Creates a {@link PersistentResource} according to the specified options.
     *
     * @return a new {@link PersistentResource}
     *
     * @see Context#config()
     * @see #defaultConfig()
     */
    @Nonnull
    public PersistentResource createResource() throws IOException {
        PersistentResource resource = PersistentResource.class.cast(new ResourceSetImpl().createResource(uri));
        if (isPersistent) {
            resource.save(defaultConfig().toMap());
        }

        reset();

        return resource;
    }

    /**
     * Loads an existing {@link PersistentResource} according to the specified options.
     *
     * @return a new {@link PersistentResource}
     *
     * @see Context#config()
     * @see #defaultConfig()
     */
    @Nonnull
    public PersistentResource loadResource() throws IOException {
        PersistentResource resource = PersistentResource.class.cast(new ResourceSetImpl().createResource(uri));
        resource.load(defaultConfig().toMap());

        reset();

        return resource;
    }

    /**
     * Creates a new {@link DataMapper} according to the specified options.
     *
     * @return a new {@link DataMapper}
     *
     * @see Context#config()
     * @see #defaultConfig()
     */
    @Nonnull
    public DataMapper createMapper() {
        Backend backend = context.factory().createPersistentBackend(uri, defaultConfig());
        return StoreFactory.getInstance().createStore(backend, defaultConfig());
    }
}
