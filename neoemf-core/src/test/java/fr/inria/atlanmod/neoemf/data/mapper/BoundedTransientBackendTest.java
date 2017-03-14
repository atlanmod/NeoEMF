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

package fr.inria.atlanmod.neoemf.data.mapper;

import fr.inria.atlanmod.neoemf.Context;
import fr.inria.atlanmod.neoemf.CoreContext;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BoundedTransientBackend;

import java.io.File;
import java.io.IOException;

/**
 * A test case for {@link BoundedTransientBackend}.
 */
public class BoundedTransientBackendTest extends AbstractPersistenceMapperTest {

    @Override
    public Context context() {
        return new CoreContext() {

            @Override
            public Backend createPersistentBackend(File file) throws IOException {
                return new BoundedTransientBackend(id0);
            }
        };
    }
}
