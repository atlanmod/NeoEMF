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
 * The factory of {@link Hasher}.
 */
public class HasherFactory {

    private HasherFactory() {
    }

    /**
     * Returns an instance of a {@link Hasher} using {@code MD5}.
     *
     * @return an instance of a {@link Hasher} using {@code MD5}
     */
    public static Hasher md5() {
        return Md5Hasher.getInstance();
    }
}
