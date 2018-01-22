/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package ${package}.context;

import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.context.AbstractContext;
import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.BackendFactory;

import ${package}.${databaseName}BackendFactory;
import ${package}.config.${databaseName}Config;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link Context} for the ${databaseName} implementation.
 */
@ParametersAreNonnullByDefault
public abstract class ${databaseName}Context extends AbstractContext {

    /**
     * Creates a new {@code BerkeleyDbContext}.
     *
     * @return a new context.
     */
    @Nonnull
    public static Context getDefault() {
        return new ${databaseName}Context() {
            @Nonnull
            @Override
            public Config config() {
                return ${databaseName}Config.newConfig();
            }
        };
    }

    @Nonnull
    @Override
    public String name() {
        return "${databaseName}";
    }

    @Nonnull
    @Override
    public BackendFactory factory() {
        return ${databaseName}BackendFactory.getInstance();
    }
}
