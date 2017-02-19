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

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import fr.inria.atlanmod.neoemf.AbstractTestHelper;
import fr.inria.atlanmod.neoemf.TestHelper;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.berkeleydb.option.BerkeleyDbOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.berkeleydb.util.BerkeleyDbURI;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;

/**
 * A specific {@link TestHelper} for the BerkeleyDB implementation.
 */
public class BerkeleyDbTestHelper extends AbstractTestHelper<BerkeleyDbTestHelper> {

    /**
     * Constructs a new {@code BerkeleyDbTestHelper} with the given {@code ePackage}.
     *
     * @param ePackage the {@link EPackage} associated to the built {@link Resource}
     *
     * @see EPackage.Registry
     */
    public BerkeleyDbTestHelper(EPackage ePackage) {
        super(ePackage);
    }

    @Override
    protected PersistenceBackendFactory factory() {
        return BerkeleyDbBackendFactory.getInstance();
    }

    @Override
    protected BerkeleyDbOptionsBuilder optionsBuilder() {
        return BerkeleyDbOptionsBuilder.newBuilder();
    }

    @Override
    protected String uriScheme() {
        return BerkeleyDbURI.SCHEME;
    }

    @Override
    public BerkeleyDbTestHelper uri(URI uri) {
        this.uri = BerkeleyDbURI.createURI(uri);
        return me();
    }

    @Override
    public BerkeleyDbTestHelper file(File file) {
        this.uri = BerkeleyDbURI.createFileURI(file);
        return me();
    }
}
