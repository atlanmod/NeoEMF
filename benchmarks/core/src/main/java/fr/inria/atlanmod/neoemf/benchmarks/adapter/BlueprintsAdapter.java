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
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsUri;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.File;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Adapter} on top of a {@link fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackend} using TinkerGraph.
 */
@ParametersAreNonnullByDefault
public class BlueprintsAdapter extends AbstractNeoAdapter {

    public static final String NAME = "neo-tinker";

    private static final String STORE_EXTENSION = "tinker.resource"; // -> neoemf.tinker.resource

    @SuppressWarnings("unused") // Called dynamically
    public BlueprintsAdapter() {
        this(NAME, STORE_EXTENSION);
    }

    protected BlueprintsAdapter(String name, String storeExtension) {
        super(name, storeExtension);
    }

    @Nonnull
    @Override
    protected BackendFactory getFactory() {
        return BlueprintsBackendFactory.getInstance();
    }

    @Nonnull
    @Override
    public Resource createResource(File file, ResourceSet resourceSet) {
        BackendFactoryRegistry.register(BlueprintsUri.SCHEME, BlueprintsBackendFactory.getInstance());
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsUri.SCHEME, PersistentResourceFactory.getInstance());

        URI uri = BlueprintsUri.builder().fromFile(file);

        return resourceSet.createResource(uri);
    }

    @Nonnull
    @Override
    public Map<String, Object> getOptions() {
        return BlueprintsOptions.builder().asMap();
    }
}
