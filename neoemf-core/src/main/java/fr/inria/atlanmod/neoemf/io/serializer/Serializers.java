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

package fr.inria.atlanmod.neoemf.io.serializer;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The factory that creates {@link Serializer} instances.
 */
@ParametersAreNonnullByDefault
public final class Serializers {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private Serializers() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Creates a new {@link Serializer} for objects, using Java serialization.
     *
     * @param <T> the type of serialized objects
     *
     * @return a new serializer
     */
    public static <T> Serializer<T> forObjects() {
        return new ObjectSerializer<>();
    }
}
