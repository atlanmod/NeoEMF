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

import fr.inria.atlanmod.neoemf.extension.Watcher;
import fr.inria.atlanmod.neoemf.extension.Workspace;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.TestRule;

/**
 * An abstract test-case that holds the {@link Workspace} and the logger.
 */
public abstract class AbstractTest {

    /**
     * The workspace that manages temporary files and folders.
     */
    @ClassRule
    public static Workspace workspace = new Workspace();

    /**
     * The test-case logger.
     */
    @Rule
    public TestRule watcher = new Watcher();
}
