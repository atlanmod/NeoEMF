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
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * The default implementation of a {@link Serializer}.
 *
 * @param <T> the type of serialized objects
 */
@ParametersAreNonnullByDefault
class ObjectSerializer<T> implements Serializer<T> {

    @Nonnull
    public byte[] serialize(T value) {
        checkNotNull(value);
        checkArgument(value instanceof Serializable,
                "ObjectSerializer requires a Serializable payload but received an object of type " + value.getClass().getName());

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutput out = new ObjectOutputStream(baos)) {
            out.writeObject(value);
            out.flush();
            return baos.toByteArray();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    public T deserialize(byte[] data) {
        checkNotNull(data);

        try (ByteArrayInputStream bais = new ByteArrayInputStream(data); ObjectInput in = new ObjectInputStream(bais)) {
            return (T) in.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
