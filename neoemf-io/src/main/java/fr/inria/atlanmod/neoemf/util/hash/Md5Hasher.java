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

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

import javax.annotation.Nonnull;

/**
 * A {@link Hasher} using the {@code MD5} algorithm.
 */
public class Md5Hasher implements Hasher {

    /**
     * The hash function implementing the MD5 hash algorithm (128 hash bits).
     */
    private static final HashFunction hashFunction = Hashing.md5();

    /**
     * Constructs a new {@code Md5Hasher}.
     */
    private Md5Hasher() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static Hasher getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public HashCode hash(String value) {
        String hash = hashFunction.newHasher().putString(value, Charset.forName("UTF-8")).hash().toString();

        return new StringHashCode(hash);
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static class Holder {

        /**
         * The instance of the outer class.
         */
        private static final Hasher INSTANCE = new Md5Hasher();
    }
}
