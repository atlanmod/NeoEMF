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

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;

import org.eclipse.emf.common.util.URI;

import java.io.File;

/**
 * A specific {@link Context} for the Blueprints implementation.
 */
public abstract class BlueprintsContext implements Context {

    /**
     * Creates a new {@code BlueprintsContext} with a mapping with indices.
     *
     * @return a new context.
     */
    public static Context getWithIndices() {
        return new BlueprintsContext() {
            @Override
            public AbstractPersistenceOptionsBuilder<?, ?> optionsBuilder() {
                return BlueprintsOptions.newBuilder().withIndices();
            }
        };
    }

    @Override
    public String name() {
        return "Tinker";
    }

    @Override
    public BackendFactory factory() {
        return BlueprintsBackendFactory.getInstance();
    }

    @Override
    public String uriScheme() {
        return BlueprintsURI.SCHEME;
    }

    @Override
    public URI createUri(URI uri) {
        return BlueprintsURI.createURI(uri);
    }

    @Override
    public URI createUri(File file) {
        return BlueprintsURI.createFileURI(file);
    }
}
