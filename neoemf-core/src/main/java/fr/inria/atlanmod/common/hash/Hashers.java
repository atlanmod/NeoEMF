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

package fr.inria.atlanmod.common.hash;

import fr.inria.atlanmod.common.annotation.Static;
import fr.inria.atlanmod.common.annotation.VisibleForTesting;
import fr.inria.atlanmod.common.hash.murmur.Murmur128;
import fr.inria.atlanmod.common.hash.murmur.Murmur32;
import fr.inria.atlanmod.common.hash.murmur.Murmur64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * The factory that creates {@link Hasher} instances.
 */
@Static
@ParametersAreNonnullByDefault
public final class Hashers {

    /**
     * The name to identify the {@code MD5} algorithm in {@link MessageDigest}.
     */
    private static final String MD5 = "MD5";

    /**
     * The name to identify the {@code SHA-1} algorithm in {@link MessageDigest}.
     */
    private static final String SHA1 = "SHA-1";

    /**
     * The name to identify the {@code SHA-256} algorithm in {@link MessageDigest}.
     */
    private static final String SHA256 = "SHA-256";

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private Hashers() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Returns a {@link Hasher} that uses the {@code MD5} algorithm (128 bits).
     *
     * @return a new {@link Hasher}
     */
    @Nonnull
    public static Hasher md5() {
        return bytes -> nativeHash(MD5, bytes);
    }

    /**
     * Returns a {@link Hasher} that uses the {@code SHA-1} algorithm (160 bits).
     *
     * @return a new {@link Hasher}
     */
    @Nonnull
    public static Hasher sha1() {
        return bytes -> nativeHash(SHA1, bytes);
    }

    /**
     * Returns a {@link Hasher} that uses the {@code SHA-256} algorithm (256 bits).
     *
     * @return a new {@link Hasher}
     */
    @Nonnull
    public static Hasher sha256() {
        return bytes -> nativeHash(SHA256, bytes);
    }

    /**
     * Returns a {@link Hasher} that uses the {@code Murmur 32-bit} algorithm (32 bits).
     *
     * @return a new {@link Hasher}
     */
    @Nonnull
    public static Hasher murmur32() {
        return new Murmur32();
    }

    /**
     * Returns a {@link Hasher} that uses the {@code Murmur 64-bit} algorithm (64 bits).
     *
     * @return a new {@link Hasher}
     */
    @Nonnull
    public static Hasher murmur64() {
        return new Murmur64();
    }

    /**
     * Returns a {@link Hasher} that uses the {@code Murmur 128-bit} algorithm (128 bits).
     *
     * @return a new {@link Hasher}
     */
    @Nonnull
    public static Hasher murmur128() {
        return new Murmur128();
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
    @VisibleForTesting
    protected static HashCode nativeHash(String algorithm, byte[] bytes) {
        checkNotNull(bytes);

        try {
            return new HashCode(MessageDigest.getInstance(algorithm).digest(bytes));
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
