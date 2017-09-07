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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.context.CoreContext;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractPersistenceMapperTest;

import java.io.File;

import javax.annotation.Nonnull;

/**
 * A test-case that checks the behavior of {@link BoundTransientBackend}.
 */
public class BoundTransientBackendTest extends AbstractPersistenceMapperTest {

    @Nonnull
    @Override
    public Context context() {
        return new CoreContext() {

            @Override
            public Backend createMapper(File file) {
                return BoundTransientBackend.forId(id0);
            }
        };
    }
}
