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

import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;

import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.IOException;

/**
 * An abstract {@link ContextualTest} that initializes {@link BackendFactory}
 * in the {@link BackendFactoryRegistry} and holds the temporary file.
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
     * Registers the current {@link BackendFactory} from the current {@link
     * Context} in the {@link BackendFactoryRegistry} and initialize the {@link #file}.
     *
     * @throws IOException if an I/O error occurs
     */
    @Before
    public final void registerFactories() throws IOException {
        BackendFactoryRegistry.register(context().uriScheme(), context().factory());
        file = workspace.newFile(context().name());
    }

    /**
     * Unregisters the current {@link BackendFactory} from the
     * {@link BackendFactoryRegistry}.
     */
    @After
    public final void unregisterFactories() {
        BackendFactoryRegistry.unregisterAll();
    }
}
