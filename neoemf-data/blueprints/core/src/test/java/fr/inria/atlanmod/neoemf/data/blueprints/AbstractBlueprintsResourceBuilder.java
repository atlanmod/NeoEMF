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

package fr.inria.atlanmod.neoemf.data.blueprints;

import fr.inria.atlanmod.neoemf.context.AbstractResourceBuilder;
import fr.inria.atlanmod.neoemf.context.ResourceBuilder;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;

/**
 * An abstract {@link ResourceBuilder} for all Blueprints implementations.
 *
 * @param <B> the "self"-type of this {@link ResourceBuilder}
 */
public abstract class AbstractBlueprintsResourceBuilder<B extends AbstractBlueprintsResourceBuilder<B>> extends AbstractResourceBuilder<B> {

    /**
     * Constructs a new {@code AbstractBlueprintsResourceBuilder} with the given {@code ePackage}.
     *
     * @param ePackage the {@link EPackage} associated to the built {@link Resource}
     *
     * @see EPackage.Registry
     */
    protected AbstractBlueprintsResourceBuilder(EPackage ePackage) {
        super(ePackage);
    }

    @Override
    public B uri(URI uri) {
        this.uri = BlueprintsURI.createURI(uri);
        return me();
    }

    @Override
    public B file(File file) {
        this.uri = BlueprintsURI.createFileURI(file);
        return me();
    }

    @Override
    protected void initBuilder() {
        super.initBuilder();

        registerFactory();

        if (!PersistenceBackendFactoryRegistry.isRegistered(BlueprintsURI.SCHEME)) {
            PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME, BlueprintsPersistenceBackendFactory.getInstance());
        }
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
    }

    /**
     * Registers the {@link fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory} in {@link #resourceOptions}.
     */
    protected abstract void registerFactory();
}
