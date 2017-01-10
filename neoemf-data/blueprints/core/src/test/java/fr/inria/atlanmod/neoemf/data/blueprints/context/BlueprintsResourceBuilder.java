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

package fr.inria.atlanmod.neoemf.data.blueprints.context;

import fr.inria.atlanmod.neoemf.context.AbstractResourceBuilder;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;

public class BlueprintsResourceBuilder extends AbstractResourceBuilder {

    public BlueprintsResourceBuilder(EPackage ePackage) {
        super(ePackage);
        initBlueprintsBuilder();
    }

    @Override
    protected void initBuilder() {
        super.initBuilder();
        initBlueprintsBuilder();
    }

    @Override
    public BlueprintsResourceBuilder uri(URI uri) {
        this.uri = BlueprintsURI.createURI(uri);
        return this;
    }

    @Override
    public BlueprintsResourceBuilder file(File file) {
        this.uri = BlueprintsURI.createFileURI(file);
        return this;
    }

    private void initBlueprintsBuilder() {
        if (!PersistenceBackendFactoryRegistry.isRegistered(BlueprintsURI.SCHEME)) {
            PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME, BlueprintsPersistenceBackendFactory.getInstance());
        }
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
    }

    public BlueprintsResourceBuilder tinkerGraph() {
        resourceOptions.put(BlueprintsResourceOptions.GRAPH_TYPE, BlueprintsResourceOptions.GRAPH_TYPE_DEFAULT);
        return this;
    }
}
