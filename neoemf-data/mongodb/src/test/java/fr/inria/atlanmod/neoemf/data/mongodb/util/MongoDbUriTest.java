/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb.util;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.mongodb.context.MongoDbContext;
import fr.inria.atlanmod.neoemf.util.AbstractUriTest;
import org.junit.jupiter.api.Disabled;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;

/**
 * A test-case about {@link MongoDbUri}.
 */
@ParametersAreNonnullByDefault
class MongoDbUriTest extends AbstractUriTest {

    @Nonnull
    @Override
    protected Context context() {
        return MongoDbContext.getDefault();
    }

    @Disabled
    @Override
    public void testCreateUriFromStandardUriInvalidScheme() {
        super.testCreateUriFromStandardUriInvalidScheme();
    }
}
