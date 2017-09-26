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

import fr.inria.atlanmod.commons.AbstractTest;
import fr.inria.atlanmod.neoemf.context.ContextualTest;

import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * An abstract {@link ContextualTest} that initializes the {@link fr.inria.atlanmod.neoemf.context.Context} and holds
 * the temporary file.
 */
@ParametersAreNonnullByDefault
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
     * Initialize the current context and the {@link #file}.
     *
     * @throws IOException if an I/O error occurs
     */
    @BeforeEach
    public final void initContext() throws IOException {
        context().init();
        assumeTrue(context().isInitialized(), "The context has not been initialized");

        file = newFile(context().name());
    }
}
