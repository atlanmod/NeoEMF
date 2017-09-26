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

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.berkeleydb.context.BerkeleyDbContext;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractPersistenceMapperTest;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A test-case about {@link BerkeleyDbBackendArrays}.
 */
@ParametersAreNonnullByDefault
public class BerkeleyDbArraysTest extends AbstractPersistenceMapperTest {

    @Nonnull
    @Override
    public Context context() {
        return BerkeleyDbContext.getWithArrays();
    }
}
