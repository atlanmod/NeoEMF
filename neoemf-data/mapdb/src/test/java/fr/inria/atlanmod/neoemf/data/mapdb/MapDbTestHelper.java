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

package fr.inria.atlanmod.neoemf.data.mapdb;

import fr.inria.atlanmod.neoemf.AbstractTestHelper;
import fr.inria.atlanmod.neoemf.TestHelper;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbURI;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;

/**
 * A specific {@link TestHelper} for the MapDB implementation.
 */
public class MapDbTestHelper extends AbstractTestHelper<MapDbTestHelper> {

    /**
     * Constructs a new {@code MapDbTestHelper} with the given {@code ePackage}.
     *
     * @param ePackage the {@link EPackage} associated to the built {@link Resource}
     *
     * @see EPackage.Registry
     */
    public MapDbTestHelper(EPackage ePackage) {
        super(ePackage);
    }

    @Override
    protected PersistenceBackendFactory factory() {
        return MapDbBackendFactory.getInstance();
    }

    @Override
    protected MapDbOptionsBuilder optionsBuilder() {
        return MapDbOptionsBuilder.newBuilder();
    }

    @Override
    protected String uriScheme() {
        return MapDbURI.SCHEME;
    }

    @Override
    public MapDbTestHelper uri(URI uri) {
        this.uri = MapDbURI.createURI(uri);
        return me();
    }

    @Override
    public MapDbTestHelper file(File file) {
        this.uri = MapDbURI.createFileURI(file);
        return me();
    }
}
