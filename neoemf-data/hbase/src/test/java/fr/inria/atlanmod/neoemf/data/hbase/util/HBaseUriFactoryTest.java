/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.hbase.util;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.hbase.context.HBaseContext;
import fr.inria.atlanmod.neoemf.util.AbstractUriFactoryTest;

import org.junit.jupiter.api.Disabled;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link HBaseUriFactory}.
 */
@ParametersAreNonnullByDefault
class HBaseUriFactoryTest extends AbstractUriFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return HBaseContext.getDefault();
    }

    @Disabled("Not supported because of the mini-cluster")
    @Override
    public void testCreateUriFromStandardUriInvalidScheme() {
    }
}
