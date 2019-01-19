/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf;

import fr.inria.atlanmod.neoemf.context.Context;

import org.atlanmod.commons.AbstractFileBasedTest;
import org.junit.jupiter.api.BeforeEach;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * An abstract contextual test-case that initializes the {@link fr.inria.atlanmod.neoemf.context.Context} and holds the
 * temporary file.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractUnitTest extends AbstractFileBasedTest {

    /**
     * Returns the current {@link Context}.
     *
     * @return the current {@link Context}.
     */
    @Nonnull
    protected abstract Context context();

    /**
     * Initialize the current context.
     */
    @BeforeEach
    void initContext() {
        context().init();
        assumeTrue(context().isInitialized(), "The context has not been initialized");
    }
}
