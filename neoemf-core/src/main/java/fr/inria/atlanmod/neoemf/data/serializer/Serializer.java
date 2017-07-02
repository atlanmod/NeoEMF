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

package fr.inria.atlanmod.neoemf.data.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.WillNotClose;

/**
 * An object that is responsible of {@link Object} to {@code byte[]} encoding and decoding.
 *
 * @param <T> the type of (de)serialized objects
 */
@ParametersAreNonnullByDefault
public interface Serializer<T> {

    /**
     * Write an object of type {@link T} to a {@code byte} array.
     * <p>
     * <b>Note:</b> The {@code value} <b>must</b> implement {@link Serializable}.
     *
     * @param t the object to serialize
     *
     * @return the serialized object as a byte array
     *
     * @throws IOException if an I/O error occurs during the serialization
     */
    @Nonnull
    default byte[] serialize(T t) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream out = new ObjectOutputStream(baos)) {
            serialize(t, out);
            out.flush();
            return baos.toByteArray();
        }
    }

    /**
     * Write an object of type {@link T} to the given {@code out}.
     * <p>
     * <b>Note:</b> The {@code value} <b>must</b> implement {@link Serializable}.
     *
     * @param t   the object to serialize
     * @param out the output stream
     *
     * @throws IOException if an I/O error occurs during the serialization
     */
    void serialize(T t, @WillNotClose DataOutput out) throws IOException;

    /**
     * Reads and assembles an object of type {@link T} from the given {@code data}.
     *
     * @param data a byte array
     *
     * @return the deserialized object
     *
     * @throws IOException if an I/O error occurs during the deserialization
     */
    @Nonnull
    default T deserialize(byte[] data) throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return deserialize(in);
        }
    }

    /**
     * Reads and assembles an object of type {@link T} from the given {@code in}.
     *
     * @param in the input stream
     *
     * @return the deserialized object
     *
     * @throws IOException if an I/O error occurs during the deserialization
     */
    @Nonnull
    T deserialize(@WillNotClose DataInput in) throws IOException;
}
