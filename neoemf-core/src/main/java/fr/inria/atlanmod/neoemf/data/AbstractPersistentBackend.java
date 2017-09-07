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

package fr.inria.atlanmod.neoemf.data;

import javax.annotation.Nullable;

/**
 * An abstract {@link PersistentBackend}.
 */
public abstract class AbstractPersistentBackend extends AbstractBackend implements PersistentBackend {

    /**
     * Constructs a new {@code AbstractPersistentBackend}.
     */
    protected AbstractPersistentBackend() {
        super();
    }

    /**
     * Constructs a new {@code AbstractPersistentBackend} with the given {@code name}.
     *
     * @param name the unique name of this backend
     */
    protected AbstractPersistentBackend(@Nullable String name) {
        super(name);
    }
}
