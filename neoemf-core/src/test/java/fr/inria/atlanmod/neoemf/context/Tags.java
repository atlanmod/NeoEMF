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

package fr.inria.atlanmod.neoemf.context;

/**
 * A utility class that regroups every tags used in test-cases.
 */
public final class Tags {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private Tags() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Category marker for tests using a persistent store.
     */
    public interface PersistentTests {}

    /**
     * Category marker for tests using a transient store.
     */
    public interface TransientTests {}
}
