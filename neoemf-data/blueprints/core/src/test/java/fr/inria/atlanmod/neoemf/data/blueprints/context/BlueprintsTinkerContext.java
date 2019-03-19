/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.context;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.config.BlueprintsTinkerConfig;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specific {@link AbstractBlueprintsContext} for the Tinkergraph implementation.
 */
@ParametersAreNonnullByDefault
public class BlueprintsTinkerContext extends AbstractBlueprintsContext {

    @Nonnull
    @Override
    public String name() {
        return super.name() + "-Tinkergraph";
    }

    @Nonnull
    @Override
    public ImmutableConfig config() {
        return new BlueprintsTinkerConfig();
    }
}
