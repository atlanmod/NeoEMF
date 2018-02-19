/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.commons.annotation.Singleton;
import fr.inria.atlanmod.commons.annotation.Static;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An {@link IdProvider} that provides {@link Id} with a long representation.
 *
 * @see LongId
 */
@Singleton
@ParametersAreNonnullByDefault
public final class LongIdProvider extends AbstractIdProvider {

    /**
     * Constructs a new {@code LongIdProvider}.
     */
    private LongIdProvider() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static IdProvider getInstance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public Id fromLong(long value) {
        return new LongId(value);
    }

    @Nonnull
    @Override
    public Id fromHexString(String hexValue) {
        checkNotNull(hexValue, "hexValue");

        try {
            return fromLong(Long.parseUnsignedLong(hexValue, 16));
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Nonnull
    @Override
    public Id generate() {
        return fromLong(UUID.randomUUID().getLeastSignificantBits());
    }

    @Nonnull
    @Override
    public Id generate(String baseValue) {
        checkNotNull(baseValue, "baseValue");

        return fromLong(hasher().hash(baseValue).toLong());
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        static final IdProvider INSTANCE = new LongIdProvider();
    }
}
