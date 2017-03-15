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

package fr.inria.atlanmod.neoemf.util.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * The factory that creates {@link Hasher} instances.
 */
@ParametersAreNonnullByDefault
public class HasherFactory {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private HasherFactory() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Returns an instance of a {@link Hasher} using {@code MD5} algorithm.
     *
     * @return a new {@link Hasher}
     */
    @Nonnull
    public static Hasher md5() {
        return bytes -> new HashCode(hash("MD5", bytes));
    }

    /**
     * Returns an instance of a {@link Hasher} using {@code SHA-1} algorithm.
     *
     * @return a new {@link Hasher}
     */
    @Nonnull
    public static Hasher sha1() {
        return bytes -> new HashCode(hash("SHA-1", bytes));
    }

    /**
     * Returns an instance of a {@link Hasher} using {@code SHA-1} algorithm.
     *
     * @return a new {@link Hasher}
     */
    @Nonnull
    public static Hasher sha256() {
        return bytes -> new HashCode(hash("SHA-256", bytes));
    }

    /**
     * Creates a new {@link HashCode} from the given {@code bytes} by using the given {@code algorithm}.
     *
     * @param algorithm the name of the algorithm requested
     * @param bytes     the value to hash
     *
     * @return a message hash instance
     */
    @Nonnull
    private static byte[] hash(String algorithm, byte[] bytes) {
        try {
            return MessageDigest.getInstance(algorithm).digest(checkNotNull(bytes));
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
