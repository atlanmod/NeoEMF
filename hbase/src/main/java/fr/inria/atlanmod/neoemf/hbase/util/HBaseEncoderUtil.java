/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.hbase.util;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Iterator;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class HBaseEncoderUtil {

    public static final int UUID_LENGTH = 23;

    public static final char VALUE_SEPERATOR_DEFAULT = ',';

    public static String[] toStringsReferences(byte[] value) {
        if (nonNull(value)) {
            checkArgument(value.length % (UUID_LENGTH + 1) == UUID_LENGTH);
            int length = (value.length + 1) / (UUID_LENGTH + 1);

            Iterator<String> iterator = Splitter.on(VALUE_SEPERATOR_DEFAULT).split(Bytes.toString(value)).iterator();
            //List<String>  strings = new LinkedList<String>();
            String[] strings = new String[length];
            int index = 0;
            while (iterator.hasNext()) {
                //strings.add(iterator.next());
                strings[index++] = iterator.next();
            }
            //return strings.toArray(new String[strings.size()]);
            return strings;
        }
        return null;
    }

    public static byte[] toBytesReferences(String[] strings) {
        if (nonNull(strings)) {
            return Joiner.on(VALUE_SEPERATOR_DEFAULT).join(strings).getBytes(Charsets.UTF_8);
        }
        return null;
    }

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

    public static String[] toStrings(byte[] bytes) {
        if (isNull(bytes)) {
            return null;
        }

        String[] result = null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            result = (String[]) objectInputStream.readObject();
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
        return result;
    }
}
