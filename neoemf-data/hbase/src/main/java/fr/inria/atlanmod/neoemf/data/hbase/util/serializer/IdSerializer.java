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

package fr.inria.atlanmod.neoemf.data.hbase.util.serializer;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;

import org.apache.hadoop.hbase.util.Bytes;
import org.eclipse.emf.ecore.EReference;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Utility class that is responsible of {@link fr.inria.atlanmod.neoemf.core.Id} to {@link Byte} encoding.
 * This class is used to ensure that HBase keys have the same size, and provides an uniformized API to encode strings
 * and {@link EReference}.
 */
@ParametersAreNonnullByDefault
public class IdSerializer {

    /**
     * Expected length (in {@code bytes}) of stored elements.
     */
    public static final int UUID_LENGTH = 23;

    /**
     * The default separator used to serialize {@link Collection}s.
     */
    public static final char VALUE_SEPERATOR_DEFAULT = ',';

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private IdSerializer() {
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
    public static byte[] serialize(Id value) {
        checkNotNull(value);

        return Bytes.toBytes(value.toString());
    }

    /**
     * Serializes an {@code Object} to a byte array for storage/serialization.
     *
     * @param value the object to serialize to bytes
     *
     * @return the serialized object as a byte array
     */
    @Nonnull
    public static byte[] serializeArray(Id[] value) {
        checkNotNull(value);

        return Bytes.toBytes(Joiner.on(VALUE_SEPERATOR_DEFAULT).join(value));
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
    public static Id deserialize(byte[] data) {
        checkNotNull(data);
        checkArgument(data.length % (UUID_LENGTH + 1) == UUID_LENGTH);

        return StringId.of(Bytes.toString(data));
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
    public static Id[] deserializeArray(byte[] data) {
        checkNotNull(data);

        checkArgument(data.length % (UUID_LENGTH + 1) == UUID_LENGTH);
        int length = (data.length + 1) / (UUID_LENGTH + 1);

        Id[] ids = new Id[length];
        int index = 0;

        for (String s : Splitter.on(VALUE_SEPERATOR_DEFAULT).split(Bytes.toString(data))) {
            ids[index++] = StringId.of(s);
        }

        return ids;
    }
}
