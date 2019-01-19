/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.benchmarks.data.resource.Resources;
import fr.inria.atlanmod.neoemf.benchmarks.data.store.DirectStoreCreator;
import fr.inria.atlanmod.neoemf.benchmarks.data.store.FileStoreCopier;
import fr.inria.atlanmod.neoemf.benchmarks.data.store.HierarchicalStoreCopier;
import fr.inria.atlanmod.neoemf.benchmarks.data.store.StandardStoreCreator;
import fr.inria.atlanmod.neoemf.benchmarks.data.store.StoreCopier;
import fr.inria.atlanmod.neoemf.benchmarks.data.store.StoreCreator;
import fr.inria.atlanmod.neoemf.benchmarks.io.LocalWorkspace;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;

import org.atlanmod.commons.Throwables;
import org.atlanmod.commons.log.Log;
import org.atlanmod.commons.primitive.Strings;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;
import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * The abstract implementation of an {@link Adapter.Internal}.
 */
@ParametersAreNonnullByDefault
abstract class AbstractAdapter implements Adapter.Internal {

    /**
     * The category of this adapter.
     */
    @Nonnull
    private final String category;

    /**
     * The name of this adapter.
     */
    @Nonnull
    private final String name;

    /**
     * The class of the {@link EPackage} associated to this adapter.
     */
    @Nonnull
    private final Class<? extends EPackage> modelPackage;

    /**
     * Constructs a new {@code AbstractAdapter}.
     *
     * @param category     the category of this adapter
     * @param modelPackage the class of the {@link EPackage} associated to this adapter
     */
    protected AbstractAdapter(String category, Class<? extends EPackage> modelPackage) {
        this.category = checkNotNull(category);
        this.modelPackage = checkNotNull(modelPackage);

        final AdapterName annotation = getClass().getAnnotation(AdapterName.class);
        if (isNull(annotation)) {
            throw new IllegalStateException(String.format("This adapter is not annotated with %s", AdapterName.class.getSimpleName()));
        }

        this.name = annotation.value();
    }

    // region Adapter

    @Nonnull
    @Override
    public final EPackage initAndGetEPackage() {
        try {
            return (EPackage) modelPackage.getMethod("init").invoke(null);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw Throwables.shouldNeverHappen(e);
        }
    }

    @Nonnull
    @Override
    public File getOrCreateResource(String name) throws IOException {
        return Resources.getOrCreateResource(name, this);
    }

    @Nonnull
    @Override
    public URI getOrCreateStore(File resourceFile, ImmutableConfig config, boolean useDirectImport) throws IOException {
        final URI uri = createUri(LocalWorkspace.getStoreDirectory(), Resources.getFileName(resourceFile, this, false));
        if (!exists(uri)) {
            createStore(resourceFile, uri, config, useDirectImport);
        }
        return uri;
    }

    @Nonnull
    @Override
    public URI createTempStore(File resourceFile, ImmutableConfig config, boolean useDirectImport) throws IOException {
        final URI uri = createUri(LocalWorkspace.newTempDirectory(), Resources.getFileName(resourceFile, this, true));
        createStore(resourceFile, uri, config, useDirectImport);
        return uri;
    }

    @Nonnull
    @Override
    public Resource load(URI uri, ImmutableConfig config) throws IOException {
        initAndGetEPackage();

        final Resource resource = create(uri);
        resource.load(getOptions(config));
        return resource;
    }

    @Override
    public void save(Resource resource, ImmutableConfig config) throws IOException {
        resource.save(getOptions(config));
    }

    @Override
    public void unload(Resource resource) {
        if (resource.isLoaded()) {
            resource.unload();
        }
    }

    @Nonnull
    @Override
    public URI copy(URI uri) throws IOException {
        final StoreCopier copier = uri.isFile()
                ? new FileStoreCopier(this)
                : new HierarchicalStoreCopier(this);

        return copier.copy(uri);
    }

    // endregion

    // region Adapter.Internal

    @Nonnull
    @Override
    public String getResourceExtension() {
        return category;
    }

    @Nonnull
    @Override
    public String getStoreExtension() {
        return Objects.equals(name, category) ? Strings.EMPTY : name;
    }

    // endregion

    /**
     * Checks that this {@code Adapter} supports the creation of {@link fr.inria.atlanmod.neoemf.data.mapping.DataMapper}s.
     *
     * @return {@code true} if this {@code Adapter} supports the creation of DataMapper instances
     */
    protected boolean supportsMapper() {
        return false;
    }

    /**
     * Returns the default {@link Map} options of this adapter.
     *
     * @param config an additional configuration, defined at runtime
     *
     * @return the {@link Map} options
     */
    @Nonnull
    protected abstract Map<String, ?> getOptions(ImmutableConfig config);

    /**
     * Creates a data store located by the {@code uri} from the given {@code resourceFile}.
     *
     * @throws IOException if an I/O error occurs when creating the datastore
     */
    private void createStore(File resourceFile, URI uri, ImmutableConfig config, boolean useDirectImport) throws IOException {
        Log.info("Creating the datastore at {0} ...", uri);

        final StoreCreator creator = useDirectImport && supportsMapper()
                ? new DirectStoreCreator(this)
                : new StandardStoreCreator(this);

        creator.create(resourceFile, uri, config);
    }

    /**
     * Checks that the resource located by the {@code uri} exists.
     *
     * @param uri the URI locating the resource
     *
     * @return {@code true} if the resource exists, {@code false} otherwise
     */
    private boolean exists(URI uri) throws IOException {
        // Check by file
        if (uri.isFile()) {
            return Paths.get(uri.toFileString()).toFile().exists();
        }

        // Check by content
        final Resource resource = load(uri, new BaseConfig<>().log());
        final boolean exists = !resource.getContents().isEmpty();

        unload(resource);

        return exists;
    }
}
