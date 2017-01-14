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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

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

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Utility class that is responsible of {@link Object} to {@link Byte} encoding. This class
 * is used to ensure that HBase keys have the same size, and provides an uniformized API to
 * encode strings and {@link EReference}.
 */
public class HBaseEncoderUtil {

    /**
     * Expected length (in {@code bytes}) of stored elements.
     */
    public static final int UUID_LENGTH = 23;
    
    /**
     * The default separator used to serialize {@link Collection}s.
     */
    public static final char VALUE_SEPERATOR_DEFAULT = ',';

    /**
     * Decodes the provided {@code byte} array into an array of {@link String} representing {@link EReference}s.
     * @param value the HBase value to decode
     * @return an array of {@link String}s representing the {@link EReference}s decoded from the database
     * 
     * @throws NullPointerException if the given {@code value} is null
     * @throws IllegalArgumentException if the length of {@code value} is not a multiple of {@code UUID_LENGTH}
     * 
     * @see HBaseEncoderUtil#toBytesReferences(String[])
     */
    public static String[] toStringsReferences(byte[] value) {
        if (nonNull(value)) {
            checkArgument(value.length % (UUID_LENGTH + 1) == UUID_LENGTH);
            int length = (value.length + 1) / (UUID_LENGTH + 1);

            Iterator<String> iterator = Splitter.on(VALUE_SEPERATOR_DEFAULT).split(Bytes.toString(value)).iterator();
//            List<String>  strings = new LinkedList<String>();
            String[] strings = new String[length];
            int index = 0;
            while (iterator.hasNext()) {
//                strings.add(iterator.next());
                strings[index++] = iterator.next();
            }
//            return strings.toArray(new String[strings.size()]);
            return strings;
        }
        return null;
    }

    /**
     * Encodes the provided {@link String} array into an arrat of {@code bytes} that can be stored in the database
     * @param strings an array of {@link String}s representing the {@link EReference}s to encode.
     * @return an array of {@code bytes} 
     * 
     * @throws NullPointerException if the value to encode is {@code null}
     * 
     * @see HBaseEncoderUtil#toStringsReferences(byte[])
     */
    public static byte[] toBytesReferences(String[] strings) {
        if (nonNull(strings)) {
            return Joiner.on(VALUE_SEPERATOR_DEFAULT).join(strings).getBytes(Charsets.UTF_8);
        }
        return null;
    }

    /**
     * Encodes an array of {@link String}s into an array of {@code bytes} that can be stored in the database.
     * @param strings the array to encode
     * @return the encoded {@code byte} array
     * 
     * @see HBaseEncoderUtil#toStrings(byte[])
     */
    public static byte[] toBytes(String[] strings) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(strings);
            objectOutputStream.flush();
            objectOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException e) {
            NeoLogger.error("Unable to convert ''{0}'' to byte[]", Arrays.toString(strings));
        }
        return null;
    }

    /**
     * Decodes an array of {@code bytes} into an array of {@link String}s.
     * @param bytes the {@code byte} array to decode
     * @return the decoded {@link String} array
     * 
     * @throws NullPointerException if the given array is {@code null}
     * 
     * @see HBaseEncoderUtil#toBytes(String[])
     */
    public static String[] toStrings(byte[] bytes) {
        if (isNull(bytes)) {
            return null;
        }

        String[] strings = null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            strings = (String[]) objectInputStream.readObject();
        }
        catch (IOException e) {
            NeoLogger.error("Unable to convert ''{0}'' to String[]", Arrays.toString(bytes));
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
