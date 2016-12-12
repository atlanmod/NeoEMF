/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf;

import fr.inria.atlanmod.neoemf.context.Contextual;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;

import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.IOException;

public abstract class AbstractUnitTest extends AbstractTest implements Contextual {

    private File file;

    protected File file() {
        return file;
    }

    @Before
    public final void registerFactories() throws IOException {
        PersistenceBackendFactoryRegistry.register(context().uriScheme(), context().persistenceBackendFactory());
        file = workspace.newFile(context().name());
    }

    @After
    public final void unregisterFactories() {
        PersistenceBackendFactoryRegistry.unregisterAll();
    }
}
