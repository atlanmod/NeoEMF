/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.hash.impl;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import fr.inria.atlanmod.neoemf.io.hash.HashCode;
import fr.inria.atlanmod.neoemf.io.hash.Hasher;

/**
 *
 */
public class Md5Hasher implements Hasher {

    private static Hasher INSTANCE;

    public static Hasher getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Md5Hasher();
        }
        return INSTANCE;
    }

    private static final HashFunction hashFunction = Hashing.md5();

    private Md5Hasher() {
    }

    @Override
    public HashCode hash(String value) {
        byte[] hash = hashFunction.newHasher().putString(value, Charsets.UTF_8).hash().asBytes();

        return new BytesHashCode(hash);
    }
}
