/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsTinkerContext;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractDataMapperTest;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link DefaultBlueprintsBackend} with a TinkerGraph implementation.
 */
@ParametersAreNonnullByDefault
class DefaultBlueprintsBackendTest extends AbstractDataMapperTest {

    @Nonnull
    @Override
    protected Context context() {
        return new BlueprintsTinkerContext();
    }
}
