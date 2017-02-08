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

package fr.inria.atlanmod.neoemf.data.berkeleydb.util.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Simple class to serialize/deserialize objects from/to byte arrays.
 */
@ParametersAreNonnullByDefault
public final class Serializer {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private Serializer() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Serializes an {@code Object} to a byte array for storage/serialization.
     *
     * @param value the object to serialize to bytes
     *
     * @return the serialized object as a byte array
     */
    @Nonnull
    public static <T> byte[] serialize(T value) {
        checkNotNull(value);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(512); ObjectOutput out = new ObjectOutputStream(baos)) {
            out.writeObject(value);
            out.flush();
            return baos.toByteArray();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deserializes a single {@code Object} from an array of bytes.
     *
     * @param data the serialized object as a byte array
     *
     * @return the deserialized object
     */
    @SuppressWarnings("unchecked")
    @Nonnull
    public static <T> T deserialize(byte[] data) {
        checkNotNull(data);

        try (ByteArrayInputStream bis = new ByteArrayInputStream(data); ObjectInput in = new ObjectInputStream(bis)) {
            return (T) in.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
