/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.im;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.context.CoreContext;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractPersistenceMapperTest;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link DefaultInMemoryBackend}.
 */
@ParametersAreNonnullByDefault
class DefaultInMemoryBackendTest extends AbstractPersistenceMapperTest {

    @Nonnull
    @Override
    protected Context context() {
        return CoreContext.get();
    }
}
