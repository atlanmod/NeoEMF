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

package fr.inria.atlanmod.neoemf.io.hash;

/**
 * The factory that creates instances of {@link Hasher}.
 */
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
     * Returns an instance of a {@link Hasher} using {@code MD5} (128 bits).
     *
     * @return an instance of a {@link Hasher} using {@code MD5} (128 bits)
     */
    public static Hasher md5() {
        return Md5Hasher.getInstance();
    }
}
