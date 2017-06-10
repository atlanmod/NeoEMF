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

package fr.inria.atlanmod.common.io.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.WillNotClose;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;
import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

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
     * Creates a new {@link Serializer} for generic objects, using Java serialization.
     *
     * @param <T> the type of (de)serialized objects
     *
     * @return a new serializer
     */
    public static <T> Serializer<T> forGenerics() {
        return new Serializer<T>() {
            @Override
            public void serialize(T value, @WillNotClose OutputStream stream) {
                checkNotNull(value);
                checkNotNull(stream);
                checkArgument(Serializable.class.isInstance(value),
                        "Serializer requires a Serializable payload but received an object of type " + value.getClass().getName());

                try (ObjectOutput output = new ObjectOutputStream(stream)) {
                    output.writeObject(value);
                    output.flush();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Nonnull
            @Override
            @SuppressWarnings("unchecked")
            public T deserialize(@WillNotClose InputStream stream) {
                checkNotNull(stream);

                try (ObjectInput input = new ObjectInputStream(stream)) {
                    return (T) input.readObject();
                }
                catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
