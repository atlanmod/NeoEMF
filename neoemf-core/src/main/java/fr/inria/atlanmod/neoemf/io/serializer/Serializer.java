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

import java.io.Serializable;

import javax.annotation.Nonnull;

/**
 * An object that is responsible of {@link Object} to {@code byte[]} encoding.
 *
 * @param <T> the type of serialized objects
 */
public interface Serializer<T> {

    /**
     * Write an object of type {@code T} to a {@code byte} array.
     * <p>
     * <b>Note:</b> The {@code value} must implement {@link Serializable}.
     *
     * @param value the object to serialize
     *
     * @return the serialized object as a byte array
     */
    @Nonnull
    byte[] serialize(T value);

    /**
     * Read (assemble) an object of type {@code T} from the given {@code data}.
     *
     * @param data the serialized object as a byte array
     *
     * @return the deserialized object
     */
    @Nonnull
    T deserialize(byte[] data);
}
