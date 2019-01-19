/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.neoemf.core.internal.LongIdConverter;
import fr.inria.atlanmod.neoemf.core.internal.StringIdConverter;

import org.atlanmod.commons.Throwables;
import org.atlanmod.commons.annotation.Static;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A static factory that creates {@link IdConverter} instances related to {@link Id}s.
 */
@Static
@ParametersAreNonnullByDefault
public final class IdConverters {

    private IdConverters() {
        throw Throwables.notInstantiableClass(getClass());
    }

    /**
     * Returns the {@link IdConverter} to use a long representation instead of {@link Id}.
     *
     * @return a converter
     */
    @Nonnull
    public static IdConverter<Long> withLong() {
        return new LongIdConverter();
    }

    /**
     * Returns the {@link IdConverter} to use a hexadecimal representation instead of {@link Id}.
     *
     * @return a converter
     */
    @Nonnull
    public static IdConverter<String> withHexString() {
        return new StringIdConverter();
    }
}
