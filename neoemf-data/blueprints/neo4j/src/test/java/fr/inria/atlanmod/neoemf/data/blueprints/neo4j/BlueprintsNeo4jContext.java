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

package fr.inria.atlanmod.neoemf.data.blueprints.neo4j;

import fr.inria.atlanmod.neoemf.Context;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsContext;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptions;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * A specific {@link Context} for the Blueprints Neo4j implementation.
 */
public class BlueprintsNeo4jContext extends BlueprintsContext {

    /**
     * The name of this context.
     */
    public static final String NAME = "Neo4j";

    /**
     * Constructs a new {@code BlueprintsNeo4jContext}.
     */
    protected BlueprintsNeo4jContext() {
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
        return BlueprintsNeo4jOptions.noOption();
    }

    @Override
    public PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException {
        return new BlueprintsNeo4jTestHelper(ePackage).persistent().file(file).createResource();
    }

    @Override
    public PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException {
        return new BlueprintsNeo4jTestHelper(ePackage).file(file).createResource();
    }

    @Override
    public PersistentResource loadResource(EPackage ePackage, File file) throws IOException {
        return new BlueprintsNeo4jTestHelper(ePackage).file(file).loadResource();
    }

    @Override
    public Backend createBackend(File file) throws IOException {
        return new BlueprintsNeo4jTestHelper(null).file(file).createBackend();
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final Context INSTANCE = new BlueprintsNeo4jContext();
    }
}
