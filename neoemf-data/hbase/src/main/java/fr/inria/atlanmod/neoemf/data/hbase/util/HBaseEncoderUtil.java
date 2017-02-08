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

package fr.inria.atlanmod.neoemf.data.hbase.util;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.hbase.util.Bytes;
import org.eclipse.emf.ecore.EReference;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Utility class that is responsible of {@link Object} to {@link Byte} encoding. This class is used to ensure that HBase
 * keys have the same size, and provides an uniformized API to encode strings and {@link EReference}.
 */
@ParametersAreNonnullByDefault
public class HBaseEncoderUtil {

    /**
     * Expected length (in {@code bytes}) of stored elements.
     */
    public static final int UUID_LENGTH = 23;

    /**
     * The default separator used to serialize {@link Collection}s.
     */
    public static final char VALUE_SEPERATOR_DEFAULT = ',';

    private HBaseEncoderUtil() {
    }

    /**
     * Decodes the provided {@code byte} array into an array of {@link String} representing {@link EReference}s.
     *
     * @param values the HBase values to decode
     *
     * @return an array of {@link String}s representing the {@link EReference}s decoded from the database
     *
     * @throws NullPointerException     if the given {@code values} is null
     * @throws IllegalArgumentException if the length of {@code values} is not a multiple of {@code UUID_LENGTH}
     * @see HBaseEncoderUtil#toBytesReferences(String[])
     */
    public static String[] toStringsReferences(byte... values) {
        checkNotNull(values);

        checkArgument(values.length % (UUID_LENGTH + 1) == UUID_LENGTH);
        int length = (values.length + 1) / (UUID_LENGTH + 1);

        Iterator<String> iterator = Splitter.on(VALUE_SEPERATOR_DEFAULT).split(Bytes.toString(values)).iterator();
        String[] strings = new String[length];
        int index = 0;
        while (iterator.hasNext()) {
            strings[index++] = iterator.next();
        }
        return strings;
    }

    /**
     * Encodes the provided {@link String} array into an array of {@code bytes} that can be stored in the database.
     *
     * @param values an array of {@link String}s representing the {@link EReference}s to encode.
     *
     * @return an array of {@code bytes}
     *
     * @throws NullPointerException if the value to encode is {@code null}
     * @see HBaseEncoderUtil#toStringsReferences(byte[])
     */
    public static byte[] toBytesReferences(String... values) {
        return Joiner.on(VALUE_SEPERATOR_DEFAULT).join(values).getBytes(Charsets.UTF_8);
    }

    /**
     * Encodes an array of {@link String}s into an array of {@code bytes} that can be stored in the database.
     *
     * @param values the array to encode
     *
     * @return the encoded {@code byte} array
     *
     * @see HBaseEncoderUtil#toStrings(byte[])
     */
    public static <V> byte[] toBytes(V... values) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream stream = new ObjectOutputStream(baos)) {
            stream.writeObject(values);
            stream.flush();
            return baos.toByteArray();
        }
        catch (IOException e) {
            NeoLogger.error("Unable to convert {0} to byte[]", Arrays.toString(values));
        }
        return null;
    }

    /**
     * Decodes an array of {@code bytes} into an array of {@link String}s.
     *
     * @param bytes the {@code byte} array to decode
     *
     * @return the decoded {@link String} array
     *
     * @throws NullPointerException if the given array is {@code null}
     * @see HBaseEncoderUtil#toBytes(Object[])
     */
    public static <V> V[] toStrings(byte... bytes) {
        checkNotNull(bytes);

        V[] strings = null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            strings = (V[]) objectInputStream.readObject();
        }
        catch (IOException e) {
            NeoLogger.error("Unable to convert {0} to String[]", Arrays.toString(bytes));
        }
        catch (ClassNotFoundException e) {
            NeoLogger.error(e);
        }
        finally {
            IOUtils.closeQuietly(objectInputStream);
        }
        return strings;
    }
}
