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

package fr.inria.atlanmod.neoemf.core;

import java.io.Serializable;

import javax.annotation.Nonnull;

/**
 * An identifier used to identify a {@link PersistentEObject} in data stores.
 * <p>
 * This is the primary key in data stores, so, each {@code Id} must be unique at back-end level to ensure data consistency.
 */
public interface Id extends Comparable<Id>, Serializable {

    /**
     * Returns the {@link String} representation of this {@code Id}.
     *
     * @return a string
     */
    @Nonnull
    String toString();

    /**
     * Returns the {@link Long} number of this {@code Id}.
     *
     * @return a long
     */
    @Nonnull
    Long toLong();
}
