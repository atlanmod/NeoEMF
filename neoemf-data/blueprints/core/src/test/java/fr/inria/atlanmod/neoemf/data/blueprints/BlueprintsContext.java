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

import fr.inria.atlanmod.neoemf.Context;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

/**
 * A specific {@link Context} for the Blueprints implementation.
 */
public class BlueprintsContext implements Context {

    /**
     * The name of this context.
     */
    public static final String NAME = "Tinker";

    /**
     * Constructs a new {@code BlueprintsContext}.
     */
    protected BlueprintsContext() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class.
     */
    public static Context get() {
        return Holder.INSTANCE;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String uriScheme() {
        return BlueprintsURI.SCHEME;
    }

    @Override
    public URI createURI(URI uri) {
        return BlueprintsURI.createURI(uri);
    }

    @Override
    public URI createFileURI(File file) {
        return BlueprintsURI.createFileURI(file);
    }

    @Override
    public PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException {
        return new BlueprintsTestHelper(ePackage).persistent().file(file).createResource();
    }

    @Override
    public PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException {
        return new BlueprintsTestHelper(ePackage).file(file).createResource();
    }

    @Override
    public PersistentResource loadResource(EPackage ePackage, File file) throws IOException {
        return new BlueprintsTestHelper(ePackage).file(file).loadResource();
    }

    @Override
    public PersistenceBackend createBackend(File file) throws IOException {
        return new BlueprintsTestHelper(null).file(file).createBackend();
    }

    @Override
    public PersistenceBackendFactory persistenceBackendFactory() {
        return BlueprintsBackendFactory.getInstance();
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static class Holder {

        /**
         * The instance of the outer class.
         */
        private static final Context INSTANCE = new BlueprintsContext();
    }
}
