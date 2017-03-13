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

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.neoemf.Context;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.option.HBaseOptions;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * A specific {@link Context} for the HBase implementation.
 */
public class HBaseContext implements Context {

    /**
     * The name of this context.
     */
    public static final String NAME = "HBase";

    /**
     * Constructs a new {@code HBaseContext}.
     */
    protected HBaseContext() {
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
    public Map<String, Object> defaultOptions() {
        return HBaseOptions.noOption();
    }

    @Override
    public String uriScheme() {
        return HBaseURI.SCHEME;
    }

    @Override
    public URI createURI(URI uri) {
        return HBaseURI.createURI(uri);
    }

    @Override
    public URI createFileURI(File file) {
        return HBaseURI.createFileURI(file);
    }

    @Override
    public PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException {
        return new HBaseTestHelper(ePackage).persistent().file(file).createResource();
    }

    @Override
    public PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException {
        return createPersistentResource(ePackage, file);
    }

    @Override
    public PersistentResource loadResource(EPackage ePackage, File file) throws IOException {
        return new HBaseTestHelper(ePackage).file(file).loadResource();
    }

    @Override
    public Backend createBackend(File file) throws IOException {
        return new HBaseTestHelper(null).file(file).createBackend();
    }

    @Override
    public BackendFactory backendFactory() {
        return HBaseBackendFactory.getInstance();
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final Context INSTANCE = new HBaseContext();
    }
}
