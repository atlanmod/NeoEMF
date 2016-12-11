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

import fr.inria.atlanmod.neoemf.extension.Workspace;
import fr.inria.atlanmod.neoemf.extension.Watcher;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.TestRule;

public abstract class AbstractTest {

    @ClassRule
    public static Workspace workspace = new Workspace();

    @Rule
    public TestRule watcher = new Watcher();
}
