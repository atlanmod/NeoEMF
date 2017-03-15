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

import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

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
        return value -> new HashCode(
                Hashing.md5()
                        .newHasher()
                        .putString(checkNotNull(value), Charset.forName("UTF-8"))
                        .hash()
                        .asBytes());
    }

    /**
     * Returns an instance of a {@link Hasher} using {@code SHA-1} algorithm.
     *
     * @return a new {@link Hasher}
     */
    @Nonnull
    public static Hasher sha1() {
        return value -> new HashCode(
                Hashing.sha1()
                        .newHasher()
                        .putString(checkNotNull(value), Charset.forName("UTF-8"))
                        .hash()
                        .asBytes());
    }
}
