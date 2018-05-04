/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.benchmarks.resource.Resources;
import fr.inria.atlanmod.neoemf.benchmarks.resource.Stores;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkState;
import static java.util.Objects.isNull;

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
    private final Class<?> modelPackage;

    /**
     * Constructs a new {@code AbstractAdapter}.
     *
     * @param category     the category of this adapter
     * @param modelPackage the class of the {@link EPackage} associated to this adapter
     */
    protected AbstractAdapter(String category, Class<?> modelPackage) {
        this.category = checkNotNull(category);
        this.modelPackage = checkNotNull(modelPackage);

        final AdapterName annotation = getClass().getAnnotation(AdapterName.class);
        if (isNull(annotation)) {
            throw new IllegalStateException(String.format("This adapter is not annotated with %s", AdapterName.class.getSimpleName()));
        }

        this.name = annotation.value();
    }

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

    @Nonnull
    @Override
    public final EPackage initAndGetEPackage() {
        try {
            return (EPackage) modelPackage.getMethod("init").invoke(null);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public File getOrCreateResource(String name) throws IOException {
        return Resources.getOrCreateResource(name, this);
    }

    @Nonnull
    @Override
    public File getOrCreateStore(File file, ImmutableConfig config, boolean useDirectImport) throws IOException {
        return getOrCreateStore(file, config, useDirectImport, false);
    }

    @Nonnull
    @Override
    public File createTempStore(File file, ImmutableConfig config, boolean useDirectImport) throws IOException {
        return getOrCreateStore(file, config, useDirectImport, true);
    }

    @Nonnull
    @Override
    public Resource load(File file, ImmutableConfig config) throws IOException {
        initAndGetEPackage();

        Resource resource = createResource(file);
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
    public File copy(File file) throws IOException {
        return Stores.copyStore(file);
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
     * Retrieves or creates a {@link Resource} used for benchmarks.
     *
     * @param file      the file to retrieve the resource
     * @param temporary {@code true} if the resource is temporary and must be placed in a temporary folder
     *
     * @return the resource
     */
    @Nonnull
    private File getOrCreateStore(File file, ImmutableConfig config, boolean useDirectImport, boolean temporary) throws IOException {
        File storeFile;

        if (temporary) {
            storeFile = Stores.createTempStore(file, config, this, useDirectImport);
        }
        else {
            storeFile = Stores.getOrCreateStore(file, config, this, useDirectImport);
        }

        checkState(storeFile.exists(), "'%s' does not exist in resource directory", file.getName());
        return storeFile;
    }
}
