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
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

/**
 * An identifier used to identify {@link PersistentEObject}s in datastores.
 * <p>
 * This is the primary key in datastores, so, each {@code Id} must be unique at {@link
 * fr.inria.atlanmod.neoemf.data.mapping.DataMapper} level to ensure data consistency. The unicity is not guaranteed
 * before adding a new {@link PersistentEObject} to a database.
 */
@Immutable
@ParametersAreNonnullByDefault
public interface Id extends Comparable<Id>, Serializable {

    /**
     * An immutable undefined {@link Id}.
     */
    @Nonnull
    Id UNDEFINED = new Id() {
        @Override
        public long toLong() {
            throw new IllegalStateException("Undefined identifier");
        }

        @Override
        @Nonnull
        public String toHexString() {
            throw new IllegalStateException("Undefined identifier");
        }

        @Override
        public int compareTo(@Nonnull Id o) {
            throw new IllegalStateException("Undefined identifier");
        }

        @Override
        public String toString() {
            return "Id {UNDEFINED}";
        }
    };

    /**
     * Returns the instance of the default {@link IdProvider}.
     *
     * @return the instance
     */
    @Nonnull
    static IdProvider getProvider() {
        return LongIdProvider.getInstance();
    }

    /**
     * Returns the {@link Long} representation of this {@code Id}.
     *
     * @return a long
     */
    long toLong();

    /**
     * Returns the {@link String} representation of this {@code Id}, as an hexadecimal value.
     *
     * @return a hexadecimal string
     */
    @Nonnull
    String toHexString();
}
