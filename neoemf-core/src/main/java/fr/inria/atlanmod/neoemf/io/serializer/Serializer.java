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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.WillNotClose;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * An object that is responsible of {@link Object} to {@code byte[]} encoding.
 *
 * @param <T> the type of serialized objects
 */
@ParametersAreNonnullByDefault
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
    default byte[] serialize(T value) {
        checkNotNull(value);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(512)) {
            serialize(value, baos);
            return baos.toByteArray();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Write an object of type {@code T} to the given {@code stream}.
     * <p>
     * <b>Note:</b> The {@code value} must implement {@link Serializable}.
     *
     * @param value  the object to serialize
     * @param stream the output stream
     */
    void serialize(T value, @WillNotClose OutputStream stream);

    /**
     * Read (assemble) an object of type {@code T} from the given {@code data}.
     *
     * @param data a byte array
     *
     * @return the deserialized object
     */
    @Nonnull
    default T deserialize(byte[] data) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data)) {
            return deserialize(bais);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Read (assemble) an object of type {@code T} from the given {@code stream}.
     *
     * @param stream the input stream
     *
     * @return the deserialized object
     */
    @Nonnull
    T deserialize(@WillNotClose InputStream stream);
}
