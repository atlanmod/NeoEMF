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

package fr.inria.atlanmod.neoemf.data.berkeleydb.serializer;

import fr.inria.atlanmod.neoemf.annotations.Experimental;

import javax.annotation.Nonnull;

/**
 * Simple class to serialize/deserialize objects to byte arrays.
 *
 * @param <T> the type of {@link Object} to serialize/deserialize
 */
@Experimental
public interface Serializer<T> {

    /**
     * Serializes an {@code Object} to a byte array for storage/serialization.
     *
     * @param value the object to serialize to bytes
     *
     * @return the serialized object as a byte array
     */
    byte[] serialize(@Nonnull T value);

    /**
     * Deserializes a single {@code Object} from an array of bytes.
     *
     * @param data the serialized object as a byte array
     *
     * @return the deserialized object
     */
    T deserialize(@Nonnull byte[] data);
}
