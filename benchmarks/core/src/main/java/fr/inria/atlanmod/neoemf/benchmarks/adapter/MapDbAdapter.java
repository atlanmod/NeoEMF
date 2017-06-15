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

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbOptions;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbUri;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.File;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Adapter} on top of a {@link fr.inria.atlanmod.neoemf.data.mapdb.MapDbBackend}.
 */
@ParametersAreNonnullByDefault
public abstract class MapDbAdapter extends AbstractNeoAdapter {

    /**
     * Constructs a new {@code MapDbAdapter}.
     *
     * @param storeExtension the extension of the resource, used for benchmarks
     */
    protected MapDbAdapter(String storeExtension) {
        super("mapdb." + storeExtension);
    }

    @Nonnull
    @Override
    protected BackendFactory getFactory() {
        return MapDbBackendFactory.getInstance();
    }

    @Nonnull
    @Override
    public Resource createResource(File file, ResourceSet resourceSet) {
        BackendFactoryRegistry.register(MapDbUri.SCHEME, MapDbBackendFactory.getInstance());
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(MapDbUri.SCHEME, PersistentResourceFactory.getInstance());

        URI uri = MapDbUri.builder().fromFile(file);

        return resourceSet.createResource(uri);
    }

    /**
     * A {@link MapDbAdapter} with a mapping with indices.
     */
    public static final class WithIndices extends MapDbAdapter {

        /**
         * Constructs a new {@code MapDbAdapter.WithIndices}.
         */
        @SuppressWarnings("unused") // Called dynamically
        public WithIndices() {
            super("indices");
        }

        @Nonnull
        @Override
        public Map<String, Object> getOptions() {
            return MapDbOptions.builder()
                    .withIndices()
                    .asMap();
        }
    }

    /**
     * A {@link MapDbAdapter} with a mapping with arrays.
     */
    public static final class WithArrays extends MapDbAdapter {

        /**
         * Constructs a new {@code MapDbAdapter.WithArrays}.
         */
        @SuppressWarnings("unused") // Called dynamically
        public WithArrays() {
            super("arrays");
        }

        @Nonnull
        @Override
        public Map<String, Object> getOptions() {
            return MapDbOptions.builder()
                    .withArrays()
                    .asMap();
        }
    }

    /**
     * A {@link MapDbAdapter} with a mapping with lists.
     */
    public static final class WithLists extends MapDbAdapter {

        /**
         * Constructs a new {@code MapDbAdapter.WithLists}.
         */
        @SuppressWarnings("unused") // Called dynamically
        public WithLists() {
            super("lists");
        }

        @Nonnull
        @Override
        public Map<String, Object> getOptions() {
            return MapDbOptions.builder()
                    .withLists()
                    .asMap();
        }
    }

    /**
     * A {@link MapDbAdapter} with a mapping with maps.
     */
    public static final class WithMaps extends MapDbAdapter {

        /**
         * Constructs a new {@code MapDbAdapter.WithMaps}.
         */
        @SuppressWarnings("unused") // Called dynamically
        public WithMaps() {
            super("maps");
        }

        @Nonnull
        @Override
        public Map<String, Object> getOptions() {
            return MapDbOptions.builder()
                    .withMaps()
                    .asMap();
        }
    }
}
