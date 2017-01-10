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

public final class Tags {

    private Tags() {
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
