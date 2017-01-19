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

package fr.inria.atlanmod.neoemf;

import fr.inria.atlanmod.neoemf.context.ContextualTest;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;

import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.IOException;

/**
 * An abstract {@link ContextualTest} that initializes {@link fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory}
 * in the {@link PersistenceBackendFactoryRegistry} and holds the temporary file.
 */
public abstract class AbstractUnitTest extends AbstractTest implements ContextualTest {

    /**
     * The current temporary file.
     */
    private File file;

    /**
     * Returns the current temporary file.
     *
     * @return a {@link File}
     */
    protected File file() {
        return file;
    }

    /**
     * Registers the current {@link fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory} from the current
     * {@link fr.inria.atlanmod.neoemf.context.Context} in the {@link PersistenceBackendFactoryRegistry} and initialize the
     * {@link #file}.
     *
     * @throws IOException if an I/O error occurs
     */
    @Before
    public final void registerFactories() throws IOException {
        PersistenceBackendFactoryRegistry.register(context().uriScheme(), context().persistenceBackendFactory());
        file = workspace.newFile(context().name());
    }

    /**
     * Unregisters the current {@link fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory} from the
     * {@link PersistenceBackendFactoryRegistry}.
     */
    @After
    public final void unregisterFactories() {
        PersistenceBackendFactoryRegistry.unregisterAll();
    }
}
