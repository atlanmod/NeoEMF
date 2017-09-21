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

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An {@link Id} with a {@link String} representation.
 */
@Immutable
@ParametersAreNonnullByDefault
class StringId implements Id {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = 7727028860219819798L;

    /**
     * The literal representation of this ientifier.
     */
    @Nonnull
    private final String value;

    /**
     * Constructs a new {@code StringId} with its literal representation.
     *
     * @param value the literal representation of this identifier
     */
    protected StringId(String value) {
        this.value = checkNotNull(value);
    }

    @Override
    public int compareTo(Id id) {
        return id.toString().compareTo(toString());
    }

    @Override
    public long toLong() {
        throw new UnsupportedOperationException("This Id cannot have a Long representation");
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (!StringId.class.isInstance(o)) {
            return false;
        }

        StringId that = StringId.class.cast(o);
        return Objects.equals(value, that.value);
    }

    @Nonnull
    @Override
    public String toString() {
        return value;
    }
}
