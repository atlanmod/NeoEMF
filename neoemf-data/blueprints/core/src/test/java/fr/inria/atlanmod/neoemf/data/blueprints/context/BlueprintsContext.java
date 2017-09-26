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

import fr.inria.atlanmod.neoemf.context.AbstractContext;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptions;
import fr.inria.atlanmod.neoemf.option.PersistenceOptions;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link Context} for the Blueprints implementation.
 */
@ParametersAreNonnullByDefault
public abstract class BlueprintsContext extends AbstractContext {

    /**
     * Creates a new {@code BlueprintsContext} with a mapping with indices.
     *
     * @return a new context.
     */
    @Nonnull
    public static Context getWithIndices() {
        return new BlueprintsContext() {
            @Nonnull
            @Override
            public PersistenceOptions optionsBuilder() {
                return BlueprintsOptions.builder().withIndices();
            }
        };
    }

    @Nonnull
    @Override
    public String name() {
        return "Tinker";
    }

    @Nonnull
    @Override
    public BackendFactory factory() {
        return BlueprintsBackendFactory.getInstance();
    }
}
